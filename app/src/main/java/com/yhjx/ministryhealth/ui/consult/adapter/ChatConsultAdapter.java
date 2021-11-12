package com.yhjx.ministryhealth.ui.consult.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.yhjx.ministryhealth.R;

/**
 * @author
 * @time 2021/9/22
 * 说明：咨询聊天
 */
public class ChatConsultAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private Context context;
    public ChatConsultAdapter(Context context ) {
        this.context=context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
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
