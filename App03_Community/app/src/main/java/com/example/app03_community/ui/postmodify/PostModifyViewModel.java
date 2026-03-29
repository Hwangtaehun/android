package com.example.app03_community.ui.postmodify;

import androidx.databinding.BindingAdapter;
import androidx.databinding.InverseBindingAdapter;
import androidx.databinding.InverseBindingListener;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.android.material.button.MaterialButtonToggleGroup;

public class PostModifyViewModel extends ViewModel {
    public MutableLiveData<String> inputPostModifySubject = new MutableLiveData<>();
    public MutableLiveData<String> inputPostModifyText = new MutableLiveData<>();
    public MutableLiveData<Integer> buttonGroupPostModifyType = new MutableLiveData<>();

    @BindingAdapter("android:checkButtonId")
    public static void setCheckedButtonId(MaterialButtonToggleGroup group, int buttonId) {
        if(group.getCheckedButtonId() != buttonId) {
            group.check(buttonId);
        }
    }

    @BindingAdapter("checkedButtonChangeListener")
    public static void checkedButtonChangeListener(MaterialButtonToggleGroup group, InverseBindingListener inverseBindingListener) {
        group.addOnButtonCheckedListener((group1, checkedId, isChecked) -> {
            inverseBindingListener.onChange();
        });
    }

    @InverseBindingAdapter(attribute = "android:checkButtonId", event = "checkedButtonChangeListener")
    public static int getCheckedButtonId(MaterialButtonToggleGroup group) {
        return group.getCheckedButtonId();
    }
}
