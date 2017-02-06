package com.comp.sam.roughdiamondcalc.listener;

import android.widget.SeekBar;
import android.widget.TextView;

/**
 * Created by shubh on 03-02-2017.
 */

public class ChangeTextOnSeekChangeListener implements SeekBar.OnSeekBarChangeListener {

    private TextView mTarget;
    private int mMinimum;

    public ChangeTextOnSeekChangeListener(TextView target , int minimum){
        mTarget = target;
        mMinimum = minimum;
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        progress = mMinimum + progress;
        mTarget.setText(String.format("%03d",progress));
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }
}
