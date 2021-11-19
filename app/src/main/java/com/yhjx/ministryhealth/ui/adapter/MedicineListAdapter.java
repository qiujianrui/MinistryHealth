package com.yhjx.ministryhealth.ui.adapter;

import androidx.annotation.NonNull;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.yhjx.ministryhealth.R;
import com.yhjx.ministryhealth.bean.MedicineListBean;

public class MedicineListAdapter extends BaseQuickAdapter<MedicineListBean, BaseViewHolder> {
    public MedicineListAdapter() {
        super(R.layout.item_medicine_record);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder baseViewHolder, MedicineListBean data) {
        baseViewHolder.setText(R.id.tv_dose,data.getWeightStr()+"g");
        baseViewHolder.setText(R.id.tv_time,data.getTimesStr());
        baseViewHolder.setText(R.id.tv_type,data.getTypelStr());
        baseViewHolder.setText(R.id.tv_name,data.getNameStr());
    }
}
