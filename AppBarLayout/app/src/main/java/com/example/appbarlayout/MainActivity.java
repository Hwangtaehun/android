package com.example.appbarlayout;

import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.appbarlayout.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding activityMainBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        activityMainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(activityMainBinding.getRoot());

        setSupportActionBar(activityMainBinding.toolbar);
        setTitle("타이틀 입니다.");

        // 접혔을 때의 타이틀 색상
        activityMainBinding.toolbarLayout.setCollapsedTitleTextColor(Color.WHITE);
        // 펼쳐졌을 때의 타이틀 색상
        activityMainBinding.toolbarLayout.setExpandedTitleColor(Color.GREEN);

        // 접혔을 때의 타이틀 위치
        activityMainBinding.toolbarLayout.setCollapsedTitleGravity(Gravity.CENTER_HORIZONTAL);
        // 펄져졌을 때의 타이틀 위치
        activityMainBinding.toolbarLayout.setExpandedTitleGravity(Gravity.RIGHT + Gravity.TOP);

        // 펄쳐졌을 때의 이미지 설정
        // activityMainBinding.appBarImage.setImageResource(R.mipmap.ic_launcher);
    }
}