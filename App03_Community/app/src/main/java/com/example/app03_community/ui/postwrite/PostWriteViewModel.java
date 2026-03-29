package com.example.app03_community.ui.postwrite;

import androidx.databinding.BindingAdapter;
import androidx.databinding.InverseBindingAdapter;
import androidx.databinding.InverseBindingListener;
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
    public static void setCheckedButtonId(MaterialButtonToggleGroup group, int buttonId){
        if(group.getCheckedButtonId() != buttonId) {
            group.check(buttonId);
        }
    }

    // 화면 요소에 새로운 값이 설정되면 ViewModel의 변수에 값이 설정될 때 호출된다.
    @BindingAdapter("checkedButtonChangeListener")
    public static void checkedButtonChangeListener(MaterialButtonToggleGroup group, InverseBindingListener inverseBindingListener) {
        group.addOnButtonCheckedListener((group1, checkedId, isChecked) -> {
            inverseBindingListener.onChange();
        });
    }

    // 역방향 바인딩이 벌어질 때 호출된다ㅏ.
    @InverseBindingAdapter(attribute = "android:checkedButtonId", event = "checkedButtonChangeListener")
    public static int getCheckedButtonId(MaterialButtonToggleGroup group) {
        return group.getCheckedButtonId();
    }


}
