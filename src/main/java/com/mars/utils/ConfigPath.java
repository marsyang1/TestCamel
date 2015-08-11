package com.mars.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.jndi.JndiTemplate;

import javax.naming.NamingException;

/**
 * Created by mars on 2015/5/7.
 * 首先  fusion.properties 不要再加東西了, 獨立出來
 * [下午 03:44:06] E化-Linus: 另外 config 位置可以先放 JNDI, 我的希望是放在 commons lib 的設定裡. 也就是程式寫死. 這種位置通常都約定成俗
 * [下午 03:44:28] E化-Linus: 寫在程式的好處是可以自動判斷 win or linux 平台來抓到目錄
 * [下午 03:44:47] E化- Mars: 要寫死也是可以 , 但就是程式裡面要寫判斷
 * [下午 03:44:57] E化- Mars: 那我要現在改嗎？
 * [下午 03:45:13] E化-Linus: 就一個 簡單的 get config path 的 function
 * [下午 03:45:28] E化-Linus: security 先當示範 ok 啊
 * [下午 03:47:10] E化- Mars: 那我就先改了 , Linux的 default path是不是可以給我一下？
 * [下午 03:47:52] E化-Linus: /srv/fusion/config
 * [下午 03:48:07] E化- Mars: ok
 * [下午 03:48:11] E化-Linus: wait.
 * [下午 03:48:19] E化-Linus: 放 /var/fusion/config 好了
 * [下午 03:48:42] E化-Linus: tomcat docker 裡好像沒這個目錄 這是 centos 自帶的
 * [下午 03:48:59] E化-Linus: 啊.應該是 /etc/fusion/config....
 * [下午 03:49:15] E化-Linus: 算了....統一好了../srv/fusion/config
 * [下午 03:49:19] E化-Linus: 不改了
 * [下午 03:49:45] E化- Mars: 那windows就是 c:\fusion\config ?
 * [下午 03:50:00] E化-Linus: OK 看現在在那就放那
 * [下午 03:50:09] E化-Linus: 不要特別再換一個名字
 */
@Slf4j
public class ConfigPath {

    public static String getFusionConfigRootPath() {
        String path = ConfigPath.getDefaultPath();
        if (FileHelper.isFileExist(path, "fusion.properties")) {
            return path;
        }
        JndiTemplate jndi = new JndiTemplate();
        path = getJndiValue(jndi, "java:comp/env/fusionConfigRoot");
        if (!path.isEmpty() && FileHelper.isFileExist(path, "fusion.properties")) {
            return FileHelper.refactorPath(path);
        }
        path = getJndiValue(jndi, "fusionConfigRoot");
        if (!path.isEmpty() && FileHelper.isFileExist(path, "fusion.properties")) {
            return FileHelper.refactorPath(path);
        } else {
            return "C:/fusion/config/";
        }
    }

    private static String getJndiValue(JndiTemplate jndi, String jndiName) {
        try {
            return jndi.lookup(jndiName, String.class);
        } catch (NamingException e) {
            log.info("can't find " + jndiName + ", search other");
            return "";
        }
    }

    public static String getDefaultPath() {
        String osName = System.getProperty("os.name");
        if (osName.toLowerCase().matches(".*?(windows).*$")) {
            return "C:\\fusion\\config\\";
        } else {
            //Linux or Mac Os or ...
            return "/srv/fusion/config/";
        }
    }

}
