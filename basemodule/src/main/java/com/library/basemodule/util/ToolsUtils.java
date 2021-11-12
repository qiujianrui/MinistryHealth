package com.library.basemodule.util;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.view.Gravity;
import android.widget.Toast;

public class ToolsUtils {

    /**
     * @param context
     * @param data
     * 复制data至剪贴板
     */
    public static void copy(Context context,String data,String toastStr) {
        // 获取系统剪贴板
        ClipboardManager clipboard = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
        // 创建一个剪贴数据集，包含一个普通文本数据条目（需要复制的数据）,其他的还有
        // newHtmlText、
        // newIntent、
        // newUri、
        // newRawUri
        ClipData clipData = ClipData.newPlainText(null, data);
        // 把数据集设置（复制）到剪贴板
        clipboard.setPrimaryClip(clipData);
        Toast toast = Toast.makeText(context, toastStr, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();

    }

}
