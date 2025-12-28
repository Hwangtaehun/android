package com.example.ratingbar;

import android.os.Bundle;
import android.view.View;
import android.widget.RatingBar;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.ratingbar.databinding.ActivityMainBinding;

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

        // RatingBar 값이 변경 되었을 때
        RatingBarChangedListener ratingBarChangedListener = new RatingBarChangedListener();
        activityMainBinding.ratingBar.setOnRatingBarChangeListener(ratingBarChangedListener);
        activityMainBinding.ratingBar2.setOnRatingBarChangeListener(ratingBarChangedListener);
        activityMainBinding.ratingBar3.setOnRatingBarChangeListener(ratingBarChangedListener);
        activityMainBinding.ratingBar4.setOnRatingBarChangeListener(ratingBarChangedListener);
    }

    class ButtonClickListener1 implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            // rating 바의 값을 가져온다.
            float rating1 = activityMainBinding.ratingBar.getRating();
            float rating2 = activityMainBinding.ratingBar2.getRating();
            float rating3 = activityMainBinding.ratingBar3.getRating();
            float rating4 = activityMainBinding.ratingBar4.getRating();

            activityMainBinding.textView.setText("Rating1 : " + rating1 + "\n");
            activityMainBinding.textView.append("Rating2 : " + rating2 + "\n");
            activityMainBinding.textView.append("Rating3 : " + rating3 + "\n");
            activityMainBinding.textView.append("Rating4 : " + rating4);
        }
    }

    class ButtonClickListener2 implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            // ratingbar에 값 설정한다.
            activityMainBinding.ratingBar.setRating(1.0f);
            activityMainBinding.ratingBar2.setRating(2.0f);
            activityMainBinding.ratingBar3.setRating(3.5f);
            activityMainBinding.ratingBar4.setRating(4.0f);
        }
    }

    // RatingBar의 값을 설정했을때...
    class RatingBarChangedListener implements RatingBar.OnRatingBarChangeListener {
        @Override
        // 첫 번째 : 값이 변경된 RatingBar
        // 두 번째 : 설정된 값
        // 세 번재 : 사용자 설정 여부(true : 사용자가 설정한 것)
        public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
            // 값 변경이 발생된 RatingBar의 ID를 추출한다.
            int ratingBarId = ratingBar.getId();

            if(ratingBarId == R.id.ratingBar) {
                activityMainBinding.textView.setText("Rating 1");
            } else if(ratingBarId == R.id.ratingBar2) {
                activityMainBinding.textView.setText("Rating 2");
            } else if(ratingBarId == R.id.ratingBar3) {
                activityMainBinding.textView.setText("Rating 3");
            } else if(ratingBarId == R.id.ratingBar4) {
                activityMainBinding.textView.setText("Rating 4");
            }

            // 현재 설정된 값
            activityMainBinding.textView2.setText("Value : " + rating);

            // 사용자 설정 여부
            if(fromUser == true) {
                activityMainBinding.textView3.setText("사용자에 의해 설정 되었습니다.");
            } else {
                activityMainBinding.textView3.setText("코드를 통해 설정 되었습니다.");
            }
        }
    }
}