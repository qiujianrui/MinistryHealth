package com.library.basemodule.dialog.districtSelect;

import android.app.Activity;


import com.library.basemodule.entity.AppCityEntity;
import com.library.basemodule.util.LogUtils;
import com.library.basemodule.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * <pre>
 *     author: 梁幸福
 *     time  : 2018/5/18
 *     desc  :
 * </pre>
 */

public class CitySelectorManage {


//    private static final CitySelectorManage INSTANCE = new CitySelectorManage();
//
//    private static final List<AppCityEntity> sList = new ArrayList<>();
//
//    private CitySelectorManage() {
//        AssetsUtils.getJson("app_city.json", list -> {
//            if (ObjectUtils.isNotEmpty(list)) {
//                sList.addAll(list);
//            }
//        });
//    }
//
//
//    public static CitySelectorManage getInstance() {
//        return INSTANCE;
//    }
//
//    public void showCityDialog(Activity activity) {
//
//
//        if (ObjectUtils.isEmpty(sList)) {
//            return;
//        }
//        final CityBottomDialog dialog = new CityBottomDialog(activity);
//
//        final CitySelector citySelector = new CitySelector(activity, 3);
//        citySelector.setSelectedListener(list -> {
//            LogUtils.e("选择的数据 " + ObjectUtils.isEmpty(list));
//
//
//            if (list.size() == 2) {
//                mOnStateChangeCityListener.onStateChangeCity(
//                        list.get(0).getCityCode(),
//                        list.get(0).getName(),
//                        list.get(0).getCityCode(),
//                        list.get(0).getName(),
//                        list.get(1).getCityCode(),
//                        list.get(1).getName());
//            } else {
//                mOnStateChangeCityListener.onStateChangeCity(
//                        list.get(0).getCityCode(),
//                        list.get(0).getName(),
//                        list.get(1).getCityCode(),
//                        list.get(1).getName(),
//                        list.get(2).getCityCode(),
//                        list.get(2).getName());
//            }
//
//            dialog.dismiss();
//        });
//        final ArrayList<ISelectAble> data = new ArrayList<>();
//        final ArrayList<ISelectAble> cityData = new ArrayList<>();
//        final ArrayList<ISelectAble> listData = new ArrayList<>();
//        final List<AppCityEntity.CityDistrictsEntity> cityList = new ArrayList<>();
//
//        citySelector.setDataProvider((currentDeep, preId, receiver) -> {
//            if (currentDeep == 0) {
//                data.clear();
//                cityData.clear();
//                listData.clear();
//                cityList.clear();
//                for (int i = 0; i < sList.size(); i++) {
//                    AppCityEntity appCityEntity = sList.get(i);
//                    ISelectAble selectAble = getSelectAble(appCityEntity.getName(), i, appCityEntity.getCode());
//                    data.add(selectAble);
//                }
//                receiver.send(data);
//            } else if (currentDeep == 1) {
//                cityData.clear();
//                cityList.clear();
//                data.clear();
//                listData.clear();
//                cityList.addAll(sList.get(preId).getDistricts());
//                for (int i = 0; i < cityList.size(); i++) {
//                    AppCityEntity.CityDistrictsEntity entity = cityList.get(i);
//                    ISelectAble selectAble = getSelectAble(entity.getName(), i, entity.getCode());
//                    cityData.add(selectAble);
//                }
//                receiver.send(cityData);
//            } else if (currentDeep == 2) {
//                listData.clear();
//                List<AppCityEntity.CityDistrictsEntity.DistrictsEntity> entityList = cityList.get(preId).getDistricts();
//                if (ObjectUtils.isEmpty(entityList)) {
//                    citySelector.callbackInternal(2);
//                    return;
//                }
//
//                for (int i = 0; i < entityList.size(); i++) {
//                    AppCityEntity.CityDistrictsEntity.DistrictsEntity entity = entityList.get(i);
//                    ISelectAble selectAble = getSelectAble(entity.getName(), i, entity.getCode());
//                    listData.add(selectAble);
//                }
//                receiver.send(listData);
//            }
//
//        });
//        dialog.init(activity, citySelector);
//        dialog.show();
//    }
//
//    private OnStateChangeCityListener mOnStateChangeCityListener;
//
//
//    public void setOnStateChangeCityListener(OnStateChangeCityListener onStateChangeCityListener) {
//        mOnStateChangeCityListener = onStateChangeCityListener;
//    }
//
//    // 设置地址选择
//    private   ISelectAble getSelectAble(final String name, final int codeId, final String cityCode) {
//        return new ISelectAble() {
//            @Override
//            public String getName() {
//                return name;
//            }
//
//            @Override
//            public int getId() {
//                return codeId;
//            }
//
//            @Override
//            public String getCityCode() {
//                return cityCode;
//            }
//        };
//    }
}
