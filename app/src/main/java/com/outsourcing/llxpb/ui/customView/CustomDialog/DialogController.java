package com.outsourcing.llxpb.ui.customView.CustomDialog;

import android.content.Context;
import android.content.DialogInterface;
import android.util.SparseArray;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;


class DialogController {
    public CustomDialog mDialog;
    public Window mWindow;

    public void setViewHelper(ViewHelper mViewHelper) {
        this.mViewHelper = mViewHelper;
    }

    private ViewHelper mViewHelper;

    public DialogController(CustomDialog dialog, Window window) {
        this.mDialog = dialog;
        this.mWindow = window;
    }

    public CustomDialog getDialog() {
        return mDialog;
    }

    public Window getWindow() {
        return mWindow;
    }

    public void setText(int viewId, CharSequence text) {
        mViewHelper.setText(viewId, text);
    }

    public void setOnClickListener(int viewId, View.OnClickListener listener) {
        mViewHelper.setOnClickListener(viewId, listener);
    }

    public <T extends View> T getView(int viewId) {
        return mViewHelper.getView(viewId);
    }

    public static class DialogParams {
        public Context mContext;
        public int mThemeResId;
        public View mContentView;
        public int mViewLayoutResId;
        public boolean mCancelable = true;
        public DialogInterface.OnCancelListener mOnCancelListener;
        public DialogInterface.OnDismissListener mOnDismissListener;
        public DialogInterface.OnKeyListener mOnKeyListener;
        public SparseArray<View.OnClickListener> mClickArray = new SparseArray<>();
        public SparseArray<CharSequence> mTextArray = new SparseArray<>();
        public SparseArray<CharSequence> mHintArray = new SparseArray<>();
        public int mWidth = ViewGroup.LayoutParams.WRAP_CONTENT;
        public int mHeight = ViewGroup.LayoutParams.WRAP_CONTENT;
        public int mGravity = Gravity.CENTER;
        public int mAnimation = 0;
        public int mCloseViewId;

        public DialogParams(Context context, int themeResId) {
            this.mContext = context;
            this.mThemeResId = themeResId;
        }

        public void apply(DialogController controller) {
            ViewHelper viewHelper = null;
            if (mViewLayoutResId != 0) {
                viewHelper = new ViewHelper(mContext, mViewLayoutResId);
            }
            if (mContentView != null) {
                viewHelper = new ViewHelper();
                viewHelper.setContentView(mContentView);
            }
            if (viewHelper == null) {
                throw new IllegalArgumentException("请先设置dialog布局视图,setContentView()");
            }
            //给dialog设置布局
            controller.getDialog().setContentView(viewHelper.getContentView());
            controller.setViewHelper(viewHelper);
            //1.设置文本
            for (int i = 0; i < mTextArray.size(); i++) {
                viewHelper.setText(mTextArray.keyAt(i), mTextArray.valueAt(i));
            }
            //2.设置EditText Placeholder
            for (int i = 0; i < mHintArray.size(); i++) {
                viewHelper.setHint(mHintArray.keyAt(i), mHintArray.valueAt(i));
            }
            //3.设置点击
            for (int i = 0; i < mClickArray.size(); i++) {
                viewHelper.setOnClickListener(mClickArray.keyAt(i), mClickArray.valueAt(i));
            }
            //4.设置自定义属性
            //设置方位
            Window window = controller.getWindow();
            window.setGravity(mGravity);
            //设置尺寸
            WindowManager.LayoutParams params = window.getAttributes();
            params.width = mWidth;
            params.height = mHeight;
            window.setAttributes(params);
            //设置动画
            if (mAnimation != 0) {
                window.setWindowAnimations(mAnimation);
            }
            if(mCloseViewId!=0){
                viewHelper.bindCloseView(mCloseViewId,controller);
            }
        }
    }
}
