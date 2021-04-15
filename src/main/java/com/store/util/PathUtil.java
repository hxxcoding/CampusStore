package com.store.util;

public class PathUtil {
    public static String getImgBasePath() {
        String os = System.getProperty("os.name");
        return "/Users/hxx/Desktop/img"; // 上传文件的绝对路径
    }

    public static String getShopImgPath(long shopId) {
        return "/upload/item/shop/" + shopId + "/";
    }

}
