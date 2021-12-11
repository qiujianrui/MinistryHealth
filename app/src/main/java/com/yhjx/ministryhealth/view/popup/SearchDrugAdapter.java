package com.yhjx.ministryhealth.view.popup;

import androidx.annotation.NonNull;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.yhjx.ministryhealth.R;
import com.yhjx.ministryhealth.bean.DrugNameBean;

import java.util.List;

public class SearchDrugAdapter extends BaseQuickAdapter<DrugNameBean, BaseViewHolder> {
    public SearchDrugAdapter() {
        super(R.layout.item_search_drug);
    }



    @Override
    protected void convert(@NonNull BaseViewHolder baseViewHolder, DrugNameBean drugNameBean) {
        baseViewHolder.setText(R.id.tv_name,drugNameBean.getName());
    }
}
