package com.outsourcing.llxpb.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.outsourcing.llxpb.Bean.MonthlyCompletionDeclarationMessageBean;
import com.outsourcing.llxpb.R;
import com.outsourcing.llxpb.view.activity.MonthlyCompletionDeclarationMessageActivity;

import java.util.List;

/**
 * Created by DELL on 2019/9/15.
 */

public class MonthlyCDMAdapter extends RecyclerView.Adapter<MonthlyCDMAdapter.MonthlyCDMHolder> {
    private Context mContext;
    private List<MonthlyCompletionDeclarationMessageBean.DataBean.WageApplyPayListBean> mListBeans;

    public MonthlyCDMAdapter(MonthlyCompletionDeclarationMessageActivity monthlyCompletionDeclarationMessageActivity, List<MonthlyCompletionDeclarationMessageBean.DataBean.WageApplyPayListBean> list) {
        mContext = monthlyCompletionDeclarationMessageActivity;
        mListBeans = list;
    }

    @NonNull
    @Override
    public MonthlyCDMHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new MonthlyCDMHolder(View.inflate(mContext, R.layout.item_monthly_message, null));
    }

    @Override
    public void onBindViewHolder(@NonNull MonthlyCDMHolder monthlyCDMHolder, int i) {
        TextView m_name = monthlyCDMHolder.mView.findViewById(R.id.m_name);
        TextView m_declare = monthlyCDMHolder.mView.findViewById(R.id.m_declare);
        RecyclerView rl_data = monthlyCDMHolder.mView.findViewById(R.id.rl_data);
        m_name.setText(mListBeans.get(i).getMonth() + "");
        m_declare.setText(mListBeans.get(i).getApply_wage() + "");
        LinearLayoutManager layoutManager = new LinearLayoutManager(mContext) {
            @Override
            public RecyclerView.LayoutParams generateDefaultLayoutParams() {
                return new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT);
            }
        };
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rl_data.setLayoutManager(layoutManager);

       // rl_data.setLayoutManager(new LinearLayoutManager(mContext));
        rl_data.setAdapter(new MonthlyCDMAdapterAdapter(mContext, mListBeans.get(i).getPay_list()));
    }

    @Override
    public int getItemCount() {
        return mListBeans.size();
    }

    class MonthlyCDMHolder extends RecyclerView.ViewHolder {
        private View mView;

        public MonthlyCDMHolder(@NonNull View itemView) {
            super(itemView);
            mView = itemView;
        }
    }
}
