package com.library.basemodule.dialog;

import android.app.Activity;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;

import androidx.annotation.NonNull;

import com.library.basemodule.R;
import com.library.basemodule.util.ToastUtils;

import java.lang.ref.WeakReference;


/**
 * <pre>
 *     author:
 *     time  : 2018/5/15
 *     desc  : loading的dialog
 * </pre>
 */

public class LoadingDialog extends Dialog {

    private boolean isClear;
    private WeakReference<Context> contextWeakReference;
    public LoadingDialog(@NonNull Context context) {
        super(context, R.style.ProgressDialog);
        contextWeakReference=new WeakReference<Context>(context);
    }

    public LoadingDialog(@NonNull Context context, boolean isClear) {
        super(context, R.style.ProgressDialog);
        this.isClear = isClear;
        contextWeakReference=new WeakReference<Context>(context);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_loading_bg);
        setCancelable(isClear);

    }

    @Override
    protected void onStart() {
        super.onStart();
        setOnKeyListener(new OnKeyListener() {
            @Override
            public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_BACK) {
                    if (contextWeakReference.get() instanceof Activity) {
                        ((Activity) contextWeakReference.get()).finish();
                        dialog.dismiss();//要达到同时销毁效果，可在finish()后dismiss,如果不dismiss，dialog会leak。
                    }
                }
                return false;
            }
        });
    }
}
