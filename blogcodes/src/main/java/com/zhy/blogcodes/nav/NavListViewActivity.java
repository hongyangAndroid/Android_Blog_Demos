package com.zhy.blogcodes.nav;

import android.content.Context;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.TextViewCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.zhy.blogcodes.R;
import com.zhy.utils.L;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class NavListViewActivity extends ActionBarActivity
{
    private ListView mLvLeftMenu;
    private DrawerLayout mDrawerLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nav_list_view);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.id_drawer_layout);
        mLvLeftMenu = (ListView) findViewById(R.id.id_lv_left_menu);

        Toolbar toolbar = (Toolbar) findViewById(R.id.id_toolbar);
        setSupportActionBar(toolbar);

        final ActionBar ab = getSupportActionBar();
        ab.setHomeAsUpIndicator(R.drawable.ic_menu);
        ab.setDisplayHomeAsUpEnabled(true);

        setUpDrawer();
    }

    private void setUpDrawer()
    {
        LayoutInflater inflater = LayoutInflater.from(this);
        mLvLeftMenu.addHeaderView(inflater.inflate(R.layout.header_just_username, mLvLeftMenu, false));
        mLvLeftMenu.setAdapter(new MenuItemAdapter(this));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_nav_list_view, menu);
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
        if (id == android.R.id.home)
        {
            mDrawerLayout.openDrawer(GravityCompat.START);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    private static class LvMenuItem
    {
        public LvMenuItem(int icon, String name)
        {
            this.icon = icon;
            this.name = name;

            if (icon == NO_ICON && TextUtils.isEmpty(name))
            {
                type = TYPE_SEPARATOR;
            } else if (icon == NO_ICON)
            {
                type = TYPE_NO_ICON;
            } else
            {
                type = TYPE_NORMAL;
            }

            if (type != TYPE_SEPARATOR && TextUtils.isEmpty(name))
            {
                throw new IllegalArgumentException("you need set a name for a non-SEPARATOR item");
            }

            L.e(type + "");


        }

        public LvMenuItem(String name)
        {
            this(NO_ICON, name);
        }

        public LvMenuItem()
        {
            this(null);
        }

        private static final int NO_ICON = 0;
        public static final int TYPE_NORMAL = 0;
        public static final int TYPE_NO_ICON = 1;
        public static final int TYPE_SEPARATOR = 2;

        int type;
        String name;
        int icon;

    }

    private static class MenuItemAdapter extends BaseAdapter
    {
        private final int mIconSize;
        private LayoutInflater mInflater;
        private Context mContext;

        public MenuItemAdapter(Context context)
        {
            mInflater = LayoutInflater.from(context);
            mContext = context;

            mIconSize = context.getResources().getDimensionPixelSize(R.dimen.drawer_icon_size);
        }

        private List<LvMenuItem> mItems = new ArrayList<LvMenuItem>(
                Arrays.asList(
                        new LvMenuItem(R.drawable.ic_dashboard, "Home"),
                        new LvMenuItem(R.drawable.ic_event, "Messages"),
                        new LvMenuItem(R.drawable.ic_headset, "Friends"),
                        new LvMenuItem(R.drawable.ic_forum, "Discussion"),
                        new LvMenuItem(),
                        new LvMenuItem("Sub Items"),
                        new LvMenuItem(R.drawable.ic_dashboard, "Sub Item 1"),
                        new LvMenuItem(R.drawable.ic_forum, "Sub Item 2")
                ));


        @Override
        public int getCount()
        {
            return mItems.size();
        }


        @Override
        public Object getItem(int position)
        {
            return mItems.get(position);
        }


        @Override
        public long getItemId(int position)
        {
            return position;
        }

        @Override
        public int getViewTypeCount()
        {
            return 3;
        }

        @Override
        public int getItemViewType(int position)
        {
            return mItems.get(position).type;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent)
        {
            LvMenuItem item = mItems.get(position);
            switch (item.type)
            {
                case LvMenuItem.TYPE_NORMAL:
                    if (convertView == null)
                    {
                        convertView = mInflater.inflate(R.layout.design_drawer_item, parent,
                                false);
                    }
                    TextView itemView = (TextView) convertView;
                    itemView.setText(item.name);
                    Drawable icon = mContext.getResources().getDrawable(item.icon);
                    setIconColor(icon);
                    if (icon != null)
                    {
                        icon.setBounds(0, 0, mIconSize, mIconSize);
                        TextViewCompat.setCompoundDrawablesRelative(itemView, icon, null, null, null);
                    }

                    break;
                case LvMenuItem.TYPE_NO_ICON:
                    if (convertView == null)
                    {
                        convertView = mInflater.inflate(R.layout.design_drawer_item_subheader,
                                parent, false);
                    }
                    TextView subHeader = (TextView) convertView;
                    subHeader.setText(item.name);
                    break;
                case LvMenuItem.TYPE_SEPARATOR:
                    if (convertView == null)
                    {
                        convertView = mInflater.inflate(R.layout.design_drawer_item_separator,
                                parent, false);
                    }
                    break;
            }

            return convertView;
        }

        public void setIconColor(Drawable icon)
        {
            int textColorSecondary = android.R.attr.textColorSecondary;
            TypedValue value = new TypedValue();
            if (!mContext.getTheme().resolveAttribute(textColorSecondary, value, true))
            {
                return;
            }
            int baseColor = mContext.getResources().getColor(value.resourceId);
            icon.setColorFilter(baseColor, PorterDuff.Mode.MULTIPLY);
        }
    }


}
