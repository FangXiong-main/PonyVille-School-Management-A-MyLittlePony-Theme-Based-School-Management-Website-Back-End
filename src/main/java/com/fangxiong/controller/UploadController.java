package com.fangxiong.controller;

import com.fangxiong.pojo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/upload")
public class UploadController {
    //使用@Value注解注入配置文件中的属性使得文件路径可以通过修改配置文件动态改变
    @Value("${fangxiong.file.path}")
    private String fileContainer;
    @PostMapping
    public Result upload(MultipartFile file) throws IOException {
        log.info("文件容器：{}",fileContainer);
        log.info("文件上传：{}",file.getOriginalFilename());
        //为了防止文件被覆盖，需要重命名 substring裁切出来的字符串包含'.'
        String newFileName = UUID.randomUUID().toString()+file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
        String url = fileContainer+"/"+newFileName;
        file.transferTo(new File(url));
        return Result.success(url);
    }
}
