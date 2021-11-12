package com.library.basemodule.net;

import android.util.Log;

import com.google.gson.Gson;
import com.library.basemodule.entity.BaseEntity;


import java.io.IOException;
import java.lang.reflect.Type;

import okhttp3.ResponseBody;
import retrofit2.Converter;

/**
 * <pre>
 *     author: 梁幸福
 *     time  : 2018/5/15
 *     desc  :
 * </pre>
 */

public final class GsonResponseBodyConverter<T> implements Converter<ResponseBody, T> {
    private final Gson gson;
    private final Type type;

    GsonResponseBodyConverter(Gson gson, Type type) {
        this.gson = gson;
        this.type = type;
    }

    /**
     * 针对数据返回成功、错误不同类型字段处理
     */
    @Override
    public T convert(ResponseBody value) throws IOException {
        String response = value.string();
        try {
            // 这里的type实际类型是 HttpResult<PhoneBean>  PhoneBean就是retData要获取的对象。
            BaseEntity result = gson.fromJson(response, BaseEntity.class);
            if (result.isOk(result.getRet())) {
                return gson.fromJson(response, type);
            } else {
                Log.d("HttpManager", "返回err==：" + response);
                BaseEntity entity = gson.fromJson(response, BaseEntity.class);
                throw new ApiException(entity.getRet(), entity.getErrMsg());
            }
        } finally {
            value.close();
        }
    }
}
