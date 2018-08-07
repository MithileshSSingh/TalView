package com.example.mithilesh.talview.data;


import com.example.mithilesh.talview.mvp.model.Album;
import com.example.mithilesh.talview.mvp.model.Comment;
import com.example.mithilesh.talview.mvp.model.Photo;
import com.example.mithilesh.talview.mvp.model.Post;

import java.util.ArrayList;

public class Repository implements DataSource {


    private static Repository INSTANCE = null;

    private DataSource mLocalDataSource = null;
    private DataSource mRemoteDataSource = null;

    private Repository() {

    }

    private Repository(DataSource localDataSource, DataSource remoteDataSource) {
        mLocalDataSource = localDataSource;
        mRemoteDataSource = remoteDataSource;
    }

    public static Repository getInstance(DataSource localDataSource, DataSource remoteDataSource) {

        if (INSTANCE == null) {
            INSTANCE = new Repository(localDataSource, remoteDataSource);
        }

        return INSTANCE;
    }

    @Override
    public void getAllPost(final GetAllPostCallback callback) {
        mRemoteDataSource.getAllPost(new GetAllPostCallback() {
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

    @Override
    public void getAllComments(Integer postId, final GetPostDetailCallBack callBack) {
        mRemoteDataSource.getAllComments(postId, new GetPostDetailCallBack() {
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

    @Override
    public void getAllAlbum(final GetAllAlbumCallBack callBack) {
        mRemoteDataSource.getAllAlbum(new GetAllAlbumCallBack() {
            @Override
            public void success(ArrayList<Album> dataList) {
                callBack.success(dataList);
            }

            @Override
            public void failed(int errorCode, String errorMessage) {
                callBack.failed(errorCode, errorMessage);
            }
        });
    }

    @Override
    public void getAllPhotos(Integer albumId, final GetAlbumDetailCallBack callBack) {
        mRemoteDataSource.getAllPhotos(albumId, new GetAlbumDetailCallBack() {
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