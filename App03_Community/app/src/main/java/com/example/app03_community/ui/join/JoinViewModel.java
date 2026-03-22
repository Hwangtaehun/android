package com.example.app03_community.ui.join;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class JoinViewModel extends ViewModel {
    public MutableLiveData<String> inputJoinUserId = new MutableLiveData<>();
    public MutableLiveData<String> inputJoinUSerPw = new MutableLiveData<>();
    public MutableLiveData<String> inputJoinUSerPw2 = new MutableLiveData<>();

}
