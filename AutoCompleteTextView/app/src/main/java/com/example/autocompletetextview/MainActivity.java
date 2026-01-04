package com.example.autocompletetextview;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.autocompletetextview.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding activityMainBinding;

    // 자동 완성 목록으로 나올 문자열
    String [] data1 = {
            "abcd", "abca", "abcb", "abcc", "bbaa", "bbab", "bcab", "bdab"
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

        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(
                this, android.R.layout.simple_dropdown_item_1line, data1
        );

        activityMainBinding.autoCompleteTextView.setAdapter(adapter1);

        ButtonClickListener1 buttonClickListener1 = new ButtonClickListener1();
        activityMainBinding.button.setOnClickListener(buttonClickListener1);

        // 자동완성 목록에서 하나를 터치 했을 때..
        AutoClickListener1 autoClickListener1 = new AutoClickListener1();
        activityMainBinding.autoCompleteTextView.setOnItemClickListener(autoClickListener1);
    }

    class ButtonClickListener1 implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            // 입력한 문자열을 가져온다.
            String str1 = activityMainBinding.autoCompleteTextView.getText().toString();
            activityMainBinding.textView.setText(str1);
        }
    }

    // 자동완성 목록에서 항목을 터치할 때...
    class AutoClickListener1 implements AdapterView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            activityMainBinding.textView2.setText(data1[position] + "를 선택했습니다.");
        }
    }
}