//
// Created by zhy on 15/8/7.
//
#include <stdio.h>
#include <string.h>
#include "com_zhy_blogcodes_jni_JniTest.h"
/*
 * Class:     com_zhy_blogcodes_jni_JniTest
 * Method:    welcome
 * Signature: (Ljava/lang/String;)Ljava/lang/String;
 */
JNIEXPORT jstring JNICALL Java_com_zhy_blogcodes_jni_JniTest_welcome
        (JNIEnv *env, jobject object, jstring string)
{
    //将java的字符串转化为c的字符串
    const char* str = (*env)->GetStringUTFChars(env, string, 0);
    char buffer[512];
    //将str赋值到buffer
    strlcpy(buffer, "jni => welcome:", sizeof buffer);
    //字符串拼接
    strlcat(buffer, str ,sizeof buffer);
    return (*env)->NewStringUTF(env, buffer);
}

