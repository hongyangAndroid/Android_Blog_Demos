package com.zhy.blogcodes.performance_01;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.zhy.blogcodes.R;


public class PerformanceMainActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perf_main);

        ViewGroup activityContainer = (ViewGroup) findViewById(R.id.activity_main_container);

        addButton(OverDrawActivity01.class,
                getString(R.string.title_activity_overdraw01), activityContainer);

        addButton(OverDrawActivity02.class, "Use ClipRect ", activityContainer);

        addButton(CompareLayoutActivity.class, "Compare Layout", activityContainer);

    }

    private void addButton(final Class destination, String description, ViewGroup parent)
    {
        Button button = new Button(this);
        button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent problemIntent = new Intent(PerformanceMainActivity.this, destination);
                startActivity(problemIntent);
            }
        });
        button.setText(description);
        parent.addView(button);
    }
}
