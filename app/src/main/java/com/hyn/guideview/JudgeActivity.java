package com.hyn.guideview;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

/**
 * Created by hyn on 2015/5/5.
 */
public class JudgeActivity extends Activity {
    boolean isFirstIn = false;
    private static final int GO_HOME = 1000;
    private static final int GO_GUIDE = 1001;
    private static final long JUDGE_DELAY_MILLIS = 2000;
    private static final String NAME = "first_pref";

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case GO_HOME:
                    goHome();
                    break;
                case GO_GUIDE:
                    goGuide();
                    break;
            }
            super.handleMessage(msg);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_judge);
        init();
    }

    private void init() {
        SharedPreferences preferences = getSharedPreferences(NAME, MODE_PRIVATE);
        isFirstIn = preferences.getBoolean("isFirstIn", true);
        if (isFirstIn) {
            handler.sendEmptyMessageDelayed(GO_GUIDE, JUDGE_DELAY_MILLIS);
        } else {
            handler.sendEmptyMessageDelayed(GO_HOME, JUDGE_DELAY_MILLIS);
        }
    }

    private void goHome() {
        Intent intent = new Intent(JudgeActivity.this, MainActivity.class);
        JudgeActivity.this.startActivity(intent);
        JudgeActivity.this.finish();
    }

    private void goGuide() {
        Intent intent = new Intent(JudgeActivity.this, GuideActivity.class);
        JudgeActivity.this.startActivity(intent);
        JudgeActivity.this.finish();
    }
}
