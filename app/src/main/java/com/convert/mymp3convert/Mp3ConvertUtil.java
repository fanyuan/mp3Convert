package com.convert.mymp3convert;

import android.util.Log;

import java.util.concurrent.ConcurrentHashMap;

public class Mp3ConvertUtil {
    static {
        System.loadLibrary("hello");
        System.loadLibrary("libmp3lame");
    }

    public static native String hello(String msg);
    /**
     * 获取LAME的版本信息
     *
     * @return
     */
    public static native String getLameVer();
    /**
     * wav转换成mp3的本地方法
     *
     * @param wav
     * @param mp3
     */
    public static native void convertmp3(String wav, String mp3);

    /**
     * 转换接口回调
     */
    public interface ConvertListener{
        public void notifyConvertProgress(int progress);
        /**
         * 转换完成回调
         */
        public  void convertFinish();

        /**
         * 转换完成回调
         * @param errorMsg
         */
        public  void convertError(String errorMsg);
    }

    /**
     * 提供的一个空实现
     */
    public static class SimpleConvertListener implements ConvertListener{

        @Override
        public void notifyConvertProgress(int progress) {

        }

        @Override
        public void convertFinish() {

        }

        @Override
        public void convertError(String errorMsg) {

        }
    }
    /**
     * 保存进度的地方
     */
    static ConcurrentHashMap<String,ConvertListener> mConvertCallbacks;
    /**
     * 保存进度的地方
     */
    static ConcurrentHashMap<String,Integer> mProgresses;


    /**
     * 注册转换回调类
     * @param mp3TargetPath
     * @param callback
     */
    protected static void registerCallback(String mp3TargetPath,ConvertListener callback){
        if(mConvertCallbacks == null){
            mConvertCallbacks = new ConcurrentHashMap<String, ConvertListener>();
        }
        mConvertCallbacks.put(mp3TargetPath,callback);
    }

    /**
     * 移除转换回调类
     * @param mp3TargetPath
     */
    private static void removeCallback(String mp3TargetPath){
        if(mConvertCallbacks == null){
            return;
        }
        if(mConvertCallbacks.containsKey(mp3TargetPath)){
            mConvertCallbacks.remove(mp3TargetPath);
        }
    }
    /**
     * 设置进度的方法
     * @param outPath
     */
    private static void setProgress(String outPath,int progress){
        if(mProgresses == null){
            mProgresses = new ConcurrentHashMap<String, Integer>();
        }
        mProgresses.put(outPath,progress);
        Log.d("ddebug","---setProgress---" + outPath + "=" + progress);
    }
    /**
     * 获取进度的方法
     * @param outPath
     * @return
     */
    protected static int getProgress(String outPath){
        if(mProgresses == null){
            return -1;
        }
        if(!mProgresses.containsKey(outPath)){
            return -1;
        }
        int progress = mProgresses.get(outPath);
        Log.d("ddebug","---getProgress---" + outPath + "=" + progress);
        return progress;
    }

    /**
     * 移除相关进度条目的方法
     *
     * @param outPath
     */
    private static void removeProgress(String outPath){
        if(mProgresses == null){
            return;
        }
        mProgresses.remove(outPath);
        Log.d("ddebug","---removeProgress---" + outPath);
    }
    /**
     * 日志打印方法，提供给C语言调用
     *
     * @param
     */
    public static void nativeLog(String logTag,String logMsg) {
        Log.d(logTag,"java nativeLog:" + logMsg);
    }
    /**
     * 设置进度条的进度，提供给C语言调用
     *
     * @param progress
     */
    public static void setConvertProgress(int progress,String outPath) {
        setProgress(outPath,progress);
        if(mConvertCallbacks == null){
            mConvertCallbacks = new ConcurrentHashMap<String, ConvertListener>();
        }
        if(mConvertCallbacks.containsKey(outPath)){
            mConvertCallbacks.get(outPath).notifyConvertProgress(progress);
        }
        Log.d("ddebug","转换进度为："+ progress);
    }
    /**
     * 转换完成回调，提供给C语言调用
     *
     * @param outPath
     */
    public static void convertFinish(String outPath) {
        removeProgress(outPath);
        if(mConvertCallbacks == null){
            return;
        }
        if(mConvertCallbacks.containsKey(outPath)){
            mConvertCallbacks.get(outPath).convertFinish();
            removeCallback(outPath);
        }
        Log.d("ddebug","转换完成  convertFinish:"+ outPath);
    }
    /**
     * 转换完成回调，提供给C语言调用
     *
     * @param outPath
     */
    public static void convertError(String errorMsg,String outPath) {
        removeProgress(outPath);

        if(mConvertCallbacks == null){
            return;
        }
        if(mConvertCallbacks.containsKey(outPath)){
            mConvertCallbacks.get(outPath).convertError(errorMsg);
            removeCallback(outPath);
        }
        Log.d("ddebug","java convertError:" + errorMsg + " --- " + outPath);
    }

}
