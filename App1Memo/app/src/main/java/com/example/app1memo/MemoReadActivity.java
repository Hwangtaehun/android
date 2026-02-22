package com.example.app1memo;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.app1memo.databinding.ActivityMemoReadBinding;

public class MemoReadActivity extends AppCompatActivity {
    ActivityMemoReadBinding activityMemoReadBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMemoReadBinding = ActivityMemoReadBinding.inflate(getLayoutInflater());

        EdgeToEdge.enable(this);
        setContentView(activityMemoReadBinding.getRoot());
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        setSupportActionBar(activityMemoReadBinding.memoReadToolbar);
        setTitle("메모 읽기");

        ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
    }

    @Override
    protected void onResume() {
        super.onResume();

        // Intent를 추출한다.
        Intent intent = getIntent();
        // 메모 번호를 추출한다.
        Integer memoIdx = intent.getIntExtra("memo_idx", 0);

        // 데이터 베이스 오픈
        DBHelper dbHelper = new DBHelper(this);
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();

        // 쿼리문
        String sql = "select memo_subject, memo_date, memo_text "
                   + "from MemoTable "
                   + "where memo_idx = ?";

        // ? 에 바인딩 될 값
        String [] args = {memoIdx.toString()};

        // 쿼리 실행
        Cursor c1 = sqLiteDatabase.rawQuery(sql, args);
        c1.moveToNext();

        // 글 데이터를 추출한다.
        int idx1 = c1.getColumnIndex("memo_subject");
        int idx2 = c1.getColumnIndex("memo_date");
        int idx3 = c1.getColumnIndex("memo_text");

        String memoSubject = c1.getString(idx1);
        String memoDate = c1.getString(idx2);
        String memoText = c1.getString(idx3);

        sqLiteDatabase.close();

        activityMemoReadBinding.memoReadSubject.setText(memoSubject);
        activityMemoReadBinding.memoReadDate.setText(memoDate);
        activityMemoReadBinding.memoReadText.setText(memoText);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.read_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();

        if(itemId == android.R.id.home) {
            finish();
        } else if(itemId == R.id.read_menu_modify) { // 글 수정
            Intent memoModifyIntent = new Intent(this, MemoModifyActivity.class);

            // 메모 번호를 추출하여 Intent에 담아준다.
            Intent intent1 = getIntent();
            int memoIdx = intent1.getIntExtra("memo_idx", 0);
            memoModifyIntent.putExtra("memo_idx", memoIdx);

            startActivity(memoModifyIntent);
        } else if(itemId == R.id.read_menu_delete) { // 글 삭제
            // 다이얼로그를 띄운다.
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("메모 삭제");
            builder.setMessage("메모를 삭제 하겠습니까?");

            DeleteDialogListener deleteDialogListener = new DeleteDialogListener();
            builder.setPositiveButton("삭제", deleteDialogListener);
            builder.setNegativeButton("취소", null);

            builder.show();
        }

        return super.onOptionsItemSelected(item);
    }

    // 다이얼로그의 리스너
    class DeleteDialogListener implements DialogInterface.OnClickListener {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            DBHelper dbHelper = new DBHelper(MemoReadActivity.this);
            SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();

            String sql = "delete from MemoTable "
                       + "where memo_idx = ?";

            Intent intent1 = getIntent();
            Integer memoIdx = intent1.getIntExtra("memo_idx", 0);

            String [] args = {memoIdx.toString()};

            sqLiteDatabase.execSQL(sql, args);
            sqLiteDatabase.close();

            finish();
        }
    }
}