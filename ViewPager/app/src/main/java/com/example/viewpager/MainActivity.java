package com.example.viewpager;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.viewpager.databinding.ActivityMainBinding;
import com.example.viewpager.databinding.SubView1Binding;
import com.example.viewpager.databinding.SubView2Binding;
import com.example.viewpager.databinding.SubView3Binding;
import com.example.viewpager.databinding.SubView4Binding;
import com.example.viewpager.databinding.SubView5Binding;

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

        MyViewPagerAdapter adapter = new MyViewPagerAdapter();
        activityMainBinding.pager.setAdapter(adapter);

        MyPagerListener myPagerListener = new MyPagerListener();
        activityMainBinding.pager.addOnPageChangeListener(myPagerListener);

        activityMainBinding.textView.setText("Page Selected : 0");
    }

    class MyViewPagerAdapter extends PagerAdapter {

        // viewPager를 통해 보여줄 View의 개수
        @Override
        public int getCount() {
            return 5;
        }

        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
            return view == object;
        }

        // ViewPager를 통해 보여줄 View를 반환하는 함수
        @NonNull
        @Override
        public Object instantiateItem(@NonNull ViewGroup container, int position) {
            View returnView = null;

            if(position == 0) {
                SubView1Binding subView1Binding = SubView1Binding.inflate(getLayoutInflater());
                returnView = subView1Binding.getRoot();
            } else if(position == 1) {
                SubView2Binding subView2Binding = SubView2Binding.inflate(getLayoutInflater());
                returnView = subView2Binding.getRoot();
            } else if(position == 2) {
                SubView3Binding subView3Binding = SubView3Binding.inflate(getLayoutInflater());
                returnView = subView3Binding.getRoot();
            } else if(position == 3) {
                SubView4Binding subView4Binding = SubView4Binding.inflate(getLayoutInflater());
                returnView = subView4Binding.getRoot();
            } else if(position == 4) {
                SubView5Binding subView5Binding = SubView5Binding.inflate(getLayoutInflater());
                returnView = subView5Binding.getRoot();
            }

            container.addView(returnView);

            return returnView;
        }

        @Override
        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
            //super.destroyItem(container, position, object);
            container.removeView((View)object);
        }
    }

    // ViewPAge에서 View가 바뀔 때 반응하는 리스너
    class MyPagerListener implements ViewPager.OnPageChangeListener {

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        // 새로운 뷰가 나타났을때
        @Override
        public void onPageSelected(int position) {
            activityMainBinding.textView.setText("Page Selected : " + position);
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    }
}