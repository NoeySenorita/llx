package com.outsourcing.llxpb.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.outsourcing.llxpb.AppInstance;
import com.outsourcing.llxpb.Bean.PersonnelListBean;
import com.outsourcing.llxpb.R;
import com.outsourcing.llxpb.adapter.common_recyler_adapter.CommonRecyclerAdapter;
import com.outsourcing.llxpb.adapter.common_recyler_adapter.RecyclerViewHolder;
import com.outsourcing.llxpb.util.UserUtil;
import com.outsourcing.llxpb.view.activity.CompleteInformation2Activity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by DELL on 2019/7/4.
 */

public class PersonnerListAdpater extends CommonRecyclerAdapter<PersonnelListBean.DataBean.MigrantWorkerListBean> {
    private Context mContext;
    private List<PersonnelListBean.DataBean.MigrantWorkerListBean> mListBeans;

    public PersonnerListAdpater(int itemResourceId, List<PersonnelListBean.DataBean.MigrantWorkerListBean> list) {
        super(itemResourceId, list);
    }


    @Override
    protected void convert(final RecyclerViewHolder holder, final PersonnelListBean.DataBean.MigrantWorkerListBean migrantWorkerListBean, int position) {
        String sex;
        if (migrantWorkerListBean.getSex() == 2) {
            sex = "女";
        } else if (migrantWorkerListBean.getSex() == 1) {
            sex = "男";
        } else {
            sex = "未知";
        }
        holder.setText(R.id.p_name, migrantWorkerListBean.getName());
        holder.setText(R.id.p_sex, sex);
        holder.setText(R.id.p_native, migrantWorkerListBean.getNative_place());
        holder.setText(R.id.p_position, migrantWorkerListBean.getWork_type_name());
        holder.setText(R.id.p_name, migrantWorkerListBean.getName());
        //info_completed = 0(信息未完善) && project_status = 1
        if(migrantWorkerListBean.getProject_status() == 0){
            holder.getView(R.id.p_play).setVisibility(View.GONE);
            ((TextView)holder.getView(R.id.p_name)).setTextColor(0xffcccccc);
            ((TextView)holder.getView(R.id.p_sex)).setTextColor(0xffcccccc);
            ((TextView)holder.getView(R.id.p_native)).setTextColor(0xffcccccc);
            ((TextView)holder.getView(R.id.p_position)).setTextColor(0xffcccccc);
            ((TextView)holder.getView(R.id.p_name)).setTextColor(0xffcccccc);
        }else {
            ((TextView)holder.getView(R.id.p_name)).setTextColor(0xff333333);
            ((TextView)holder.getView(R.id.p_sex)).setTextColor(0xff333333);
            ((TextView)holder.getView(R.id.p_native)).setTextColor(0xff333333);
            ((TextView)holder.getView(R.id.p_position)).setTextColor(0xff333333);
            ((TextView)holder.getView(R.id.p_name)).setTextColor(0xff333333);
        }
        if (0==migrantWorkerListBean.getInfo_completed()&& 1 == migrantWorkerListBean.getProject_status()) {

            TextView play = holder.getView(R.id.p_play);
            play.setVisibility(View.VISIBLE);
            play.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //完善信息
                    UserUtil.sendPerposesActivitys = new ArrayList<>();
                    Intent intent = new Intent(AppInstance.getContext(), CompleteInformation2Activity.class);
                    intent.putExtra("id", migrantWorkerListBean.getId());
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK );
                    AppInstance.getContext().startActivity(intent);
                }
            });
        } else {
            holder.getView(R.id.p_play).setVisibility(View.GONE);
        }


    }
}
