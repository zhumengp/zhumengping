package org.com.tianzmp.controller;

import java.io.File;
import java.io.FileOutputStream;

import javax.servlet.http.HttpServletRequest;

import org.com.tianzmp.common.result.Result;
import org.com.tianzmp.common.result.ResultStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;


@Controller
@RequestMapping("/file")
public class FileUploadController {
	
	private final Logger log = LoggerFactory.getLogger(FileUploadController.class);
	
	/**
	 * 图片上传方法
	 * @param file
	 * @param request
	 * @return
	 * @throws Exception
	 */
    @RequestMapping("/upload")
    @ResponseBody
    public Object upload(MultipartFile file, HttpServletRequest request) throws  Exception{
        String path = "F:/images/uploadFile";
        String[] split = path.split("/");
        String a = split[split.length-1];
        String newFileName = String.valueOf( System.currentTimeMillis());
        File fileDir = new File(path);
        if (!fileDir.exists()) {
            fileDir.mkdirs();
        }
        String filename=file.getOriginalFilename();
        String extensionName = filename.substring(filename.lastIndexOf(".") + 1);
        try {
             String imgPath = a + "/"+newFileName + "." +extensionName;
             System.out.println(imgPath);
             String upload = "http://127.0.0.1:81/"+imgPath;
             FileOutputStream out = new FileOutputStream(path+"/"+newFileName + "." +extensionName);
             out.write(file.getBytes());
             out.flush();
             out.close();
             return new Result(ResultStatus.SUCCESS,upload);
        } catch (Exception e) {
             log.error("系统异常", e);
             return new Result(ResultStatus.ERROR,null);
        }
     }
}
