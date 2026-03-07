package com.example.mvvm;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.mvvm.databinding.ActivityShowBinding;

// model : 애플리케이션에서 사용되는 모든 데이터를 관리하는 요소들
// repository : 서버나 데이터 베이스에서 데이터를 가져오거나 저장, 수정, 삭제 등의 작업을 한다.
// viewmodel : 화면 구성을 위해 피룡한 데이터를 관리하는 요소들

public class ShowActivity extends AppCompatActivity {
    ActivityShowBinding activityShowBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        activityShowBinding = ActivityShowBinding.inflate(getLayoutInflater());
        setContentView(activityShowBinding.getRoot());
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        activityShowBinding.buttonShow.setOnClickListener(v -> {
            finish();
        });
    }
}