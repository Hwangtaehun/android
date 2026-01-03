package com.example.customlistview;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.customlistview.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding activityMainBinding;

    String [] data1 = {
            "문자열1", "문자열2", "문자열3", "문자열4", "문자열5"
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

        // 어뎁터
        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(
                //this, android.R.layout.simple_list_item_1, data
                this, R.layout.row, R.id.textView2, data1
        );

        activityMainBinding.listView.setAdapter(adapter1);

        ListItemClickListener1 listItemClickListener1 = new ListItemClickListener1();
        activityMainBinding.listView.setOnItemClickListener(listItemClickListener1);
    }

    class ListItemClickListener1 implements AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            String str1 = data1[position];
            activityMainBinding.textView.setText(str1);
        }
    }
}