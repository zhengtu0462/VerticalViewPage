# VerticalViewPager
竖直滑动的例子
实现仿抖音上下切换视频

在布局中使用VerticalViewPager 代替ViewPage
```
<com.zhengtu.verticalviewpagelibrary.VerticalViewPager
        android:id="@+id/main_view_page"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:scrollbars="none"
        android:overScrollMode="never"/>

```
activity中的代码
```
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
```
