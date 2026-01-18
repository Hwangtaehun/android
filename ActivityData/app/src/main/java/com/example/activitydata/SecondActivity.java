package com.example.activitydata;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.activitydata.databinding.ActivitySecondBinding;

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

        Button3ClickListener listener3 = new Button3ClickListener();
        activitySecondBinding.button3.setOnClickListener(listener3);

        // SecondActivity를 실행하기 위해 사용한 Intent를 추출한다.
        Intent secondIntent = getIntent();
        // 데이터를 추출한다.
        int data1 = secondIntent.getIntExtra("data1", 0);
        double data2 = secondIntent.getDoubleExtra("data2", 0.0);
        boolean data3 = secondIntent.getBooleanExtra("data3", false);
        String data4 = secondIntent.getStringExtra("data4");

        activitySecondBinding.textView2.setText("data1 : " + data1 + "\n");
        activitySecondBinding.textView2.append("data2 : " + data2 + "\n");
        activitySecondBinding.textView2.append("data3 : " + data3 + "\n");
        activitySecondBinding.textView2.append("data4 : " + data4);
    }

    class Button3ClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            // 이전 Activity로 전달한 Intent를 설정하기
            Intent resultIntent = new Intent();
            resultIntent.putExtra("value1", 300);
            resultIntent.putExtra("value2", 33.33);
            resultIntent.putExtra("value3", true);
            resultIntent.putExtra("value4", "문자열3");

            setResult(RESULT_OK, resultIntent);
            finish();
        }
    }
}