package com.example.pendingintent;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
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

import com.example.pendingintent.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding activityMainBinding;

    NotificationManager notificationManager;

    static final String NOTIFICATION_CHANNEL1_ID = "Padding";
    static final String NOTIFICATION_CHANNEL1_NAME = "pending intent";

    static final int MESSAGE1_ID = 10;
    static final int MESSAGE2_ID = 20;

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

        notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        addNotificationChannel(NOTIFICATION_CHANNEL1_ID, NOTIFICATION_CHANNEL1_NAME);

        Button1ClickListener listener1 = new Button1ClickListener();
        activityMainBinding.button.setOnClickListener(listener1);

        Button2ClickListener listener2 = new Button2ClickListener();
        activityMainBinding.button2.setOnClickListener(listener2);
    }

    class Button1ClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            NotificationCompat.Builder builder1 = getNotificationBuilder(NOTIFICATION_CHANNEL1_ID);
            builder1.setContentTitle("Notification 1");
            builder1.setContentText("알림 메시지 1입니다.");
            builder1.setSmallIcon(android.R.drawable.ic_menu_search);

            // 메시지를 터차하면 자동으로 메시지를 제거하게 한다.
            builder1.setAutoCancel(true);

            // 메시지를 터치했을 때 실행할 Activity를 지정한다.
            Intent intent1 = new Intent(MainActivity.this, Notification1Activity.class);
            // 실행될 Activity에 전달할 데이터
            intent1.putExtra("data1", 100);
            intent1.putExtra("data2", 200);

            PendingIntent pending1 = PendingIntent.getActivity(MainActivity.this, 10,
                    intent1, PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE);
            builder1.setContentIntent(pending1);

            // Action 설정
            Intent intent2 = new Intent(MainActivity.this, Notification3Activity.class);
            PendingIntent pending2 = PendingIntent.getActivity(MainActivity.this, 100,
            intent2, PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE);
            NotificationCompat.Action.Builder builder2 = new NotificationCompat.Action.Builder(
                    android.R.drawable.ic_menu_compass, "Action 1", pending2
            );
            NotificationCompat.Action action2 = builder2.build();
            builder1.addAction(action2);

            Intent intent3 = new Intent(MainActivity.this, Notification4Activity.class);
            PendingIntent pending3 = PendingIntent.getActivity(MainActivity.this, 100,
                    intent3, PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE);
            NotificationCompat.Action.Builder builder3 = new NotificationCompat.Action.Builder(
                    android.R.drawable.ic_menu_agenda, "Action 2", pending3
            );
            NotificationCompat.Action action3 = builder3.build();
            builder1.addAction(action3);

            Notification notification = builder1.build();
            notificationManager.notify(MESSAGE1_ID, notification);
        }
    }

    class Button2ClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            NotificationCompat.Builder builder1 = getNotificationBuilder(NOTIFICATION_CHANNEL1_ID);
            builder1.setContentTitle("Notification 2");
            builder1.setContentText("알림 메시지 2입니다.");
            builder1.setSmallIcon(android.R.drawable.ic_menu_search);

            // 메시지를 터차하면 자동으로 메시지를 제거하게 한다.
            builder1.setAutoCancel(true);

            // 메시지를 터치했을 때 실행할 Activity를 지정한다.
            Intent intent1 = new Intent(MainActivity.this, Notification2Activity.class);
            PendingIntent pending1 = PendingIntent.getActivity(MainActivity.this, 10,
                    intent1, PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE);
            builder1.setContentIntent(pending1);

            Notification notification = builder1.build();
            notificationManager.notify(MESSAGE2_ID, notification);
        }
    }

    public void addNotificationChannel(String id, String name){

        notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);


        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            NotificationChannel channel = notificationManager.getNotificationChannel(id);

            if(channel == null){
                channel = new NotificationChannel(id, name, NotificationManager.IMPORTANCE_HIGH);
                channel.enableLights(true);
                channel.setLightColor(Color.RED);
                channel.enableVibration(true);
                notificationManager.createNotificationChannel(channel);
            }
        }
    }

    public NotificationCompat.Builder getNotificationBuilder(String id){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            NotificationCompat.Builder builder = new NotificationCompat.Builder(this, id);
            return builder;
        } else {
            NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
            return builder;
        }
    }
}