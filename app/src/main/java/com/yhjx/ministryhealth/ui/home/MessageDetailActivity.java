package com.yhjx.ministryhealth.ui.home;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.yhjx.ministryhealth.R;
import com.yhjx.ministryhealth.base.BaseActivity;
import com.yhjx.ministryhealth.bean.MsgDetailBean;
import com.yhjx.ministryhealth.bean.MsgListBean;
import com.yhjx.ministryhealth.mvp.contract.MessageContract;
import com.yhjx.ministryhealth.mvp.presenter.MessagePresenter;

import java.util.List;

public class MessageDetailActivity extends BaseActivity implements MessageContract.View, View.OnClickListener {

    private ImageView imgBack;
    private TextView tvMsgTitle;
    private TextView tvMsgTime;
    private TextView tvMessageReceiver;
    private TextView tvMsgContent;
    private TextView tvMessageSender;

    private MessagePresenter messagePresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_message_detail;
    }

    @Override
    protected void initView() {

        imgBack = findViewById(R.id.img_back);
        imgBack.setOnClickListener(this);
        tvMsgTitle = findViewById(R.id.tv_msg_title);
        tvMsgTime = findViewById(R.id.tv_msg_time);
        tvMessageReceiver = findViewById(R.id.tv_messageReceiver);
        tvMsgContent = findViewById(R.id.tv_msg_content);
        tvMessageSender = findViewById(R.id.tv_messageSender);
    }

    @Override
    protected void initData() {
        messagePresenter=new MessagePresenter(this,this);
        messagePresenter.getMessageDetail(getIntent().getStringExtra("id"));
    }

    @Override
    protected void setListener() {

    }

    @Override
    public void getMessageListSuccess(List<MsgListBean> data) {

    }

    @Override
    public void getMessageDetailSuccess(MsgDetailBean data) {
        tvMessageReceiver.setText(data.getMessageReceiver());
        tvMessageSender.setText(data.getMessageSender());
        tvMsgTitle.setText(data.getMessageTitle());
        tvMsgTime.setText(data.getMessageDate());
        tvMsgContent.setText(data.getMessageContent() );
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.img_back:
                finish();
                break;
        }
    }
}