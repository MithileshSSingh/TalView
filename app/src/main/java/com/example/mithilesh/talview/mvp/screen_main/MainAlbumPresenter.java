package com.example.mithilesh.talview.mvp.screen_main;

import com.example.mithilesh.talview.data.DataSource;
import com.example.mithilesh.talview.data.Repository;
import com.example.mithilesh.talview.mvp.model.Album;

import java.util.ArrayList;


public class MainAlbumPresenter implements MainAlbumContract.Presenter {

    private Repository mRepository = null;
    private MainAlbumContract.View mView = null;

    private MainAlbumPresenter() {
    }

    public MainAlbumPresenter(Repository repository, MainAlbumContract.View view) {

        mRepository = repository;
        mView = view;

        mView.setPresenter(this);
    }

    @Override
    public void getAllPost(final MainAlbumContract.View.GetAllAlbumCallback callback) {
        mRepository.getAllAlbum(new DataSource.GetAllAlbumCallBack() {
            @Override
            public void success(ArrayList<Album> dataList) {
                callback.success(dataList);
            }

            @Override
            public void failed(int errorCode, String errorMessage) {
                callback.failed(errorCode, errorMessage);
            }
        });
    }
}
