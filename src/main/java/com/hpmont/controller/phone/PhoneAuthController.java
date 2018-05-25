package com.hpmont.controller.phone;

import com.github.pagehelper.PageInfo;
import com.hpmont.controller.BaseController;
import com.hpmont.domain.search.SearchPhoneAuth;
import com.hpmont.domain.phone.PhoneAuth;
import com.hpmont.service.phone.IPhoneAuthService;
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

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import java.io.*;
import java.util.List;

/**
 * Created by 徐 on 2018/5/17.
 */
@Controller
@RequestMapping("/phone_auth")
public class PhoneAuthController extends BaseController{

    @Resource(name = "PhoneAuthService")
    private IPhoneAuthService phoneAuthService;

    @Value("${file.upload.base}")
    private String baseUrl;



    @RequestMapping(value = "/list")
    public String findAll(Model model, SearchPhoneAuth search){
        try {
            search.setAdminId(getCurrUserId());
            PageInfo<PhoneAuth> list = phoneAuthService.findPhoneAuthList(search);
            model.addAttribute("page",list);
            model.addAttribute("search",search);
        } catch (Exception e) {
            logger.error("查询授权编码列表出错", e);
        }
        return "/phone_auth/list";
    }

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    @ResponseBody
    public String edit(MultipartFile file, HttpServletRequest request){
        try {

            String realPath =  request.getSession().getServletContext().getRealPath("/upload");

            //上传文件名
            String filename = file.getOriginalFilename();
            //将上传文件保存到一个目标文件当中
//            file.transferTo(new File(baseUrl+filename));
            File f = new File(realPath,filename);
            if (!f.getParentFile().exists()) {
                f.getParentFile().mkdirs();
            }
            file.transferTo(new File(realPath + File.separator + filename));
        } catch (Exception e) {
            logger.error("编辑授权编码失败",e);
            return errorResult("编辑授权编码失败");
        }
        return success();
    }

/*    @RequestMapping("/download")
    public ResponseEntity<byte[]> String(HttpServletRequest request) throws IOException {
        byte[] size=null;
//        ServletContext servletContext =request.getSession().getServletContext();
        String filename="1.pdf";
//        String path=servletContext.getRealPath("/WEB-INF/"+filename);
        File file=new File(baseUrl+filename);
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

            e.printStackTrace();
        }finally{
            in.close();
        }
        return response;

    }*/

    @RequestMapping(value = "/findByVersionType")
    @ResponseBody
    public  List<PhoneAuth> findByVersionType(@RequestParam Integer versionType){
        List<PhoneAuth> list=null;
        try {
           list= phoneAuthService.findByVersionType(versionType,getCurrUserId());
        } catch (Exception e) {
            logger.error("根据软件版本获取授权码失败",e);
        }
        return list;
    }


}
