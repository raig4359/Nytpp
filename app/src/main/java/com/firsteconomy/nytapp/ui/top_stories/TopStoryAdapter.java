package com.firsteconomy.nytapp.ui.top_stories;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.firsteconomy.nytapp.R;
import com.firsteconomy.nytapp.databinding.LayoutTopStoryBinding;
import com.firsteconomy.nytapp.model.TopStory;
import com.firsteconomy.nytapp.util.DateFormatter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Gaurav on 07-05-2018.
 */

public class TopStoryAdapter extends RecyclerView.Adapter<TopStoryAdapter.TopStoryViewHolder> {

    private Context context;
    private ArrayList<TopStory> topStories = new ArrayList<>();

    public TopStoryAdapter(Context context) {
        this.context = context;
    }

    public void updateList(List<TopStory> newStoryList) {
        topStories.addAll(newStoryList);
        notifyDataSetChanged();
    }

    public static class TopStoryViewHolder extends RecyclerView.ViewHolder {

        LayoutTopStoryBinding binding;

        public TopStoryViewHolder(LayoutTopStoryBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void setBinding(Context context, TopStory topStory) {
            binding.setTopStory(topStory);
            String imgUrl = topStory.multimedia.size()>=4?topStory.multimedia.get(4).url:"";
            Glide.with(context)
                    .load(imgUrl)
                    .into(binding.ivNews);
            binding.tvAuthorTimestamp.setText(context.getString(R.string.time_and_author_name,
                    topStory.byline, DateFormatter.getFormattedDate()));
            binding.executePendingBindings();
        }
    }

    @NonNull
    @Override
    public TopStoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        LayoutTopStoryBinding binding = LayoutTopStoryBinding.inflate(layoutInflater, parent, false);
        return new TopStoryViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull TopStoryViewHolder holder, int position) {
        holder.setBinding(context, topStories.get(position));
    }

    @Override
    public int getItemCount() {
        return topStories.size();
    }
}
