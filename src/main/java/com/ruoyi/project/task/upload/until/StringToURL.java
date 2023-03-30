package com.ruoyi.project.task.upload.until;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author by hujun
 * @date 2022-11-21
 */
@Slf4j
public class StringToURL {

    public static List<String> toURL( String path) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        String hostAddress = "127.0.0.1";
        Integer port = 8080;
        InetAddress address = null;
        String head = "http://";
        try {
            //        String serverName = request.getServerName();//获取服务器域名
            //获取服务器端口
            port = request.getServerPort();
            //ip
            address = InetAddress.getByName(request.getServerName());
            //获取服务器IP地址
            hostAddress = address.getHostAddress();
        } catch (UnknownHostException e) {
            log.info("e:{}", e);
        }

        String avatarPics = path;
        log.info("图片路径String:{}", avatarPics);
        List<String> picURLs = new ArrayList<>();
        if (avatarPics != null && !avatarPics.equals("")) {
            List<String> picList = Arrays.stream(avatarPics.split(",")).collect(Collectors.toList());
            for (int i = 0; i < picList.size(); i++) {
                String imgURL = head + hostAddress + ":" + port +avatarPics;
                picURLs.add(imgURL);
            }
        }
        return picURLs;
    }
}
