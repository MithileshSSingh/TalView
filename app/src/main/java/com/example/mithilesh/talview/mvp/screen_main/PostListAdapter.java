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
import com.example.mithilesh.talview.mvp.model.Post;

import java.util.ArrayList;
import java.util.List;

public class PostListAdapter extends RecyclerView.Adapter<PostListViewHolder> {

    private Context mContext;
    private LayoutInflater mInflater;
    private List<Post> mListData;

    private OnItemClicked mListener;

    public PostListAdapter(Context context, ArrayList<Post> listPosts, OnItemClicked listener) {
        mContext = context;
        mInflater = LayoutInflater.from(context);
        mListData = listPosts;
        mListener = listener;

    }

    @Override
    public PostListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View convertView = mInflater.inflate(R.layout.item_post, parent, false);
        return new PostListViewHolder(mContext, convertView, mListener);
    }

    @Override
    public void onBindViewHolder(PostListViewHolder holder, int position) {
        Post data = mListData.get(position);
        holder.apply(data, position);
    }

    @Override
    public int getItemCount() {
        return mListData.size();
    }

    public void setListData(ArrayList<Post> data) {
        mListData = data;
        notifyDataSetChanged();
    }
}


class PostListViewHolder extends RecyclerView.ViewHolder implements BaseViewHolder<Post>, View.OnClickListener {

    private View mView;
    private int mPosition;
    private Context mContext;
    private Post mData;

    private OnItemClicked mListener;

    private LinearLayout rootLayout;

    private TextView tvTitle;
    private TextView tvDescription;

    public PostListViewHolder(Context context, View itemView, OnItemClicked listener) {
        super(itemView);
        mView = itemView;
        mContext = context;
        mListener = listener;

        init();
    }

    public PostListViewHolder(View itemView) {
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
    public void apply(Post data, int position) {
        mData = data;
        mPosition = position;


        tvTitle.setText(mData.getTitle());
        tvDescription.setText(mData.getBody());
    }

    @Override
    public void onClick(View view) {
        mListener.onItemClicked(null, mPosition);
    }
}
