package com.outsourcing.llxpb.adapter;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lzy.okgo.OkGo;
import com.outsourcing.llxpb.Bean.IReleaseBean;
import com.outsourcing.llxpb.Bean.IReleaseMyBean;
import com.outsourcing.llxpb.R;
import com.outsourcing.llxpb.ui.customView.CommomDialog;
import com.outsourcing.llxpb.util.HttpHost;
import com.outsourcing.llxpb.util.JsonCallBack;
import com.outsourcing.llxpb.util.T;
import com.outsourcing.llxpb.util.UserUtil;
import com.outsourcing.llxpb.view.activity.SelectUpDataTypeWorkActivity;

import java.util.List;

/**
 * Created by DELL on 2019/9/25.
 */

public class IReleaseAdapter extends RecyclerView.Adapter<IReleaseAdapter.IReleaseHolder> {
    private Context mContext;
    private List<IReleaseMyBean.DataBean> mDataBeans;

    public IReleaseAdapter(Context context, List<IReleaseMyBean.DataBean> dataBeans) {
        mContext = context;
        mDataBeans = dataBeans;
    }

    @NonNull
    @Override
    public IReleaseHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new IReleaseHolder(View.inflate(mContext, R.layout.item_i_release, null));
    }

    @Override
    public void onBindViewHolder(@NonNull IReleaseHolder iReleaseHolder, final int i) {
        TextView time = iReleaseHolder.mView.findViewById(R.id.time);
        RecyclerView item_rl = iReleaseHolder.mView.findViewById(R.id.item_rl);
        time.setText(mDataBeans.get(i).getRelease_time() + "");
       // item_rl.setLayoutManager(new LinearLayoutManager(mContext));
        LinearLayout state = iReleaseHolder.mView.findViewById(R.id.state);
        boolean viewState = mDataBeans.get(i).getIs_valid() == 0 ? false : true;
        if (viewState) {
            time.setTextColor(0xff333333);
            state.setVisibility(View.VISIBLE);
        } else {
            time.setTextColor(0xff999999);
            state.setVisibility(View.GONE);
        }
        LinearLayoutManager layoutManager = new LinearLayoutManager(mContext) {
            @Override
            public RecyclerView.LayoutParams generateDefaultLayoutParams() {
                return new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT);
            }
        };
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        item_rl.setLayoutManager(layoutManager);
        item_rl.setAdapter(new IReleaseItemAdapter(mContext, mDataBeans.get(i).getIdle_list(), viewState));
        Button updata = iReleaseHolder.mView.findViewById(R.id.updata);
        Button delete = iReleaseHolder.mView.findViewById(R.id.delete);
        updata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, SelectUpDataTypeWorkActivity.class);
                intent.putExtra("id",mDataBeans.get(i).getId());
                mContext.startActivity(intent);
            }
        });
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                show(i);
            }
        });
    }

    private void show(final int position) {
        new CommomDialog(mContext, R.style.dialog, "您确定要删除该条发布？", new CommomDialog.OnCloseListener() {

            @Override
            public void onClickDialog(Dialog dialog, boolean confirm) {
                if (confirm) {
                    delete(position);
                    dialog.dismiss();
                }
            }
        }).setTitle("提示").setNegativeButton("删除").show();
    }

    private void delete(final int position) {
        OkGo.get(HttpHost.httpHost + "/subcontractor/labour/delete?id="+mDataBeans.get(position).getId())
                .headers("Content-Type", "application/json")
                .headers("token", UserUtil.token)
                .headers("uuid", UserUtil.uuid)
                .headers("project_id", UserUtil.projectId)
                .execute(new JsonCallBack<IReleaseBean>() {
                    @Override
                    public void onSuccess(IReleaseBean iReleaseBean, okhttp3.Call call, okhttp3.Response response) {
                        if (iReleaseBean != null && iReleaseBean.getCode()==0) {
                            //  mWageDistributionTimeBean = wageDistributionTimeBean;
                            T.showLong(mContext, "删除成功");
                            mDataBeans.remove(position);
                            IReleaseAdapter.this.notifyDataSetChanged();
                        } else {
                            T.showLong(mContext, iReleaseBean.getMessage( ));
                        }
                    }
                });
    }

    @Override
    public int getItemCount() {
        return mDataBeans.size();
    }


    class IReleaseHolder extends RecyclerView.ViewHolder {
        private View mView;

        public IReleaseHolder(@NonNull View itemView) {
            super(itemView);
            mView = itemView;
        }
    }
}
