package com.example.actionview;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.actionview.databinding.ActivityMainBinding;

import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding activityMainBinding;

    String [] data1 = {"aaaa", "bbbb", "cccc", "aabb", "ccdd"};

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

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_list_item_1, data1);
        activityMainBinding.list1.setAdapter(adapter);

        ListItemClickListener listener1 = new ListItemClickListener();
        activityMainBinding.list1.setOnItemClickListener(listener1);

        // 리스트뷰가 검색 기능을 지원하기 위해 셋팅한다.
        activityMainBinding.list1.setTextFilterEnabled(true);
    }

    class ListItemClickListener implements AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            // 리스트 뷰의 어뎁터를 추출한다.
            Adapter adapter = parent.getAdapter();
            // position 번째 항목의 문자열을 가져온다.
            String str1 = (String) adapter.getItem(position);
            // 해당 문자열이 리스트 뷰를 구성하기 위해 사용한 배열의 몇 번째인지 확인한다.
            List<String> a2 = Arrays.asList(data1);
            int idx = a2.indexOf(str1);

            activityMainBinding.textView3.setText(data1[idx]);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_menu, menu);

        // SearchView를 가지고 있는 메뉴 아이템을 추출한다.
        MenuItem item1 = menu.findItem(R.id.item1);
        // SearchView를 가지고 온다.
        SearchView searchView = (SearchView) item1.getActionView();

        // 안내 문구를 설정한다.
        searchView.setQueryHint("검색어 입력");

        // 메뉴 아이템에 배치된 뷰가 접히거나 펼쳐질 때 반응하는 리스너
        MenuActionListener1 listener1 = new MenuActionListener1();
        item1.setOnActionExpandListener(listener1);

        // SearchView의 리스너
        MenuQueryListener listener2 = new MenuQueryListener(searchView);
        searchView.setOnQueryTextListener(listener2);

        return true;
    }

    // 메뉴 아이템에 배치된 뷰가 접히거나 펼처질 때 반응하는 리스너
    class MenuActionListener1 implements MenuItem.OnActionExpandListener {
        // 접혔을 때 호출되는 메서드
        @Override
        public boolean onMenuItemActionCollapse(@NonNull MenuItem item) {
            activityMainBinding.textView.setText("접혀졌습니다.");
            return true;
        }

        // 펄쳐졌을 때 호출되는 메서드
        @Override
        public boolean onMenuItemActionExpand(@NonNull MenuItem item) {
            activityMainBinding.textView.setText("펼쳐졌습니다.");
            return true;
        }
    }

    // SearchView에 입력을 했을 때
    class MenuQueryListener implements SearchView.OnQueryTextListener {
        SearchView searchView;

        public MenuQueryListener(SearchView searchView) {
            this.searchView = searchView;
        }

        // 문자열 입력이 완료 되었을 때
        @Override
        public boolean onQueryTextSubmit(String query) {
            activityMainBinding.textView.setText("문자열 입력 완료");
            activityMainBinding.textView2.setText("입력완료 : " + query);
            searchView.clearFocus();
            return true;
        }

        // 문자열 입력 중일 때
        @Override
        public boolean onQueryTextChange(String newText) {
            activityMainBinding.textView.setText("문자열 입력중");
            activityMainBinding.textView2.setText("입력중 : " + newText);

            // SearchView에 입력한 내용을 ListViewFilter로 설정한다.
            // activityMainBinding.list1.setFilterText(newText);

            // 만약 설정한 문자열의 길이가 0이라면 필터 문자열 제거한다.
            // if(newText.length() == 0) {
                // activityMainBinding.list1.clearTextFilter();
            // }

            if (activityMainBinding.list1.getAdapter() instanceof ArrayAdapter) {
                ArrayAdapter<String> adapter = (ArrayAdapter<String>) activityMainBinding.list1.getAdapter();

                // 어댑터의 필터를 통해 검색어를 전달합니다.
                adapter.getFilter().filter(newText);
            }

            return false;
        }
    }
}