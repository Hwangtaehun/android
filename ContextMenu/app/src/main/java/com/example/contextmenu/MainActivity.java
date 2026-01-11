package com.example.contextmenu;

import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.contextmenu.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding activityMainBinding;

    String [] data1 = {
            "항목1", "항목2", "항목3", "항목4", "항목5"
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

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_list_item_1, data1
        );
        activityMainBinding.list1.setAdapter(adapter);

        ListItemClickListener1 listener1 = new ListItemClickListener1();
        activityMainBinding.list1.setOnItemClickListener(listener1);

        // 뷰에 컨텍스트 메뉴를 등록한다.
        registerForContextMenu(activityMainBinding.textView);
        registerForContextMenu(activityMainBinding.list1);
    }

    class ListItemClickListener1 implements AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        activityMainBinding.textView.setText("리스트뷰의 항목 클릭 : " + data1[position]);
        }
    }

    // 컨텍스트 메뉴를 구성하는 메뉴
    // 두 번째: 메뉴의 띄우기 위해 사용자가 길게 누른 뷰 객체가 전달된다.
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        // 사용자가 길게 누른 뷰의 id를 추출한다.
        int id = v.getId();
        // xml을 통해 메뉴를 구상하기 위한 객체
        MenuInflater menuInflater = getMenuInflater();

        // 뷰의 아이디가 텍스뷰라면..
        if (id == R.id.textView) {
            // 제목
            //menu.setHeaderIcon(R.mipmap.ic_launcher);
            menu.setHeaderTitle("텍스트 뷰의 메뉴");
            menuInflater.inflate(R.menu.menu1, menu);
        } else if(id == R.id.list1 ) // 리스트 뷰 라면
        {
            // 사용자가 길게 누른 항목의 순서 값을 파악하기 위해
            AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
            menu.setHeaderTitle("리스트뷰의 메뉴 : " + data1[info.position]);
            menuInflater.inflate(R.menu.menu2, menu);
        }
    }

    // 메뉴 항목을 터치했을 때 호출되는 메서드
    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        // 사용자가 선택한 메뉴 항목의 아이디를 추출한다.
        int itemId = item.getItemId();

        if (itemId == R.id.text_item1) {
            activityMainBinding.textView.setText("텍스트뷰의 메뉴1을 선택했습니다.");
        } else if(itemId == R.id.text_item2) {
            activityMainBinding.textView.setText("텍스트뷰의 메뉴2를 선택했습니다.");
        } else if(itemId == R.id.list_item1) {
            AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
            activityMainBinding.textView.setText("리스트뷰의 메뉴1를 눌렀습니다 : " + data1[info.position]);
        } else if(itemId == R.id.list_item2) {
            AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
            activityMainBinding.textView.setText("리스트뷰의 메뉴2를 눌렀습니다 : " + data1[info.position]);
        }

        return super.onContextItemSelected(item);
    }
}