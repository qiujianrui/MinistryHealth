package com.library.basemodule.widget.pageLayout;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Looper;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.library.basemodule.R;


/**
 * Created by ${Hankkin} on 2018/10/12.
 */

public class PageLayout extends FrameLayout {
    public  static int BASE_LOADING_LAYOUT_ID ;//= R.layout.pager_loading
    public static int BASE_ERROR_LAYOUT_ID ;//= R.layout.pager_error
    public  static int BASE_EMPTY_LAYOUT_ID ;//= R.layout.pager_empty
    enum State {
        EMPTY_TYPE,
        LOADING_TYPE,
        ERROR_TYPE,
        CONTENT_TYPE,
        CUSTOM_TYPE
    }

    private View mLoading;
    private View mEmpty;
    private View mError;
    private TextView mTvError;
    private View mContent;
    private View mCustom;
    private Context mContext;
    private State mCurrentState;

    /**
     *
     * @param layoutIdOfEmpty
     * @param layoutIdOfLoading
     * @param layoutIdOfError
     */
    public static void initInApp(int layoutIdOfEmpty,int layoutIdOfLoading,int layoutIdOfError){

        if(layoutIdOfEmpty > 0){
            BASE_EMPTY_LAYOUT_ID = layoutIdOfEmpty;
        }

        if(layoutIdOfLoading >0){
            BASE_LOADING_LAYOUT_ID = layoutIdOfLoading;
        }

        if(layoutIdOfError >0){
            BASE_ERROR_LAYOUT_ID = layoutIdOfError;
        }


    }
    public PageLayout(@NonNull Context context) {
        super(context);
    }

