package com.yhjx.ministryhealth.ui.consult;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.library.basemodule.impl.ScrollBoundaryDeciderAdapter;
import com.library.basemodule.listener.SoftKeyBoardListener;
import com.library.basemodule.util.SPUtils;
import com.yhjx.ministryhealth.R;
import com.yhjx.ministryhealth.base.BaseActivity;
import com.yhjx.ministryhealth.bean.ChatMsgBean;
import com.yhjx.ministryhealth.constants.SpConstants;
import com.yhjx.ministryhealth.mvp.contract.ChatContract;
import com.yhjx.ministryhealth.mvp.presenter.ChatPresenter;
import com.yhjx.ministryhealth.ui.consult.adapter.ChatConsultAdapter;
import com.scwang.smart.refresh.footer.ClassicsFooter;
import com.scwang.smart.refresh.layout.SmartRefreshLayout;
import com.scwang.smart.refresh.layout.api.RefreshLayout;
import com.scwang.smart.refresh.layout.listener.OnLoadMoreListener;

import java.util.List;

public class  ConsultMainActivity extends BaseActivity implements ChatContract.View, View.OnClickListener {

    private ImageView imgBack;
    private TextView tvTitle;
    private SmartRefreshLayout refreshLayout;
    private RecyclerView listMessage;
    private ClassicsFooter footer;
    private LinearLayout llEditView;
    private EditText editChat;
    private ImageView imgBtnEmoji;
    private ImageView imgBtnSend;

    private ChatConsultAdapter chatConsultAdapter;

    private ChatPresenter chatPresenter;

    private int PageIndex = 1;
    private String patientId;

    private Handler handler = new Handler();

