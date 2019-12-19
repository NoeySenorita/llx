package com.outsourcing.llxpb.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.outsourcing.llxpb.Bean.IReleaseMyBean;
import com.outsourcing.llxpb.R;

import java.util.List;

/**
 * Created by DELL on 2019/9/25.
 */

class IReleaseItemAdapter extends RecyclerView.Adapter<IReleaseItemAdapter.IReleaseItemHolder> {
    Context mContext;
    List<IReleaseMyBean.DataBean.IdleListBean> mListBeans;
    boolean state;
    public IReleaseItemAdapter(Context context, List<IReleaseMyBean.DataBean.IdleListBean> idle_list,boolean state) {
        mContext = context;
        mListBeans = idle_list;
        this.state = state;
    }

    @NonNull
    @Override
    public IReleaseItemHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new IReleaseItemHolder(View.inflate(mContext, R.layout.item_item_i_release,null));
    }

    @Override
    public void onBindViewHolder(@NonNull IReleaseItemHolder iReleaseItemHolder, int i) {
        TextView workType = iReleaseItemHolder.mView.findViewById(R.id.workType);
        TextView num = iReleaseItemHolder.mView.findViewById(R.id.num);
        TextView time = iReleaseItemHolder.mView.findViewById(R.id.time);
        ImageView workTypeBg = iReleaseItemHolder.mView.findViewById(R.id.workTypeBg);
        num.setText(mListBeans.get(i).getIdle_num()+"人");
        workType.setText(mListBeans.get(i).getWork_type_name());
        StringBuilder  sTime = new StringBuilder ((mListBeans.get(i).getStart_date()+"").substring(4));
        sTime.insert(2,"/");
        StringBuilder  eTime = new StringBuilder ((mListBeans.get(i).getEnd_date()+"").substring(4));
        eTime.insert(2,"/");
        String tm = sTime.toString()+"至"+eTime.toString();
        time.setText(tm);
        if(state){
            switch (mListBeans.get(i).getWork_type_name()){
                case "普工":
                    workTypeBg.setBackgroundColor(0xff0BA194);
                    break;
                case "钢筋工":
                    workTypeBg.setBackgroundColor(0xff108EE9);
                    break;
                case "混凝土工":
                    workTypeBg.setBackgroundColor(0xffE8A010);
                    break;
                case "模板工":
                    workTypeBg.setBackgroundColor(0xffE8541E);
                    break;
                case "架子工":
                    workTypeBg.setBackgroundColor(0xff10E8DF);
                    break;
                case "电焊工":
                    workTypeBg.setBackgroundColor(0xff1810E8);
                    break;
                case "砌筑工":
                    workTypeBg.setBackgroundColor(0xffE310E8);
                    break;
            }
            time.setTextColor(0xff333333);
            workType.setTextColor(0xff333333);
            num.setTextColor(0xff333333);
        }else {
            workTypeBg.setBackgroundColor(0xff999999);
            time.setTextColor(0xff999999);
            workType.setTextColor(0xff999999);
            num.setTextColor(0xff999999);
        }

    }

    @Override
    public int getItemCount() {
        return mListBeans.size();
    }

    class IReleaseItemHolder extends RecyclerView.ViewHolder {
        View mView;
        public IReleaseItemHolder(@NonNull View itemView) {
            super(itemView);
            mView = itemView;
        }
    }
}
