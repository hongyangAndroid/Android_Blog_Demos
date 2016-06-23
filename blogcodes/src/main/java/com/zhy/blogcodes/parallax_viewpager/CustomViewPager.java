package com.zhy.blogcodes.parallax_viewpager;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;

import com.zhy.blogcodes.R;

/**
 * Created by zhy on 16/6/23.
 */
public class CustomViewPager extends ViewPager
{
    private Bitmap mBg = BitmapFactory.decodeResource(getResources(), R.drawable.bg_home);

    public CustomViewPager(Context context, AttributeSet attrs)
    {
        super(context, attrs);
    }

    private static final int INVALID_POS = -1;
    private int mFirstPos = INVALID_POS;
    @Override
    protected void dispatchDraw(Canvas canvas)
    {
        if (mFirstPos == INVALID_POS)
        {
            mFirstPos = getCurrentItem();
        }
        if (this.mBg != null)
        {
            int width = mBg.getWidth();
            int height = mBg.getHeight();

            int count = getAdapter().getCount();

            int x = getScrollX() + mFirstPos * getWidth();
            //每个Item需要显示图片的宽度
            float widthForItem = width * 1.0f / count;
            //控件每移动一个像素，图片应该移动的像素值
            float widthForPerPx = widthForItem * 1.0f / getWidth();
            Rect src = new Rect((int) (x * widthForPerPx), 0
                    , (int) (x * widthForPerPx + widthForItem), height);
            Rect dest = new Rect( getScrollX(), 0, getScrollX() + getWidth(), getHeight());
            canvas.drawBitmap(mBg, src, dest, null);

        }
        super.dispatchDraw(canvas);
    }

}
