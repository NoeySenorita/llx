package com.outsourcing.llxpb.adapter;

import android.widget.TextView;

import com.outsourcing.llxpb.Bean.WageDistributionBean;
import com.outsourcing.llxpb.R;
import com.outsourcing.llxpb.adapter.common_recyler_adapter.CommonRecyclerAdapter;
import com.outsourcing.llxpb.adapter.common_recyler_adapter.RecyclerViewHolder;

import java.util.List;

public class WageDistributionAdapter extends CommonRecyclerAdapter<WageDistributionBean.DataBean.ContractStatBean> {
    List<WageDistributionBean.DataBean.ContractStatBean> list;
    public WageDistributionAdapter(int itemResourceId, List<WageDistributionBean.DataBean.ContractStatBean> list) {
        super(itemResourceId, list);
        this.list = list;
    }

    @Override
    protected void convert(RecyclerViewHolder holder, WageDistributionBean.DataBean.ContractStatBean contractStatBean, int position) {
        TextView name = holder.getView(R.id.name);
        TextView declare = holder.getView(R.id.declare);
        TextView inspectionWork = holder.getView(R.id.inspectionWork);
        name.setText(contractStatBean.getCode());
        declare.setText(contractStatBean.getApply_amount()+"");
        inspectionWork.setText(contractStatBean.getAccept_amount()+"");
        if(position >= list.size()-2 ){
            name.setBackgroundColor(0xfff5f5f5);
            declare.setBackgroundColor(0xfff5f5f5);
            inspectionWork.setBackgroundColor(0xfff5f5f5);
        }else {
            name.setBackgroundColor(0xffffffff);
            declare.setBackgroundColor(0xffffffff);
            inspectionWork.setBackgroundColor(0xffffffff);
        }
    }
}
