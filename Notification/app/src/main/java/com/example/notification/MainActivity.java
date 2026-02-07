package com.example.notification;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
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

    // 메시지의 번호
    static final int MESSAGE1_ID = 10;
    static final int MESSAGE2_ID = 20;
    static final int MESSAGE3_ID = 30;
    static final int MESSAGE4_ID = 40;

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

        Button1ClickListener listener1 = new Button1ClickListener();
        activityMainBinding.button.setOnClickListener(listener1);

        Button2ClickListener listener2 = new Button2ClickListener();
        activityMainBinding.button2.setOnClickListener(listener2);

        Button3ClickListener listener3 = new Button3ClickListener();
        activityMainBinding.button3.setOnClickListener(listener3);

        Button4ClickListener listener4 = new Button4ClickListener();
        activityMainBinding.button4.setOnClickListener(listener4);
    }

    class Button1ClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            // NotificationBuilder를 가져온다.
            NotificationCompat.Builder builder = getNotificationBuilder(NOTIFICATION_CHANNEL1_ID);

            // 작은 아이콘 (메시지 수신시 상단에 보여줄 작은 아이콘, 안드로이드 버전에 따라서 노출되지 않을 수 있다.)
            builder.setSmallIcon(android.R.drawable.ic_menu_search);

            // 큰 아이콘 (메시지 본문에 표시할 메시지. Bitmap 객체)
            Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
            builder.setLargeIcon(bitmap);

            // 숫자 설정
            builder.setNumber(100);
            // 타이틀 설정
            builder.setContentTitle("Content Title 1");
            // 메시지 설정
            builder.setContentText("Content Text 1");

            // 메시지 객체를 생성한다.
            Notification notification = builder.build();
            // 알림 메시지를 관리하는 객체를 추출한다.
            NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            // 메시지를 출력한다.
            manager.notify(MESSAGE1_ID, notification);
        }
    }

    class Button2ClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            // NotificationBuilder를 가져온다.
            NotificationCompat.Builder builder = getNotificationBuilder(NOTIFICATION_CHANNEL1_ID);

            // 작은 아이콘 (메시지 수신시 상단에 보여줄 작은 아이콘, 안드로이드 버전에 따라서 노출되지 않을 수 있다.)
            builder.setSmallIcon(android.R.drawable.ic_menu_search);

            // 큰 아이콘 (메시지 본문에 표시할 메시지. Bitmap 객체)
            Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
            builder.setLargeIcon(bitmap);

            // 숫자 설정
            builder.setNumber(200);
            // 타이틀 설정
            builder.setContentTitle("Content Title 2");
            // 메시지 설정
            builder.setContentText("Content Text 2");

            // 메시지 객체를 생성한다.
            Notification notification = builder.build();
            // 알림 메시지를 관리하는 객체를 추출한다.
            NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            // 메시지를 출력한다.
            manager.notify(MESSAGE2_ID, notification);
        }
    }

    class Button3ClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            // NotificationBuilder를 가져온다.
            NotificationCompat.Builder builder = getNotificationBuilder(NOTIFICATION_CHANNEL2_ID);

            // 작은 아이콘 (메시지 수신시 상단에 보여줄 작은 아이콘, 안드로이드 버전에 따라서 노출되지 않을 수 있다.)
            builder.setSmallIcon(android.R.drawable.ic_menu_search);

            // 큰 아이콘 (메시지 본문에 표시할 메시지. Bitmap 객체)
            Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
            builder.setLargeIcon(bitmap);

            // 숫자 설정
            builder.setNumber(300);
            // 타이틀 설정
            builder.setContentTitle("Content Title 3");
            // 메시지 설정
            builder.setContentText("Content Text 3");

            // 메시지 객체를 생성한다.
            Notification notification = builder.build();
            // 알림 메시지를 관리하는 객체를 추출한다.
            NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            // 메시지를 출력한다.
            manager.notify(MESSAGE3_ID, notification);
        }
    }

    class Button4ClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            // NotificationBuilder를 가져온다.
            NotificationCompat.Builder builder = getNotificationBuilder(NOTIFICATION_CHANNEL2_ID);

            // 작은 아이콘 (메시지 수신시 상단에 보여줄 작은 아이콘, 안드로이드 버전에 따라서 노출되지 않을 수 있다.)
            builder.setSmallIcon(android.R.drawable.ic_menu_search);

            // 큰 아이콘 (메시지 본문에 표시할 메시지. Bitmap 객체)
            Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
            builder.setLargeIcon(bitmap);

            // 숫자 설정
            builder.setNumber(400);
            // 타이틀 설정
            builder.setContentTitle("Content Title 4");
            // 메시지 설정
            builder.setContentText("Content Text 4");

            // 메시지 객체를 생성한다.
            Notification notification = builder.build();
            // 알림 메시지를 관리하는 객체를 추출한다.
            NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            // 메시지를 출력한다.
            manager.notify(MESSAGE4_ID, notification);
        }
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

    // Notification 메시지 관리 객체를 생성하는 메서드
    // 첫 번째 : 코드상으로 관리하는 Notification Channel의 이름
    public NotificationCompat.Builder getNotificationBuilder(String id) {
        // 안드로이드 8.0 이상인 경우에만...
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationCompat.Builder builder = new NotificationCompat.Builder(this, id);
            return builder;
        } else {
            NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
            return builder;
        }
    }
}