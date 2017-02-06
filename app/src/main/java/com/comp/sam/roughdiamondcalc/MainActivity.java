package com.comp.sam.roughdiamondcalc;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.UiThread;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.bignerdranch.expandablerecyclerview.ExpandableRecyclerAdapter;
import com.comp.sam.roughdiamondcalc.listener.ChangeTextOnSeekChangeListener;
import com.comp.sam.roughdiamondcalc.models.RoughDiamond;
import com.comp.sam.roughdiamondcalc.verticalExpandable.RoughDiamondAdapter;

import java.util.ArrayList;
import java.util.List;

import static com.comp.sam.roughdiamondcalc.Settings.LESS_MAX;
import static com.comp.sam.roughdiamondcalc.Settings.LESS_MIN;
import static com.comp.sam.roughdiamondcalc.Settings.PER_MAX;
import static com.comp.sam.roughdiamondcalc.Settings.PER_MIN;
import static com.comp.sam.roughdiamondcalc.Settings.POLISHED_MAX;
import static com.comp.sam.roughdiamondcalc.Settings.POLISHED_MIN;
import static com.comp.sam.roughdiamondcalc.Settings.PREFS_NAME;
import static com.comp.sam.roughdiamondcalc.Settings.SIZE_MAX;
import static com.comp.sam.roughdiamondcalc.Settings.SIZE_MIN;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final int LABOUR_COUNT = 178;
    private TextView mAnswerValue;
    private EditText mRoughRate;
    private SeekBar mSize;
    private TextView mSizeValue;
    private SeekBar mPercentage;
    private TextView mPercentageValue;
    private EditText mLabour;
    private SeekBar mPolished;
    private TextView mPolishedValue;
    private SeekBar mLess;
    private TextView mLessValue;
    private Button mCalculate;
    private RoughDiamond mRoughDiamond;
    private RoughDiamondAdapter mAdapter;
    private List<RoughDiamond> mRoughDiamonds;
    private SharedPreferences mSharedPref;
    private RelativeLayout mRelativeRoot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mSharedPref = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);

        mRelativeRoot = (RelativeLayout) findViewById(R.id.data_layer);
        mRoughRate = (EditText) findViewById(R.id.rough_rate_value);

        mSize = (SeekBar) findViewById(R.id.size_text_seek);
        mSizeValue = (TextView) findViewById(R.id.size_text_value);

        mPercentage = (SeekBar) findViewById(R.id.percentage_text_seek);
        mPercentageValue = (TextView) findViewById(R.id.percentage_text_value);

        mLabour = (EditText) findViewById(R.id.labour_1_value);

        mPolished = (SeekBar) findViewById(R.id.polished_weight_text_seek);
        mPolishedValue = (TextView) findViewById(R.id.polished_weight_text_value);


        mLess = (SeekBar) findViewById(R.id.less_text_seek);
        mLessValue = (TextView) findViewById(R.id.less_text_value);

        mCalculate = (Button) findViewById(R.id.calculate_button);
        mCalculate.setOnClickListener(this);

        mAnswerValue = (TextView) findViewById(R.id.answer0);


        mRoughDiamonds = new ArrayList<>();

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        mAdapter = new RoughDiamondAdapter(this, mRoughDiamonds);
        mAdapter.setExpandCollapseListener(new ExpandableRecyclerAdapter.ExpandCollapseListener() {
            @UiThread
            @Override
            public void onParentExpanded(int parentPosition) {
                mRelativeRoot.setVisibility(View.GONE);
            }

            @UiThread
            @Override
            public void onParentCollapsed(int parentPosition) {
            }
        });

        recyclerView.setAdapter(mAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        mCount = 0;


    }

    private int mCount;

    private int getProgressMaxValue(float start, float end) {
        return (int) (end - start);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.calculate_button) {
            mRoughDiamond = new RoughDiamond();
            mRoughDiamond.setRoughRate(getDoubleValue(mRoughRate.getText().toString()));
            mRoughDiamond.setSize(getDoubleValue(mSizeValue.getText().toString()));
            mRoughDiamond.setLabour(getDoubleValue(mLabour.getText().toString()));
            mRoughDiamond.setPercentage(getDoubleValue(mPercentageValue.getText().toString()));
            mRoughDiamond.setPolishedWeight(getDoubleValue(mPolishedValue.getText().toString()));
            mRoughDiamond.setLess(getDoubleValue(mLessValue.getText().toString()));
            mAnswerValue.setText(String.format("%.2f", mRoughDiamond.getPrice()));
            mRoughDiamonds.add(mRoughDiamond);
            mAdapter.notifyParentInserted(mCount++);
            //TODO: Save result and Parameters
        }
    }

    private double getDoubleValue(String value) {
        try {
            return Double.parseDouble(value);
        } catch (Exception exception) {
            return 0;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (R.id.settings_button == item.getItemId()) {
            Intent intent = new Intent(getApplicationContext(), Settings.class);
            startActivity(intent);
        }
        return true;
    }

    @Override
    protected void onResume() {
        super.onResume();
        mSize.setOnSeekBarChangeListener(new ChangeTextOnSeekChangeListener(mSizeValue, mSharedPref.getInt(SIZE_MIN, 0)));
        mSize.setMax(getProgressMaxValue(mSharedPref.getInt(SIZE_MIN, 0), mSharedPref.getInt(SIZE_MAX, 100)));

        mPercentage.setOnSeekBarChangeListener(new ChangeTextOnSeekChangeListener(mPercentageValue, mSharedPref.getInt(PER_MIN, 0)));
        mPercentage.setMax(getProgressMaxValue(mSharedPref.getInt(PER_MIN, 0), mSharedPref.getInt(PER_MAX, 100)));

        mPolished.setOnSeekBarChangeListener(new ChangeTextOnSeekChangeListener(mPolishedValue, mSharedPref.getInt(POLISHED_MIN, 0)));
        mPolished.setMax(getProgressMaxValue(mSharedPref.getInt(POLISHED_MIN, 0), mSharedPref.getInt(POLISHED_MAX, 100)));

        mLess.setOnSeekBarChangeListener(new ChangeTextOnSeekChangeListener(mLessValue, mSharedPref.getInt(LESS_MIN, 0)));
        mLess.setMax(getProgressMaxValue(mSharedPref.getInt(LESS_MIN, 0), mSharedPref.getInt(LESS_MAX, 100)));
    }

    @Override
    public void onBackPressed() {
        if (mRelativeRoot.getVisibility() == View.GONE) {
            mRelativeRoot.setVisibility(View.VISIBLE);
            return;
        }
        super.onBackPressed();
    }
}
