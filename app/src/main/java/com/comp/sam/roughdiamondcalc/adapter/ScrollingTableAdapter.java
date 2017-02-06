package com.comp.sam.roughdiamondcalc.adapter;

import android.view.View;

/**
 * Created by shubh on 04-02-2017.
 */

public interface ScrollingTableAdapter {
    int getColumnCount();
    int getRowCount();
    View getDataViewAt(int row, int column);
    View getHeaderViewAt(int column);
}
