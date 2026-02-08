package com.example.thread;

import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.thread.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding activityMainBinding;

    boolean isRunning = false;

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

        Button1ClickListener listener1 = new Button1ClickListener();
        activityMainBinding.button.setOnClickListener(listener1);

//        while (true) {
//            SystemClock.sleep(100);
//            long now2 = System.currentTimeMillis();
//            Log.d("test", "while 문 : " + now2);
//        }

        isRunning = true;
        TreadClass treadClass = new TreadClass();
        treadClass.start();
    }

    class Button1ClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            // 현재 시간을 구한다.
            long now = System.currentTimeMillis();
            activityMainBinding.textView.setText("버튼 클릭 : " + now);
        }
    }

    class TreadClass extends Thread {
        @Override
        public void run() {
            super.run();

            while (isRunning) {
                SystemClock.sleep(100);
                long now2 = System.currentTimeMillis();
                Log.d("test", "Thread : " + now2);
                // 안드로이드 8.0 미만 버전에서는 개발자가 발생시킨 쓰레드에서
                // 화면 처리 작업을 수행하면 오류가 발생한다.
                // activityMainBinding.textView2.setText("Thread : " + now2);
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        isRunning = false;
    }
}