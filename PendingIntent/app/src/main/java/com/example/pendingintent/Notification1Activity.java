package com.example.pendingintent;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.pendingintent.databinding.ActivityNotification1Binding;

public class Notification1Activity extends AppCompatActivity {

    ActivityNotification1Binding activityNotification1Binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        activityNotification1Binding = ActivityNotification1Binding.inflate(getLayoutInflater());
        setContentView(activityNotification1Binding.getRoot());

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Activity 실행을 위해 사용되어진 Intent를 추출한다.
        Intent intent = getIntent();

        // 데이터를 추출한다.
        int data1 = intent.getIntExtra("data1", 0);
        int data2 = intent.getIntExtra("data2", 0);

        activityNotification1Binding.textView.setText("data1 : "+ data1 + "\n");
        activityNotification1Binding.textView.append("data2 : " + data2);
    }
}