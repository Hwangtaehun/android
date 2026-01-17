package com.example.startactivity;

import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.startactivity.databinding.ActivitySecondBinding;

public class SecondActivity extends AppCompatActivity {
    ActivitySecondBinding activitySecondBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activitySecondBinding = ActivitySecondBinding.inflate(getLayoutInflater());
        EdgeToEdge.enable(this);
        setContentView(activitySecondBinding.getRoot());
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        FinishSecondBtnClickeListener listener2 = new FinishSecondBtnClickeListener();
        activitySecondBinding.finishSecondBtn.setOnClickListener(listener2);
    }

    class FinishSecondBtnClickeListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            // 현재 Activity를 종료한다.
            finish();
        }
    }
}