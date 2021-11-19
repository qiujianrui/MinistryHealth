package com.yhjx.ministryhealth.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.yhjx.ministryhealth.R;
import com.yhjx.ministryhealth.base.BaseActivity;
import com.yhjx.ministryhealth.bean.MsgListBean;
import com.yhjx.ministryhealth.mvp.contract.MessageContract;
import com.yhjx.ministryhealth.mvp.presenter.MessagePresenter;
import com.yhjx.ministryhealth.ui.adapter.MessageAdapter;

import java.util.List;

public class NotificationMessageActivity extends BaseActivity implements View.OnClickListener, MessageContract.View {

    private ImageView imgBack;
    private RecyclerView listContent;
    private MessageAdapter messageAdapter;
    private MessagePresenter messagePresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_notification_message;
    }

    @Override
    protected void initView() {

        imgBack = findViewById(R.id.img_back);
        imgBack.setOnClickListener(this);
        listContent = findViewById(R.id.list_content);
        messageAdapter=new MessageAdapter();
        listContent.setLayoutManager(new LinearLayoutManager(this));
        listContent.setAdapter(messageAdapter);
    }

    @Override
    protected void initData() {
        messagePresenter=new MessagePresenter(this,this);
        messagePresenter.getMessageList();
    }

    @Override
    protected void setListener() {
        messageAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(@NonNull BaseQuickAdapter<?, ?> adapter, @NonNull View view, int position) {
                switch (messageAdapter.getData().get(position).getType()){
                    case "0":
                        startActivity(new Intent(mActivity, MessageDetailActivity.class));
                        break;
                    case "1":
                        break;
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.img_back:
                finish();
                break;
        }
    }

    @Override
    public void getMessageListSuccess(List<MsgListBean> data) {
        messageAdapter.setList(data);
    }
}