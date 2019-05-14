package com.firsteconomy.nytapp.ui.movie_review;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.firsteconomy.nytapp.databinding.LayoutMovieReviewBinding;
import com.firsteconomy.nytapp.model.MovieReview;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Gaurav on 12-12-2018.
 */
public class MovieReviewsAdapter extends RecyclerView.Adapter<MovieReviewsAdapter.MovieReviewViewHolder> {

    private List<MovieReview> reviews = new ArrayList<>();

    @NonNull
    @Override
    public MovieReviewViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MovieReviewViewHolder(LayoutMovieReviewBinding.inflate(LayoutInflater
                .from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MovieReviewViewHolder holder, int position) {
        holder.setBinding(reviews.get(position));
    }

    @Override
    public int getItemCount() {
        return reviews.size();
    }

    public void updateList(List<MovieReview> data) {
        reviews.addAll(data);
        notifyDataSetChanged();
    }

    static class MovieReviewViewHolder extends RecyclerView.ViewHolder {

        private final LayoutMovieReviewBinding binding;

        public MovieReviewViewHolder(LayoutMovieReviewBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        void setBinding(MovieReview review) {
            if (review.multimedia != null)
                Glide.with(binding.getRoot().getContext())
                        .load(review.multimedia.src)
                        .transition(DrawableTransitionOptions.withCrossFade(400))
                        .into(binding.ivMovie);
            binding.setReview(review);
            binding.executePendingBindings();
        }

    }

}
