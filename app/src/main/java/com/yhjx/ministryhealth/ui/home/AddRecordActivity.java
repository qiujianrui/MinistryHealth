package com.yhjx.ministryhealth.ui.home;

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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.TimePickerView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.library.basemodule.entity.BaseEntity;
import com.library.basemodule.util.ToastUtils;
import com.yhjx.ministryhealth.R;
import com.yhjx.ministryhealth.base.BaseActivity;
import com.yhjx.ministryhealth.bean.DrugNameBean;
import com.yhjx.ministryhealth.mvp.contract.AddMedicineContract;
import com.yhjx.ministryhealth.mvp.contract.AddRemindContract;
import com.yhjx.ministryhealth.mvp.presenter.AddMedicinePresenter;
import com.yhjx.ministryhealth.mvp.presenter.AddRemindPresenter;
import com.yhjx.ministryhealth.util.DataUtil;
import com.yhjx.ministryhealth.view.popup.SearchDrugAdapter;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * @author
 * @time 2021/9/18
 * 说明：新增记录
 */
public class AddRecordActivity extends BaseActivity implements View.OnClickListener, AddMedicineContract.View, AddRemindContract.View {

    private ImageView imgBack;

    private Button tvSave;
    private String dateCreate;
    private AddMedicinePresenter addMedicinePresenter;
    private ScrollView scrollLayout;
    private TextView tvTime;
    private ConstraintLayout llTypeMedicine;
    private EditText editMedicineName;
    private ImageView idImg1;
    private EditText editForenoonDose;
    private EditText editNoonDose;
    private EditText editAfternoonDose;
    private EditText editDrugLongName;
    private EditText editDateLong;
    private EditText editDrugLongNum;
    private RecyclerView listSearch;

    private SearchDrugAdapter searchDrugAdapter;
    private boolean isSearchSelect;
    private int searchEditState; // 1 药品 2 长针剂
    private Date dateSelect;

    private AddRemindPresenter addRemindPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_add_record2;
    }

    @Override
    protected void initView() {
        imgBack = findViewById(R.id.img_back);
        imgBack.setOnClickListener(this);

        tvSave = findViewById(R.id.tv_save);
        tvSave.setOnClickListener(this);
        scrollLayout = findViewById(R.id.scroll_layout);
        tvTime = findViewById(R.id.tv_time);
        tvTime.setOnClickListener(this);
        llTypeMedicine = findViewById(R.id.ll_type_medicine);
        editMedicineName = findViewById(R.id.edit_medicine_name);
        idImg1 = findViewById(R.id.id_img_1);
        editForenoonDose = findViewById(R.id.edit_forenoon_dose);
        editNoonDose = findViewById(R.id.edit_noon_dose);
        editAfternoonDose = findViewById(R.id.edit_afternoon_dose);
        editDrugLongName = findViewById(R.id.edit_drugLongName);
        editDateLong = findViewById(R.id.edit_dateLong);
        editDrugLongNum = findViewById(R.id.edit_drugLongNum);
        listSearch = findViewById(R.id.list_search);
        searchDrugAdapter = new SearchDrugAdapter();
        listSearch.setLayoutManager(new LinearLayoutManager(this));
        listSearch.setAdapter(searchDrugAdapter);
    }


    @Override
    protected void initData() {
        addRemindPresenter = new AddRemindPresenter(this, this);
        dateCreate = getIntent().getStringExtra("dateCreate");
        addMedicinePresenter = new AddMedicinePresenter(this, this);
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
            case R.id.tv_save:
                SimpleDateFormat format = new SimpleDateFormat("HH:mm");

                if (tvTime.getText().toString().isEmpty()) {
                    ToastUtils.showShort("请选取日期");
                    return;
                }
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
                    hashMap.put("type", "0");
                    hashMap.put("remindData", "服药提醒");
                    String dateStart= format.format(dateSelect);
                    hashMap.put("dateStart", dateStart);
                    hashMap.put("dateCreate", tvTime.getText().toString());
                    hashMap.put("drugName",editMedicineName.getText().toString());
                    hashMap.put("forenoon",editForenoonDose.getText().toString());
                    hashMap.put("noon",editNoonDose.getText().toString());
                    hashMap.put("afternoon",editAfternoonDose.getText().toString());
                    hashMap.put("drugLongName",editDrugLongName.getText().toString());
                    hashMap.put("dateLong",editDateLong.getText().toString());
                    hashMap.put("drugLongNum",editDrugLongNum.getText().toString());
                    addRemindPresenter.addRemindDrug(hashMap);
                break;
        }
    }

    @Override
    public void addMedicineSuccess(BaseEntity data) {
        ToastUtils.showShort("添加成功");
        setResult(200);
        finish();

    }

    @Override
    public void addRemindSuccess(BaseEntity data) {
        ToastUtils.showShort("添加成功");
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
}