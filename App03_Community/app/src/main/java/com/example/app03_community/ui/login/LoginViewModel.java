package com.example.app03_community.ui.login;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class LoginViewModel extends ViewModel {
    public MutableLiveData<String> inputLoginUserId = new MutableLiveData<>();
    public MutableLiveData<String> inputLoginUserPw = new MutableLiveData<>();
    public MutableLiveData<Boolean> checkBoxLoginAuto =  new MutableLiveData<>();


}
