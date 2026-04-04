package com.example.app03_community.ui.adduserinfo;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.app03_community.MainActivity;
import com.example.app03_community.R;
import com.example.app03_community.databinding.FragmentAddUserInfoBinding;
import com.example.app03_community.model.UserInfoModel;
import com.example.app03_community.repository.UserInfoRepository;
import com.google.android.material.checkbox.MaterialCheckBox;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.firestore.DocumentSnapshot;

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

        UserInfoModel userInfoModel = setUserData();

        UserInfoRepository.getUserInfoSequence(o -> {
            DocumentSnapshot documentSnapshot = (DocumentSnapshot) o;
            int userSequence = documentSnapshot.getLong("value").intValue();
            userSequence++;
            final int tempUserSequence = userSequence;
            UserInfoRepository.setUserInfoSequence(userSequence, o1 -> {
                userInfoModel.setUserIdx(tempUserSequence);
                UserInfoRepository.addUserInfo(userInfoModel, o2 -> {
                    Snackbar snackbar = Snackbar.make(fragmentAddUserInfoBinding.buttonAddUserInfoSubmit,
                                                "가입이 완료되었습니다.", Snackbar.LENGTH_SHORT);
                    snackbar.show();
                    mainActivity.removeFragment(MainActivity.JOIN_FRAGMENT);
                });
            });
        });

        // mainActivity.removeFragment(MainActivity.JOIN_FRAGMENT);
    }

    public UserInfoModel setUserData() {
        Bundle dataBundle = getArguments();

        String userId = dataBundle.getString("inputJoinUserId");
        String userPw = dataBundle.getString("inputJoinUserPw");
        String nickName = addUserInfoViewModel.inputAddUserInfoNickname.getValue();
        int age = Integer.parseInt(addUserInfoViewModel.inputAddUserInfoAge.getValue());
        boolean hobby1 = addUserInfoViewModel.checkBoxAdduserInfo1.getValue();
        boolean hobby2 = addUserInfoViewModel.checkBoxAdduserInfo2.getValue();
        boolean hobby3 = addUserInfoViewModel.checkBoxAdduserInfo3.getValue();
        boolean hobby4 = addUserInfoViewModel.checkBoxAdduserInfo4.getValue();
        boolean hobby5 = addUserInfoViewModel.checkBoxAdduserInfo5.getValue();
        boolean hobby6 = addUserInfoViewModel.checkBoxAdduserInfo6.getValue();

//        Log.d("test1234", "userId : " + userId);
//        Log.d("test1234", "userPw : " + userPw);
//        Log.d("test1234", "nickName : " + nickName);
//        Log.d("test1234", "age : " + age);
//        Log.d("test1234", "hobby1 : " + hobby1);
//        Log.d("test1234", "hobby2 : " + hobby2);
//        Log.d("test1234", "hobby3 : " + hobby3);
//        Log.d("test1234", "hobby4 : " + hobby4);
//        Log.d("test1234", "hobby5 : " + hobby5);
//        Log.d("test1234", "hobby6 : " + hobby6);

        UserInfoModel userInfoModel = new UserInfoModel();
        userInfoModel.setUserId(userId);
        userInfoModel.setUserPw(userPw);
        userInfoModel.setNickName(nickName);
        userInfoModel.setAge(age);
        userInfoModel.setHobby1(hobby1);
        userInfoModel.setHobby2(hobby2);
        userInfoModel.setHobby3(hobby3);
        userInfoModel.setHobby4(hobby4);
        userInfoModel.setHobby5(hobby5);
        userInfoModel.setHobby6(hobby6);

        return userInfoModel;
    }
}