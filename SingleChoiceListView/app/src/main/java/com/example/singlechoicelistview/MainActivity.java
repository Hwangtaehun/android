package com.example.singlechoicelistview;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.singlechoicelistview.databinding.ActivityMainBinding;

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

        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(
                this, android.R.layout.simple_list_item_single_choice, data1
        );

        activityMainBinding.list1.setAdapter(adapter1);

        // Single Choice 모드로 설정한다.
        activityMainBinding.list1.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        // 항목을 선택한다.
        activityMainBinding.list1.setItemChecked(2, true);

        ButtonClickLisener1 buttonClickLisener1 = new ButtonClickLisener1();
        activityMainBinding.button.setOnClickListener(buttonClickLisener1);
    }

    class ButtonClickLisener1 implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            // 현재 선택되어 있는 항목의 위치값을 가져온다.
            int position = activityMainBinding.list1.getCheckedItemPosition();
            activityMainBinding.textView.setText(data1[position]);
        }
    }
}