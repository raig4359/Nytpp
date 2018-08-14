package com.firsteconomy.nytapp.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Spannable;
import android.text.SpannableString;

import com.firsteconomy.nytapp.R;
import com.firsteconomy.nytapp.ui.common.TypefaceSpan;

public class BaseActivity extends AppCompatActivity {

    private TypefaceSpan typefaceSpan;
    private final String CHELTENHAM_NORMAL = "CheltenhamNormal";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        typefaceSpan = new TypefaceSpan(this, CHELTENHAM_NORMAL, R.font.nyt_font_family);
    }

    protected void setUpToolbar(Toolbar toolbar) {
        toolbar.setLogo(R.drawable.nyt_logo);
        toolbar.setTitleMarginStart(getMeasurementInPixels(25));
        setSupportActionBar(toolbar);
    }

    protected void setTitle(String title) {
        SpannableString spannableTitle = new SpannableString(title);
        spannableTitle.setSpan(typefaceSpan, 0, title.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        super.setTitle(spannableTitle);
    }

    public int getMeasurementInPixels(int dpUnits) {
        float screenDensity = getResources().getDisplayMetrics().density;
        return (int) (dpUnits * screenDensity);
    }

}
