package com.shengaike.controller;

import com.shengaike.util.Result;
import org.apache.velocity.runtime.resource.loader.ResourceLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;

/**
 * Rev 0006. 接收上传图片并保存到本地.
 */

@RestController
@RequestMapping("/upload")
public class UploadImgController {

    @Autowired(required = false)
    private ResourceLoader resourceLoader;

    @Value(value = "/Users/hardy/IdeaProjects/uploadImg")
    private String uploadPath;


    /**
     * 时间格式化
     */
    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd/");

    @PostMapping("/uploadImg")
    public Result uploadFile(@RequestParam("file") MultipartFile multipartFile) {
        try {
            if (multipartFile.isEmpty()) {
                return Result.error("文件为空,请重新选择!");
            }
            // 上传的图片全部保存在 "/Users/fangjinliang/Java/Upload/MyManagerPro/uploadImg/" 目录下
            File file = new File(uploadPath);
            if (!file.exists()) {
                // 创建完整的目录
                file.mkdirs();
            }
            // 获取文件原始名(包含后缀名)
            String orgName = multipartFile.getOriginalFilename();
            // 获取文件名（不包括后缀）
            String prefixName = orgName.substring(0, orgName.lastIndexOf("."));
            // 获取文件后缀名
            String suffixName = orgName.substring(orgName.lastIndexOf("."));
            // 这是处理后的新文件名
            String fileName;
            if (orgName.contains(".")) {
                // 示例：avatar.123.png，经过处理后得到：avatar.123_1661136943533.png
                fileName = prefixName + "_" + System.currentTimeMillis() + suffixName;
            } else {
                // 上传的图片没有后缀（这压根就不算是一个正常的图片吧？）
                return Result.error("上传图片格式错误,请重新选择！");
            }
            String savePath = file.getPath() + File.separator + fileName;
            File saveFile = new File(savePath);
            // 将上传的文件复制到指定目录
            FileCopyUtils.copy(multipartFile.getBytes(), saveFile);            // 返回给前端的图片保存路径；前台可以根据返回的路径拼接完整地址，即可在浏览器上预览该图片
            String path = "uploadImg" + File.separator + fileName;
            if (path.contains("\\")) {
                path = path.replace("\\", "/");
            }
            return Result.ok().put("data",path);
        } catch (IOException e) {
            return Result.error(e.getMessage());
        }
    }
}