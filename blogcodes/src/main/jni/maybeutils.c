#include "com_zhy_blogcodes_jni_complicated_MaybeUtils.h"
#include <stdio.h>
//参数2为jclass，jobject是用于指向java对象的引用，而static方法，只需要
//类名即可，保存的是类的引用
JNIEXPORT jobject JNICALL Java_com_zhy_blogcodes_jni_complicated_MaybeUtils_generate
        (JNIEnv *env, jclass clazz) {
    jclass targetClass;
    jmethodID mid;
    jobject newObject;
    //查找类
    targetClass = (*env)->FindClass(env, "com/zhy/blogcodes/jni/complicated/MaybeUtils");
    //查找构造方法
    mid = (*env)->GetMethodID(env, targetClass, "<init>", "(I)V");
    //创建对象
    newObject = (*env)->NewObject(env, targetClass, mid, 100);
    //查找对象的方法
    mid = (*env)->GetMethodID(env, targetClass, "setNum", "(I)Lcom/zhy/blogcodes/jni/complicated/MaybeUtils;");
    //调用方法
    newObject = (*env)->CallObjectMethod(env, newObject, mid, 188);
    return newObject;


}