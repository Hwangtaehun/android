package com.example.radiobutton;

import android.os.Bundle;
import android.view.View;
import android.widget.RadioGroup;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.radiobutton.databinding.ActivityMainBinding;

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

        CheckedChangeListener1 checkedChangeListener1 = new CheckedChangeListener1();
        activityMainBinding.radioGroup1.setOnCheckedChangeListener(checkedChangeListener1);
        activityMainBinding.radioGroup2.setOnCheckedChangeListener(checkedChangeListener1);

        ButtonClickListener3 buttonClickListener3 = new ButtonClickListener3();
        activityMainBinding.button3.setOnClickListener(buttonClickListener3);
    }

    class ButtonClickListener1 implements View.OnClickListener  {
        @Override
        public void onClick(View v) {
            // 체크 되어 있는 라디오 버튼의 아이디를 추출합니다.
            int checkId = activityMainBinding.radioGroup1.getCheckedRadioButtonId();
            System.out.println("buttonclick1 " + checkId);
            if (checkId == R.id.radioButton) {
                activityMainBinding.textView.setText("라디오 1-1");
            } else if (checkId == R.id.radioButton2) {
                activityMainBinding.textView.setText("라디오 1-2");
            } else if (checkId == R.id.radioButton3) {
                activityMainBinding.textView.setText("라디오 1-3");
            }
        }
    }

    class ButtonClickListener2 implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            int checkId = activityMainBinding.radioGroup2.getCheckedRadioButtonId();
            System.out.println("buttonclick2 " + checkId);
            if (checkId == R.id.radioButton4) {
                activityMainBinding.textView2.setText("라디오 2-1");
            } else if (checkId == R.id.radioButton5) {
                activityMainBinding.textView2.setText("라디오 2-2");
            } else if (checkId == R.id.radioButton6) {
                activityMainBinding.textView2.setText("라디오 2-3");
            }
        }
    }

    class CheckedChangeListener1 implements RadioGroup.OnCheckedChangeListener {
        @Override
        public void onCheckedChanged(@NonNull RadioGroup group, int checkedId) {
            // 체크 상태가 변경된 라다오 그룹의 아이디를 가져온다.
            int groupId = group.getId();

            if (groupId == R.id.radioGroup1) {
                if (checkedId == R.id.radioButton) {
                    activityMainBinding.textView3.setText("라디오 1-1");
                } else if (checkedId == R.id.radioButton2) {
                    activityMainBinding.textView3.setText("라디오 1-2");
                } else if (checkedId == R.id.radioButton3) {
                    activityMainBinding.textView3.setText("라디오 1-3");
                }
            } else if (groupId == R.id.radioGroup2) {
                if (checkedId == R.id.radioButton4) {
                    activityMainBinding.textView3.setText("라디오 2-1");
                } else if (checkedId == R.id.radioButton5) {
                    activityMainBinding.textView3.setText("라디오 2-2");
                } else if (checkedId == R.id.radioButton6) {
                    activityMainBinding.textView3.setText("라디오 2-3");
                }
            }
        }
    }

    class ButtonClickListener3 implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            // 체크 상태를 설정한다.
            activityMainBinding.radioButton3.setChecked(true);
            activityMainBinding.radioButton4.setChecked(true);
        }
    }
}