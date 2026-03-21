package com.example.app03_community.ui.postread;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.app03_community.MainActivity;
import com.example.app03_community.R;
import com.example.app03_community.databinding.FragmentPostReadBinding;
import com.example.app03_community.ui.postmain.PostMainFragment;

public class PostReadFragment extends Fragment {
    FragmentPostReadBinding fragmentPostReadBinding;
    MainActivity mainActivity;
    PostMainFragment postMainFragment;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        fragmentPostReadBinding = FragmentPostReadBinding.inflate(inflater);
        mainActivity = (MainActivity) getActivity();
        postMainFragment = mainActivity.postMainFragment;

        setToolbar();
        setContent();

        return fragmentPostReadBinding.getRoot();
    }

    public void setToolbar() {
        fragmentPostReadBinding.toolbarPostRead.setTitle("글 읽기");
        fragmentPostReadBinding.toolbarPostRead.setNavigationIcon(R.drawable.arrow_back_24px);
        fragmentPostReadBinding.toolbarPostRead.setNavigationOnClickListener(v -> {
            postMainFragment.removerFragment(PostMainFragment.POST_WRITE_FRAGMENT);
            postMainFragment.removerFragment(PostMainFragment.POST_READ_FRAGMENT);
        });

        fragmentPostReadBinding.toolbarPostRead.inflateMenu(R.menu.post_read_main_menu);
    }

    public void setContent() {
        fragmentPostReadBinding.inputPostReadType.setText("자유게시판");
        fragmentPostReadBinding.inputPostReadSubject.setText("제목입니다.");
        fragmentPostReadBinding.inputPostReadNickname.setText("홍길동");
        fragmentPostReadBinding.inputPostReadData.setText("2000-01-01");
        fragmentPostReadBinding.inputPostReadText.setText("내용입니다.");
        fragmentPostReadBinding.imageViewPostRead.setImageResource(R.mipmap.ic_launcher);
    }
}