package com.deschen.myblog.core.utils;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.UUID;

/**
 * @Author deschen
 * @Create 2019/5/21
 * @Description 文件上传下载工具类
 * @Since 1.0.0
 */
@Data
@Slf4j
public class FileUtil {

    private String uploadRootDir;

    private String filePrefix;

    public FileUtil() {
    }

    //设置存储路径，后缀默认
    public FileUtil(String uploadRootDir) {
        char tail = uploadRootDir.charAt(uploadRootDir.length() - 1);
        char head = uploadRootDir.charAt(0);
        // 以防止路径报错
        if (tail != '/') {
            uploadRootDir = uploadRootDir + "/";
        }

        log.info("【文件工具】 文件路径名 uploadRootDir = {}", uploadRootDir);
        this.uploadRootDir = uploadRootDir;
        this.filePrefix = UUID.randomUUID().toString().replaceAll("-", "");
    }

    /*设置存储路径和后缀*/
    public FileUtil(String uploadRootDir, String filePrefix) {
        char c = uploadRootDir.charAt(uploadRootDir.length() - 1);
        if (c != '/') {
            uploadRootDir = uploadRootDir + "/";
        }
        log.info("【文件工具】 文件路径名 uploadRootDir = {}", uploadRootDir);
        this.uploadRootDir = uploadRootDir;
        this.filePrefix = filePrefix;
    }

    //存储文件并返回文件路径名
    public String uploadFile(MultipartFile multipartFile) throws IOException {

        String filePath = getFilePath(multipartFile);
        File file = new File(filePath);
        //如果没有files文件夹，则创建
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
        //文件写入指定路径
//        InputStream inputStream = multipartFile.getInputStream();
        multipartFile.transferTo(file);
//        inputStream.close();
        /*FileOutputStream out = new FileOutputStream(filePath);
        out.write(multipartFile.getBytes());
        out.flush();
        out.close();*/

        log.info("【文件工具】保存文件成功，filePath={}", filePath);
        return filePath;
    }

    // 根据文件名
    public String getfileName(String filePath) {
        String fileName = filePath.substring(filePath.lastIndexOf("/")+1);
        log.info("【文件工具】文件名, fileName = {}",
                fileName);
        return fileName;
    }

    // 下载文件
    public void downloadFile(
            String filePath, HttpServletResponse response) throws UnsupportedEncodingException {

        File file = new File(filePath);
        if (file.exists()) {
            // 文件名字
            String simpleName = file.getName();
            String fileName = simpleName.substring(0, simpleName.lastIndexOf("_"));
            String suffix = simpleName.substring(simpleName.lastIndexOf("."));
            fileName = fileName + suffix;
            // 防止中文乱码
            response.setContentType("multipart/form-data");
            response.setHeader("Content-Disposition", "attachment;filename=" + new String(fileName.getBytes("UTF-8"), "iso-8859-1"));
            // 输出流
            byte[] buffer = new byte[1024];
            FileInputStream fis = null;
            BufferedInputStream bis = null;
            try {
                fis = new FileInputStream(file);
                bis = new BufferedInputStream(fis);
                OutputStream os = response.getOutputStream();
                int i = bis.read(buffer);
                while (i != -1) {
                    os.write(buffer, 0, i);
                    i = bis.read(buffer);
                }
                log.info("【文件工具】下载成功，fileName={}");
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (bis != null) {
                    try {
                        bis.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (fis != null) {
                    try {
                        fis.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    // 删除文件
    public boolean deleteFile(String filePath) {
        File file = new File(filePath);
        if (file.exists()) {
            if (file.delete()) {
                log.info("【文件工具】删除文件成功，filePath={}",
                        filePath);
                return true;
            }else {
                log.info("【文件工具】删除文件失败，filePath={}",
                        filePath);
                return false;
            }
        }
        log.info("【文件工具】删除文件失败，不存在该文件，filePath={}",
                filePath);
        return false;

    }
    // 生成文件路径名
    private String getFilePath(MultipartFile file) {
        // 获取文件的后缀名
        String originalFilename = file.getOriginalFilename();
        // 文件名除后缀
        String fileName = originalFilename.substring(0, originalFilename.lastIndexOf("."));
        // 文件后缀名
        String suffixName =
                originalFilename.substring(originalFilename.lastIndexOf("."));
        log.info("【文件工具】后缀, suffixName = {}", suffixName);
        // 防止文件重复
        String path = uploadRootDir + fileName + "_" + filePrefix + suffixName;
        return path;
    }

}
