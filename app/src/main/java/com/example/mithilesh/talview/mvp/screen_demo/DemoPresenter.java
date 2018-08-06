package com.example.mithilesh.talview.mvp.screen_demo;

import com.example.mithilesh.talview.data.Repository;


public class DemoPresenter implements DemoContract.Presenter {

    private Repository mRepository = null;
    private DemoContract.View mView = null;

    private DemoPresenter() {
    }

    public DemoPresenter(Repository repository, DemoContract.View view) {

        mRepository = repository;
        mView = view;

        mView.setPresenter(this);
    }

}
