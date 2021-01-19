package com.convert.mymp3convert;

import android.Manifest;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    TextView tv;
    String [] perms = {Manifest.permission.MODIFY_AUDIO_SETTINGS,Manifest.permission.RECORD_AUDIO,
            Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.WRITE_EXTERNAL_STORAGE};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv = findViewById(R.id.tv);
        String msg = Mp3ConvertUtil.hello("test");
        tv.setText(msg);
        Log.d("ddebug","hello jni = " + msg);

        ActivityCompat.requestPermissions(this, perms, 123);
    }
    public void test(View v){
        String str = MyTest.test();
        tv.append("\n"+str);
        Log.d("ddebug","test str = " + "str" + "----" + Mp3ConvertUtil.getLameVer());
        //Mp3ConvertUtil.convertmp3("test.wav","demo.mp3");
    }
    public void convert(View v){
        String path = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "temp/out.wav";//"temp/test123.wav";
        String pathTaret = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "temp/out123.mp3";
        Mp3ConvertUtil.convertmp3(path,pathTaret);
    }
    public void convertByHelper01(View v){
//        String path = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "temp/out.wav";//"temp/test123.wav";
//        String pathTaret = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "temp/out456.mp3";
//        Mp3ConvertUtilHelper.convertmp3(path,pathTaret);

        new Thread(){
            @Override
            public void run() {

                String path = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "temp/姑娘我爱你convert.wav";//"temp/test123.wav";
                String pathTaret = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "temp/姑娘我爱你out123.mp3";
                Mp3ConvertUtilHelper.convertmp3(path,pathTaret);

            }
        }.start();
    }
    public void convertByHelper02(View v){
        new Thread(){
            @Override
            public void run() {

                String path = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "temp/out测试.wav";//"temp/test123.wav";
                String pathTaret = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "temp/out测试789.mp3";
                Mp3ConvertUtilHelper.convertmp3(path, pathTaret, new Mp3ConvertUtil.ConvertListener() {
                    @Override
                    public void notifyConvertProgress(int progress) {
                        Log.d("ddebug","convertByHelper02 --- notifyConvertProgress = " + progress);
                    }

                    @Override
                    public void convertFinish() {
                        Log.d("ddebug","convertByHelper02 --- convertFinish");
                    }

                    @Override
                    public void convertError(String errorMsg) {
                        Log.d("ddebug","convertByHelper02 --- convertError --- " + errorMsg);
                    }
                });

            }
        }.start();

    }
}