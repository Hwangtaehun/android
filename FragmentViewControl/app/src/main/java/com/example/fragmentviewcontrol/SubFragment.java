package com.example.fragmentviewcontrol;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.fragmentviewcontrol.databinding.FragmentSubBinding;

public class SubFragment extends Fragment {
    FragmentSubBinding fragmentSubBinding;

    // TODO: Rename and change types and number of parameters
    public static SubFragment newInstance(int data1, String data2) {
        SubFragment fragment = new SubFragment();
        Bundle args = new Bundle();
        // 매개변수로 받은 데이터를 Bundle 객체에 담는다.
        args.putInt("data1", data1);
        args.putString("data2", data2);

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        fragmentSubBinding = FragmentSubBinding.inflate(inflater);

        // 번들 객체를 추출한다.
        Bundle args = getArguments();
        // 번들에 저장되어 있는 데이터를 추출한다.
        int data100 = args.getInt("data1");
        String data200 = args.getString("data2");
        // View에 반영한다.
        fragmentSubBinding.fragmentTextView.setText("data1 : " + data100 + "\n");
        fragmentSubBinding.fragmentTextView.append("data2 : " + data200);

        // Fragment를 소유하고 있는 Activity를 추출한다.
        MainActivity mainActivity = (MainActivity) getActivity();
        mainActivity.activityMainBinding.activityTextView.setText("value1 : " + 200 + "\n");
        mainActivity.activityMainBinding.activityTextView.append("value2 : 문자열2");

        return fragmentSubBinding.getRoot();
    }
}