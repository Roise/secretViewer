package com.jun.roise.pdfviewer.pdf.util;

import android.content.Context;
import android.graphics.Rect;
import android.support.annotation.DimenRes;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by Roise on 2016. 12. 1..
 */

public class ItemOffsetDecoration extends RecyclerView.ItemDecoration {
    private int mItemSideOffset;
    private int mItemTopOffset;

    public ItemOffsetDecoration(int sideOffset, int topOffset) {
        this.mItemSideOffset = sideOffset;
        this.mItemTopOffset = topOffset;
    }

    public ItemOffsetDecoration(@NonNull Context context, @DimenRes int sideOffsetId, @DimenRes int topOffsetId) {
        this(context.getResources().getDimensionPixelSize(sideOffsetId), context.getResources().getDimensionPixelSize(topOffsetId));
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent,
                               RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        //int left, int top, int right, int bottom
        outRect.set(mItemSideOffset, mItemTopOffset, mItemSideOffset, 0);

//        int count = parent.getChildCount();
//        int index = parent.getChildAdapterPosition(view) + 1;
//        if(index % 3 != 0) {
//            outRect.right = mItemSideOffset;
//        }
//        if(count - index >= 3) {
//            outRect.top = mItemTopOffset;
//        }
    }
}
