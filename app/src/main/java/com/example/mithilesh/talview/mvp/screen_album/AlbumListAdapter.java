package com.example.mithilesh.talview.mvp.screen_album;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.example.mithilesh.talview.R;
import com.example.mithilesh.talview.mvp.BaseViewHolder;
import com.example.mithilesh.talview.mvp.listeners.OnItemClicked;
import com.example.mithilesh.talview.mvp.model.Photo;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
import com.nostra13.universalimageloader.core.listener.ImageLoadingProgressListener;

import java.util.ArrayList;
import java.util.List;

public class AlbumListAdapter extends RecyclerView.Adapter<AlbumListViewHolder> {

    private Context mContext;
    private LayoutInflater mInflater;
    private List<Photo> mListData;

    private OnItemClicked mListener;

    public AlbumListAdapter(Context context, ArrayList<Photo> listPosts, OnItemClicked listener) {
        mContext = context;
        mInflater = LayoutInflater.from(context);
        mListData = listPosts;
        mListener = listener;

    }

    @Override
    public AlbumListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View convertView = mInflater.inflate(R.layout.item_photo, parent, false);
        return new AlbumListViewHolder(mContext, convertView, mListener);
    }

    @Override
    public void onBindViewHolder(AlbumListViewHolder holder, int position) {
        Photo data = mListData.get(position);
        holder.apply(data, position);
    }

    @Override
    public int getItemCount() {
        return mListData.size();
    }

    public void setListData(ArrayList<Photo> data) {
        mListData = data;
        notifyDataSetChanged();
    }
}


class AlbumListViewHolder extends RecyclerView.ViewHolder implements BaseViewHolder<Photo>, View.OnClickListener {

    private View mView;
    private int mPosition;
    private Context mContext;
    private Photo mData;

    private OnItemClicked mListener;

    private LinearLayout rootLayout;
    private ProgressBar progress;
    private ImageView ivPhoto;

    public AlbumListViewHolder(Context context, View itemView, OnItemClicked listener) {
        super(itemView);
        mView = itemView;
        mContext = context;
        mListener = listener;

        init();
    }

    public AlbumListViewHolder(View itemView) {
        super(itemView);
    }

    private void init() {
        initView();
        initListener();
    }

    private void initView() {
        rootLayout = (LinearLayout) mView.findViewById(R.id.rootLayout);
        ivPhoto = (ImageView) mView.findViewById(R.id.ivPhoto);
        progress = (ProgressBar) mView.findViewById(R.id.progress);
    }

    private void initListener() {
        rootLayout.setOnClickListener(this);
    }

    @Override
    public void apply(Photo data, int position) {
        mData = data;
        mPosition = position;

        initImage(mData.getThumbnailUrl());

    }

    private void initImage(String url) {
        ImageLoader imageLoader = ImageLoader.getInstance();

        DisplayImageOptions options;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            options = new DisplayImageOptions.Builder().cacheInMemory(true).resetViewBeforeLoading(true).build();
        } else {
            options = new DisplayImageOptions.Builder().cacheInMemory(true).resetViewBeforeLoading(false).build();
        }

        imageLoader.displayImage(url, ivPhoto, options, new ImageLoadingListener() {
            @Override
            public void onLoadingStarted(String imageUri, View view) {
                progress.setVisibility(View.VISIBLE);
                ivPhoto.setVisibility(View.GONE);
            }

            @Override
            public void onLoadingFailed(String imageUri, View view, FailReason failReason) {
                progress.setVisibility(View.GONE);
                ivPhoto.setVisibility(View.GONE);
            }

            @Override
            public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
                progress.setVisibility(View.GONE);
                ivPhoto.setVisibility(View.VISIBLE);
            }

            @Override
            public void onLoadingCancelled(String imageUri, View view) {
                progress.setVisibility(View.GONE);
                ivPhoto.setVisibility(View.GONE);
            }
        }, new ImageLoadingProgressListener() {
            @Override
            public void onProgressUpdate(String imageUri, View view, int current, int total) {

            }
        });

    }

    @Override
    public void onClick(View view) {
        mListener.onItemClicked(null, mPosition);
    }
}
