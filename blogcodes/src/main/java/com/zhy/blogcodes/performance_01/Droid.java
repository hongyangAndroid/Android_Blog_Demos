package com.zhy.blogcodes.performance_01;

import com.zhy.blogcodes.R;

import java.util.ArrayList;
import java.util.List;

public class Droid
{
    public String name;
    public int imageId;
    public String date;
    public String msg;


    public Droid(String msg, String date, int imageId, String name)
    {
        this.msg = msg;
        this.date = date;
        this.imageId = imageId;
        this.name = name;
    }

    public static List<Droid> generateDatas()
    {
        List<Droid> datas = new ArrayList<Droid>();

        datas.add(new Droid("Lorem ipsum dolor sit amet, orci nullam cra", "3分钟前", -1, "alex"));
        datas.add(new Droid("Omnis aptent magnis suspendisse ipsum, semper egestas", "12分钟前", R.drawable.joanna, "john"));
        datas.add(new Droid("eu nibh, rhoncus wisi posuere lacus, ad erat egestas", "17分钟前", -1, "7heaven"));
        datas.add(new Droid("eu nibh, rhoncus wisi posuere lacus, ad erat egestas", "33分钟前", R.drawable.shailen, "Lseven"));

        return datas;
    }


}