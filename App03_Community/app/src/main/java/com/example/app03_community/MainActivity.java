package com.example.app03_community;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.content.ContentProvider;
import android.content.ContentResolver;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.SystemClock;
import android.provider.MediaStore;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.view.Window;
import android.view.animation.AnticipateInterpolator;
import android.view.inputmethod.InputMethodManager;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;
import androidx.core.graphics.Insets;
import androidx.core.splashscreen.SplashScreen;
import androidx.core.splashscreen.SplashScreenViewProvider;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.app03_community.databinding.ActivityMainBinding;
import com.example.app03_community.ui.adduserinfo.AddUserInfoFragment;
import com.example.app03_community.ui.join.JoinFragment;
import com.example.app03_community.ui.login.LoginFragment;
import com.example.app03_community.ui.postmain.PostMainFragment;
import com.example.app03_community.ui.postwrite.PostWriteFragment;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.transition.MaterialSharedAxis;

import java.io.File;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding activityMainBinding;

    Fragment newFragment, oldFragment;

    public PostMainFragment postMainFragment;

    public static final String LOGIN_FRAGMENT = "LoginFragment";
    public static final String JOIN_FRAGMENT = "JoinFragment";
    public static final String ADD_USER_INFO_FRAGMENT = "AddUserInfoFragment";
    public static final String POST_MAIN_FRAGMENT = "PostMainFragment";

    ActivityResultLauncher<Intent> cameraLaunchar;
    String filePath;
    Uri contentUri;
    Fragment pictureFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // 기본
        // SplashScreen.installSplashScreen(this);

//        // 사라질때의 애니메이션 적용
//        SplashScreen splashScreen = SplashScreen.installSplashScreen(this);
//        splashScreen.setOnExitAnimationListener(splashScreenViewProvider -> {
//            // 가로 비율 애니메이션
//            PropertyValuesHolder scaleX = PropertyValuesHolder.ofFloat(View.SCALE_X, 1f, 2f, 1f, 0f);
//            // 세로 비율 애니메이션
//            PropertyValuesHolder scaleY = PropertyValuesHolder.ofFloat(View.SCALE_Y, 1f, 2f, 1f, 0f);
//            // 투명도
//            PropertyValuesHolder alpha = PropertyValuesHolder.ofFloat(View.ALPHA, 1f, 1f, 0,5f, 0f);
//
//            // SplashScreen의 아이콘 View를 추출한다.
//            View iconView = splashScreenViewProvider.getIconView();
//
//            // 애니메이션 관리 객체를 생성한다.
//            // 첫 번째 뷰: 애니메이션을 적용할 뷰
//            // 나머지는 적용할 애니메이션 종류
//            ObjectAnimator objectAnimator = ObjectAnimator.ofPropertyValuesHolder(iconView, scaleX, scaleY, alpha);
//            // 애니메이션 적용을 위한 수학적 계산 방식
//            objectAnimator.setInterpolator(new AnticipateInterpolator());
//            // 애니메이션 동작 시간
//            objectAnimator.setDuration(1000);
//            // 애니메이션이 끝났을 때 동작할 리스너
//            objectAnimator.addListener(new AnimatorListenerAdapter() {
//                @Override
//                public void onAnimationEnd(Animator animation) {
//                    super.onAnimationEnd(animation);
//                    // SplashScreen을 제거한다.
//                    splashScreenViewProvider.remove();
//                }
//            });
//
//            // 애니메이션 가동
//            objectAnimator.start();
//        });
//
//        SystemClock.sleep(1000);

        // gif 이미지 사용
        SplashScreen.installSplashScreen(this);
        SystemClock.sleep(4000);

        EdgeToEdge.enable(this);
        activityMainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(activityMainBinding.getRoot());
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        filePath = getExternalFilesDir(null).toString();

        CameraActivityCallback cameraActivityCallback = new CameraActivityCallback();
        ActivityResultContracts.StartActivityForResult cameraContract = new ActivityResultContracts.StartActivityForResult();
        cameraLaunchar = registerForActivityResult(cameraContract, cameraActivityCallback);

        replaceFragment(LOGIN_FRAGMENT, false, false, null);
    }

