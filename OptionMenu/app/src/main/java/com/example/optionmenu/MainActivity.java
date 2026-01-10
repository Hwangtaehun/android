package com.example.optionmenu;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.SubMenu;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.optionmenu.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding activityMainBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        activityMainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setSupportActionBar(activityMainBinding.toolbar);
        setContentView(activityMainBinding.getRoot());
    }

    // option menu를 구성하는 메서드
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        boolean used = true;

        if(used) {
            // xml 파일로 부터 메뉴를 생성하는 객체
            // MenuInflater menuInflater = getMenuInflater();
            // menuInflater.inflate(R.menu.main_menu, menu);

            // 코드를 통해 메뉴를 구성한다.
            menu.add(Menu.NONE, Menu.FIRST, Menu.NONE, "코드 메뉴 1");
            SubMenu subMenu = menu.addSubMenu("코드 메뉴 2");
            //menu.add(Menu.NONE, Menu.FIRST + 1, Menu.NONE, "코드 메뉴 2");
            subMenu.add(Menu.NONE, Menu.FIRST + 10, Menu.NONE, "하위 메뉴 1");
            subMenu.add(Menu.NONE, Menu.FIRST + 20, Menu.NONE, "하위 메뉴 2");
            menu.add(Menu.NONE, Menu.FIRST + 2, Menu.NONE, "코드 메뉴 3");
        }

        return used;
    }

    // 사용자가 메뉴를 선택했을 때 호출되는 메서드
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        // 사용자가 터치한 메뉴의 객체에서 id를 추출한다.
        int id = item.getItemId();

//        if (id == R.id.menu_item1) {
//            activityMainBinding.textView.setText("메뉴 항목1을 선택했습니다.");
//        } else if (id == R.id.menu_item3) {
//            activityMainBinding.textView.setText("메뉴 항목3을 선택했습니다.");
//        } else if (id == R.id.sub_item1) {
//            activityMainBinding.textView.setText("하위 항목1을 선택했습니다.");
//        } else if (id == R.id.sub_item2) {
//            activityMainBinding.textView.setText("하위 항목2를 선택했습니다.");
//        }

        if (id == Menu.FIRST) {
            activityMainBinding.textView.setText("코드 메뉴1을 선택했습니다.");
        } else if (id == Menu.FIRST + 2) {
            activityMainBinding.textView.setText("코드 메뉴3을 선택했습니다.");
        } else if (id == Menu.FIRST + 10) {
            activityMainBinding.textView.setText("하위 항목1을 선택했습니다.");
        } else if (id == Menu.FIRST + 20) {
            activityMainBinding.textView.setText("하위 항목2을 선택했습니다.");
        }

        return super.onOptionsItemSelected(item);
    }
}