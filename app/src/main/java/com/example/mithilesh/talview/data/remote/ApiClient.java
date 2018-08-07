package com.example.mithilesh.talview.data.remote;

import com.example.mithilesh.talview.mvp.model.Album;
import com.example.mithilesh.talview.mvp.model.Comment;
import com.example.mithilesh.talview.mvp.model.Photo;
import com.example.mithilesh.talview.mvp.model.Post;

import java.util.ArrayList;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Path;


public class ApiClient {

    public interface APICalls {

        @GET(ServiceType.GET_ALL_POST)
        Call<ArrayList<Post>> getAllPost();
        
        @GET(ServiceType.GET_POST_DETAIL)
        Call<ArrayList<Comment>> getAllComments(@Path("post_id") Integer postId);

        @GET(ServiceType.GET_ALL_ALBUM)
        Call<ArrayList<Album>> getAllAlbum();

        @GET(ServiceType.GET_ALBUM_DETAIL)
        Call<ArrayList<Photo>> getAllAlbumPhotos(@Path("album_id") Integer postId);

    }

    public static class ServiceType {
        public static final String BASE_URL = " https://jsonplaceholder.typicode.com";

        public static final String GET_ALL_POST = BASE_URL + "/posts";
        public static final String GET_ALL_ALBUM = BASE_URL + "/albums";
        public static final String GET_POST_DETAIL = BASE_URL + "/posts/{post_id}/comments";
        public static final String GET_ALBUM_DETAIL = BASE_URL + "/albums/{album_id}/photos";
    }

    public static class HttpErrorCode {
        public static final Integer NO_CODE = 000;
    }

}