    private FragmentManager mFragmentManager;
    private FaceFragment mFaceFragment;
    protected View mInputMoreView;
    private boolean isKeyBoardShow;//键盘隐藏显示标识
    private int moreShowState;//控制键盘隐藏后显示 显示更多功能弹窗标识 1.表情

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_consult_main;
    }

    @Override
    protected void initView() {
        patientId= SPUtils.getInstance().getString(SpConstants.SP_KEY_PATIENT_ID);
        imgBack = findViewById(R.id.img_back);
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mInputMoreView = findViewById(R.id.more_groups);
        tvTitle = findViewById(R.id.tv_title);
        refreshLayout = findViewById(R.id.refresh_layout);
        listMessage = findViewById(R.id.list_message);
        footer = findViewById(R.id.footer);
        llEditView = findViewById(R.id.ll_edit_view);
        editChat = findViewById(R.id.edit_chat);
        imgBtnEmoji = findViewById(R.id.img_btn_emoji);
        imgBtnEmoji.setOnClickListener(this);
        imgBtnSend = findViewById(R.id.img_btn_send);
        imgBtnSend.setOnClickListener(this);
        View arrow = footer.findViewById(ClassicsFooter.ID_IMAGE_ARROW);
        arrow.setScaleY(-1);//必须设置
        refreshLayout = findViewById(R.id.refresh_layout);
        refreshLayout.setEnableRefresh(false);//必须关闭
        refreshLayout.setEnableAutoLoadMore(true);//必须关闭
        refreshLayout.setEnableNestedScroll(false);//必须关闭
        refreshLayout.setEnableScrollContentWhenLoaded(true);//必须关闭
        refreshLayout.getLayout().setScaleY(-1);//必须设置
        refreshLayout.setScrollBoundaryDecider(new ScrollBoundaryDeciderAdapter() {
            @Override
            public boolean canLoadMore(View content) {
                return super.canRefresh(content);//必须替换
            }
        });
        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                loadMoreData();
            }
        });
        listMessage.setLayoutManager(new LinearLayoutManager(this));
        chatConsultAdapter = new ChatConsultAdapter(this);
        listMessage.setAdapter(chatConsultAdapter);
        listMessage.setScaleY(-1);

        if (mFragmentManager == null) {
            mFragmentManager = mActivity.getSupportFragmentManager();
        }
        if (mFaceFragment == null) {
            mFaceFragment = new FaceFragment();
        }
    }

    private void loadMoreData() {
        PageIndex++;
        chatPresenter.getHistoryMsg(PageIndex,patientId);
    }

    @Override
    protected void initData() {
        PageIndex=1;
        if (chatPresenter==null) {
            chatPresenter = new ChatPresenter(this, this);
        }
        chatPresenter.getHistoryMsg(PageIndex,patientId);
    }

    @Override
    protected void setListener() {
        softKeyboardListener();
        imgBtnSend.setSelected(false);
        imgBtnSend.setClickable(false);
        editChat.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (editChat.getText().toString().length() > 0) {
                    imgBtnSend.setSelected(true);
                    imgBtnSend.setClickable(true);
                } else {
                    imgBtnSend.setSelected(false);
                    imgBtnSend.setClickable(false);
                }
            }
        });
        listMessage.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction()==MotionEvent.ACTION_DOWN){
                    hideSoftInput();
                    mInputMoreView.setVisibility(View.GONE);
                    moreShowState=0;
                }
                return false;
            }
        });
    }

    private void getRecordNet() {
        handler.post(task);
    }

    private Runnable task = new Runnable() {
        public void run() {
            // TODOAuto-generated method stub
            handler.postDelayed(this, 5 * 1000);//设置延迟时间，此处是5秒
            //需要执行的代码
            chatPresenter.getEndMsg();
        }
    };

    @Override
    public void getHistoryMsgSuccess(List<ChatMsgBean> data) {
        if (PageIndex == 1) {
            chatConsultAdapter.setNewData(data);
            getRecordNet();
        } else {
            chatConsultAdapter.insertDataSource(data);
        }
        if (data.size() == 0) {
            footer.setNoMoreData(true);
            refreshLayout.setEnableLoadMore(false);
        }
        refreshLayout.finishLoadMore();
    }

    @Override
    public void getEndMsgSuccess(List<ChatMsgBean> data) {
        if (data != null && data.size() != 0) {
            chatConsultAdapter.addDataSourceList(data);
        }

    }

    @Override
    public void sendMsgSuccess(ChatMsgBean data) {
        if (data != null) {
            chatConsultAdapter.addDataSource(data);

        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.removeCallbacks(task);
    }

    @Override
    protected void onPause() {
        super.onPause();
        handler.removeCallbacks(task);
    }

    private void showFaceViewGroup() {

        if (mFragmentManager == null) {
            mFragmentManager = mActivity.getSupportFragmentManager();
        }
        if (mFaceFragment == null) {
            mFaceFragment = new FaceFragment();
        }
        editChat.requestFocus();
        mFaceFragment.setListener(new FaceFragment.OnEmojiClickListener() {
            @Override
            public void onEmojiDelete() {
                int keyCode = KeyEvent.KEYCODE_DEL;
                KeyEvent keyEventDown = new KeyEvent(KeyEvent.ACTION_DOWN, keyCode);
                KeyEvent keyEventUp = new KeyEvent(KeyEvent.ACTION_UP, keyCode);
                editChat.onKeyDown(keyCode, keyEventDown);
                editChat.onKeyUp(keyCode, keyEventUp);
            }

            @Override
            public void onEmojiClick(String emoji) {
                int index = editChat.getSelectionStart();
                Editable editable = editChat.getText();
                editable.insert(index, emoji);
                int selection = editChat.getSelectionStart();
                editChat.setText(editable.toString());
                editChat.setSelection(selection);

            }


        });
        mInputMoreView.setVisibility(View.VISIBLE);
        mFragmentManager.beginTransaction().replace(R.id.more_groups, mFaceFragment).commitNow();
        chatConsultAdapter.scrollToEnd();
    }

    /**
     * 软键盘显示与隐藏的监听
     */
    private void softKeyboardListener() {
        SoftKeyBoardListener.setListener(this, new SoftKeyBoardListener.OnSoftKeyBoardChangeListener() {
            @Override
            public void keyBoardShow(int height) {
                isKeyBoardShow=true;
                mInputMoreView.setVisibility(View.GONE);
                chatConsultAdapter.scrollToEnd();
            }

            @Override
            public void keyBoardHide(int height) {
                isKeyBoardShow=false;
                if (moreShowState==1){
                    //显示表情窗口
                    showFaceViewGroup();
                }
                moreShowState=0;
            }
        });
    }

    /**
     * 隐藏键盘
     */
    public void hideSoftInput() {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(editChat.getWindowToken(), 0);
        editChat.clearFocus();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.img_btn_emoji:
                if (isKeyBoardShow){
                    hideSoftInput();
                    moreShowState=1;
                    //控制在隐藏键盘后弹出more窗口
                }else {
                    hideSoftInput();
                    showFaceViewGroup();
                }
                break;
            case R.id.img_btn_send:
                chatPresenter.sendMsg(editChat.getText().toString());
                editChat.getText().clear();
                break;

        }
    }
}