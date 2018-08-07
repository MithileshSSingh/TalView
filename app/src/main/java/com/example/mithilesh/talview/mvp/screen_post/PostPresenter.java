package com.example.mithilesh.talview.mvp.screen_post;

import com.example.mithilesh.talview.data.DataSource;
import com.example.mithilesh.talview.data.Repository;
import com.example.mithilesh.talview.mvp.model.Comment;

import java.util.ArrayList;


public class PostPresenter implements PostContract.Presenter {

    private Repository mRepository = null;
    private PostContract.View mView = null;

    private PostPresenter() {
    }

    public PostPresenter(Repository repository, PostContract.View view) {

        mRepository = repository;
        mView = view;

        mView.setPresenter(this);
    }

    @Override
    public void getAllComments(Integer postId, final PostContract.View.GetPostDetailCallBack callBack) {
        mRepository.getAllComments(postId, new DataSource.GetPostDetailCallBack() {
            @Override
            public void success(ArrayList<Comment> dataList) {
                callBack.success(dataList);
            }

            @Override
            public void failed(int errorCode, String errorMessage) {
                callBack.failed(errorCode, errorMessage);
            }
        });
    }
}
