package com.example.mithilesh.talview.mvp.screen_main;

import com.example.mithilesh.talview.mvp.BasePresenter;
import com.example.mithilesh.talview.mvp.BaseView;
import com.example.mithilesh.talview.mvp.model.Post;

import java.util.ArrayList;


public class MainPostContract {

    interface View extends BaseView<Presenter> {

        interface GetAllPostCallback {
            void success(ArrayList<Post> dataList);

            void failed(int errorCode, String errorMessage);
        }

    }

    interface Presenter extends BasePresenter {

        void getAllPost(View.GetAllPostCallback callback);

    }
}
