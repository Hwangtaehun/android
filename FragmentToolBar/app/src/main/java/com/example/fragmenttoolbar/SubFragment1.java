package com.example.fragmenttoolbar;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.example.fragmenttoolbar.databinding.FragmentSub1Binding;

public class SubFragment1 extends Fragment {

    FragmentSub1Binding fragmentSub1Binding;
    MainActivity mainActivity;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mainActivity = (MainActivity) getActivity();
        fragmentSub1Binding = FragmentSub1Binding.inflate(inflater);

        // Fragment에 배치한 toolbar를 액션바로 설정한다.
        mainActivity.setSupportActionBar(fragmentSub1Binding.toolbar);
        ActionBar actionBar = mainActivity.getSupportActionBar();
        actionBar.setTitle("SubFragment 1");

        // 메뉴를 보이게 설정한다.
        setHasOptionsMenu(true);

        Button1ClickListener button1ClickListener = new Button1ClickListener();
        fragmentSub1Binding.button.setOnClickListener(button1ClickListener);

        return fragmentSub1Binding.getRoot();
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.sub1_menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int itemid = item.getItemId();

        if(itemid == R.id.sub1_item1) {
            fragmentSub1Binding.textView.setText("Sub1 Item1");
        } else if(itemid == R.id.sub1_item2) {
            fragmentSub1Binding.textView.setText("Sub1 Item2");
        }

        return super.onOptionsItemSelected(item);
    }

    class Button1ClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            mainActivity.setFragment(MainActivity.SUB_FRAGMENT2, true);
        }
    }
}