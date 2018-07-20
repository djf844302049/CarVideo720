package com.example.a844302049.carvideo1;

import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.VideoView;


public class Mp4Activity extends AppCompatActivity {

    private String url;

    @SuppressLint("JavascriptInterface")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mp4_layout);
        initView();
    }
    private void initView(){
        url = getIntent().getStringExtra("mp4");
        VideoView videoView = findViewById(R.id.videoView);
        videoView.setVideoPath(url);
        videoView.requestFocus();
        videoView.start();
    }

}
