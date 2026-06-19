package com.site.blog.my.core.controller.admin;

import com.site.blog.my.core.config.Constants;
import com.site.blog.my.core.result.Result;
import com.site.blog.my.core.util.MyBlogUtils;
import com.site.blog.my.core.util.ResultGenerator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;
import java.util.Random;

@Slf4j
@Controller
@RequestMapping("/admin")
public class UploadController {

    @PostMapping({"/upload/file"})
    @ResponseBody
    public Result<Object> upload(HttpServletRequest httpServletRequest, @RequestParam("file") MultipartFile file) throws URISyntaxException {
        String fileName = file.getOriginalFilename();
        String suffixName = Objects.requireNonNull(fileName).substring(fileName.lastIndexOf("."));
        //生成文件名称通用方法
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss");
        Random r = new Random();
        String newFileName = sdf.format(new Date()) + r.nextInt(100) + suffixName;
        File fileDirectory = new File(Constants.FILE_UPLOAD_DIC);
        //创建文件
        File destFile = new File(Constants.FILE_UPLOAD_DIC + newFileName);
        try {
            if (!fileDirectory.exists()) {
                if (!fileDirectory.mkdir()) {
                    throw new IOException("文件夹创建失败,路径为：" + fileDirectory);
                }
            }
            file.transferTo(destFile);
            Result<Object> resultSuccess = ResultGenerator.genSuccessResult();
            resultSuccess.setData(MyBlogUtils.getHost(new URI(httpServletRequest.getRequestURL() + "")) + "/upload/" + newFileName);
            return resultSuccess;
        } catch (IOException e) {
            log.error("文件上传失败", e);
            return ResultGenerator.genFailResult("文件上传失败");
        }
    }

}
