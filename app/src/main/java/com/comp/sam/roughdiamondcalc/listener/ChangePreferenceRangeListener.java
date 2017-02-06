package com.comp.sam.roughdiamondcalc.listener;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.comp.sam.roughdiamondcalc.coordinators.OnRangeSeekbarFinalValueListener;

/**
 * Created by shubh on 05-02-2017.
 */

public class ChangePreferenceRangeListener implements OnRangeSeekbarFinalValueListener {

    private Context mContext;
    private String mPrefKeyMin;
    private String mPrefKeyMax;
    private String mAppKey;
    private SharedPreferences.Editor mEditor;
    private SharedPreferences mSharedPreferences;
    public ChangePreferenceRangeListener(Context context,String appKey,String prefKeyMin,String prefKeyMax){
        mContext = context;
        mAppKey = appKey;
        mPrefKeyMin = prefKeyMin;
        mPrefKeyMax = prefKeyMax;
        mSharedPreferences = mContext.getSharedPreferences(appKey,Context.MODE_PRIVATE);
    }

    @Override
    public void finalValue(Number minValue, Number maxValue, boolean byUser) {
        Log.d("Final",minValue.toString()+" max "+maxValue.toString());
        if(byUser) {
            mEditor = mSharedPreferences.edit();
            mEditor.putInt(mPrefKeyMin, minValue.intValue());
            mEditor.putInt(mPrefKeyMax, maxValue.intValue());
            mEditor.commit();
        }
    }
}
