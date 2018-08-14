package com.firsteconomy.nytapp.ui;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.view.Menu;
import android.view.View;

import com.firsteconomy.nytapp.R;
import com.firsteconomy.nytapp.databinding.ActivityMainBinding;
import com.firsteconomy.nytapp.ui.common.BottomNavigationViewHelper;
import com.firsteconomy.nytapp.ui.top_stories.TopStoryFragment;

public class MainActivity extends BaseActivity implements TopStoryFragment.OnTopStoryInteraction {

    private static final String TOP_STORY_FRAG = "TopStoryFrag";
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil
                .setContentView(this, R.layout.activity_main);
        setUpToolbar(binding.toolbar);
        setTitle("NyTimes");
        BottomNavigationViewHelper.disableShiftMode(binding.bottomNavigation);
        showTopStoryFragment();
    }

    public void showTopStoryFragment() {
        FragmentManager manager = getSupportFragmentManager();
        boolean fragmentPopped = manager.popBackStackImmediate(TOP_STORY_FRAG, 0);
        if (!fragmentPopped && manager.findFragmentByTag(TOP_STORY_FRAG) == null) {
            TopStoryFragment fragment = TopStoryFragment.newInstance();
            manager.beginTransaction()
                    .replace(R.id.frame, fragment, TOP_STORY_FRAG)
                    .addToBackStack(TOP_STORY_FRAG)
                    .commit();
        }
    }

    public void showCancelButton() {
        binding.bottomNavigation.setVisibility(View.GONE);
        binding.clRootCancel.setVisibility(View.VISIBLE);
    }

    public void hideCancelButton() {
        binding.bottomNavigation.setVisibility(View.VISIBLE);
        binding.clRootCancel.setVisibility(View.GONE);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.home_menu, menu);
        return true;
    }

    @Override
    public void onBackPressed() {
        if (getSupportFragmentManager().getBackStackEntryCount() == 1) {
            finish();
        } else {
            super.onBackPressed();
        }
    }

}
