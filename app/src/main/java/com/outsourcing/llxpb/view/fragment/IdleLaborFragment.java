package com.outsourcing.llxpb.view.fragment;

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

public class IdleLaborFragment extends BaseFragment {
    private View show;
    private TabLayout mTbLayout;
    private ViewPager mVp;
    private ArrayList<Fragment> fragmentsList;
    IReleaseFragment mIReleaseFragment;
    LookingLaborFragment mLookingLaborFragment;
    @Override
    public View getContentView() {
        View view = LayoutInflater.from(mActivity).inflate(R.layout.fragment_idle_labor, null, false);
        return view;
    }

    @Override
    protected void initContentView(View contentView) {
        getBarTool().setTitle(UserUtil.default_project_short_name);
        getBarTool().hideIvTitleBar();
        show = contentView.findViewById(R.id.show);
        mTbLayout = contentView.findViewById(R.id.tb_layout);
        mVp = contentView.findViewById(R.id.vp);
        fragmentsList = new ArrayList<>();
        mIReleaseFragment = new IReleaseFragment();
        mLookingLaborFragment = new LookingLaborFragment();
        // mWageDistributionDeclarationFragment.rep
        fragmentsList.add(mIReleaseFragment);
        fragmentsList.add(mLookingLaborFragment);
        String[] titles = new String[]{"我的发布", "找劳力"};
        FragmentManager fm= getActivity().getSupportFragmentManager();
        ViewPagerAdapter adapter = new ViewPagerAdapter(getChildFragmentManager(),fragmentsList,titles);
        mVp.setAdapter(adapter);
        mTbLayout.setupWithViewPager(mVp);
        getBarTool().hideTitleBar();
    }
}
