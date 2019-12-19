package com.outsourcing.llxpb.ui.customView;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.Selection;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.outsourcing.llxpb.R;
import com.outsourcing.llxpb.util.ui.UIUtils;

public class SearchView extends FrameLayout implements View.OnClickListener, TextWatcher {
    private EditText mEtText;
    private TextView mTvSearch;
    private OnSearchListener onSearchListener;

    public void setOnTextChangeListener(OnTextChangeListener onTextChangeListener) {
        this.onTextChangeListener = onTextChangeListener;
    }

    private OnTextChangeListener onTextChangeListener;

    public void setOnSearchListener(OnSearchListener onSearchListener) {
        this.onSearchListener = onSearchListener;
    }

    public SearchView(@NonNull Context context) {
        this(context, null);
    }

    public SearchView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, -1);
    }

    public SearchView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        View view = LayoutInflater.from(context).inflate(R.layout.view_search_layout, null, false);
        initView(context, view);
        addView(view);
    }

    private void initView(Context context, View view) {
        mEtText = view.findViewById(R.id.et_text);
        mTvSearch = view.findViewById(R.id.tv_search);
        mTvSearch.setOnClickListener(this);
        mEtText.addTextChangedListener(this);
    }

    @Override
    public void onClick(View v) {
        String text = mEtText.getText().toString();
        if(TextUtils.isEmpty(text)){
            UIUtils.showToast("请输入关键词");
        }else {
            if(onSearchListener!=null){
                onSearchListener.onSearch(text);
            }
        }
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        if(onTextChangeListener!=null){
            onTextChangeListener.onTextChanged(s,start,before,count);
        }
    }

    @Override
    public void afterTextChanged(Editable s) {

    }

    public interface OnSearchListener{
        void onSearch(String text);
    }
    public interface OnTextChangeListener{
        void onTextChanged(CharSequence s, int start, int before, int count);
    }
    public void clearText(){
        setText("");
    }
    public void setText(String text){
        mEtText.setText(text);
        Editable etext = mEtText.getText();
        Selection.setSelection(etext, etext.length());
    }
}
