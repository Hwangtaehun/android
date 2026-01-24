package com.example.snackbar;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.snackbar.databinding.ActivityMainBinding;
import com.example.snackbar.databinding.CustomSnackbarBinding;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding activityMainBinding;

    // SnackBar의 출현 시간을 LENGTH_INDFINITE로 설정했을 경우
    // 메시지를 닫는 처리를 위해 맴버 변수로 선언한다.
    Snackbar snackbar1;

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
            // SnackBar 객체를 생성한다.
            // Snackbar snackbar1 = Snackbar.make(v, "기본 스낵바", Snackbar.LENGTH_SHORT);
            // Snackbar snackbar1 = Snackbar.make(v, "기본 스낵바", Snackbar.LENGTH_LONG);
            snackbar1 = Snackbar.make(v, "기본 스낵바", Snackbar.LENGTH_INDEFINITE);

            // 메시지 색상
            snackbar1.setTextColor(Color.RED);
            // 배경색
            snackbar1.setBackgroundTint(Color.BLUE);
            // 애니메이션
            // snackbar1.setAnimationMode(Snackbar.ANIMATION_MODE_FADE);
            snackbar1.setAnimationMode(Snackbar.ANIMATION_MODE_SLIDE);

            SnackBarActionClickListener listener1 = new SnackBarActionClickListener();
            snackbar1.setAction("Action", listener1);

            // Callback 설정
            SnackBarCallback callback = new SnackBarCallback();
            snackbar1.addCallback(callback);

            snackbar1.show();
        }
    }

    class Button2ClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            if(snackbar1 != null) {
                // 현재 Snackbar 메시지가 보여지고 있는 상태라면...
                if(snackbar1.isShown() == true) {
                    // SnackBar 메시지를 사라지게 한다.
                    snackbar1.dismiss();
                }
            }
        }
    }

    // SnackBar의 Action을 클릭하면 사용하는 리스너
    class SnackBarActionClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            activityMainBinding.textView.setText("Action Click");
        }
    }

    // SnackBar의 Callback
    class SnackBarCallback extends BaseTransientBottomBar.BaseCallback<Snackbar> {
        // SnackBar가 나타났을 때
        @Override
        public void onShown(Snackbar transientBottomBar) {
            super.onShown(transientBottomBar);

            activityMainBinding.textView2.setText("SnackBar가 나타났습니다.");
        }

        // SnackBar가 사라졌을 때
        @Override
        public void onDismissed(Snackbar transientBottomBar, int event) {
            super.onDismissed(transientBottomBar, event);

            activityMainBinding.textView2.setText("SnackBar가 사라졌습니다.");
        }
    }

    class Button3ClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            // 스낵바를 생성한다.
            Snackbar snackbar2 = Snackbar.make(v, "Custom SnackBar", Snackbar.LENGTH_SHORT);

            // ViewBinding
            CustomSnackbarBinding customSnackbarBinding = CustomSnackbarBinding.inflate(getLayoutInflater());

            customSnackbarBinding.imageView.setImageResource(R.drawable.img_android);
            customSnackbarBinding.textView3.setText("새로 추가된 View");
            customSnackbarBinding.textView3.setTextColor(Color.WHITE);

            // SnackBar Layout을 추출해서 새로운 뷰를 추가한다.
            Snackbar.SnackbarLayout layout = (Snackbar.SnackbarLayout) snackbar2.getView();
            layout.addView(customSnackbarBinding.getRoot());

            // 기본 TextView 숨기기 (R 사용 안 함)
            for (int i = 0; i < layout.getChildCount(); i++) {
                View child = layout.getChildAt(i);
                if (child instanceof TextView) {
                    child.setVisibility(View.GONE);
                }
            }

            // TextView snackText = layout.findViewById(com.google.android.material.R.id.snackbar_text);
            // snackText.setVisibility(View.INVISIBLE);

            snackbar2.show();
        }
    }
}