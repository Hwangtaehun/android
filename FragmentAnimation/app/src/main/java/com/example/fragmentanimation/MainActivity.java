package com.example.fragmentanimation;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class MainActivity extends AppCompatActivity {
    //Fragment의 이름
    static final String FIRST_FRAGMENT = "first";
    static final String SECOND_FRAGMENT = "second";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setFragment(FIRST_FRAGMENT, false);
    }

    public void setFragment(String name, boolean add) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        switch (name){
            case FIRST_FRAGMENT:
                FirstFragment firstFragment = new FirstFragment();
                fragmentTransaction.replace(R.id.fragmentContainerView, firstFragment);
                break;
            case SECOND_FRAGMENT:
                SecondFragment secondFragment = new SecondFragment();

                // 애니메이션 설정
                // fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                // fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                //fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE);
                // A -> B
                // B가 나타날때의 애니메이션, A가 사라질때의 애니메이션, B가 사라지는 애니메이션, A가 나타나는 애니메시션
                //fragmentTransaction.setCustomAnimations(R.anim.fade_xml1, R.anim.fade_xml2, R.anim.fade_xml1, R.anim.fade_xml2);
                fragmentTransaction.setCustomAnimations(R.anim.slide_xml1, R.anim.slide_xml2, R.anim.slide_xml3, R.anim.slide_xml4);

                fragmentTransaction.replace(R.id.fragmentContainerView, secondFragment);
                break;
        }

        if(add == true) {
            fragmentTransaction.addToBackStack(null);
        }

        fragmentTransaction.commit();
    }
}