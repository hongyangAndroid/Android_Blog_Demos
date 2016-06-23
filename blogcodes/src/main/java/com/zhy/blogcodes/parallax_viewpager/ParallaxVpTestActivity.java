package com.zhy.blogcodes.parallax_viewpager;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.zhy.blogcodes.R;
import com.zhy.blogcodes.parallax_viewpager.fragment.SimpleFragment;

public class ParallaxVpTestActivity extends AppCompatActivity
{


    private ViewPager mViewPager;
    private int mTabCount = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parallax_vp_test);

        mViewPager = (ViewPager) findViewById(R.id.id_viewpager);

        mViewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager())
        {
            @Override
            public Fragment getItem(int position)
            {
                return
                        SimpleFragment.newInstance("Position:" + position);
            }
            @Override
            public int getCount()
            {
                return mTabCount;
            }

        });

        mViewPager.setCurrentItem(1);

    }
}
