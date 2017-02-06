package com.comp.sam.roughdiamondcalc.listener;

import android.widget.TextView;

import com.comp.sam.roughdiamondcalc.coordinators.OnRangeSeekbarChangeListener;

/**
 * Created by shubh on 05-02-2017.
 */

public class ChangeTextOnRangeChangeListener implements OnRangeSeekbarChangeListener {

    private TextView mTitleTextView;
    private String mTitle;

    public ChangeTextOnRangeChangeListener(TextView titleTextView, String title) {
        mTitleTextView = titleTextView;
        mTitle = title;
    }

    @Override
    public void valueChanged(Number minValue, Number maxValue, boolean byUser) {
        mTitleTextView.setText(String.format(mTitle + " Min: %03d" + " Max: %03d", minValue.intValue(), maxValue.intValue()));
    }
}
