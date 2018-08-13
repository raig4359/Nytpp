package com.firsteconomy.nytapp.ui.top_stories;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.Menu;

import com.firsteconomy.nytapp.R;
import com.firsteconomy.nytapp.databinding.ActivityTopStoryBinding;
import com.firsteconomy.nytapp.ui.BaseActivity;

public class TopStoryActivity extends BaseActivity implements TopStoryFragment.OnTopStoryInteraction {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final ActivityTopStoryBinding topStoryBinding = DataBindingUtil
                .setContentView(this, R.layout.activity_top_story);
        setUpToolbar(topStoryBinding.toolbar);
        setTitle("NyTimes");
        SectionAdapter adapter = new SectionAdapter(getSupportFragmentManager(),
                getResources().getStringArray(R.array.top_story_sections));
        topStoryBinding.vpTopStory.setAdapter(adapter);
        topStoryBinding.tbTopStory.setupWithViewPager(topStoryBinding.vpTopStory);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.home_menu, menu);
        return true;
    }


}
