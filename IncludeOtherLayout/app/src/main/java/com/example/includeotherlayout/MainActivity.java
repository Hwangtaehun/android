package com.example.includeotherlayout;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.includeotherlayout.databinding.ActivityMainBinding;
import com.example.includeotherlayout.databinding.SecondBinding;
import com.example.includeotherlayout.databinding.ThirdBinding;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding activityMainBinding;
    SecondBinding secondBinding;
    ThirdBinding thirdBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        activityMainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        secondBinding = SecondBinding.inflate(getLayoutInflater());
        thirdBinding = ThirdBinding.inflate(getLayoutInflater());

        setContentView(activityMainBinding.getRoot());

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        activityMainBinding.textView.setText("첫 번째 문자열 입니다.");
        // secondBinding.textView.setText("두 번째 문자열 입니다.");
        // thirdBinding.textView.setText("세 번째 문자열 입니다.");
        activityMainBinding.secondLayout.textView.setText("두 번째 문자열 입니다.");
        activityMainBinding.thridLayout.textView.setText("세 번째 문자열 입니다.");
    }
}