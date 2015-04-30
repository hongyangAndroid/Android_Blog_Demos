package com.zhy.performance_01_render;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

import com.android.debug.hv.ViewServer;


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
