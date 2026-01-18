package com.example.activityaction;

import android.Manifest;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.activityaction.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding activityMainBinding;

    // 확인 받을 권한
    String [] permissionList = {
            Manifest.permission.CALL_PHONE
    };

    ActivityResultLauncher<String []> permissionLauncher;

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

//        ActivityResultContracts.RequestMultiplePermissions r1 = new ActivityResultContracts.RequestMultiplePermissions();
//        permissionLauncher = registerForActivityResult(r1, result -> {});
//        permissionLauncher.launch(permissionList);

        requestPermissions(permissionList, 0);

        Button1ClickListener listener1 = new Button1ClickListener();
        activityMainBinding.button.setOnClickListener(listener1);

        Button2ClickListener listener2 = new Button2ClickListener();
        activityMainBinding.button2.setOnClickListener(listener2);

        Button3ClickListener listener3 = new Button3ClickListener();
        activityMainBinding.button3.setOnClickListener(listener3);

        Button4ClickListener listener4 = new Button4ClickListener();
        activityMainBinding.button4.setOnClickListener(listener4);
    }

    class Button1ClickListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            // 위도와 경도를 가지고 있는 Uri 객체 생성한다.
            Uri uri = Uri.parse("geo:37.243243,131.861691");
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            startActivity(intent);
        }
    }

    class Button2ClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            // 요청할 페이지의 주소를 가지고 있는 Uri 객체를 생성한다.
            Uri uri = Uri.parse("https://developer.android.com");
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            startActivity(intent);
        }
    }

    class Button3ClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            // 전화 번호를 가지고 있는 Uri 객체를 생성한다.
            Uri uri = Uri.parse("tel:12341234");
            Intent intent = new Intent(Intent.ACTION_DIAL, uri);
            startActivity(intent);
        }
    }

    class Button4ClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            // 전화 번호를 가지고 있는 Uri 객체를 생성한다.
            Uri uri = Uri.parse("tell:12341234");
            Intent intent = new Intent(Intent.ACTION_CALL, uri);
            startActivity(intent);
        }
    }
}