package com.outsourcing.llxpb.view.fragment;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.outsourcing.llxpb.Bean.InspectionWorkBean;
import com.outsourcing.llxpb.Bean.MessageBean;
import com.outsourcing.llxpb.Bean.PeopleBean;
import com.outsourcing.llxpb.Bean.SalaryBean;
import com.outsourcing.llxpb.Bean.SubcontractorBean;
import com.outsourcing.llxpb.MainActivity;
import com.outsourcing.llxpb.R;
import com.outsourcing.llxpb.adapter.MessageAdapter;
import com.outsourcing.llxpb.adapter.SelectProjectAdapter;
import com.outsourcing.llxpb.manager.MessageEvent;
import com.outsourcing.llxpb.ui.base.BaseFragment;
import com.outsourcing.llxpb.util.HttpHost;
import com.outsourcing.llxpb.util.JsonCallBack;
import com.outsourcing.llxpb.util.T;
import com.outsourcing.llxpb.util.UserUtil;
import com.outsourcing.llxpb.view.activity.MeActivity;
import com.outsourcing.llxpb.view.activity.MessageListActivity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import q.rorbin.badgeview.Badge;
import q.rorbin.badgeview.QBadgeView;


public class HomeFragment extends BaseFragment {




    private TextView mNowNum;
    private TextView mOldNum;
    private TextView mLocationNum;
    private TextView mOtherNum;
    private TextView mYearNum;
    private TextView mOperNum;
    private TextView mAllSendNum;
    private TextView mAllGetNum;
    private TextView mTimeNum;
    private TextView mResidueold1;
    private TextView mAllSendPopNum;
    private TextView mAllGetPopNum;
    private TextView mTimePopNum;
    private TextView mResidueold2;
    private RecyclerView mRl_message;

    private MessageAdapter mAdapter;

    @Override
    public View getContentView() {
        View view = LayoutInflater.from(mActivity).inflate(R.layout.fragment_home, null, false);
        return view;
    }

