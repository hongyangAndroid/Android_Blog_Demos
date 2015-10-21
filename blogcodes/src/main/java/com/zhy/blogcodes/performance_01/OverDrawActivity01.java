package com.zhy.blogcodes.performance_01;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.zhy.blogcodes.R;


/**
 * Created by zhy on 15/4/29.
 */
public class OverDrawActivity01 extends AppCompatActivity
{
    private ListView mListView;
    private LayoutInflater mInflater;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        ViewServer.get(this).addWindow(this);

        setContentView(R.layout.activity_overdraw_01);
        getWindow().setBackgroundDrawable(null);

        mInflater = LayoutInflater.from(this);
        mListView = (ListView) findViewById(R.id.id_listview_chats);

        mListView.setAdapter(new ArrayAdapter<Droid>(this, -1, Droid.generateDatas())
        {
            @Override
            public View getView(int position, View convertView, ViewGroup parent)
            {

                ViewHolder holder = null;
                if (convertView == null)
                {
                    convertView = mInflater.inflate(R.layout.chat_item, parent, false);
                    holder = new ViewHolder();
                    holder.icon = (ImageView) convertView.findViewById(R.id.id_chat_icon);
                    holder.name = (TextView) convertView.findViewById(R.id.id_chat_name);
                    holder.date = (TextView) convertView.findViewById(R.id.id_chat_date);
                    holder.msg = (TextView) convertView.findViewById(R.id.id_chat_msg);
                    convertView.setTag(holder);
                } else
                {
                    holder = (ViewHolder) convertView.getTag();
                }

                Droid droid = getItem(position);
                if (droid.imageId == -1)
                {
                    holder.icon.setBackgroundColor(0x4400ff00);
                    holder.icon.setImageResource(android.R.color.transparent);
                } else
                {
                    holder.icon.setImageResource(droid.imageId);
                    holder.icon.setBackgroundResource(android.R.color.transparent);
                }
                holder.date.setText(droid.date);
                holder.msg.setText(droid.msg);
                holder.name.setText(droid.name);

                return convertView;
            }

            class ViewHolder
            {
                ImageView icon;
                TextView name;
                TextView date;
                TextView msg;
            }

        });
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        ViewServer.get(this).setFocusedWindow(this);
    }

    public void onDestroy()
    {
        super.onDestroy();
        ViewServer.get(this).removeWindow(this);
    }

}
