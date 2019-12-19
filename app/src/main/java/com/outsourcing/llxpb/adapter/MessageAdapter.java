package com.outsourcing.llxpb.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.outsourcing.llxpb.Bean.MessageBean;
import com.outsourcing.llxpb.R;
import com.outsourcing.llxpb.view.activity.MessageInfoActivity;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by DELL on 2019/10/9.
 */

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.Holder> {
    private Context mContext;
    List<MessageBean.DataBean.NoticeListBean> mListBeans;
    public MessageAdapter(Context context, List<MessageBean.DataBean.NoticeListBean> notice_list) {
        mContext = context;
        mListBeans = notice_list;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new Holder(View.inflate(mContext, R.layout.item_message, null));
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, final int i) {
        TextView message = holder.mView.findViewById(R.id.message);
        TextView time = holder.mView.findViewById(R.id.time);
        message.setText(mListBeans.get(i).getContent());
        time.setText(handleDate(mListBeans.get(i).getRelease_time()));
        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext,MessageInfoActivity.class);
                intent.putExtra("id",mListBeans.get(i).getId());
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mListBeans.size();
    }

    class Holder extends RecyclerView.ViewHolder {
        private View mView;
        public Holder(@NonNull View itemView) {
            super(itemView);
            mView = itemView;
        }
    }

    private String handleDate(long time) {
        Date date = new Date(time);
        long now = new Date().getTime();

        long day = (now + (8 * 60 * 1000)) / (24 * 60 * 60 * 1000) - (time + (8 * 60 * 1000)) / (24 * 60 * 60 * 1000);
        // (now-time)/(……)的结果和上面的结果不一样噢

        if (day < 1) {  //今天
            SimpleDateFormat format = new SimpleDateFormat("HH:mm");
            return "今天  " + format.format(date);
        } else if (day == 1) {     //昨天
            SimpleDateFormat format = new SimpleDateFormat("HH:mm");
            return "昨天  " + format.format(date);
        } else {    //可依次类推
            SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
            return format.format(date);
        }
    }

}
