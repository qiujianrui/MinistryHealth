package com.yhjx.ministryhealth.ui.home;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.bigkoo.pickerview.view.TimePickerView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.kyle.calendarprovider.calendar.CalendarEvent;
import com.kyle.calendarprovider.calendar.CalendarProviderManager;
import com.library.basemodule.entity.BaseEntity;
import com.library.basemodule.util.ToastUtils;
import com.yhjx.ministryhealth.R;
import com.yhjx.ministryhealth.base.BaseActivity;
import com.yhjx.ministryhealth.bean.DrugNameBean;
import com.yhjx.ministryhealth.bean.RemindListBean;
import com.yhjx.ministryhealth.mvp.contract.AddRemindContract;
import com.yhjx.ministryhealth.mvp.presenter.AddRemindPresenter;
import com.yhjx.ministryhealth.util.DataUtil;
import com.yhjx.ministryhealth.view.popup.SearchDrugAdapter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
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
    private EditText editMedicineName;
    private ConstraintLayout llTypeMedicine;
    private EditText editForenoonDose;
    private EditText editNoonDose;
    private EditText editAfternoonDose;
    private EditText editDrugLongName;
    private EditText editDateLong;
    private EditText editDrugLongNum;
    private RecyclerView listSearch;
    private ConstraintLayout clTypeVisit;
    private SearchDrugAdapter searchDrugAdapter;
    private ScrollView scrollLayout;
    private boolean isSearchSelect;
    private int searchEditState; // 1 药品 2 长针剂
    private Date dateSelect;

    private RemindListBean remindListData;
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
        return R.layout.activity_add_remind2;
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
        editMedicineName = findViewById(R.id.edit_medicine_name);
        llTypeMedicine = findViewById(R.id.ll_type_medicine);
        editForenoonDose = findViewById(R.id.edit_forenoon_dose);
        editNoonDose = findViewById(R.id.edit_noon_dose);
        editAfternoonDose = findViewById(R.id.edit_afternoon_dose);
        editDrugLongName = findViewById(R.id.edit_drugLongName);
        editDateLong = findViewById(R.id.edit_dateLong);
        editDrugLongNum = findViewById(R.id.edit_drugLongNum);
        listSearch = findViewById(R.id.list_search);
        clTypeVisit = findViewById(R.id.cl_type_visit);
        searchDrugAdapter = new SearchDrugAdapter();
        listSearch.setLayoutManager(new LinearLayoutManager(this));
        listSearch.setAdapter(searchDrugAdapter);
        tvType.setText("服药提醒");
        selectType = "0";
        scrollLayout = findViewById(R.id.scroll_layout);
    }

    @Override
    protected void initData() {
        addRemindPresenter = new AddRemindPresenter(this, this);
        remindListData= (RemindListBean) getIntent().getSerializableExtra("data");
        //修改 获取数据
        if (remindListData!=null){
            if (remindListData.getType().equals("0")){
                llTypeMedicine.setVisibility(View.VISIBLE);
                selectType="0";
                tvType.setText(remindListData.getRemindData());
                remindData=remindListData.getRemindData();
                tvTime.setText(remindListData.getDateCreate());
                editMedicineName.setText(remindListData.getDrugName());
                editForenoonDose.setText(remindListData.getForenoon());
                editNoonDose.setText(remindListData.getNoon());
                editAfternoonDose.setText(remindListData.getAfternoon());
                editDrugLongName.setText(remindListData.getDrugLongName());
                editDateLong.setText(remindListData.getDateLong());
                editDrugLongNum.setText(remindListData.getDrugLongNum());
            }else if (remindListData.getType().equals("1")){
            llTypeMedicine.setVisibility(View.GONE);
                selectType="1";
                tvTime.setText(remindListData.getDateCreate());
            }
        }
    }

    @Override
    protected void setListener() {
        scrollLayout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction()==MotionEvent.ACTION_DOWN){
                    listSearch.setVisibility(View.GONE);
                }
                return false;
            }
        });
        searchDrugAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(@NonNull BaseQuickAdapter<?, ?> adapter, @NonNull View view, int position) {
                isSearchSelect=true;
                if (searchEditState==1){
                    editMedicineName.setText(searchDrugAdapter.getData().get(position).getName());
                }
                if (searchEditState==2){
                    editDrugLongName.setText(searchDrugAdapter.getData().get(position).getName());
                }
                listSearch.setVisibility(View.GONE);

            }
        });
        editMedicineName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!s.toString().isEmpty()) {
                    if (!isSearchSelect) {
                        searchEditState=1;
                        addRemindPresenter.getDrug(s.toString());
                    }
                }else {
                    listSearch.setVisibility(View.GONE);
                }
                isSearchSelect=false;
            }
        });
        editDrugLongName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!s.toString().isEmpty()) {
                    if (!isSearchSelect) {
                        searchEditState=2;
                        addRemindPresenter.getDrug(s.toString());
                    }
                }else {
                    listSearch.setVisibility(View.GONE);
                }
                isSearchSelect=false;
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.img_back:
                finish();
                break;
            case R.id.tv_type:
                if (remindListData!=null){
                    ToastUtils.showShort("无法修改类型");
                    return;
                }
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
                        selectType = optionsTypeState.get(options1);
                        if (options1 == 0) {
                            llTypeMedicine.setVisibility(View.VISIBLE);
                        } else if (options1 == 1) {
                            llTypeMedicine.setVisibility(View.GONE);
                        }
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
                        dateSelect=date;
                        tvTime.setText(DataUtil.getDateHM(date));

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
                        remindData = optionsResetState.get(options1);
                    }
                })
                        .setTitleText("是否重复")
                        .build();
                pvOptions.setPicker(optionsReset);//一级选择器*/
                pvOptions.show();
                break;
            case R.id.tv_save:
                if (tvType.getText().toString().isEmpty()) {
                    ToastUtils.showShort("请选择标题类型");
                    return;
                }
                if (tvTime.getText().toString().isEmpty()) {
                    ToastUtils.showShort("请选取日期");
                    return;
                }

                if (selectType.equals("0")){
                    if (editMedicineName.getText().toString().isEmpty()&&editDrugLongName.getText().toString().isEmpty()){
                        ToastUtils.showShort("请输入药品名称或长效针剂名称");
                        return;
                    }
                    if (!editMedicineName.getText().toString().isEmpty()&&editForenoonDose.getText().toString().isEmpty()&&
                    editNoonDose.getText().toString().isEmpty()&&
                            editAfternoonDose.getText().toString().isEmpty()
                    ){
                        ToastUtils.showShort("请输入药品剂量");
                        return;
                    }
                    if (!editDrugLongName.getText().toString().isEmpty()){
                        if (editDateLong.getText().toString().isEmpty()||editDrugLongNum.getText().toString().isEmpty()){
                            ToastUtils.showShort("请输入长效针剂时间及剂量");
                            return;
                        }
                    }
                    HashMap<String, Object> hashMap = new HashMap<>();
                    hashMap.put("type", selectType);
                    hashMap.put("remindData", remindData);
                    hashMap.put("dateCreate", tvTime.getText().toString());
                    hashMap.put("drugName",editMedicineName.getText().toString());
                    hashMap.put("forenoon",editForenoonDose.getText().toString());
                    hashMap.put("noon",editNoonDose.getText().toString());
                    hashMap.put("afternoon",editAfternoonDose.getText().toString());
                    hashMap.put("drugLongName",editDrugLongName.getText().toString());
                    hashMap.put("dateLong",editDateLong.getText().toString());
                    hashMap.put("drugLongNum",editDrugLongNum.getText().toString());
                    if (remindListData==null) {
                        addRemindPresenter.addRemindDrug(hashMap);
                    }else {
                        hashMap.put("dateId",remindListData.getDateId());
                        addRemindPresenter.revampRemindInfo(hashMap);
                    }
                }
                if (selectType.equals("1")){
                    if (remindListData==null) {
                        addRemindPresenter.addRemind(selectType, remindData,tvTime.getText().toString());
                    }else {
                        HashMap<String, Object> hashMap = new HashMap<>();
                        hashMap.put("type", selectType);
                        hashMap.put("remindData", remindData);
                        hashMap.put("dateCreate", tvTime.getText().toString());
                        hashMap.put("dateId", remindListData.getDateId());
                        addRemindPresenter.revampRemindInfo(hashMap);
                    }
                }

                break;
        }
    }

    @Override
    public void addRemindSuccess(BaseEntity data) {
        ToastUtils.showShort("添加成功");
        //添加提醒到日历
//        long timeRemind = DataUtil.date2TimeStamp(tvTime.getText().toString(), "yyyy-MM-dd HH:mm:00");
//        CalendarEvent calendarEvent = new CalendarEvent(
//                tvType.getText().toString(),
//                "",
//                "",
//                timeRemind,
//                timeRemind + 1800000,
//                0, null
//        );
        //FREQ=DAILY;COUNT=30 每天发生一次，重复30次：
        // 添加事件
       //int result = CalendarProviderManager.addCalendarEvent(this, calendarEvent);
//        if (result == 0) {
//            Toast.makeText(this, "插入成功", Toast.LENGTH_SHORT).show();
//        } else if (result == -1) {
//            Toast.makeText(this, "插入失败", Toast.LENGTH_SHORT).show();
//        } else if (result == -2) {
//            Toast.makeText(this, "没有权限", Toast.LENGTH_SHORT).show();
//        }
        setResult(200);
        finish();
    }

    @Override
    public void getDrugSuccess(List<DrugNameBean> data) {
        if (data.size()>0) {
            listSearch.setVisibility(View.VISIBLE);
            searchDrugAdapter.setList(data);
        }
    }

    @Override
    public void revampRemindInfoSuccess() {
        ToastUtils.showShort("修改成功");
        setResult(200);
        finish();
    }
}