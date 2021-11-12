package com.library.basemodule.dialog;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatDialog;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.library.basemodule.R;
import com.library.basemodule.listener.OnConfirmClickListener;
import com.library.basemodule.util.ObjectUtils;

/**
 * @author qjr
 * @create 2019/8/14
 * @Describe
 */
public class HintDialog extends AppCompatDialog implements View.OnClickListener {
    private OnConfirmClickListener onConfirmClickListener;

    public HintDialog setOnConfirmClickListener(OnConfirmClickListener onConfirmClickListener) {
        this.onConfirmClickListener = onConfirmClickListener;
        return this;
    }

    private TextView mTvMsg;
    private TextView mBtnCancel;
    private int gravity;

    public HintDialog(Context context) {
        super(context, R.style.DialogHint);
        this.gravity=Gravity.CENTER;
        init();
    }
    public HintDialog(Context context,int gravity) {
        super(context, R.style.DialogHint);
        this.gravity=gravity;
        init();
    }


    public HintDialog setMessage(@NonNull String message) {

        if (ObjectUtils.isNotEmpty(mTvMsg)) {
            mTvMsg.setText(message);
        }
        return this;
    }

    public HintDialog setCancel(String cancel) {

        if (ObjectUtils.isNotEmpty(mBtnCancel)) {
            mBtnCancel.setText(cancel);
        }
        return this;
    }


    private void init() {
        setContentView(R.layout.dialog_hint);
        mTvMsg = findViewById(R.id.tv_msg);
        mTvMsg.setGravity(gravity);
        mBtnCancel = findViewById(R.id.btn_cancel);
        mBtnCancel.setOnClickListener(this);

    }


    @Override
    public void onClick(View view) {
        int i = view.getId();
        if (i == R.id.btn_cancel) {
            if (onConfirmClickListener!=null){
                onConfirmClickListener.onConfirmClick(this,view);
            }
            dismiss();
        }
    }
}

