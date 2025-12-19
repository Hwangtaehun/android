package workplace.viewbasice;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    Button btn1;
    TextView text1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        //생성된 View의 주소값을 가져온다.
        btn1 = findViewById(R.id.button);
        text1 = findViewById(R.id.textView);

        BtnListener listener1 = new BtnListener();
        btn1.setOnClickListener(listener1);
    }

    //  버튼 누르면 동작할 리스너
    class BtnListener implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            text1.setText("버튼을 눌렀습니다.");
        }
    }
}