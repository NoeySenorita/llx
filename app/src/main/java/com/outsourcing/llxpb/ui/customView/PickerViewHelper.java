package com.outsourcing.llxpb.ui.customView;

import android.content.Context;
import android.graphics.Color;

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;

import java.util.List;

public class PickerViewHelper {
    public static OptionsPickerView createOneLevelView(Context context, List<? extends Object> data, String title, OnOptionsSelectListener listener){
        OptionsPickerView pv = new OptionsPickerBuilder(context,listener)
                .setTitleText(title)
                .setContentTextSize(20)//设置滚轮文字大小
                .setDividerColor(Color.LTGRAY)//设置分割线的颜色
                .setSelectOptions(0, 1)//默认选中项
                .setBgColor(Color.WHITE)
                .setTitleBgColor(Color.WHITE)
                .setTitleColor(Color.LTGRAY)
                .setCancelColor(Color.parseColor("#ff156cf0"))
                .setSubmitColor(Color.parseColor("#ff156cf0"))
                .setTextColorCenter(Color.BLACK)
                .isRestoreItem(true)//切换时是否还原，设置默认选中第一项。
                .isCenterLabel(false) //是否只显示中间选中项的label文字，false则每项item全部都带有label。
                .build();
        pv.setPicker(data);
        return pv;
    }
    public static OptionsPickerView createTwoLevelView(Context context, List<? extends Object> dataOne,
                                                       List<? extends Object> dataTwo,
                                                       String title,
                                                       OnOptionsSelectListener listener){
        OptionsPickerView pv = new OptionsPickerBuilder(context,listener)
                .setTitleText(title)
                .setContentTextSize(20)//设置滚轮文字大小
                .setDividerColor(Color.LTGRAY)//设置分割线的颜色
                .setSelectOptions(0, 1)//默认选中项
                .setBgColor(Color.WHITE)
                .setTitleBgColor(Color.WHITE)
                .setTitleColor(Color.LTGRAY)
                .setCancelColor(Color.parseColor("#ff156cf0"))
                .setSubmitColor(Color.parseColor("#ff156cf0"))
                .setTextColorCenter(Color.BLACK)
                .isRestoreItem(true)//切换时是否还原，设置默认选中第一项。
                .isCenterLabel(false) //是否只显示中间选中项的label文字，false则每项item全部都带有label。
                .build();
        pv.setPicker(dataOne,dataTwo);
        return pv;
    }
}
