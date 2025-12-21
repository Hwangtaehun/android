package com.example.textview;

import android.graphics.Color;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.textview.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding activityMainBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        activityMainBinding = ActivityMainBinding.inflate(getLayoutInflater());

        EdgeToEdge.enable(this);
        setContentView(activityMainBinding.getRoot());
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // 문자열 설정
        activityMainBinding.textView2.setText("안녕하세요.");

        // 문자열 가져오기
        String str1 = activityMainBinding.textView2.getText().toString();
        System.out.println(str1);

        // 배경 색상 지정
        // activityMainBinding.textView2.setBackgroundColor(Color.RED);
        // activityMainBinding.textView2.setBackgroundColor(Color.rgb(0, 255, 191));
        activityMainBinding.textView2.setBackgroundColor(Color.argb(50, 0, 255,191));

        activityMainBinding.textView2.setText("문자열1");

        // 문자열 추가
        activityMainBinding.textView2.append("\n문자열2");
        activityMainBinding.textView2.append("\n문자열3");
    }
}