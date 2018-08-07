package com.example.mithilesh.talview.mvp.screen_post;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mithilesh.talview.R;
import com.example.mithilesh.talview.di.RepositoryInjector;
import com.example.mithilesh.talview.mvp.BaseFragment;
import com.example.mithilesh.talview.mvp.listeners.OnItemClicked;
import com.example.mithilesh.talview.mvp.model.Comment;
import com.example.mithilesh.talview.mvp.model.Post;
import com.example.mithilesh.talview.utils.AppConstants;
import com.example.mithilesh.talview.utils.IdelingResourceInstance;

import java.util.ArrayList;


public class PostFragment extends BaseFragment implements PostContract.View, OnItemClicked {

    public static final String TAG = PostFragment.class.getSimpleName();

    private PostContract.Presenter mPresenter;

    private Post mPost = null;
    private TextView tvTitle;
    private TextView tvDescription;
    private RecyclerView recyclerView;

    private ArrayList<Comment> mListData = new ArrayList<>();
    private PostCommentListAdapter mAdapter;
    private LinearLayoutManager mLayoutManagerRV;

    public PostFragment() {
    }

    public static PostFragment newInstance() {
        return new PostFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_post, container, false);
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

        tvTitle = (TextView) mView.findViewById(R.id.tvTitle);
        tvDescription = (TextView) mView.findViewById(R.id.tvDescription);

        recyclerView = (RecyclerView) mView.findViewById(R.id.recyclerView);

    }

    @Override
    protected void initMembers() {
        mPresenter = new PostPresenter(RepositoryInjector.provideRepository(getContext()), this);

        Bundle bundle = getArguments();

        if (bundle != null) {
            mPost = (Post) bundle.get(AppConstants.IntentKeys.POST);

            tvTitle.setText(String.valueOf(mPost.getTitle()));
            tvDescription.setText(String.valueOf(mPost.getBody()));

        }

        initRecyclerView();

    }

    private void initRecyclerView() {
        mAdapter = new PostCommentListAdapter(mActivity, mListData, this);
        mLayoutManagerRV = new LinearLayoutManager(mActivity.getApplicationContext());
        RecyclerView.ItemAnimator itemAnimatorVertical = new DefaultItemAnimator();

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(mLayoutManagerRV);
        recyclerView.setItemAnimator(itemAnimatorVertical);
        recyclerView.setAdapter(mAdapter);

        mAdapter.notifyDataSetChanged();

    }


    @Override
    protected void initListeners() {

    }

    @Override
    protected void initData() {
        if (mPost != null) {
            IdelingResourceInstance.getInstance().getCountingIdlingResource().increment();

            mPresenter.getAllComments(mPost.getId(), new GetPostDetailCallBack() {
                @Override
                public void success(ArrayList<Comment> dataList) {
                    IdelingResourceInstance.getInstance().getCountingIdlingResource().decrement();
                    mListData = dataList;
                    mAdapter.setListData(mListData);
                }

                @Override
                public void failed(int errorCode, String errorMessage) {
                    IdelingResourceInstance.getInstance().getCountingIdlingResource().decrement();
                    Toast.makeText(mActivity, errorMessage, Toast.LENGTH_LONG).show();
                }
            });
        }
    }

    @Override
    public void setPresenter(PostContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void onItemClicked(View v, int position) {

    }
}
