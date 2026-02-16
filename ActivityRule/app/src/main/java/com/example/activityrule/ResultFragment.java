package com.example.activityrule;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.activityrule.databinding.FragmentResultBinding;

public class ResultFragment extends Fragment {
    FragmentResultBinding fragmentResultBinding;
    MainActivity mainActivity;

    // TODO: Rename and change types and number of parameters
//    public static ResultFragment newInstance(String value1, String value2) {
//        ResultFragment fragment = new ResultFragment();
//        Bundle args = new Bundle();
//        args.putString("value1", value1);
//        args.putString("value2", value2);
//
//        fragment.setArguments(args);
//        return fragment;
//    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mainActivity = (MainActivity) getActivity();
        fragmentResultBinding = FragmentResultBinding.inflate(inflater);

        // MainActivity의 변수에 저장되어 있는 값을 TextView에 설정한다.
        fragmentResultBinding.textView.setText(mainActivity.edit1Value);
        fragmentResultBinding.textView2.setText(mainActivity.edit2Value);

//        Bundle args = getArguments();
//        String str1 = args.getString("value1");
//        String str2 = args.getString("value2");
//
//        fragmentResultBinding.textView.setText(str1);
//        fragmentResultBinding.textView2.setText(str2);

        return fragmentResultBinding.getRoot();
    }
}