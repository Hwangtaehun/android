package com.example.activitylifecycle;

import android.os.Bundle;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.activitylifecycle.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding activityMainBinding;

    // Activity가 생성될 때 자동으로 호출된다.
    // 화면 전환이 발생했을 때 자동으로 호출된다.
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

        Log.d("test", "onCreate");

        //System.out.println("안녕하세요");
//        Log.d("test1", "d");
//        Log.e("test1", "e");
//        Log.i("test1", "i");
//        Log.v("test1", "v");
//        Log.w("test2", "w");
    }

    // onCreate 메서드 호출 이후에 자동으로 호출된다.
    // Activity가 정지 상태가 되었다가 활동 상태로 돌아올 때 호출
    @Override
    protected void onStart() {
        super.onStart();
        Log.d("test", "onStart");
    }

    // onStart 메서드가 호출된 이후 자동으로 호출된다.
    // Activity가 일시 정지 되었다가 다시 돌아 올때 호출
    @Override
    protected void onResume() {
        super.onResume();
        Log.d("test", "onResume");
    }

    // Activity가 정지 상태가 되었다가 활동 상태로 돌아갈 때 onStart 전에 호출된다.
    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d("test", "onRestart");
    }

    // Activity가 일시 정지 상태가 될 때 호출된다.
    // 화면상에서 완전히 사라지거나 화면 위에 작은 팝업 창 같이 나타날 때 호출
    @Override
    protected void onPause() {
        super.onPause();
        Log.d("test", "onPause");
    }

    // Activity가 화면에서 사라질 때 호출된다.
    @Override
    protected void onStop() {
        super.onStop();
        Log.d("test", "onStop");
    }

    // 현재 액티비티의 수행이 완전히 종료되어 메모리상에서 제거될 때 호출된다.
    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("test", "onDestroy");
    }
}