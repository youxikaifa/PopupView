package com.example.jin.popupview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.jin.popupview.custom.PopView;

public class MainActivity extends AppCompatActivity {

    public PopView popView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        popView = (PopView) findViewById(R.id.tv_content);
        popView.setText("你在干嘛几号放假好久发货多久啊师傅你要好好学习天天向上好好学习天天向上");
    }
}
