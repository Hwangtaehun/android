package com.example.app1memo;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.inputmethod.InputMethodManager;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.app1memo.databinding.ActivityMemoAddBinding;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class MemoAddActivity extends AppCompatActivity {
   ActivityMemoAddBinding activityMemoAddBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMemoAddBinding = ActivityMemoAddBinding.inflate(getLayoutInflater());

        EdgeToEdge.enable(this);
        setContentView(activityMemoAddBinding.getRoot());
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        
        // toolbar 설정
        setSupportActionBar(activityMemoAddBinding.memoAddToolbar);
        setTitle("메모 추가");

        // 이전 버튼 설정
        ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);

        // 제목에 포커스를 준다.
        //activityMemoAddBinding.addMenoSubject.requestFocus();

        // 키보드를 올려준다.
        //InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        //inputMethodManager.showSoftInput(activityMemoAddBinding.addMenoSubject, InputMethodManager.SHOW_FORCED);

        // 키보드 쓰레드 기동
        KeyboardThread keyboardThread = new KeyboardThread();
        keyboardThread.start();
    }

    // 키보드 자동으로 보여주기 위한 쓰레드
    class KeyboardThread extends Thread {
        @Override
        public void run() {
            super.run();

            SystemClock.sleep(500);

            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    // 제목에 포커스를 준다.
                    activityMemoAddBinding.addMemoSubject.requestFocus();

                    // 키보드를 올려준다.
                    InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                    inputMethodManager.showSoftInput(activityMemoAddBinding.addMemoSubject, InputMethodManager.SHOW_FORCED);
                }
            });
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.add_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();

        // Home 버튼
        if(itemId == android.R.id.home) {
            // 현재 액티비티를 종료한다.
            finish();
        } else if (itemId == R.id.add_menu_save) { // 저장 버튼
            // 사용자가 입력한 내용을 가지고 온다.
            String memoSubject = activityMemoAddBinding.addMemoSubject.getText().toString();
            String memoText = activityMemoAddBinding.addMemoText.getText().toString();

            // 쿼리문
            String sql = "insert into MemoTable "
                    + "(memo_subject, memo_text, memo_date) "
                    + "values (?, ?, ?)";

            // 현재 시간을 구한다.
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
            String now = simpleDateFormat.format(new Date());

            // 데이터베이스 오픈
            DBHelper dbHelper = new DBHelper(this);

            // 저장할 값 배열
            Object [] arg1 = {memoSubject, memoText, now};

            // 저장한다.
            SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
            sqLiteDatabase.execSQL(sql, arg1);
            sqLiteDatabase.close();

            // 현재 액티비티를 종료한다.
            finish();
        }

        return super.onOptionsItemSelected(item);
    }
}