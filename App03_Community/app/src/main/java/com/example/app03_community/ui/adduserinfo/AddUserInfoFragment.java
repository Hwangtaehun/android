package com.example.app03_community.ui.adduserinfo;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.app03_community.MainActivity;
import com.example.app03_community.R;
import com.example.app03_community.databinding.FragmentAddUserInfoBinding;

public class AddUserInfoFragment extends Fragment {
    FragmentAddUserInfoBinding fragmentAddUserInfoBinding;
    MainActivity mainActivity;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        fragmentAddUserInfoBinding = FragmentAddUserInfoBinding.inflate(inflater);
        mainActivity = (MainActivity) getActivity();

        setToolbar();
        setButton();

        return fragmentAddUserInfoBinding.getRoot();
    }

    public void setToolbar() {
        fragmentAddUserInfoBinding.toolbarAddUserInfo.setTitle("회원가입");
        fragmentAddUserInfoBinding.toolbarAddUserInfo.setNavigationIcon(R.drawable.arrow_back_24px);
        fragmentAddUserInfoBinding.toolbarAddUserInfo.setNavigationOnClickListener(v -> {
            mainActivity.removeFragment(MainActivity.ADD_USER_INFO_FRAGMENT);
        });
    }

    public void setButton() {
        fragmentAddUserInfoBinding.buttonAddUserInfoSubmit.setOnClickListener(v -> {
            //mainActivity.removeFragment(MainActivity.ADD_USER_INFO_FRAGMENT);
            mainActivity.removeFragment(MainActivity.JOIN_FRAGMENT);
        });
    }
}