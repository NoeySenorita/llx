package com.outsourcing.llxpb.adapter;

import com.outsourcing.llxpb.Bean.WageDIstributionMessageBean;
import com.outsourcing.llxpb.R;
import com.outsourcing.llxpb.adapter.common_recyler_adapter.CommonRecyclerAdapter;
import com.outsourcing.llxpb.adapter.common_recyler_adapter.RecyclerViewHolder;

import java.util.List;

public class WageDIstributionMessageAdapter extends CommonRecyclerAdapter<WageDIstributionMessageBean.DataBean.ItemStatBean> {
    List<WageDIstributionMessageBean.DataBean.ItemStatBean> list;
    public WageDIstributionMessageAdapter(int itemResourceId, List<WageDIstributionMessageBean.DataBean.ItemStatBean> list) {
        super(itemResourceId, list);
        this.list = list;
    }

    @Override
    protected void convert(RecyclerViewHolder holder, WageDIstributionMessageBean.DataBean.ItemStatBean itemStatBean, int position) {
        holder.setText(R.id.m_name,itemStatBean.getItem_name());
        holder.setText(R.id.m_declare,itemStatBean.getItem_price()+"");
        holder.setText(R.id.m_inspectionWork1,itemStatBean.getApply_item_num()+"");
        holder.setText(R.id.m_inspectionWork2,itemStatBean.getApply_item_amount()+"");
    }
}
