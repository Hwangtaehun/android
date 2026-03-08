package com.example.mvvm.viewmodel;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.mvvm.model.TestModel;
import com.example.mvvm.repository.TestRepository;

import java.util.ArrayList;

public class TestViewModel extends ViewModel {
    public MutableLiveData<ArrayList<TestModel>> dataList = new MutableLiveData<>();

    public TestViewModel(){
        ArrayList<TestModel> a1 = new ArrayList<>();
        dataList.setValue(a1);
    }

    public void getAllData(Context context){
        ArrayList<TestModel> a1 = TestRepository.getDataAll(context);
        dataList.setValue(a1);
    }
}
