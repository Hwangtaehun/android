package com.example.mvvm;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.ViewModelProvider;

import com.example.mvvm.databinding.ActivityShowBinding;
import com.example.mvvm.viewmodel.TestViewModel;

public class ShowActivity extends AppCompatActivity {
    ActivityShowBinding activityShowBinding;

    TestViewModel testViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        activityShowBinding = ActivityShowBinding.inflate(getLayoutInflater());
        setContentView(activityShowBinding.getRoot());
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        testViewModel = new ViewModelProvider(this).get(TestViewModel.class);

        testViewModel.data1.observe(this, s -> {
            activityShowBinding.textViewShowData1.setText(s);
        });

        testViewModel.data2.observe(this, s -> {
            activityShowBinding.textViewShowData2.setText(s);
        });

        Intent intent = getIntent();
        testViewModel.data1.setValue(intent.getStringExtra("data1"));
        testViewModel.data2.setValue(intent.getStringExtra("data2"));

        activityShowBinding.buttonShow.setOnClickListener(v -> {
            finish();
        });
    }
}