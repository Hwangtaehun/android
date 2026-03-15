package com.example.app03_community.ui.postlist;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.app03_community.MainActivity;
import com.example.app03_community.R;
import com.example.app03_community.databinding.FragmentPostListBinding;

public class PostListFragment extends Fragment {

    FragmentPostListBinding fragmentPostListBinding;
    MainActivity mainActivity;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        fragmentPostListBinding = FragmentPostListBinding.inflate(inflater);
        mainActivity = (MainActivity) getActivity();

        setToolbar();

        return fragmentPostListBinding.getRoot();
    }

    public void setToolbar(){
        fragmentPostListBinding.toolbarPostList.setTitle("전체 게시판");
    }
}