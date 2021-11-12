package com.library.basemodule.widget;

import android.content.Context;
import android.content.res.Resources;
import android.os.CountDownTimer;
import androidx.appcompat.widget.AppCompatTextView;
import android.util.AttributeSet;
import android.util.TypedValue;

import com.library.basemodule.R;
import com.library.basemodule.util.Utils;





public class TextViewCountDownView extends AppCompatTextView {

	//倒计时的时间
	public static final long TIME_REMAINING = 60000;
	/**
	 * 倒计时
	 */
	private CountDownTimer countDownTimer;
	private Resources resources;


	public TextViewCountDownView(Context context, AttributeSet attrs) {
		super(context, attrs);
		resources = Utils.getApp().getResources();
		setTextSize(TypedValue.COMPLEX_UNIT_PX, resources.getDimension(R.dimen.text_count_down));
	}

	/**
	 * 启动倒计时
	 *
	 * @param timeRemaining(毫秒)
	 *            总共的时间
	 */
	public void startCountDown(long timeRemaining) {

		// 由于CountDownTimer并不是准确计时，在onTick方法调用的时候，time会有1-10ms左右的误差，这会导致最后一秒不会调用onTick()
		// 因此，设置间隔的时候，默认减去了10ms，从而减去误差。
		// 经过以上的微调，最后一秒的显示时间会由于10ms延迟的积累，导致显示时间比1s长max*10ms的时间，其他时间的显示正常,总时间正常
		countDownTimer = new CountDownTimer(timeRemaining, 1000 - 10) {
			@Override
			public void onTick(long time) {
				long time1 = (time + 15) / 1000;
				// 第一次调用会有1-10ms的误差，因此需要+15ms，防止第一个数不显示，第二个数显示2s
				setText(time1 + "s后获取");
				setSelected(false);
				setEnabled(false);
				if (mOnIsCountDownListener != null) {
					mOnIsCountDownListener.onIsCountDown(true);
				}
			}

			@Override
			public void onFinish() {
				setEnabled(true);
				setSelected(true);
				setText("重新获取");
				if (countDownTimer != null) {
					countDownTimer.cancel();
				}
				if (mOnIsCountDownListener != null) {
					mOnIsCountDownListener.onIsCountDown(false);
				}

			}
		};

		countDownTimer.start();

	}
	/**
	 * 取消倒计时
	 */
	public TextViewCountDownView cancelCountDown() {
		if (countDownTimer!=null) {
			countDownTimer.cancel();
		}
		return this;
	}

	public OnIsCountDownListener mOnIsCountDownListener;

	public TextViewCountDownView setOnIsCountDownListener(OnIsCountDownListener onIsCountDownListener) {
		mOnIsCountDownListener = onIsCountDownListener;
		return this;
	}

	public  interface  OnIsCountDownListener{
		void onIsCountDown(boolean isStart);
	}


}
