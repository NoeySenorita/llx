package com.outsourcing.llxpb.ui.customView;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.outsourcing.llxpb.R;

public class AmountView extends LinearLayout implements View.OnClickListener, TextWatcher {

    private static final String TAG = "AmountView";

    public int getAmount() {
        return amount;
    }

    public void setMinLimitAccount(int minLimitAccount) {
        this.minLimitAccount = minLimitAccount;
        etAmount.setText(minLimitAccount + "");
    }

    private int minLimitAccount = 1;
    private int amount = 1; //购买数量
    private int goods_storage = 100; //商品库存

    private OnAmountChangeListener mListener;

    private EditText etAmount;
    private Button btnDecrease;
    private Button btnIncrease;

    public AmountView(Context context) {
        this(context, null);
    }

    public AmountView(Context context, AttributeSet attrs) {
        super(context, attrs);

        LayoutInflater.from(context).inflate(R.layout.view_amount, this);
        etAmount = findViewById(R.id.etAmount);
        btnDecrease = findViewById(R.id.btnDecrease);
        btnIncrease = findViewById(R.id.btnIncrease);
        btnDecrease.setOnClickListener(this);
        btnIncrease.setOnClickListener(this);
        etAmount.setSelection(etAmount.getText().length());
        etAmount.addTextChangedListener(this);

        TypedArray obtainStyledAttributes = getContext().obtainStyledAttributes(attrs, R.styleable.AmountView);
        int btnWidth = obtainStyledAttributes.getDimensionPixelSize(R.styleable.AmountView_btnWidth, LayoutParams.WRAP_CONTENT);
        int tvWidth = obtainStyledAttributes.getDimensionPixelSize(R.styleable.AmountView_tvWidth, 80);
        int tvTextSize = obtainStyledAttributes.getDimensionPixelSize(R.styleable.AmountView_tvTextSize, 0);
        int btnTextSize = obtainStyledAttributes.getDimensionPixelSize(R.styleable.AmountView_btnTextSize, 0);
        obtainStyledAttributes.recycle();

        LayoutParams btnParams = new LayoutParams(btnWidth, LayoutParams.MATCH_PARENT);
        btnDecrease.setLayoutParams(btnParams);
        btnIncrease.setLayoutParams(btnParams);
        if (btnTextSize != 0) {
            btnDecrease.setTextSize(TypedValue.COMPLEX_UNIT_PX, btnTextSize);
            btnIncrease.setTextSize(TypedValue.COMPLEX_UNIT_PX, btnTextSize);
        }

        LayoutParams textParams = new LayoutParams(tvWidth, LayoutParams.MATCH_PARENT);
        etAmount.setLayoutParams(textParams);
        if (tvTextSize != 0) {
            etAmount.setTextSize(tvTextSize);
        }
        etAmount.clearFocus();
    }

    public void setOnAmountChangeListener(OnAmountChangeListener onAmountChangeListener) {
        this.mListener = onAmountChangeListener;
    }

    public void setGoods_storage(int goods_storage) {
        this.goods_storage = goods_storage;
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.btnDecrease) {
            if (amount > 0) {
                amount--;
                etAmount.setText(amount + "");
            }
        } else if (i == R.id.btnIncrease) {
            if (amount < goods_storage) {
                amount++;
                etAmount.setText(amount + "");
            }
        }

        etAmount.clearFocus();
        etAmount.setSelection(etAmount.getText().length());
        if (mListener != null) {
            mListener.onAmountChange(this, amount);
        }
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        Log.i(TAG, "beforeTextChanged#CharSequence:" + s.toString() + ",start:" + start + ",count:" + count + ",after" + after);
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        Log.i(TAG, "onTextChanged#CharSequence:" + s.toString() + ",before:" + before + ",count:" + count);
        etAmount.setSelection(etAmount.getText().length());
    }

    @Override
    public void afterTextChanged(Editable s) {
        Log.i(TAG, "afterTextChanged#Editable:" + s.toString());
//        if (s.toString().isEmpty()) {
//            if(minLimitAccount>1){
//                amount = minLimitAccount;
//                etAmount.setText(""+minLimitAccount);
//            }else {
//                amount = 1;
//                etAmount.setText("1");
//            }
//        } else {
//            amount = Integer.valueOf(s.toString());
//            if(minLimitAccount>amount){
//                amount = minLimitAccount;
//                etAmount.setText(""+amount);
//            }
//        }
        if (!s.toString().isEmpty()) {
            amount = Integer.valueOf(s.toString());
        } else {
            amount = 0;
        }
        if (amount > goods_storage) {
            etAmount.setText(goods_storage + "");
        }

        if (mListener != null) {
            mListener.onAmountChange(this, amount);
        }
    }

    public void setAmount(int amount) {
        etAmount.setText(""+amount);
    }

    public interface OnAmountChangeListener {
        void onAmountChange(AmountView view, int amount);
    }

}


