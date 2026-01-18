package com.example.onactivityresult;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.onactivityresult.databinding.ActivityThirdBinding;

public class ThirdActivity extends AppCompatActivity {
    ActivityThirdBinding activityThirdBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityThirdBinding = ActivityThirdBinding.inflate(getLayoutInflater());

        EdgeToEdge.enable(this);
        setContentView(activityThirdBinding.getRoot());
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Button4ClickListener listener4 = new Button4ClickListener();
        activityThirdBinding.button4.setOnClickListener(listener4);

        Button5ClickListener listener5 = new Button5ClickListener();
        activityThirdBinding.button5.setOnClickListener(listener5);

        Button6ClickListener listener6 = new Button6ClickListener();
        activityThirdBinding.button6.setOnClickListener(listener6);

        Button7ClickListener listener7 = new Button7ClickListener();
        activityThirdBinding.button7.setOnClickListener(listener7);
    }

    class Button4ClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            // 작업이 잘 완료 되었다는 의미
            setResult(RESULT_OK);
            finish();
        }
    }

    class Button5ClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            // 작업이 취소 되었다는 의미
            setResult(RESULT_CANCELED);
            finish();
        }
    }

    class Button6ClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            // 사용자 정의
            setResult(RESULT_FIRST_USER);
            finish();
        }
    }

    class Button7ClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            // 사용자 정의
            setResult(RESULT_FIRST_USER + 1);
            finish();
        }
    }
}