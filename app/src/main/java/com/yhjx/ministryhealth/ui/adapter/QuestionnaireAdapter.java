package com.yhjx.ministryhealth.ui.adapter;

import android.graphics.Color;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.TypedValue;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.annotation.NonNull;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.library.basemodule.util.SizeUtils;
import com.yhjx.ministryhealth.R;
import com.yhjx.ministryhealth.bean.PaperDataListVosBean;

public class QuestionnaireAdapter extends BaseMultiItemQuickAdapter<PaperDataListVosBean, BaseViewHolder> {

    public QuestionnaireAdapter() {
        addItemType(0, R.layout.item_questionnaire_radiobutton);//单选
        addItemType(1, R.layout.item_questionnaire_checkbutton); //多选
        addItemType(2, R.layout.item_questionnaire_edittext); //填空

    }

    @Override
    protected void convert(@NonNull BaseViewHolder baseViewHolder, PaperDataListVosBean data) {
            switch (baseViewHolder.getItemViewType()){
                case 0:
                baseViewHolder.setText(R.id.tv_topic,data.getTitle());
                RadioGroup radioGroup0=baseViewHolder.getView(R.id.rg_choice);
                    radioGroup0.removeAllViews();
                    for (PaperDataListVosBean.PaperDataVosBean paperDataVo : data.getPaperDataVos()) {
                        RadioButton radioButton=new RadioButton(getContext());
                        radioButton.setTextColor(Color.parseColor("#ff333333"));
                        radioButton.setTextSize(TypedValue.COMPLEX_UNIT_SP, 13);
                        radioButton.setText(paperDataVo.getOptional());
                        radioButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                            @Override
                            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                                if (isChecked){
                                    paperDataVo.setIsSelected("true");
                                }else {
                                    paperDataVo.setIsSelected("false");
                                }

                            }
                        });
                        radioGroup0.addView(radioButton);

                    }
                    break;
                case 1:
                    baseViewHolder.setText(R.id.tv_topic,data.getTitle());
                    RadioGroup radioGroup1=baseViewHolder.getView(R.id.rg_choice);
                    radioGroup1.removeAllViews();
                    for (PaperDataListVosBean.PaperDataVosBean paperDataVo : data.getPaperDataVos()) {
                        CheckBox checkBox=new CheckBox(getContext());
                        checkBox.setText(paperDataVo.getOptional());
                        checkBox.setTextColor(Color.parseColor("#ff333333"));
                        checkBox.setTextSize(TypedValue.COMPLEX_UNIT_SP, 13);
                        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                            @Override
                            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                                if (isChecked){
                                    paperDataVo.setIsSelected("true");
                                }else {
                                    paperDataVo.setIsSelected("false");
                                }
                            }
                        });
                        radioGroup1.addView(checkBox);

                    }
                    break;
                case 2:
                    baseViewHolder.setText(R.id.tv_topic,data.getTitle());
                    EditText editText=baseViewHolder.getView(R.id.edit_optional);
                    editText.addTextChangedListener(new TextWatcher() {
                        @Override
                        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                        }

                        @Override
                        public void onTextChanged(CharSequence s, int start, int before, int count) {

                        }

                        @Override
                        public void afterTextChanged(Editable s) {
                            data.getPaperDataVos().get(0).setOptional(s.toString());
                        }
                    });
                    break;
            }
    }
}
