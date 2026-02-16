package com.example.fragmentanimation;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.fragmentanimation.databinding.FragmentFirstBinding;

public class FirstFragment extends Fragment {

    FragmentFirstBinding fragmentFirstBinding;
    MainActivity mainActivity;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        fragmentFirstBinding = FragmentFirstBinding.inflate(inflater);
        mainActivity = (MainActivity) getActivity();

        Button1ClickListener listener1 = new Button1ClickListener();
        fragmentFirstBinding.button.setOnClickListener(listener1);

        return fragmentFirstBinding.getRoot();
    }

    class Button1ClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            mainActivity.setFragment(MainActivity.SECOND_FRAGMENT, true);
        }
    }
}