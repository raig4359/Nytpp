package com.firsteconomy.nytapp.ui.common;

import android.databinding.BindingAdapter;
import android.widget.ImageView;

import com.firsteconomy.nytapp.R;
import com.firsteconomy.nytapp.network.Status;

/**
 * Created by Gaurav on 06-07-2018.
 */

public class DataBindingAdapters {

    @BindingAdapter("errorStatus")
    public static void setErrorImage(ImageView image, Status status) {
        if (status != null)
            switch (status) {
                case LOADING:
                    image.setImageDrawable(null);
                    break;

                case SUCCESS:
                    image.setImageDrawable(null);
                    break;

                case NO_INTERNET:
                    image.setImageResource(R.drawable.no_internet_two);
                    break;

                case ERROR:
                    image.setImageResource(R.drawable.error_icon);
                    break;

                case FAILURE:
                    image.setImageResource(R.drawable.ic_failure);
                    break;
            }
    }


}
