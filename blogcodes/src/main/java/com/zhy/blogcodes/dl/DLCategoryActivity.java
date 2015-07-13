package com.zhy.blogcodes.dl;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.zhy.blogcodes.BaseContentActivity;
import com.zhy.blogcodes.R;

import java.io.File;

public class DLCategoryActivity extends ActionBarActivity
{

    private ListView mListView;
    private LayoutInflater mInflater;

    private Class[] CLAZZES = new Class[]
            {
                    DLProxyActivity.class
            };

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_activity);

        mInflater = LayoutInflater.from(this);
        mListView = (ListView) findViewById(R.id.id_listview);

        mListView.setAdapter(
                new ArrayAdapter<Class>
                        (this, -1, CLAZZES)
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
                Intent intent = new Intent(DLCategoryActivity.this, CLAZZES[position]);
                intent.putExtra(BaseContentActivity.TITLE, CLAZZES[position].getSimpleName());
                intent.putExtra(DLProxyActivity.KEY_APK_PATH, File.separator + "dl" + File.separator + "dl_01.apk");
                startActivity(intent);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_dlactivity01, menu);
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
