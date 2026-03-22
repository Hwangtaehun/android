package com.example.app03_community.ui.postmain;

import android.os.Bundle;

import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.SystemClock;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.app03_community.ui.modifyuserinfo.ModifyUserInfoFragment;
import com.example.app03_community.ui.postmodify.PostModifyFragment;
import com.example.app03_community.ui.postread.PostReadFragment;
import com.example.app03_community.ui.postwrite.PostWriteFragment;
import com.google.android.material.transition.MaterialSharedAxis;

import com.example.app03_community.MainActivity;
import com.example.app03_community.R;
import com.example.app03_community.databinding.FragmentPostMainBinding;
import com.example.app03_community.databinding.HeaderPostmainBinding;
import com.example.app03_community.ui.postlist.PostListFragment;

public class PostMainFragment extends Fragment {
    FragmentPostMainBinding fragmentPostMainBinding;
    MainActivity mainActivity;

    // 프래그먼트를 담을 변수
    Fragment newFragment;
    Fragment oldFragment;

    public DrawerLayout postDrawerLayout;

    public static final String POST_LIST_FRAGMENT = "PostListFragment";
    public static final String POST_WRITE_FRAGMENT = "PostWireFragment";
    public static final String POST_READ_FRAGMENT = "PostReadFragment";
    public static final String POST_MODIFY_FRAGMENT = "PostModifyFragment";
    public static final String MODIFY_USER_FRAGMENT = "ModifyUserFragment";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        fragmentPostMainBinding = FragmentPostMainBinding.inflate(inflater);
        mainActivity = (MainActivity) getActivity();

        setNavigationDrawer();

        replaceFragment(POST_LIST_FRAGMENT, false, false, null);

        return fragmentPostMainBinding.getRoot();
    }

    public void setNavigationDrawer() {
        postDrawerLayout = fragmentPostMainBinding.drawerLayoutPostMain;

        HeaderPostmainBinding headerPostmainBinding = HeaderPostmainBinding.inflate(getLayoutInflater());
        headerPostmainBinding.headerPostMainNickname.setText("홍길동님");
        fragmentPostMainBinding.navigationViewPostMain.addHeaderView(headerPostmainBinding.getRoot());

        fragmentPostMainBinding.navigationViewPostMain.setNavigationItemSelectedListener(menuItem -> {
            new Thread(() -> {
                SystemClock.sleep(300);
                fragmentPostMainBinding.drawerLayoutPostMain.close();
            }).start();

            int itemId = menuItem.getItemId();

            if (itemId == R.id.menuItemPostNavigationAll) {
                replaceFragment(POST_LIST_FRAGMENT, false, false, null);
            } else if (itemId == R.id.menuItemPostNavigation1) {
                replaceFragment(POST_LIST_FRAGMENT, false, false, null);
            } else if (itemId == R.id.menuItemPostNavigation2) {
                replaceFragment(POST_LIST_FRAGMENT, false, false, null);
            } else if (itemId == R.id.menuItemPostNavigation3) {
                replaceFragment(POST_LIST_FRAGMENT, false, false, null);
            } else if (itemId == R.id.menuItemPostNavigation4) {
                replaceFragment(POST_LIST_FRAGMENT, false, false, null);
            } else if (itemId == R.id.menuItemPostNavigationModifyUserInfo) {
                replaceFragment(MODIFY_USER_FRAGMENT, false, false, null);
            } else if (itemId == R.id.menuItemPostNaivgationLogout) {
                mainActivity.replaceFragment(MainActivity.LOGIN_FRAGMENT, false, false, null);
            } else if (itemId == R.id.menuItemPostNavigationSignOut) {
                mainActivity.replaceFragment(MainActivity.LOGIN_FRAGMENT, false, false, null);
            }

            return true;
        });
    }

    public void replaceFragment(String name, boolean addToBackStack, boolean animate, Bundle bundle) {
        SystemClock.sleep(200);
        FragmentManager fragmentManager = mainActivity.getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        if(newFragment != null) {
            oldFragment = newFragment;
        }

        switch (name){
            case POST_LIST_FRAGMENT :
                newFragment = new PostListFragment();
                break;
            case POST_WRITE_FRAGMENT:
                newFragment = new PostWriteFragment();
                break;
            case POST_READ_FRAGMENT:
                newFragment = new PostReadFragment();
                break;
            case POST_MODIFY_FRAGMENT:
                newFragment = new PostModifyFragment();
                break;
            case MODIFY_USER_FRAGMENT:
                newFragment = new ModifyUserInfoFragment();
                break;
        }

        if(newFragment != null){
            if(animate == true){
                if(oldFragment != null){
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

            fragmentTransaction.replace(R.id.postMainContainer, newFragment);

            if(addToBackStack == true){
                fragmentTransaction.addToBackStack(name);
            }

            fragmentTransaction.commit();
        }
    }

    public void removerFragment(String name){
        SystemClock.sleep(200);
        FragmentManager fragmentManager = mainActivity.getSupportFragmentManager();
        fragmentManager.popBackStack(name, FragmentManager.POP_BACK_STACK_INCLUSIVE);
    }
}