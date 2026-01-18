package com.example.activityapp3;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.activityapp3.databinding.ActivityApp3Binding;

public class App3Activity extends AppCompatActivity {
    ActivityApp3Binding activityApp3Binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityApp3Binding = ActivityApp3Binding.inflate(getLayoutInflater());

        EdgeToEdge.enable(this);
        setContentView(activityApp3Binding.getRoot());
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Intent testIntent = getIntent();

        int data1 = testIntent.getIntExtra("data1", 0);
        String data2 = testIntent.getStringExtra("data2");

        activityApp3Binding.textView.setText("data1 : " + data1 + "\n");
        activityApp3Binding.textView.append("data2 : " + data2);

        Button1ClickListener listener1 = new Button1ClickListener();
        activityApp3Binding.button.setOnClickListener(listener1);
    }

    class Button1ClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            Intent resultIntent = new Intent();
            resultIntent.putExtra("value1", 300);
            resultIntent.putExtra("value2", "문자열3");

            setResult(RESULT_OK, resultIntent);
            finish();
        }
    }
}