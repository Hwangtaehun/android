package com.example.app03_community.ui.postwrite;

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
import com.example.app03_community.databinding.FragmentPostWriteBinding;
import com.example.app03_community.ui.postmain.PostMainFragment;

import java.io.File;

public class PostWriteFragment extends Fragment {
    FragmentPostWriteBinding fragmentPostWriteBinding;
    MainActivity mainActivity;
    PostMainFragment postMainFragment;
    Uri contentUri;

    PostWriteViewModel postWriteViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // fragmentPostWriteBinding = FragmentPostWriteBinding.inflate(inflater);
        fragmentPostWriteBinding = DataBindingUtil.inflate(inflater,
                R.layout.fragment_post_write,
                container,
                false);
        postWriteViewModel = new PostWriteViewModel();
        fragmentPostWriteBinding.setPostWriteViewModel(postWriteViewModel);
        fragmentPostWriteBinding.setLifecycleOwner(this);

        mainActivity = (MainActivity) getActivity();
        postMainFragment = mainActivity.postMainFragment;

        setToolbar();
        setContent();

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
                mainActivity.showCamera(this);
            } else if(itemId == R.id.menuItemPostWriteAlbum) {
                mainActivity.showAlbum(this);
            } else if(itemId == R.id.menuItemPostWriteDone) {
                postMainFragment.replaceFragment(PostMainFragment.POST_READ_FRAGMENT, true, true, null);
            }

            return true;
        });
    }

    public void setPictureUri(Uri contentUri) {
        this.contentUri = contentUri;

        Bitmap bitmap = BitmapFactory.decodeFile(contentUri.getPath());

        int degree = mainActivity.getDegree(contentUri);

        Bitmap bitmap2 = mainActivity.rotateBitmap(bitmap, degree);
        Bitmap bitmap3 = mainActivity.resizeBitmap(1024, bitmap2);

        fragmentPostWriteBinding.imageViewPostWrite.setImageBitmap(bitmap3);

        File file = new File(contentUri.getPath());
        file.delete();
    }

    public void  setAlbumUri(Uri uri) {
        ContentResolver contentResolver = mainActivity.getContentResolver();

        try {
            if(uri != null) {
                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                    ImageDecoder.Source source = ImageDecoder.createSource(contentResolver, uri);
                    Bitmap bitmap = ImageDecoder.decodeBitmap(source);
                    fragmentPostWriteBinding.imageViewPostWrite.setImageBitmap(bitmap);
                } else {
                    Cursor cursor = contentResolver.query(uri, null, null, null, null);

                    if(cursor != null) {
                      cursor.moveToNext();

                      int index = cursor.getColumnIndex(MediaStore.Images.Media.DATA);
                      String source = cursor.getString(index);

                      Bitmap bitmap = BitmapFactory.decodeFile(source);
                      fragmentPostWriteBinding.imageViewPostWrite.setImageBitmap(bitmap);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setContent() {
        postWriteViewModel.inputPostWriteSubject.setValue("");
        postWriteViewModel.inputPostWriteText.setValue("");
        postWriteViewModel.buttonGroupPostWriteType.setValue(R.id.buttonPostModifyType3);
    }
}