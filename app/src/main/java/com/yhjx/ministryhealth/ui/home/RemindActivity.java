package com.yhjx.ministryhealth.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.necer.calendar.BaseCalendar;
import com.necer.calendar.Miui10Calendar;
import com.necer.calendar.MonthCalendar;
import com.necer.enumeration.CalendarState;
import com.necer.enumeration.DateChangeBehavior;
import com.necer.listener.OnCalendarChangedListener;
import com.necer.listener.OnCalendarMultipleChangedListener;
import com.necer.listener.OnCalendarStateChangedListener;
import com.necer.painter.InnerPainter;
import com.yhjx.ministryhealth.R;
import com.yhjx.ministryhealth.base.BaseActivity;
import com.yhjx.ministryhealth.bean.RemindDateBean;
import com.yhjx.ministryhealth.bean.RemindListBean;
import com.yhjx.ministryhealth.mvp.contract.RemindContract;
import com.yhjx.ministryhealth.mvp.presenter.RemindPresenter;
import com.yhjx.ministryhealth.painter.BlackBackground;
import com.yhjx.ministryhealth.painter.CustomPainter;
import com.yhjx.ministryhealth.ui.adapter.RemindListAdapter;

import org.joda.time.LocalDate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RemindActivity extends BaseActivity implements View.OnClickListener, RemindContract.View {

    private ImageView imgBack;
    private ImageView imgAddRecord;
    private LinearLayout llCalendar;
    private ImageView imgLastContent;
    private TextView tvSelectedDate;
    private ImageView imgNextPager;
    private RecyclerView listContent;
    private Miui10Calendar calendar;
    private RemindListAdapter remindListAdapter;
    private RemindPresenter remindPresenter;
    private LocalDate selectLocalDate;
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
        imgBack.setOnClickListener(this);
        imgAddRecord = findViewById(R.id.img_add_record);
        imgAddRecord.setOnClickListener(this);
        llCalendar = findViewById(R.id.ll_calendar);
        imgLastContent = findViewById(R.id.img_last_content);
        imgLastContent.setOnClickListener(this);
        tvSelectedDate = findViewById(R.id.tv_selected_date);
        imgNextPager = findViewById(R.id.img_next_pager);
        imgNextPager.setOnClickListener(this);
        listContent = findViewById(R.id.list_content);
        listContent.setLayoutManager(new LinearLayoutManager(this));
        remindListAdapter=new RemindListAdapter();
        listContent.setAdapter(remindListAdapter);
        calendar = findViewById(R.id.calendar_month);
        calendar.setMonthCalendarBackground(new BlackBackground());
        calendar.setWeekCalendarBackground(new BlackBackground());

    }

    @Override
    protected void initData() {
        selectLocalDate = LocalDate.now();
        remindPresenter=new RemindPresenter(this,this);
        remindPresenter.getRemindDate();


    }

    @Override
    protected void setListener() {

        calendar.setOnCalendarChangedListener(new OnCalendarChangedListener() {
            @Override
            public void onCalendarChange(BaseCalendar baseCalendar, int year, int month, LocalDate localDate, DateChangeBehavior dateChangeBehavior) {
                selectLocalDate = localDate;
//                if (calendar.getCalendarState() == CalendarState.WEEK) {
//                    tvSelectedDate.setText(localDate.getWeekyear() + " 第" + localDate.getWeekOfWeekyear() + "周");
//                } else {
                    tvSelectedDate.setText(year + "  " + month);
//                }
                remindPresenter.getRemind(selectLocalDate.toString("yyyy-MM-dd"));

            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.img_back:
                finish();
                break;
            case R.id.img_last_content:
                calendar.toLastPager();
                break;
            case R.id.img_next_pager:
                calendar.toNextPager();
                break;
            case R.id.img_add_record:
                startActivity(new Intent(this,AddRemindActivity.class ));
                break;

        }
    }

    @Override
    public void getRemindDateSuccess(RemindDateBean data) {
        CustomPainter customPainter = new CustomPainter(this,calendar);
        calendar.setCalendarPainter(customPainter);
        customPainter.setPointList(data.getTakeRemindTimeList());
        customPainter.setBGPointList(data.getVisitRemindTimeList());
    }

    @Override
    public void getRemind(List<RemindListBean> data) {
        remindListAdapter.setList(data);
    }
}