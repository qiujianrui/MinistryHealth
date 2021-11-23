package com.yhjx.ministryhealth.ui.home;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.library.basemodule.entity.BaseEntity;
import com.library.basemodule.util.ToastUtils;
import com.yhjx.ministryhealth.R;
import com.yhjx.ministryhealth.base.BaseActivity;
import com.yhjx.ministryhealth.mvp.contract.AddMedicineContract;
import com.yhjx.ministryhealth.mvp.presenter.AddMedicinePresenter;

import org.greenrobot.eventbus.EventBus;

/**
 * @author
 * @time 2021/9/18
 * 说明：新增记录
 */
public class AddRecordActivity extends BaseActivity implements View.OnClickListener, AddMedicineContract.View {

    private ImageView imgBack;
    private EditText editMedicineClass;
    private EditText editMedicineName;
    private EditText editMedicineNum;
    private EditText editMedicineHz;
    private Button tvSave;

    private AddMedicinePresenter addMedicinePresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_add_record;
    }

    @Override
    protected void initView() {
        imgBack = findViewById(R.id.img_back);
        imgBack.setOnClickListener(this);
        editMedicineClass = findViewById(R.id.edit_medicine_class);
        editMedicineName = findViewById(R.id.edit_medicine_name);
        editMedicineNum = findViewById(R.id.edit_medicine_num);
        editMedicineHz = findViewById(R.id.edit_medicine_hz);
        tvSave = findViewById(R.id.tv_save);
        tvSave.setOnClickListener(this);
    }


    @Override
    protected void initData() {
        addMedicinePresenter=new AddMedicinePresenter(this,this);
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
            case R.id.tv_save:
                if (editMedicineClass.getText().toString().isEmpty()){
                    ToastUtils.showShort("请输入药品大类");
                    return;
                }
                if (editMedicineName.getText().toString().isEmpty()){
                    ToastUtils.showShort("请输入药品名称");
                    return;
                }
                if (editMedicineNum.getText().toString().isEmpty()){
                    ToastUtils.showShort("请输入剂量");
                    return;
                }
                if (editMedicineHz.getText().toString().isEmpty()){
                    ToastUtils.showShort("请输入频率");
                    return;
                }
                addMedicinePresenter.addMedicine(editMedicineClass.getText().toString()
                ,editMedicineName.getText().toString(),
                        editMedicineNum.getText().toString(),
                        editMedicineHz.getText().toString());
                break;
        }
    }

    @Override
    public void addMedicineSuccess(BaseEntity data) {
        ToastUtils.showShort("添加成功");
        finish();

    }
}