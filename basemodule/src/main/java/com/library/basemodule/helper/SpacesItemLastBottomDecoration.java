package com.library.basemodule.helper;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.library.basemodule.util.SizeUtils;
/**
 * @author
 * @time 2021/6/11
 * 说明：用于Recyclerview 间距 控制每个item底部间距及最后一个item的底部间距
 */
public class SpacesItemLastBottomDecoration extends RecyclerView.ItemDecoration {
    private int bottomSpacesDpValue; //底部间距
    private int lastBottomSpacesDpValue; //最后一个item底部间距

    public SpacesItemLastBottomDecoration(int bottomSpacesDpValue, int lastBottomSpacesDpValue) {
        this.bottomSpacesDpValue = SizeUtils.dp2px(bottomSpacesDpValue);
        this.lastBottomSpacesDpValue = SizeUtils.dp2px(lastBottomSpacesDpValue);
    }

    @Override
    public void onDraw(@NonNull Canvas c, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.onDraw(c, parent, state);
    }

    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        LinearLayoutManager linearLayoutManager= (LinearLayoutManager) parent.getLayoutManager();
        if (linearLayoutManager.getOrientation()==LinearLayoutManager.VERTICAL){
            if (parent.getChildAdapterPosition(view)==linearLayoutManager.getItemCount()-1){
                outRect.bottom= lastBottomSpacesDpValue;
            }else {
                outRect.bottom = bottomSpacesDpValue;
            }
        }
    }
}
