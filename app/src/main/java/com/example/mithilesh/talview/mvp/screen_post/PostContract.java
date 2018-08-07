package com.example.mithilesh.talview.mvp.screen_post;

import com.example.mithilesh.talview.mvp.BasePresenter;
import com.example.mithilesh.talview.mvp.BaseView;
import com.example.mithilesh.talview.mvp.model.Comment;

import java.util.ArrayList;


public class PostContract {

    interface View extends BaseView<Presenter> {

        interface GetPostDetailCallBack {
            void success(ArrayList<Comment> dataList);

            void failed(int errorCode, String errorMessage);
        }
    }

    interface Presenter extends BasePresenter {
        void getAllComments(Integer postId,View.GetPostDetailCallBack callBack);
    }
}
