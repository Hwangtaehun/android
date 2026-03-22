package com.example.app03_community.ui.adduserinfo;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.app03_community.MainActivity;
import com.example.app03_community.R;
import com.example.app03_community.databinding.FragmentAddUserInfoBinding;
import com.google.android.material.checkbox.MaterialCheckBox;

public class AddUserInfoFragment extends Fragment {
    FragmentAddUserInfoBinding fragmentAddUserInfoBinding;
    MainActivity mainActivity;
    AddUserInfoViewModel addUserInfoViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        // fragmentAddUserInfoBinding = FragmentAddUserInfoBinding.inflate(inflater);
        fragmentAddUserInfoBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_add_user_info, container, false);
        addUserInfoViewModel = new AddUserInfoViewModel();
        fragmentAddUserInfoBinding.setAddUserInfoViewModel(addUserInfoViewModel);
        fragmentAddUserInfoBinding.setLifecycleOwner(this);

        mainActivity = (MainActivity) getActivity();

        setToolbar();
        setButton();
        setContent();

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
            processSubmit();
        });
    }

    public void setContent() {
        addUserInfoViewModel.inputAddUserInfoNickname.setValue("");
        addUserInfoViewModel.inputAddUserInfoAge.setValue("");

//        fragmentAddUserInfoBinding.checkBoxAddUserInfo1.setChecked(true);
//        fragmentAddUserInfoBinding.checkBoxAddUserInfo2.setChecked(false);
//
//        fragmentAddUserInfoBinding.checkBoxAddUserInfo4.setCheckedState(MaterialCheckBox.STATE_CHECKED);
//        fragmentAddUserInfoBinding.checkBoxAddUserInfo5.setCheckedState(MaterialCheckBox.STATE_UNCHECKED);
//        fragmentAddUserInfoBinding.checkBoxAddUserInfo6.setCheckedState(MaterialCheckBox.STATE_INDETERMINATE);

//        addUserInfoViewModel.checkBoxAddUserInfo1.setValue(MaterialCheckBox.STATE_CHECKED);

        addUserInfoViewModel.setCheckAll(false);
    }

    public void processSubmit() {
        String inputAddUserInfoNickname = addUserInfoViewModel.inputAddUserInfoNickname.getValue();
        String inputAddUserInfoAge = addUserInfoViewModel.inputAddUserInfoAge.getValue();

        if(inputAddUserInfoNickname == null || inputAddUserInfoNickname.trim().length() == 0) {
            mainActivity.showAlertDialog("닉네임을 입력 오류", "닉네임을 입력해주세요.", (dialog, which) -> {
                mainActivity.showSoftInput(fragmentAddUserInfoBinding.inputAddUserInfoNickname);
            });

            return;
        }

        if(inputAddUserInfoAge == null || inputAddUserInfoAge.trim().length() == 0) {
            mainActivity.showAlertDialog("나이를 입력 오류", "나이를 입력해주세요.", (dialog, which) -> {
                mainActivity.showSoftInput(fragmentAddUserInfoBinding.inputAddUserInfoAge);
            });

            return;
        }

        mainActivity.removeFragment(MainActivity.JOIN_FRAGMENT);
    }
}