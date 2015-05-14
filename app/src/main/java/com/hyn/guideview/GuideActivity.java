package com.hyn.guideview;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hyn on 2015/5/5.
 */
public class GuideActivity extends Activity implements ViewPager.OnPageChangeListener {
    private ViewPager viewPager;
    private List<View> views;
    private ImageView[] dots;
    private MyViewPagerAdapter adapter;
    private int currentIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_guide);
        initViews();
        initDots();
    }

    private void initViews() {
        LayoutInflater inflater = LayoutInflater.from(this);
        views = new ArrayList<View>();
        views.add(inflater.inflate(R.layout.what_new_one, null));
        views.add(inflater.inflate(R.layout.what_new_two, null));
        views.add(inflater.inflate(R.layout.what_new_three, null));
        views.add(inflater.inflate(R.layout.what_new_four, null));
        adapter = new MyViewPagerAdapter(views, this);
        viewPager = (ViewPager) findViewById(R.id.viewpage);
        viewPager.setAdapter(adapter);
        viewPager.setOnPageChangeListener(this);
    }

    private void initDots() {
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.layout);
        dots = new ImageView[views.size()];
        for (int i = 0; i < views.size(); i++) {
            dots[i] = (ImageView) linearLayout.getChildAt(i);
            dots[i].setEnabled(true);
        }
        currentIndex = 0;
        dots[currentIndex].setEnabled(false);
    }

    private void setCurrentDots(int position) {
        if (position < 0 || position > views.size()) {
            return;
        }
        dots[position].setEnabled(false);
        dots[currentIndex].setEnabled(true);
        currentIndex = position;
    }

    @Override
    public void onPageScrolled(int i, float v, int i2) {

    }

    @Override
    public void onPageSelected(int i) {
        setCurrentDots(i);
    }

    @Override
    public void onPageScrollStateChanged(int i) {


    }
}
