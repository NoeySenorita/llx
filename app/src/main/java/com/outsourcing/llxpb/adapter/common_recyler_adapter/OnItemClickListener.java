package com.outsourcing.llxpb.adapter.common_recyler_adapter;

import android.view.View;

public interface OnItemClickListener<DATA> {
    void onItemClick(View view, DATA data, int position);
}
