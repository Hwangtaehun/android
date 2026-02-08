package com.example.messagenotification;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import androidx.core.app.Person;
import android.graphics.Color;
import android.graphics.drawable.Icon;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.graphics.Insets;
import androidx.core.graphics.drawable.IconCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.messagenotification.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding activityMainBinding;
    NotificationManager notificationManager;

    static final String NOTIFICATION_CHANNEL1_ID = "Message";
    static final String NOTIFICATION_CHANNEL1_NAME = "Message Style";

    static final int MESSAGE1_ID = 10;

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

        addNotificationChannel(NOTIFICATION_CHANNEL1_ID, NOTIFICATION_CHANNEL1_NAME);

        Button1ClickListener listener1 = new Button1ClickListener();
        activityMainBinding.button.setOnClickListener(listener1);
    }

    class Button1ClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            // 안드로이드 9.0 이상이라면..
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                NotificationCompat.Builder builder1 = getNotificationBuilder(NOTIFICATION_CHANNEL1_ID);
                builder1.setContentTitle("Message Style");
                builder1.setContentText("Message Style Notification");
                builder1.setSmallIcon(android.R.drawable.ic_input_delete);

                // 사람1
                Person.Builder personBuilder1 = new Person.Builder();
                IconCompat icon1 = IconCompat.createWithResource(MainActivity.this, android.R.drawable.ic_media_next);
                personBuilder1.setIcon(icon1);
                personBuilder1.setName("홍길동");
                Person person1 = personBuilder1.build();

                // 사람2
                Person.Builder personBuilder2 = new Person.Builder();
                IconCompat icon2 = IconCompat.createWithResource(MainActivity.this, R.mipmap.ic_launcher);
                personBuilder2.setIcon(icon2);
                personBuilder2.setName("최길동");
                Person person2 = personBuilder2.build();

                // Message Style Notification을 생성한다.
                NotificationCompat.MessagingStyle messagingStyle = new NotificationCompat.MessagingStyle(person1);

                // 대화 내용을 설정한다.
                messagingStyle.addMessage("첫 번째 메시지", System.currentTimeMillis(), person1);
                messagingStyle.addMessage("두 번째 메시지", System.currentTimeMillis(), person2);
                messagingStyle.addMessage("세 번째 메시지", System.currentTimeMillis(), person1);
                messagingStyle.addMessage("네 번째 메시지", System.currentTimeMillis(), person2);

                builder1.setStyle(messagingStyle);

                Notification notification = builder1.build();
                notificationManager.notify(MESSAGE1_ID, notification);
            }
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