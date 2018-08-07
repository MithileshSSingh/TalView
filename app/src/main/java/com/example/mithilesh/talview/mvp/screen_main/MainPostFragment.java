package com.example.mithilesh.talview.mvp.screen_main;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.mithilesh.talview.R;
import com.example.mithilesh.talview.di.RepositoryInjector;
import com.example.mithilesh.talview.mvp.BaseFragment;
import com.example.mithilesh.talview.mvp.listeners.OnItemClicked;
import com.example.mithilesh.talview.mvp.model.Post;
import com.example.mithilesh.talview.mvp.screen_post.PostActivity;
import com.example.mithilesh.talview.utils.AppConstants;
import com.example.mithilesh.talview.utils.IdelingResourceInstance;

import java.util.ArrayList;


public class MainPostFragment extends BaseFragment implements MainPostContract.View, OnItemClicked {

    public static final String TAG = MainPostFragment.class.getSimpleName();

    private MainPostContract.Presenter mPresenter;

    private LinearLayoutManager mLayoutManagerRV;
    private PostListAdapter mAdapter;
    private ArrayList<Post> mListData = new ArrayList<>();
    private RecyclerView rvPost;

    public MainPostFragment() {
    }

    public static MainPostFragment newInstance() {
        return new MainPostFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_main_post, container, false);
        setHasOptionsMenu(true);
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setScreenTitle(getString(R.string.screen_title_post));
        init();
    }

    @Override
    protected void init() {
        initView();
        initMembers();
        initListeners();
        initData();
    }

    @Override
    protected void initView() {
        rvPost = (RecyclerView) mView.findViewById(R.id.rvPost);
    }

    @Override
    protected void initMembers() {
        mPresenter = new MainPostPresenter(RepositoryInjector.provideRepository(mActivity), this);

        initRecyclerView();
    }

    @Override
    protected void initListeners() {

    }

    @Override
    protected void initData() {
        IdelingResourceInstance.getInstance().getCountingIdlingResource().increment();
        mPresenter.getAllPost(new GetAllPostCallback() {
            @Override
            public void success(ArrayList<Post> dataList) {
                IdelingResourceInstance.getInstance().getCountingIdlingResource().decrement();
                mListData = dataList;
                mAdapter.setListData(dataList);
            }

            @Override
            public void failed(int errorCode, String errorMessage) {
                IdelingResourceInstance.getInstance().getCountingIdlingResource().decrement();
                Toast.makeText(mActivity, errorMessage, Toast.LENGTH_LONG).show();
            }
        });
    }

    private void initRecyclerView() {
        mAdapter = new PostListAdapter(mActivity, mListData, this);
        mLayoutManagerRV = new LinearLayoutManager(mActivity.getApplicationContext());
        RecyclerView.ItemAnimator itemAnimatorVertical = new DefaultItemAnimator();

        rvPost.setHasFixedSize(true);
        rvPost.setLayoutManager(mLayoutManagerRV);
        rvPost.setItemAnimator(itemAnimatorVertical);
        rvPost.setAdapter(mAdapter);

        mAdapter.notifyDataSetChanged();

    }

    @Override
    public void setPresenter(MainPostContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void onItemClicked(View v, int position) {
        Post post = mListData.get(position);

        Intent intent = new Intent(mActivity, PostActivity.class);

        Bundle bundle = new Bundle();
        bundle.putSerializable(AppConstants.IntentKeys.POST, post);
        intent.putExtra(AppConstants.IntentKeys.DATA, bundle);

        startActivity(intent);

    }
}
