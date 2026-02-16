package com.example.activityanimation;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.activityanimation.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding activityMainBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        activityMainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(activityMainBinding.getRoot());

        Button1ClickListener listener1 = new Button1ClickListener();
        activityMainBinding.button.setOnClickListener(listener1);
    }

    class Button1ClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            Intent secondIntent = new Intent(MainActivity.this, SecondActivity.class);
            startActivity(secondIntent);

            // 애니메이션 지정
            // 나타나는 액티비티의 애니메이션, 현재 액티비티의 사라지는 애니메이션
            //overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            //overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
            overridePendingTransition(R.anim.slide_xml1, R.anim.slide_xml2);
        }
    }
}