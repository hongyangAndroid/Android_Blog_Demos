package com.zhy.blogcodes.performance_01;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.view.View;

import com.zhy.blogcodes.R;

/**
 * Created by zhy on 15/4/30.
 */
public class CardView extends View
{
    private Bitmap[] mCards = new Bitmap[3];

    private int[] mImgId = new int[]{R.drawable.alex, R.drawable.chris, R.drawable.claire};

    public CardView(Context context)
    {
        super(context);

        Bitmap bm = null;
        for (int i = 0; i < mCards.length; i++)
        {
            bm = BitmapFactory.decodeResource(getResources(), mImgId[i]);
            mCards[i] = Bitmap.createScaledBitmap(bm, 400, 600, false);
        }

        setBackgroundColor(0xFF00ff00);
    }

    @Override
    protected void onDraw(Canvas canvas)
    {

        super.onDraw(canvas);

        canvas.save();
        canvas.translate(20, 120);
        for (int i = 0; i < mCards.length; i++)
        {
            canvas.translate(120, 0);
            canvas.save(Canvas.CLIP_SAVE_FLAG);
            if (i < mCards.length - 1)
            {
                canvas.clipRect(0, 0, 120, mCards[i].getHeight());
            }
            canvas.drawBitmap(mCards[i], 0, 0, null);
            canvas.restore();
        }
        canvas.restore();

    }
}
