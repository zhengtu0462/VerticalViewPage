package com.zhengtu.verticalviewpager;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class MainFragment extends Fragment {
    TextView mContent;
    static String mText = "";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, null);
        mContent = view.findViewById(R.id.fragment_content);
        return view;
        //super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.e(">>>>>>>>>>>>>>", "onActivityCreated: "+mText );
        mContent.setText(mText + "");
    }

    public static MainFragment getInstance(String text) {
        mText = text;
        return new MainFragment();
    }
}
