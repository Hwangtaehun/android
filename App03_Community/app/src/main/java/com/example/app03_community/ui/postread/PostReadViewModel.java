package com.example.app03_community.ui.postread;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class PostReadViewModel extends ViewModel {
    public MutableLiveData<String> inputPostReadType = new MutableLiveData<>();
    public MutableLiveData<String> inputPostReadSubject = new MutableLiveData<>();
    public MutableLiveData<String> inputPostReadNickname = new MutableLiveData<>();
    public MutableLiveData<String> inputPostReadData = new MutableLiveData<>();
    public MutableLiveData<String> inputPostReadText = new MutableLiveData<>();
}
