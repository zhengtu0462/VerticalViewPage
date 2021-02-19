package com.zhengtu.verticalviewpager;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v7.app.AppCompatActivity;
import android.view.ViewGroup;

import com.zhengtu.verticalviewpagelibrary.MyViewPage;
import com.zhengtu.verticalviewpagelibrary.VerticalPageTransformer;
import com.zhengtu.verticalviewpagelibrary.VerticalViewPager;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    VerticalViewPager mVerticalViewPager;
    List<String> mStringList;
    DummyAdapter mDummyAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mVerticalViewPager = findViewById(R.id.main_view_page);
        initView();
        getMoreStr();
    }

    private void initView() {
        mStringList = new ArrayList<>();
        mDummyAdapter = new DummyAdapter(getSupportFragmentManager());
        mVerticalViewPager.setAdapter(mDummyAdapter);
        mVerticalViewPager.setPageTransformer(true, new VerticalPageTransformer());
        mVerticalViewPager.addOnPageChangeListener(new MyViewPage.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                //剩三个开始加载更多
                if ((position > mStringList.size() - 3) && position != 0) {
                    mHandler.sendEmptyMessageDelayed(0,3000);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void getMoreStr() {
        List<String> item = new ArrayList<>();
        item.add("0");
        mStringList.addAll(item);
        mDummyAdapter.notifyDataSetChanged();
        mHandler.sendEmptyMessageDelayed(0,3000);
    }

    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            mStringList.add(mStringList.size() + "");
            mStringList.add(mStringList.size() + "");
            mStringList.add(mStringList.size() + "");
            mDummyAdapter.notifyDataSetChanged();
        }
    };

    public class DummyAdapter extends FragmentPagerAdapter {

        public DummyAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return MainFragment.getInstance(mStringList.get(position));
        }

        @Override
        public int getCount() {
            return mStringList.size();
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            //super.destroyItem(container, position, object);
        }
    }
}
