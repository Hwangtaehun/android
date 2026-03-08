package com.example.app03_community;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.view.animation.AnticipateInterpolator;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.splashscreen.SplashScreen;
import androidx.core.splashscreen.SplashScreenViewProvider;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.app03_community.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding activityMainBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // 기본
        // SplashScreen.installSplashScreen(this);

//        // 사라질때의 애니메이션 적용
//        SplashScreen splashScreen = SplashScreen.installSplashScreen(this);
//        splashScreen.setOnExitAnimationListener(splashScreenViewProvider -> {
//            // 가로 비율 애니메이션
//            PropertyValuesHolder scaleX = PropertyValuesHolder.ofFloat(View.SCALE_X, 1f, 2f, 1f, 0f);
//            // 세로 비율 애니메이션
//            PropertyValuesHolder scaleY = PropertyValuesHolder.ofFloat(View.SCALE_Y, 1f, 2f, 1f, 0f);
//            // 투명도
//            PropertyValuesHolder alpha = PropertyValuesHolder.ofFloat(View.ALPHA, 1f, 1f, 0,5f, 0f);
//
//            // SplashScreen의 아이콘 View를 추출한다.
//            View iconView = splashScreenViewProvider.getIconView();
//
//            // 애니메이션 관리 객체를 생성한다.
//            // 첫 번째 뷰: 애니메이션을 적용할 뷰
//            // 나머지는 적용할 애니메이션 종류
//            ObjectAnimator objectAnimator = ObjectAnimator.ofPropertyValuesHolder(iconView, scaleX, scaleY, alpha);
//            // 애니메이션 적용을 위한 수학적 계산 방식
//            objectAnimator.setInterpolator(new AnticipateInterpolator());
//            // 애니메이션 동작 시간
//            objectAnimator.setDuration(1000);
//            // 애니메이션이 끝났을 때 동작할 리스너
//            objectAnimator.addListener(new AnimatorListenerAdapter() {
//                @Override
//                public void onAnimationEnd(Animator animation) {
//                    super.onAnimationEnd(animation);
//                    // SplashScreen을 제거한다.
//                    splashScreenViewProvider.remove();
//                }
//            });
//
//            // 애니메이션 가동
//            objectAnimator.start();
//        });
//
//        SystemClock.sleep(1000);

        // gif 이미지 사용
        SplashScreen.installSplashScreen(this);
        SystemClock.sleep(4000);

        EdgeToEdge.enable(this);
        activityMainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(activityMainBinding.getRoot());
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

//    class SplashScreenListener implements SplashScreen.OnExitAnimationListener {
//        @Override
//        public void onSplashScreenExit(@NonNull SplashScreenViewProvider splashScreenViewProvider) {
//
//        }
//    }
}