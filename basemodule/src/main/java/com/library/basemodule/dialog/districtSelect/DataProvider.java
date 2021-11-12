package com.library.basemodule.dialog.districtSelect;

import java.util.List;


public interface DataProvider {
    void provideData(int currentDeep, int preId, DataReceiver receiver);


    interface DataReceiver {
        void send(List<ISelectAble> data);
    }
}
