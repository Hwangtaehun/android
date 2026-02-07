package com.example.notification;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.notification.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    // Notification Channel의 코드상에서 관리할 이름
    static final String NOTIFICATION_CHANNEL1_ID = "CHANNEL1";
    static final String NOTIFICATION_CHANNEL2_ID = "CHANNEL2";

    // 사용자에게 노출할 Notification Channel 이름
    static final String NOTIFICATION_CHANNEL1_NAME = "첫 번째 채널";
    static final String NOTIFICATION_CHANNEL2_NAME = "두 번째 채널";

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

        // Notification Channel을 등록한다.
        addNotficationChannel(NOTIFICATION_CHANNEL1_ID, NOTIFICATION_CHANNEL1_NAME);
        addNotficationChannel(NOTIFICATION_CHANNEL2_ID, NOTIFICATION_CHANNEL2_NAME);
    }

    // Notification Channel을 등록하는 메서드
    // 첫 번째 : 코드에서 채널을 관리하기 위한 이름
    // 두 번째 : 사용자에게 노출 시킬 이름
    public void addNotficationChannel(String id, String name){
        // 안드로이드 8.0 이상일 때만 동작하도록 한다.
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            // 알림 메시지를 관리하는 객체를 추출한다.
            NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

            // id를 통해 Notification 객체를 추출한다.
            NotificationChannel channel = manager.getNotificationChannel(id);

            // 추출된 Channel이 없다면 (등록된 적이 없다면...)
            if(channel == null) {
                // 채널 객체를 생성한다.
                channel = new NotificationChannel(id, name, NotificationManager.IMPORTANCE_HIGH);
                // 메시지 출력시 단말기 LED를 사용할 것인가..
                channel.enableLights(true);
                // LED 색상 설정
                channel.setLightColor(Color.RED);
                // 진동을 사용할 것 인가.
                channel.enableVibration(true);

                // 알림 메시지를 관리하는 객체에 채널을 등록한다.
                manager.createNotificationChannel(channel);
            }
        }
    }
}