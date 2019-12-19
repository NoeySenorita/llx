package com.outsourcing.llxpb.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.outsourcing.llxpb.R;
import com.outsourcing.llxpb.util.UserUtil;

/**
 * Created by DELL on 2019/8/6.
 */

public class SelectProjectAdapter extends BaseAdapter {
    Context mContext;
    public SelectProjectAdapter(Context context) {
        mContext = context;
    }

    @Override
    public int getCount() {
        return UserUtil.projectList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Holder holder;
        if(convertView == null){
            convertView = View.inflate(mContext, R.layout.item_select_project,null);
            holder = new Holder();
            holder.mTextView = convertView.findViewById(R.id.projectName);
            convertView.setTag(holder);
        }holder = (Holder) convertView.getTag();
        holder.mTextView.setText(UserUtil.projectList.get(position).getShort_name());
        return convertView;
    }

    class Holder{
        TextView mTextView;
    }
}
