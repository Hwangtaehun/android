package com.example.app03_community.ui.login;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.app03_community.MainActivity;
import com.example.app03_community.R;
import com.example.app03_community.databinding.FragmentLoginBinding;

public class LoginFragment extends Fragment {
    FragmentLoginBinding fragmentLoginBinding;
    MainActivity mainActivity;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        fragmentLoginBinding = FragmentLoginBinding.inflate(inflater);
        mainActivity = (MainActivity) getActivity();

        setToolbar();

        setButton();

        return fragmentLoginBinding.getRoot();
    }

    public void setToolbar() {
        fragmentLoginBinding.toolbarLogin.setTitle("로그인");
    }

    public void setButton() {
        fragmentLoginBinding.buttonLoginJoin.setOnClickListener(v -> {
            mainActivity.replaceFragment(MainActivity.JOIN_FRAGMENT, true, true, null);
        });
    }
}