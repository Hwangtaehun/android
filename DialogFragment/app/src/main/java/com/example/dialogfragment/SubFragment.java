package com.example.dialogfragment;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class SubFragment extends DialogFragment {
    MainActivity mainActivity;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        mainActivity = (MainActivity) getActivity();

        // AlertDialog를 구성한다.
        AlertDialog.Builder builder1 = new AlertDialog.Builder(mainActivity);
        builder1.setTitle("타이틀 입니다.");
        builder1.setMessage("메시지 입니다.");

        // 버튼
        DialogButtonClickListener listener1 = new DialogButtonClickListener();
        builder1.setPositiveButton("Positive", listener1);
        builder1.setNeutralButton("Neutral", listener1);
        builder1.setNegativeButton("Negative", listener1);

        // AlertDialog를 추출한다.
        AlertDialog alertDialog = builder1.create();

        // AlertDialog를 반환한다.
        return alertDialog;
    }

    // 다이얼로그 버튼의 리스너
    class DialogButtonClickListener implements DialogInterface.OnClickListener {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            switch (which) {
                case DialogInterface.BUTTON_POSITIVE:
                    mainActivity.activityMainBinding.textView.setText("positive");
                    break;
                case DialogInterface.BUTTON_NEUTRAL:
                    mainActivity.activityMainBinding.textView.setText("Neutral");
                    break;
                case DialogInterface.BUTTON_NEGATIVE:
                    mainActivity.activityMainBinding.textView.setText("Negative");
                    break;
            }
        }
    }
}