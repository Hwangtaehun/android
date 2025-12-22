package com.example.checkbox;

import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.checkbox.databinding.ActivityMainBinding;

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

        CheckedChangeListener checkedChangeListener = new CheckedChangeListener();
        activityMainBinding.checkBox.setOnCheckedChangeListener(checkedChangeListener);
        activityMainBinding.checkBox2.setOnCheckedChangeListener(checkedChangeListener);
        activityMainBinding.checkBox3.setOnCheckedChangeListener(checkedChangeListener);
    }

    class ButtonClickListener1 implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            // 체크박스들의 체크 상태를 가져온다.
            boolean chk1 = activityMainBinding.checkBox.isChecked();
            boolean chk2 = activityMainBinding.checkBox2.isChecked();
            boolean chk3 = activityMainBinding.checkBox3.isChecked();

            activityMainBinding.textView.setText("");

            if(chk1 == true) {
                activityMainBinding.textView.append("첫 번째 체크 박스는 체크 되어 있습니다.\n");
            } else {
                activityMainBinding.textView.append("첫 번째 체크 박스는 체크 되어 있지 않습니다.\n");
            }

            if(chk2 == true) {
                activityMainBinding.textView.append("두 번째 체크 박스는 체크 되어 있습니다.\n");
            } else {
                activityMainBinding.textView.append("두 번째 체크 박스는 체크 되어 있지 않습니다.\n");
            }

            if(chk3 == true) {
                activityMainBinding.textView.append("세 번째 체크 박스는 체크 되어 있습니다.\n");
            } else {
                activityMainBinding.textView.append("세 번째 체크 박스는 체크 되어 있지 않습니다.\n");
            }
        }
    }

    class ButtonClickListener2 implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            // 체크 상태를 true로 설정한다.
            activityMainBinding.checkBox.setChecked(true);
            activityMainBinding.checkBox2.setChecked(true);
            activityMainBinding.checkBox3.setChecked(true);
        }
    }

    class ButtonClickListener3 implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            // 체크 상태를 false로 설정한다.
            activityMainBinding.checkBox.setChecked(false);
            activityMainBinding.checkBox2.setChecked(false);
            activityMainBinding.checkBox3.setChecked(false);
        }
    }

    class ButtonClickListener4 implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            // 체크 상태를 반전시킨다.
            activityMainBinding.checkBox.toggle();
            activityMainBinding.checkBox2.toggle();
            activityMainBinding.checkBox3.toggle();
        }
    }

    class CheckedChangeListener implements CompoundButton.OnCheckedChangeListener {
        @Override
        public void onCheckedChanged(@NonNull CompoundButton buttonView, boolean isChecked) {
            // 체크 상태가 변경된 checkbox의 id를 추출
            int checkboxId = buttonView.getId();

            // id로 분기한다.
            if (checkboxId == R.id.checkBox){
                if(isChecked == true) {
                    activityMainBinding.textView.setText("첫 번째 체크박스가 체크되었습니다.");
                } else {
                    activityMainBinding.textView.setText("첫 번째 체크박스가 해제되었습니다.");
                }
            } else if(checkboxId == R.id.checkBox2) {
                if(isChecked == true) {
                    activityMainBinding.textView.setText("두 번째 체크박스가 체크되었습니다.");
                } else {
                    activityMainBinding.textView.setText("두 번째 체크박스가 해제되었습니다.");
                }
            } else if(checkboxId == R.id.checkBox3) {
                if(isChecked == true) {
                    activityMainBinding.textView.setText("세 번째 체크박스가 체크되었습니다.");
                } else {
                    activityMainBinding.textView.setText("세 번째 체크박스가 해제되었습니다.");
                }
            }
        }
    }
}