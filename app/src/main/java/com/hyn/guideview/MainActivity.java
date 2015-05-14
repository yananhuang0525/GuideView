package com.hyn.guideview;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity {
    private EditText ed_name, ed_pass;
    private TextView tv_login;
    private CheckBox cb_remember, cb_autoLogin;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private String Name, Password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        ed_name = (EditText) findViewById(R.id.name);
        ed_pass = (EditText) findViewById(R.id.pass);
        cb_remember = (CheckBox) findViewById(R.id.remember_password);
        cb_autoLogin = (CheckBox) findViewById(R.id.auto_login);
        tv_login = (TextView) findViewById(R.id.login);
        sharedPreferences = getSharedPreferences("userId", MODE_PRIVATE);
        Name = sharedPreferences.getString("name", null);
        Password = sharedPreferences.getString("password", null);
        ed_name.setText(Name);
        ed_pass.setText(Password);
        cb_remember.setChecked(sharedPreferences.getBoolean("isRemember", false));
        cb_autoLogin.setChecked(sharedPreferences.getBoolean("isAutoLogin", false));
        boolean isBack = sharedPreferences.getBoolean("back", false);

        if (!isBack && cb_autoLogin.isChecked()) {
            if (ed_name.getText().toString().equals("hyn") && ed_pass.getText().toString().equals("123123")) {
//                Toast.makeText(getApplicationContext(), "自动登录成功", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(MainActivity.this, SuccessActivity.class);
                MainActivity.this.startActivity(intent);
                MainActivity.this.finish();
            }
        }
        tv_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ed_name.getText().toString().equals("hyn") && ed_pass.getText().toString().equals("123123")) {

//                    Toast.makeText(getApplicationContext(), "登录成功", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(MainActivity.this, SuccessActivity.class);
                    MainActivity.this.startActivity(intent);
                    MainActivity.this.finish();
                } else {

                    Toast.makeText(getApplicationContext(), "登录失败", Toast.LENGTH_SHORT).show();
                }
                editor = sharedPreferences.edit();
                editor.putString("name", ed_name.getText().toString());
                editor.commit();

            }
        });
        cb_remember.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    editor = sharedPreferences.edit();
                    editor.putString("password", ed_pass.getText().toString());
                    editor.putBoolean("isRemember", true);
                    editor.commit();
                } else {
                    editor = sharedPreferences.edit();
                    editor.putString("password", null);
                    editor.putBoolean("isRemember", false);
                    editor.commit();
                }
            }
        });
        cb_autoLogin.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    editor = sharedPreferences.edit();
                    editor.putBoolean("isAutoLogin", true);
                    editor.commit();
                } else {
                    editor = sharedPreferences.edit();
                    editor.putBoolean("isAutoLogin", false);
                    editor.commit();
                }
            }
        });
        editor = sharedPreferences.edit();
        editor.putBoolean("back", false);
        editor.commit();
        Log.i("账号", "" + ed_name.getText().toString());
        Log.i("密码", "" + ed_pass.getText());
    }

}
