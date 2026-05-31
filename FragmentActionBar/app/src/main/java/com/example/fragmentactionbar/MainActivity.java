package com.example.fragmentactionbar;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.fragmentactionbar.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding activityMainBinding;

    static final String SUB_FRAGMENT1 = "SubFragment1";
    static final String SUB_FRAGMENT2 = "SubFragment2";

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

        setFragment(SUB_FRAGMENT1, false);
    }

    public void setFragment(String name, boolean add) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        if( name == SUB_FRAGMENT1) {
            SubFragment1 sub1 = new SubFragment1();
            fragmentTransaction.replace(R.id.fragmentContainerView, sub1);
        } else if(name == SUB_FRAGMENT2) {
            SubFragment2 sub2 = new SubFragment2();
            fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
            fragmentTransaction.replace(R.id.fragmentContainerView, sub2);
        }

        if(add == true) {
            fragmentTransaction.addToBackStack(null);
        }

        fragmentTransaction.commit();
    }
}