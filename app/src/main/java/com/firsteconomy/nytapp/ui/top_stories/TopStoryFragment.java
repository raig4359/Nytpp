package com.firsteconomy.nytapp.ui.top_stories;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.firsteconomy.nytapp.R;
import com.firsteconomy.nytapp.databinding.FragmentTopStoryBinding;

public class TopStoryFragment extends Fragment implements TopStoryView.StoryInteraction {

    private String TAG = "TopStoryFrag";

    private FragmentTopStoryBinding binding;
    private OnTopStoryInteraction mListener;

    public TopStoryFragment() {
        // Required empty public constructor
    }

    public static TopStoryFragment newInstance() {
        TopStoryFragment fragment = new TopStoryFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentTopStoryBinding.inflate(inflater, container, false);
        SectionAdapter adapter = new SectionAdapter(getChildFragmentManager(),
                getResources().getStringArray(R.array.top_story_sections));
        binding.vpTopStory.setAdapter(adapter);
        binding.tbTopStory.setupWithViewPager(binding.vpTopStory);
        return binding.getRoot();
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

   public interface OnTopStoryInteraction {

    }
}
