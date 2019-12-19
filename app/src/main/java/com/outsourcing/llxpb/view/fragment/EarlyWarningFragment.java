package com.outsourcing.llxpb.view.fragment;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.PopupWindow;

import com.outsourcing.llxpb.MainActivity;
import com.outsourcing.llxpb.R;
import com.outsourcing.llxpb.adapter.SelectProjectAdapter;
import com.outsourcing.llxpb.adapter.ViewPagerAdapter;
import com.outsourcing.llxpb.ui.base.BaseFragment;
import com.outsourcing.llxpb.util.UserUtil;

import java.util.ArrayList;

public class EarlyWarningFragment extends BaseFragment {
    private TabLayout mTbLayout;
    private ViewPager mVp;
    private ArrayList<Fragment> fragmentsList;

    private View show;
    private EarlyWarningFragment1 ew1;
    private EarlyWarningFragment2 ew2;
    private EarlyWarningFragment3 ew3;
    private EarlyWarningFragment4 ew4;

    @Override
    public View getContentView() {
        View view = LayoutInflater.from(mActivity).inflate(R.layout.fragment_early_warning, null, false);
        return view;
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void initContentView(View contentView) {
        getBarTool().setTitle(UserUtil.default_project_short_name);
        show = contentView.findViewById(R.id.show);
        //getBarTool().hideTitleBar();
        getBarTool().hideIvTitleBar();
        mTbLayout = contentView.findViewById(R.id.tb_layout);
        mVp = contentView.findViewById(R.id.vp);
        fragmentsList = new ArrayList<>();
        ew1 = new EarlyWarningFragment1();
        ew2 = new EarlyWarningFragment2();
        ew3 = new EarlyWarningFragment3();
        ew4 = new EarlyWarningFragment4();
        // mWageDistributionDeclarationFragment.rep
        fragmentsList.add(ew1);
        fragmentsList.add(ew2);
        fragmentsList.add(ew3);
        fragmentsList.add(ew4);
        String[] titles = new String[]{"实名不符", "民工超龄","用当地人","潜亏预警"};
        FragmentManager fm= getActivity().getSupportFragmentManager();
        ViewPagerAdapter adapter = new ViewPagerAdapter(getChildFragmentManager(),fragmentsList,titles);
        mVp.setAdapter(adapter);
        mTbLayout.setupWithViewPager(mVp);
        getBarTool().hideTitleBar();

    }





}
