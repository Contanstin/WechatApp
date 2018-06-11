package com.hpmont.controller.wechat;

import com.github.pagehelper.PageInfo;
import com.hpmont.controller.BaseController;
import com.hpmont.domain.page.PageSearch;
import com.hpmont.domain.wechat.Slideshow;
import com.hpmont.service.wechat.ISlideshowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Date;
import java.util.List;

/**
 * Created by 徐 on 2018/5/21.
 */
@Controller
@RequestMapping("/slideshow")
public class SlideshowController extends BaseController{

    @Autowired
    private ISlideshowService slideshowService;

    @Value("${file.slideshow.upload.base}")
    private String baseUrl;
    @Value("${project.path}")
    private String projectPath;

    @RequestMapping(value = "/list")
    public String findAll(Model model, PageSearch search){
        try {
            PageInfo<Slideshow> list = slideshowService.findSlideshowList(search);
            model.addAttribute("page",list);
            model.addAttribute("search",search);
        } catch (Exception e) {
            logger.error("查询轮播图列表出错", e);
        }
        return "/slideshow/list";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    @ResponseBody
    public String edit(MultipartFile file, Slideshow slideshow){
        String realName="";
        try {
            if (file.getSize() > 0) {
                String filename = file.getOriginalFilename();
                File f = new File(baseUrl, filename);
                if (!f.getParentFile().exists()) {
                    f.getParentFile().mkdirs();
                }
                realName= new Date().getTime()+filename;
                file.transferTo(new File(baseUrl + realName));
                slideshow.setImageUrl(projectPath+"slideshow/showPic?realName="+realName);
                slideshow.setRealName(realName);
            }
            if (null==slideshow.getId()){
                slideshowService.insert(slideshow);
            }else {
                slideshowService.update(slideshow);
            }
        } catch (Exception e) {
            logger.error("编辑轮播图失败",e);
            return errorResult("编辑轮播图失败");
        }
        return success();
    }


    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public String delete(@RequestParam Integer id,String realName){
        try {
            File file = new File(baseUrl+realName);
            if (file.exists() && file.isFile()) {
               file.delete();
            }
            slideshowService.delete(id);
        } catch (Exception e) {
            logger.error("删除轮播图失败",e);
            return errorResult("删除轮播图失败");
        }
        return success();
    }

    @RequestMapping("/download")
    public ResponseEntity<byte[]> String(String realName) throws IOException {
        File file=new File(baseUrl+realName);
        return getResponse(realName,file);

    }

    private ResponseEntity<byte[]> getResponse(String filename,File file) throws IOException {
        byte[] size=null;
        ResponseEntity<byte[]> response=null;
        InputStream in=null;
        try {
            in= new FileInputStream(file);
            size= new byte[in.available()];
            in.read(size);
            HttpHeaders headers= new HttpHeaders();
            filename=new String(filename.getBytes("gbk"),"iso8859-1");
            headers.add("Content-Disposition", "attachment;filename="+filename);
            HttpStatus status= HttpStatus.OK;
            response=new ResponseEntity<byte[]>(size,headers,status);
        } catch (FileNotFoundException e) {
            logger.error("文件不存在",e);
            e.printStackTrace();
        } finally{
            in.close();
        }
        return response;
    }

    @RequestMapping("/showPic")
    public void showPicture(String realName , HttpServletResponse response){
        File imgFile = new File(baseUrl+ realName);
        responseFile(response, imgFile);
    }

    /**
     * 响应输出图片文件
     * @param response
     * @param imgFile
     */
    private void responseFile(HttpServletResponse response, File imgFile) {
        try(InputStream is = new FileInputStream(imgFile);
            OutputStream os = response.getOutputStream();){
            byte [] buffer = new byte[1024]; // 图片文件流缓存池
            while(is.read(buffer) != -1){
                os.write(buffer);
            }
            os.flush();
        } catch (IOException ioe){
            ioe.printStackTrace();
        }
    }



}
