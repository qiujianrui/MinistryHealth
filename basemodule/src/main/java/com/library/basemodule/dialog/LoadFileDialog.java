package com.library.basemodule.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import androidx.annotation.NonNull;

import com.library.basemodule.R;


/**
 * <pre>
 *     author: 梁幸福
 *     time  : 2018/12/17
 *     desc  : 文件上传dialog
 * </pre>
 */
public class LoadFileDialog extends Dialog {
    public LoadFileDialog(@NonNull Context context) {
        this(context,R.style.ProgressDialog);
    }

    public LoadFileDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_file_loading);
        setCancelable(true);
    }
}