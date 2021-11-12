package com.library.basemodule.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.TextView;


@SuppressLint("AppCompatCustomView")
public class SlowShowTextView extends TextView {
    /**
     * default duration
     * 40ms
     */
    private static final int DEFAULT_DURATION = 200;
    /**
     * duration one by one
     * ms
     */
    private int mDuration;

    /**
     * handler
     */
    private BeanShowingHandler mHandler;

    private int mOriginalTextColor;

    private String mOriginalText;

    private String[] mStringList;

    private int mCurrentIndex;

    private String mCurrentText;

    /**
     * handler event
     */
    private static final int EVENT_BEAN_START = 0;
    private static final int EVENT_BEAN_SHOW = 1;

    public interface OnFinishShowListener{
        void onFinish();
    }

    private OnFinishShowListener onFinishShowListener;

    public void setOnFinishShowListener(OnFinishShowListener onFinishShowListener) {
        this.onFinishShowListener = onFinishShowListener;
    }



    public SlowShowTextView(Context context) {
        super(context);
        init();
    }

    public SlowShowTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public SlowShowTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }


    private void init() {
        // set transparent first
        mOriginalTextColor = getCurrentTextColor();
        mOriginalText = (String) getText();
        mStringList = mOriginalText.split("");
        mCurrentIndex = 0;
        mDuration = DEFAULT_DURATION;
        mHandler = new BeanShowingHandler();
        mHandler.sendEmptyMessage(EVENT_BEAN_START);
    }

    private class BeanShowingHandler extends Handler {
        public BeanShowingHandler() {

        }

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case EVENT_BEAN_START:
                    mCurrentText = "";
                    mCurrentIndex = 0;
                    setTextColor(mOriginalTextColor);
                    sendEmptyMessage(EVENT_BEAN_SHOW);
                    break;
                case EVENT_BEAN_SHOW:
                    doBeanShowText();
                    break;
                default:
                    break;
            }
        }
    }

    private void doBeanShowText() {

        if (mOriginalText.isEmpty()||mCurrentIndex>=mStringList.length){
            if (onFinishShowListener!=null){
                onFinishShowListener.onFinish();
            }
            return;
        }
        mCurrentText = mCurrentText + mStringList[mCurrentIndex];

        super.setText(mCurrentText);
        mCurrentIndex++;
        mHandler.sendEmptyMessageDelayed(EVENT_BEAN_SHOW, DEFAULT_DURATION);
    }

    /**
     * set text
     * @param text
     */
    public void setText(String text) {
        if (getVisibility()!=VISIBLE) {
            setVisibility(VISIBLE);
        }
        mHandler.removeMessages(EVENT_BEAN_SHOW);
        mHandler.removeMessages(EVENT_BEAN_START);

        mOriginalTextColor = getCurrentTextColor();
        mOriginalText = text;
        Log.d("SlowShowTextView", "setText: "+mOriginalText);
        mStringList = mOriginalText.split("");

        mHandler.sendEmptyMessage(EVENT_BEAN_START);
    }

    public void clear(){
        super.setText("");
        mHandler.removeMessages(EVENT_BEAN_SHOW);
        mHandler.removeMessages(EVENT_BEAN_START);

    }
}
