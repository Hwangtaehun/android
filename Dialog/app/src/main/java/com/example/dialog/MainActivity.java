package com.example.dialog;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TimePicker;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.dialog.databinding.ActivityMainBinding;
import com.example.dialog.databinding.CustomDialogBinding;

import java.util.Calendar;

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

        Button1ClickListener listener1 = new Button1ClickListener();
        activityMainBinding.button.setOnClickListener(listener1);

        Button2ClickListener listener2 = new Button2ClickListener();
        activityMainBinding.button2.setOnClickListener(listener2);

        Button3ClickListener listener3 = new Button3ClickListener();
        activityMainBinding.button3.setOnClickListener(listener3);

        Button4ClickListener listener4 = new Button4ClickListener();
        activityMainBinding.button4.setOnClickListener(listener4);
    }

    class Button1ClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            // 다이얼로그 생성을 위한 객체를 생성한다.
            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);

            // 타이틀
            builder.setTitle("기본 다이얼로그");
            // 메시지
            builder.setMessage("기본 다이얼로그 입니다.");
            // 아이콘
            builder.setIcon(R.mipmap.ic_launcher);

            // 버튼을 배치
            Dialog1ClickListener listener100 = new Dialog1ClickListener();
            builder.setPositiveButton("Positive", listener100);
            builder.setNeutralButton("Neutural", listener100);
            builder.setNegativeButton("Negative", listener100);

            // 다이얼로그를 띄운다.
            builder.show();
        }
    }

    // 기본 다이얼로그의 버튼 리스너
    class Dialog1ClickListener implements DialogInterface.OnClickListener {
        // 버튼을 배치한 다이얼로그
        // 두 번째 매개 변수 : 누른 버튼을 구분하는 값
        @Override
        public void onClick(DialogInterface dialog, int which) {
            // 버튼 분기에 따라 구분한다.
            if(which == DialogInterface.BUTTON_POSITIVE) {
                activityMainBinding.textView.setText("Positivie 버튼을 눌렀습니다.");
            } else if(which == DialogInterface.BUTTON_NEUTRAL) {
                activityMainBinding.textView.setText("Neutral 버튼을 눌렀습니다.");
            } else if(which == DialogInterface.BUTTON_NEGATIVE) {
                activityMainBinding.textView.setText("Negative 버튼을 눌렀습니다.");
            }
        }
    }

    class Button2ClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            builder.setTitle("커스텀 다이얼로그");
            builder.setIcon(R.mipmap.ic_launcher);

            // viewBinding
            CustomDialogBinding customDialogBinding = CustomDialogBinding.inflate(getLayoutInflater());
            // 다이얼로그에 View를 셋팅한다.
            builder.setView(customDialogBinding.getRoot());

            Dialog2ClickListener listener200 = new Dialog2ClickListener(customDialogBinding);
            builder.setPositiveButton("확인", listener200);
            builder.setNegativeButton("취소", null);

            builder.show();
        }
    }

    class Dialog2ClickListener implements DialogInterface.OnClickListener {
        // 커스터마이징할 때 사용한 View를 관리하는 ViewBinding
        CustomDialogBinding customDialogBinding;

        // 생성자를 통해 viewBinding 객체를 받는다.
        public Dialog2ClickListener(CustomDialogBinding customDialogBinding){
            this.customDialogBinding = customDialogBinding;
        }

        @Override
        public void onClick(DialogInterface dialog, int which) {
            // 사용자가 입력한 문자열을 가져온다.
            String str1 = customDialogBinding.edit1.getText().toString();
            String str2 = customDialogBinding.edit2.getText().toString();

            activityMainBinding.textView.setText(str1 + "\n");
            activityMainBinding.textView.append(str2);
        }
    }

    class Button3ClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            // 현재 날짜를 구하기 위해 Calendar 객체를 추출한다.
            Calendar calendar = Calendar.getInstance();

            // 현재 날짜를 가져온다.
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH);
            int day = calendar.get(Calendar.DAY_OF_MONTH);

            // DatePickerDialog를 생성한다.
            // 두 번째 : 날짜를 선택했을 때의 리스너
            // 세 번째 : 년
            // 네 번째 : 월
            // 다섯 번재 : 일
            Dialog3DataSetListener listener300 = new Dialog3DataSetListener();
            DatePickerDialog pickerDialog = new DatePickerDialog(
                    MainActivity.this, listener300, year, month, day
            );

            pickerDialog.show();
        }
    }

    // DatePickerDialog에서 날짜를 선택했을때의 리스너
    class Dialog3DataSetListener implements DatePickerDialog.OnDateSetListener {
        // 두 번째: 선택한 년
        // 세 번째: 선택한 월(0월 부터 시작)
        // 네 번째: 선택한 일
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            // 가져온 날짜를 출력한다.
        activityMainBinding.textView.setText(year + " 년 " + month + 1 + "월 " + dayOfMonth + "일");
        }
    }

    class Button4ClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            // 현재 시간을 구하기 위한 Calendar
            Calendar calendar = Calendar.getInstance();

            // 현재 시간을 구한다.
            int hour = calendar.get(Calendar.HOUR);
            int minute = calendar.get(Calendar.MINUTE);

            // TimePickerDialog를 띄운다.
            Dialog4TimeSetListener listener400 = new Dialog4TimeSetListener();
            // 두 번째 : 시간을 선택했을 때 리스너
            // 세 번째 : 시간
            // 네 번째 : 분
            // 다섯 번째 : 24시간제 여부, false - 12시간제, true - 24시간제
            TimePickerDialog pickerDialog = new TimePickerDialog(MainActivity.this, listener400, hour, minute, true);

            pickerDialog.show();
        }
    }

    // TimePickerDialog에서 시간을 선택했을 때의 리스너
    class Dialog4TimeSetListener implements TimePickerDialog.OnTimeSetListener {
        // 두 번째 : 선택한 시간
        // 세 번째 : 선택한 분
        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            activityMainBinding.textView.setText(hourOfDay + "시" + minute + "분");
        }
    }
}