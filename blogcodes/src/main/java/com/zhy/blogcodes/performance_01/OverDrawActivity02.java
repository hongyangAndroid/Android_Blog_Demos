package com.zhy.blogcodes.performance_01;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by zhy on 15/4/30.
 */
public class OverDrawActivity02 extends AppCompatActivity
{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        //getWindow().setBackgroundDrawable(null);

        setContentView(new CardView(this));
    }
}
