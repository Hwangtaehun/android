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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mvvm.databinding.ActivityMainBinding;
import com.example.mvvm.databinding.RowBinding;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding activityMainBinding;

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

        activityMainBinding.buttonAdd.setOnClickListener(view -> {
            Intent newIntent = new Intent(this, AddActivity.class);
            startActivity(newIntent);
        });

        MainRecyclerViewAdapter mainRecyclerViewAdapter = new MainRecyclerViewAdapter();
        activityMainBinding.recyclerViewMain.setAdapter(mainRecyclerViewAdapter);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        activityMainBinding.recyclerViewMain.setLayoutManager(linearLayoutManager);
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
            holder.rowBinding.textViewRow.setText("항목 : " + position);
        }

        @Override
        public int getItemCount() {
            return 30;
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
                    Intent newIntent = new Intent(MainActivity.this, ShowActivity.class);
                    startActivity(newIntent);
                });
            }
        }
    }
}