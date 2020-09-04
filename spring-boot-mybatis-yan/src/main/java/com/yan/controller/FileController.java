package com.yan.controller;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * controller - 文件管理
 * @author master-yan
 *
 */
@RestController
public class FileController {

	@Value("${yan.file.path}")
	private String filePath;
	
	/**
	 * 上传文件
	 * 文件类型限制跟大小限制需要去spring boot配置文件设置
	 * @param file
	 * @return
	 * @throws IOException
	 */
	@PostMapping(value = "/file/upload")
    public String upload(@RequestParam("file") MultipartFile file) throws IOException {
        if (file.isEmpty()) {
            return "文件为空";
        }
        // 获取文件名
        String sourceFileName = file.getOriginalFilename();
        // 获取后缀名
        String suffix = sourceFileName.substring(sourceFileName.lastIndexOf("."), sourceFileName.length());
        // 生成新文件名
        String fileName = UUID.randomUUID().toString().replace("-", "");
        // 创建保存路径和文件名
        String path = new StringBuffer(filePath).append("/").append(fileName).append(suffix).toString();
        File targetFile = new File(path);
        // 检测是否存在目录
        if (!targetFile.getParentFile().exists()) {
        	// 新建文件夹
            targetFile.getParentFile().mkdirs();
        }
        // 文件写入
        file.transferTo(targetFile);
        return "上传成功";
    }

	/**
	 * 下载文件
	 * 需要做权限控制的文件下载
	 * @param fileName
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException 
	 */
    @GetMapping("/file/download/{fileName}")
    public void downloadFile(@PathVariable("fileName")String fileName, HttpServletRequest request, HttpServletResponse response) throws IOException {
        if (fileName != null) {
            //设置文件路径
            File file = new File(filePath, fileName);
            
            if (file.exists()) {
            	// 设置强制下载不打开
                response.setContentType("application/force-download");
                // 设置文件名
                response.addHeader("Content-Disposition", "attachment;fileName=" + fileName);
                byte[] buffer = new byte[1024];
                FileInputStream fileInputStream = new FileInputStream(file);
                BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);
                OutputStream outputStream = response.getOutputStream();
                try {
                    int i = bufferedInputStream.read(buffer);
                    while (i != -1) {
                        outputStream.write(buffer, 0, i);
                        i = bufferedInputStream.read(buffer);
                    }
                } finally {
                    if (bufferedInputStream != null) {
                        bufferedInputStream.close();
                    }
                    if (fileInputStream != null) {
                    	fileInputStream.close();
                    }
                }
            }
        }
    }
    
    /**
     * 浏览文件
     * 需要权限的文件浏览
     * @param fileName
     * @param request
     * @param response
     * @throws IOException
     */
    @GetMapping("/file/browse/{fileName}")
    public void browseFile(@PathVariable("fileName")String fileName, HttpServletRequest request, HttpServletResponse response) throws IOException {
        if (fileName != null) {
            //设置文件路径
            File file = new File(filePath, fileName);
            
            if (file.exists()) {
            	// 获取后缀名
            	String suffix = fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length());
            	// 设置返回值类型,如果要支持图片以外的文件,可能需要个枚举
                response.setContentType("image/" + suffix);
                // 设置文件名
                response.addHeader("Content-Disposition", "inline;fileName=" + fileName);
                byte[] buffer = new byte[1024];
                FileInputStream fileInputStream = new FileInputStream(file);
                BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);
                OutputStream outputStream = response.getOutputStream();
                try {
                    int i = bufferedInputStream.read(buffer);
                    while (i != -1) {
                        outputStream.write(buffer, 0, i);
                        i = bufferedInputStream.read(buffer);
                    }
                } finally {
                    if (bufferedInputStream != null) {
                        bufferedInputStream.close();
                    }
                    if (fileInputStream != null) {
                    	fileInputStream.close();
                    }
                }
            }
        }
    }
	
}