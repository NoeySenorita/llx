package com.outsourcing.llxpb.adapter;

import com.outsourcing.llxpb.Bean.OutsideTheContractBean;
import com.outsourcing.llxpb.R;
import com.outsourcing.llxpb.adapter.common_recyler_adapter.CommonRecyclerAdapter;
import com.outsourcing.llxpb.adapter.common_recyler_adapter.RecyclerViewHolder;

import java.util.List;

/**
 * Created by DELL on 2019/8/22.
 */

public class OutsideTheContractAdapter extends CommonRecyclerAdapter<OutsideTheContractBean.DataBean> {
    public OutsideTheContractAdapter(int itemResourceId, List<OutsideTheContractBean.DataBean> list) {
        super(itemResourceId,list);
    }

    @Override
    protected void convert(RecyclerViewHolder holder, OutsideTheContractBean.DataBean dataBean, int position) {
        holder.setText(R.id.matter_subject,dataBean.getMatter_subject());
        holder.setText(R.id.matter_content,dataBean.getMatter_content());
        holder.setText(R.id.apply_month,dataBean.getApply_month()+"-"+dataBean.getMatter_num());
        holder.setText(R.id.matter_num,(position+1)+"");
    }
}
