package com.library.basemodule.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.library.basemodule.R;

public class ListDialog extends Dialog {

    public interface OnDialogClick{
        void onClickItem(int position);
    }

    private OnDialogClick onDialogClick;

    public void setOnDialogClick(OnDialogClick onDialogClick) {
        this.onDialogClick = onDialogClick;
    }

    private RecyclerView listView;
    private ListAdapter listAdapter;
    public ListDialog(@NonNull Context context) {
        super(context);
        initView();
    }



    private void initView() {
        setContentView(R.layout.layout_list_dialog);
        listView = findViewById(R.id.listView);
        listView.setLayoutManager(new LinearLayoutManager(getContext()));
        listAdapter=new ListAdapter();
        listAdapter.setOnItemClick(new ListAdapter.OnClick() {
            @Override
            public void onClickItem(int position) {
                    if (onDialogClick!=null){
                        onDialogClick.onClickItem(position);
                        dismiss();
                    }
            }
        });
        listView.setAdapter(listAdapter);
        setWindow(getWindow());
    }


    public void show(CharSequence[] items,int seletectPosition) {
        listAdapter.setData(items,seletectPosition);
        super.show();

    }

    private void setWindow(Window wd){


        if (wd==null){
            return;
        }
        WindowManager.LayoutParams params = wd.getAttributes();
        params.width = WindowManager.LayoutParams.MATCH_PARENT;
        params.height = WindowManager.LayoutParams.WRAP_CONTENT;
        wd.setAttributes(params);


    }

    public static class ListAdapter extends RecyclerView.Adapter<ListAdapter.itemViewHolder> {
        private  OnClick onClick;

        public void setOnItemClick(OnClick onClick) {
            this.onClick = onClick;
        }

        private interface OnClick{
            void onClickItem(int position);
        }
        private CharSequence[] items;
        private int seletectPositio = -1;

        public void setData(CharSequence[] items, int seletectPositio){
            this.items = items;
            this.seletectPositio = seletectPositio;
            notifyDataSetChanged();
        }
        @NonNull
        @Override
        public itemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new itemViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_dialog_list, parent, false));
        }

        @Override
        public void onBindViewHolder(@NonNull itemViewHolder holder, int position) {
            holder.tvTitle.setText(items[position]);
            if (position == seletectPositio) {
                holder.imgCheck.setVisibility(View.VISIBLE);
            }else {
                holder.imgCheck.setVisibility(View.GONE);
            }
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onClick!=null){
                        onClick.onClickItem(position);
                    }
                }
            });
        }

        @Override
        public int getItemCount() {
            return items.length;
        }

        class itemViewHolder extends RecyclerView.ViewHolder {
            private TextView tvTitle;
            private ImageView imgCheck;

            public itemViewHolder(@NonNull View itemView) {
                super(itemView);
                tvTitle = itemView.findViewById(R.id.tv_title);
                imgCheck = itemView.findViewById(R.id.img_check);
            }
        }
    }
}
