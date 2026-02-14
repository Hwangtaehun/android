package com.example.broadcastreceiver;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.broadcastreceiver.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding activityMainBinding;
    TestReceiver br = new TestReceiver();

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

        // 안드로이드 8.0 이상 부터는 BR의 이름은 코드로 등록하고 해제해야 한다.
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            IntentFilter filter = new IntentFilter("kr.co.softcampus.testbr");
            registerReceiver(br, filter, RECEIVER_EXPORTED);
        }
    }

    class Button1ClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            // 실행할 BR를 관리하는 Intent를 생성한다.
            //Intent intent = new Intent(MainActivity.this, TestReceiver.class);

            // 등록된 이름으로 실행 요청을 한다.
            Intent intent = new Intent("kr.co.softcampus.testbr");
            sendBroadcast(intent);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        // 등록된 BR를 해제한다.
        if(Build.VERSION.SDK_INT > Build.VERSION_CODES.O) {
            unregisterReceiver(br);
        }
    }
}