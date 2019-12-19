package com.outsourcing.llxpb.adapter;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.outsourcing.llxpb.Bean.LookingLaborBean;
import com.outsourcing.llxpb.R;

import java.util.List;

/**
 * Created by DELL on 2019/9/25.
 */

public class LookingLaborAdapter extends RecyclerView.Adapter<LookingLaborAdapter.LookingLaborHolder> {
    Context mContext;
    List<LookingLaborBean.DataBean> mDataBeans;

    public LookingLaborAdapter(Context context, List<LookingLaborBean.DataBean> datas) {
        mContext = context;
        mDataBeans = datas;
    }

    @NonNull
    @Override
    public LookingLaborHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new LookingLaborHolder(View.inflate(mContext, R.layout.item_looking_labor, null));
    }

    @Override
    public void onBindViewHolder(@NonNull LookingLaborHolder lookingLaborHolder, final int i) {
        TextView name = lookingLaborHolder.mView.findViewById(R.id.name);
        TextView time = lookingLaborHolder.mView.findViewById(R.id.time);
        TextView num = lookingLaborHolder.mView.findViewById(R.id.num);
        TextView p_name = lookingLaborHolder.mView.findViewById(R.id.p_name);
        TextView phone = lookingLaborHolder.mView.findViewById(R.id.phone);
        StringBuilder sTime = new StringBuilder((mDataBeans.get(i).getStart_date() + "").substring(4));
        sTime.insert(2, "/");
        StringBuilder eTime = new StringBuilder((mDataBeans.get(i).getEnd_date() + "").substring(4));
        eTime.insert(2, "/");
        String tm = sTime.toString() + "至" + eTime.toString();
        time.setText(tm);
        name.setText(mDataBeans.get(i).getCompany()+"");
        num.setText(mDataBeans.get(i).getIdle_num()+"");
        p_name.setText(mDataBeans.get(i).getUser_name());
        phone.setText(mDataBeans.get(i).getMobile() + "");
        phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_CALL);
                Uri data = Uri.parse("tel:" + mDataBeans.get(i).getMobile());
                intent.setData(data);
                if (ActivityCompat.checkSelfPermission(mContext, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    return;
                }
                mContext.startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return mDataBeans.size();
    }

    class LookingLaborHolder extends RecyclerView.ViewHolder {
        View mView;
        public LookingLaborHolder(@NonNull View itemView) {
            super(itemView);
            mView = itemView;
        }
    }
}
