package com.yhjx.ministryhealth.ui.home;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.yhjx.ministryhealth.R;
import com.yhjx.ministryhealth.base.BaseFragment;
import com.yhjx.ministryhealth.bean.IndexBean;
import com.yhjx.ministryhealth.mvp.contract.IndexContract;
import com.yhjx.ministryhealth.mvp.presenter.IndexPresenter;
import com.yhjx.ministryhealth.view.WeekDataLayout;

public class HomeFragment extends BaseFragment implements View.OnClickListener, IndexContract.View {

    private TextView tvMsgContent;
    private LinearLayout llCalendar;
    private LinearLayout llMsgHint;
    private LinearLayout llRecord;
    private LinearLayout llTelephone;
    private TextView tvMessageReceiver;
    private TextView tvMessageSender;
    private TextView tvNewMsgNum;
    private TextView tvMsgTitle;
    private TextView tvMsgTime;
    private TextView tvPhone;
    private IndexBean homeData;
    private FrameLayout flEmptyMsg;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
        initData();
    }

    private void initData() {
        IndexPresenter indexPresenter = new IndexPresenter(this, getActivity());
        indexPresenter.index();
    }

    private void initView(View view) {
        tvMsgContent = view.findViewById(R.id.tv_msg_content);
        tvMsgContent.setMovementMethod(ScrollingMovementMethod.getInstance());
        llCalendar = view.findViewById(R.id.ll_calendar);
        llCalendar.setOnClickListener(this);
        llMsgHint = view.findViewById(R.id.ll_msg_hint);
        llMsgHint.setOnClickListener(this);
        llRecord = view.findViewById(R.id.ll_record);
        llRecord.setOnClickListener(this);
        llTelephone = view.findViewById(R.id.ll_telephone);
        llTelephone.setOnClickListener(this);
        tvMessageReceiver = view.findViewById(R.id.tv_messageReceiver);
        tvMessageSender = view.findViewById(R.id.tv_messageSender);
        tvNewMsgNum = view.findViewById(R.id.tv_new_msg_num);
        tvMsgTitle = view.findViewById(R.id.tv_msg_title);
        tvMsgTime = view.findViewById(R.id.tv_msg_time);
        tvPhone = view.findViewById(R.id.tv_phone);
        flEmptyMsg = view.findViewById(R.id.fl_empty_msg);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_calendar:
                startActivity(new Intent(getContext(), RemindActivity.class));
                break;
            case R.id.ll_msg_hint:
                startActivity(new Intent(getContext(), NotificationMessageActivity.class));
                break;
            case R.id.ll_record:
                startActivity(new Intent(getContext(), MedicineRecordActivity.class));
                break;
            case R.id.ll_telephone:
                if (homeData.getHotTel() != null) {
                    Intent intent = new Intent(Intent.ACTION_DIAL);
                    Uri data = Uri.parse("tel:" + homeData.getHotTel());
                    intent.setData(data);
                    startActivity(intent);
                }
                break;
        }
    }

    @Override
    public void indexSuccess(IndexBean data) {
        homeData = data;
        llCalendar.removeAllViews();
        for (int i = 0; i < data.getIndexDateListVos().size(); i++) {
            WeekDataLayout weekDataLayout = new WeekDataLayout(getActivity());
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            layoutParams.weight = 1;
            weekDataLayout.setData(data.getIndexDateListVos().get(i).getEnglishWeekDate(),
                    data.getIndexDateListVos().get(i).getChineseDate(),
                    data.getIndexDateListVos().get(i).getIsTakeRemind(),
                    data.getIndexDateListVos().get(i).getIsVisitRemind());
            weekDataLayout.setLayoutParams(layoutParams);
            llCalendar.addView(weekDataLayout);
        }
        if (data.getIndexMsgVo() != null) {
            if (data.getIndexMsgVo().getMessageContent().isEmpty()){
                flEmptyMsg.setVisibility(View.VISIBLE);
            }else {
                flEmptyMsg.setVisibility(View.GONE);
                tvMessageReceiver.setText(data.getIndexMsgVo().getMessageReceiver());
                tvMessageSender.setText(data.getIndexMsgVo().getMessageSender());
                tvMsgContent.setText(data.getIndexMsgVo().getMessageContent());
            }

            llMsgHint.setVisibility(View.VISIBLE);
            tvNewMsgNum.setText(data.getIndexMsgVo().getMessageCount());
            tvMsgTitle.setText(data.getIndexMsgVo().getMessageTitle());
            tvMsgTime.setText(data.getIndexMsgVo().getMessageDate());

        }
        tvPhone.setText(data.getHotTel());
    }
}