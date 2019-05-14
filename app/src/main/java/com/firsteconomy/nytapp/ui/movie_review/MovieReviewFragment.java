package com.firsteconomy.nytapp.ui.movie_review;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.firsteconomy.nytapp.databinding.FragmentReviewBinding;
import com.firsteconomy.nytapp.model.MovieReview;
import com.firsteconomy.nytapp.network.Resource;
import com.firsteconomy.nytapp.ui.common.RetryCallback;

import java.util.List;

public class MovieReviewFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private MovieReviewViewModel reviewViewModel;

    private String mParam1;
    private String mParam2;

    private ReviewInteraction mListener;
    private FragmentReviewBinding binding;
    private Observer<Resource<List<MovieReview>>> observer;
    private MovieReviewsAdapter reviewAdapter;

    public MovieReviewFragment() {
        // Required empty public constructor
    }

    public static MovieReviewFragment newInstance(String param1, String param2) {
        MovieReviewFragment fragment = new MovieReviewFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        MovieReviewViewModel.Factory factory = new MovieReviewViewModel.Factory(getContext());
        reviewViewModel = ViewModelProviders.of(this, factory).get(MovieReviewViewModel.class);
        initObserver();
    }

    private void initObserver() {
        observer = new Observer<Resource<List<MovieReview>>>() {
            @Override
            public void onChanged(@Nullable Resource<List<MovieReview>> listResource) {
                List<MovieReview> reviews = listResource.data == null ? null :
                        listResource.data.size() == 0 ? null : listResource.data;
                binding.setStoriesResource(listResource);
                binding.executePendingBindings();
                if (listResource.data != null)
                    reviewAdapter.updateList(listResource.data);
            }
        };
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentReviewBinding.inflate(inflater, container, false);
        binding.setRetryCallback(new RetryCallback() {
            @Override
            public void retry() {
                reviewViewModel.getReviewList().observe(MovieReviewFragment.this, observer);
            }
        });

        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        reviewAdapter = new MovieReviewsAdapter();
        binding.rvMovie.setAdapter(reviewAdapter);
        reviewViewModel.getReviewList().observe(this, observer);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof ReviewInteraction) {
            mListener = (ReviewInteraction) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement ReviewInteraction");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface ReviewInteraction {

    }

}
