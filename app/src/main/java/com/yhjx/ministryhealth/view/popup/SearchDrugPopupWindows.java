package com.yhjx.ministryhealth.view.popup;

import android.content.Context;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.yhjx.ministryhealth.R;

import java.util.ArrayList;
import java.util.List;

import razerdp.basepopup.BasePopupWindow;

public class SearchDrugPopupWindows extends BasePopupWindow {
    private SearchDrugAdapter searchDrugAdapter;
    private RecyclerView list;


    public SearchDrugPopupWindows(Context context) {
        super(context);
        setContentView(R.layout.layout_search_drug_list);
        list=findViewById(R.id.list);
        init();
    }

    private void init() {
        searchDrugAdapter=new SearchDrugAdapter();
        list.setLayoutManager(new LinearLayoutManager(getContext()));
        list.setAdapter(searchDrugAdapter);

    }
}
