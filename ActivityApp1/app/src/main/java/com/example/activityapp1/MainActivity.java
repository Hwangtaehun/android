package com.example.activityapp1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.activityapp1.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding activityMainBinding;
    ActivityResultLauncher<Intent> testActivityLauncher;

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

        TestActivityCallback callback1 = new TestActivityCallback();
        ActivityResultContracts.StartActivityForResult s1 = new ActivityResultContracts.StartActivityForResult();
        testActivityLauncher = registerForActivityResult(s1, callback1);

        Button1ClickListener listener1 = new Button1ClickListener();
        activityMainBinding.button.setOnClickListener(listener1);
    }

    class TestActivityCallback implements ActivityResultCallback<ActivityResult> {
        @Override
        public void onActivityResult(ActivityResult o) {
            int resultCode = o.getResultCode();

            if(resultCode == RESULT_OK) {
                Intent data = o.getData();

                int value1 = data.getIntExtra("value1", 0);
                String value2 = data.getStringExtra("value2");

                activityMainBinding.textView.setText("value1 : " + value1 + "\n");
                activityMainBinding.textView.append("value2 : " + value2);
            }
        }
    }

    class Button1ClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            // 다른 애플리게이션의 Activity 이름이 설정된 Intent를 생성한다.
            Intent testIntent = new Intent("kr.co.softcampus.test_activity");

            testIntent.putExtra("data1", 100);
            testIntent.putExtra("data2", "문자열1");

            testActivityLauncher.launch(testIntent);
        }
    }
}