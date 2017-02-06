package com.comp.sam.roughdiamondcalc.models;

import android.support.annotation.NonNull;
import android.view.View;
import android.widget.TextView;

import com.bignerdranch.expandablerecyclerview.ChildViewHolder;
import com.comp.sam.roughdiamondcalc.R;

/**
 * Created by shubh on 04-02-2017.
 */

public class SummaryViewHolder extends ChildViewHolder {

    private TextView mRoughRateTextView;
    private TextView mPercentageTextView;
    private TextView mSizeTextView;
    private TextView mPolishedTextView;
    private TextView mLabourTextView;
    private TextView mLessTextView;

    /**
     * Default constructor.
     *
     * @param itemView The {@link View} being hosted in this ViewHolder
     */
    public SummaryViewHolder(@NonNull View itemView) {
        super(itemView);
        mRoughRateTextView = (TextView) itemView.findViewById(R.id.rough_rate_text_view);
        mPercentageTextView = (TextView) itemView.findViewById(R.id.percentage_text_view);
        mSizeTextView = (TextView) itemView.findViewById(R.id.size_text_view);
        mPolishedTextView = (TextView) itemView.findViewById(R.id.polished_weight_text_view);
        mLabourTextView = (TextView) itemView.findViewById(R.id.labour_text_view);
        mLessTextView = (TextView) itemView.findViewById(R.id.less_text_view);
    }


    public void bind(RoughDiamond child) {
        mRoughRateTextView.setText(getValue(child.getRoughRate()));
        mPercentageTextView.setText(getValue(child.getPercentage() * 100));
        mSizeTextView.setText(getValue(child.getSize()));
        mPolishedTextView.setText(getValue(child.getPolishedWeight() * 100));
        mLabourTextView.setText(getValue(child.getLabour()));
        mLessTextView.setText(getValue(child.getLess() * 100));
    }

    private String getValue(double value){
        return String.format("%.2f",value);
    }
}
