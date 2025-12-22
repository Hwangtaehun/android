package com.example.edittext;

import android.os.Bundle;
import android.os.SystemClock;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.edittext.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding activityMainBinding;

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

        // EditText에 문자열 셋팅
        activityMainBinding.editText1.setText("코드에서 문자열 입력");

        ButtonClickListener1 buttonClickListener1 = new ButtonClickListener1();
        activityMainBinding.button.setOnClickListener(buttonClickListener1);

        // 포거스를 부여한다.
        activityMainBinding.editText1.requestFocus();

        ShowKeyboardThread thread1 = new ShowKeyboardThread();
        thread1.start();

        // 입력 감시자
        EditTextWatcher1 editTextWatcher1 = new EditTextWatcher1();
        activityMainBinding.editText1.addTextChangedListener(editTextWatcher1);

        // 엔터키를 눌렀을 때의 이벤트
        EditTextEnterListener1 editTextEnterListener1 = new EditTextEnterListener1();
        activityMainBinding.editText1.setOnEditorActionListener(editTextEnterListener1);
    }

    class ShowKeyboardThread extends Thread
    {
        @Override
        public void run() {
            super.run();

            //500ms 쉬었다간다.
            SystemClock.sleep(500);

            //키보드가 나타나게 한다.
            InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            imm.showSoftInput(getCurrentFocus(), 0);
        }
    }

    class ButtonClickListener1 implements View.OnClickListener
    {
        @Override
        public void onClick(View v) {
            // EditText의 문자열을 가져온다.
            String str1 = activityMainBinding.editText1.getText().toString();
            activityMainBinding.textView.setText(str1);

            // 키보드 내리기
            InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);

            // 포커스를 해제한다.
            getCurrentFocus().clearFocus();

        }
    }

    // edittext에 입력한 내용을 실시간으로 감시하는 왓처
    class EditTextWatcher1 implements TextWatcher
    {
        // 입력 내용 변경한 후
        @Override
        public void afterTextChanged(Editable s) {
            activityMainBinding.textView3.setText("after : " + s);
        }

        // 입력 내용 변경 전
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            activityMainBinding.textView.setText("before : " + s);
        }

        // 입력 내용 변경 했을 때
        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            activityMainBinding.textView2.setText("changed : " + s);
        }
    }


    // Enter 키를 눌렀을 때...
    class EditTextEnterListener1 implements TextView.OnEditorActionListener
    {
        @Override
        public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
            String str1 = activityMainBinding.editText1.getText().toString();
            activityMainBinding.textView.setText("엔터 버튼을 눌렀습니다.");
            activityMainBinding.textView2.setText(str1);

            return false;
        }
    }
}