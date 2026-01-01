package com.example.searchview;

import android.os.Bundle;
import android.view.View;
import android.widget.SearchView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.searchview.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding activityMainBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        activityMainBinding = ActivityMainBinding.inflate(getLayoutInflater());

        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(activityMainBinding.getRoot());
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        ButtonClickListener1 buttonClickListener1 = new ButtonClickListener1();
        activityMainBinding.button.setOnClickListener(buttonClickListener1);

        SearchViewTextQueryListener1 searchViewTextQueryListener1 = new SearchViewTextQueryListener1();
        activityMainBinding.searchView.setOnQueryTextListener(searchViewTextQueryListener1);
    }

    class ButtonClickListener1 implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            // 사용자가 입력한 검색어를 가져온다.
            String str1 = activityMainBinding.searchView.getQuery().toString();
            activityMainBinding.textView.setText(str1);
        }
    }

    // 검색을 입력했거나 엔터 버튼을 눌렀을 때
    class SearchViewTextQueryListener1 implements SearchView.OnQueryTextListener {
        // 검색어 입력 시
        @Override
        public boolean onQueryTextChange(String newText) {
            activityMainBinding.textView2.setText(newText);
            return false;
        }

        // 엔터 버튼을 눌렀을 때
        @Override
        public boolean onQueryTextSubmit(String query) {
            activityMainBinding.textView.setText(query);
            // false : 키보드가 내려간다. true : 키보드가 유지된다.
            return false;
        }
    }
}