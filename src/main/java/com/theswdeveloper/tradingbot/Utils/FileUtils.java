package com.theswdeveloper.tradingbot.Utils;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;

public class FileUtils {

    public static boolean isFileExist(String path) {
        return new File(path).exists();
    }

    public static void createOutputDirectoryIfNotExist(String path) {
        File file = new File(path);
        if (!file.exists()) {
            file.mkdir();
        }
    }

    public static File getFileFromResource(String fileName) throws URISyntaxException {
        ClassLoader classLoader = FileUtils.class.getClassLoader();
        URL resource = classLoader.getResource(fileName);
        if (resource == null) {
            throw new IllegalArgumentException("file not found! " + fileName);
        } else {
            // failed if files have whitespaces or special characters
            //return new File(resource.getFile());
            return new File(resource.toURI());
        }
    }

}
