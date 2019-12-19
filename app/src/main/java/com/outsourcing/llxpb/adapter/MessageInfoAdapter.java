package com.outsourcing.llxpb.adapter;

import android.content.Context;
import android.widget.TextView;

import com.outsourcing.llxpb.Bean.MessageListBean;
import com.outsourcing.llxpb.R;
import com.outsourcing.llxpb.adapter.common_recyler_adapter.CommonRecyclerAdapter;
import com.outsourcing.llxpb.adapter.common_recyler_adapter.RecyclerViewHolder;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by DELL on 2019/7/4.
 */

public class MessageInfoAdapter extends CommonRecyclerAdapter<MessageListBean.DataBean.NoticeListBean> {
    private Context mContext;

    public MessageInfoAdapter(int itemResourceId, List<MessageListBean.DataBean.NoticeListBean> list) {
        super(itemResourceId, list);
    }

    @Override
    protected void convert(final RecyclerViewHolder holder, final MessageListBean.DataBean.NoticeListBean noticeListBean, final int position) {
        TextView title = holder.getView(R.id.title);
        title.setText(noticeListBean.getContent());
        if(noticeListBean.getRead_time() > 0){
            title.setTextColor(0xff999999);
        }else title.setTextColor(0xff333333);
        holder.setText(R.id.mesage,noticeListBean.getContent());
        holder.setText(R.id.time,handleDate(noticeListBean.getRelease_time()));
    }

    private String handleDate(long time) {
        Date date = new Date(time);
        long now = new Date().getTime();

        long day = (now + (8 * 60 * 1000)) / (24 * 60 * 60 * 1000) - (time + (8 * 60 * 1000)) / (24 * 60 * 60 * 1000);
        // (now-time)/(……)的结果和上面的结果不一样噢

        if (day < 1) {  //今天
            SimpleDateFormat format = new SimpleDateFormat("HH:mm");
            return "" + format.format(date);
        } else if (day == 1) {     //昨天
            SimpleDateFormat format = new SimpleDateFormat("HH:mm");
            return "昨天  " + format.format(date);
        } else {    //可依次类推
            SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
            return format.format(date);
        }
    }
}
