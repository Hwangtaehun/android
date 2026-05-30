package com.example.toolbaractionview;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import androidx.appcompat.widget.SearchView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.toolbaractionview.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding activityMainBinding;

    String [] dataList = {
            "aaaa", "bbbb", "cccc", "aabb", "aacc"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        activityMainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(activityMainBinding.getRoot());

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        setSupportActionBar(activityMainBinding.toolbar);

        ArrayAdapter<String> adapter = new ArrayAdapter<String> (
                this, android.R.layout.simple_list_item_1, dataList
        );

        activityMainBinding.list1.setAdapter(adapter);
        activityMainBinding.list1.setTextFilterEnabled(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_menu, menu);

        MenuItem item1 = menu.findItem(R.id.item1);
        SearchView searchView = (SearchView) item1.getActionView();
        searchView.setQueryHint("검색어 입력");

        QueryTestListener listener1 = new QueryTestListener(searchView);
        searchView.setOnQueryTextListener(listener1);

        return true;
    }

    class QueryTestListener implements SearchView.OnQueryTextListener {
        SearchView searchView;

        public QueryTestListener(SearchView searchView) {
            this.searchView = searchView;
        }

        @Override
        public boolean onQueryTextChange(String newText) {
            activityMainBinding.textView.setText("입력중입니다.");
            activityMainBinding.textView2.setText(newText);

            activityMainBinding.list1.setFilterText(newText);

            if(newText.length() == 0) {
                activityMainBinding.list1.clearTextFilter();
            }

            return false;
        }

        @Override
        public boolean onQueryTextSubmit(String query) {
            activityMainBinding.textView.setText("입력완료");
            activityMainBinding.textView2.setText("입력 완료 : " + query);
            searchView.clearFocus();

            return true;
        }
    }
}