package com.example.fragment;

import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.fragment.databinding.ActivityMainBinding;

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
            // Fragment 객체를 생성한다.
            FirstFragment firstFragment = FirstFragment.newInstance();
            // Fragment 관리자를 가져온다.
            FragmentManager fragmentManager = getSupportFragmentManager();
            // Fragment를 설정한다.
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

            //fragmentTransaction.add(R.id.fragmentContainerView, firstFragment);
            fragmentTransaction.replace(R.id.fragmentContainerView, firstFragment);
            fragmentTransaction.addToBackStack(null);

            fragmentTransaction.commit();
        }
    }

    class Button2ClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            SecondFragment secondFragment = new SecondFragment();
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            //fragmentTransaction.add(R.id.fragmentContainerView, secondFragment);
            fragmentTransaction.replace(R.id.fragmentContainerView, secondFragment);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
        }
    }

    class Button3ClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            // Fragment 관리자를 추출한다.
            FragmentManager fragmentManager = getSupportFragmentManager();
            // 현재 BackStack에 담겨있는 Fragment의 수를 구해온다.
            int count = fragmentManager.getBackStackEntryCount();
            // 0개가 아니라면..
            if(count >  0) {
                // BackStack에서 Fragment를 제거한다.
                fragmentManager.popBackStack();
            } else {
                // 더 이상 Fragment가 없으면 Activity를 종료한다.
                finish();
            }
        }
    }
}