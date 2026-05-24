package com.example.actionbarbasic;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.actionbarbasic.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding activityMainBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        activityMainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(activityMainBinding.getRoot());
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    // 옵션 메뉴를 구성하는 메뉴
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    // 메뉴를 선택하면 호출되는 메서드
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        // 누른 메뉴의 id를 가져온다.
        int itemId = item.getItemId();
        
        if (itemId == R.id.item1) {
            activityMainBinding.textView.setText("첫 번째 메뉴");
        } else if (itemId == R.id.item2) {
            activityMainBinding.textView.setText("두 번째 메뉴");
        } else if (itemId == R.id.item3) {
            activityMainBinding.textView.setText("세 번째 메뉴");
        } else if (itemId == R.id.item4) {
            activityMainBinding.textView.setText("네 번째 메뉴");
        }
        
        return super.onOptionsItemSelected(item);
    }
}