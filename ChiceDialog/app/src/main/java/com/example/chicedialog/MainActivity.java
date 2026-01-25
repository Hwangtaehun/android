package com.example.chicedialog;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.chicedialog.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding activityMainBinding;

    String [] data1 = {
            "항목1", "항목2", "항목3", "항목4", "항목5",
            "항목6", "항목7", "항목8", "항목9", "항목10"
    };

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
    }

    class Button1ClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            builder.setTitle("Single Choice List");

            // Single Choice List를 구성한다.
            // 첫 번째 : 항목을 구성하기 위한 문자열 배열
            // 두 번째 : 다이얼로그가 나타날대 선택되어 있을 항목의 순서 값(0부터 시작)
            // 세 번째 : 사용자가 항목을 터치 했을 때의 리스너
            Single1ClickListener listener200 = new Single1ClickListener();
            builder.setSingleChoiceItems(data1, 3, listener200);

            Dialog1ButtonClickListener listener100 = new Dialog1ButtonClickListener();
            builder.setNegativeButton("취소", null);
            builder.setPositiveButton("확인", listener100);

            builder.show();
        }
    }

    class Dialog1ButtonClickListener implements DialogInterface.OnClickListener {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            // 첫 번째 매개 변수로 들어오는 다이얼로그를 형변환한다.
            AlertDialog alertDialog = (AlertDialog) dialog;

            // 다이얼로그에서 리스트를 추출한다.
            ListView listView = alertDialog.getListView();
            // 선택한 항목의 위치 값을 가져온다.
            int position = listView.getCheckedItemPosition();

            activityMainBinding.textView.setText("선택한 항목 : " + data1[position]);
        }
    }

    // SingleChoice 다이얼로그에서 항목을 클릭했을 때
    class Single1ClickListener implements DialogInterface.OnClickListener {
        @Override
        // 사용자가 선택한 항목의 위치 값이 들어온다.
        public void onClick(DialogInterface dialog, int which) {
            Toast t1 = Toast.makeText(MainActivity.this, data1[which], Toast.LENGTH_SHORT);
            t1.show();
        }
    }

    class Button2ClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            builder.setTitle("Multi Choice Dialog");

            // 각 항목의 체크 상태 값
            boolean [] boolArray = {true, false, false, true, false, true, false, false, true, false};

            // Multi Choice 다이얼로그를 구성한다.
            // 첫 번째 : 리스트를 구성하기 위한 항목 배열
            // 두 번째 : 각 항목들의 체크 상태 값
            // 세 번째 : 항목을 터치했을 때의 리스너
            Multi1ClickListener listener400 = new Multi1ClickListener();
            builder.setMultiChoiceItems(data1, boolArray, listener400);

            Dialog2ClickListener listener300 = new Dialog2ClickListener();
            builder.setNegativeButton("취소", null);
            builder.setPositiveButton("확인", listener300);
            builder.show();
        }
    }

    class Dialog2ClickListener implements DialogInterface.OnClickListener {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            // 형변환
            AlertDialog alertDialog = (AlertDialog) dialog;

            activityMainBinding.textView.setText("");

            // 리스트 뷰를 추출한다.
            ListView listView = alertDialog.getListView();
            // 항목들의 선택 상태값을 관리하는 객체를 추출한다.
            SparseBooleanArray postitions = listView.getCheckedItemPositions();

            // 항목 상태값의 개수 만틈 반복한다.
            for (int i = 0; i < postitions.size(); i++) {
                // 현재 항목의 위치값을 가져온다.
                int index = postitions.keyAt(i);

                // 현재 항목의 상태값에 따라 분기를 처리한다.
                if(postitions.get(index) == true) {
                    activityMainBinding.textView.append(data1[index] + "\n");
                }
            }
        }
    }

    // Multi Choice에서 항목을 터치했을 때
    class Multi1ClickListener implements DialogInterface.OnMultiChoiceClickListener {
        @Override
        // 두 번째 : 사용자가 터치한 항목의 위치 값
        // 세 번째 : 항목의 체크 여부 (false : 체크 안되어 있는 상태, true : 체크 되어 있는 상태)
        public void onClick(DialogInterface dialog, int which, boolean isChecked) {
            String msg = "";

            if (isChecked == true) {
                msg = data1[which] + "가 체크되었습니다.";
            } else {
                msg = data1[which] + "가 체크 해제되었습니다.";
            }

            Toast t1 = Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT);
            t1.show();
        }
    }
}