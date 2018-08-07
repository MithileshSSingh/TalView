package com.example.mithilesh.talview.mvp.screen_main;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.InputDevice;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.mithilesh.talview.R;
import com.example.mithilesh.talview.di.RepositoryInjector;
import com.example.mithilesh.talview.mvp.BaseFragment;
import com.example.mithilesh.talview.mvp.listeners.OnItemClicked;
import com.example.mithilesh.talview.mvp.model.Album;
import com.example.mithilesh.talview.mvp.screen_album.AlbumActivity;
import com.example.mithilesh.talview.utils.AppConstants;
import com.example.mithilesh.talview.utils.IdelingResourceInstance;

import java.util.ArrayList;


public class MainAlbumFragment extends BaseFragment implements MainAlbumContract.View, OnItemClicked {

    public static final String TAG = MainAlbumFragment.class.getSimpleName();

    private MainAlbumContract.Presenter mPresenter;

    private LinearLayoutManager mLayoutManagerRV;
    private AlbumListAdapter mAdapter;
    private ArrayList<Album> mListData = new ArrayList<>();
    private RecyclerView rvAlbum;

    public MainAlbumFragment() {
    }

    public static MainAlbumFragment newInstance() {
        return new MainAlbumFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_main_album, container, false);
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
        rvAlbum = (RecyclerView) mView.findViewById(R.id.rvAlbum);
    }

    @Override
    protected void initMembers() {
        mPresenter = new MainAlbumPresenter(RepositoryInjector.provideRepository(mActivity), this);

        initRecyclerView();
    }

    @Override
    protected void initListeners() {

    }

    @Override
    protected void initData() {
        IdelingResourceInstance.getInstance().getCountingIdlingResource().increment();
        mPresenter.getAllPost(new MainAlbumContract.View.GetAllAlbumCallback() {
            @Override
            public void success(ArrayList<Album> dataList) {
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
        mAdapter = new AlbumListAdapter(mActivity, mListData, this);
        mLayoutManagerRV = new LinearLayoutManager(mActivity.getApplicationContext());
        RecyclerView.ItemAnimator itemAnimatorVertical = new DefaultItemAnimator();

        rvAlbum.setHasFixedSize(true);
        rvAlbum.setLayoutManager(mLayoutManagerRV);
        rvAlbum.setItemAnimator(itemAnimatorVertical);
        rvAlbum.setAdapter(mAdapter);

        mAdapter.notifyDataSetChanged();

    }

    @Override
    public void setPresenter(MainAlbumContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void onItemClicked(View v, int position) {
        Album album = mListData.get(position);

        Intent intent = new Intent(mActivity, AlbumActivity.class);

        Bundle bundle = new Bundle();
        bundle.putSerializable(AppConstants.IntentKeys.ALBUM, album);
        intent.putExtra(AppConstants.IntentKeys.DATA, bundle);

        startActivity(intent);
    }
}