    public PageLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public PageLayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
    private void showView(final State state){
        this.showView(state,"");
    }
    private void showView(final State state,String errString) {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            changeView(state,errString);
        } else {
            post(new Runnable() {
                @Override
                public void run() {
                    changeView(state,errString);
                }
            });
        }
    }

    private void changeView(State state,String errString) {
        mCurrentState = state;
        setViewGone();
        switch (state) {
            case LOADING_TYPE:
                mLoading.setVisibility(VISIBLE);
                break;
            case EMPTY_TYPE:
                mEmpty.setVisibility(VISIBLE);
                break;
            case ERROR_TYPE:
                mError.setVisibility(VISIBLE);
                if (!errString.isEmpty()) {
                    mTvError.setText(errString);
                }
                break;
            case CUSTOM_TYPE:
                if (mCustom != null)
                    mCustom.setVisibility(VISIBLE);
                break;
            case CONTENT_TYPE:
                mContent.setVisibility(VISIBLE);
                break;
        }
    }

    private void setViewGone() {
        mLoading.setVisibility(GONE);
        mEmpty.setVisibility(GONE);
        mError.setVisibility(GONE);
        mContent.setVisibility(GONE);
        if (mCustom != null) {
            mCustom.setVisibility(GONE);
        }
    }

    public void showLoading() {
        showView(State.LOADING_TYPE);
    }

    public void showError() {
        showView(State.ERROR_TYPE);

    }
    public void showError(String errString) {
        showView(State.ERROR_TYPE,errString);

    }
    public void showEmpty() {
        showView(State.EMPTY_TYPE);
    }

    public void hide() {
        showView(State.CONTENT_TYPE);
    }

    public void showCustom() {
        showView(State.CUSTOM_TYPE);
    }

    public static class Builder {
        private PageLayout mPageLayout;
        private LayoutInflater mInflater;
        private Context mContext;
        private TextView mTvEmpty;
        private TextView mTvError;
        private TextView mTvErrorRetry;
        private TextView mTvLoading;
        private TextView mTvLoadingBlink;
        private OnRetryClickListener mOnRetryClickListener;

        public Builder(Context context) {
            mContext = context;
            this.mPageLayout = new PageLayout(mContext);
            this.mInflater = LayoutInflater.from(mContext);
        }

        private void initDefault() {
            if (mPageLayout.mEmpty == null) {
                setDefaultEmpty();
            }
            if (mPageLayout.mError == null) {
                setDefaultError();
            }
            if (mPageLayout.mLoading == null) {
                setDefaultLoading();
            }

        }

        private void setDefaultEmpty() {
            if (BASE_EMPTY_LAYOUT_ID==0){
                throw new IllegalArgumentException("??????????????????????????????initInApp??????");
            }
            mPageLayout.mEmpty = mInflater.inflate(BASE_EMPTY_LAYOUT_ID, mPageLayout, false);
            mTvEmpty = mPageLayout.mEmpty.findViewById(R.id.tv_page_empty);
            mPageLayout.mEmpty.setVisibility(GONE);
            mPageLayout.addView(mPageLayout.mEmpty);
        }

        private void setDefaultError() {
            if (BASE_ERROR_LAYOUT_ID==0){
                throw new IllegalArgumentException("??????????????????????????????initInApp??????");
            }
            mPageLayout.mError = mInflater.inflate(BASE_ERROR_LAYOUT_ID, mPageLayout, false);
            mPageLayout.mTvError = mPageLayout.mError.findViewById(R.id.tv_page_error);
            mTvErrorRetry = mPageLayout.mError.findViewById(R.id.tv_page_error_retry);
            mTvErrorRetry.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (mOnRetryClickListener != null) {
                        mOnRetryClickListener.onRetry();
                    }
                }
            });
            mPageLayout.mError.setVisibility(GONE);
            mPageLayout.addView(mPageLayout.mError);
        }


        private void setDefaultLoading() {
            if (BASE_LOADING_LAYOUT_ID==0){
                throw new IllegalArgumentException("??????????????????????????????initInApp??????");
            }
            mPageLayout.mLoading = mInflater.inflate(BASE_LOADING_LAYOUT_ID, mPageLayout, false);
            mPageLayout.mLoading.setVisibility(GONE);
            mPageLayout.addView(mPageLayout.mLoading);
        }

        /**
         * ??????loading??????
         */
        public Builder setLoading(int loadingId, int loadingTvId) {
            View loading = mInflater.inflate(loadingId, mPageLayout, false);
            mTvLoading = loading.findViewById(loadingTvId);
            mPageLayout.mLoading = loading;
            mPageLayout.addView(loading);
            return this;
        }

        /**
         * ?????????????????????
         * ?????????????????????????????????ID??????????????????
         */
        public Builder setError(int errorId, int errorClickId, final OnRetryClickListener onRetryClickListener) {
            View error = mInflater.inflate(errorId, mPageLayout, false);
            mPageLayout.mError = error;
            mPageLayout.addView(error);
            mTvError = error.findViewById(errorClickId);
            mTvError.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View view) {
                    onRetryClickListener.onRetry();
                }
            });
            return this;
        }

        /**
         * ?????????????????????
         * ??????????????????????????????View???????????????
         */
        public Builder setError(View error) {
            mPageLayout.mError = error;
            mPageLayout.addView(error);
            return this;
        }

        /**
         * ??????????????????
         */
        public Builder setEmpty(int empty, int emptyTvId) {
            View view = mInflater.inflate(empty, mPageLayout, false);
            mTvEmpty = view.findViewById(emptyTvId);
            mPageLayout.mEmpty = view;
            mPageLayout.addView(view);
            return this;
        }

        /**
         * ???????????????
         */
        public Builder setCustomView(View view) {
            mPageLayout.mCustom = view;
            mPageLayout.addView(view);
            return this;
        }

        /**
         * ??????????????????
         */
        public Builder setLoadingText(String text) {
            mTvLoading.setText(text);
            return this;
        }

        /**
         * ??????????????????????????????
         */
        public Builder setDefaultLoadingBlinkText(String text) {
            mTvLoadingBlink.setText(text);
            return this;
        }


        /**
         * ????????????????????????
         */
        public Builder setLoadingTextColor(int color) {
            mTvLoading.setTextColor(mContext.getResources().getColor(color));
            return this;
        }

        /**
         * ???????????????????????????
         */
        public Builder setDefaultEmptyText(String text) {
            mTvEmpty.setText(text);
            return this;
        }

        /**
         * ?????????????????????????????????
         */
        public Builder setDefaultEmptyTextColor(int color) {
            mTvEmpty.setTextColor(mContext.getResources().getColor(color));
            return this;
        }

        /**
         * ??????????????????????????????
         */
        public Builder setDefaultErrorText(String text) {
            mTvError.setText(text);
            return this;
        }


        /**
         * ????????????????????????????????????
         */
        public Builder setDefaultErrorTextColor(int color) {
            mTvError.setTextColor(mContext.getResources().getColor(color));
            return this;
        }

        /**
         * ????????????????????????????????????
         */
        public Builder setDefaultErrorRetryText(String text) {
            mTvErrorRetry.setText(text);
            return this;
        }

        /**
         * ??????????????????????????????????????????
         */
        public Builder setDefaultErrorRetryTextColor(int color) {
            mTvErrorRetry.setTextColor(mContext.getResources().getColor(color));
            return this;
        }

        /**
         * ???????????????????????????
         */
        public Builder setEmptyDrawable(int resId) {
            setTopDrawables(mTvEmpty, resId);
            return this;
        }

        /**
         * ??????????????????????????????
         */
        public Builder setErrorDrawable(int resId) {
            setTopDrawables(mTvError, resId);
            return this;
        }


        /**
         * ????????????top drawable
         */
        private Builder setTopDrawables(TextView textView, int resId) {
            if (resId == 0) {
                textView.setCompoundDrawables(null, null, null, null);
            }
            Drawable drawable = mContext.getResources().getDrawable(resId);
            drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());//??????????????????????????????????????????
            textView.setCompoundDrawables(null, drawable, null, null);
            textView.setCompoundDrawablePadding(20);
            return this;
        }

        public Builder initPage(Object targetView) {
            ViewGroup content = null;
            if (targetView instanceof Activity) {
                mContext = (Context) targetView;
                content = ((Activity) targetView).findViewById(android.R.id.content);
            } else if (targetView instanceof Fragment) {
                mContext = ((Fragment) targetView).getActivity();
                content = (ViewGroup) ((Fragment) targetView).getView().getParent();
            } else if (targetView instanceof androidx.fragment.app.Fragment) {//??????V4Fragment
                mContext = ((androidx.fragment.app.Fragment) targetView).getActivity();
                content = (ViewGroup) ((androidx.fragment.app.Fragment) targetView).getView().getParent();
            }else if (targetView instanceof View) {
                mContext = ((View) targetView).getContext();
                content = (ViewGroup) ((View) targetView).getParent();
            }
            if (content != null) {
                int childCount = content.getChildCount();
                int index = 0;
                View oldContent = null;
                if (targetView instanceof View) {
                    oldContent = (View) targetView;
                    for (int i = 0; i < childCount; i++) {
                        if (content.getChildAt(i) == oldContent) {
                            index = i;
                            break;
                        }
                    }
                } else {
                    oldContent = content.getChildAt(0);
                }
                mPageLayout.mContent = oldContent;
                mPageLayout.removeAllViews();
                content.removeView(oldContent);
                ViewGroup.LayoutParams params = oldContent.getLayoutParams();
                content.addView(mPageLayout, index, params);
                mPageLayout.addView(oldContent);
                initDefault();
            }
            return this;
        }

        public Builder setOnRetryListener(OnRetryClickListener onRetryListener) {
            this.mOnRetryClickListener = onRetryListener;
            return this;
        }

        public PageLayout create() {
            return mPageLayout;
        }

    }

    public interface OnRetryClickListener {
        void onRetry();
    }
}
