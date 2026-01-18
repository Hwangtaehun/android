package com.example.activitydata;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.activitydata.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding activityMainBinding;
    ActivityResultLauncher<Intent> secondActivityLauncher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        EdgeToEdge.enable(this);
        setContentView(activityMainBinding.getRoot());

        SecondActivityCallback callback1 = new SecondActivityCallback();
        ActivityResultContracts.StartActivityForResult contracts1 = new ActivityResultContracts.StartActivityForResult();
        secondActivityLauncher = registerForActivityResult(contracts1, callback1);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Button1ClickListener listener1 = new Button1ClickListener();
        activityMainBinding.button.setOnClickListener(listener1);

        Button2ClickListener listener2 = new Button2ClickListener();
        activityMainBinding.button2.setOnClickListener(listener2);
    }

    class Button1ClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            Intent secondIntent = new Intent(MainActivity.this, SecondActivity.class);

            // secondActivity로 보낼 데이터를 저장한다.
            secondIntent.putExtra("data1", 100);
            secondIntent.putExtra("data2", 11.11);
            secondIntent.putExtra("data3", true);
            secondIntent.putExtra("data4", "문자열1");

           // startActivity(secondIntent);
            startActivityForResult(secondIntent, 0);

        }
    }

    class Button2ClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            Intent secondIntent = new Intent(MainActivity.this, SecondActivity.class);

            // secondActivity로 보낼 데이터를 저장한다.
            secondIntent.putExtra("data1", 200);
            secondIntent.putExtra("data2", 22.22);
            secondIntent.putExtra("data3", false);
            secondIntent.putExtra("data4", "문자열2");

            secondActivityLauncher.launch(secondIntent);
        }
    }

    // StartActivityForResult를 통해 다른 액티비티로 갔다 돌아왔을 때
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == RESULT_OK) {
            int value1 = data.getIntExtra("value1", 0);
            double value2 = data.getDoubleExtra("value2", 0.0);
            boolean value3 = data.getBooleanExtra("value3", false);
            String value4 = data.getStringExtra("value4");

            activityMainBinding.textView.setText("value1 : " + value1 + "\n");
            activityMainBinding.textView.append("value2 : " + value2 + "\n");
            activityMainBinding.textView.append("value3 : " + value3 + "\n");
            activityMainBinding.textView.append("value4 : " + value4);
        }
    }

    // ActivityResultLauncher의 콜백
    class SecondActivityCallback implements ActivityResultCallback<ActivityResult> {
        @Override
        public void onActivityResult(ActivityResult o) {
            // Result Code를 추출한다.
            int resultCode = o.getResultCode();

            if(resultCode == RESULT_OK) {
                Intent data = o.getData();

                int value1 = data.getIntExtra("value1", 0);
                double value2 = data.getDoubleExtra("value2", 0.0);
                boolean value3 = data.getBooleanExtra("value3", false);
                String value4 = data.getStringExtra("value4");

                activityMainBinding.textView.setText("value1 : " + value1 + "\n");
                activityMainBinding.textView.append("value2 : " + value2 + "\n");
                activityMainBinding.textView.append("value3 : " + value3 + "\n");
                activityMainBinding.textView.append("value4 : " + value4);
            }
        }
    }
}