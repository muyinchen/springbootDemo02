package com.zhiqiu.web.upcontroller;

import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * Author  知秋
 * Created by kauw on 2016/10/1.
 */
@Controller//主要看配置类的配置就可，没什么东西
public class UpLoadController {
    @RequestMapping(value = "/upload",method = RequestMethod.POST)
    public @ResponseBody String upload(MultipartFile file){
        try {
            FileUtils.writeByteArrayToFile(new File("f:/upload/"+file.getOriginalFilename()),
                    file.getBytes());
            return "ok";
        } catch (IOException e) {
            e.printStackTrace();
            return "wrong";
        }
    }
}
