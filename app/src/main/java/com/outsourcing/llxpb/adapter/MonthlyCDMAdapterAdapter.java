package com.outsourcing.llxpb.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.outsourcing.llxpb.Bean.MonthlyCompletionDeclarationMessageBean;
import com.outsourcing.llxpb.R;

import java.util.List;

/**
 * Created by DELL on 2019/9/15.
 */

class MonthlyCDMAdapterAdapter extends RecyclerView.Adapter<MonthlyCDMAdapterAdapter.MonthlyCDMAdapterHolder> {
    private Context mContext;
    private List<MonthlyCompletionDeclarationMessageBean.DataBean.WageApplyPayListBean.PayListBean> mListBeans;
    public MonthlyCDMAdapterAdapter(Context context, List<MonthlyCompletionDeclarationMessageBean.DataBean.WageApplyPayListBean.PayListBean> pay_list) {
        mContext = context;
        mListBeans = pay_list;
    }

    @NonNull
    @Override
    public MonthlyCDMAdapterHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
      //  return new MonthlyCDMAdapterHolder(View.inflate(mContext, R.layout.item_item_monthly_message,null));
        return new MonthlyCDMAdapterHolder(View.inflate(mContext, R.layout.item_item_monthly_message,null));
    }

    @Override
    public void onBindViewHolder(@NonNull MonthlyCDMAdapterHolder monthlyCDMAdapterHolder, int i) {
        TextView message1 = monthlyCDMAdapterHolder.mView.findViewById(R.id.message1);
        TextView message2 = monthlyCDMAdapterHolder.mView.findViewById(R.id.message2);
        message1.setText(mListBeans.get(i).getPay_day()+"");
        message2.setText(mListBeans.get(i).getPay_wage()+"");
    }

    @Override
    public int getItemCount() {
        return mListBeans.size();
    }

    class MonthlyCDMAdapterHolder extends RecyclerView.ViewHolder{
        View mView;
        public MonthlyCDMAdapterHolder(@NonNull View itemView) {
            super(itemView);
            mView = itemView;
        }
    }
}
