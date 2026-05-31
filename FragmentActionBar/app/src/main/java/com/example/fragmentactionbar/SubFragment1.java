package com.example.fragmentactionbar;

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
import android.widget.Button;

import com.example.fragmentactionbar.databinding.FragmentSub1Binding;

public class SubFragment1 extends Fragment {
    FragmentSub1Binding fragmentSub1Binding;
    MainActivity mainActivity;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mainActivity = (MainActivity) getActivity();
        // Inflate the layout for this fragment
        fragmentSub1Binding = FragmentSub1Binding.inflate(getLayoutInflater());

        // action bar에 메뉴가 활성화 되도록 한다.
        setHasOptionsMenu(true);

        // MainActivity의 ActionBar를 가져온다.
        ActionBar actionBar = mainActivity.getSupportActionBar();
        actionBar.setTitle("SubFragment1");

        Button1ClickListener listener1 = new Button1ClickListener();
        fragmentSub1Binding.button.setOnClickListener(listener1);

        return fragmentSub1Binding.getRoot();
    }

    class Button1ClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            mainActivity.setFragment(MainActivity.SUB_FRAGMENT2, true);
        }
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.sub1_menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();

        if(itemId == R.id.sub_item1) {
            fragmentSub1Binding.textView.setText("Sub1 Item1");
        } else if( itemId == R.id.sub_item2) {
            fragmentSub1Binding.textView.setText("Sub1 Item2");
        }

        return super.onOptionsItemSelected(item);
    }
}