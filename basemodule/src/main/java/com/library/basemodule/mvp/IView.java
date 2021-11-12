package com.library.basemodule.mvp;

/**
 * @author
 * @create 2019/7/18
 * @Describe
 */
public interface IView {
    /**
     * 显示 loading
     */
    void showLoading();

    /**
     * 隐藏 loading
     */
    void hideLoading();
    /**
     * 加载失败回调
     * */
    void loadFailure(int errCode,String msg, String action);
}
