package com.hpmont.controller.wechat;

import com.github.pagehelper.PageInfo;
import com.hpmont.controller.BaseController;
import com.hpmont.domain.search.SearchManual;
import com.hpmont.domain.wechat.DictManual;
import com.hpmont.domain.wechat.WechatManual;
import com.hpmont.service.wechat.IWechatManualService;
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

import java.io.*;
import java.util.Date;
import java.util.List;

/**
 * Created by 徐 on 2018/5/28.
 */
@Controller
@RequestMapping("/manual")
public class WechatManualController extends BaseController{

    @Autowired
    private IWechatManualService manualService;

    @Value("${file.manual.upload.base}")
    private String baseUrl;
    @Value("${project.path}")
    private String projectPath;

    @RequestMapping(value = "/list")
    public String findAll(Model model, SearchManual search){
        try {
            PageInfo<WechatManual> list = manualService.findManualList(search);
            model.addAttribute("page",list);
            model.addAttribute("search",search);
        } catch (Exception e) {
            logger.error("查询手册列表出错", e);
        }
        return "/manual/list";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    @ResponseBody
    public String edit(@RequestParam MultipartFile[] file, WechatManual manual){
        String realName="";
        try {
            if (null!=file&&file.length>0){
                for (MultipartFile multipartFile : file) {
                    if (multipartFile.getSize() > 0) {
                        if (null!=manual.getRealName()){
                            File df = new File(baseUrl+manual.getRealName());
                            if (df.exists() && df.isFile()) {
                                df.delete();
                            }
                        }
                        String filename = multipartFile.getOriginalFilename();
                        File f = new File(baseUrl, filename);
                        if (!f.getParentFile().exists()) {
                            f.getParentFile().mkdirs();
                        }
                        if (manual.getManualName()==null)
                        manual.setManualName(filename.substring(0, filename.lastIndexOf(".")));
                        manual.setManualFormat(filename.substring(filename.lastIndexOf(".") + 1));
                        realName= new Date().getTime()+"."+manual.getManualFormat();
                        multipartFile.transferTo(new File(baseUrl + realName));
                        manual.setManualUrl(projectPath+"manual/download?realName="+realName);
                        manual.setRealName(realName);
                    }
                    if (null==manual.getId()){
                        manualService.insert(manual);
                    }else {
                        manualService.update(manual);
                    }
                }
            }
        } catch (Exception e) {
            logger.error("编辑手册失败",e);
            return errorResult("编辑手册失败");
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
            manualService.delete(id);
        } catch (Exception e) {
            logger.error("删除手册失败",e);
            return errorResult("删除手册失败");
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

}
