package com.example.activityobject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.activityobject.databinding.ActivitySecondBinding;

public class SecondActivity extends AppCompatActivity {
    ActivitySecondBinding activitySecondBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        activitySecondBinding = ActivitySecondBinding.inflate(getLayoutInflater());
        setContentView(activitySecondBinding.getRoot());
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Button2ClickListener listener2 = new Button2ClickListener();
        activitySecondBinding.button2.setOnClickListener(listener2);

        // Intent 추출
        Intent secondIntent = getIntent();
        // 객체를 복원한다.
        TestClass t1 = secondIntent.getParcelableExtra("t1");

        activitySecondBinding.textView2.setText("data1 : " + t1.getData1() + "\n");
        activitySecondBinding.textView2.append("data2 : " + t1.getData2());
    }

    class Button2ClickListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            Intent resultInent = new Intent();

            // 객체를 생성한다.
            TestClass t2 = new TestClass(200, "문자열2");
            resultInent.putExtra("t2", t2);

            setResult(RESULT_OK, resultInent);

            finish();
        }
    }
}