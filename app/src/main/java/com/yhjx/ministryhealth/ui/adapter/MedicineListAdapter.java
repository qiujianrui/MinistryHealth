package com.yhjx.ministryhealth.ui.adapter;

import android.widget.CheckBox;
import android.widget.ImageView;

import androidx.annotation.NonNull;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.yhjx.ministryhealth.R;
import com.yhjx.ministryhealth.bean.MedicineListBean;
import com.yhjx.ministryhealth.bean.RemindListBean;

public class MedicineListAdapter extends BaseQuickAdapter<RemindListBean, BaseViewHolder> {
    public MedicineListAdapter() {
        super(R.layout.item_medicine_record2);
        addChildClickViewIds(R.id.check_drug,R.id.check_drugLong,R.id.img_edit);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder baseViewHolder, RemindListBean data) {
        if (data.getDrugName().isEmpty()){
            baseViewHolder.setGone(R.id.cl_drug, true);
        }else {
            baseViewHolder.setGone(R.id.cl_drug, false);
            baseViewHolder.setText(R.id.tv_drugName,data.getDrugName());
            baseViewHolder.setText(R.id.tv_time,data.getDateStart());
            baseViewHolder.setText(R.id.tv_drugNum,"早:"+data.getForenoonUnit()+";"+
                            "中:"+data.getNoonUnit()+";"+
                            "晚:"+data.getAfternoonUnit()+";"
                    );
            ImageView checkBox=baseViewHolder.getView(R.id.check_drug);
            if (data.getStatus().equals("0")){
                checkBox.setSelected(true);
            }else {
                checkBox.setSelected(false);
            }
        }

        if (data.getDrugLongName().isEmpty()){
            baseViewHolder.setGone(R.id.cl_drugLong, true);
        }else {
            baseViewHolder.setGone(R.id.cl_drugLong, false);
            baseViewHolder.setText(R.id.tv_drugLongName,data.getDrugLongName());
            baseViewHolder.setText(R.id.tv_dateLong_time,data.getDateStart());
            baseViewHolder.setText(R.id.tv_drugLongNum,data.getLongPeriod()+" "+data.getLongUnit());
            ImageView checkBox=baseViewHolder.getView(R.id.check_drugLong);
            if (data.getStatusLong().equals("0")){
                checkBox.setSelected(true);
            }else {
                checkBox.setSelected(false);
            }
        }

        if (data.getDrugLongName().isEmpty()||data.getDrugName().isEmpty()){
            baseViewHolder.setGone(R.id.view_line, true);
        }else {
            baseViewHolder.setGone(R.id.view_line, false);
        }

    }
}
