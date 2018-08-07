package com.example.mithilesh.talview.mvp.screen_main;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.mithilesh.talview.R;
import com.example.mithilesh.talview.mvp.BaseViewHolder;
import com.example.mithilesh.talview.mvp.listeners.OnItemClicked;
import com.example.mithilesh.talview.mvp.model.Album;

import java.util.ArrayList;
import java.util.List;

public class AlbumListAdapter extends RecyclerView.Adapter<AlbumListViewHolder> {

    private Context mContext;
    private LayoutInflater mInflater;
    private List<Album> mListData;

    private OnItemClicked mListener;

    public AlbumListAdapter(Context context, ArrayList<Album> listPosts, OnItemClicked listener) {
        mContext = context;
        mInflater = LayoutInflater.from(context);
        mListData = listPosts;
        mListener = listener;

    }

    @Override
    public AlbumListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View convertView = mInflater.inflate(R.layout.item_post, parent, false);
        return new AlbumListViewHolder(mContext, convertView, mListener);
    }

    @Override
    public void onBindViewHolder(AlbumListViewHolder holder, int position) {
        Album data = mListData.get(position);
        holder.apply(data, position);
    }

    @Override
    public int getItemCount() {
        return mListData.size();
    }

    public void setListData(ArrayList<Album> data) {
        mListData = data;
        notifyDataSetChanged();
    }
}


class AlbumListViewHolder extends RecyclerView.ViewHolder implements BaseViewHolder<Album>, View.OnClickListener {

    private View mView;
    private int mPosition;
    private Context mContext;
    private Album mData;

    private OnItemClicked mListener;

    private LinearLayout rootLayout;

    private TextView tvTitle;
    private TextView tvDescription;

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
        tvTitle = (TextView) mView.findViewById(R.id.tvTitle);
        tvDescription = (TextView) mView.findViewById(R.id.tvDescription);

    }

    private void initListener() {
        rootLayout.setOnClickListener(this);
    }

    @Override
    public void apply(Album data, int position) {
        mData = data;
        mPosition = position;


        tvTitle.setText(mData.getTitle());
        tvDescription.setVisibility(View.GONE);
    }

    @Override
    public void onClick(View view) {
        mListener.onItemClicked(null, mPosition);
    }
}
