package com.library.basemodule.dialog.districtSelect;

/**
 * <pre>
 *     author: 梁幸福
 *     time  : 2018/5/18
 *     desc  : 城市选择的回调
 * </pre>
 */

public interface OnStateChangeCityListener {
    /**
     * @param provinceCode 省会编码
     * @param provinceName 省会的名字
     * @param cityCode     城市编码
     * @param cityName     城市名字
     * @param areaCode     区县编码
     * @param areaName     区县名字
     */
    void onStateChangeCity(String provinceCode, String provinceName, String cityCode, String cityName, String areaCode, String areaName);
}
