package com.yhjx.ministryhealth.ui.adapter;

import androidx.annotation.NonNull;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.yhjx.ministryhealth.R;
import com.yhjx.ministryhealth.bean.MsgListBean;

public class MessageAdapter extends BaseQuickAdapter<MsgListBean, BaseViewHolder> {
    public MessageAdapter( ) {
        super(R.layout.item_notification_message);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder baseViewHolder, MsgListBean data) {
        baseViewHolder.setText(R.id.tv_msg_title,data.getMessageTitle());
        baseViewHolder.setText(R.id.tv_msg_time,data.getMessageDate());
        baseViewHolder.setText(R.id.tv_msg_content,data.getMessageContent());
    }
}
