package com.hyn.guideview;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

/**
 * Created by hyn on 2015/5/6.
 */
public class SuccessActivity extends Activity {
    private TextView tv;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_success);
        tv = (TextView) findViewById(R.id.back);
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sharedPreferences = getSharedPreferences("userId", MODE_PRIVATE);
                editor = sharedPreferences.edit();
                editor.putBoolean("back", true);
                editor.commit();
                Intent intent = new Intent(SuccessActivity.this, MainActivity.class);
                SuccessActivity.this.startActivity(intent);
                SuccessActivity.this.finish();

            }
        });
    }
}
