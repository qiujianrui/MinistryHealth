package com.yhjx.ministryhealth.ui.dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.azhon.appupdate.config.UpdateConfiguration;
import com.azhon.appupdate.dialog.NumberProgressBar;
import com.azhon.appupdate.listener.OnDownloadListener;
import com.azhon.appupdate.manager.DownloadManager;
import com.azhon.appupdate.utils.ApkUtil;
import com.azhon.appupdate.utils.Constant;
import com.yhjx.ministryhealth.R;
import com.yhjx.ministryhealth.bean.UpdateAppBean;
import com.yhjx.ministryhealth.manager.AppManager;


import java.io.File;

public class UpdateAppDialog extends Dialog implements OnDownloadListener {
    private Context context;
    private DownloadManager manager;
    private TextView tvTitle;
    private TextView tvContent;
    private NumberProgressBar npBar;
    private TextView tvBtnUpdate;
    private TextView tvBtnClose;
    private String url = "https://imtt.dd.qq.com/16891/apk/FA48766BA12A41A1D619CB4B152889C6.apk?fsname=com.estrongs.android.pop_4.2.3.3_10089.apk&csr=1bbd";
    private UpdateConfiguration configuration;
    private File apk;
    private boolean isExit=false;
    private UpdateAppBean data;
    public UpdateAppDialog(@NonNull Context context, UpdateAppBean data) {
        super(context, R.style.UpdateDialog);
        this.context = context;
        this.data=data;
        init(context);
    }

    private void init(Context context) {
         configuration = new UpdateConfiguration()
                .setJumpInstallPage(true)
                .setOnDownloadListener(this);
        manager = DownloadManager.getInstance(context);
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_update_app, null);
        setContentView(view);
        initView(view);
    }

    private void initView(View view) {
        setCancelable(false);
        tvTitle = view.findViewById(R.id.tv_title);
        tvContent = view.findViewById(R.id.tv_content);
        npBar = view.findViewById(R.id.np_bar);
        tvBtnUpdate = view.findViewById(R.id.tv_btn_update);
        tvBtnClose = view.findViewById(R.id.tv_btn_close);
        tvBtnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (apk!=null){
                    installApk();
                }else {
                    manager.setApkName("MirroregoCounselor.apk")
                            .setApkUrl(url)
                            .setSmallIcon(R.mipmap.ic_launcher)
                            .setConfiguration(configuration)
                            .download();
                }
            }
        });
        tvBtnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        setOnKeyListener(new OnKeyListener() {
            @Override
            public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_BACK) {
                    if (isExit) {
                        AppManager.getAppManager().AppExit();
                    }
                    dismiss();
                }
                return false;
            }
        });
        if (isExit){
            tvBtnClose.setVisibility(View.GONE);
        }
        initData();
    }

    private void initData() {
        url=data.getDownloadLink();
        tvTitle.setText(data.getUpdateTitle());
        tvContent.setText(data.getUpdateContent());
        if (data.getIsForcedUpdating().equals("1")){
            isExit=true;
            setCancelable(false);
            tvBtnClose.setVisibility(View.GONE);
        }else {
            isExit=false;
            setCancelable(true);
            tvBtnClose.setVisibility(View.VISIBLE);
        }
    }

    private void installApk() {
        ApkUtil.installApk(context, Constant.AUTHORITIES, apk);
    }

    @Override
    public void start() {
        npBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void downloading(int max, int progress) {
        int curr = (int) (progress / (double) max * 100.0);
        npBar.setMax(100);
        npBar.setProgress(curr);
    }

    @Override
    public void done(File apk) {
        this.apk = apk;
    }

    @Override
    public void error(Exception e) {

    }
}
