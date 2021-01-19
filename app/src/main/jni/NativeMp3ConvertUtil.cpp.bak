//
// Created by shaomingfa on 2021/1/14.
//

#include <string>
#include "libmp3lame/lame.h"

#include <jni.h>

#include<stdio.h>
#include<malloc.h>
#include<lame.h>
#include<android/log.h>
#include "libmp3lame/lame.h"
#include <unistd.h>
#include <fcntl.h>
#include <sys/stat.h>


#include "NativeMp3ConvertUtil.h"
#include "com_convert_mymp3convert_Mp3ConvertUtil.h"
#define LOG_TAG "System.out.c"
/**
 * 日志打印tag
 */
#define CONVERT_LOG_TAG "nativeTag"
#define LOGD(...) __android_log_print(ANDROID_LOG_DEBUG, LOG_TAG, __VA_ARGS__)
#define LOGI(...) __android_log_print(ANDROID_LOG_INFO, LOG_TAG, __VA_ARGS__)



#ifndef com_convert_mymp3convert_Mp3ConvertUtil
#define com_convert_mymp3convert_Mp3ConvertUtil
#ifdef __cplusplus
extern "C" {
#endif

/**
* 返回值 char* 这个代表char数组的首地址
*  Jstring2CStr 把java中的jstring的类型转化成一个c语言中的char 字符串
*/
char* Jstring2CStr(JNIEnv *env, jstring jstr) {
    char* rtn = NULL;
    jclass clsstring = (*env).FindClass( "java/lang/String"); //String
    jstring strencode = (*env).NewStringUTF( "GB2312"); // 得到一个java字符串 "GB2312"
    jmethodID mid = (*env).GetMethodID(clsstring, "getBytes",
                                       "(Ljava/lang/String;)[B"); //[ String.getBytes("gb2312");
    jbyteArray barr = (jbyteArray)(*env).CallObjectMethod(jstr, mid,
                                                          strencode); // String .getByte("GB2312");
    jsize alen = (*env).GetArrayLength( barr); // byte数组的长度
    jbyte* ba = (*env).GetByteArrayElements( barr, JNI_FALSE);
    if (alen > 0) {
        rtn = (char*) malloc(alen + 1); //"\0"
        memcpy(rtn, ba, alen);
        rtn[alen] = 0;
    }
    (*env).ReleaseByteArrayElements(barr, ba, 0); //
    (*env).DeleteLocalRef(strencode);
    return rtn;
}
/**==============================================================================================================================================*/
/**
 * 调用java代码 更新程序的进度条
 */
void publishJavaProgress(JNIEnv * env, jobject obj, jint progress,jstring outPath) {
    //1.找到java的LameUtils的class          com/example/myjnidemo/
    jclass clazz = (*env).FindClass("com/convert/mymp3convert/Mp3ConvertUtil");
    if (clazz == 0) {
        LOGI("can't find clazz");
        return;
    }
    LOGI(" convert progress %d" , progress);

    //2 找到class 里面的方法定义

    jmethodID methodid = (*env).GetStaticMethodID(clazz,"setConvertProgress","(ILjava/lang/String;)V");//jmethodID methodid = (*env).GetMethodID(clazz, "setConvertProgress","(I)V");
    if (methodid == 0) {
        LOGI("can't find methodid");
        return;
    }
    LOGI(" find methodid");

    //3 .调用方法
    (*env).CallStaticVoidMethod(clazz, methodid, progress,outPath);//(*env).CallVoidMethod(obj, methodid, progress);
    //env -> CallVoidMethod(obj,methodid,progress);
    env->DeleteLocalRef(clazz);
    //env->DeleteLocalRef(methodid);
}
/**
 * 调用java代码 更新程序的进度条
 */
void convertFinish(JNIEnv * env, jstring mp3Path) {
    //1.找到java的LameUtils的class          com/example/myjnidemo/
    jclass clazz = (*env).FindClass("com/convert/mymp3convert/Mp3ConvertUtil");
    if (clazz == 0) {
        LOGI("can't find clazz");
        return;
    }
    const char *c = env->GetStringUTFChars(mp3Path, JNI_FALSE);
    LOGI(" convert finished %s" , c);
    env->ReleaseStringUTFChars(mp3Path,c);

    //2 找到class 里面的方法定义
    jmethodID methodid = (*env).GetStaticMethodID(clazz,"convertFinish","(Ljava/lang/String;)V");
    if (methodid == 0) {
        LOGI("can't find methodid");
        return;
    }
    LOGI(" find convertFinish methodid");

    //3 .调用方法
    env -> CallStaticVoidMethod(clazz,methodid,mp3Path);
    env->DeleteLocalRef(clazz);
}
/**
 * 调用java代码 转换失败时调用
 */
void convertError(JNIEnv * env,jstring msg,jstring mp3Path) {
    //1.找到java的LameUtils的class          com/example/myjnidemo/
    jclass clazz = (*env).FindClass("com/convert/mymp3convert/Mp3ConvertUtil");
    if (clazz == 0) {
        LOGI("can't find clazz");
        return;
    }

    const char *c = (*env).GetStringUTFChars(mp3Path, JNI_FALSE);
    LOGI(" convert error %s" , c);
    (*env).ReleaseStringUTFChars(mp3Path,c);

    //2 找到class 里面的方法定义

    jmethodID methodid = (*env).GetStaticMethodID(clazz,"convertError",
                                                  "(Ljava/lang/String;Ljava/lang/String;)V");
    if (methodid == 0) {
        LOGI("can't find methodid");
        return;
    }
    LOGI(" find methodid");

    //3 .调用方法
    env -> CallStaticVoidMethod(clazz,methodid,msg,mp3Path);
    env->DeleteLocalRef(clazz);
}
/**
 * 调用java代码 打印日志时调用
 */
void nativeLog(JNIEnv * env, jstring msg) {
    //1.找到java的LameUtils的class          com/example/myjnidemo/
    jclass clazz = (*env).FindClass("com/convert/mymp3convert/Mp3ConvertUtil");
    if (clazz == 0) {
        LOGI("can't find clazz");
        return;
    }
    //2 找到class 里面的方法定义
    jmethodID methodid = (*env).GetStaticMethodID(clazz,"nativeLog",
                                                  "(Ljava/lang/String;Ljava/lang/String;)V");
    if (methodid == 0) {
        LOGI("can't find methodid");
        return;
    }
    LOGI(" find methodid");
    jstring logTag = env->NewStringUTF(CONVERT_LOG_TAG);
    //3 .调用方法
    env -> CallStaticVoidMethod(clazz,methodid,logTag,msg);
    env->DeleteLocalRef(logTag);
    env->DeleteLocalRef(clazz);
}
/**
 * 字符串拼接
 * @param env
 * @param cstr
 * @param jstr
 * @return
 */
jstring jstrCat(JNIEnv *env,char * cstr,jstring jstr){
    char *c =(char *)(*env).GetStringUTFChars(jstr,JNI_FALSE);
    char * bf = new char[strlen(cstr) + strlen(c) +1];
    memcpy(bf, cstr, strlen(cstr) + 1);
    strcat(bf, c);
    jstring  js = env->NewStringUTF(bf);
    delete [] bf;
    (*env).ReleaseStringUTFChars(jstr,c);
    return js;
}
/**
 * 获取文件的大小
 * @param filename
 * @return
 */
long long file_size(char* filename){
    struct stat statbuf;
    stat(filename,&statbuf);
    return statbuf.st_size;
}
/*
 * Class:     com_convert_mymp3convert_Mp3ConvertUtil
 * Method:    hello
 * Signature: (Ljava/lang/String;)Ljava/lang/String;
 */
JNIEXPORT jstring JNICALL Java_com_convert_mymp3convert_Mp3ConvertUtil_hello
        (JNIEnv *env, jclass obj, jstring str){

    jstring js = env->NewStringUTF("/sd/mp3");
    publishJavaProgress(env,obj,123,js);
    env->DeleteLocalRef(js);

    jstring jstr = env->NewStringUTF("/sd/m/mp3");
    convertFinish(env,jstr);
    env->DeleteLocalRef(jstr);

    jstring msg = env->NewStringUTF("xxx原因导致了未转换成功");
    convertError(env,  msg, msg);
    env->DeleteLocalRef(msg);

    LOGD("lame ver = %s",get_lame_very_short_version());
    LOGI("convertmp3   %S  ===  %S","wav","mp3");
    LOGD("CONVERT   %S","test");
    return env->NewStringUTF("Hello From JNI!");//
}
/*
 * Class:     com_convert_mymp3convert_Mp3ConvertUtil
 * Method:    getLameVer
 * Signature: ()Ljava/lang/String;
 */
JNIEXPORT jstring JNICALL
Java_com_convert_mymp3convert_Mp3ConvertUtil_getLameVer(JNIEnv *env, jclass obj) {
    LOGD("CONVERT  123321 测试 S ");
    LOGD("CONVERT  123321 测试 S --- %d",123);
    LOGD("CONVERT  123321 测试 S --- %s","test");
    return (*env).NewStringUTF(get_lame_version());
}
//int flag = 0;
/**
 *wav转换mp3
 * @param env
 * @param obj
 * @param wav
 * @param mp3
 */
/*
 * Class:     com_convert_mymp3convert_Mp3ConvertUtil
 * Method:    convertmp3
 * Signature: (Ljava/lang/String;Ljava/lang/String;)V
 */
JNIEXPORT void JNICALL
Java_com_convert_mymp3convert_Mp3ConvertUtil_convertmp3(JNIEnv *env, jclass obj, jstring wav,jstring mp3) {

    char* cwav = const_cast<char *>(env->GetStringUTFChars(wav, JNI_FALSE));//Jstring2CStr(env,wav) ;
    char* cmp3= const_cast<char *>(env->GetStringUTFChars(mp3, JNI_FALSE));//Jstring2CStr(env,mp3);
    LOGI("wav = %s", cwav);
    LOGI("mp3 = %s", cmp3);

    char buf[2048] = {};
    sprintf(buf, "source：%s，target：%s ", cwav,cmp3);
    LOGD(buf,NULL);

    if(access(cwav,F_OK) == -1){
        free(cwav);
        free(cmp3);
        LOGD("转换源文件不存在");
        jstring msg = jstrCat(env,"转换源文件不存在:",mp3);
        nativeLog(env, msg);
        convertError(env,msg,mp3);
        env->DeleteLocalRef(msg);
        return;
    }
    long long fileSize = file_size(cwav);
    if (fileSize == 0) {
        free(cwav);
        free(cmp3);
        jstring msg = jstrCat(env,"转换源文件大小为0:",mp3);
        nativeLog(env, msg);
        convertError(env,msg,mp3);
        env->DeleteLocalRef(msg);
        return;
    }
    //1.打开 wav,MP3文件
    FILE* fwav = fopen(cwav,"rb");
    FILE* fmp3 = fopen(cmp3,"wb");

    short int wav_buffer[8192*2]= {};
    unsigned char mp3_buffer[8192] = {};

    //1.初始化lame的编码器
    lame_t lame =  lame_init();
    //2. 设置lame mp3编码的采样率
    lame_set_in_samplerate(lame , 44100);
    lame_set_num_channels(lame,2);
    // 3. 设置MP3的编码方式
    lame_set_VBR(lame, vbr_default);
    lame_init_params(lame);
    LOGI("lame init finish");

    int read ; int write; //代表读了多少个次 和写了多少次
    long long total=0; // 当前读的wav文件的byte数目

    LOGD("FILE SIZE = %d",fileSize);
    int progress = 0;
    do{
//        if(flag==404){
//            return;
//        }
        read = fread(wav_buffer,sizeof(short int)*2, 8192,fwav);
        total +=  read* sizeof(short int)*2;
        LOGI("converting ....%d", total);
        int tmpProgress = (total*100)/fileSize;
        LOGD("converting size = %d",tmpProgress);

        if(tmpProgress != progress){
            progress = tmpProgress;
            publishJavaProgress(env,obj,progress,mp3);
        }

        // 调用java代码 完成进度条的更新
        if(read!=0){
            write = lame_encode_buffer_interleaved(lame,wav_buffer,read,mp3_buffer,8192);
            //把转化后的mp3数据写到文件里
            fwrite(mp3_buffer,sizeof(unsigned char),write,fmp3);
        }
        if(read==0){
            write = lame_encode_flush(lame,mp3_buffer,8192);
            fwrite(mp3_buffer,sizeof(unsigned char),write,fmp3);
        }
    }while(read!=0);

    lame_close(lame);
    fclose(fwav);
    fclose(fmp3);
    env->ReleaseStringUTFChars(wav,cwav);
    env->ReleaseStringUTFChars(mp3,cmp3);

    convertFinish(env,mp3);
    LOGI("convert  finish");

}


#ifdef __cplusplus
}
#endif
#endif
