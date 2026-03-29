package com.example.app03_community.ui.postmodify;

import android.content.ContentResolver;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.ImageDecoder;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.app03_community.MainActivity;
import com.example.app03_community.R;
import com.example.app03_community.databinding.FragmentPostModifyBinding;
import com.example.app03_community.ui.postmain.PostMainFragment;
import com.example.app03_community.ui.postwrite.PostWriteViewModel;

import java.io.File;

public class PostModifyFragment extends Fragment {
    FragmentPostModifyBinding fragmentPostModifyBinding;
    MainActivity mainActivity;
    PostMainFragment postMainFragment;
    Uri contentUri;
    PostModifyViewModel postModifyViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        // fragmentPostModifyBinding = FragmentPostModifyBinding.inflate(inflater);
        fragmentPostModifyBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_post_modify, container, false);
        postModifyViewModel = new PostModifyViewModel();
        fragmentPostModifyBinding.setPostModifyViewModel(postModifyViewModel);
        fragmentPostModifyBinding.setLifecycleOwner(this);

        mainActivity = (MainActivity) getActivity();
        postMainFragment = mainActivity.postMainFragment;

        setToolbar();
        setContent();

        return fragmentPostModifyBinding.getRoot();
    }

    public void setToolbar() {
        fragmentPostModifyBinding.toolbarPostModify.setTitle("글 수정");
        fragmentPostModifyBinding.toolbarPostModify.setNavigationIcon(R.drawable.arrow_back_24px);
        fragmentPostModifyBinding.toolbarPostModify.setNavigationOnClickListener(view -> {
            postMainFragment.removerFragment(PostMainFragment.POST_MODIFY_FRAGMENT);
        });

        fragmentPostModifyBinding.toolbarPostModify.inflateMenu(R.menu.post_modify_main_menu);
        fragmentPostModifyBinding.toolbarPostModify.setOnMenuItemClickListener(item -> {
            int itemId = item.getItemId();

            if (itemId == R.id.menuItemPostModifyCamera) {
                mainActivity.showCamera(this);
            } else if (itemId == R.id.menuItemPostModifyAlbum) {
                mainActivity.showAlbum(this);
            } else if (itemId == R.id.menuItemPostModifyDone) {
                processSubmit();
            }

            return true;
        });
    }

    public void setContent() {
//        fragmentPostModifyBinding.buttonGroupPostModifyType.check(R.id.buttonPostModifyType2);
//        fragmentPostModifyBinding.inputPostModifySubject.setText("제목입니다.");
//        fragmentPostModifyBinding.inputPostModifyText.setText("내용입니다.");

        postModifyViewModel.buttonGroupPostModifyType.setValue(R.id.buttonPostModifyType3);
        postModifyViewModel.inputPostModifySubject.setValue("테스트 제목입니다.");
        postModifyViewModel.inputPostModifyText.setValue("테스트 내용입니다.");

        fragmentPostModifyBinding.imageViewPostModify.setImageResource(R.mipmap.ic_launcher);
    }

    public void setPictureUri(Uri contentUri) {
        this.contentUri = contentUri;

        Bitmap bitmap = BitmapFactory.decodeFile(contentUri.getPath());
        int degree = mainActivity.getDegree(contentUri);
        Bitmap bitmap2 = mainActivity.rotateBitmap(bitmap, degree);
        Bitmap bitmap3 = mainActivity.resizeBitmap(1024, bitmap2);

        fragmentPostModifyBinding.imageViewPostModify.setImageBitmap(bitmap3);

        File file = new File(contentUri.getPath());
        file.delete();
    }

    public void setAlbumUri(Uri uri) {
        ContentResolver contentResolver = mainActivity.getContentResolver();

        try {
            if(uri != null) {
                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                    ImageDecoder.Source source = ImageDecoder.createSource(contentResolver, uri);
                    Bitmap bitmap = ImageDecoder.decodeBitmap(source);
                    fragmentPostModifyBinding.imageViewPostModify.setImageBitmap(bitmap);
                } else {
                    Cursor cursor = contentResolver.query(uri, null, null, null, null);

                    int index = cursor.getColumnIndex(MediaStore.Images.Media.DATA);
                    String source = cursor.getString(index);

                    Bitmap bitmap = BitmapFactory.decodeFile(source);
                    fragmentPostModifyBinding.imageViewPostModify.setImageBitmap(bitmap);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void processSubmit() {
        String inputPostModifySubject = postModifyViewModel.inputPostModifySubject.getValue();
        String inputPostModifyText = postModifyViewModel.inputPostModifyText.getValue();

        if(inputPostModifySubject == null || inputPostModifySubject.trim().length() == 0) {
            mainActivity.showAlertDialog("제목 입력 오류", "제목을 입력해주세요.", (dialog, which) -> {
                mainActivity.showSoftInput(fragmentPostModifyBinding.inputPostModifySubject);
            });
            return;
        }

        if(inputPostModifyText == null || inputPostModifyText.trim().length() == 0) {
            mainActivity.showAlertDialog("내용 입력 오류", "내용을 입력해주세요.", (dialog, which) -> {
                mainActivity.showSoftInput(fragmentPostModifyBinding.inputPostModifyText);
            });
            return;
        }

        mainActivity.hideSoftInput();
        postMainFragment.removerFragment(PostMainFragment.POST_MODIFY_FRAGMENT);
    }
}