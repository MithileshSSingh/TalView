package com.example.mithilesh.talview.mvp.screen_album;

import com.example.mithilesh.talview.data.DataSource;
import com.example.mithilesh.talview.data.Repository;
import com.example.mithilesh.talview.mvp.model.Photo;

import java.util.ArrayList;


public class AlbumPresenter implements AlbumContract.Presenter {

    private Repository mRepository = null;
    private AlbumContract.View mView = null;

    private AlbumPresenter() {
    }

    public AlbumPresenter(Repository repository, AlbumContract.View view) {

        mRepository = repository;
        mView = view;

        mView.setPresenter(this);
    }

    @Override
    public void getAllPhotos(Integer albumId, final AlbumContract.View.GetAlbumDetailCallBack callBack) {
        mRepository.getAllPhotos(albumId, new DataSource.GetAlbumDetailCallBack() {
            @Override
            public void success(ArrayList<Photo> dataList) {
                callBack.success(dataList);
            }

            @Override
            public void failed(int errorCode, String errorMessage) {
                callBack.failed(errorCode, errorMessage);
            }
        });
    }
}
