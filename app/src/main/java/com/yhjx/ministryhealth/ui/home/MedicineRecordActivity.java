package com.yhjx.ministryhealth.ui.home;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.library.basemodule.dialog.CommonDialog;
import com.library.basemodule.listener.OnConfirmClickListener;
import com.necer.calendar.BaseCalendar;
import com.necer.calendar.Miui10Calendar;
import com.necer.enumeration.DateChangeBehavior;
import com.necer.listener.OnCalendarChangedListener;
import com.yhjx.ministryhealth.R;
import com.yhjx.ministryhealth.base.BaseActivity;
import com.yhjx.ministryhealth.bean.MedicineListBean;
import com.yhjx.ministryhealth.bean.RemindListBean;
import com.yhjx.ministryhealth.mvp.contract.MedicineRecordContract;
import com.yhjx.ministryhealth.mvp.presenter.MedicineRecordPresenter;
import com.yhjx.ministryhealth.painter.BlackBackground;
import com.yhjx.ministryhealth.painter.CustomPainter;
import com.yhjx.ministryhealth.ui.adapter.MedicineListAdapter;
import com.yhjx.ministryhealth.ui.adapter.RemindListAdapter;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.joda.time.LocalDate;

import java.util.List;

public class MedicineRecordActivity extends BaseActivity implements View.OnClickListener,   MedicineRecordContract.View {

    private ImageView imgBack;
    private ImageView imgAddRecord;
    private LinearLayout llCalendar;
    private ImageView imgLastContent;
    private TextView tvSelectedDate;
    private ImageView imgNextPager;
    private RecyclerView listContent;
    private Miui10Calendar calendar;
    private MedicineListAdapter medicineListAdapter;
    private MedicineRecordPresenter medicineRecordPresenter;
    private LocalDate selectLocalDate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_medicine_record;
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
        medicineListAdapter =new MedicineListAdapter();
        listContent.setAdapter(medicineListAdapter);
        calendar = findViewById(R.id.calendar_month);
        calendar.setMonthCalendarBackground(new BlackBackground());
        calendar.setWeekCalendarBackground(new BlackBackground());

    }

    @Override
    protected void initData() {
        selectLocalDate = LocalDate.now();
        medicineRecordPresenter=new MedicineRecordPresenter(this,this);
        medicineRecordPresenter.getMedicineDate();


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
                medicineRecordPresenter.getMedicine(selectLocalDate.toString("yyyy-MM-dd"));

            }
        });
        medicineListAdapter.setOnItemChildClickListener(new OnItemChildClickListener() {
            @Override
            public void onItemChildClick(@NonNull BaseQuickAdapter adapter, @NonNull View view, int position) {
                if (view.getId()==R.id.check_drug){
                    if (medicineListAdapter.getData().get(position).getStatus().equals("1")) {
                        CommonDialog commonLogoutDialog = new CommonDialog(mContext);
                        commonLogoutDialog.setMessage("是否确认已经服药?").setCancel("取消").setConfirm("确定")
                                .setOnConfirmClickListener(new OnConfirmClickListener() {
                                    @Override
                                    public void onConfirmClick(Dialog dialog, View view) {
                                        medicineRecordPresenter.updRemindInfo(medicineListAdapter.getData().get(position).getRemindId(), "0");
                                    }
                                });
                        commonLogoutDialog.show();
                    }
                }
                else if (view.getId()==R.id.check_drugLong){
                    if (medicineListAdapter.getData().get(position).getStatusLong().equals("1")) {
                        CommonDialog commonLogoutDialog = new CommonDialog(mContext);
                        commonLogoutDialog.setMessage("是否确认已经服药?").setCancel("取消").setConfirm("确定")
                                .setOnConfirmClickListener(new OnConfirmClickListener() {
                                    @Override
                                    public void onConfirmClick(Dialog dialog, View view) {
                                        medicineRecordPresenter.updRemindInfo(medicineListAdapter.getData().get(position).getRemindId(), "1");

                                    }
                                });
                        commonLogoutDialog.show();
                    }
                }
                else if (view.getId()==R.id.img_edit){
                    startActivityForResult(new Intent(MedicineRecordActivity.this,AddRecordActivity.class)
                    .putExtra("data",medicineListAdapter.getData().get(position))
                            ,1);
                }
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
                startActivityForResult(new Intent(this,AddRecordActivity.class ).putExtra("dateCreate",selectLocalDate.toString("yyyy-MM-dd")),1);
                break;

        }
    }


    @Override
    public void getMedicineDateSuccess(List<String> data) {
        CustomPainter customPainter = new CustomPainter(this,calendar);
        calendar.setCalendarPainter(customPainter);
        customPainter.setPointList(data);
    }

    @Override
    public void getMedicine(List<RemindListBean> data) {
        medicineListAdapter.setList(data);
    }

    @Override
    public void updRemindInfoSuccess() {
        medicineRecordPresenter.getMedicine(selectLocalDate.toString("yyyy-MM-dd"));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==1&&resultCode==200){
            medicineRecordPresenter.getMedicineDate();
            medicineRecordPresenter.getMedicine(selectLocalDate.toString("yyyy-MM-dd"));
        }
    }
}