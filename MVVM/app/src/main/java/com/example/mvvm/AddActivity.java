package com.example.mvvm;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.mvvm.databinding.ActivityAddBinding;
import com.example.mvvm.model.TestModel;
import com.example.mvvm.repository.TestRepository;

public class AddActivity extends AppCompatActivity {
    ActivityAddBinding activityAddBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityAddBinding = ActivityAddBinding.inflate(getLayoutInflater());
        EdgeToEdge.enable(this);
        setContentView(activityAddBinding.getRoot());
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        activityAddBinding.buttonAddSubmit.setOnClickListener(v -> {
            String data1 = activityAddBinding.editTextAddData1.getText().toString();
            String data2 = activityAddBinding.editTextAddData2.getText().toString();

            TestModel testModel = new TestModel(data1, data2);
            TestRepository.addData(this, testModel);

            finish();
        });
    }
}