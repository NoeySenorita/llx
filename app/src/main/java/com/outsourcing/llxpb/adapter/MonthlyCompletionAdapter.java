package com.outsourcing.llxpb.adapter;

import android.widget.TextView;

import com.outsourcing.llxpb.Bean.MonthlyCompletionBean;
import com.outsourcing.llxpb.R;
import com.outsourcing.llxpb.adapter.common_recyler_adapter.CommonRecyclerAdapter;
import com.outsourcing.llxpb.adapter.common_recyler_adapter.RecyclerViewHolder;

import java.util.List;

public class MonthlyCompletionAdapter extends CommonRecyclerAdapter<MonthlyCompletionBean.DataBean.MigrantWorkerStatBean> {
    public MonthlyCompletionAdapter(int itemResourceId, List<MonthlyCompletionBean.DataBean.MigrantWorkerStatBean> list) {
        super(itemResourceId, list);
    }

    @Override
    protected void convert(RecyclerViewHolder holder, MonthlyCompletionBean.DataBean.MigrantWorkerStatBean migrantWorkerStatBean, int position) {
        TextView name = holder.getView(R.id.m_name);
        TextView m_declare = holder.getView(R.id.m_declare);
        TextView m_inspectionWork1 = holder.getView(R.id.m_inspectionWork1);
        TextView m_inspectionWork2 = holder.getView(R.id.m_inspectionWork2);
        name.setText(migrantWorkerStatBean.getName()+"");
        m_declare.setText(migrantWorkerStatBean.getApply_amount()+"");
        m_inspectionWork1.setText(migrantWorkerStatBean.getPay_amount()+"");
        m_inspectionWork2.setText(migrantWorkerStatBean.getUnpaid_amount()+"");

        if(position ==0){
            name.setTextColor(0xff333333);
            name.setBackgroundColor(0xfff5f5f5);
            m_declare.setBackgroundColor(0xfff5f5f5);
            m_inspectionWork1.setBackgroundColor(0xfff5f5f5);
            m_inspectionWork2.setBackgroundColor(0xfff5f5f5);
        }else {
            name.setTextColor(0xffe88110);
            name.setBackgroundColor(0xffffffff);
            m_declare.setBackgroundColor(0xffffffff);
            m_inspectionWork1.setBackgroundColor(0xffffffff);
            m_inspectionWork2.setBackgroundColor(0xffffffff);
        }
    }
}
