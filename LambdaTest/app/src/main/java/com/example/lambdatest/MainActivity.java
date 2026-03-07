package com.example.lambdatest;

import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.lambdatest.databinding.ActivityMainBinding;

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

//        ButtonClickListener buttonClickListener = new ButtonClickListener();
//        activityMainBinding.button.setOnClickListener(buttonClickListener);

        activityMainBinding.button.setOnClickListener(v -> {
            setTextView(100, 200, (a1, a2) -> {
                return a1 + a2;
            });
        });

        new Thread(() -> {
            while (true){
                SystemClock.sleep(100);

                runOnUiThread(() -> {
                    activityMainBinding.textView.setText("now : " + System.currentTimeMillis());
                });
            }
        }).start();
    }

//    class ButtonClickListener implements View.OnClickListener {
//        @Override
//        public void onClick(View v) {
//            TestClass1 t100 = new TestClass1();
//            setTextView(100, 200, t100);
//
//            setTextView(100, 200, new Test() {
//                @Override
//                public int calc(int a1, int a2) {
//                    return a1 - a2;
//                }
//            });
//
//            setTextView(100, 200, (a1, a2) -> {
//                return a1 * a2;
//            });
//        }
//    }

    public void setTextView(int v1, int v2, Test t1) {
        int r1 = t1.calc(v1, v2);
        activityMainBinding.textView.setText(" r1 : " + r1);
    }
}

@FunctionalInterface
interface Test {
    public int calc(int a1, int a2);
}

class TestClass1 implements Test {
    @Override
    public int calc(int a1, int a2) {
        return a1 + a2;
    }
}