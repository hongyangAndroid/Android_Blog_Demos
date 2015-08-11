package com.zhy.blogcodes.jni.complicated;

import android.util.Log;

/**
 * Created by zhy on 15/8/7.
 */
public class MaybeUtils
{
    public static native MaybeUtils generate();

    private int num  ;

    public MaybeUtils(int num)
    {
        this.num = num ;
    }


    public MaybeUtils setNum(int num)
    {
        this.num = num ;
        Log.e("TAG","call by native method ");
        return this ;
    }

    public int getNum()
    {
        return num ;
    }



}

