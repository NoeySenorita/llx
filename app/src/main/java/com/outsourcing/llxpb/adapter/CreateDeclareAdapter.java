package com.outsourcing.llxpb.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.outsourcing.llxpb.Bean.CreateDeclareBean;
import com.outsourcing.llxpb.R;

import java.util.List;

/**
 * Created by DELL on 2019/8/23.
 */

public class CreateDeclareAdapter extends RecyclerView.Adapter<CreateDeclareAdapter.CreateHolder> {
    List<CreateDeclareBean.DataBean.ContractListBean> list;
    LinearLayoutManager mLinearLayoutManager ;
    Context mContext;
    public CreateDeclareAdapter(Context mContext, List<CreateDeclareBean.DataBean.ContractListBean> list) {
        this.list = list;
        this.mContext = mContext;
       mLinearLayoutManager =   new LinearLayoutManager(mContext);
    }

    boolean setState = false;


    @NonNull
    @Override
    public CreateHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new CreateHolder(View.inflate(mContext,R.layout.item_create_declare,null));
    }

    @Override
    public void onBindViewHolder(@NonNull final CreateHolder createHolder, final int position) {
        ((TextView)createHolder.mView.findViewById(R.id.code)).setText(list.get(position).getCode());
        final ImageView state = createHolder.mView.findViewById(R.id.state);
        final RelativeLayout rl_state = createHolder.mView.findViewById(R.id.rl_state);
        final LinearLayout ll_setStateShow = createHolder.mView.findViewById(R.id.ll_setStateShow);
        final RecyclerView rvl_list = createHolder.mView.findViewById(R.id.rvl_item_list);
        //  rvl_list.setAdapter(new  CreateItemDeclareAdapter(R.layout.item_item_create_declare, list.get(0).getContract_list().get(position).getItem_list()));
        setState = false;
        ll_setStateShow.setVisibility(setState?View.VISIBLE:View.GONE);
        state.setBackgroundResource(setState?R.drawable.close:R.drawable.open);
        rl_state.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setState = !setState;
                ll_setStateShow.setVisibility(setState?View.VISIBLE:View.GONE);
                state.setBackgroundResource(setState?R.drawable.close:R.drawable.open);
                //   notifyItemChanged(position);
                rvl_list.setLayoutManager(new LinearLayoutManager(mContext));
                rvl_list.setAdapter(new UpDataDeclareItemAdapter(list.get(position).getItem_list(),createHolder,position));

            }
        });
        ((TextView)createHolder.mView.findViewById(R.id.prior_period)).setText("上期申报："+list .get(position).getPrior_period()+"日");
        ((TextView)createHolder.mView.findViewById(R.id.apply_times)).setText("总第"+list .get(position).getApply_times()+"期");
        ((TextView)createHolder.mView.findViewById(R.id.apply_month)).setText("截止上期申报："+list .get(position).getApplied_amount()/10000+"万元");
        ((TextView)createHolder.mView.findViewById(R.id.code)).setText("合同编号 "+list .get(position).getCode()+"");
        setMoney(createHolder,position);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public class UpDataDeclareItemAdapter extends RecyclerView.Adapter<UpDataDeclareItemAdapter.ItemHolder> {
        List<CreateDeclareBean.DataBean.ContractListBean.ItemListBean> list;
        CreateHolder  mHolder;
        int fPosition;
        public UpDataDeclareItemAdapter( List<CreateDeclareBean.DataBean.ContractListBean.ItemListBean> list,CreateHolder holder,int fPosition) {
            this.list = list;
            mHolder = holder;
            this.fPosition = fPosition;
        }


        @NonNull
        @Override
        public ItemHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            return new ItemHolder(View.inflate(mContext,R.layout.item_updata_declare_item,null));
        }

        @Override
        public void onBindViewHolder(@NonNull ItemHolder itemHolder, final int i) {
            TextView name = itemHolder.view.findViewById(R.id.item_name);
            EditText num = itemHolder.view.findViewById(R.id.item_num);
            EditText pos = itemHolder.view.findViewById(R.id.suggest_construction_position);
            name.setText(list.get(i).getItem_name());
            pos.setText(list.get(i).getSuggest_construction_position());
            num.setText(list.get(i).getNum()== 0? "":list.get(i).getNum()+"");
            num.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    if(TextUtils.isEmpty(s)){
                        s = "0";
                    }
                    list.get(i).setNum(Integer.parseInt(s.toString()));
                   int i1 = CreateDeclareAdapter.this.list.size();
                    setMoney(mHolder,fPosition);
                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });

            pos.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    list.get(i).setSuggest_construction_position(s.toString());
                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });
        }

        @Override
        public int getItemCount() {
            return list.size();
        }


        class ItemHolder extends RecyclerView.ViewHolder {
            private View view;

            public ItemHolder(@NonNull View itemView) {
                super(itemView);
                view = itemView;
            }
        }
    }


    private void setMoney(CreateHolder holder,int position) {
        double num = 0;
        CreateDeclareBean.DataBean.ContractListBean itemListBean = list .get(position);
        for (int i = 0; i < list.get(position).getItem_list().size();i++){
            num = itemListBean.getItem_list().get(i).getItem_price()*itemListBean.getItem_list().get(i).getNum() + num;
        }
        itemListBean.setAll(num);
        ((TextView)holder.mView.findViewById(R.id.applied_amount)).setText("本期申报 "+num+"");
    }

    class CreateHolder extends RecyclerView.ViewHolder {
        View mView;
        public CreateHolder(@NonNull View itemView) {
            super(itemView);
            mView = itemView;
        }
    }
}
