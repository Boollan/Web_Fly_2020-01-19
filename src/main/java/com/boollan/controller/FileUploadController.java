package com.boollan.controller;

import java.io.IOException;
import java.io.File;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * @author Boollan
 */
@RequestMapping(path = "/File", method = {RequestMethod.GET, RequestMethod.POST})
public class FileUploadController {
    /**
     * 用于文件上传的类
     * @param uploadFile 文件的二进制数据
     * @param request 请求头
     * @return 返回状态码
     */
    @RequestMapping(value = "/fileUpload",method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView handleFormUpload(@RequestParam("uploadFile") List<MultipartFile> uploadFile, HttpServletRequest request) {

         String username1 = request.getParameter("username");
        System.out.println(username1);
        //判定文件是否存在
        Map<String, String> info = new HashMap<>();
        String message = "message";
        String originalFilename = null;
        String dirPath = null;
        if (!uploadFile.isEmpty()) {
            //遍历资源文件
            for (MultipartFile file : uploadFile) {
                //获取用户上传的文件名
                originalFilename = file.getOriginalFilename();
                //获取相对路径的计算机绝对路径用来存储文件
                dirPath = request.getServletContext().getRealPath("/WEB-INF/page/upload/");
                //判断指定目录是否有当前文件夹
                File filePath = new File(dirPath);
                //如果文件夹不存在则创建文件夹
                if (!filePath.exists()) {
                    filePath.mkdirs();
                }
                //使用UUID重新命名上传的文件的名称(上传人_uuid_初始化文件名称)
                String newFileName = "/" + "boollan" + "_" + UUID.randomUUID()+originalFilename.substring(originalFilename.lastIndexOf("."));
                try {
                    //使用MultipartFile接口完成文件上传到指定位置
                    file.transferTo(new File(dirPath + newFileName));
                } catch (IOException e) {
                    e.printStackTrace();
                    //抛出异常
                    info.put(message, "上传文件过程中出现问题请重新上传!");
                    return new ModelAndView(new MappingJackson2JsonView(), info);
                }
            }
            info.put(message, "上传文件成功!");
            info.put("FileName", originalFilename);
            return new ModelAndView(new MappingJackson2JsonView(), info);
        }
        info.put(message, "上传文件过程中出现问题请重新上传!");
        return new ModelAndView(new MappingJackson2JsonView(), info);
    }

    /**
     * 文件上传测试页面
     *
     * @return 返回页面
     */
    @RequestMapping(value = "/fileIndex")
    @ResponseBody
    public ModelAndView passwordManagement() {
        return new ModelAndView("FileUpdate/Html/fileupload");
    }

}