package com.saraceni.imgurclient.ui_adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.saraceni.imgurclient.R;
import com.saraceni.imgurclient.api.client.ApiClient;
import com.saraceni.imgurclient.api.response.common.Image;
import com.saraceni.imgurclient.util.Constants;
import com.squareup.picasso.Picasso;

import java.util.List;


/**
 * Created by rafaelgontijo on 13/08/17.
 */
public class PostAdapter extends RecyclerView.Adapter {

    public final int VIEW_ITEM = 1;
    public final int VIEW_PROGRESS = 0;

    List<Image> postsList;
    private Context context;
    private OnPostClickListener onPostClickListener;

    public PostAdapter(Context context, List<Image> postsList) {
        this.context = context;
        this.postsList = postsList;
    }

    public void setOnPostClickListener(OnPostClickListener onPostClickListener) {
        this.onPostClickListener = onPostClickListener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(viewType == VIEW_ITEM) {
            View itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.post_column, parent, false);
            return new PostViewHolder(itemView);
        } else {
            View v = LayoutInflater.from(parent.getContext()).inflate(
                    R.layout.progressbar_column, parent, false);

            return new ProgressViewHolder(v);
        }
    }

    @Override
    public int getItemViewType(int position) {
        return position < postsList.size() ? VIEW_ITEM : VIEW_PROGRESS;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof PostViewHolder) {
            PostViewHolder postViewHolder = (PostViewHolder) holder;
            postViewHolder.position = position;
            Image post = postsList.get(position);

            String imgUrl = post.getIsAlbum() ? ApiClient.getBackgroundImageUrl(post.getCover()) : post.getLink();
            Picasso.with(context)
                    .load(imgUrl)
                    .resize(Constants.ThumbnailMaxWidthInPx, 0)
                    .placeholder(R.drawable.gradient_circ)
                    .into(postViewHolder.postImage);
            postViewHolder.postTitle.setText(post.getTitle());
        } else {
            StaggeredGridLayoutManager.LayoutParams layoutParams = (StaggeredGridLayoutManager.LayoutParams) holder.itemView.getLayoutParams();
            layoutParams.setFullSpan(true);
            ((ProgressViewHolder) holder).progressBar.setIndeterminate(true);
        }
    }

    @Override
    public int getItemCount() {
        return postsList.size() + 1;
    }

    public interface OnPostClickListener {
        void onPostClick(Image image);
    }

    public class PostViewHolder extends RecyclerView.ViewHolder {

        public TextView postTitle;
        public ImageView postImage;
        public int position;

        public PostViewHolder(View view) {
            super(view);
            postTitle = (TextView) view.findViewById(R.id.post_col_title);
            postImage = (ImageView) view.findViewById(R.id.post_col_img);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(onPostClickListener != null) {
                        onPostClickListener.onPostClick(postsList.get(position));
                    }
                }
            });
        }
    }

    public class ProgressViewHolder extends RecyclerView.ViewHolder {
        public ProgressBar progressBar;
        public ProgressViewHolder(View view) {
            super(view);
            progressBar = (ProgressBar) view.findViewById(R.id.progressbar);
        }
    }






}
