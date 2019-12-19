package com.outsourcing.llxpb.ui.customView;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.support.v4.content.res.ResourcesCompat;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.outsourcing.llxpb.R;
import com.outsourcing.llxpb.util.ui.UIUtils;

public class TimerTextView extends FrameLayout {

    private TextView textView;

    public TimerTextView(Context context) {
        this(context, null);
    }

    public TimerTextView(Context context, AttributeSet attrs) {
        this(context, attrs, -1);
    }

    public TimerTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.TimerTextView);
        int tvSize = ta.getDimensionPixelSize(R.styleable.TimerTextView_tTextSize, 23);
        int tvColor = ta.getColor(R.styleable.TimerTextView_tTextColor, 0xFF666666);
        textView = new TextView(context);
        UIUtils.setFont(textView,R.font.digital);
        Typeface typeface = ResourcesCompat.getFont(context, R.font.digital);
        textView.setTypeface(typeface);
        textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, tvSize);
        textView.setTextColor(tvColor);
        textView.setText("00:00:00");
        addView(textView);
    }

    public void updateTime(long time) {
        int hour = (int) (time / 3600);
        int minute = (int) (time / 60) - hour * 60;
        int second = (int) (time % 60);
        String ss = second < 10 ? "0" + second : "" + second;
        String mm = minute < 10 ? "0" + minute : "" + minute;
        String hr = hour < 10 ? "0" + hour : "" + hour;
        textView.setText(hr + ":" + mm + ":" + ss);
    }
}
