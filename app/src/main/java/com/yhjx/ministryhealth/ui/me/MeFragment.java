package com.yhjx.ministryhealth.ui.me;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.yhjx.ministryhealth.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class MeFragment extends Fragment {

    private ImageView imgHead;
    private TextView tvName;
    private TextView tvPhone;
    private LinearLayout llResetPaw;
    private LinearLayout llUpdate;
    private TextView tvLogout;

    public MeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_me, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
    }

    private void initView(View view) {
        imgHead = view.findViewById(R.id.img_head);
        tvName = view.findViewById(R.id.tv_name);
        tvPhone = view.findViewById(R.id.tv_phone);
        llResetPaw = view.findViewById(R.id.ll_reset_paw);
        llUpdate = view.findViewById(R.id.ll_update);
        tvLogout = view.findViewById(R.id.tv_logout);
    }
}