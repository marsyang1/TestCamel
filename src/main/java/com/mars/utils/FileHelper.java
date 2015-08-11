package com.mars.utils;

import lombok.extern.slf4j.Slf4j;

import java.io.File;

/**
 * Created by mars on 2015/5/7.
 */
@Slf4j
public class FileHelper {

    public static boolean isFileExist(String path, String fileName) {
        String tempPath;
        if (path.endsWith("/") || path.endsWith("\\")) {
            tempPath = path + fileName;
        } else {
            tempPath = path + "/" + fileName;
        }
        File f = new File(tempPath);
        log.debug("Check File " + tempPath);
        boolean isExist = f.exists() && !f.isDirectory();
        log.debug("File " + tempPath + "is exist = " + String.valueOf(isExist));
        return isExist;
    }

    public static String refactorPath(String path) {
        if (path.endsWith("/") || path.endsWith("\\")) {
            return path;
        } else {
            return path + "/";
        }
    }

}
