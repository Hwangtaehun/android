package com.example.startactivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.startactivity.databinding.ActivityMainBinding;

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

        ToSecondBtnClickListener listener1 = new ToSecondBtnClickListener();
        activityMainBinding.toSecondBtn.setOnClickListener(listener1);
    }
    
    class ToSecondBtnClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            // SecondActivity 실행을 위한 정보를 담고 있는 Intent를 생성한다.
            Intent secondActivityIntent = new Intent(MainActivity.this, SecondActivity.class);
            // Activity를 실행한다.
            startActivity(secondActivityIntent);
        }
    }
}