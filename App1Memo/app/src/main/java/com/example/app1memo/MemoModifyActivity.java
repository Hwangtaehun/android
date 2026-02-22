package com.example.app1memo;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.MenuItem;
import android.view.inputmethod.InputMethodManager;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.app1memo.databinding.ActivityMemoModifyBinding;

public class MemoModifyActivity extends AppCompatActivity {
    ActivityMemoModifyBinding  activityMemoModifyBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMemoModifyBinding = ActivityMemoModifyBinding.inflate(getLayoutInflater());

        EdgeToEdge.enable(this);
        setContentView(activityMemoModifyBinding.getRoot());
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        setSupportActionBar(activityMemoModifyBinding.memoModifyToolbar);
        setTitle("메모 수정");

        ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);

        // 메모 번호를 가져온다.
        Intent intent = getIntent();
        Integer memoIdx = intent.getIntExtra("memo_idx", 0);

        // 데이터 베이스 오픈
        DBHelper dbHelper = new DBHelper(this);
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();

        // 쿼리문
        String sql = "select memo_subject, memo_text "
                   + "from MemoTable "
                   + "where memo_idx = ?";

        // ?에 바인딩 될 값
        String [] args = {memoIdx.toString()};

        // 데이터를 가져온다.
        Cursor c1 = sqLiteDatabase.rawQuery(sql, args);
        c1.moveToNext();

        int idx1 = c1.getColumnIndex("memo_subject");
        int idx2 = c1.getColumnIndex("memo_text");

        String memoSubject = c1.getString(idx1);
        String memoText = c1.getString(idx2);

        sqLiteDatabase.close();

        activityMemoModifyBinding.memoModifySubject.setText(memoSubject);
        activityMemoModifyBinding.memoModifyText.setText(memoText);

        KeyboardThread keyboardThread = new KeyboardThread();
        keyboardThread.start();
    }

    class KeyboardThread extends Thread {
        @Override
        public void run() {
            super.run();

            SystemClock.sleep(500);

            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    activityMemoModifyBinding.memoModifySubject.requestFocus();
                    InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                    inputMethodManager.showSoftInput(activityMemoModifyBinding.memoModifySubject, InputMethodManager.SHOW_IMPLICIT);
                }
            });
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();

        if(itemId == android.R.id.home) {
            finish();
        }

        return super.onOptionsItemSelected(item);
    }
}