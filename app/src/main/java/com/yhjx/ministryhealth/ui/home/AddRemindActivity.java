package com.yhjx.ministryhealth.ui.home;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.bigkoo.pickerview.view.TimePickerView;
import com.google.gson.Gson;
import com.kyle.calendarprovider.calendar.CalendarEvent;
import com.kyle.calendarprovider.calendar.CalendarProviderManager;
import com.library.basemodule.entity.BaseEntity;
import com.library.basemodule.util.ToastUtils;
import com.yhjx.ministryhealth.R;
import com.yhjx.ministryhealth.base.BaseActivity;
import com.yhjx.ministryhealth.mvp.contract.AddRemindContract;
import com.yhjx.ministryhealth.mvp.presenter.AddRemindPresenter;
import com.yhjx.ministryhealth.util.DataUtil;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author
 * @time 2021/9/18
 * 说明：新增提醒
 */
public class AddRemindActivity extends BaseActivity implements View.OnClickListener, AddRemindContract.View {

    private ImageView imgBack;
    private TextView tvTitle;
    private TextView tvType;
    private TextView tvTime;
    private TextView tvReset;
    private Button tvSave;
    private String selectType;
    private String remindData;
    private AddRemindPresenter addRemindPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.WRITE_CALENDAR) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.WRITE_CALENDAR,
                            Manifest.permission.READ_CALENDAR}, 1);
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_add_remind;
    }

    @Override
    protected void initView() {

        imgBack = findViewById(R.id.img_back);
        imgBack.setOnClickListener(this);
        tvTitle = findViewById(R.id.tv_title);
        tvType = findViewById(R.id.tv_type);
        tvType.setOnClickListener(this);
        tvTime = findViewById(R.id.tv_time);
        tvTime.setOnClickListener(this);
        tvReset = findViewById(R.id.tv_reset);
        tvReset.setOnClickListener(this);
        tvSave = findViewById(R.id.tv_save);
        tvSave.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        addRemindPresenter=new AddRemindPresenter(this,this);
    }

    @Override
    protected void setListener() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.img_back:
                finish();
                break;
            case R.id.tv_type:
                 List<String> optionsType = new ArrayList<>();
                List<String> optionsTypeState = new ArrayList<>();
                optionsType.add("服药提醒");
                optionsType.add("复诊提醒");
                optionsTypeState.add("0");
                optionsTypeState.add("1");
                OptionsPickerView typeOptions = new OptionsPickerBuilder(this, new OnOptionsSelectListener() {
                    @Override
                    public void onOptionsSelect(int options1, int options2, int options3, View v) {
                        //返回的分别是三个级别的选中位置
                        String opt1tx = optionsType.size() > 0 ?
                                optionsType.get(options1) : "";
                        tvType.setText(opt1tx);
                        selectType=optionsTypeState.get(options1);
                    }
                })
                        .setTitleText("请选择标题类型")
                        .build();
                typeOptions.setPicker(optionsType);//一级选择器*/
                typeOptions.show();
                break;
            case R.id.tv_time:
                TimePickerView pvTime = new TimePickerBuilder(this, new OnTimeSelectListener() {
                    @Override
                    public void onTimeSelect(Date date, View v) {//选中事件回调
                        tvTime.setText(DataUtil.getDateHM (date));
                    }
                })
                        .setType(new boolean[]{true, true, true, true, true, false})
                        .build();
                pvTime.setTitleText("选取日期");
                pvTime.show();
                break;
            case R.id.tv_reset:
                List<String> optionsReset = new ArrayList<>();
                optionsReset.add("重复");
                optionsReset.add("不重复");
                List<String> optionsResetState = new ArrayList<>();
                optionsResetState.add("1");
                optionsResetState.add("0");
                OptionsPickerView pvOptions = new OptionsPickerBuilder(this, new OnOptionsSelectListener() {
                    @Override
                    public void onOptionsSelect(int options1, int options2, int options3, View v) {
                        //返回的分别是三个级别的选中位置
                        String opt1tx = optionsReset.size() > 0 ?
                                optionsReset.get(options1) : "";
                        tvReset.setText(opt1tx);
                        remindData=optionsResetState.get(options1);
                    }
                })
                        .setTitleText("是否重复")
                        .build();
                pvOptions.setPicker(optionsReset);//一级选择器*/
                pvOptions.show();
                break;
            case R.id.tv_save:
                if (tvType.getText().toString().isEmpty()){
                    ToastUtils.showShort("请选择标题类型");
                    return;
                }
                if (tvTime.getText().toString().isEmpty()){
                    ToastUtils.showShort("请选取日期");
                    return;
                }
                if (tvReset.getText().toString().isEmpty()){
                    ToastUtils.showShort("请选择是否重复");
                    return;
                }

                addRemindPresenter.addRemind(selectType,remindData,tvTime.getText().toString());
                break;
        }
    }

    @Override
    public void addRemindSuccess(BaseEntity data) {
            ToastUtils.showShort("添加成功");
            //添加提醒到日历
        long timeRemind= DataUtil.date2TimeStamp(tvTime.getText().toString(),"yyyy-MM-dd HH:mm:00");
        CalendarEvent calendarEvent = new CalendarEvent(
                tvType.getText().toString(),
                "",
                "",
                timeRemind,
                timeRemind + 1800000,
                0, null
        );

        // 添加事件
        int result = CalendarProviderManager.addCalendarEvent(this, calendarEvent);
//        if (result == 0) {
//            Toast.makeText(this, "插入成功", Toast.LENGTH_SHORT).show();
//        } else if (result == -1) {
//            Toast.makeText(this, "插入失败", Toast.LENGTH_SHORT).show();
//        } else if (result == -2) {
//            Toast.makeText(this, "没有权限", Toast.LENGTH_SHORT).show();
//        }
            finish();
    }
}