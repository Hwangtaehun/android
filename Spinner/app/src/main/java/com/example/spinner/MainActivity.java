package com.example.spinner;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.spinner.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding activityMainBinding;

    String [] data1 = {
            "항목1", "항목2", "항목3", "항목4", "항목5",
            "항목6", "항목7", "항목8", "항목9", "항목10",
            "항목11", "항목12", "항목13", "항목14", "항목15"
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

        // 어뎁터를 생성한다. (접혀져 있을 때의 모양을 설정)
        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, data1);
        // 펼쳐졌을 때의 모양을 설정한다.
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        activityMainBinding.spinner.setAdapter(adapter1);

        // 항목을 선택한다.
        activityMainBinding.spinner.setSelection(2);

        ButtonClickListener1 buttonClickListener1 = new ButtonClickListener1();
        activityMainBinding.button.setOnClickListener(buttonClickListener1);

        // 스피너의 항목을 선택했을 때
        SpinnerItemSelectedListener1 spinnerItemSelectedListener1 = new SpinnerItemSelectedListener1();
        activityMainBinding.spinner.setOnItemSelectedListener(spinnerItemSelectedListener1);
    }

    class ButtonClickListener1 implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            // 현재 선택되어있는 항목의 인덱스(위치값)을 가져온다.
            int position = activityMainBinding.spinner.getSelectedItemPosition();

            activityMainBinding.textView.setText("선택한 항목 : " + data1[position]);
        }
    }

    // 스피너에서 항목을 선택하였을 때.
    class SpinnerItemSelectedListener1 implements AdapterView.OnItemSelectedListener {
        // 항목을 선택했을 때 호출
        // 세번째: 선택한 항목의 순서(0부터 시작)값
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            // 이벤트가 발생한 스피너의 id를 가져온다.
            int m_id = parent.getId();

            if(m_id == R.id.spinner){
                activityMainBinding.textView2.setText(data1[position] + "항목을 선택하였습니다");
            }
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    }
}