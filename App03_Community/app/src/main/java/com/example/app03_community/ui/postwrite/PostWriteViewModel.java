package com.example.app03_community.ui.postwrite;

import androidx.databinding.BindingAdapter;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.android.material.button.MaterialButtonToggleGroup;

public class PostWriteViewModel extends ViewModel {
    public MutableLiveData<String> inputPostWriteSubject = new MutableLiveData<>();
    public MutableLiveData<String> inputPostWriteText = new MutableLiveData<>();
    public MutableLiveData<Integer> buttonGroupPostWriteType = new MutableLiveData<>();

    // ViewModel에 값을 설정하여 화면에 반영하는 작업을 할 때 호출된다.
    // 매개변수: 값이 설정된 View 객체, ViewModel을 통해 설정되는 값
    @BindingAdapter("android:checkedButtonId")
    public static void setCheckedButtonId(MaterialButtonToggleGroup group, int buttonId){ {
        group.check(buttonId);
    }
}
