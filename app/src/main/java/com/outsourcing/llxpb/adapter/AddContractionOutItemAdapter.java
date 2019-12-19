package com.outsourcing.llxpb.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.outsourcing.llxpb.Bean.DeclareBean;
import com.outsourcing.llxpb.R;

import java.util.List;

/**
 * Created by DELL on 2019/9/11.
 */

public class AddContractionOutItemAdapter extends RecyclerView.Adapter<AddContractionOutItemAdapter.AddContractionOutItemHolder> {
    private Context mContext;
    List<DeclareBean.DataBean> mDataBeanList;
    public AddContractionOutItemAdapter(Context addcontractingOutItemActivity, List<DeclareBean.DataBean> declareBean) {
        mContext = addcontractingOutItemActivity;
        mDataBeanList = declareBean;
    }

    @NonNull
    @Override
    public AddContractionOutItemHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new AddContractionOutItemHolder(View.inflate(mContext, R.layout.item_add_contraction_outitem,null));
    }

    boolean satet;

    @Override
    public void onBindViewHolder(@NonNull AddContractionOutItemHolder addContractionOutItemHolder, int i) {
        TextView name =  addContractionOutItemHolder.mView.findViewById(R.id.name);
        final TextView message =  addContractionOutItemHolder.mView.findViewById(R.id.message);
        name.setText(mDataBeanList.get(i).getMatter_subject());
        message.setText(mDataBeanList.get(i).getMatter_content());
        satet = false;
        message.setVisibility(satet?View.VISIBLE:View.GONE);
        addContractionOutItemHolder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                satet = !satet;
                message.setVisibility(satet?View.VISIBLE:View.GONE);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mDataBeanList.size();
    }


    class AddContractionOutItemHolder extends RecyclerView.ViewHolder {
        private View mView;
        public AddContractionOutItemHolder(@NonNull View itemView) {
            super(itemView);
            mView = itemView;
        }
    }
}
