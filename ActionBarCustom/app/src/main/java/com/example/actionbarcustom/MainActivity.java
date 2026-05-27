package com.example.actionbarcustom;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.actionbarcustom.databinding.ActivityMainBinding;
import com.example.actionbarcustom.databinding.CustomActionbarBinding;

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

        // ActionBar를 추출한다.
        ActionBar actionBar = getSupportActionBar();

        // 커스터마이징 하기 위해 지정한 뷰를 보여줄 수 있게 설정한다.
        actionBar.setDisplayShowCustomEnabled(true);
        // 홈버튼이 동작하지 않게 한다.
        actionBar.setDisplayHomeAsUpEnabled(false);
        // 홈버튼이 나타지 않게 한다.
        actionBar.setDisplayShowHomeEnabled(false);
        // 타이틀이 나타나지 않게 한다.
        actionBar.setDisplayShowTitleEnabled(false);

        //ActionBar에 뷰를 설정한다.
        CustomActionbarBinding customActionbarBinding = CustomActionbarBinding.inflate(getLayoutInflater());
        actionBar.setCustomView(customActionbarBinding.getRoot());

        customActionbarBinding.textView.setText("커스텀 액션바");
        customActionbarBinding.textView.setTextColor(Color.BLUE);

        ActionBarButtonClickListener listener1 = new ActionBarButtonClickListener();
        customActionbarBinding.button.setOnClickListener(listener1);
    }

    class ActionBarButtonClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            activityMainBinding.textView2.setText("액션바의 버튼을 눌렀습니다.");
        }
    }
}