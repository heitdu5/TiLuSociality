package com.club.oss.controller;


import com.club.oss.service.FileService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.List;

/**
 * 文件操作controller
 *
 */
@RestController
public class FileController {

    @Resource
    private FileService fileService;

    /**
     * 列出所有桶
     * @return
     * @throws Exception
     */
    @RequestMapping("/testGetAllBuckets")
    public String testGetAllBuckets() throws Exception {
        List<String> allBucket = fileService.getAllBucket();
        return allBucket.get(0);
    }

    /**
     *
     * @param bucketName
     * @param objectName
     * @return
     * @throws Exception
     */
    @RequestMapping("/getUrl")
    public String getUrl(String bucketName, String objectName) throws Exception {
        return fileService.getUrl(bucketName, objectName);
    }

    /**
     * 上传文件
     * 要返回一个地址是多少的信息
     */
    @RequestMapping("/upload")
    public String upload(MultipartFile uploadFile, String bucket, String objectName)  {

        return fileService.uploadFile(uploadFile,bucket,objectName);
    }

}
