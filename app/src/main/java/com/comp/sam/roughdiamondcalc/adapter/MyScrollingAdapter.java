package com.comp.sam.roughdiamondcalc.adapter;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

/**
 * Created by shubh on 04-02-2017.
 */

public class MyScrollingAdapter implements ScrollingTableAdapter {

    private Context mContext;

    public MyScrollingAdapter(Context context) {
        mContext = context;
    }

    @Override
    public int getColumnCount() {
        return 7;
    }

    @Override
    public int getRowCount() {
        return 5;
    }

    @Override
    public View getDataViewAt(int row, int column) {
        TextView textView = new TextView(mContext);
        textView.setText("Cell " + row + "," + column);
        Log.d("Adapter", row + " " + column);
        return textView;
    }

    @Override
    public View getHeaderViewAt(int column) {
        TextView textView = new TextView(mContext);
        textView.setText("Header " + column);
        return textView;
    }
}
