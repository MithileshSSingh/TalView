package com.example.mithilesh.talview.data;


import com.example.mithilesh.talview.mvp.model.Album;
import com.example.mithilesh.talview.mvp.model.Comment;
import com.example.mithilesh.talview.mvp.model.Photo;
import com.example.mithilesh.talview.mvp.model.Post;

import java.util.ArrayList;

public interface DataSource {

    void getAllPost(GetAllPostCallback callback);

    void getAllComments(Integer postId, GetPostDetailCallBack callBack);

    void getAllAlbum(GetAllAlbumCallBack callBack);

    void getAllPhotos(Integer albumId, GetAlbumDetailCallBack callBack);

    interface GetAlbumDetailCallBack {
        void success(ArrayList<Photo> dataList);

        void failed(int errorCode, String errorMessage);
    }


    interface GetPostDetailCallBack {
        void success(ArrayList<Comment> dataList);

        void failed(int errorCode, String errorMessage);
    }

    interface GetAllAlbumCallBack {
        void success(ArrayList<Album> dataList);

        void failed(int errorCode, String errorMessage);
    }

    interface GetAllPostCallback {
        void success(ArrayList<Post> dataList);

        void failed(int errorCode, String errorMessage);
    }
}
