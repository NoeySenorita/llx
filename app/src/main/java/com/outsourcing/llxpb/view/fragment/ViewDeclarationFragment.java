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

public class ViewDeclarationFragment extends BaseFragment {

    private TabLayout mTbLayout;
    private ViewPager mVp;
    private ArrayList<Fragment> fragmentsList;

    private View show;
    private WageDistributionDeclarationFragment mWageDistributionDeclarationFragment;
    private MonthlyCompletionDeclarationFragment mMonthlyCompletionDeclarationFragment;

    @Override
    public View getContentView() {
        View view = LayoutInflater.from(mActivity).inflate(R.layout.fragment_view_declaration, null, false);
        return view;
    }

    @Override
    protected void initContentView(View contentView) {
        getBarTool().setTitle(UserUtil.default_project_short_name);
        show = contentView.findViewById(R.id.show);
        getBarTool().hideIvTitleBar();
        mTbLayout = contentView.findViewById(R.id.tb_layout);
        mVp = contentView.findViewById(R.id.vp);
        fragmentsList = new ArrayList<>();
        mWageDistributionDeclarationFragment = new WageDistributionDeclarationFragment();
        mMonthlyCompletionDeclarationFragment = new MonthlyCompletionDeclarationFragment();
       // mWageDistributionDeclarationFragment.rep
        fragmentsList.add(mWageDistributionDeclarationFragment);
        fragmentsList.add(mMonthlyCompletionDeclarationFragment);
        String[] titles = new String[]{"验工申请", "工资申报申报"};
        FragmentManager fm= getActivity().getSupportFragmentManager();
        ViewPagerAdapter adapter = new ViewPagerAdapter(getChildFragmentManager(),fragmentsList,titles);
        mVp.setAdapter(adapter);
        mTbLayout.setupWithViewPager(mVp);
        getBarTool().hideTitleBar();
    }

}
