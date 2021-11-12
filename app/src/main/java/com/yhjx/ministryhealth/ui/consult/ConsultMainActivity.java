package com.yhjx.ministryhealth.ui.consult;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.library.basemodule.impl.ScrollBoundaryDeciderAdapter;
import com.yhjx.ministryhealth.R;
import com.yhjx.ministryhealth.base.BaseActivity;
import com.yhjx.ministryhealth.ui.consult.adapter.ChatConsultAdapter;
import com.scwang.smart.refresh.footer.ClassicsFooter;
import com.scwang.smart.refresh.layout.SmartRefreshLayout;
import com.scwang.smart.refresh.layout.api.RefreshLayout;
import com.scwang.smart.refresh.layout.listener.OnLoadMoreListener;

public class ConsultMainActivity extends BaseActivity {

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

        imgBack = findViewById(R.id.img_back);
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        tvTitle = findViewById(R.id.tv_title);
        refreshLayout = findViewById(R.id.refresh_layout);
        listMessage = findViewById(R.id.list_message);
        footer = findViewById(R.id.footer);
        llEditView = findViewById(R.id.ll_edit_view);
        editChat = findViewById(R.id.edit_chat);
        imgBtnEmoji = findViewById(R.id.img_btn_emoji);
        imgBtnSend = findViewById(R.id.img_btn_send);
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
    }

    private void loadMoreData() {
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void setListener() {

    }
}