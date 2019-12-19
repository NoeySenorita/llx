package com.outsourcing.llxpb.ui.customView;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.outsourcing.llxpb.R;
import com.outsourcing.llxpb.util.ui.UIUtils;


public class SelectPicPopupWindow extends PopupWindow {

    private TextView btn_take_photo;

    private TextView btn_pick_photo;

    private Button btn_cancel;
    private View mMenuView;
    private Activity mActivity;
    private final LinearLayout mLlPop;

    public SelectPicPopupWindow(Activity context, View.OnClickListener itemsOnClick) {
        super(context);
        mActivity = context;
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mMenuView = inflater.inflate(R.layout.poupwindow_dialog, null);
        btn_take_photo =  mMenuView.findViewById(R.id.btn_take_photo);
        btn_pick_photo =  mMenuView.findViewById(R.id.btn_pick_photo);

        mMenuView.findViewById(R.id.canss).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        mLlPop = mMenuView.findViewById(R.id.ll_pop);

        btn_cancel =  mMenuView.findViewById(R.id.btn__cancel);
        //取消按钮
        btn_cancel.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //销毁弹出框
                dismiss();
            }
        });

        //设置按钮监听
        btn_pick_photo.setOnClickListener(itemsOnClick);
        btn_take_photo.setOnClickListener(itemsOnClick);
        //设置SelectPicPopupWindow的View
        this.setContentView(mMenuView);
        //设置SelectPicPopupWindow弹出窗体的宽
        this.setWidth(RelativeLayout.LayoutParams.MATCH_PARENT);
        //设置SelectPicPopupWindow弹出窗体的高
        this.setHeight(RelativeLayout.LayoutParams.WRAP_CONTENT);
        //设置SelectPicPopupWindow弹出窗体可点击
        this.setFocusable(true);
        //实例化一个ColorDrawable颜色为半透明
        ColorDrawable dw = new ColorDrawable(UIUtils.getColor(R.color.colorPopUpWindowBg)); //android:background="#30000000"
        //设置SelectPicPopupWindow弹出窗体的背景
        this.setBackgroundDrawable(dw);
        //mMenuView添加OnTouchListener监听判断获取触屏位置如果在选择框外面则销毁弹出框
        mMenuView.setOnTouchListener(new View.OnTouchListener() {

            public boolean onTouch(View v, MotionEvent event) {

                int height = mMenuView.findViewById(R.id.pop_layout).getTop();
                int y = (int) event.getY();
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    if (y < height) {
                        dismiss();
                    }
                }
                return true;
            }
        });

    }
    public void show(){
        this.showAtLocation(mActivity.getWindow().getDecorView(), Gravity.BOTTOM, 0, 0);
    }

    @Override
    public void showAsDropDown(View anchor, int xoff, int yoff, int gravity) {
        super.showAsDropDown(anchor, xoff, yoff, gravity);
        postAnimateIn(anchor);
    }

    @Override
    public void showAtLocation(View parent, int gravity, int x, int y) {
        super.showAtLocation(parent, gravity, x, y);
        postAnimateIn(parent);
    }

    private void postAnimateIn(View postView) {
        postView.postDelayed(new Runnable() {
            @Override
            public void run() {
                animateIn();
            }
        }, 1);
    }

    private void animateIn() {

        int height = mLlPop.getHeight();
        mLlPop.setTranslationY(height);
        mLlPop.animate().translationY(0).setDuration(200)
                .setListener(null).start();

    }

    public void superDismiss() {
        super.dismiss();
    }

    @Override
    public void dismiss() {
        animateOut(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                superDismiss();
            }
        });
    }

    private void animateOut(final Animator.AnimatorListener listener) {

        int height = mLlPop.getHeight();
        mLlPop.animate().translationY(height).setListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                listener.onAnimationEnd(animation);
                mLlPop.animate().setListener(null);
            }
        }).setDuration(200).start();

    }

}
