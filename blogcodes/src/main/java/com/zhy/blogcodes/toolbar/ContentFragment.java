package com.zhy.blogcodes.toolbar;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by zhy on 15/4/26.
 */
public class ContentFragment extends Fragment {

    public static final String KEY_TITLE = "key_title";
    private String mTitle;

    public static ContentFragment newInstance(String title) {
        ContentFragment fragment = new ContentFragment();
        Bundle bundle = new Bundle();
        bundle.putString(KEY_TITLE, title);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        TextView tv = new TextView(getActivity());
        String title = (String) getArguments().get(KEY_TITLE);
        if (!TextUtils.isEmpty(title))
        {
            tv.setGravity(Gravity.CENTER);
            tv.setTextSize(40);
            tv.setText(title);
        }

        return tv;
    }
}
