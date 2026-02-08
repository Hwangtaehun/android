package com.example.handlermessage;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.handlermessage.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding activityMainBinding;

    boolean isRunning = false;
    HanderClass handerClass;

    int value1 = 100;
    int value2 = 200;
    String value3 = "문자열";

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

        // Handler 객체 생성
        handerClass = new HanderClass();

        // 오래 걸리는 작업을 처리하기 위해
        // 새로운 쓰레드를 발생시켰다.
        isRunning = true;
        ThreadClass threadClass = new ThreadClass();
        threadClass.start();
    }

    class Button1ClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            long now = System.currentTimeMillis();
            activityMainBinding.textView.setText("버튼 클릭 : " + now);
        }
    }

    class ThreadClass extends Thread {
        @Override
        public void run() {
            super.run();

            while(isRunning){
                SystemClock.sleep(500);
                // 핸들러에게 작업을 요청한다.
                handerClass.sendEmptyMessage(0);

                SystemClock.sleep(500);
                // 핸들러에게 작업을 요청한다.
                handerClass.sendEmptyMessage(1);

                SystemClock.sleep(500);
                // 데이터를 담을 Message 객체
                Message msg = new Message();
                msg.what = 2;

                value1 += 1;
                value2 += 1;
                value3 += 1;

                msg.arg1 = value1;
                msg.arg2 = value2;
                msg.obj = value3;

                handerClass.sendMessage(msg);
            }
        }
    }

    // MainThread가 처리해야할 작업을 구현하는 핸들러
    class HanderClass extends Handler{
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            // what 값으로 분기한다.
            switch (msg.what){
                case 0:
                    activityMainBinding.textView2.setText("Handler : 0");
                    break;
                case 1:
                    activityMainBinding.textView2.setText("Handler : 1");
                    break;
                case 2:
                    activityMainBinding.textView2.setText("Handler : 2\n");
                    activityMainBinding.textView2.append("arg1 : " + msg.arg1 + "\n");
                    activityMainBinding.textView2.append("arg2 : " + msg.arg2 + "\n");
                    activityMainBinding.textView2.append("arg3 : " + msg.obj + "\n");
            }
        }
    }
}