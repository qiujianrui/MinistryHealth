package com.yhjx.ministryhealth.ui.home;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.necer.calendar.MonthCalendar;
import com.necer.painter.InnerPainter;
import com.yhjx.ministryhealth.R;
import com.yhjx.ministryhealth.base.BaseActivity;
import com.yhjx.ministryhealth.painter.CustomPainter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RemindActivity extends BaseActivity implements View.OnClickListener {

    private ImageView imgBack;
    private ImageView imgAddRecord;
    private LinearLayout llCalendar;
    private ImageView imgLastContent;
    private TextView tvSelectedDate;
    private ImageView imgNextPager;
    private RecyclerView listContent;
    private MonthCalendar calendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_remind;
    }

    @Override
    protected void initView() {

        imgBack = findViewById(R.id.img_back);
        imgAddRecord = findViewById(R.id.img_add_record);
        llCalendar = findViewById(R.id.ll_calendar);
        imgLastContent = findViewById(R.id.img_last_content);
        imgLastContent.setOnClickListener(this);
        tvSelectedDate = findViewById(R.id.tv_selected_date);
        imgNextPager = findViewById(R.id.img_next_pager);
        imgNextPager.setOnClickListener(this);
        listContent = findViewById(R.id.list_content);
        calendar = findViewById(R.id.calendar_month);

    }

    @Override
    protected void initData() {
        CustomPainter customPainter = new CustomPainter(this,calendar);
        calendar.setCalendarPainter(customPainter);
//            List<String> workdayList = new ArrayList<>(bean.getData().getWorkTimeList());
//            innerPainter.setLegalHolidayList(new ArrayList<>(), workdayList);

        List<String> pointList = Arrays.asList("2021-11-01", "2021-07-19", "2021-11-25", "2021-11-23", "2021-11-01", "2021-11-23");
            //List<String> pointList = new ArrayList<>(bean.getData().getAppointmentTimeList());
        customPainter.setPointList(pointList);
        List<String> bgPointList = Arrays.asList("2021-11-01", "2021-07-19", "2021-11-21", "2021-11-20", "2021-11-01", "2021-11-22");

        customPainter.setBGPointList(bgPointList);

    }

    @Override
    protected void setListener() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.img_last_content:
                calendar.toLastPager();
                break;
            case R.id.img_next_pager:
                calendar.toNextPager();
                break;
        }
    }
}