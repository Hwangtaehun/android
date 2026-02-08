package com.example.kindofnotification;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
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

import com.example.kindofnotification.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding activityMainBinding;
    NotificationManager notificationManager;
    static final String NOTIFICATION_CHANNEL1_ID = "style";
    static final String NOTIFICATION_CHANNEL1_NAME = "style Notification";
    static final int MESSAGE1_ID = 10;
    static final int MESSAGE2_ID = 20;
    static final int MESSAGE3_ID = 30;

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

        Button2ClickListener listener2 = new Button2ClickListener();
        activityMainBinding.button2.setOnClickListener(listener2);

        Button3ClickListener listener3 = new Button3ClickListener();
        activityMainBinding.button3.setOnClickListener(listener3);
    }

    class Button1ClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            NotificationCompat.Builder builder1 = getNotificationBuilder(NOTIFICATION_CHANNEL1_ID);
            builder1.setContentTitle("Big Picture");
            builder1.setContentText("Big Picture Notification");
            builder1.setSmallIcon(android.R.drawable.ic_menu_camera);

            // BigPicture Notification 객체를 생성한다.
            NotificationCompat.BigPictureStyle big = new NotificationCompat.BigPictureStyle(builder1);
            // 보여줄 이미지를 설정한다.
            Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.img_android);
            big.bigPicture(bitmap);
            big.setBigContentTitle("Big Content Title");
            big.setSummaryText("Summary Text");

            Notification notification = builder1.build();
            notificationManager.notify(MESSAGE1_ID, notification);
        }
    }

    class Button2ClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            NotificationCompat.Builder builder1 = getNotificationBuilder(NOTIFICATION_CHANNEL1_ID);
            builder1.setContentTitle("Bit Text");
            builder1.setContentText("Big Text Notification");
            builder1.setSmallIcon(android.R.drawable.ic_menu_search);

            // Big Text Notification을 생성한다.
            NotificationCompat.BigTextStyle big = new NotificationCompat.BigTextStyle(builder1);
            big.setBigContentTitle("Big Content Title");
            big.setSummaryText("Summary Text");
            big.bigText("동해물과 백두산이 마르고 닳도록\n 하느님이 보우하사 우리나라 만세");

            Notification notification = builder1.build();
            notificationManager.notify(MESSAGE2_ID, notification);
        }
    }

    class Button3ClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            NotificationCompat.Builder builder1 = getNotificationBuilder(NOTIFICATION_CHANNEL1_ID);
            builder1.setContentTitle("InBox");
            builder1.setContentText("InBox Notification");
            builder1.setSmallIcon(android.R.drawable.ic_menu_call);

            // InBox Style Notification을 생성 한다.
            NotificationCompat.InboxStyle inbox = new NotificationCompat.InboxStyle(builder1);
            inbox.setSummaryText("SummaryText");

            inbox.addLine("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
            inbox.addLine("bbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbb");
            inbox.addLine("cccccccccccccccccccccccccccccccccccccccccccccccccccccc");
            inbox.addLine("dddddddddddddddddddddddddddddddddddddddddddddddddddddd");
            inbox.addLine("eeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee");
            inbox.addLine("ffffffffffffffffffffffffffffffffffffffffffffffffffffff");

            Notification notification = builder1.build();
            notificationManager.notify(MESSAGE3_ID, notification);
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