package com.outsourcing.llxpb.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.outsourcing.llxpb.Bean.EarlyWBean1;
import com.outsourcing.llxpb.R;

import java.util.List;

/**
 * Created by DELL on 2019/9/30.
 */

public class EarlyWarningAdapter1 extends RecyclerView.Adapter<EarlyWarningAdapter1.Holder> {
    private Context mContext;
    private List<EarlyWBean1.DataBean> mDataBeans;

    public EarlyWarningAdapter1(Context context, List<EarlyWBean1.DataBean> dataBeans) {
        mContext = context;
        mDataBeans = dataBeans;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new Holder(View.inflate(mContext, R.layout.item_ew1, null));
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int i) {
        TextView time = holder.mView.findViewById(R.id.time);
        TextView num = holder.mView.findViewById(R.id.num);
        final TextView names = holder.mView.findViewById(R.id.names);
        time.setText(mDataBeans.get(i).getVerify_date() + "");
        num.setText(mDataBeans.get(i).getNot_match_migrant_worker_num() + "");
        String mg = "";
        if (mDataBeans.get(i).getNot_match_migrant_worker_list().size() > 0) {
            if (mDataBeans.get(i).getNot_match_migrant_worker_list().size() > 1) {
                mg = mDataBeans.get(i).getNot_match_migrant_worker_list().get(0).getName() + "," + mDataBeans.get(i).getNot_match_migrant_worker_list().get(1).getName() + "等" + mDataBeans.get(i).getNot_match_migrant_worker_list().size() + "人";
            } else {
                mg = mDataBeans.get(i).getNot_match_migrant_worker_list().get(0).getName() + mDataBeans.get(i).getNot_match_migrant_worker_list().size() + "人";
            }
        }
        String mgs = "";
        for (int j = 0; j < mDataBeans.get(i).getNot_match_migrant_worker_list().size(); j++) {
            if (j == 0) {
                mgs = mDataBeans.get(i).getNot_match_migrant_worker_list().get(j).getName();
            } else {
                mgs = mgs + "," + mDataBeans.get(i).getNot_match_migrant_worker_list().get(j).getName() + mDataBeans.get(i).getNot_match_migrant_worker_list().size() + "人不符";
            }
        }
        names.setText(mg);
        if(TextUtils.isEmpty(mg)){
            return;
        }
        final String finalMgs = mgs;
        names.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopSelectProject(names, finalMgs);
            }
        });
    }


    PopupWindow mPopupWindow;

    private void showPopSelectProject(View show,String message) {
        View contentView = LayoutInflater.from(mContext).inflate(R.layout.result_page_show_names, null);
        TextView mes = contentView.findViewById(R.id.message);
        mes.setText(message);
        mPopupWindow = new PopupWindow(contentView,dip2px(128) ,dip2px(78), true);
        mPopupWindow.setTouchable(true);
        mPopupWindow.setOutsideTouchable(true);
        mPopupWindow.setTouchInterceptor(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return false;
            }
        });
        int width = mPopupWindow.getWidth();
        int[] xy = new int[2];
        show.getLocationInWindow(xy);
        //  mPopupWindow.showAtLocation(show, Gravity.CENTER_VERTICAL,
        //      xy[0] + (dip2px(width) - show.getWidth()) / 2, xy[1] - dip2px(50));
        mPopupWindow.showAsDropDown(show, -250, -180);

    }
    public  int dip2px( float dpValue) {
        final float scale = mContext.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }
    @Override
    public int getItemCount() {
        return mDataBeans.size();
    }

    class Holder extends RecyclerView.ViewHolder {
        private View mView;

        public Holder(@NonNull View itemView) {
            super(itemView);
            mView = itemView;
        }
    }
}
