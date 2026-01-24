package com.example.toast;

import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.toast.databinding.ActivityMainBinding;
import com.example.toast.databinding.CustomToastBinding;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding activityMainBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        activityMainBinding = ActivityMainBinding.inflate(getLayoutInflater());

        setContentView(activityMainBinding.getRoot());
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Button1ClickListener listener1 = new Button1ClickListener();
        activityMainBinding.button.setOnClickListener(listener1);

        Button2ClickListener listener2 = new Button2ClickListener();
        activityMainBinding.button2.setOnClickListener(listener2);
    }

    class Button1ClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            // Toast 객체를 생성한다.
            Toast t1 = Toast.makeText(MainActivity.this, "기본 Toast 입니다.", Toast.LENGTH_SHORT);

            // Callback 설정
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                ToastCallback toastCallback = new ToastCallback();
                t1.addCallback(toastCallback);
            }

            // Toast 메시지를 보여준다.
            t1.show();
        }
    }

    class Button2ClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            // Toast에 보여줄 View를 관리할 ViewBinder를 생성한다.
            CustomToastBinding customToastBinding = CustomToastBinding.inflate(getLayoutInflater());
            customToastBinding.imageView.setImageResource(R.drawable.img_android);
            customToastBinding.textView.setText("Custom Toast 입니다");

            // 배경을 설정한다.
            customToastBinding.getRoot().setBackgroundResource(android.R.drawable.toast_frame);

            // Toast를 생성한다.
            Toast t2 = new Toast(MainActivity.this);

            // View를 설정한다.
            t2.setView(customToastBinding.getRoot());
            // 위치 설정
            t2.setGravity(Gravity.CENTER, 0, 300);

            // 시간 설정
            t2.setDuration(Toast.LENGTH_LONG);

            // 메시지를 띄운다.
            t2.show();
        }
    }

    // API Level 30(안드로이드 10) 버전 부터 사용한다고
    // 명시한다.
    @RequiresApi(30)
    // Toast의 callback 클래스
    class ToastCallback extends Toast.Callback {
        // Toast 메시지가 나타날 때
        @Override
        public void onToastShown() {
            super.onToastShown();
            
            activityMainBinding.textView2.setText("Toast 메시지가 나타났습니다.");
        }

        // Toast 메시지가 사라질 때
        @Override
        public void onToastHidden() {
            super.onToastHidden();

            activityMainBinding.textView2.setText("Toast 메시지가 사라졌습니다.");
        }
    }
}