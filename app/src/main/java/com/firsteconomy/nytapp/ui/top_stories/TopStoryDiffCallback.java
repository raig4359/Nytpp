package com.firsteconomy.nytapp.ui.top_stories;

import android.support.v7.util.DiffUtil;

import com.firsteconomy.nytapp.model.TopStory;

import java.util.List;

/**
 * Created by Gaurav on 13-08-2018.
 */

public class TopStoryDiffCallback extends DiffUtil.Callback {

    private List<TopStory> mOldList;
    private List<TopStory> mNewList;

    public TopStoryDiffCallback(List<TopStory> mOldList, List<TopStory> mNewList) {
        this.mOldList = mOldList;
        this.mNewList = mNewList;
    }

    @Override
    public int getOldListSize() {
        return mOldList.size();
    }

    @Override
    public int getNewListSize() {
        return mNewList.size();
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        return mOldList.get(oldItemPosition).shortUrl.equals(mNewList.get(newItemPosition).shortUrl);
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        return true;
    }

}
