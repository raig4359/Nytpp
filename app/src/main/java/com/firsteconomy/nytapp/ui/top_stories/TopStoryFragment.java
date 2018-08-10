package com.firsteconomy.nytapp.ui.top_stories;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.firsteconomy.nytapp.databinding.FragmentTopStoryBinding;
import com.firsteconomy.nytapp.network.Resource;
import com.firsteconomy.nytapp.network_responses.TopStoriesResponse;
import com.firsteconomy.nytapp.ui.common.RetryCallback;

public class TopStoryFragment extends Fragment {

    private String TAG = "TopStoryFrag";
    private static final String ARG_SECTION = "section";

    private String section;

    private OnTopStoryInteraction mListener;
    private TopStoryViewModel topStoryViewModel;
    private GridLayoutManager manager;
    private TopStoryAdapter storyAdapter;
    private FragmentTopStoryBinding binding;

    public TopStoryFragment() {
        // Required empty public constructor
    }

    public static TopStoryFragment newInstance(String section) {
        TopStoryFragment fragment = new TopStoryFragment();
        Bundle args = new Bundle();
        args.putString(ARG_SECTION, section);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            section = getArguments().getString(ARG_SECTION);
        }
        TopStoryViewModel.Factory factory = new TopStoryViewModel.Factory(getContext());
        topStoryViewModel = ViewModelProviders.of(this, factory).get(TopStoryViewModel.class);
        initObserver();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentTopStoryBinding.inflate(inflater, container, false);
        binding.setRetryCallback(new RetryCallback() {
            @Override
            public void retry() {
                topStoryViewModel.retry();
            }
        });
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        manager = new GridLayoutManager(getActivity(), 1);
        binding.rvTopStories.setLayoutManager(manager);
        storyAdapter = new TopStoryAdapter(getActivity());
        binding.rvTopStories.setAdapter(storyAdapter);
        Log.e(TAG, "onActivityCreated: section - setting section from fragment - " + section);
        topStoryViewModel.setSection(section);
    }

    private void initObserver() {
        topStoryViewModel.getTopStoryData().observe(this, new Observer<Resource<TopStoriesResponse>>() {
            @Override
            public void onChanged(@Nullable Resource<TopStoriesResponse> topStoriesResponseResource) {
                binding.setStoriesResource(topStoriesResponseResource);
                binding.executePendingBindings();
                if (topStoriesResponseResource.data != null) {
                    storyAdapter.updateList(topStoriesResponseResource.data.topStories);
                }
            }
        });
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnTopStoryInteraction) {
            mListener = (OnTopStoryInteraction) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnTopStoryInteraction");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    interface OnTopStoryInteraction {

    }
}
