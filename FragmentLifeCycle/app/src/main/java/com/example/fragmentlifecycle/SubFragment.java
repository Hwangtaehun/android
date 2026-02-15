package com.example.fragmentlifecycle;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class SubFragment extends Fragment {
    // TODO: Rename and change types and number of parameters
    public static SubFragment newInstance() {
        SubFragment fragment = new SubFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    // Fragment가 생성될 때 호출
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("test", "OnCreate");
    }

    // Fragment를 통해 보여질 View를 생성하기 위해 호출되는 메서드
    // 메서드가 반환하는 View를 Fragment를 통해 보여준다.
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Log.d("test", "OnCreateView");
        return inflater.inflate(R.layout.fragment_sub, container, false);
    }

    // Fragment를 통해 보여줄 View가 생성되면 호출
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.d("test", "onViewCteated");
    }

    // 화면을 복원하기 위해 호출되는 메서드
    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        Log.d("test", "onViewStateRestored");
    }

    // Fragment가 실행될 때
    @Override
    public void onStart() {
        super.onStart();
        Log.d("test", "onStart");
    }

    // Fragment가 보여지고 나서 호출 된다.
    @Override
    public void onResume() {
        super.onResume();
        Log.d("test", "onResume");
    }

    // Fragment가 사라질 때 호출
    @Override
    public void onPause() {
        super.onPause();
        Log.d("test", "onPause");
    }

    // Fragment가 정지 될 때
    @Override
    public void onStop() {
        super.onStop();
        Log.d("test", "onStop");
    }

    // Fragment 복원을 위해 필요한 데이터를 저장하기 위한 메서드
    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.d("test", "onSaveInstanceState");
    }

    // Fragment가 보여줄 View가 소멸 될때
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.d("test", "onDestoryView");
    }

    // Fragment가 제거될 때
    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("test", "onDestroy");
    }
}