package com.example.mithilesh.talview.mvp.screen_album;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.mithilesh.talview.R;
import com.example.mithilesh.talview.di.RepositoryInjector;
import com.example.mithilesh.talview.mvp.BaseFragment;
import com.example.mithilesh.talview.mvp.listeners.OnItemClicked;
import com.example.mithilesh.talview.mvp.model.Album;
import com.example.mithilesh.talview.mvp.model.Photo;
import com.example.mithilesh.talview.utils.AppConstants;
import com.example.mithilesh.talview.utils.IdelingResourceInstance;

import java.util.ArrayList;


public class AlbumFragment extends BaseFragment implements AlbumContract.View, OnItemClicked {

    public static final String TAG = AlbumFragment.class.getSimpleName();

    private AlbumContract.Presenter mPresenter;
    private Album mAlbum;

    private ArrayList<Photo> mListData = new ArrayList<>();
    private AlbumListAdapter mAdapter;
    private GridLayoutManager mLayoutManagerRV;
    private RecyclerView recyclerView;

    public AlbumFragment() {
    }

    public static AlbumFragment newInstance() {
        return new AlbumFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_album, container, false);
        setHasOptionsMenu(true);
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setScreenTitle(getString(R.string.screen_title_album));
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

        recyclerView = (RecyclerView) mView.findViewById(R.id.recyclerView);

    }

    @Override
    protected void initMembers() {
        mPresenter = new AlbumPresenter(RepositoryInjector.provideRepository(getContext()), this);


        Bundle bundle = getArguments();

        if (bundle != null) {
            mAlbum = (Album) bundle.get(AppConstants.IntentKeys.ALBUM);
        }

        initRecyclerView();

    }

    @Override
    protected void initListeners() {

    }

    @Override
    protected void initData() {

        IdelingResourceInstance.getInstance().getCountingIdlingResource().increment();
        mPresenter.getAllPhotos(mAlbum.getId(), new GetAlbumDetailCallBack() {
            @Override
            public void success(ArrayList<Photo> dataList) {
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

    @Override
    public void setPresenter(AlbumContract.Presenter presenter) {
        mPresenter = presenter;
    }

    private void initRecyclerView() {
        mAdapter = new AlbumListAdapter(mActivity, mListData, this);
        mLayoutManagerRV = new GridLayoutManager(mActivity.getApplicationContext(), 2);
        RecyclerView.ItemAnimator itemAnimatorVertical = new DefaultItemAnimator();

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(mLayoutManagerRV);
        recyclerView.setItemAnimator(itemAnimatorVertical);
        recyclerView.setAdapter(mAdapter);

        mAdapter.notifyDataSetChanged();

    }

    @Override
    public void onItemClicked(View v, int position) {
        Photo photo = mListData.get(position);

        Intent intent = new Intent(mActivity, FullScreenPhotoActivity.class);

        Bundle bundle = new Bundle();
        bundle.putSerializable(AppConstants.IntentKeys.PHOTO, photo);
        intent.putExtra(AppConstants.IntentKeys.DATA, bundle);

        startActivity(intent);
    }
}
