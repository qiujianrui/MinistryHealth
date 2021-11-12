package com.library.basemodule.dialog;


import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import androidx.annotation.NonNull;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;


import com.library.basemodule.R;
import com.library.basemodule.util.ObjectUtils;

import butterknife.ButterKnife;



public abstract class BaseBottomDialog extends Dialog {


    protected Context mContext;
    public BaseBottomDialog(@NonNull Context context) {
        super(context, R.style.BottomAnimDialogStyle);
        mContext = context;
    }

    public BaseBottomDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
        mContext = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getLayoutId() == 0) {
            throw new RuntimeException("layout布局文件不能为空");
        }
        setContentView(getLayoutId());
		setGravity();
		initData();
    }

    // 可以做数据操作
	protected abstract void initData();

	private void setGravity() {
        //获取当前Activity所在的窗体
        Window dialogWindow = getWindow();
        if (ObjectUtils.isEmpty(dialogWindow)) {
            return;
        }

        //设置Dialog从窗体底部弹出
        dialogWindow.setGravity(Gravity.BOTTOM);
        dialogWindow.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);

    }

	protected abstract int getLayoutId();
}
