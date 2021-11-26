package com.yhjx.ministryhealth.ui.adapter;

import androidx.annotation.NonNull;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.yhjx.ministryhealth.R;
import com.yhjx.ministryhealth.bean.RemindListBean;

public class RemindListAdapter extends BaseQuickAdapter<RemindListBean, BaseViewHolder> {
    public RemindListAdapter() {
        super(R.layout.item_remind);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder baseViewHolder, RemindListBean data) {
        baseViewHolder.setText(R.id.tv_time_end_start,data.getDateStart());
        baseViewHolder.setText(R.id.tv_name,data.getRemindData());
    }
}
