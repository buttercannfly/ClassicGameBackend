package com.tencent.wxcloudrun.utils;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.util.ResourceUtils;

import javax.servlet.http.HttpServletResponse;
import java.io.*;

public class DownloadUtils {
    public static void download(String filename, HttpServletResponse res) throws IOException {
        // 发送给客户端的数据
        // 读取filename
//        File file = ResourceUtils.getFile("classpath:"+filename);
//        InputStream in = new FileInputStream(file);

//        ClassPathResource classPathResource = new ClassPathResource(filename);

        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        Resource[] resources = resolver.getResources(filename);
        Resource resource = resources[0];
        InputStream in = resource.getInputStream();

//        long length = resource.getFile().length();
//        res.addHeader("Content-Length",String.valueOf(length));
        OutputStream outputStream = res.getOutputStream();
        byte[] buff = new byte[1024];
        BufferedInputStream bis = null;
        bis = new BufferedInputStream(in);
        int i = bis.read(buff);
        while (i != -1) {
            outputStream.write(buff, 0, buff.length);
            outputStream.flush();
            i = bis.read(buff);
        }
        bis.close();
        outputStream.close();
    }
}
