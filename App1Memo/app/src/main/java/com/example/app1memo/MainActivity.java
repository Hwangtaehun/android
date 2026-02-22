package com.example.app1memo;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.splashscreen.SplashScreen;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app1memo.databinding.ActivityMainBinding;
import com.example.app1memo.databinding.MainRecylcerRowBinding;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding activityMainBinding;

    // 제목을 담을 ArrayList
    ArrayList<String> subjectList = new ArrayList<String>();
    // 작성 날짜를 담을 ArrayList
    ArrayList<String> dateList = new ArrayList<String>();
    // 메모의 번호를 담을 ArrayList
    ArrayList<Integer> idxList = new ArrayList<Integer>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        SplashScreen.installSplashScreen(this);

        super.onCreate(savedInstanceState);

        activityMainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        EdgeToEdge.enable(this);
        setContentView(activityMainBinding.getRoot());
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

//        DBHelper dbHelper = new DBHelper(this);
//        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
//        sqLiteDatabase.close();

        // 툴바를 설정한다.
        setSupportActionBar(activityMainBinding.mainToolbar);
        setTitle("메모앱");

        // RecyclerView 설정
        MainRecyclerAdapter mainRecyclerAdapter = new MainRecyclerAdapter();
        activityMainBinding.mainRecycler.setAdapter(mainRecyclerAdapter);

        activityMainBinding.mainRecycler.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    protected void onResume() {
        super.onResume();

        // ArrayList를 비워준다.
        subjectList.clear();
        dateList.clear();
        idxList.clear();

        // 데이터 베이스 오픈
        DBHelper helper = new DBHelper(this);
        SQLiteDatabase sqLiteDatabase = helper.getWritableDatabase();

        // 쿼리문
        String sql = "select memo_subject, memo_date, memo_idx from MemoTable "
                   + "order by memo_idx desc";

        // 데이터를 가져온다.
        Cursor c1 = sqLiteDatabase.rawQuery(sql, null);

        while (c1.moveToNext()) {
            // 컬럼 index를 가져온다.
            int idx1 = c1.getColumnIndex("memo_subject");
            int idx2 = c1.getColumnIndex("memo_date");
            int idx3 = c1.getColumnIndex("memo_idx");

            // 데이터를 가져온다.
            String memoSubject = c1.getString(idx1);
            String memoDate = c1.getString(idx2);
            int memoIdx = c1.getInt(idx3);

            //Log.d("memo_app", memoSubject);
            //Log.d("memo_app", memoDate);
            //Log.d("memo_app", "-------------------------");

            // 데이터를 담는다.
            subjectList.add(memoSubject);
            dateList.add(memoDate);
            idxList.add(memoIdx);
        }

        sqLiteDatabase.close();

        // RecyclerView 갱신
        MainRecyclerAdapter mainRecyclerAdapter = (MainRecyclerAdapter) activityMainBinding.mainRecycler.getAdapter();
        mainRecyclerAdapter.notifyDataSetChanged();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        // 선택한 메뉴의 아이디를 추출한다.
        int itemId = item.getItemId();

        // 분기한다.
        if(itemId == R.id.main_menu_add){
            Intent memoAddIntent = new Intent(this, MemoAddActivity.class);
            startActivity(memoAddIntent);
        }

        return super.onOptionsItemSelected(item);
    }

    // RecyclerView의 어뎁터
    class MainRecyclerAdapter extends RecyclerView.Adapter<MainRecyclerAdapter.ViewHolderClass>{

        // ViewHolder를 생성해 반환한다.
        @NonNull
        @Override
        public ViewHolderClass onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            MainRecylcerRowBinding mainRecylcerRowBinding = MainRecylcerRowBinding.inflate(getLayoutInflater());
            ViewHolderClass viewHolderClass = new ViewHolderClass(mainRecylcerRowBinding);

            // 생서되는 항목 View의 가로 세로 길이을 설정해준다.
            RecyclerView.LayoutParams layoutParams = new RecyclerView.LayoutParams(
                    RecyclerView.LayoutParams.MATCH_PARENT,
                    RecyclerView.LayoutParams.WRAP_CONTENT
            );
            mainRecylcerRowBinding.getRoot().setLayoutParams(layoutParams);

            // 클릭 리스너 설정
            mainRecylcerRowBinding.getRoot().setOnClickListener(viewHolderClass);

            return viewHolderClass;
        }

        // 항목 하나를 구성한다.
        @Override
        public void onBindViewHolder(@NonNull ViewHolderClass holder, int position) {
            holder.mainRecyclerSubject.setText(subjectList.get(position));
            holder.mainRecyclerDate.setText(dateList.get(position));
        }


        // 항목의 개술를 반환하는 메서드
        @Override
        public int getItemCount() {
            return subjectList.size();
        }

        // HolderClass
        class ViewHolderClass extends RecyclerView.ViewHolder implements View.OnClickListener {
            TextView mainRecyclerSubject;
            TextView mainRecyclerDate;

            public ViewHolderClass(MainRecylcerRowBinding mainRecylcerRowBinding) {
                super(mainRecylcerRowBinding.getRoot());

                mainRecyclerSubject = mainRecylcerRowBinding.mainRecyclerSubject;
                mainRecyclerDate = mainRecylcerRowBinding.mainRecyclerDate;
            }

            @Override
            public void onClick(View v) {
                // MemoReadActivity를 실행해 준다.
                Intent memoReadIntent = new Intent(MainActivity.this, MemoReadActivity.class);

                // 사용자가 터치한 항목의 순서값을 가져온다.
                int adapterPosition = getBindingAdapterPosition();
                // 항목 번째의 메모 인덱스 번호를 가져온다.
                int memoIdx = idxList.get(adapterPosition);
                // Intent에 담는다.
                memoReadIntent.putExtra("memo_idx", memoIdx);

                startActivity(memoReadIntent);
            }
        }
    }
}