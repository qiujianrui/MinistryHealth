package com.library.basemodule.dialog;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatDialog;
import android.view.View;
import android.widget.TextView;

import com.library.basemodule.R;
import com.library.basemodule.listener.OnConfirmClickListener;
import com.library.basemodule.util.ObjectUtils;



/**
 * <pre>
 *     author:
 *     time  : 2019/2/26
 *     desc  :
 * </pre>
 */
public class CommonDialog extends AppCompatDialog implements View.OnClickListener {



    private TextView mTvMsg;
    private TextView mBtnCancel;
    private TextView mBtnConfirm;



    public CommonDialog(Context context) {
        super(context, R.style.DialogTransparent);
        init();
    }

    public CommonDialog(Context context, int theme) {
        super(context, theme);
        this.init();
    }

    public CommonDialog setMessage(@NonNull String message) {

        if (ObjectUtils.isNotEmpty(mTvMsg)) {
            mTvMsg.setText(message);
        }
        return this;
    }

    public CommonDialog setCancel(String cancel) {

        if (ObjectUtils.isNotEmpty(mBtnCancel)) {
            mBtnCancel.setText(cancel);
        }
        return this;
    }

    public CommonDialog setConfirm(String confirm) {

        if (ObjectUtils.isNotEmpty(mBtnConfirm)) {
            mBtnConfirm.setText(confirm);
        }
        return this;
    }


    private void init() {
        setContentView(R.layout.dialog_common);
        mTvMsg = findViewById(R.id.tv_msg);
        mBtnCancel = findViewById(R.id.btn_cancel);
        mBtnConfirm = findViewById(R.id.btn_confirm);
        mBtnCancel.setOnClickListener(this);
        mBtnConfirm.setOnClickListener(this);
    }



    private OnConfirmClickListener onConfirmClickListener;

    public CommonDialog setOnConfirmClickListener(OnConfirmClickListener onConfirmClickListener) {
        this.onConfirmClickListener = onConfirmClickListener;
        return this;
    }

    @Override
    public void onClick(View view) {
        int i = view.getId();
        if (i == R.id.btn_cancel) {
            dismiss();
        } else if (i == R.id.btn_confirm) {
            if (ObjectUtils.isNotEmpty(onConfirmClickListener)) {
                onConfirmClickListener.onConfirmClick(this, view);
            }
            dismiss();
        }
    }
}
