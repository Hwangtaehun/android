package com.example.viewpager2;

import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import com.example.viewpager2.databinding.ActivityMainBinding;

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

        // Adapter를 생성한다.
        MyFragmentStateAdapter myFragmentStateAdapter = new MyFragmentStateAdapter(this);
        activityMainBinding.pager2.setAdapter(myFragmentStateAdapter);

        // Callback을 설정해준다.
        MyPageChangeCallback myPageChangeCallback = new MyPageChangeCallback();
        activityMainBinding.pager2.registerOnPageChangeCallback(myPageChangeCallback);

        // 스크롤 방향을 오른쪽으로 설정한다.
        // activityMainBinding.pager2.setLayoutDirection(View.LAYOUT_DIRECTION_RTL);

        // 스크롤 방향을 위아래로 설정한다.
        // activityMainBinding.pager2.setOrientation(ViewPager2.ORIENTATION_VERTICAL);
    }

    // ViewPager2를 구성하기 위해 사용할 어뎁터
    class MyFragmentStateAdapter extends FragmentStateAdapter {

        public MyFragmentStateAdapter(@NonNull FragmentActivity fragmentActivity) {
            super(fragmentActivity);
        }

        // Fragment를 생성해서 반환하는 함수
        // position: 현재 페이지의 번호
        @NonNull
        @Override
        public Fragment createFragment(int position) {
            // 반환할 Fragment를 담을 변수
            Fragment fragment = null;

            // 현재 페이지 번호를 맞는 Fragment 객체를 생성하여 반환한다.
            switch (position) {
                case 0:
                    fragment = new Sub1Fragment();
                    break;
                case 1:
                    fragment = new Sub2Fragment();
                    break;
                case 2:
                    fragment = new Sub3Fragment();
                    break;
                case 3:
                    fragment = new Sub1Fragment();
                    break;
                case 4:
                    fragment = new Sub2Fragment();
                    break;
                case 5:
                    fragment = new Sub3Fragment();
                    break;
            }

            return fragment;
        }

        // 전체 페이지의 개수
        @Override
        public int getItemCount() {
            return 6;
        }
    }

    // page가 슬라이드 될 때 반응하는 리스너
    class MyPageChangeCallback extends ViewPager2.OnPageChangeCallback {

        // position: 현재 슬라이드 된 페이지의 번호
        @Override
        public void onPageSelected(int position) {
            super.onPageSelected(position);
            activityMainBinding.textView.setText("page selected : " + position);
        }
    }
}