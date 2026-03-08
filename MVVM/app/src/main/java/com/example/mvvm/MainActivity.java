package com.example.mvvm;

import android.content.Intent;
import android.os.Bundle;
import android.view.ViewGroup;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mvvm.databinding.ActivityMainBinding;
import com.example.mvvm.databinding.RowBinding;
import com.example.mvvm.model.TestModel;
import com.example.mvvm.viewmodel.TestViewModel;

import java.util.ArrayList;

// model : 애플리케이션에서 사용되는 모든 데이터를 관리하는 요소들
// repository : 서버나 데이터 베이스에서 데이터를 가져오거나 저장, 수정, 삭제 등의 작업을 한다.
// viewmodel : 화면 구성을 위해 피룡한 데이터를 관리하는 요소들

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding activityMainBinding;
    //ViewModel
    TestViewModel testViewModel;

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

        // ViewModel 객체를 받아온다.
        testViewModel = new ViewModelProvider(this).get(TestViewModel.class);

        activityMainBinding.buttonAdd.setOnClickListener(view -> {
            Intent newIntent = new Intent(this, AddActivity.class);
            startActivity(newIntent);
        });

        MainRecyclerViewAdapter mainRecyclerViewAdapter = new MainRecyclerViewAdapter();
        activityMainBinding.recyclerViewMain.setAdapter(mainRecyclerViewAdapter);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        activityMainBinding.recyclerViewMain.setLayoutManager(linearLayoutManager);

        // 감시자 설정
        // MutableLiveData가 관리하는 값을 새롭게 설정하면 감시자가 동작하고 감시자에 구현한 코드가 자동으로 동작하게 된다.
        // MutableLiveData가 관리하는 데이터를 통해 화면을 구성하는 작업을 해주면 된다.
        testViewModel.dataList.observe(this, testModels -> {
            RecyclerView.Adapter adapter = activityMainBinding.recyclerViewMain.getAdapter();
            adapter.notifyDataSetChanged();
        });
    }

    class MainRecyclerViewAdapter extends RecyclerView.Adapter<MainRecyclerViewAdapter.MainViewHolder> {
        @NonNull
        @Override
        public MainViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            RowBinding rowBinding = RowBinding.inflate(getLayoutInflater());
            MainViewHolder mainViewHolder = new MainViewHolder(rowBinding);

            return mainViewHolder;
        }

        @Override
        public void onBindViewHolder(@NonNull MainViewHolder holder, int position) {
            ArrayList<TestModel> a1 = testViewModel.dataList.getValue();
            TestModel testModel = a1.get(position);

            holder.rowBinding.textViewRow.setText(testModel.getTextData1());
        }

        @Override
        public int getItemCount() {
            ArrayList<TestModel> a1 = testViewModel.dataList.getValue();
            return a1.size();
        }

        class MainViewHolder extends RecyclerView.ViewHolder {
            RowBinding rowBinding;

            public MainViewHolder(RowBinding rowBinding) {
                super(rowBinding.getRoot());
                this.rowBinding = rowBinding;

                ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT
                );
                rowBinding.getRoot().setLayoutParams(layoutParams);
                rowBinding.getRoot().setOnClickListener(v -> {
                    ArrayList<TestModel> a1 = testViewModel.dataList.getValue();
                    //int position = getAdapterPosition();
                    int position = getBindingAdapterPosition();
                    TestModel a2 = a1.get(position);

                    Intent newIntent = new Intent(MainActivity.this, ShowActivity.class);

                    newIntent.putExtra("data1", a2.getTextData1());
                    newIntent.putExtra("data2", a2.getTextData2());

                    startActivity(newIntent);
                });
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        // MutableLiveData에 새로운 객체를 설정하는 메서드를 호출한다.
        testViewModel.getAllData(this);

    }
}