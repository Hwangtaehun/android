package com.example.app03_community.ui.postmodify;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.app03_community.MainActivity;
import com.example.app03_community.R;
import com.example.app03_community.databinding.FragmentPostModifyBinding;
import com.example.app03_community.ui.postmain.PostMainFragment;

public class PostModifyFragment extends Fragment {
    FragmentPostModifyBinding fragmentPostModifyBinding;
    MainActivity mainActivity;
    PostMainFragment postMainFragment;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        fragmentPostModifyBinding = FragmentPostModifyBinding.inflate(inflater);
        mainActivity = (MainActivity) getActivity();
        postMainFragment = mainActivity.postMainFragment;

        setToolbar();
        setContent();

        return fragmentPostModifyBinding.getRoot();
    }

    public void setToolbar() {
        fragmentPostModifyBinding.toolbarPostModify.setTitle("글 수정");
        fragmentPostModifyBinding.toolbarPostModify.setNavigationIcon(R.drawable.arrow_back_24px);
        fragmentPostModifyBinding.toolbarPostModify.setNavigationOnClickListener(view -> {
            postMainFragment.removerFragment(PostMainFragment.POST_MODIFY_FRAGMENT);
        });

        fragmentPostModifyBinding.toolbarPostModify.inflateMenu(R.menu.post_modify_main_menu);
    }

    public void setContent() {
        fragmentPostModifyBinding.buttonGroupPostModifyType.check(R.id.buttonPostModifyType2);
        fragmentPostModifyBinding.inputPostModifySubject.setText("제목입니다.");
        fragmentPostModifyBinding.inputPostModifyText.setText("내용입니다.");
        fragmentPostModifyBinding.imageViewPostModify.setImageResource(R.mipmap.ic_launcher);
    }
}