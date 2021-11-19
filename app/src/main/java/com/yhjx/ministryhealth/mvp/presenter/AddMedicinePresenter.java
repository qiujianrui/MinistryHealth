package com.yhjx.ministryhealth.mvp.presenter;

import android.app.Activity;

import androidx.annotation.NonNull;

import com.alibaba.fastjson.JSON;
import com.library.basemodule.entity.BaseEntity;
import com.library.basemodule.mvp.BasePresenter;
import com.library.basemodule.net.RxHelper;
import com.yhjx.ministryhealth.api.AppHttpUtils;
import com.yhjx.ministryhealth.api.BaseObserver;
import com.yhjx.ministryhealth.mvp.contract.AddMedicineContract;

import java.util.HashMap;

import okhttp3.MediaType;
import okhttp3.RequestBody;

public class AddMedicinePresenter extends BasePresenter<AddMedicineContract.View> implements AddMedicineContract.Presenter {
    public AddMedicinePresenter(AddMedicineContract.View iView, Activity activity) {
        super(iView, activity);
    }

    @Override
    public void addMedicine(String medicineClass, String medicineName, String medicineNum, String medicineHz) {
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("medicineClass",medicineClass );
        hashMap.put("medicineName",medicineName );
        hashMap.put("medicineNum",medicineNum );
        hashMap.put("medicineHz",medicineHz );
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"), JSON.toJSONString(hashMap));
        AppHttpUtils.getApiService().addMedicineRecord(requestBody)
                .compose(RxHelper.ioMain())
                .subscribe(new BaseObserver<BaseEntity>(activityRef.get()) {


                    @Override
                    public void onSuccess(BaseEntity result) {
                        getView().addMedicineSuccess(result);
                    }

                    @Override
                    public void onFailure(int errCode, String msg) {
                          getView().loadFailure(errCode, msg, "");
                    }
                });
    }
}
