package com.library.basemodule.dialog.districtSelect;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;

import com.library.basemodule.R;
import com.library.basemodule.util.ObjectUtils;
import com.library.basemodule.util.SizeUtils;


public class CityBottomDialog extends Dialog {
    private CitySelector mCitySelector;

    public CityBottomDialog(Context context) {
        super(context, R.style.bottom_dialog);
    }

    public CityBottomDialog(Context context, int themeResId) {
        super(context, themeResId);
    }

    public CityBottomDialog(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    public void init(Context context,CitySelector citySelector) {
        this.mCitySelector = citySelector;
        setContentView(citySelector.getView());
        Window window = getWindow();
        if (ObjectUtils.isNotEmpty(window)) {
            WindowManager.LayoutParams params = window.getAttributes();
            params.width = WindowManager.LayoutParams.MATCH_PARENT;
            params.height = SizeUtils.dp2px( 256);
            window.setAttributes(params);

            window.setGravity(Gravity.BOTTOM);
        }
    }

    public void setOnAddressSelectedListener(SelectedListener listener) {
        this.mCitySelector.setSelectedListener(listener);
    }

    public static CityBottomDialog show(Context context) {
        return show(context, null);
    }

    public static CityBottomDialog show(Context context, SelectedListener listener) {
        CityBottomDialog dialog = new CityBottomDialog(context, R.style.bottom_dialog);
        dialog.mCitySelector.setSelectedListener(listener);
        dialog.show();

        return dialog;
    }
}
