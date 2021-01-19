//
// Created by shaomingfa on 2021/1/14.
//
#include <jni.h>
#include <string>
#include "com_convert_mymp3convert_MyTest.h"
#include "libmp3lame/lame.h"
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
/*
 * Class:     com_convert_mymp3convert_MyTest
 * Method:    test
 * Signature: ()Ljava/lang/String;
 */
extern "C" JNIEXPORT jstring JNICALL Java_com_convert_mymp3convert_MyTest_test
        (JNIEnv *env, jclass clazz){

    //1、直接使用GetStringUTFChars方法将传递过来的jstring转为char*
    char *c1 = "即将转换：";
    jstring mp3 = env->NewStringUTF("mp3");
    char *c2 = (char *) (env->GetStringUTFChars(mp3, JNI_FALSE));
    env->ReleaseStringUTFChars(mp3,c2);
    //2、再使用本地函数strcat 拼接两个char*对象，然后NewStringUTF转为jstring返回去
    jstring  res = jstrCat(env,c1,mp3);//strcat(c1, c2);
    jstring jstr = res;//env->NewStringUTF(res);
    //convertLog(env,jstr);
    env->DeleteLocalRef(jstr);

    return env->NewStringUTF(get_lame_very_short_version());
}