    @Override
    protected void initContentView(View contentView) {
        //getBarTool().setTitle(R.string.tab_bar_home);
        getBarTool().hideTitleBar();




        mNowNum = contentView.findViewById(R.id.nowNum);
        mOldNum = contentView.findViewById(R.id.oldNum);
        mLocationNum = contentView.findViewById(R.id.locationNum);
        mOtherNum = contentView.findViewById(R.id.otherNum);
        mYearNum = contentView.findViewById(R.id.yearNum);
        mOperNum = contentView.findViewById(R.id.operNum);
        mAllSendNum = contentView.findViewById(R.id.allSendNum);
        mAllGetNum = contentView.findViewById(R.id.allGetNum);
        mTimeNum = contentView.findViewById(R.id.timeNum);
        mResidueold1 = contentView.findViewById(R.id.residueold1);
        mAllSendPopNum = contentView.findViewById(R.id.allSendPopNum);
        mAllGetPopNum = contentView.findViewById(R.id.allGetPopNum);
        mTimePopNum = contentView.findViewById(R.id.timePopNum);
        mResidueold2 = contentView.findViewById(R.id.residueold2);
        mRl_message = contentView.findViewById(R.id.rl_message);
        mRl_message.setLayoutManager(new LinearLayoutManager(this.getContext()));
        EventBus.getDefault().register(this);
       // getPeopleData();

    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void Event(MessageEvent messageEvent) {
        getPeopleData();
    }
    public void getPeopleData() {
        if(getBarTool()== null){
            return;
        }
          //  getBarTool().setTitle(UserUtil.default_project_short_name);
        OkGo.get(HttpHost.httpHost + "/subcontractor/home/getMigrantWorkerStat")
                .headers("Content-Type", "application/json")
                .headers("token", UserUtil.token)
                .headers("uuid", UserUtil.uuid)
                .headers("project_id", UserUtil.projectId)
                .execute(new JsonCallBack<PeopleBean>() {
                    @Override
                    public void onSuccess(PeopleBean earlyWBean1, okhttp3.Call call, okhttp3.Response response) {
                        if(HomeFragment.this.getContext()!=null){
                            if (earlyWBean1 != null && earlyWBean1.getCode()==0 ) {
                                if( earlyWBean1.getData() !=null){
                                    if(mNowNum!= null&&mOldNum!= null&&mLocationNum!=null&&mOtherNum!=null&&mYearNum!= null&&mOperNum!=null){
                                        mNowNum.setText(earlyWBean1.getData().getIn_project_person_count()+"人");
                                        mOldNum.setText(earlyWBean1.getData().getOverage_person_count()+"人");
                                        mLocationNum.setText(earlyWBean1.getData().getLocal_person_count()+"人");
                                        mOtherNum.setText(earlyWBean1.getData().getUnlocal_person_count()+"人");
                                        mYearNum.setText(earlyWBean1.getData().getYear_person_count()+"人·月");
                                        mOperNum.setText(earlyWBean1.getData().getAccumulative_person_count()+"人·月");
                                    }
                                }else {
                                    if(mNowNum!= null&&mOldNum!= null&&mLocationNum!=null&&mOtherNum!=null&&mYearNum!= null&&mOperNum!=null){
                                        mNowNum.setText(0+"人");
                                        mOldNum.setText(0+"人");
                                        mLocationNum.setText(0+"人");
                                        mOtherNum.setText(0+"人");
                                        mYearNum.setText(0+"人·月");
                                        mOperNum.setText(0+"人·月");
                                    }
                                }
                            } else {
                                T.showLong(HomeFragment.this.getContext(), earlyWBean1.getMessage());
                            }
                        }

                    }
                });
        OkGo.get(HttpHost.httpHost + "/subcontractor/home/getWageStat")
                .headers("Content-Type", "application/json")
                .headers("token", UserUtil.token)
                .headers("uuid", UserUtil.uuid)
                .headers("project_id", UserUtil.projectId)
                .execute(new JsonCallBack<SalaryBean>() {
                    @Override
                    public void onSuccess(SalaryBean earlyWBean1, okhttp3.Call call, okhttp3.Response response) {
                        if(HomeFragment.this.getContext() != null){
                            if (earlyWBean1 != null && earlyWBean1.getCode()==0) {
                                if(earlyWBean1.getData() !=null){
                                    if(mAllSendNum != null&& mAllGetNum!=null&& mTimeNum!=null&&mResidueold1!= null){
                                        mAllSendNum.setText(earlyWBean1.getData().getWage_apply_amount()/10000+"万元");
                                        mAllGetNum.setText(earlyWBean1.getData().getWage_pay_amount()/10000+"万元");
                                        mTimeNum.setText(earlyWBean1.getData().getNext_apply_date()+"");
                                        mResidueold1.setText("还剩"+earlyWBean1.getData().getNext_apply_counted_down()+"天");
                                    }
                                }

                            } else {
                                if(mAllSendNum != null&& mAllGetNum!=null&& mTimeNum!=null&&mResidueold1!= null){
                                    mAllSendNum.setText(0+"万元");
                                    mAllGetNum.setText(0+"万元");
                                    mTimeNum.setText("");
                                    mResidueold1.setText("还剩"+"0天");
                                }
                                T.showLong(HomeFragment.this.getContext(), earlyWBean1.getMessage());
                            }
                        }

                    }
                });
        OkGo.get(HttpHost.httpHost + "/subcontractor/home/getOutputStat")
                .headers("Content-Type", "application/json")
                .headers("token", UserUtil.token)
                .headers("uuid", UserUtil.uuid)
                .headers("project_id", UserUtil.projectId)
                .execute(new JsonCallBack<InspectionWorkBean>() {
                    @Override
                    public void onSuccess(InspectionWorkBean earlyWBean1, okhttp3.Call call, okhttp3.Response response) {
                        if(HomeFragment.this.getContext() != null){
                            if (earlyWBean1 != null && earlyWBean1.getCode()==0) {
                                if(earlyWBean1.getData()!= null){
                                    if(mAllGetPopNum!=null&&mAllSendPopNum!= null&&mTimePopNum!=null&&mResidueold2!=null){
                                        mAllSendPopNum.setText(earlyWBean1.getData().getOutput_apply_amount()/10000+"万元");
                                        mAllGetPopNum.setText(earlyWBean1.getData().getOutput_accept_amount()/10000+"万元");
                                        mTimePopNum.setText(earlyWBean1.getData().getNext_apply_date()+"");
                                        mResidueold2.setText("还剩"+earlyWBean1.getData().getNext_apply_counted_down()+"天");
                                    }
                                }
                            } else {
                                if(mAllGetPopNum!=null&&mAllSendPopNum!= null&&mTimePopNum!=null&&mResidueold2!=null){
                                    mAllSendPopNum.setText(0+"万元");
                                    mAllGetPopNum.setText(0+"万元");
                                    mTimePopNum.setText("");
                                    mResidueold2.setText("还剩0天");
                                }
                                T.showLong(HomeFragment.this.getContext(), earlyWBean1.getMessage());
                            }
                        }

                    }
                });
      /*  OkGo.get(HttpHost.httpHost + "/subcontractor/home/getNoticeList")
                .headers("Content-Type", "application/json")
                .headers("token", UserUtil.token)
                .headers("uuid", UserUtil.uuid)
                .headers("project_id", UserUtil.projectId)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String earlyWBean1, okhttp3.Call call, okhttp3.Response response) {
                        Log.e( "onSuccess: ",earlyWBean1 );
                    }
                });*/

        OkGo.get(HttpHost.httpHost + "/subcontractor/home/getNoticeList")
                .headers("Content-Type", "application/json")
                .headers("token", UserUtil.token)
                .headers("uuid", UserUtil.uuid)
                .headers("project_id", UserUtil.projectId)
                .execute(new JsonCallBack<MessageBean>() {
                    @Override
                    public void onSuccess(MessageBean earlyWBean1, okhttp3.Call call, okhttp3.Response response) {
                        if(HomeFragment.this.getContext() != null){
                            if(mRl_message != null ){
                                if (earlyWBean1 != null && earlyWBean1.getCode()==0 &&earlyWBean1.getData() !=null) {
                                    if(mRl_message != null){
                                        if(earlyWBean1.getData().getNotice_list().size() == 0){
                                            mRl_message.setVisibility(View.GONE);
                                            return;
                                        }
                                        mRl_message.setVisibility(View.VISIBLE);
                                        mAdapter = new MessageAdapter(HomeFragment.this.getContext(), earlyWBean1.getData().getNotice_list());
                                        mRl_message.setAdapter(mAdapter);
                                    }

                                } else {
                                    T.showLong(HomeFragment.this.getContext(), earlyWBean1.getMessage());
                                }
                            }
                        }

                    }
                });
    }

    @Override
    public void onResume() {
        super.onResume();
        getPeopleData();
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        if(EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }
    }
}
