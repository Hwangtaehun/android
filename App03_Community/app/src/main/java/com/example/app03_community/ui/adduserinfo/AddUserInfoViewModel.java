package com.example.app03_community.ui.adduserinfo;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.android.material.checkbox.MaterialCheckBox;

public class AddUserInfoViewModel extends ViewModel {
    public MutableLiveData<String> inputAddUserInfoNickname = new MutableLiveData<>();
    public MutableLiveData<String> inputAddUserInfoAge = new MutableLiveData<>();
    public MutableLiveData<Integer> checkBoxAddUserInfoAllState = new MutableLiveData<>();
    public MutableLiveData<Boolean> checkBoxAddUserInfoAll = new MutableLiveData<>();
    public MutableLiveData<Boolean> checkBoxAdduserInfo1 = new MutableLiveData<>();
    public MutableLiveData<Boolean> checkBoxAdduserInfo2 = new MutableLiveData<>();
    public MutableLiveData<Boolean> checkBoxAdduserInfo3 = new MutableLiveData<>();
    public MutableLiveData<Boolean> checkBoxAdduserInfo4 = new MutableLiveData<>();
    public MutableLiveData<Boolean> checkBoxAdduserInfo5 = new MutableLiveData<>();
    public MutableLiveData<Boolean> checkBoxAdduserInfo6 = new MutableLiveData<>();

    public void setCheckAll(boolean checked) {
        checkBoxAdduserInfo1.setValue(checked);
        checkBoxAdduserInfo2.setValue(checked);
        checkBoxAdduserInfo3.setValue(checked);
        checkBoxAdduserInfo4.setValue(checked);
        checkBoxAdduserInfo5.setValue(checked);
        checkBoxAdduserInfo6.setValue(checked);
    }

    public void onCheckBoxAllChanged() {
        boolean chk = checkBoxAddUserInfoAll.getValue();
        setCheckAll(chk);
    }

    public void onCheckBoxChanged() {
        int checkedCnt = 0;

        if(checkBoxAdduserInfo1.getValue() == true) {
            checkedCnt++;
        }
        if(checkBoxAdduserInfo2.getValue() == true) {
            checkedCnt++;
        }
        if(checkBoxAdduserInfo3.getValue() == true) {
            checkedCnt++;
        }
        if(checkBoxAdduserInfo4.getValue() == true) {
            checkedCnt++;
        }
        if(checkBoxAdduserInfo5.getValue() == true) {
            checkedCnt++;
        }
        if(checkBoxAdduserInfo6.getValue() == true) {
            checkedCnt++;
        }

        if(checkedCnt == 0) {
            checkBoxAddUserInfoAll.setValue(false);
            checkBoxAddUserInfoAllState.setValue(MaterialCheckBox.STATE_UNCHECKED);
        } else if(checkedCnt == 6) {
            checkBoxAddUserInfoAll.setValue(true);
            checkBoxAddUserInfoAllState.setValue(MaterialCheckBox.STATE_CHECKED);
        } else {
            checkBoxAddUserInfoAllState.setValue(MaterialCheckBox.STATE_INDETERMINATE);
        }
    }
}
