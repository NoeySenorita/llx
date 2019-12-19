package com.outsourcing.llxpb.ui.customView;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;
import android.util.Log;

import com.outsourcing.llxpb.R;


public class RangeTextView extends AppCompatTextView {
    private String mBadgeText = "";
    public void setBadgeText(String sequence) {
        this.mBadgeText = sequence;
        Integer integer = Integer.valueOf(mBadgeText);
        if(integer<=0){
            setVisibility(GONE);
        }else {
            if(integer > 9){
                mBadgeText = "9+";
            }
            setText(mBadgeText);
            setVisibility(VISIBLE);
        }
    }
    public RangeTextView(Context context) {
        this(context,null);
    }

    public RangeTextView(Context context, AttributeSet attrs) {
        this(context, attrs,-1);
    }

    public RangeTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.RangeTextView);
        mBadgeText = ta.getString(R.styleable.RangeTextView_badgeText);
        ta.recycle();
        Log.i("RangeTextView:",mBadgeText);
        setBadgeText(mBadgeText);
    }
}
