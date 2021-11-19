package com.yhjx.ministryhealth.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.yhjx.ministryhealth.R;

public class WeekDataLayout extends ConstraintLayout {


    private TextView tvEnglishWeekDate;
    private View vPointBg;
    private TextView tvChineseDate;
    private View vPoint;

    public WeekDataLayout(@NonNull Context context) {
        super(context);
        initView(context);
    }

    private void initView(Context context) {
        LayoutInflater.from(context).inflate(R.layout.item_home_week, this, true);

        tvEnglishWeekDate = findViewById(R.id.tv_englishWeekDate);
        vPointBg = findViewById(R.id.v_point_bg);
        tvChineseDate = findViewById(R.id.tv_chineseDate);
        vPoint = findViewById(R.id.v_point);
    }

    public void setData(String englishWeekDate,String chineseDate,String isTakeRemind,String isVisitRemind){
        tvEnglishWeekDate.setText(englishWeekDate);
        tvChineseDate.setText(chineseDate);
        if (isTakeRemind.equals("1")){
            vPoint.setVisibility(VISIBLE);
        }else {
            vPoint.setVisibility(INVISIBLE);
        }
        if (isVisitRemind.equals("1")){
            vPointBg.setVisibility(VISIBLE);
        }else {
            vPointBg.setVisibility(INVISIBLE);
        }
    }
}
