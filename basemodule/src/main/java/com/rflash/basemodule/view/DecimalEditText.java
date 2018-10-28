package com.rflash.basemodule.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v7.widget.AppCompatEditText;
import android.text.InputFilter;
import android.text.Spanned;
import android.util.AttributeSet;

import com.rflash.basemodule.R;

/**
 * Created by yangfan on 2017/11/29.
 */

public class DecimalEditText extends AppCompatEditText {


    private static final int DEFAULT_DECIMAL_NUMBER = 2;
    /**
     * 保留小数点后多少位
     */
    private int mDecimalNumber = DEFAULT_DECIMAL_NUMBER;

    public DecimalEditText(Context context) {
        super(context);
    }

    public DecimalEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public DecimalEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.DecimalEditText);
        mDecimalNumber = typedArray.getInt(R.styleable.DecimalEditText_decimalNumber,DEFAULT_DECIMAL_NUMBER);
        typedArray.recycle();
        init();
    }

    private void init() {


        setFilters(new InputFilter[]{new InputFilter() {
            @Override
            public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
                String lastInputContent = dest.toString();

                if (source.equals(".") && lastInputContent.length() == 0) {
                    return "0.";
                }
                if (lastInputContent.contains(".")) {
                    int index = lastInputContent.indexOf(".");
                    if(dend - index >= mDecimalNumber + 1){
                        return "";
                    }
                }
                return null;
            }
        }});

    }
    public int getDecimalNumber() {
        return mDecimalNumber;
    }

    public void setDecimalNumber(int decimalNumber) {
        mDecimalNumber = decimalNumber;
    }

}
