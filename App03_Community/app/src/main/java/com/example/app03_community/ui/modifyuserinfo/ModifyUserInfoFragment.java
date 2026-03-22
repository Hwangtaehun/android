package com.example.app03_community.ui.modifyuserinfo;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.app03_community.MainActivity;
import com.example.app03_community.R;
import com.example.app03_community.databinding.FragmentModifyUserInfoBinding;
import com.example.app03_community.ui.postmain.PostMainFragment;

public class ModifyUserInfoFragment extends Fragment {
    FragmentModifyUserInfoBinding fragmentModifyUserInfoBinding;
    MainActivity mainActivity;
    PostMainFragment postMainFragment;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fragmentModifyUserInfoBinding = FragmentModifyUserInfoBinding.inflate(inflater);
        mainActivity = (MainActivity) getActivity();
        postMainFragment = mainActivity.postMainFragment;

        setToolbar();

        // Inflate the layout for this fragment
        return fragmentModifyUserInfoBinding.getRoot();
    }

    public void setToolbar() {
        fragmentModifyUserInfoBinding.toolbarModifyUserInfo.setTitle("회원 정보 수정");
        fragmentModifyUserInfoBinding.toolbarModifyUserInfo.setNavigationIcon(R.drawable.menu_24px);
        fragmentModifyUserInfoBinding.toolbarModifyUserInfo.setNavigationOnClickListener(v -> {
            postMainFragment.postDrawerLayout.open();
        });
    }
}