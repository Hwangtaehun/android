package com.example.progressbar;

import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.progressbar.databinding.ActivityMainBinding;

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

        ButtonClickListnere2 buttonClickListnere2 = new ButtonClickListnere2();
        activityMainBinding.button2.setOnClickListener(buttonClickListnere2);

        ButtonClickListener3 buttonClickListener3 = new ButtonClickListener3();
        activityMainBinding.button3.setOnClickListener(buttonClickListener3);
        
        ButtonClickListener4 buttonClickListener4 = new ButtonClickListener4();
        activityMainBinding.button4.setOnClickListener(buttonClickListener4);
    }

    class ButtonClickListener1 implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            // 현재 값을 가져온다.
            int value = activityMainBinding.progressBar4.getProgress();
            activityMainBinding.textView.setText("value : " + value);
        }
    }

    class ButtonClickListnere2 implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            // 현재 값을 설정한다.
            activityMainBinding.progressBar4.setProgress(140);
        }
    }

    class ButtonClickListener3 implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            // 10 만큼 증가한다.
            activityMainBinding.progressBar4.incrementProgressBy(10);
        }
    }

    class ButtonClickListener4 implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            // 10 만큼 감소한다.
            activityMainBinding.progressBar4.incrementProgressBy(-10);
        }
    }
}