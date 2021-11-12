package com.library.basemodule.widget;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.ColorInt;
import androidx.annotation.ColorRes;
import androidx.annotation.DrawableRes;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.library.basemodule.R;
import com.library.basemodule.util.ObjectUtils;
import com.library.basemodule.util.StringUtils;


/**
 * author  :  LiangXingFu
 * time    :  2017/8/9
 * 统一的标题栏
 * 注意传递颜色的时候统一传入 颜色不要传入资源文件
 * 点击事件通过内部接口回调
 * 其中默认显示的只有返回按钮  跟中间的文字 其他的需要动态设置
 */
public class TitleBarLayout extends ConstraintLayout implements View.OnClickListener {
    //返回箭头
    private ImageView mImvBack;
    // 中间的文字
    private TextView mTvTitle;

    //整个layout
    private ConstraintLayout mClTitle;


    private int mLeftImage;

    private String mTitleStr;
    private boolean mIsFinish;
    private Context mContext;
    private int bgColor;

    private boolean isTitleBold;
    private boolean isShowView;
    private FrameLayout mFlFinish;
    private View viewLine;


    public TitleBarLayout(Context context) {
        this(context, null);
    }

    public TitleBarLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        init(context, attrs);
    }


    private void init(Context context, AttributeSet attrs) {
        TypedArray attributes = context.obtainStyledAttributes(attrs, R.styleable.TitleBarLayout);
        mTitleStr = attributes.getString(R.styleable.TitleBarLayout_title_str);
        mLeftImage = attributes.getResourceId(R.styleable.TitleBarLayout_left_image, 0);
        mIsFinish = attributes.getBoolean(R.styleable.TitleBarLayout_is_finish, true);
        isTitleBold = attributes.getBoolean(R.styleable.TitleBarLayout_is_title_bold, false);
        isShowView= attributes.getBoolean(R.styleable.TitleBarLayout_is_show_view, true);
        bgColor=attributes.getColor(R.styleable.TitleBarLayout_bg_color, Color.WHITE);
        LayoutInflater.from(context).inflate(R.layout.title_bar_layout_base, this);
        mImvBack = findViewById(R.id.imv_back);
        viewLine = findViewById(R.id.view_line);
        mTvTitle = findViewById(R.id.tv_title_bar);
        mClTitle = findViewById(R.id.cl_title);

        mFlFinish = findViewById(R.id.fl_back_bar);
        mFlFinish.setOnClickListener(this);


        setImgBackResId(mLeftImage);
        setTitle(mTitleStr);

        attributes.recycle();
        if (isTitleBold) {
            mTvTitle.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
//            mTvTitle.getPaint().setFakeBoldText(true);
        }
        if (isShowView){
            viewLine.setVisibility(VISIBLE);
        }else {
            viewLine.setVisibility(GONE);
        }

        setTitleBackgroundColor(bgColor);
    }


    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.fl_back_bar) {
            if (mOnImageClickListener != null) {
                mOnImageClickListener.onImageClick(view);
            }else {
                if (mIsFinish) {
                    if (mContext instanceof Activity) {
                        Activity activity = (Activity) mContext;
                        hintKbTwo(activity);
                        activity.onBackPressed();
                    }
                    return;
                }
            }

        }

    }

    // -------------------------- 对外提供一些方法使用 ------------------------------------

    /**
     * @param title 设置title
     * @return 返回本类对象
     */
    public TitleBarLayout setTitle(CharSequence title) {
        if (!StringUtils.isEmpty(title)) {
            mTvTitle.setText(title);
        }
        return this;
    }

    /**
     * @param resId 设置title
     * @return 返回本类对象
     */
    public TitleBarLayout setTitle(int resId) {
        if (resId != 0) {
            mTvTitle.setText(resId);
        }
        return this;
    }


    /**
     * @param visibility 设置返回箭头是否显示
     * @return 返回本类对象
     */
    public TitleBarLayout setImgBackVisibility(int visibility) {
        switch (visibility) {
            case View.GONE:
                mImvBack.setVisibility(GONE);
                break;
            case VISIBLE:
                mImvBack.setVisibility(VISIBLE);
                break;
            default:
                mImvBack.setVisibility(VISIBLE);
                break;
        }
        return this;
    }

    /**
     * @param resId 设置左边的资源文件
     * @return 返回本类对象
     */
    public TitleBarLayout setImgBackResId(@DrawableRes int resId) {
        if (resId != 0) {
            mImvBack.setImageResource(resId);
        }
//        else {
//            mFlFinish.setVisibility(GONE);
//            mImvBack.setVisibility(GONE);
//        }
        return this;
    }


    /**
     * @param colorId 背景颜色
     * @return 返回本类对象
     */
    public TitleBarLayout setTitleBackgroundColor(@ColorInt int colorId) {
        mClTitle.setBackgroundColor(colorId);
        return this;
    }


    /**
     * @param colorId title的颜色
     * @return 返回本类对象
     */
    public TitleBarLayout setTvTitleColor(@ColorRes int colorId) {
        mTvTitle.setTextColor(getResources().getColor(colorId));
        return this;
    }


    //------------------------- 对外提供点击监听回调 ---------------------------

    // 左边的图片点击监听接口
    private OnImageClickListener mOnImageClickListener;


    public TitleBarLayout setOnImageClickListener(OnImageClickListener onImageClickListener) {
        mOnImageClickListener = onImageClickListener;
        return this;
    }

    private void initView() {
        viewLine = findViewById(R.id.view_line);
    }


    public interface OnImageClickListener {
        void onImageClick(View view);
    }

    public interface OnLeftClickListener {
        void onLeftClick(View view);
    }

    public interface OnCenterClickListener {
        void onCenterClick(View view, ImageView imageView);
    }

    public interface OnRightClickListener {
        void onRightClick(View view);
    }

    public interface OnRightImgClickListener {
        void onRightImgClick(View view);
    }

    /**
     * 此方法只是关闭软键盘 可以在finish之前调用一下
     *
     * @param activity activity
     */
    public void hintKbTwo(Activity activity) {
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
        if (ObjectUtils.isNotEmpty(imm) && imm.isActive() && activity.getCurrentFocus() != null) {
            if (activity.getCurrentFocus().getWindowToken() != null) {
                imm.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
            }
        }
    }
}
