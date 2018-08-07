package com.example.mithilesh.talview.mvp.screen_main;

import com.example.mithilesh.talview.data.DataSource;
import com.example.mithilesh.talview.data.Repository;
import com.example.mithilesh.talview.mvp.model.Post;

import java.util.ArrayList;

public class MainPostPresenter implements MainPostContract.Presenter {

    private Repository mRepository = null;
    private MainPostContract.View mView = null;

    private MainPostPresenter() {
    }

    public MainPostPresenter(Repository repository, MainPostContract.View view) {

        mRepository = repository;
        mView = view;

        mView.setPresenter(this);
    }

    @Override
    public void getAllPost(final MainPostContract.View.GetAllPostCallback callback) {
        mRepository.getAllPost(new DataSource.GetAllPostCallback() {
            @Override
            public void success(ArrayList<Post> dataList) {
                callback.success(dataList);
            }

            @Override
            public void failed(int errorCode, String errorMessage) {
                callback.failed(errorCode, errorMessage);
            }
        });
    }
}
