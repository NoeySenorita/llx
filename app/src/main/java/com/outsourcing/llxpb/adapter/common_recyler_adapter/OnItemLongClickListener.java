package com.outsourcing.llxpb.adapter.common_recyler_adapter;

import android.view.View;

public interface OnItemLongClickListener<DATA> {
    boolean onItemLongClick(View view, DATA data, int position);
}
