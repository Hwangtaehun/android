package com.example.scrollview;

import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.scrollview.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
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

        ButtonClickListener1 buttonClickListener1 = new ButtonClickListener1();
        activityMainBinding.button.setOnClickListener(buttonClickListener1);

        ButtonClickListener2 buttonClickListener2 = new ButtonClickListener2();
        activityMainBinding.button2.setOnClickListener(buttonClickListener2);

        // 스크롤이 발생 하였을 때..
        ScrollViewChangeListener1 scrollViewChangeListener1 = new ScrollViewChangeListener1();
        activityMainBinding.scroll1.setOnScrollChangeListener(scrollViewChangeListener1);
        activityMainBinding.scroll2.setOnScrollChangeListener(scrollViewChangeListener1);
    }

    class ButtonClickListener1 implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            // ScrollView의 Y좌표 값을 가져온다.
            int y = activityMainBinding.scroll1.getScrollY();
            // HorizontalScrollView의 X좌표 값을 가져온다.
            int x = activityMainBinding.scroll2.getScrollX();

            activityMainBinding.textView.setText("X : " + x);
            activityMainBinding.textView2.setText("Y : " + y);
        }
    }

    class ButtonClickListener2 implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            // 지정된 위치로 이동한다.
            // activityMainBinding.scroll1.scrollTo(0, 1000);
            // activityMainBinding.scroll2.scrollTo(1000, 0);

            // 현재 위치에서 지정된 만큼 이동한다.
            activityMainBinding.scroll1.scrollBy(0, 1000);
            activityMainBinding.scroll2.scrollBy(1000, 0);

            // 지정된 위치로 이동한다.
            // activityMainBinding.scroll1.smoothScrollTo(0, 1000);
            // activityMainBinding.scroll2.smoothScrollTo(1000, 0);

            // 현재 위치에서 지정된 만큼 이동한다.
            // activityMainBinding.scroll1.smoothScrollBy(0, 1000);
            // activityMainBinding.scroll2.smoothScrollBy(1000, 0);
        }
    }

    // 스크롤 뷰가 스크롤 되었을 때
    class ScrollViewChangeListener1 implements View.OnScrollChangeListener {
        // 첫 번째: 스크롤이 발생한 스크롤 뷰
        // 두 번째: 이동된 X 좌표
        // 세 번째: 이동된 Y 좌표
        // 네 번째: 이동되기 전 X 좌표
        // 다섯 번재: 이동되기 전 Y 좌표
        @Override
        public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
            // 스크롤 된 스크롤 뷰의 ID를 추출한다.
            int id = v.getId();

            if (id == R.id.scroll1) {
                activityMainBinding.textView2.setText("Y : " + oldScrollY + " -> " + scrollY);
            } else if (id == R.id.scroll2) {
                activityMainBinding.textView.setText("X : " + oldScrollX + " -> " + scrollX);
            }
        }
    }
}