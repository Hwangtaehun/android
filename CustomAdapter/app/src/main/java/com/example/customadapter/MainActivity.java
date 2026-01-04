package com.example.customadapter;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.customadapter.databinding.ActivityMainBinding;
import com.example.customadapter.databinding.RowBinding;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding activityMainBinding;

    String [] data1 = {
            "데이터1", "데이터2", "데이터3", "데이터4", "데이터5"
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

        // 어뎁터를 적용한다.
        CustomAdapter adapter1 = new CustomAdapter();
        activityMainBinding.list1.setAdapter(adapter1);
    }

    // BaseAdapter를 상속받은 클래스
    class CustomAdapter extends BaseAdapter {
        RowButtonClickListener1 listener1 = new RowButtonClickListener1();

        // 어뎁터뷰를 통해 보여줄 항목 개수를 결정한다.
        @Override
        public int getCount() {
            return data1.length;
        }

        // 생성된 항목 뷰를 반환하는 목적으로 사용한다.
        @Override
        public Object getItem(int position) {
            return null;
        }

        // 생성된 항목 뷰의 아이디를 반환하는 목적으로 사용한다.
        @Override
        public long getItemId(int position) {
            return 0;
        }

        // 항목 하나를 구성하기 위해 호출되늰 메서드
        // 첫 번째: 위치
        // 두 번째: 재 사용 가능한 항목의 뷰가 전달된다. 만약 재사용 가능한 뷰가 없다면 null이 들어온다.
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            RowBinding rowBinding = null;

            // 재사용 가능한 항목 뷰가 없다면
            if(convertView == null) {
                rowBinding = RowBinding.inflate(getLayoutInflater());
                convertView = rowBinding.getRoot();

                // 바인딩 객체를 View에 저장한다.
                convertView.setTag(rowBinding);
            } else {
                // 재사용 가능한 뷰가 있다면...
                // 이 뷰를 만들 때 사용했던 바인딩 객체를 추출한다.
                rowBinding = (RowBinding) convertView.getTag();
            }

            rowBinding.rowTextView1.setText(data1[position]);

            // 버튼에 현재 위치값을 저장한다.
            rowBinding.rowButton1.setTag(position);
            rowBinding.rowButton2.setTag(position);

            rowBinding.rowButton1.setOnClickListener(listener1);
            rowBinding.rowButton2.setOnClickListener(listener1);

            return convertView;
        }
    }

    // 항목의 뷰가 가지고 있는 버튼을 눌렀을 때...
    class RowButtonClickListener1 implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            // 사용자가 누른 버튼의 아이디를 추출한다.
            int id = v.getId();
            // 항목 위치 값을 가져온다.
            int position = (int) v.getTag();

            if (id == R.id.rowButton1) {
                activityMainBinding.textView.setText("첫 번째 버튼을 눌렀습니다." + position);
            } else if (id == R.id.rowButton2) {
                activityMainBinding.textView.setText("두 번째 버튼을 눌렀습니다." + position);
            }
        }
    }
}