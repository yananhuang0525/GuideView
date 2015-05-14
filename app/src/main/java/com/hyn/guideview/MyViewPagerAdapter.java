package com.hyn.guideview;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.List;

/**
 * Created by hyn on 2015/5/5.
 */
public class MyViewPagerAdapter extends PagerAdapter {
    private List<View> views;
    private Activity activity;
    private static final String NAME = "first_pref";

    public MyViewPagerAdapter(List<View> views, Activity activity) {
        this.views = views;
        this.activity = activity;
    }

    @Override
    public int getCount() {
        return views.size();
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
         container.removeView(views.get(position));
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        container.addView(views.get(position), 0);
        if (position == views.size() - 1) {
            ImageView imageView = (ImageView) container.findViewById(R.id.iv_start_weibo);
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
//                    setGuide();
                    goHome();
                }
            });
        }
        return views.get(position);
    }

    private void setGuide() {
        SharedPreferences preferences = activity.getSharedPreferences(NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean("isFirstIn", false);
        editor.commit();
    }

    private void goHome() {
        Intent intent = new Intent(activity, MainActivity.class);
        activity.startActivity(intent);
        activity.finish();
    }

    @Override
    public boolean isViewFromObject(View view, Object o) {
        return view == o;
    }
}
