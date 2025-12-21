package com.example.button;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.button.databinding.ActivityMainBinding;

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

        // Button 문자열 변경
        activityMainBinding.button.setText("버튼 입니다");

        // ImageButton의 이미지 변경
        // Bitmap 객체 생성
        Resources res = getResources();
        Bitmap bitmap = BitmapFactory.decodeResource(res, R.drawable.books2);
        activityMainBinding.imageButton.setImageBitmap(bitmap);

        // 버튼에 리스너 설정
        ButtonClickListener1 buttonClickListener1 = new ButtonClickListener1();
        activityMainBinding.button.setOnClickListener(buttonClickListener1);

        // 이미지 버튼의 리스너 설정
        ImageButtonClickListenr1 imageButtonClickListenr1 = new ImageButtonClickListenr1();
        activityMainBinding.imageButton.setOnClickListener(imageButtonClickListenr1);

        // 버튼2와 버튼3에 리스너 설정
        ButtonClickListener2 buttonClickListener2 = new ButtonClickListener2();
        activityMainBinding.button2.setOnClickListener(buttonClickListener2);
        activityMainBinding.button3.setOnClickListener(buttonClickListener2);
    }

    // Button의 Click 리스너
    class ButtonClickListener1 implements View.OnClickListener
    {
        @Override
        public void onClick(View v) {
            activityMainBinding.textView.setText("버튼을 눌렀습니다");
        }
    }

    // ImageButton의 Click 리스너
    class ImageButtonClickListenr1 implements View.OnClickListener
    {
        @Override
        public void onClick(View v) {
            activityMainBinding.textView.setText("이미지버튼을 눌렀습니다.");
        }
    }

    // 버튼2와 버튼3의 리스너
    class ButtonClickListener2 implements View.OnClickListener
    {
        @Override
        public void onClick(View v) {
            // 사용자가 누른 버튼의 id를 가져온다.
            int id = v.getId();

            if(R.id.button2 == id){
                activityMainBinding.textView.setText("버튼2를 눌렀습니다.");
            }
            else if(R.id.button3 == id) {
                activityMainBinding.textView.setText("버튼3을 눌렀습니다.");
            }
        }
    }
}