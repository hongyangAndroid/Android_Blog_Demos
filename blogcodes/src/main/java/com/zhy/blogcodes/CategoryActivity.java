package com.zhy.blogcodes;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.zhy.blogcodes.colour_app_01.ColourImageActivity;
import com.zhy.blogcodes.intentservice.HandlerThreadActivity;
import com.zhy.blogcodes.intentservice.IntentServiceActivity;
import com.zhy.blogcodes.jni.Jni01Activity;
import com.zhy.blogcodes.largeImage.LargeImageViewActivity;
import com.zhy.blogcodes.mvp.UserLoginActivity;
import com.zhy.blogcodes.nav.NavListViewActivity;
import com.zhy.blogcodes.nav.NavigationViewActivity;
import com.zhy.blogcodes.parallax_viewpager.ParallaxVpTestActivity;
import com.zhy.blogcodes.performance_01.PerformanceMainActivity;
import com.zhy.blogcodes.toolbar.ToolBarMainActivity;
import com.zhy.blogcodes.vdh.LeftDrawerLayoutActivity;
import com.zhy.blogcodes.vdh.VDHBlogActivity;


public class CategoryActivity extends ActionBarActivity
{
    private ListView mListView;

    private LayoutInflater mInflater;


    private Class[] CLAZZES = new Class[]
            {
                    NavigationViewActivity.class,
                    NavListViewActivity.class,
                    UserLoginActivity.class,
                    VDHBlogActivity.class,
                    //DLCategoryActivity.class,
                    HandlerThreadActivity.class,
                    IntentServiceActivity.class,
                    ColourImageActivity.class,
                    PerformanceMainActivity.class,
                    ToolBarMainActivity.class,
                    //MainTestActivity.class,
                    Jni01Activity.class,
                    LeftDrawerLayoutActivity.class,
                    LargeImageViewActivity.class,
                    ParallaxVpTestActivity.class

            };

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        mInflater = LayoutInflater.from(this);

        mListView = (ListView) findViewById(R.id.id_listview);

        mListView.setAdapter(new ArrayAdapter<Class>(this, -1, CLAZZES)
                             {
                                 @Override
                                 public View getView(int position, View convertView, ViewGroup parent)
                                 {
                                     String title = getItem(position).getSimpleName();
                                     if (convertView == null)
                                     {
                                         convertView = mInflater.inflate(R.layout.item_category, parent, false);
                                     }
                                     TextView tv = (TextView) convertView.findViewById(R.id.id_title);
                                     tv.setText(title);
                                     return convertView;
                                 }
                             }

        );

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                Intent intent = new Intent(CategoryActivity.this, CLAZZES[position]);
                intent.putExtra(BaseContentActivity.TITLE, CLAZZES[position].getSimpleName());
                startActivity(intent);
            }
        });
    }


}
