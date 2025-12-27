package com.example.chip;

import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.chip.databinding.ActivityMainBinding;

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

        // Action Chip에 리스너를 설정
        ChipClickListener1 chipClickListener1 = new ChipClickListener1();
        activityMainBinding.chip.setOnClickListener(chipClickListener1);

        ButtonClickListener1 buttonClickListener1 = new ButtonClickListener1();
        activityMainBinding.button.setOnClickListener(buttonClickListener1);

        // 체크 상태가 변경되었을 때
        ChipCheckChangeListener chipCheckChangeListener = new ChipCheckChangeListener();
        activityMainBinding.chip2.setOnCheckedChangeListener(chipCheckChangeListener);

        // Close 버튼을 눌렀을 때
        ChipCloseClickListener chipCloseClickListener = new ChipCloseClickListener();
        activityMainBinding.chip3.setOnCloseIconClickListener(chipCloseClickListener);
    }

    //Style : Action
    class ChipClickListener1 implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            // Style : Choice
            // 체크 상태를 가져온다.
            boolean chk2 = activityMainBinding.chip2.isChecked();
            // Style: Entry
            boolean chk3 = activityMainBinding.chip3.isChecked();
            // Style: Fillter
            boolean chk4 = activityMainBinding.chip4.isChecked();

            activityMainBinding.textView.setText("첫 번째 Chip을 눌렀습니다.\n");

            if(chk2 == true) {
                activityMainBinding.textView.append("두 번째 Chip : true\n");
            } else {
                activityMainBinding.textView.append("두 번째 Chip : false\n");
            }

            if(chk3 == true) {
                activityMainBinding.textView.append("세 번째 Chip : true\n");
            } else {
                activityMainBinding.textView.append("세 번째 Chip : false\n");
            }

            if(chk4 == true) {
                activityMainBinding.textView.append("네 번째 Chip : true\n");
            } else {
                activityMainBinding.textView.append("네 번째 Chip : false\n");
            }

            // ChipGroup내에서 체크 되어 있는 group의 ID를 가져온다.
            int chipId = activityMainBinding.chipGroup1.getCheckedChipId();

            if(chipId == R.id.chip5) {
                activityMainBinding.textView.append("Group1이 선택되었습니다.\n");
            } else if(chipId == R.id.chip6) {
                activityMainBinding.textView.append("Group2이 선택되었습니다.\n");
            } else if(chipId == R.id.chip7) {
                activityMainBinding.textView.append("Group3이 선택되었습니다.\n");
            }
        }
    }

    class ButtonClickListener1 implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            // Chip의 체크 상태를 선택한다.
            activityMainBinding.chip2.setChecked(true);
            activityMainBinding.chip3.setChecked(true);
            activityMainBinding.chip4.setChecked(true);

            activityMainBinding.chip5.setChecked(true);
        }
    }

    // 체크 상태가 변경되었을 때
    class ChipCheckChangeListener implements CompoundButton.OnCheckedChangeListener {
        @Override
        public void onCheckedChanged(@NonNull CompoundButton buttonView, boolean isChecked) {
            if(isChecked == true) {
                activityMainBinding.textView.setText("체크 되었습니다.");
            } else {
                activityMainBinding.textView.setText("체크 해제 되었습니다.");
            }
        }
    }

    // Close 버튼 이벤트
    class ChipCloseClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            activityMainBinding.textView.setText("Close 버튼을 눌렀습니다.");
        }
    }
}