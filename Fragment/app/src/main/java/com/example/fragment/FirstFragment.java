package com.example.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.fragment.databinding.FragmentFirstBinding;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FirstFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FirstFragment extends Fragment {

    FragmentFirstBinding fragmentFirstBinding;

    // TODO: Rename and change types and number of parameters
    public static FirstFragment newInstance() {
        FirstFragment fragment = new FirstFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    // 프래그먼트가 생성될 때 호출되는 메서드
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    // 프래그먼트가 생성되고 프래그먼트를 통해 보여줄 View를 생성하기 위해 호출되는 메서드
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        fragmentFirstBinding = FragmentFirstBinding.inflate(inflater);
        return fragmentFirstBinding.getRoot();
    }
}