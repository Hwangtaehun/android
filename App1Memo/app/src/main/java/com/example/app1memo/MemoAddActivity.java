package com.example.app1memo;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.app1memo.databinding.ActivityMemoAddBinding;

public class MemoAddActivity extends AppCompatActivity {
   ActivityMemoAddBinding activityMemoAddBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMemoAddBinding = ActivityMemoAddBinding.inflate(getLayoutInflater());

        EdgeToEdge.enable(this);
        setContentView(activityMemoAddBinding.getRoot());
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        
        // toolbar 설정
        setSupportActionBar(activityMemoAddBinding.memoAddToolbar);
        setTitle("메모 추가");

        // 이전 버튼 설정
        ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();

        // Home 버튼
        if(itemId == android.R.id.home) {
            // 현재 액티비티를 종료한다.
            finish();
        }

        return super.onOptionsItemSelected(item);
    }
}