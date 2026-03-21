package com.example.app03_community.ui.postwrite;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.app03_community.MainActivity;
import com.example.app03_community.R;
import com.example.app03_community.databinding.FragmentPostWriteBinding;
import com.example.app03_community.ui.postmain.PostMainFragment;

public class PostWriteFragment extends Fragment {
    FragmentPostWriteBinding fragmentPostWriteBinding;
    MainActivity mainActivity;
    PostMainFragment postMainFragment;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fragmentPostWriteBinding = FragmentPostWriteBinding.inflate(inflater);
        mainActivity = (MainActivity) getActivity();
        postMainFragment = mainActivity.postMainFragment;

        setToolbar();

        return fragmentPostWriteBinding.getRoot();
    }

    public void setToolbar() {
        fragmentPostWriteBinding.toolbarPostWrite.setTitle("글 작성");
        fragmentPostWriteBinding.toolbarPostWrite.setNavigationIcon(R.drawable.arrow_back_24px);
        fragmentPostWriteBinding.toolbarPostWrite.setNavigationOnClickListener(v -> {
            postMainFragment.removerFragment(PostMainFragment.POST_WRITE_FRAGMENT);
        });

        fragmentPostWriteBinding.toolbarPostWrite.inflateMenu(R.menu.post_write_main_menu);
        fragmentPostWriteBinding.toolbarPostWrite.setOnMenuItemClickListener(item -> {
            int itemId = item.getItemId();

            if(itemId == R.id.menuItemPostWriteCamera) {

            } else if(itemId == R.id.menuItemPostWriteAlbum) {

            } else if(itemId == R.id.menuItemPostWriteDone) {
                postMainFragment.replaceFragment(PostMainFragment.POST_READ_FRAGMENT, true, true, null);
            }

            return true;
        });
    }
}