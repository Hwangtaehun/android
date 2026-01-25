package com.example.listdialog;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.SimpleAdapter;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.listdialog.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding activityMainBinding;

    // 리스트 구성을 위한 데이터
    String [] data1 = {
            "항목1", "항목2", "항목3", "항목4", "항목5", "항목6", "항목7", "항목8"
    };

    String [] data2 = {
            "토고", "프랑스", "스위스", "스페인", "일본", "독일", "브라질", "대한민국"
    };

    int [] data3 = {
            R.drawable.imgflag1, R.drawable.imgflag2, R.drawable.imgflag3, R.drawable.imgflag4,
            R.drawable.imgflag5, R.drawable.imgflag6, R.drawable.imgflag7, R.drawable.imgflag8
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
            builder.setTitle("리스트 다이얼로그");
            builder.setNegativeButton("취소", null);

            // 리스트 다이얼로그 구성을 위해 세팅한다.
            Dialog1ClickListener listener100 = new Dialog1ClickListener();
            builder.setItems(data1, listener100);
            builder.show();
        }
    }

    // 리스트 다이얼로그에서 항목을 선택했을 때의 리스너
    class Dialog1ClickListener implements DialogInterface.OnClickListener {
        @Override
        // 두 번째 : 선택한 항목의 위치값(0부터 시작)
        public void onClick(DialogInterface dialog, int which) {
            activityMainBinding.textView.setText("기본 리스트 다이얼로그 : " + data1[which]);
        }
    }

    class Button2ClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            // 항목을 구성하기 위한 앱을 가지고 있는 arraylist
            ArrayList<HashMap<String, Object>> list1 = new ArrayList<HashMap<String, Object>>();

            // 항목의 수 만큼
            for(int i = 0; i < data2.length; i++) {
                // 항목하나를 구성하기 위한 데이터를 담을 맵
                HashMap<String, Object> map = new HashMap<String, Object>();
                map.put("data2", data2[i]);
                map.put("data3", data3[i]);

                list1.add(map);
            }

            // 어뎁터 생성
            String [] keys = {"data2", "data3"};
            int [] ids = { R.id.custom_text, R.id.custom_image};
            SimpleAdapter adapter = new SimpleAdapter(MainActivity.this, list1,
                    R.layout.custom_list, keys, ids);

            // 다이얼로그를 구성한다.
            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            builder.setTitle("커스텀 리스트 다이얼로그");
            builder.setIcon(R.mipmap.ic_launcher);

            Dialog2ClickListener listener200 = new Dialog2ClickListener();
            // 어뎁터를 설정한다.
            builder.setAdapter(adapter, listener200);

            builder.setNegativeButton("취소", null);
            builder.show();
        }
    }

    class Dialog2ClickListener implements DialogInterface.OnClickListener {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            activityMainBinding.textView.setText("커스텀 리스틑 다이얼로그 : " + data2[which]);
        }
    }
}