package com.example.listfragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.ListFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.listfragment.databinding.FragmentSubBinding;

public class SubFragment extends ListFragment {
    FragmentSubBinding fragmentSubBinding;
    MainActivity mainActivity;

    String [] data1 = {
            "항목1", "항목2", "항목3", "항목4", "항목5"
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        fragmentSubBinding = FragmentSubBinding.inflate(inflater);
        mainActivity = (MainActivity) getActivity();

        // 어뎁터를 생성한다.
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                mainActivity, android.R.layout.simple_list_item_1, data1
        );

        // 어뎁터를 셋팅한다.
        setListAdapter(adapter);

        return fragmentSubBinding.getRoot();
    }

    // 항목을 터치하면 호출되는 메서드
    @Override
    public void onListItemClick(@NonNull ListView l, @NonNull View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        fragmentSubBinding.textView.setText(data1[position]);
    }
}