package com.example.app03_community.ui.login;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
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
    LoginViewModel loginViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        // fragmentLoginBinding = FragmentLoginBinding.inflate(inflater);
        fragmentLoginBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false);
        loginViewModel = new LoginViewModel();
        fragmentLoginBinding.setLoginViewModel(loginViewModel);
        fragmentLoginBinding.setLifecycleOwner(this);

        mainActivity = (MainActivity) getActivity();

        setToolbar();
        setButton();
        setContent();

        return fragmentLoginBinding.getRoot();
    }

    public void setToolbar() {
        fragmentLoginBinding.toolbarLogin.setTitle("로그인");
    }

    public void setButton() {
        fragmentLoginBinding.buttonLoginJoin.setOnClickListener(v -> {
            processSubmit();
        });

        fragmentLoginBinding.buttonLoginSubmit.setOnClickListener(v -> {
            mainActivity.replaceFragment(MainActivity.POST_MAIN_FRAGMENT, false, true, null);
        });
    }

    public void setContent() {
        loginViewModel.inputLoginUserId.setValue("");
        loginViewModel.inputLoginUserPw.setValue("");
        loginViewModel.checkBoxLoginAuto.setValue(false);

        fragmentLoginBinding.inputLoginUserPw.setOnEditorActionListener((v, actionId, event) -> {
            processSubmit();
            return true;
        });
    }

    public void processSubmit() {
        String inputLoginUserId = loginViewModel.inputLoginUserId.getValue();
        String inputLoginUserPw = loginViewModel.inputLoginUserPw.getValue();

        if(inputLoginUserId == null || inputLoginUserId.trim().length() == 0) {
            mainActivity.showAlertDialog("아이디 입력 오류", "아이디를 입력해주세요.", (dialog, which) -> {
                mainActivity.showSoftInput(fragmentLoginBinding.inputLoginUserId);
            });
            return;
        }

        if(inputLoginUserPw == null || inputLoginUserPw.trim().length() == 0) {
            mainActivity.showAlertDialog("비밀번호 입력 오류", "비밀번호를 입력해주세요.", (dialog, which) -> {
                mainActivity.showSoftInput(fragmentLoginBinding.inputLoginUserPw);
            });
            return;
        }

        mainActivity.replaceFragment(MainActivity.JOIN_FRAGMENT, true, true, null);
    }
}