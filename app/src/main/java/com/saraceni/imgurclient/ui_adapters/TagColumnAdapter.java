package com.saraceni.imgurclient.ui_adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.saraceni.imgurclient.R;
import com.saraceni.imgurclient.api.client.ApiClient;
import com.saraceni.imgurclient.api.response.common.Tag;
import com.saraceni.imgurclient.util.Constants;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by rafaelgontijo on 13/08/17.
 */
public class TagColumnAdapter extends RecyclerView.Adapter<TagColumnAdapter.TagColViewHolder> {

    List<Tag> tagsList;
    private Context context;
    private OnTagClickListener onTagClickListener;

    public TagColumnAdapter(Context context, List<Tag> tagsList) {
        this.tagsList = tagsList;
        this.context = context;
    }

    public void setOnTagClickListener(OnTagClickListener onTagClickListener) {
        this.onTagClickListener = onTagClickListener;
    }

    @Override
    public TagColViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.tag_column, parent, false);

        return new TagColViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(TagColViewHolder holder, int position) {
        holder.position = position;
        Tag tag = tagsList.get(position);
        holder.tagName.setText(tag.getDisplayName());
        Picasso.with(context)
                .load(ApiClient.getBackgroundImageUrl(tag.getBackgroundHash()))
                .resize(Constants.TagMaxWidthInPx, 0)
                .into(holder.tagBackground);
    }

    @Override
    public int getItemCount() {
        return tagsList.size();
    }

    public interface OnTagClickListener {
        void onTagClick(Tag tag);
    }

    public class TagColViewHolder extends RecyclerView.ViewHolder {
        public TextView tagName;
        public ImageView tagBackground;
        public int position;

        public TagColViewHolder(View view) {
            super(view);
            tagName = (TextView) view.findViewById(R.id.tag_col_name);
            tagBackground = (ImageView) view.findViewById(R.id.tag_col_background);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(onTagClickListener != null) {
                        onTagClickListener.onTagClick(tagsList.get(position));
                    }
                }
            });
        }
    }
}
