package com.outsourcing.llxpb.ui.customView.CustomDialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.outsourcing.llxpb.R;


public class CustomDialog extends Dialog {
    private DialogController mController;

    public CustomDialog(@NonNull Context context) {
        super(context);
    }

    public CustomDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
        mController = new DialogController(this,getWindow());
    }

    public static class Builder{
        private final DialogController.DialogParams P ;
        /**
         * Creates a builder for an alert dialog that uses the default alert
         * dialog theme.
         * <p>
         * The default alert dialog theme is defined by
         * {@link android.R.attr#alertDialogTheme} within the parent
         * {@code context}'s theme.
         *
         * @param context the parent context
         */
        public Builder(Context context) {
            this(context, R.style.DialogDefaultTheme);
        }

        /**
         * Creates a builder for an alert dialog that uses an explicit theme
         * resource.
         * <p>
         * The specified theme resource ({@code themeResId}) is applied on top
         * of the parent {@code context}'s theme. It may be specified as a
         * style resource containing a fully-populated theme, such as
         * {@link android.R.style#Theme_Material_Dialog}, to replace all
         * attributes in the parent {@code context}'s theme including primary
         * and accent colors.
         * <p>
         * To preserve attributes such as primary and accent colors, the
         * {@code themeResId} may instead be specified as an overlay theme such
         * as {@link android.R.style#ThemeOverlay_Material_Dialog}. This will
         * override only the window attributes necessary to style the alert
         * window as a dialog.
         * <p>
         * Alternatively, the {@code themeResId} may be specified as {@code 0}
         * to use the parent {@code context}'s resolved value for
         * {@link android.R.attr#alertDialogTheme}.
         *
         * @param context the parent context
         * @param themeResId the resource ID of the theme against which to inflate
         *                   this dialog, or {@code 0} to use the parent
         *                   {@code context}'s default alert dialog theme
         */
        public Builder(Context context, int themeResId) {
            P = new DialogController.DialogParams(context, themeResId);
        }

        /**
         * Set a custom view resource to be the contents of the Dialog. The
         * resource will be inflated, adding all top-level views to the screen.
         *
         * @param layoutResId Resource ID to be inflated.
         * @return this Builder object to allow for chaining of calls to set
         *         methods
         */
        public Builder setContentView(int layoutResId) {
            P.mContentView = null;
            P.mViewLayoutResId = layoutResId;
            return this;
        }

        /**
         * Sets a custom view to be the contents of the alert dialog.
         * <p>
         * When using a pre-Holo theme, if the supplied view is an instance of
         * a {@link ListView} then the light background will be used.
         * <p>
         * <strong>Note:</strong> To ensure consistent styling, the custom view
         * should be inflated or constructed using the alert dialog's themed
         * context obtained via {@link #getContext()}.
         *
         * @param view the view to use as the contents of the alert dialog
         * @return this Builder object to allow for chaining of calls to set
         *         methods
         */
        public Builder setContentView(View view) {
            P.mContentView = view;
            P.mViewLayoutResId = 0;
            return this;
        }

        /**
         * Sets whether the dialog is cancelable or not.  Default is true.
         *
         * @return This Builder object to allow for chaining of calls to set methods
         */
        public Builder setCancelable(boolean cancelable) {
            P.mCancelable = cancelable;
            return this;
        }

        /**
         * Sets the callback that will be called if the dialog is canceled.
         *
         * <p>Even in a cancelable dialog, the dialog may be dismissed for reasons other than
         * being canceled or one of the supplied choices being selected.
         * If you are interested in listening for all cases where the dialog is dismissed
         * and not just when it is canceled, see
         * {@link #setOnDismissListener(OnDismissListener) setOnDismissListener}.</p>
         * @see #setCancelable(boolean)
         * @see #setOnDismissListener(OnDismissListener)
         *
         * @return This Builder object to allow for chaining of calls to set methods
         */
        public Builder setOnCancelListener(OnCancelListener onCancelListener) {
            P.mOnCancelListener = onCancelListener;
            return this;
        }

        /**
         * Sets the callback that will be called when the dialog is dismissed for any reason.
         *
         * @return This Builder object to allow for chaining of calls to set methods
         */
        public Builder setOnDismissListener(OnDismissListener onDismissListener) {
            P.mOnDismissListener = onDismissListener;
            return this;
        }

        /**
         * Sets the callback that will be called if a key is dispatched to the dialog.
         *
         * @return This Builder object to allow for chaining of calls to set methods
         */
        public Builder setOnKeyListener(OnKeyListener onKeyListener) {
            P.mOnKeyListener = onKeyListener;
            return this;
        }

        public Builder setOnViewClickListener(int viewId,View.OnClickListener listener) {
            P.mClickArray.put(viewId,listener);
            return this;
        }

        public Builder setText(int viewId,CharSequence sequence) {
            P.mTextArray.put(viewId,sequence);
            return this;
        }

        public Builder setHint(int viewId,CharSequence sequence) {
            P.mHintArray.put(viewId,sequence);
            return this;
        }
        /**
         * 全屏显示
         * */
        public Builder fullWidth() {
            P.mWidth = ViewGroup.LayoutParams.MATCH_PARENT;
            return this;
        }
        public Builder fullScreen() {
            P.mWidth = ViewGroup.LayoutParams.MATCH_PARENT;
            P.mHeight = ViewGroup.LayoutParams.MATCH_PARENT;
            return this;
        }
        public Builder fromBottom(boolean hasAnimation) {
            if(hasAnimation){
                P.mAnimation = R.style.DialogFromBottomAnimate;
            }
            P.mGravity = Gravity.BOTTOM;
            return this;
        }
        public Builder fromTop(boolean hasAnimation) {
            if(hasAnimation){
                P.mAnimation = R.style.DialogFromTopAnimate;
            }
            P.mGravity = Gravity.TOP;
            return this;
        }

        public Builder setWidthAndHeight(int width,int height) {
            P.mWidth = width;
            P.mHeight = height;
            return this;
        }

        public Builder addDefaultAnimation() {
            P.mAnimation = R.style.DialogScaleAnimate;
            return this;
        }

        public Builder addAnimation(int animationStyle) {
            P.mAnimation = animationStyle;
            return this;
        }
        public Builder bindCloseView(int viewId) {
            P.mCloseViewId = viewId;
            return this;
        }
        /**
         * Creates an {@link AlertDialog} with the arguments supplied to this
         * builder.
         * <p>
         * Calling this method does not display the dialog. If no additional
         * processing is needed, {@link #show()} may be called instead to both
         * create and display the dialog.
         */
         CustomDialog create() {
            // Context has already been wrapped with the appropriate theme.
            final CustomDialog dialog = new CustomDialog(P.mContext,P.mThemeResId);
            P.apply(dialog.mController);
            dialog.setCancelable(P.mCancelable);
            if (P.mCancelable) {
                dialog.setCanceledOnTouchOutside(true);
            }
            dialog.setOnCancelListener(P.mOnCancelListener);
            dialog.setOnDismissListener(P.mOnDismissListener);
            if (P.mOnKeyListener != null) {
                dialog.setOnKeyListener(P.mOnKeyListener);
            }
            return dialog;
        }

        /**
         * Creates an {@link AlertDialog} with the arguments supplied to this
         * builder and immediately displays the dialog.
         * <p>
         * Calling this method is functionally identical to:
         * <pre>
         *     AlertDialog dialog = builder.create();
         *     dialog.show();
         * </pre>
         */
        public CustomDialog show() {
            final CustomDialog dialog = create();
            dialog.show();
            return dialog;
        }
    }

    public void setText(int viewId, CharSequence text) {
        mController.setText(viewId,text);
    }

    public void setOnClickListener(int viewId, View.OnClickListener listener) {
        mController.setOnClickListener(viewId,listener);
    }

    public <T extends View> T getView(int viewId) {
        return mController.getView(viewId);
    }
}
