package com.library.basemodule.ui;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.github.chrisbanes.photoview.PhotoView;
import com.gyf.immersionbar.ImmersionBar;
import com.library.basemodule.R;

public class PhotoViewActivity extends AppCompatActivity {

    private ImageView imgBack;
    private PhotoView photoView;
    public static void Initialization(Context context, String img){
        Intent intent=new Intent(context, PhotoViewActivity.class);
        intent.putExtra("url",img);
        context.startActivity(intent);
        ((Activity)context).overridePendingTransition(R.anim.fade_in,R.anim.fade_out);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ImmersionBar.with(this).init();
        setContentView(R.layout.activity_photo_view);
        initView();
    }

    private void initView() {
        imgBack = (ImageView) findViewById(R.id.img_back);
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        photoView = (PhotoView) findViewById(R.id.photoView);
        Glide.with(this).load(getIntent().getStringExtra("url")).into(photoView);
    }
}