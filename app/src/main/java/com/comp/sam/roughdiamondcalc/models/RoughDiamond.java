package com.comp.sam.roughdiamondcalc.models;

import com.bignerdranch.expandablerecyclerview.model.Parent;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by shubh on 03-02-2017.
 */

public class RoughDiamond implements Parent<RoughDiamond> {
    private double mRoughRate;
    private double mPercentage;
    private double mSize;
    private double mLabour;
    private double mPolishedWeight;
    private double mLess;
    private final double PERCENTAGE = 100;

    public RoughDiamond()  {
    }

    public RoughDiamond(RoughDiamond roughDiamond) {
        setRoughRate(roughDiamond.getRoughRate());
        setPercentage(roughDiamond.getPercentage() * PERCENTAGE);
        setSize(roughDiamond.getSize());
        setLabour(roughDiamond.getLabour());
        setPolishedWeight(roughDiamond.getPolishedWeight() * PERCENTAGE);
        setLess(roughDiamond.getLess() * PERCENTAGE);
    }

    public double getRoughRate() {
        return mRoughRate;
    }

    public void setRoughRate(double mRoughRate) {
        this.mRoughRate = mRoughRate;
    }

    public double getPercentage() {
        return mPercentage / PERCENTAGE;
    }

    public void setPercentage(double mPercentage) {
        this.mPercentage = mPercentage;
    }

    public double getSize() {
        return mSize;
    }

    public void setSize(double mSize) {
        this.mSize = mSize;
    }

    public double getLabour() {
        return mLabour;
    }

    public void setLabour(double mLabour) {
        this.mLabour = mLabour;
    }

    public double getPolishedWeight() {
        return mPolishedWeight / PERCENTAGE;
    }

    public void setPolishedWeight(double mPolishedWeight) {
        this.mPolishedWeight = mPolishedWeight;
    }

    public double getLess() {
        return mLess;
    }

    public void setLess(double mLess) {
        this.mLess = mLess;
    }

    public double getPrice() {
        return (((getRoughRate() / getPercentage())
                + (getSize() * getLabour()))
                / getPolishedWeight())
                / ((100 - getLess()) / PERCENTAGE);
    }


    @Override
    public List<RoughDiamond> getChildList() {
        List<RoughDiamond> list = new ArrayList<RoughDiamond>();
        list.add(this);
        return list;
    }

    @Override
    public boolean isInitiallyExpanded() {
        return false;
    }
}
