package com.yhjx.ministryhealth.ui.home;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.fastjson.JSON;
import com.library.basemodule.util.ToastUtils;
import com.yhjx.ministryhealth.R;
import com.yhjx.ministryhealth.base.BaseActivity;
import com.yhjx.ministryhealth.bean.PaperDataListVosBean;
import com.yhjx.ministryhealth.bean.QuestionnaireBean;
import com.yhjx.ministryhealth.mvp.contract.PaperContract;
import com.yhjx.ministryhealth.mvp.presenter.PaperPresenter;
import com.yhjx.ministryhealth.ui.adapter.QuestionnaireAdapter;

import java.util.List;

public class QuestionnaireActivity extends BaseActivity implements PaperContract.View, View.OnClickListener {

    private ImageView imgBack;
    private TextView tvTitle;
    private RecyclerView listContent;
    private QuestionnaireAdapter questionnaireAdapter;
    private PaperPresenter paperPresenter;
    private String  msgId;
    private List<PaperDataListVosBean> paperDataListVos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_questionnaire;
    }

    @Override
    protected void initView() {
        msgId=getIntent().getStringExtra("id");
        imgBack = findViewById(R.id.img_back);
        imgBack.setOnClickListener(this);
        tvTitle = findViewById(R.id.tv_title);
        listContent = findViewById(R.id.list_content);
        listContent.setLayoutManager(new LinearLayoutManager(this));
        questionnaireAdapter=new QuestionnaireAdapter();
        listContent.setAdapter(questionnaireAdapter);
    }

    @Override
    protected void initData() {
        paperPresenter=new PaperPresenter(this,this);
        paperPresenter.getPaperDetail(getIntent().getStringExtra("id"));
    }

    @Override
    protected void setListener() {

    }

    @Override
    public void getPaperDetailSuccess(QuestionnaireBean data) {
        View headView = getLayoutInflater().inflate(R.layout.item_questionnaire_title, listContent, false);
        TextView title=headView.findViewById(R.id.tv_title);
        title.setText(data.getPaperData());
        questionnaireAdapter.addHeaderView(headView);
        questionnaireAdapter.setList(data.getPaperDataListVos());
        View footerView=getLayoutInflater().inflate(R.layout.footer_questionnaire_submit,listContent,false);
        TextView tvSubmit=footerView.findViewById(R.id.tv_submit);
        tvSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                paperPresenter.addPaper(msgId, JSON.toJSONString(questionnaireAdapter.getData()));
            }
        });
        questionnaireAdapter.addFooterView(footerView);
    }

    @Override
    public void addPaperSuccess() {
        ToastUtils.showShort("提交成功");
        finish();
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