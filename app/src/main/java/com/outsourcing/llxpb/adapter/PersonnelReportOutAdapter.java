package com.outsourcing.llxpb.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.outsourcing.llxpb.Bean.PersonnelListBean;
import com.outsourcing.llxpb.R;
import com.outsourcing.llxpb.adapter.common_recyler_adapter.CommonRecyclerAdapter;
import com.outsourcing.llxpb.adapter.common_recyler_adapter.RecyclerViewHolder;

import java.util.List;

/**
 * Created by DELL on 2019/7/4.
 */

public class PersonnelReportOutAdapter extends CommonRecyclerAdapter<PersonnelListBean.DataBean.MigrantWorkerListBean> {
    private Context mContext;

    public PersonnelReportOutAdapter(int itemResourceId, List<PersonnelListBean.DataBean.MigrantWorkerListBean> list) {
        super(itemResourceId, list);
    }

/*
    PersonnerListHolder personnerListHolder = null;
    private SparseArray<View> views = new SparseArray<>();*/

  /*  @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = View.inflate(mContext, , null);
            personnerListHolder = new PersonnerListHolder();
            personnerListHolder.p_name = convertView.findViewById(R.id.p_name);
            personnerListHolder.p_sex = convertView.findViewById(R.id.p_sex);
            personnerListHolder.p_age = convertView.findViewById(R.id.p_age);
            personnerListHolder.p_native = convertView.findViewById(R.id.p_native);
            personnerListHolder.p_position = convertView.findViewById(R.id.p_position);
            personnerListHolder.p_select = convertView.findViewById(R.id.p_select);
            personnerListHolder.iv_select = convertView.findViewById(R.id.iv_select);
            personnerListHolder.mView = convertView;
            views.put(position, convertView);
            convertView.setTag(personnerListHolder);
        } else {
            personnerListHolder = (PersonnerListHolder) convertView.getTag();
            convertView =views.get(position);
        }
        personnerListHolder.p_name.setText(mListBeans.get(position).getName());
        personnerListHolder.p_sex.setText(mListBeans.get(position).getSex() == 1 ? "男" : "女");
        personnerListHolder.p_native.setText(mListBeans.get(position).getNative_place() + "");
        personnerListHolder.p_position.setText(mListBeans.get(position).getWork_type_name());
        personnerListHolder.p_age.setText(mListBeans.get(position).getAge() + "");
        personnerListHolder.iv_select.setBackgroundResource(R.drawable.select_false);
        personnerListHolder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        return convertView;
    }*/

    @Override
    protected void convert(final RecyclerViewHolder holder, final PersonnelListBean.DataBean.MigrantWorkerListBean migrantWorkerListBean, final int position) {
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
        holder.setText(R.id.p_age, migrantWorkerListBean.getAge()+"");
        holder.setText(R.id.p_age, migrantWorkerListBean.getAge()+"");
        holder.getView(R.id.iv_select).setBackgroundResource(migrantWorkerListBean.isStateSelect() ? R.drawable.select_true : R.drawable.select_false);
        holder.getItemView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                migrantWorkerListBean.setStateSelect(!migrantWorkerListBean.isStateSelect());
                holder.getView(R.id.iv_select).setBackgroundResource(migrantWorkerListBean.isStateSelect() ? R.drawable.select_true : R.drawable.select_false);
            }
        });
    }

    class PersonnerListHolder {
        private TextView p_name, p_sex, p_age, p_native, p_position;
        private RelativeLayout p_select;
        private ImageView iv_select;
        private View mView;
    }
}
