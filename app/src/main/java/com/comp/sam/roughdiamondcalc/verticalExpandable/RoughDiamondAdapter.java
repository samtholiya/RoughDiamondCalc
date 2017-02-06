package com.comp.sam.roughdiamondcalc.verticalExpandable;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bignerdranch.expandablerecyclerview.ExpandableRecyclerAdapter;
import com.comp.sam.roughdiamondcalc.R;
import com.comp.sam.roughdiamondcalc.models.PriceViewHolder;
import com.comp.sam.roughdiamondcalc.models.RoughDiamond;
import com.comp.sam.roughdiamondcalc.models.SummaryViewHolder;

import java.util.List;

public class RoughDiamondAdapter extends ExpandableRecyclerAdapter<RoughDiamond, RoughDiamond, PriceViewHolder, SummaryViewHolder> {

    private LayoutInflater mInflater;
    private List<RoughDiamond> mRoughList;

    public RoughDiamondAdapter(Context context, @NonNull List<RoughDiamond> roughList) {
        super(roughList);
        mRoughList = roughList;
        mInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public PriceViewHolder onCreateParentViewHolder(@NonNull ViewGroup parentViewGroup, int viewType) {
        View view;
        view = mInflater.inflate(R.layout.price_view, parentViewGroup, false);
        return new PriceViewHolder(view);
    }

    @NonNull
    @Override
    public SummaryViewHolder onCreateChildViewHolder(@NonNull ViewGroup childViewGroup, int viewType) {
        View view;
        view = mInflater.inflate(R.layout.summary_view, childViewGroup, false);
        return new SummaryViewHolder(view);
    }

    @Override
    public void onBindParentViewHolder(@NonNull PriceViewHolder parentViewHolder, int parentPosition, @NonNull RoughDiamond parent) {
        parentViewHolder.bind(parent);
    }

    @Override
    public void onBindChildViewHolder(@NonNull SummaryViewHolder childViewHolder, int parentPosition, int childPosition, @NonNull RoughDiamond child) {
        childViewHolder.bind(child);
    }


}
