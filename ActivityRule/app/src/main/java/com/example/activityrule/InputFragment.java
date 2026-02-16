package com.example.activityrule;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.activityrule.databinding.FragmentInputBinding;

public class InputFragment extends Fragment {
    FragmentInputBinding fragmentInputBinding;
    MainActivity mainActivity;

    // TODO: Rename and change types and number of parameters
//    public static InputFragment newInstance() {
//        InputFragment fragment = new InputFragment();
//        Bundle args = new Bundle();
//        fragment.setArguments(args);
//        return fragment;
//    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mainActivity = (MainActivity) getActivity();
        fragmentInputBinding = FragmentInputBinding.inflate(inflater);

        InputButtonClickListener listener1 = new InputButtonClickListener();
        fragmentInputBinding.button.setOnClickListener(listener1);

        return fragmentInputBinding.getRoot();
    }

    class InputButtonClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            // 사용자가 입력한 내용을 추출한다.
            String str1 = fragmentInputBinding.editText.getText().toString();
            String str2 = fragmentInputBinding.editText2.getText().toString();

            // MainActivity의 변수에 담아준다.
            mainActivity.edit1Value = str1;
            mainActivity.edit2Value = str2;

            // ResultFragment로 교체한디.
            mainActivity.setFragment(MainActivity.RESULT_FRAGMENT, true);
        }
    }
}