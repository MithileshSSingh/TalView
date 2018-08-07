package com.example.mithilesh.talview.mvp.screen_post;

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
import com.example.mithilesh.talview.mvp.model.Comment;

import java.util.ArrayList;
import java.util.List;

public class PostCommentListAdapter extends RecyclerView.Adapter<PostCommentListViewHolder> {

    private Context mContext;
    private LayoutInflater mInflater;
    private List<Comment> mListData;

    private OnItemClicked mListener;

    public PostCommentListAdapter(Context context, ArrayList<Comment> listPosts, OnItemClicked listener) {
        mContext = context;
        mInflater = LayoutInflater.from(context);
        mListData = listPosts;
        mListener = listener;

    }

    @Override
    public PostCommentListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View convertView = mInflater.inflate(R.layout.item_post_commnet, parent, false);
        return new PostCommentListViewHolder(mContext, convertView, mListener);
    }

    @Override
    public void onBindViewHolder(PostCommentListViewHolder holder, int position) {
        Comment data = mListData.get(position);
        holder.apply(data, position);
    }

    @Override
    public int getItemCount() {
        return mListData.size();
    }

    public void setListData(ArrayList<Comment> data) {
        mListData = data;
        notifyDataSetChanged();
    }
}


class PostCommentListViewHolder extends RecyclerView.ViewHolder implements BaseViewHolder<Comment>, View.OnClickListener {

    private View mView;
    private int mPosition;
    private Context mContext;
    private Comment mData;

    private OnItemClicked mListener;

    private LinearLayout rootLayout;
    private TextView tvName;
    private TextView tvBody;

    public PostCommentListViewHolder(Context context, View itemView, OnItemClicked listener) {
        super(itemView);
        mView = itemView;
        mContext = context;
        mListener = listener;

        init();
    }

    public PostCommentListViewHolder(View itemView) {
        super(itemView);
    }

    private void init() {
        initView();
        initListener();
    }

    private void initView() {
        rootLayout = (LinearLayout) mView.findViewById(R.id.llRootLayout);
        tvName = (TextView) mView.findViewById(R.id.tvName);
        tvBody = (TextView) mView.findViewById(R.id.tvBody);
    }

    private void initListener() {
        rootLayout.setOnClickListener(this);
    }

    @Override
    public void apply(Comment data, int position) {
        mData = data;
        mPosition = position;

        tvName.setText(data.getName());
        tvBody.setText(data.getBody());
    }


    @Override
    public void onClick(View view) {
        mListener.onItemClicked(null, mPosition);
    }
}
