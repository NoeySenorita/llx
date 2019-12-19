package com.outsourcing.llxpb.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.outsourcing.llxpb.Bean.CreateMonthlyCDABean;
import com.outsourcing.llxpb.R;
import com.outsourcing.llxpb.view.activity.CreateMonthlyCDAActivity;

import java.util.List;

/**
 * Created by DELL on 2019/9/16.
 */

public class CreateMonthlyCDAAdapter extends RecyclerView.Adapter<CreateMonthlyCDAAdapter.CreateMonthlyCDAHolder> {
    private Context mContext;
    private  List<CreateMonthlyCDABean.DataBean.MigrantWorkerListBean> mListBeans;
    private TextView mTextView;
    public CreateMonthlyCDAAdapter(CreateMonthlyCDAActivity createMonthlyCDAActivity, List<CreateMonthlyCDABean.DataBean.MigrantWorkerListBean> listBeanList,TextView textView) {
        mContext = createMonthlyCDAActivity;
        mListBeans = listBeanList;
        mTextView = textView;
    }

    @NonNull
    @Override
    public CreateMonthlyCDAHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new CreateMonthlyCDAHolder(View.inflate(mContext, R.layout.item_create_monthly,null));
    }

    @Override
    public void onBindViewHolder(@NonNull CreateMonthlyCDAHolder createMonthlyCDAHolder, final int i) {
        TextView name = createMonthlyCDAHolder.mView.findViewById(R.id.name);
        TextView work = createMonthlyCDAHolder.mView.findViewById(R.id.work);
        TextView time = createMonthlyCDAHolder.mView.findViewById(R.id.time);
        EditText item_num = createMonthlyCDAHolder.mView.findViewById(R.id.item_num);
        name.setText(mListBeans.get(i).getName());
        work.setText(mListBeans.get(i).getWork_type_name());
        time.setText(mListBeans.get(i).getWork_day()+"");
        item_num.setText(mListBeans.get(i).getSuggest_apply_wage()+"");
        item_num.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                for (int j = 0;j < mListBeans.size();j++){
                    if(TextUtils.isEmpty(s)){
                        mListBeans.get(j).setState(false);
                        mListBeans.get(i).setSuggest_apply_wage(0);
                    }else {
                        mListBeans.get(i).setSuggest_apply_wage(Long.parseLong(s.toString()) );
                        mListBeans.get(j).setState(true);
                    }
                }
                boolean setState = true;
                for (int j = 0;j < mListBeans.size();j++){
                    if( !mListBeans.get(j).isState()){
                        setState = false;
                    }
                }
                mTextView.setBackgroundResource(setState?R.drawable.login_button:R.drawable.login_button_offclick);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return mListBeans.size();
    }

    class CreateMonthlyCDAHolder extends RecyclerView.ViewHolder{
        private View mView;
        public CreateMonthlyCDAHolder(@NonNull View itemView) {
            super(itemView);
            mView = itemView;
        }
    }
}
