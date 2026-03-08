package com.example.app03_community.ui.join;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.app03_community.MainActivity;
import com.example.app03_community.R;
import com.example.app03_community.databinding.FragmentJoinBinding;

public class JoinFragment extends Fragment {
    FragmentJoinBinding fragmentJoinBinding;
    MainActivity mainActivity;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        fragmentJoinBinding = FragmentJoinBinding.inflate(inflater);
        mainActivity = (MainActivity) getActivity();

        setToolbar();
        setButton();

        return fragmentJoinBinding.getRoot();
    }

    public void setToolbar() {
        fragmentJoinBinding.toolbarJoin.setTitle("회원가입");
        fragmentJoinBinding.toolbarJoin.setNavigationIcon(R.drawable.arrow_back_24px);
        fragmentJoinBinding.toolbarJoin.setNavigationOnClickListener(v -> {
            mainActivity.removeFragment(MainActivity.JOIN_FRAGMENT);
        });
    }

    private void setButton() {
        fragmentJoinBinding.buttonJoinNext.setOnClickListener(v -> {
            mainActivity.replaceFragment(MainActivity.ADD_USER_INFO_FRAGMENT, true, true, null);
        });
    }
}