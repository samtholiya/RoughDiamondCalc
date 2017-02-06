package com.comp.sam.roughdiamondcalc;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.comp.sam.roughdiamondcalc.listener.ChangePreferenceRangeListener;
import com.comp.sam.roughdiamondcalc.listener.ChangeTextOnRangeChangeListener;
import com.comp.sam.roughdiamondcalc.view.RangeSeekBar;

public class Settings extends AppCompatActivity {

    public static final String PER_MIN = "com.sam.RoughDiamond.per_min";
    public static final String PER_MAX = "com.sam.RoughDiamond.per_max";
    public static final String PREFS_NAME = "com.sam.RoughDiamond";
    public static final String SIZE_MIN = "com.sam.RoughDiamond.siz_min";
    public static final String SIZE_MAX = "com.sam.RoughDiamond.siz_max";
    public static final String POLISHED_MIN = "com.sam.RoughDiamond.pol_min";
    public static final String POLISHED_MAX = "com.sam.RoughDiamond.pol_max";
    public static final String LESS_MIN = "com.sam.RoughDiamond.less_min";
    public static final String LESS_MAX = "com.sam.RoughDiamond.less_max";


    private SharedPreferences mSharedPreference;
    private SharedPreferences.Editor mEditor;
    private RangeSeekBar mPercentageSettings;
    private TextView mPercentageText;
    private RangeSeekBar mPolishedSettings;
    private TextView mPolishedText;
    private RangeSeekBar mLessSettings;
    private TextView mLessText;

    private EditText mSizeMin;
    private EditText mSizeMax;

    private int tempMin;
    private int tempMax;
    boolean isTempSaved;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        mSharedPreference = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);


        mPercentageText = (TextView) findViewById(R.id.percentage_text_settings);
        mPercentageSettings = (RangeSeekBar) findViewById(R.id.percentage_settings);
        ChangeTextOnRangeChangeListener percentageChange = new ChangeTextOnRangeChangeListener(
                mPercentageText
                , getString(R.string.percentage));
        mPercentageSettings.setOnRangeSeekbarChangeListener(percentageChange);
        mPercentageSettings.setOnRangeSeekbarFinalValueListener(
                new ChangePreferenceRangeListener(
                        getApplicationContext()
                        , PREFS_NAME
                        , PER_MIN
                        , PER_MAX));
        if (mSharedPreference.contains(PER_MIN)) {
            mPercentageSettings.setMinSeekValue(mSharedPreference.getInt(PER_MIN, 0));
            mPercentageSettings.setMaxSeekValue(mSharedPreference.getInt(PER_MAX, 100));
        }

        isTempSaved = true;
        mPolishedText = (TextView) findViewById(R.id.polished_weight_text_settings);
        mPolishedSettings = (RangeSeekBar) findViewById(R.id.polished_weight_settings);
        ChangeTextOnRangeChangeListener polishedChange = new ChangeTextOnRangeChangeListener(
                mPolishedText
                , getString(R.string.polished_weight));
        mPolishedSettings.setOnRangeSeekbarChangeListener(polishedChange);
        mPolishedSettings.setOnRangeSeekbarFinalValueListener(
                new ChangePreferenceRangeListener(
                        getApplicationContext()
                        , PREFS_NAME
                        , POLISHED_MIN
                        , POLISHED_MAX));
        if (mSharedPreference.contains(POLISHED_MIN)) {
            mPolishedSettings.setMinSeekValue(mSharedPreference.getInt(POLISHED_MIN, 0));
            mPolishedSettings.setMaxSeekValue(mSharedPreference.getInt(POLISHED_MAX, 100));
        }


        mLessText = (TextView) findViewById(R.id.less_text_settings);
        mLessSettings = (RangeSeekBar) findViewById(R.id.less_settings);
        ChangeTextOnRangeChangeListener lessChange = new ChangeTextOnRangeChangeListener(
                mLessText
                , getString(R.string.less));
        mLessSettings.setOnRangeSeekbarChangeListener(lessChange);
        mLessSettings.setOnRangeSeekbarFinalValueListener(
                new ChangePreferenceRangeListener(
                        getApplicationContext()
                        , PREFS_NAME
                        , LESS_MIN
                        , LESS_MAX));
        if (mSharedPreference.contains(LESS_MIN)) {
            mLessSettings.setMinSeekValue(mSharedPreference.getInt(LESS_MIN, 0));
            mLessSettings.setMaxSeekValue(mSharedPreference.getInt(LESS_MAX, 100));
        }

        mSizeMin = (EditText) findViewById(R.id.size_settings_min);
        mSizeMax = (EditText) findViewById(R.id.size_settings_max);
        if (mSharedPreference.contains(SIZE_MIN)) {
            tempMin = mSharedPreference.getInt(SIZE_MIN, 0);
            mSizeMin.setText(String.valueOf(mSharedPreference.getInt(SIZE_MIN, 0)));
        }
        if (mSharedPreference.contains(SIZE_MAX)) {
            tempMax = mSharedPreference.getInt(SIZE_MAX, 100);
            mSizeMax.setText(String.valueOf(mSharedPreference.getInt(SIZE_MAX, 100)));
        }

        mSizeMin.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!s.toString().isEmpty()) {
                    if (!s.toString().isEmpty()) {
                        int saved = Integer.valueOf(s.toString());
                        if (saved < mSharedPreference.getInt(SIZE_MAX, 0)) {
                            mEditor = mSharedPreference.edit();
                            mEditor.putInt(SIZE_MIN, saved);
                            mEditor.putInt(SIZE_MAX, tempMax);
                            mEditor.commit();
                            isTempSaved = true;
                        } else {
                            isTempSaved = false;
                        }
                        tempMin = saved;
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        mSizeMax.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!s.toString().isEmpty()) {
                    int saved = Integer.valueOf(s.toString());
                    if (saved > mSharedPreference.getInt(SIZE_MIN, 0)) {
                        mEditor = mSharedPreference.edit();
                        mEditor.putInt(SIZE_MIN, tempMin);
                        mEditor.putInt(SIZE_MAX, saved);
                        mEditor.commit();
                        isTempSaved = true;
                    } else {
                        isTempSaved = false;
                    }
                    tempMax = saved;
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (!isTempSaved)
            Toast.makeText(getApplicationContext(), "Size Range Not Saved", Toast.LENGTH_LONG).show();
    }
}
