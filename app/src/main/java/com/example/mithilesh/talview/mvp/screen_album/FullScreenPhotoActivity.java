package com.example.mithilesh.talview.mvp.screen_album;

import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.example.mithilesh.talview.R;
import com.example.mithilesh.talview.mvp.BaseActivity;
import com.example.mithilesh.talview.mvp.model.Photo;
import com.example.mithilesh.talview.utils.AppConstants;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
import com.nostra13.universalimageloader.core.listener.ImageLoadingProgressListener;

public class FullScreenPhotoActivity extends BaseActivity {

    private ImageView imageView;
    private ProgressBar progress;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_screen_photo);
        
        init();

    }

    @Override
    protected void init() {
        initView();
        initMembers();
        initListeners();
        initData();
    }

    @Override
    protected void initView() {
        imageView = (ImageView) findViewById(R.id.imageView);
        progress = (ProgressBar) findViewById(R.id.progress);
    }

    @Override
    protected void initMembers() {

        Bundle bundle = getIntent().getBundleExtra(AppConstants.IntentKeys.DATA);

        Photo photo = (Photo) bundle.getSerializable(AppConstants.IntentKeys.PHOTO);

        initImage(photo.getUrl());
    }

    @Override
    protected void initListeners() {

    }

    @Override
    protected void initData() {

    }

    private void initImage(String url) {
        ImageLoader imageLoader = ImageLoader.getInstance();

        DisplayImageOptions options;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            options = new DisplayImageOptions.Builder().cacheInMemory(true).resetViewBeforeLoading(true).build();
        } else {
            options = new DisplayImageOptions.Builder().cacheInMemory(true).resetViewBeforeLoading(false).build();
        }

        imageLoader.displayImage(url, imageView, options, new ImageLoadingListener() {
            @Override
            public void onLoadingStarted(String imageUri, View view) {
                progress.setVisibility(View.VISIBLE);
                imageView.setVisibility(View.GONE);
            }

            @Override
            public void onLoadingFailed(String imageUri, View view, FailReason failReason) {
                progress.setVisibility(View.GONE);
                imageView.setVisibility(View.GONE);
            }

            @Override
            public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
                progress.setVisibility(View.GONE);
                imageView.setVisibility(View.VISIBLE);
            }

            @Override
            public void onLoadingCancelled(String imageUri, View view) {
                progress.setVisibility(View.GONE);
                imageView.setVisibility(View.GONE);
            }
        }, new ImageLoadingProgressListener() {
            @Override
            public void onProgressUpdate(String imageUri, View view, int current, int total) {

            }
        });

    }
}
