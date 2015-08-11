package com.zhy.blogcodes.test;

import android.annotation.TargetApi;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

import com.zhy.blogcodes.R;
import com.zhy.utils.BitmapBase64Utils;
import com.zhy.utils.L;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainTestActivity extends AppCompatActivity
{

    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR1)
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_test);

        Bitmap bm = BitmapFactory.decodeResource(getResources(), R.drawable.ccc);
        L.e(" origin : " + bm.getByteCount());
        String string = BitmapBase64Utils.bitmap2String(bm
        );

        FileOutputStream fos = null;
        try
        {
            File f = new File(Environment.getExternalStorageDirectory().getAbsolutePath(),"test1.txt");

            Uri url = Uri.parse(f.getAbsolutePath());
            L.e(url.getScheme()+" , " + url);

            url = Uri.parse("http://www.xxx.xxx");
            L.e(url.getScheme()+" , " + url);

            fos = new FileOutputStream(f);
        } catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        try
        {
            fos.write(string.getBytes());
            fos.flush();
            fos.close();
        } catch (IOException e)
        {
            e.printStackTrace();
        }

        try
        {
            FileInputStream fis = new FileInputStream(new File(Environment.getExternalStorageDirectory().getAbsolutePath(),"test1.txt"));
            int len = 0 ;
            byte[] buf = new byte[1024];
            StringBuilder sb = new StringBuilder();
            while( (len = fis.read(buf)) != -1)
            {
                sb.append(new String(buf , 0 , len));
            }
            fis.close();

            L.e(sb.toString());


        } catch (FileNotFoundException e)
        {
            e.printStackTrace();
        } catch (IOException e)
        {
            e.printStackTrace();
        }


        L.e(string.getBytes().length * 8 + "");
        bm = BitmapBase64Utils.string2Bitmap(string);

        L.e("target :"  + bm.getByteCount());
        ImageView iv = (ImageView) findViewById(R.id.id_imageview);
        iv.setImageBitmap(bm);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main_test, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings)
        {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
