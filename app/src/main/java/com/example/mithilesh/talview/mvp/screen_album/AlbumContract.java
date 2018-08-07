package com.example.mithilesh.talview.mvp.screen_album;

import com.example.mithilesh.talview.mvp.BasePresenter;
import com.example.mithilesh.talview.mvp.BaseView;
import com.example.mithilesh.talview.mvp.model.Photo;

import java.util.ArrayList;


public class AlbumContract {

    interface View extends BaseView<Presenter> {
        interface GetAlbumDetailCallBack {
            void success(ArrayList<Photo> dataList);

            void failed(int errorCode, String errorMessage);
        }

    }

    interface Presenter extends BasePresenter {

        void getAllPhotos(Integer albumId,View.GetAlbumDetailCallBack callBack);

    }
}
