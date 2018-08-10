package com.firsteconomy.nytapp.ui.top_stories;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Created by Gaurav on 10-08-2018.
 */

public class SectionAdapter extends FragmentStatePagerAdapter {

    private String sections[];

    SectionAdapter(FragmentManager fm, String[] sections) {
        super(fm);
        this.sections = sections;
    }

    @Override
    public Fragment getItem(int position) {
        return TopStoryFragment.newInstance(sections[position]);
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return sections[position];
    }

    @Override
    public int getCount() {
        return sections.length;
    }

}
