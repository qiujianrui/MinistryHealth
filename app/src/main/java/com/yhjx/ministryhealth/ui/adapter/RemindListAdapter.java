package com.yhjx.ministryhealth.ui.adapter;

import androidx.annotation.NonNull;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.yhjx.ministryhealth.R;
import com.yhjx.ministryhealth.bean.RemindListBean;

public class RemindListAdapter extends BaseQuickAdapter<RemindListBean, BaseViewHolder> {
    public RemindListAdapter() {
        super(R.layout.item_remind2);
        addChildClickViewIds(R.id.img_edit);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder baseViewHolder, RemindListBean data) {
        baseViewHolder.setText(R.id.tv_time,data.getDateStart());
        baseViewHolder.setText(R.id.tv_hint,data.getRemindData());
        //服药提醒
        if (data.getType().equals("0")) {
            if (data.getDrugName().isEmpty()) {
                baseViewHolder.setGone(R.id.ll_drug_name, true);
            } else {
                baseViewHolder.setGone(R.id.ll_drug_name, false);
                baseViewHolder.setGone(R.id.tv_drug_data,false);
                baseViewHolder.setText(R.id.tv_drug_name,data.getDrugName());
                baseViewHolder.setText(R.id.tv_drug_data,
                        "早:" + data.getForenoonUnit() + ";"
                        + "中:" + data.getNoonUnit() + ";"
                        + "晚:" + data.getAfternoonUnit()+";"
                );
            }
            if (data.getDrugLongName().isEmpty()) {
                baseViewHolder.setGone(R.id.ll_drugLong_name, true);
            } else {
                baseViewHolder.setGone(R.id.ll_drugLong_name, false);
                baseViewHolder.setText(R.id.tv_drugLong_name,data.getDrugLongName());
                baseViewHolder.setText(R.id.tv_drugLong_data, data.getLongPeriod()
                        + " " + data.getLongUnit());
            }
        }
        //复诊提醒
        if (data.getType().equals("1")){
            baseViewHolder.setGone(R.id.ll_drug_name, false);
            baseViewHolder.setGone(R.id.ll_drugLong_name, true);
            baseViewHolder.setGone(R.id.tv_drug_data,true);
            baseViewHolder.setText(R.id.tv_drug_name,data.getRemindData());
        }
    }
}
