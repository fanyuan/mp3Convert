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
     * @param wavInPath   需要转换的wav源文件输入路径
     * @param mp3OutPath  转换完成后的mp3目标文件输出路径
     */
    public static void convertmp3(String wavInPath, String mp3OutPath){
        Mp3ConvertUtil.convertmp3(wavInPath,mp3OutPath);
    }

    /**
     * wav转换成mp3
     * @param wavInPath   需要转换的wav源文件输入路径
     * @param mp3OutPath  转换完成后的mp3目标文件输出路径
     * @param callback    转换相关的回调
     */
    public static void convertmp3(String wavInPath, String mp3OutPath, Mp3ConvertUtil.ConvertListener callback){
        Mp3ConvertUtil.registerCallback(mp3OutPath,callback);
        Mp3ConvertUtil.convertmp3(wavInPath,mp3OutPath);
    }
    /**
     * 获取转换进度
     * @param outPath  在多个任务并行时以输出路径为token来查询相关转换文件的转换进度
     * @return
     */
    public static int getProgress(String outPath){
        return Mp3ConvertUtil.getProgress(outPath);
    }
}
