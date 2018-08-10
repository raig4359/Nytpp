package com.firsteconomy.nytapp.ui.top_stories;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.firsteconomy.nytapp.R;
import com.firsteconomy.nytapp.databinding.ActivityTopStoryBinding;

public class TopStoryActivity extends AppCompatActivity implements TopStoryFragment.OnTopStoryInteraction{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final ActivityTopStoryBinding topStoryBinding = DataBindingUtil
                .setContentView(this, R.layout.activity_top_story);
        SectionAdapter adapter = new SectionAdapter(getSupportFragmentManager(),
                getResources().getStringArray(R.array.top_story_sections));
        topStoryBinding.vpTopStory.setAdapter(adapter);
        topStoryBinding.tbTopStory.setupWithViewPager(topStoryBinding.vpTopStory);
    }
}
