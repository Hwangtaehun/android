package com.example.recyclerview;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.recyclerview.databinding.ActivityMainBinding;
import com.example.recyclerview.databinding.RowBinding;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding activityMainBinding;

    int [] imgRes = {
            R.drawable.imgflag1, R.drawable.imgflag2,
            R.drawable.imgflag3, R.drawable.imgflag4,
            R.drawable.imgflag5, R.drawable.imgflag6,
            R.drawable.imgflag7, R.drawable.imgflag8
    };

    String [] data1 = {
            "토고", "프랑스 문자열을 길게 작성 해주세요.", "스위스", "스페인", "일본 문자열을 길게 작성 해주세요.", "독일", "브라질", "대한민국"
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
        RecyclerAdapter adapter1 = new RecyclerAdapter();
        activityMainBinding.recycler1.setAdapter(adapter1);

        // RecyclerView의 보여주는 방식을 설정한다.
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        // RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this, 2);
        // RecyclerView.LayoutManager layoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        // RecyclerView.LayoutManager layoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.HORIZONTAL);

        activityMainBinding.recycler1.setLayoutManager(layoutManager);
    }

    // RecyclerView의 어뎁터를 생성한다.
    class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolderClass>{

        // ViewHolder 클래스를 생성한다.
        @NonNull
        @Override
        public ViewHolderClass onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            RowBinding rowBinding = RowBinding.inflate(getLayoutInflater());
            ViewHolderClass viewHolderClass = new ViewHolderClass(rowBinding);

            // 생성되는 항목의 View의 가로 세로 길이를 설정한다.
            RecyclerView.LayoutParams layoutParams = new RecyclerView.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
            );

            rowBinding.getRoot().setLayoutParams(layoutParams);
            rowBinding.getRoot().setOnClickListener(viewHolderClass);

            return viewHolderClass;
        }

        // ViewHolder가 가지고 있는 View에 데이터를 셋팅한다.
        @Override
        public void onBindViewHolder(@NonNull ViewHolderClass holder, int position) {
            holder.rowImageView.setImageResource(imgRes[position]);
            holder.rowTextView.setText(data1[position]);
        }

        // 전체 항목의 개수
        @Override
        public int getItemCount() {
            return imgRes.length;
        }

        class ViewHolderClass extends RecyclerView.ViewHolder implements View.OnClickListener {
            // 항목 하나의 View의 주소값을 담을 변수
            ImageView rowImageView;
            TextView rowTextView;

            public ViewHolderClass(RowBinding rowBinding) {
                super(rowBinding.getRoot());

                rowImageView = rowBinding.rowImageView;
                rowTextView = rowBinding.rowTextView;
            }

            @Override
            public void onClick(View v) {
                // 홀더가 관리하는 항목의 순서값을 가져온다.
                int position = getAdapterPosition();
                activityMainBinding.textView.setText(data1[position]);
            }
        }
    }
}