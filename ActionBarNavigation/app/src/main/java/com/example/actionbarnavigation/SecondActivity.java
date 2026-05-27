package com.example.actionbarnavigation;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.actionbarnavigation.databinding.ActivitySecondBinding;

public class SecondActivity extends AppCompatActivity {
    ActivitySecondBinding activitySecondBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        activitySecondBinding = ActivitySecondBinding.inflate(getLayoutInflater());
        setContentView(activitySecondBinding.getRoot());

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // ActionBar 객체를 추출한다.
        ActionBar actionBar = getSupportActionBar();

        // HomeButton을 활성화 한다.
        actionBar.setHomeButtonEnabled(true);

        // HomeButton을 노출시킨다.
        actionBar.setDisplayHomeAsUpEnabled(true);

        // 아이콘을 설정해준다.
        // actionBar.setHomeAsUpIndicator(R.mipmap.ic_launcher);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();

        switch (itemId) {
            // home 버튼을 눌렀을 때..
            case android.R.id.home:
                // 현재 Activity를 종료한다.
                finish();
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}