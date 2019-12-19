package com.outsourcing.llxpb;

import android.support.v4.app.Fragment;

import com.outsourcing.llxpb.model.Constants;
import com.outsourcing.llxpb.ui.base.BaseFragment;
import com.outsourcing.llxpb.view.fragment.IdleLaborFragment;
import com.outsourcing.llxpb.view.fragment.MonthlyCompletionDeclarationFragment;
import com.outsourcing.llxpb.view.fragment.ViewDeclarationFragment;
import com.outsourcing.llxpb.view.fragment.HomeFragment;
import com.outsourcing.llxpb.view.fragment.EarlyWarningFragment;
import com.outsourcing.llxpb.view.fragment.VerificationFragment;

import java.util.HashMap;

/**
 * Created by Administrator on 2018/3/13 0013.
 */

public class FragmentFactory {
    private static HashMap<Integer, BaseFragment> fragmentMap = new HashMap<>();
    public static Fragment createFragment(int position) {
        if(fragmentMap.get(position)!=null){
            return fragmentMap.get(position);
        }else{
            return create(position);
        }
    }

    private static BaseFragment create(int position) {
        BaseFragment fragment;
        switch (position){
            case Constants.FragmentsId.FRAGMENT_HOME:
                fragment = new HomeFragment();
                fragmentMap.put(Constants.FragmentsId.FRAGMENT_HOME,fragment);
                break;
            case Constants.FragmentsId.FRAGMENT_VERIFICATION:
                fragment = new VerificationFragment();
                fragmentMap.put(Constants.FragmentsId.FRAGMENT_VERIFICATION,fragment);
                break;
            case Constants.FragmentsId.FRAGMENT_EARLY_WARNING:
                fragment = new EarlyWarningFragment();
                fragmentMap.put(Constants.FragmentsId.FRAGMENT_EARLY_WARNING,fragment);
                break;
            case Constants.FragmentsId.FRAGMENT_IDLE_LABOR:
                fragment = new IdleLaborFragment();
                fragmentMap.put(Constants.FragmentsId.FRAGMENT_IDLE_LABOR,fragment);
                break;
            case Constants.FragmentsId.FRAGMENT_VIEW_DECLARATION:
                fragment = new ViewDeclarationFragment();
                fragmentMap.put(Constants.FragmentsId.FRAGMENT_VIEW_DECLARATION,fragment);
                break;
            case Constants.FragmentsId.MY1:
                break;
            case Constants.FragmentsId.MY2:
                fragment = new MonthlyCompletionDeclarationFragment();
                fragmentMap.put(Constants.FragmentsId.FRAGMENT_VIEW_DECLARATION,fragment);
                break;
        }
        return fragmentMap.get(position);
    }

    public static void clearCache(){
        if(fragmentMap.size()!=0){
            fragmentMap.clear();
//            Logger.i("All Fragment in Factory has been clear!");
        }
    }
}
