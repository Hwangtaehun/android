package com.example.fragmentactionbar;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.example.fragmentactionbar.databinding.FragmentSub2Binding;

public class SubFragment2 extends Fragment {
    FragmentSub2Binding fragmentSub2Binding;
    MainActivity mainActivity;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mainActivity = (MainActivity) getActivity();
        fragmentSub2Binding = FragmentSub2Binding.inflate(getLayoutInflater());

        setHasOptionsMenu(true);
        ActionBar actionBar = mainActivity.getSupportActionBar();
        actionBar.setTitle("SubFragment2");

        // Back Button
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);

        // Inflate the layout for this fragment
        return fragmentSub2Binding.getRoot();
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.sub2_menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();

        if(itemId == R.id.sub2_item1) {
            fragmentSub2Binding.textView2.setText("Sub2 Item1");
        } else if( itemId == R.id.sub2_item2) {
            fragmentSub2Binding.textView2.setText("Sub2 Item2");
        } else if (itemId == android.R.id.home) {
            FragmentManager fragmentManager = mainActivity.getSupportFragmentManager();
            fragmentManager.popBackStack();
        }

        return super.onOptionsItemSelected(item);
    }
}