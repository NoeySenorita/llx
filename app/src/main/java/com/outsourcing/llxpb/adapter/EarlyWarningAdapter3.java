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

import com.outsourcing.llxpb.Bean.EarlyWBean3;
import com.outsourcing.llxpb.R;

import java.util.List;

/**
 * Created by DELL on 2019/9/30.
 */

public class EarlyWarningAdapter3 extends RecyclerView.Adapter<EarlyWarningAdapter3.Holder> {
    private Context mContext;
    private List<EarlyWBean3.DataBean> mDataBeans;

    public EarlyWarningAdapter3(Context context, List<EarlyWBean3.DataBean> dataBeans) {
        mContext = context;
        mDataBeans = dataBeans;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new Holder(View.inflate(mContext, R.layout.item_ew2, null));
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, final int i) {
        TextView time = holder.mView.findViewById(R.id.time);
        TextView num = holder.mView.findViewById(R.id.num);
        TextView weigth = holder.mView.findViewById(R.id.weigth);
        final TextView names = holder.mView.findViewById(R.id.names);
        time.setText(mDataBeans.get(i).getMonth() + "");
        num.setText(mDataBeans.get(i).getLocal_person_num() + "");
        if((mDataBeans.get(i).getLocal_person_percent()+"").length() > 3){
            weigth.setText((mDataBeans.get(i).getLocal_person_percent()+"").substring(0,4) + "%");
        }else {
            weigth.setText(mDataBeans.get(i).getLocal_person_percent() + "%");
        }
        String mg = "";
        if (mDataBeans.get(i).getLocal_person_list().size() > 0) {
            if (mDataBeans.get(i).getLocal_person_list().size() > 1) {
                mg = mDataBeans.get(i).getLocal_person_list().get(0).getName() + "," + mDataBeans.get(i).getLocal_person_list().get(1).getName() + "等" + mDataBeans.get(i).getLocal_person_list().size() + "人";
            } else {
                mg = mDataBeans.get(i).getLocal_person_list().get(0).getName() + mDataBeans.get(i).getLocal_person_list().size() + "人";
            }
        }
        String mgs = "";
        for (int j = 0; j < mDataBeans.get(i).getLocal_person_list().size(); j++) {
            if (j == 0) {
                mgs = mDataBeans.get(i).getLocal_person_list().get(j).getName();
            } else {
                mgs = mgs + "," + mDataBeans.get(i).getLocal_person_list().get(j).getName() + mDataBeans.get(i).getLocal_person_list().size() + "人超龄";
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
                String mgs = "";
                for (int j = 0; j < mDataBeans.get(i).getLocal_person_list().size(); j++) {
                    if (j == 0) {
                        mgs = mDataBeans.get(i).getLocal_person_list().get(j).getName();
                    } else if(j == mDataBeans.get(i).getLocal_person_list().size() - 1){
                        mgs = mgs + "," + mDataBeans.get(i).getLocal_person_list().get(j).getName() + "人超龄";
                    }else {
                        mgs = mgs + "," + mDataBeans.get(i).getLocal_person_list().get(j).getName();
                    }
                }
                showPopSelectProject(names, mgs);
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
