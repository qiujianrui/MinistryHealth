package com.yhjx.ministryhealth.ui.consult.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.fastjson.JSON;
import com.library.basemodule.util.SPUtils;
import com.library.basemodule.util.glide.GlideUtils;
import com.yhjx.ministryhealth.R;
import com.yhjx.ministryhealth.bean.ChatMsgBean;
import com.yhjx.ministryhealth.bean.LoginBean;
import com.yhjx.ministryhealth.constants.SpConstants;

import java.util.ArrayList;
import java.util.List;

/**
 * @author
 * @time 2021/9/22
 * 说明：咨询聊天
 */
public class ChatConsultAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private static final int LEFT=0X001;
    private static final int RIGHT=0X002;
    private Context context;
    private List<ChatMsgBean> mDatas;
    private RecyclerView mRecycleView;
    private  LoginBean loginBean;
    public ChatConsultAdapter(Context context ) {
        mDatas=new ArrayList<>();
        this.context=context;
         loginBean= JSON.parseObject(SPUtils.getInstance().getString(SpConstants.SP_KEY_USER_DATA), LoginBean.class);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        switch (viewType){
            case LEFT:
                return new LeftTextHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_left_text_consult,parent,false));
            case RIGHT:
                return new RightTextHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_right_text_consult,parent,false));

        }

        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ChatMsgBean data=mDatas.get(position);
        if (holder instanceof LeftTextHolder){
            if (data.getDoctorImg()!=null&&!data.getDoctorImg().isEmpty()) {
                GlideUtils.intoCircle(((LeftTextHolder) holder).tvIcon, data.getDoctorImg());
            }
            if (data.getDateSend()==null||data.getDateSend().isEmpty()){
                ((LeftTextHolder) holder).tvTime.setVisibility(View.GONE);
            }else {
                ((LeftTextHolder) holder).tvTime.setVisibility(View.VISIBLE);
                ((LeftTextHolder) holder).tvTime.setText(data.getDateSend());
            }
            ((LeftTextHolder) holder).tvMessage.setText(data.getDataMsg());
            ((LeftTextHolder) holder).tvName.setText(data.getDoctor());

        }else if (holder instanceof RightTextHolder){
            if (data.getPatientImg()!=null&&!data.getPatientImg().isEmpty()) {
                GlideUtils.intoCircle(((RightTextHolder) holder).tvIcon, data.getPatientImg());
            }else {
                GlideUtils.intoCircle(((RightTextHolder) holder).tvIcon, loginBean.getPersonImg());
            }

            if (data.getDateSend()==null||data.getDateSend().isEmpty()){
                ((RightTextHolder) holder).tvTime.setVisibility(View.GONE);
            }else {
                ((RightTextHolder) holder).tvTime.setVisibility(View.VISIBLE);
                ((RightTextHolder) holder).tvTime.setText(data.getDateSend());
            }
            ((RightTextHolder) holder).tvMessage.setText(data.getDataMsg());
        }
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        mRecycleView = recyclerView;
    }

    @Override
    public int getItemViewType(int position) {
        if (mDatas.get(position).getMsgType()==null){
            return LEFT;
        }else {
            switch (mDatas.get(position).getMsgType()) {
                case "0":
                    return LEFT;
                case "1":
                    return RIGHT;
            }
            return LEFT;
        }
    }

    public void setNewData(List<ChatMsgBean> list){
        this.mDatas.addAll(list);
        notifyDataSetChanged();
        scrollToEnd();
    }


    public void insertDataSource(List<ChatMsgBean> list) {
        this.mDatas.addAll(0, list);
        notifyItemRangeInserted(0, list.size());
        //mRecycleView.scrollBy(0, -200);
    }

    public void addDataSourceList(List<ChatMsgBean> list) {
        this.mDatas.addAll(list);
        notifyItemInserted(getItemCount() - 1);
        scrollToEnd();
    }

    public void addDataSource(ChatMsgBean data) {
        this.mDatas.add(data);
        notifyItemInserted(getItemCount() - 1);
        scrollToEnd();
    }

    public void scrollToEnd() {
        if (mRecycleView != null) {
            LinearLayoutManager layoutManager = (LinearLayoutManager) mRecycleView.getLayoutManager();
            layoutManager.scrollToPositionWithOffset(getItemCount() - 1, 0);
        }
    }

    /**---------------------------ViewHolder-----------------------------------------------*/
    static class LeftTextHolder extends RecyclerView.ViewHolder {
        private TextView tvTime;
        private ImageView tvIcon;
        private TextView tvMessage;
        private TextView tvName;

        public LeftTextHolder(@NonNull View itemView) {
            super(itemView);
            tvName=itemView.findViewById(R.id.tv_name);
            tvTime = itemView.findViewById(R.id.tv_time);
            tvIcon = itemView.findViewById(R.id.tv_icon);
            tvMessage = itemView.findViewById(R.id.tv_message);
        }
    }

    static class RightTextHolder extends RecyclerView.ViewHolder {
        private TextView tvTime;
        private ImageView tvIcon;
        private TextView tvMessage;

        public RightTextHolder(@NonNull View itemView) {
            super(itemView);
            tvTime = itemView.findViewById(R.id.tv_time);
            tvIcon = itemView.findViewById(R.id.tv_icon);
            tvMessage = itemView.findViewById(R.id.tv_message);
        }
    }
}
