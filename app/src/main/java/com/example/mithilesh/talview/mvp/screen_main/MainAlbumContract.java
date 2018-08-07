package com.example.mithilesh.talview.mvp.screen_main;

import com.example.mithilesh.talview.mvp.BasePresenter;
import com.example.mithilesh.talview.mvp.BaseView;
import com.example.mithilesh.talview.mvp.model.Album;

import java.util.ArrayList;


public class MainAlbumContract {

    interface View extends BaseView<MainAlbumContract.Presenter> {

        interface GetAllAlbumCallback {
            void success(ArrayList<Album> dataList);

            void failed(int errorCode, String errorMessage);
        }

    }

    interface Presenter extends BasePresenter {

        void getAllPost(View.GetAllAlbumCallback callback);

    }
}
