package com.library.basemodule.dialog;


import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import androidx.annotation.ColorRes;
import androidx.annotation.StringRes;
import android.view.View;
import android.widget.TextView;

import com.library.basemodule.R;
import com.library.basemodule.listener.OnConfirmClickListener;
import com.library.basemodule.util.StringUtils;


public class BuilderDialog extends Dialog implements View.OnClickListener {


	// 设置按钮现实的文字
	private String titleStr;
	private String msgStr;
	private String cancelStr;
	private String confirmStr;

	private int titleStrId;
	private int msgStrId;
	private int cancelStrId;
	private int confirmStrId;

	private boolean isShowCancel; // 是否现实取消按钮 true 不显示，false显示
	private OnConfirmClickListener onConfirmClickListener;
	private OnCancelClickListener onCancelClickListener;
	// 按钮颜色的资源id
	private int titleColor;
	private int msgColor;
	private int cancelColor;
	private int confirmColor;


	private Context mContext;

	private BuilderDialog(Context context, Builder builder) {
		super(context, R.style.AppDialogTheme);
		mContext = context;
		titleStr = builder.titleStr;
		msgStr = builder.msgStr;
		cancelStr = builder.cancelStr;
		confirmStr = builder.confirmStr;
		titleStrId = builder.titleStrId;
		msgStrId = builder.msgStrId;
		cancelStrId = builder.cancelStrId;
		confirmStrId = builder.confirmStrId;
		isShowCancel = builder.isShowCancel;
		onConfirmClickListener = builder.onConfirmClickListener;
		titleColor = builder.titleColor;
		msgColor = builder.msgColor;
		cancelColor = builder.cancelColor;
		confirmColor = builder.confirmColor;
		onCancelClickListener = builder.onCancelClickListener;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dialog_builder);
		initView();
	}

	private void initView() {
		TextView mBtnConfirm = findViewById(R.id.btn_confirm);
		TextView mBtnCancel = findViewById(R.id.btn_cancel);
		TextView mTvMsg = findViewById(R.id.tv_msg);
		TextView mTvTitle = findViewById(R.id.tv_title);
		View mLineView = findViewById(R.id.line_view);

		setText(mTvTitle, titleStr);
		setText(mTvMsg, msgStr);
		setText(mBtnConfirm, confirmStr);
		setText(mBtnCancel, cancelStr);

		setColor(mTvTitle, titleColor);
		setColor(mTvMsg, msgColor);
		setColor(mBtnConfirm, confirmColor);
		setColor(mBtnCancel, cancelColor);


		setText(mTvTitle, titleStrId);
		setText(mTvMsg, msgStrId);
		setText(mBtnConfirm, confirmStrId);
		setText(mBtnCancel, cancelStrId);


		if (isShowCancel) { // 是否现实取消按钮 true 不显示，false显示
			setCancelable(false); //点击屏幕或物理返回键
			mBtnCancel.setVisibility(View.GONE);
			mLineView.setVisibility(View.GONE);
		} else {
			mBtnCancel.setVisibility(View.VISIBLE);
			mLineView.setVisibility(View.VISIBLE);
		}
		mBtnConfirm.setOnClickListener(this);
		mBtnCancel.setOnClickListener(this);


	}

	private void setText(TextView textView, @StringRes int stringId) {
		if (stringId == 0) {
			return;
		}
		textView.setText(stringId);
	}

	private void setColor(TextView textView, @ColorRes int colorId) {
		if (colorId == 0) {
			return;
		}
		textView.setTextColor(mContext.getResources().getColor(colorId));
	}

	// 设置文字
	private void setText(TextView textView, String msgStr) {
		if (!StringUtils.isEmpty(msgStr)) {
			textView.setText(msgStr);
		}
	}

	@Override
	public void onClick(View view) {
		int i = view.getId();
		if (i == R.id.btn_confirm) {
			dismiss();
			if (onConfirmClickListener != null) {
				onConfirmClickListener.onConfirmClick(this, view);
			}
		} else if (i == R.id.btn_cancel) {
			dismiss();
			if (onCancelClickListener != null) {
				onCancelClickListener.onConfirmClick(this, view);
			}
		}
	}



	//   取消按钮默认dismiss 回调 onConfirmClick
	public interface OnCancelClickListener {
		void onConfirmClick(Dialog dialog, View view);
	}


	/**
	 * {@code BuilderDialog} builder static inner class.
	 */
	public static final class Builder {
		private String titleStr;
		private String msgStr;
		private String cancelStr;
		private String confirmStr;
		private int titleStrId;
		private int msgStrId;
		private int cancelStrId;
		private int confirmStrId;
		private boolean isShowCancel;
		private OnConfirmClickListener onConfirmClickListener;
		private OnCancelClickListener onCancelClickListener;
		private int titleColor;
		private int msgColor;
		private int cancelColor;
		private int confirmColor;

		public Builder() {
		}

		/**
		 * Sets the {@code titleStr} and returns a reference to this Builder so that the methods can be chained together.
		 *
		 * @param val the {@code titleStr} to set
		 * @return a reference to this Builder
		 */
		public Builder setTitleStr(String val) {
			titleStr = val;
			return this;
		}

		/**
		 * Sets the {@code msgStr} and returns a reference to this Builder so that the methods can be chained together.
		 *
		 * @param val the {@code msgStr} to set
		 * @return a reference to this Builder
		 */
		public Builder setMsgStr(String val) {
			msgStr = val;
			return this;
		}

		/**
		 * Sets the {@code cancelStr} and returns a reference to this Builder so that the methods can be chained together.
		 *
		 * @param val the {@code cancelStr} to set
		 * @return a reference to this Builder
		 */
		public Builder setCancelStr(String val) {
			cancelStr = val;
			return this;
		}

		/**
		 * Sets the {@code confirmStr} and returns a reference to this Builder so that the methods can be chained together.
		 *
		 * @param val the {@code confirmStr} to set
		 * @return a reference to this Builder
		 */
		public Builder setConfirmStr(String val) {
			confirmStr = val;
			return this;
		}

		/**
		 * Sets the {@code titleStrId} and returns a reference to this Builder so that the methods can be chained together.
		 *
		 * @param val the {@code titleStrId} to set
		 * @return a reference to this Builder
		 */
		public Builder setTitleStrId(@StringRes int val) {
			titleStrId = val;
			return this;
		}

		/**
		 * Sets the {@code msgStrId} and returns a reference to this Builder so that the methods can be chained together.
		 *
		 * @param val the {@code msgStrId} to set
		 * @return a reference to this Builder
		 */
		public Builder setMsgStrId(@StringRes int val) {
			msgStrId = val;
			return this;
		}

		/**
		 * Sets the {@code cancelStrId} and returns a reference to this Builder so that the methods can be chained together.
		 *
		 * @param val the {@code cancelStrId} to set
		 * @return a reference to this Builder
		 */
		public Builder setCancelStrId(@StringRes int val) {
			cancelStrId = val;
			return this;
		}

		/**
		 * Sets the {@code confirmStrId} and returns a reference to this Builder so that the methods can be chained together.
		 *
		 * @param val the {@code confirmStrId} to set
		 * @return a reference to this Builder
		 */
		public Builder setConfirmStrId(@StringRes int val) {
			confirmStrId = val;
			return this;
		}

		/**
		 * Sets the {@code isShowCancel} and returns a reference to this Builder so that the methods can be chained together.
		 *
		 * @param val the {@code isShowCancel} to set
		 * @return a reference to this Builder
		 */
		public Builder setIsShowCancel(boolean val) {
			isShowCancel = val;
			return this;
		}

		/**
		 * Sets the {@code onConfirmClickListener} and returns a reference to this Builder so that the methods can be chained together.
		 *
		 * @param val the {@code onConfirmClickListener} to set
		 * @return a reference to this Builder
		 */
		public Builder setOnConfirmClickListener(OnConfirmClickListener val) {
			onConfirmClickListener = val;
			return this;
		}

		/**
		 * Sets the {@code OnCancelClickListener} and returns a reference to this Builder so that the methods can be chained together.
		 *
		 * @param val the {@code OnCancelClickListener} to set
		 * @return a reference to this Builder
		 */
		public Builder setOnCancelClickListener(OnCancelClickListener val) {
			onCancelClickListener = val;
			return this;
		}

		/**
		 * Sets the {@code titleColor} and returns a reference to this Builder so that the methods can be chained together.
		 *
		 * @param val the {@code titleColor} to set
		 * @return a reference to this Builder
		 */
		public Builder setTitleColor(@ColorRes int val) {
			titleColor = val;
			return this;
		}

		/**
		 * Sets the {@code msgColor} and returns a reference to this Builder so that the methods can be chained together.
		 *
		 * @param val the {@code msgColor} to set
		 * @return a reference to this Builder
		 */
		public Builder setMsgColor(@ColorRes int val) {
			msgColor = val;
			return this;
		}

		/**
		 * Sets the {@code cancelColor} and returns a reference to this Builder so that the methods can be chained together.
		 *
		 * @param val the {@code cancelColor} to set
		 * @return a reference to this Builder
		 */
		public Builder setCancelColor(@ColorRes int val) {
			cancelColor = val;
			return this;
		}

		/**
		 * Sets the {@code confirmColor} and returns a reference to this Builder so that the methods can be chained together.
		 *
		 * @param val the {@code confirmColor} to set
		 * @return a reference to this Builder
		 */
		public Builder setConfirmColor(@ColorRes int val) {
			confirmColor = val;
			return this;
		}

		/**
		 * Returns a {@code BuilderDialog} built from the parameters previously set.
		 *
		 * @return a {@code BuilderDialog} built with parameters of this {@code BuilderDialog.Builder}
		 */
		public BuilderDialog build(Context context) {
			return new BuilderDialog(context, this);
		}
	}
}
