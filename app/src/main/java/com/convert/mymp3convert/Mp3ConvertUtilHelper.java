package com.convert.mymp3convert;

public class Mp3ConvertUtilHelper {
    /**
     * 获取版本号
     * @return
     */
    public static String getVer(){
        return Mp3ConvertUtil.getLameVer();
    }

    /**
     * wav转换成mp3
     * @param wavInPath
     * @param mp3OutPath
     */
    public static void convertmp3(String wavInPath, String mp3OutPath){
        Mp3ConvertUtil.convertmp3(wavInPath,mp3OutPath);
    }

    /**
     * wav转换成mp3
     * @param wavInPath
     * @param mp3OutPath
     * @param callback
     */
    public static void convertmp3(String wavInPath, String mp3OutPath, Mp3ConvertUtil.ConvertListener callback){
        Mp3ConvertUtil.registerCallback(mp3OutPath,callback);
        Mp3ConvertUtil.convertmp3(wavInPath,mp3OutPath);
    }
    /**
     * 获取转换进度
     * @param outPath
     * @return
     */
    public static int getProgress(String outPath){
        return Mp3ConvertUtil.getProgress(outPath);
    }
}
