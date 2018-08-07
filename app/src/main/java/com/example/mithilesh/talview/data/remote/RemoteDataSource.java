package com.example.mithilesh.talview.data.remote;

import android.content.Context;

import com.example.mithilesh.talview.data.DataSource;
import com.example.mithilesh.talview.mvp.model.Album;
import com.example.mithilesh.talview.mvp.model.Comment;
import com.example.mithilesh.talview.mvp.model.Photo;
import com.example.mithilesh.talview.mvp.model.Post;
import com.squareup.okhttp.OkHttpClient;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

public class RemoteDataSource implements DataSource {


    private static RemoteDataSource INSTANCE = null;
    private static Retrofit retrofit;
    private static ApiClient.APICalls apiCalls;
    private Context mContext;

    private RemoteDataSource() {

    }

    private RemoteDataSource(Context context) {
        mContext = context;
    }

    public static RemoteDataSource getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = new RemoteDataSource(context);
        }

        return INSTANCE;
    }

    public synchronized ApiClient.APICalls getApiClient() {
        if (apiCalls == null) {
            apiCalls = getRetrofitInstance().create(ApiClient.APICalls.class);
        }

        return apiCalls;
    }

    private synchronized Retrofit getRetrofitInstance() {
        //XXX
        if (retrofit == null) {
            OkHttpClient client = new OkHttpClient();

            client.setReadTimeout(10, TimeUnit.SECONDS);
            client.setWriteTimeout(10, TimeUnit.SECONDS);
            client.setConnectTimeout(10, TimeUnit.SECONDS);

            retrofit = new Retrofit.Builder().baseUrl(ApiClient.ServiceType.BASE_URL).client(client).addConverterFactory(GsonConverterFactory.create()).addCallAdapterFactory(new ErrorHandlingExecutorCallAdapterFactory(new ErrorHandlingExecutorCallAdapterFactory.MainThreadExecutor())).build();
        }

        return retrofit;
    }

    private ApiError getApiError(Throwable t) {
        RetrofitException exception = (RetrofitException) t;
        ApiError apiError = new ApiError();

        if (exception.getErrorResponse() != null) {
            ApiErrorResponse apiErrorResponse = exception.getErrorResponse();
            if (apiErrorResponse != null) {

                apiError.errorCode = apiErrorResponse.getStatusCode();
                apiError.msgError = apiErrorResponse.getMessage();

            } else {

                apiError.errorCode = ApiClient.HttpErrorCode.NO_CODE;
                apiError.msgError = "Error";

            }
        } else {

            apiError.errorCode = ApiClient.HttpErrorCode.NO_CODE;
            apiError.msgError = "Error";

        }

        return apiError;
    }


    @Override
    public void getAllPost(final GetAllPostCallback callback) {

        if (!NetworkUtils.isNetworkAvailable(mContext)) {

            callback.failed(ApiClient.HttpErrorCode.NO_CODE, "No Network");
            return;
        }


        final Call<ArrayList<Post>> call = getApiClient().getAllPost();

        call.enqueue(new Callback<ArrayList<Post>>() {
            @Override
            public void onResponse(retrofit.Response<ArrayList<Post>> response, Retrofit retrofit) {

                ArrayList<Post> list = response.body();

                if (list == null || list.size() == 0) {
                    callback.failed(ApiClient.HttpErrorCode.NO_CODE, "NO DATA");
                } else {
                    callback.success(list);
                }
            }

            @Override
            public void onFailure(Throwable t) {
                ApiError apiError = getApiError(t);
                callback.failed(apiError.errorCode, apiError.msgError);
            }
        });

    }

    @Override
    public void getAllComments(Integer postId, final GetPostDetailCallBack callBack) {

        if (!NetworkUtils.isNetworkAvailable(mContext)) {

            callBack.failed(ApiClient.HttpErrorCode.NO_CODE, "No Network");
            return;
        }

        Call<ArrayList<Comment>> call = getApiClient().getAllComments(postId);

        call.enqueue(new Callback<ArrayList<Comment>>() {
            @Override
            public void onResponse(Response<ArrayList<Comment>> response, Retrofit retrofit) {

                ArrayList<Comment> list = response.body();

                if (list == null || list.size() == 0) {
                    callBack.failed(ApiClient.HttpErrorCode.NO_CODE, "NO DATA");
                } else {
                    callBack.success(list);
                }
            }

            @Override
            public void onFailure(Throwable t) {
                ApiError apiError = getApiError(t);
                callBack.failed(apiError.errorCode, apiError.msgError);
            }
        });
    }

    @Override
    public void getAllAlbum(final GetAllAlbumCallBack callBack) {

        if (!NetworkUtils.isNetworkAvailable(mContext)) {

            callBack.failed(ApiClient.HttpErrorCode.NO_CODE, "No Network");
            return;
        }


        Call<ArrayList<Album>> call = getApiClient().getAllAlbum();

        call.enqueue(new Callback<ArrayList<Album>>() {
            @Override
            public void onResponse(retrofit.Response<ArrayList<Album>> response, Retrofit retrofit) {

                ArrayList<Album> list = response.body();

                if (list == null || list.size() == 0) {
                    callBack.failed(ApiClient.HttpErrorCode.NO_CODE, "NO DATA");
                } else {
                    callBack.success(list);
                }
            }

            @Override
            public void onFailure(Throwable t) {
                ApiError apiError = getApiError(t);
                callBack.failed(apiError.errorCode, apiError.msgError);
            }
        });

    }

    @Override
    public void getAllPhotos(Integer albumId, final GetAlbumDetailCallBack callBack) {
        if (!NetworkUtils.isNetworkAvailable(mContext)) {

            callBack.failed(ApiClient.HttpErrorCode.NO_CODE, "No Network");
            return;
        }

        Call<ArrayList<Photo>> call = getApiClient().getAllAlbumPhotos(albumId);

        call.enqueue(new Callback<ArrayList<Photo>>() {
            @Override
            public void onResponse(Response<ArrayList<Photo>> response, Retrofit retrofit) {

                ArrayList<Photo> list = response.body();

                if (list == null || list.size() == 0) {
                    callBack.failed(ApiClient.HttpErrorCode.NO_CODE, "NO DATA");
                } else {
                    callBack.success(list);
                }
            }

            @Override
            public void onFailure(Throwable t) {
                ApiError apiError = getApiError(t);
                callBack.failed(apiError.errorCode, apiError.msgError);
            }
        });

    }

    private class ApiError {
        public int errorCode;
        public String msgError;

        @Override
        public String toString() {
            return "ApiError{" + "errorCode=" + errorCode + ", msgError=" + msgError + '}';
        }
    }
}
