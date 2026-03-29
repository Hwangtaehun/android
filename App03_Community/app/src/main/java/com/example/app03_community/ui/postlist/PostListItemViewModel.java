package com.example.app03_community.ui.postlist;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class PostListItemViewModel extends ViewModel {
    public MutableLiveData<String> textViewRowPostListSubject = new MutableLiveData<>();
    public MutableLiveData<String> textViewRowPostListNickName = new MutableLiveData<>();
}