//    class SplashScreenListener implements SplashScreen.OnExitAnimationListener {
//        @Override
//        public void onSplashScreenExit(@NonNull SplashScreenViewProvider splashScreenViewProvider) {
//
//        }
//    }

    public void replaceFragment(String name, boolean addToBackStack, boolean animate, Bundle bundle) {
        SystemClock.sleep(200);

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        if(newFragment != null) {
            oldFragment = newFragment;
        }

        if(name == LOGIN_FRAGMENT) {
            newFragment = new LoginFragment();
        } else if(name == JOIN_FRAGMENT) {
            newFragment = new JoinFragment();
        } else if(name == ADD_USER_INFO_FRAGMENT) {
            newFragment = new AddUserInfoFragment();
        } else if(name == POST_MAIN_FRAGMENT) {
            postMainFragment = new PostMainFragment();
            newFragment = postMainFragment;
        }

        if(newFragment != null){
            if(animate == true) {
                // oldFragment -> newFragment
                // oldFragment : exit
                // newFragment : enter

                // newFragment -> oldeFragment
                // oldFragment : reenter
                // newFragment : return
                if(oldFragment != null) {
                    oldFragment.setExitTransition(new MaterialSharedAxis(MaterialSharedAxis.X, true));
                    oldFragment.setReenterTransition(new MaterialSharedAxis(MaterialSharedAxis.X, false));
                    oldFragment.setEnterTransition(null);
                    oldFragment.setReturnTransition(null);
                }

                newFragment.setExitTransition(null);
                newFragment.setReenterTransition(null);
                newFragment.setEnterTransition(new MaterialSharedAxis(MaterialSharedAxis.X, true));
                newFragment.setReturnTransition(new MaterialSharedAxis(MaterialSharedAxis.X, false));
            }
            fragmentTransaction.replace(R.id.mainContainer, newFragment);

            if(addToBackStack == true) {
                fragmentTransaction.addToBackStack(name);
            }

            fragmentTransaction.commit();
        }
    }

    public void removeFragment(String name){
        SystemClock.sleep(200);
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.popBackStack(name, FragmentManager.POP_BACK_STACK_INCLUSIVE);
    }

    public int dpToPixel(int dp) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);

        int p1 = Math.round((float) dp * displayMetrics.density);
        return p1;
    }

    public void showSoftInput(View view) {
        view.requestFocus();
        new Thread(() -> {
            SystemClock.sleep(200);
            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            inputMethodManager.showSoftInput(view, 0);
        }).start();
    }

    public void hideSoftInput() {
        Window window = getWindow();
        View view = window.getCurrentFocus();
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    public void showAlertDialog(String title, String message, DialogInterface.OnClickListener listener) {
        MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(this);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.setIcon(R.drawable.warning_24px);
        builder.setPositiveButton("확인", listener);
        builder.show();
    }

    public void showCamera(Fragment fragment) {
        pictureFragment = fragment;
        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        long now = System.currentTimeMillis();
        String file_name = "/temp_" + now + ".jpg";
        String pic_path = filePath + "/" + file_name;
        File file = new File(pic_path);

        contentUri = FileProvider.getUriForFile(this, "com.example.app03_community.file_provider", file);

        if(contentUri != null) {
            cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, contentUri);
            cameraLaunchar.launch(cameraIntent);
        }
    }

    class CameraActivityCallback implements ActivityResultCallback<ActivityResult> {
        @Override
        public void onActivityResult(ActivityResult o) {
            int resultCode = o.getResultCode();

            if(resultCode == RESULT_OK) {
                if(pictureFragment.getClass() == PostWriteFragment.class) {
                    PostWriteFragment postWriteFragment = (PostWriteFragment) pictureFragment;
                    postWriteFragment.setPictureUri(contentUri);
                }
            }
        }
    }

    public int getDegree(Uri uri) {
        ExifInterface exifInterface = null;

        try {
            if(Build.VERSION.SDK_INT == Build.VERSION_CODES.Q) {
                Uri photoUri = MediaStore.setRequireOriginal(uri);
                ContentResolver contentResolver = getContentResolver();
                InputStream inputStream = contentResolver.openInputStream(photoUri);
                exifInterface = new ExifInterface(inputStream);
            } else {
                exifInterface = new ExifInterface(uri.getPath());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        if(exifInterface != null) {
            int degree = 0;
            int orientation = exifInterface.getAttributeInt(ExifInterface.TAG_ORIENTATION, -1);

            switch (orientation) {
                case ExifInterface.ORIENTATION_ROTATE_90:
                    degree = 90;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_180:
                    degree = 180;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_270:
                    degree = 270;
                    break;
            }
            return degree;
        } else {
            return 0;
        }
    }

    public Bitmap rotateBitmap(Bitmap bitmap, int degree) {
        Matrix matrix = new Matrix();
        matrix.postRotate(degree);

        int width = bitmap.getWidth();
        int height = bitmap.getHeight();

        Bitmap result = Bitmap.createBitmap(bitmap, 0, 0, width, height, matrix, false);
        return result;
    }

    public Bitmap resizeBitmap(int targetWidth, Bitmap source) {
        double ratio = (double) targetWidth / (double) source.getWidth();
        int targetHeight = (int)(source.getHeight() * ratio);
        Bitmap result = Bitmap.createScaledBitmap(source, targetWidth, targetHeight, false);
        return result;
    }
}