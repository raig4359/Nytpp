package com.firsteconomy.nytapp.ui.common;

import android.databinding.BindingAdapter;
import android.view.View;
import android.widget.ImageView;

import com.firsteconomy.nytapp.R;
import com.firsteconomy.nytapp.network.Resource;
import com.firsteconomy.nytapp.network.Status;

import java.util.List;

import static com.firsteconomy.nytapp.network.Status.LOADING;
import static com.firsteconomy.nytapp.network.Status.SUCCESS;

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

    @BindingAdapter("visibleGone")
    public static void setErrorViewVisibility(View view, Resource resource) {
        if (resource.status == LOADING || resource.status == SUCCESS) {
            view.setVisibility(View.GONE);
        } else {
            if (resource.data == null) {
                view.setVisibility(View.VISIBLE);
            } else if (resource.data instanceof List && ((List) resource.data).size() == 0) {
                view.setVisibility(View.VISIBLE);
            } else {
                view.setVisibility(View.GONE);
            }
        }
    }

}
