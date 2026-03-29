package com.example.app03_community.ui.postlist;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.app03_community.MainActivity;
import com.example.app03_community.R;
import com.example.app03_community.databinding.FragmentPostListBinding;
import com.example.app03_community.databinding.RowPostListBinding;
import com.example.app03_community.ui.postmain.PostMainFragment;
import com.google.android.material.divider.MaterialDividerItemDecoration;

import java.util.ArrayList;

public class PostListFragment extends Fragment {

    FragmentPostListBinding fragmentPostListBinding;
    MainActivity mainActivity;
    PostMainFragment postMainFragment;
    PostListViewModel postListViewModel;
    ArrayList<PostListItemViewModel> postListItemViewModelArrayList = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        // fragmentPostListBinding = FragmentPostListBinding.inflate(inflater);
        fragmentPostListBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_post_list, container, false);
        postListViewModel = new PostListViewModel();
        fragmentPostListBinding.setPostListViewModel(postListViewModel);
        fragmentPostListBinding.setLifecycleOwner(this);

        mainActivity = (MainActivity) getActivity();
        postMainFragment = mainActivity.postMainFragment;

        setToolbar();
        setSearch();
        setRecyclerView();

        return fragmentPostListBinding.getRoot();
    }

    public void setToolbar(){
        // fragmentPostListBinding.toolbarPostList.setTitle("전체 게시판");

        Bundle bundle = getArguments();
        if(bundle != null) {
            String title = bundle.getString("toolbarTitle");
            postListViewModel.toolbarPostListTitle.setValue(title);
        }

        fragmentPostListBinding.toolbarPostList.setNavigationIcon(R.drawable.menu_24px);
        fragmentPostListBinding.toolbarPostList.setNavigationOnClickListener(v -> {
            postMainFragment.postDrawerLayout.open();
        });
    }

    public void setSearch() {
        fragmentPostListBinding.searchBarPostList.setHint("여기를 눌러 검색해주세요.");
        fragmentPostListBinding.searchBarPostList.inflateMenu(R.menu.post_list_main_menu);
        fragmentPostListBinding.searchBarPostList.setOnMenuItemClickListener(item -> {
            int itemId = item.getItemId();

            if(itemId == R.id.menuItemPostListAdd) {
                postMainFragment.replaceFragment(PostMainFragment.POST_WRITE_FRAGMENT, true, true, null);
            }

            return true;
        });

        fragmentPostListBinding.searchViewPostList.setHint("검색어을 입력해주세요.");
    }

    public void setRecyclerView() {
        // 메인
        PostListMainRecyclerViewAdapter adapter1 = new PostListMainRecyclerViewAdapter();
        fragmentPostListBinding.recyclerViewPostListMain.setAdapter(adapter1);
        fragmentPostListBinding.recyclerViewPostListMain.setLayoutManager(new LinearLayoutManager(mainActivity));

        MaterialDividerItemDecoration decoration1 = new MaterialDividerItemDecoration(mainActivity, MaterialDividerItemDecoration.VERTICAL);
        decoration1.setDividerInsetStart(mainActivity.dpToPixel(10));
        decoration1.setDividerInsetEnd(mainActivity.dpToPixel(10));

        fragmentPostListBinding.recyclerViewPostListMain.addItemDecoration(decoration1);

        // 검색 결과
        PostListMainRecyclerViewAdapter adapter2 = new PostListMainRecyclerViewAdapter();
        fragmentPostListBinding.recyclerViewPostListResult.setAdapter(adapter2);
        fragmentPostListBinding.recyclerViewPostListResult.setLayoutManager(new LinearLayoutManager(mainActivity));

        MaterialDividerItemDecoration decoration2 = new MaterialDividerItemDecoration(mainActivity, MaterialDividerItemDecoration.VERTICAL);
        decoration2.setDividerInsetStart(mainActivity.dpToPixel(10));
        decoration2.setDividerInsetEnd(mainActivity.dpToPixel(10));

        fragmentPostListBinding.recyclerViewPostListResult.addItemDecoration(decoration2);
    }

    class PostListMainRecyclerViewAdapter extends RecyclerView.Adapter<PostListMainRecyclerViewAdapter.PostListMainRecyclerViewHolder> {
        public PostListMainRecyclerViewAdapter() {
            postListItemViewModelArrayList.clear();

            for (int i = 0; i < 100; i++) {
                postListItemViewModelArrayList.add(new PostListItemViewModel());
            }
        }

        @NonNull
        @Override
        public PostListMainRecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            // RowPostListBinding rowPostListBinding = RowPostListBinding.inflate(getLayoutInflater());
            RowPostListBinding rowPostListBinding = DataBindingUtil.inflate(getLayoutInflater(), R.layout.row_post_list, parent, false);
            PostListMainRecyclerViewHolder postListMainRecyclerViewHolder = new PostListMainRecyclerViewHolder(rowPostListBinding);

            return postListMainRecyclerViewHolder;
        }

        @Override
        public void onBindViewHolder(@NonNull PostListMainRecyclerViewHolder holder, int position) {
            // position 번째 ViewModel 객체를 추출한다.
            PostListItemViewModel postListItemViewModel = postListItemViewModelArrayList.get(position);
            holder.rowPostListBinding.setPostListItemViewModel(postListItemViewModel);

            postListItemViewModel.textViewRowPostListSubject.setValue("제목 : " + position);
            postListItemViewModel.textViewRowPostListNickName.setValue("닉네임 : " + position);

            holder.rowPostListBinding.textViewRowPostListSubject.setText("제목 : " + position);
            holder.rowPostListBinding.textViewRowPostListNickname.setText("닉네임 : " + position);

            holder.rowPostListBinding.getRoot().setOnClickListener(v -> {
                postMainFragment.replaceFragment(PostMainFragment.POST_READ_FRAGMENT, true, true, null);
            });
        }

        @Override
        public int getItemCount() {
            return postListItemViewModelArrayList.size();
        }

        class PostListMainRecyclerViewHolder extends RecyclerView.ViewHolder {
            RowPostListBinding rowPostListBinding;

            public PostListMainRecyclerViewHolder(RowPostListBinding rowPostListBinding) {
                super(rowPostListBinding.getRoot());
                this.rowPostListBinding = rowPostListBinding;

                rowPostListBinding.getRoot().setLayoutParams(new ViewGroup.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT
                ));
            }
        }
    }
}