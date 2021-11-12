package com.yhjx.ministryhealth.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.yhjx.ministryhealth.R;

public class HomeFragment extends Fragment implements View.OnClickListener {

    private TextView tvMsgContent;
    private LinearLayout llCalendar;
    private LinearLayout llMsgHint;
    private LinearLayout llRecord;
    private LinearLayout llTelephone;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
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
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ll_calendar:
                startActivity(new Intent(getContext(), RemindActivity.class));
                break;
            case R.id.ll_msg_hint:
                startActivity(new Intent(getContext(),NotificationMessageActivity.class));
                break;
            case R.id.ll_record:
                startActivity(new Intent(getContext(),MedicineRecordActivity.class));
                break;
            case R.id.ll_telephone:
                break;
        }
    }
}