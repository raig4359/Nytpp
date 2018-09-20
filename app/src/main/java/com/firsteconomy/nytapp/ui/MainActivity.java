package com.firsteconomy.nytapp.ui;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.firsteconomy.nytapp.R;
import com.firsteconomy.nytapp.databinding.ActivityMainBinding;
import com.firsteconomy.nytapp.ui.books.BookFragment;
import com.firsteconomy.nytapp.ui.common.BottomNavigationViewHelper;
import com.firsteconomy.nytapp.ui.top_stories.TopStoryFragment;

public class MainActivity extends BaseActivity implements TopStoryFragment.OnTopStoryInteraction,
        BookFragment.BookInteraction {

    private static final String TOP_STORY_FRAG = "TopStoryFrag";
    private static final String BOOK_FRAG = "BookFrag";
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil
                .setContentView(this, R.layout.activity_main);
        setUpToolbar(binding.toolbar);
        setTitle("NyTimes");
        setBottomNavigationListener();
        BottomNavigationViewHelper.disableShiftMode(binding.bottomNavigation);
        showTopStoryFragment();
    }

    private void setBottomNavigationListener() {
        binding.bottomNavigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.action_top_stories:
                        showTopStoryFragment();
                        break;
                    case R.id.action_books:
                        showBookFragment();
                        break;
                    case R.id.action_movie_review:
                        break;
                    case R.id.action_most_popular:
                        break;
                    case R.id.action_archive:
                        break;
                }
                return true;
            }
        });
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

    public void showBookFragment() {
        FragmentManager manager = getSupportFragmentManager();
        boolean fragmentPopped = manager.popBackStackImmediate(BOOK_FRAG, 0);
        if (!fragmentPopped && manager.findFragmentByTag(BOOK_FRAG) == null) {
            BookFragment fragment = BookFragment.newInstance("", "");
            manager.beginTransaction()
                    .replace(R.id.frame, fragment, BOOK_FRAG)
                    .addToBackStack(BOOK_FRAG)
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
