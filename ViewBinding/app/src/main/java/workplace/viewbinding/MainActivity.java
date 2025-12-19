package workplace.viewbinding;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import workplace.viewbinding.databinding.ActivityMainBinding;
import workplace.viewbinding.databinding.ActivitySecondBinding;

public class MainActivity extends AppCompatActivity {

    // View의 주소 값을 담을 변수
    // Button button1;
    // TextView textView1;

    // ViewBinding  객체를 담을 변수
    ActivityMainBinding activityMainBinding;
    ActivitySecondBinding activitySecondBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        // ViewBinding 객체를 추출한다.
         activityMainBinding = ActivityMainBinding.inflate(getLayoutInflater());
         setContentView(activityMainBinding.getRoot());

        //activitySecondBinding = ActivitySecondBinding.inflate(getLayoutInflater());
        //setContentView(activitySecondBinding.getRoot());

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // view의 주소 값을 가져옴
//        button1 = (Button) findViewById(R.id.button);
//        textView1 = (TextView) findViewById(R.id.textView);
//
        ButtonClickLinstener1 buttonClickLinstener1 = new ButtonClickLinstener1();
//        button1.setOnClickListener(buttonClickLinstener1);
        activityMainBinding.button.setOnClickListener(buttonClickLinstener1);
    }

    class ButtonClickLinstener1 implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            // textView1.setText("버튼을 눌렀습니다.");
            activityMainBinding.textView.setText("버튼을 눌렀습니다.");
        }
    }
}