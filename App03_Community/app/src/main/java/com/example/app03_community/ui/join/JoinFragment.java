package com.example.app03_community.ui.join;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.os.SystemClock;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.example.app03_community.MainActivity;
import com.example.app03_community.R;
import com.example.app03_community.databinding.FragmentJoinBinding;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

public class JoinFragment extends Fragment {
    FragmentJoinBinding fragmentJoinBinding;
    MainActivity mainActivity;
    JoinViewModel joinViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        // fragmentJoinBinding = FragmentJoinBinding.inflate(inflater);
        fragmentJoinBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_join, container, false);
        joinViewModel = new JoinViewModel();
        fragmentJoinBinding.setJoinViewModel(joinViewModel);
        fragmentJoinBinding.setLifecycleOwner(this);

        mainActivity = (MainActivity) getActivity();

        setToolbar();
        setButton();
        setContent();

        //joinViewModel.inputJoinUserId.setValue("하하하");

        return fragmentJoinBinding.getRoot();
    }

    public void setToolbar() {
        fragmentJoinBinding.toolbarJoin.setTitle("회원가입");
        fragmentJoinBinding.toolbarJoin.setNavigationIcon(R.drawable.arrow_back_24px);
        fragmentJoinBinding.toolbarJoin.setNavigationOnClickListener(v -> {
            mainActivity.removeFragment(MainActivity.JOIN_FRAGMENT);
        });
    }

    private void setButton() {
        fragmentJoinBinding.buttonJoinNext.setOnClickListener(v -> {
            // 입력한 내용을 가져온다.
            // String test = joinViewModel.inputJoinUserId.getValue();
            // Toast.makeText(mainActivity, test, Toast.LENGTH_SHORT).show();

            // mainActivity.replaceFragment(MainActivity.ADD_USER_INFO_FRAGMENT, true, true, null);

            processSubmit();
        });
    }

    public void setContent() {
        joinViewModel.inputJoinUserId.setValue("");
        joinViewModel.inputJoinUSerPw.setValue("");
        joinViewModel.inputJoinUSerPw2.setValue("");

        fragmentJoinBinding.inputJoinUserPw2.setOnEditorActionListener((v, actionId, event) -> {
            processSubmit();

            return true;
        });
    }

    public void processSubmit() {
        // 입력한 내용을 가져온다.
        String inputJoinUserId = joinViewModel.inputJoinUserId.getValue();
        String inputJoinUserPw = joinViewModel.inputJoinUSerPw.getValue();
        String inputJoinUserPw2 = joinViewModel.inputJoinUSerPw2.getValue();

        if(inputJoinUserId == null || inputJoinUserId.trim().length() == 0){
//            MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(mainActivity);
//            builder.setTitle("아이디 입력 오류");
//            builder.setMessage("아이디를 입력해주세요.");
//            builder.setIcon(R.drawable.warning_24px);
//            builder.setPositiveButton("확인", (dialog, which) -> {
//                fragmentJoinBinding.inputJoinUserId.requestFocus();
//                new Thread(() -> {
//                    SystemClock.sleep(200);
//                    InputMethodManager inputMethodManager = (InputMethodManager) mainActivity.getSystemService(MainActivity.INPUT_METHOD_SERVICE);
//                    inputMethodManager.showSoftInput(fragmentJoinBinding.inputJoinUserId, 0);
//                }).start();
//            });
//            builder.show();
            mainActivity.showAlertDialog("아이디 입력 오류", "아이디를 입력해주세요", (dialog, which) -> {
                mainActivity.showSoftInput(fragmentJoinBinding.inputJoinUserId);
            });
            return;
        }

        if(inputJoinUserPw == null || inputJoinUserPw.trim().length() == 0) {
            mainActivity.showAlertDialog("비밀번호 입력 오류", "비밀번호를 입력해주세요", (dialog, which) -> {
                mainActivity.showSoftInput(fragmentJoinBinding.inputJoinUserPw);
            });
            return;
        }

         if(inputJoinUserPw2 == null || inputJoinUserPw2.trim().length() == 0) {
             mainActivity.showAlertDialog("비밀번호 입력 오류", "비밀번호를 입력해주세요", (dialog, which) -> {
                 mainActivity.showSoftInput(fragmentJoinBinding.inputJoinUserPw2);
             });
             return;
         }

         if(inputJoinUserPw.equals(inputJoinUserPw2) == false) {
             mainActivity.showAlertDialog("비밀번호 입력 오류", "비밀번호가 다릅니다", (dialog, which) -> {
                 joinViewModel.inputJoinUSerPw.setValue("");
                 joinViewModel.inputJoinUSerPw2.setValue("");
                 mainActivity.showSoftInput(fragmentJoinBinding.inputJoinUserPw);
             });
             return;
         }

        mainActivity.replaceFragment(MainActivity.ADD_USER_INFO_FRAGMENT, true, true, null);
    }
}