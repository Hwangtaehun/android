package com.example.activityrule;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.activityrule.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    // Fragment의 이름
    static final String INPUT_FRAGMENT = "input";
    static final String RESULT_FRAGMENT = "result";

    // 입력 내용을 담을 변수
    String edit1Value;
    String edit2Value;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 첫 화면으로 InputFragment를 보여준다.
        setFragment(INPUT_FRAGMENT, false);

    }

    // Fragment를 전환하는 메서드
    public void setFragment(String name, boolean add) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        // 이름을 기준으로 분기한다.
        switch (name) {
            case INPUT_FRAGMENT:
                //InputFragment inputFragment = InputFragment.newInstance();
                InputFragment inputFragment = new InputFragment();
                fragmentTransaction.replace(R.id.fragmentContainerView, inputFragment);
                break;
            case RESULT_FRAGMENT:
                //ResultFragment resultFragment = ResultFragment.newInstance(edit1Value, edit2Value);
                ResultFragment resultFragment = new ResultFragment();
                fragmentTransaction.replace(R.id.fragmentContainerView, resultFragment);
                break;
        }

        if(add == true){
            fragmentTransaction.addToBackStack(null);
        }

        fragmentTransaction.commit();
    }
}