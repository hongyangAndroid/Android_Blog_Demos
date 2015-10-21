package com.zhy.blogcodes.performance_01;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

import com.zhy.blogcodes.R;


public class CompareLayoutActivity extends ActionBarActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compare_layouts);

        ViewServer.get(this).addWindow(this);
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        ViewServer.get(this).setFocusedWindow(this);
    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        ViewServer.get(this).removeWindow(this);
    }
}
