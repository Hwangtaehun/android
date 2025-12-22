package com.example.togglebutton;

import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.RadioGroup;
import android.widget.ToggleButton;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.togglebutton.databinding.ActivityMainBinding;

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

        ButtonClickListener3 buttonClickListener3 = new ButtonClickListener3();
        activityMainBinding.button3.setOnClickListener(buttonClickListener3);

        ButtonClickListener4 buttonClickListener4 = new ButtonClickListener4();
        activityMainBinding.button4.setOnClickListener(buttonClickListener4);

        ToggleClickListener1 toggleClickListener1 = new ToggleClickListener1();
        activityMainBinding.toggleButton.setOnClickListener(toggleClickListener1);

        ToggleCheckedChagneListener1 toggleCheckedChagneListener1 = new ToggleCheckedChagneListener1();
        activityMainBinding.toggleButton2.setOnCheckedChangeListener(toggleCheckedChagneListener1);
    }

    class ButtonClickListener1 implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            // ON/OFF 상태값을 가져온다.
            boolean a1 = activityMainBinding.toggleButton.isChecked();

            if(a1 == true){
                activityMainBinding.textView.setText("ON 상태입니다.");
            } else {
                activityMainBinding.textView.setText("OFF 상태입니다.");
            }
        }
    }

    class ButtonClickListener2 implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            activityMainBinding.toggleButton.setChecked(true);
            activityMainBinding.toggleButton2.setChecked(true);
        }
    }

    class ButtonClickListener3 implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            activityMainBinding.toggleButton.setChecked(false);
            activityMainBinding.toggleButton2.setChecked(false);
        }
    }

    class ButtonClickListener4 implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            activityMainBinding.toggleButton.toggle();
            activityMainBinding.toggleButton2.toggle();
        }
    }

    // 토글 버튼 클릭 이벤트
    class ToggleClickListener1 implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            // 토글 버튼의 상태를 가져온다.
            boolean a1 = activityMainBinding.toggleButton.isChecked();

            if(a1 == true){
                activityMainBinding.textView.setText("Toggle Click: ON");
            } else {
                activityMainBinding.textView.setText("Toggle Click: OFF");
            }
        }
    }

    // 토글 버튼의 상태가 변경되었을 때
    class ToggleCheckedChagneListener1 implements CompoundButton.OnCheckedChangeListener {
        @Override
        public void onCheckedChanged(@NonNull CompoundButton buttonView, boolean isChecked) {
            if(isChecked == true){
                activityMainBinding.textView2.setText("changed: ON");
            } else {
                activityMainBinding.textView2.setText("changed: OFF");
            }
        }
    }
}