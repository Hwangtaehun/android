package com.example.aswitch;

import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.aswitch.databinding.ActivityMainBinding;

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

        SwithchCheckedChangeListener1 swithchCheckedChangeListener1 = new SwithchCheckedChangeListener1();
        activityMainBinding.switch1.setOnCheckedChangeListener(swithchCheckedChangeListener1);
        activityMainBinding.switch2.setOnCheckedChangeListener(swithchCheckedChangeListener1);
    }

    class ButtonClickListener1 implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            // 스위치의 상태값을 가져온다.
            boolean chk1 = activityMainBinding.switch1.isChecked();

            if(chk1 == true) {
                activityMainBinding.textView.setText("ON 상태입니다.");
            } else {
                activityMainBinding.textView.setText("OFF 상태입니다.");
            }
        }
    }

    class ButtonClickListener2 implements View.OnClickListener
    {
        @Override
        public void onClick(View v) {
            //On 상태로 설정한다.
            activityMainBinding.switch1.setChecked(true);
        }
    }

    class ButtonClickListener3 implements View.OnClickListener
    {
        @Override
        public void onClick(View v) {
            //OFF 상태로 설정한다.
            activityMainBinding.switch1.setChecked(false);
        }
    }

    // 스위치의 ON/OFF 상태가 변경될 때...
    class SwithchCheckedChangeListener1 implements CompoundButton.OnCheckedChangeListener {
        @Override
        public void onCheckedChanged(@NonNull CompoundButton buttonView, boolean isChecked) {
            // 상태가 변경된 스위치의 ID를 추출한다.
            int switchId = buttonView.getId();

            if (switchId == R.id.switch1) {
                // ON/OFF 여부에 따른 분기
                if (isChecked == true) {
                    activityMainBinding.textView.setText("스위치 1 : ON");
                } else {
                    activityMainBinding.textView.setText("스위치 1 : OFF");
                }
            } else if (switchId == R.id.switch2) {
                if (isChecked == true) {
                    activityMainBinding.textView2.setText("스위치 2 : ON");
                } else {
                    activityMainBinding.textView2.setText("스위치 2 : OFF");
                }
            }
        }
    }
}