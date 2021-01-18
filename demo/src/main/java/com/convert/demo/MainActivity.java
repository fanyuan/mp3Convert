package com.convert.demo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.convert.mymp3convert.Mp3ConvertUtil;

public class MainActivity extends AppCompatActivity {
    TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv = findViewById(R.id.tv);
    }
    public void click(View v){
        Log.d("ddebug",Mp3ConvertUtil.getLameVer());
        tv.append("\n " + Mp3ConvertUtil.hello("hello"));
    }
}