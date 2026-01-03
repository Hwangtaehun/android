package com.example.listview;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.listview.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding activityMainBinding;

    // 리스트뷰 구성을 위해 사용할 문자열 데이터
    String [] data1 = {
            "문자열1", "문자열2", "문자열3", "문자열4", "문자열5",
            "문자열6", "문자열7", "문자열8", "문자열9", "문자열10",
            "문자열11", "문자열12", "문자열13", "문자열14", "문자열15",
            "문자열16", "문자열17", "문자열18", "문자열19", "문자열20",
            "문자열21", "문자열22", "문자열23", "문자열24", "문자열25"
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

        // 문자열 값을 셋팅할 수 있는 ArrayAdapter
        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(
                this, android.R.layout.simple_expandable_list_item_1, data1
        );

        // adapter를 ListView에 적용한다.
        activityMainBinding.listView.setAdapter(adapter1);

        // 항목을 터치했을 때
        ListItemClickListener1 listItemClickListener1 = new ListItemClickListener1();
        activityMainBinding.listView.setOnItemClickListener(listItemClickListener1);
    }

    // 항목을 더치했을 떄..
    class ListItemClickListener1 implements AdapterView.OnItemClickListener {
        // 첫 번째: 이벤트가 발생한 어뎁터 뷰
        // 두 번째: 터치한 항목 뷰
        // 세 번째: 터치한 항목의 순서 값(0부터 1씩 증가)
        // 네 번째: 터치한 항목의 id 값
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            // 사용자가 터치한 번째의 문자열을 가져온다.
            String str1 = data1[position];
            activityMainBinding.textView.setText(str1);

        }
    }
}