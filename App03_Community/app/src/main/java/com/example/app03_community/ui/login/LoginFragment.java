package com.example.app03_community.ui.login;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.app03_community.MainActivity;
import com.example.app03_community.R;
import com.example.app03_community.databinding.FragmentLoginBinding;
import com.example.app03_community.repository.UserInfoRepository;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.List;

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

    public void setToolbar(){
        fragmentLoginBinding.toolbarLogin.setTitle("로그인");
    }

    public void setButton(){
        fragmentLoginBinding.buttonLoginJoin.setOnClickListener(view -> {
            mainActivity.replaceFragment(MainActivity.JOIN_FRAGMENT, true, true, null);
        });

        fragmentLoginBinding.buttonLoginSubmit.setOnClickListener(view -> {
            processSubmit();

        });
    }

    public void setContent(){
        loginViewModel.inputLoginUserId.setValue("");
        loginViewModel.inputLoginUserPw.setValue("");
        loginViewModel.checkBoxLoginAuto.setValue(false);

        fragmentLoginBinding.inputLoginUserPw.setOnEditorActionListener((textView, i, keyEvent) -> {
            processSubmit();
            return true;
        });
    }

    public void processSubmit(){

        String inputLoginUserId = loginViewModel.inputLoginUserId.getValue();
        String inputLoginUserPw = loginViewModel.inputLoginUserPw.getValue();

        if(inputLoginUserId == null || inputLoginUserId.trim().length() == 0){
            mainActivity.showAlertDialog("아이디 입력 오류", "아이디를 입력해주세요", (dialogInterface, i) -> {
                mainActivity.showSoftInput(fragmentLoginBinding.inputLoginUserId);
            });
            return;
        }

        if(inputLoginUserPw == null || inputLoginUserPw.trim().length() == 0){
            mainActivity.showAlertDialog("비밀번호 입력 오류", "비밀번호를 입력해주세요", (dialogInterface, i) -> {
                mainActivity.showSoftInput(fragmentLoginBinding.inputLoginUserPw);
            });
            return;
        }

        UserInfoRepository.checkLoginUser(inputLoginUserId, o -> {
            QuerySnapshot querySnapshot = (QuerySnapshot) o;
            List<DocumentSnapshot> documentSnapshotList = querySnapshot.getDocuments();

            if(documentSnapshotList.size() == 0) {
                mainActivity.showAlertDialog("아이디 입력 오류", "입력하신 아이디가 존재하지 않습니다.",
                        (dialog, which) -> {
                            fragmentLoginBinding.inputLoginUserId.setText("");
                            mainActivity.showSoftInput(fragmentLoginBinding.inputLoginUserId);
                        });
            } else {
                DocumentSnapshot documentSnapshot = documentSnapshotList.get(0);
                String loginUserPw = documentSnapshot.getString("userPw");

                if(inputLoginUserPw.equals(loginUserPw) ==  false) {
                    mainActivity.showAlertDialog("비밀번호 입력 오류", "비밀번호를 잘못 입력하였습니다.",
                    (dialog, which) -> {
                        fragmentLoginBinding.inputLoginUserPw.setText("");
                        mainActivity.showSoftInput(fragmentLoginBinding.inputLoginUserPw);
                    });
                } else {
                    Snackbar.make(fragmentLoginBinding.getRoot(), "로그인 되었습니다.", Snackbar.LENGTH_SHORT).show();
                    mainActivity.loginUserIdx = documentSnapshot.getLong("userIdx").intValue();

                    if(fragmentLoginBinding.checkBoxLoginAuto.isChecked() == true) {
                        SharedPreferences sharedPreferences = mainActivity.getSharedPreferences("AutoLogin", MainActivity.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putInt("loginUserIdx", mainActivity.loginUserIdx);
                        editor.apply();
                    }

                    mainActivity.replaceFragment(MainActivity.POST_MAIN_FRAGMENT, false, true, null);
                }
            }
        });

        mainActivity.hideSoftInput();
        //mainActivity.replaceFragment(MainActivity.POST_MAIN_FRAGMENT, false, true, null);
    }
}