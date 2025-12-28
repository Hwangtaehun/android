package com.example.seekbar;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.seekbar.databinding.ActivityMainBinding;

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

        SeekBarChangeListener seekBarChangeListener = new SeekBarChangeListener();
        activityMainBinding.seekBar.setOnSeekBarChangeListener(seekBarChangeListener);
        activityMainBinding.seekBar2.setOnSeekBarChangeListener(seekBarChangeListener);
    }

    class ButtonClickListener1 implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            // SeekBar에 설정된 값을 가져온다.
            int value1 = activityMainBinding.seekBar.getProgress();
            int value2 = activityMainBinding.seekBar2.getProgress();

            activityMainBinding.textView.setText("seekBar1 : " + value1);
            activityMainBinding.textView2.setText("seekBar2 : " + value2);
        }
    }

    class ButtonClickListener2 implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            // 값을 설정한다.
            activityMainBinding.seekBar.setProgress(1);
            // 안드로이드 누가 버전 이상인 경우 애니메이션 추가
            if(Build.VERSION.SDK_INT > Build.VERSION_CODES.N){
                activityMainBinding.seekBar2.setProgress(9, true);
            } else {
                activityMainBinding.seekBar2.setProgress(9);
            }
        }
    }

    class ButtonClickListener3 implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            // 1 증가
            activityMainBinding.seekBar.incrementProgressBy(1);
            activityMainBinding.seekBar2.incrementProgressBy(1);
        }
    }

    class ButtonClickListener4 implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            // 1 감소
            activityMainBinding.seekBar.incrementProgressBy(-1);
            activityMainBinding.seekBar2.incrementProgressBy(-1);
        }
    }

    // Seekbar의 값이 변경되었을 때
    class SeekBarChangeListener implements SeekBar.OnSeekBarChangeListener {
        // 값이 변경된 후
        @Override
        // 첫 번째 : 값이 설정된 Seekbar
        // 두 번째 : 설정된 값
        // 세 번째 : 사용자 변경 여부
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            // 값이 변경된 Seekbar의 아이디
            int seekBarId = seekBar.getId();

            if (seekBarId == R.id.seekBar) {
                activityMainBinding.textView.setText("첫 번째 SeekBar : " + progress + "\n");
            } else if (seekBarId == R.id.seekBar2) {
                activityMainBinding.textView.setText("두 번째 SeekBar : " + progress + "\n");
            }
            
            // 사용자가 설정했는지...
            if(fromUser == true) {
                activityMainBinding.textView.append("사용자에 의해 설정되었습니다.");
            } else {
                activityMainBinding.textView.append("코드를 통해 설정되었습니다.");
            }
        }

        // 사용자가 값 설정을 위해 터치 했을 때
        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {
            // 사용자 터치한 seekbar의 id를 가져온다.
            int seekBarId = seekBar.getId();

            if(seekBarId == R.id.seekBar) {
                activityMainBinding.textView2.setText("첫 번쩨 SeekBar 터치 시작");
            } else if(seekBarId == R.id.seekBar2) {
                activityMainBinding.textView2.setText("두 번쩨 SeekBar 터치 시작");
            }
        }

        // 사용자가 값 설정을 위해 터치 한 수 떼었을 때
        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {
            // 사용자가 터치 했다가 뗀 SeekBar의 ID를 추출한다.
            int seekBarId = seekBar.getId();

            if(seekBarId == R.id.seekBar) {
                activityMainBinding.textView2.setText("첫 번쩨 SeekBar 터치 종료");
            } else if(seekBarId == R.id.seekBar2) {
                activityMainBinding.textView2.setText("두 번쩨 SeekBar 터치 종료");
            }
        }
    }
}