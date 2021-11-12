package com.library.basemodule.dialog.districtSelect;

public interface ISelectAble {
    /**
     * 显示在栏目上的名字
     */
    String getName();

    /**
     * 用户设定的id，根据这个id，可以获取级栏目或者指定为最终栏目的id
     */
    int getId();

    /**
     * 城市的编号
     */
    String getCityCode();

}
