package com.example.activityobject;

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

import com.example.activityobject.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding activityMainBinding;
    ActivityResultLauncher<Intent> secondActivityLauncher;

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

        SecondActivityCallback callback1 = new SecondActivityCallback();
        ActivityResultContracts.StartActivityForResult contracts1 = new ActivityResultContracts.StartActivityForResult();
        secondActivityLauncher = registerForActivityResult(contracts1, callback1);

        Button1ClickListener listener1 = new Button1ClickListener();
        activityMainBinding.button.setOnClickListener(listener1);
    }

    class SecondActivityCallback implements ActivityResultCallback<ActivityResult>{
        @Override
        public void onActivityResult(ActivityResult o) {
            // result code를 추출한다.
            int resultCode = o.getResultCode();

            if(resultCode == RESULT_OK) {
                // Intent를 추출한다.
                Intent data = o.getData();

                // 객체를 추출한다.
                TestClass t2 = data.getParcelableExtra("t2");
                activityMainBinding.textView.setText("data1 : " + t2.getData1() + "\n");
                activityMainBinding.textView.append("data2 : " + t2.getData2());
            }
        }
    }

    class Button1ClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            Intent secondIntent = new Intent(MainActivity.this, SecondActivity.class);

            // 전달할 객체를 생성한다.
            TestClass t1 = new TestClass(100, "문자열1");
            secondIntent.putExtra("t1", t1);

            secondActivityLauncher.launch(secondIntent);
        }
    }
}