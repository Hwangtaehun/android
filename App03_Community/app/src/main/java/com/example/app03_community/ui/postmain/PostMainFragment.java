package com.example.app03_community.ui.postmain;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.SystemClock;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.app03_community.MainActivity;
import com.example.app03_community.R;
import com.example.app03_community.databinding.FragmentPostMainBinding;
import com.example.app03_community.databinding.HeaderPostmainBinding;

public class PostMainFragment extends Fragment {
    FragmentPostMainBinding fragmentPostMainBinding;
    MainActivity mainActivity;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        fragmentPostMainBinding = FragmentPostMainBinding.inflate(inflater);
        mainActivity = (MainActivity) getActivity();

        setNavigationDrawer();


        return fragmentPostMainBinding.getRoot();
    }

    public void setNavigationDrawer() {
        HeaderPostmainBinding headerPostmainBinding = HeaderPostmainBinding.inflate(getLayoutInflater());
        headerPostmainBinding.headerPostMainNickname.setText("홍길동님");
        fragmentPostMainBinding.navigationViewPostMain.addHeaderView(headerPostmainBinding.getRoot());

        fragmentPostMainBinding.navigationViewPostMain.setNavigationItemSelectedListener(menuItem -> {
            SystemClock.sleep(300);
            fragmentPostMainBinding.drawLayoutPostMain.close();
            return true;
        });
    }
}