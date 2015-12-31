package com.sbhachu.kotlin.newsreader.presentation.common.recycler

import android.content.Context
import android.graphics.Canvas
import android.graphics.drawable.Drawable
import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.view.View
import com.sbhachu.kotlin.newsreader.R

class LineSeparatorDecorator : RecyclerView.ItemDecoration {

    private var divider : Drawable? = null

    constructor(context : Context) {
        divider = ContextCompat.getDrawable(context, R.drawable.line_divider);
    }

    override fun onDrawOver(c: Canvas?, parent: RecyclerView?, state: RecyclerView.State?) {
        super.onDrawOver(c, parent, state)

        var left : Int? = parent?.paddingLeft
        var right : Int? = parent?.paddingRight

        var childCount : Int = parent?.childCount as Int

        var i : Int = 0

        while (i < childCount) {
            var child : View = parent?.getChildAt(i) as View;

            var params : RecyclerView.LayoutParams = child.layoutParams as RecyclerView.LayoutParams;

            var top : Int = child.bottom + params.bottomMargin;
            var bottom : Int = top + (divider?.intrinsicHeight as Int);

            divider?.setBounds((left as Int), top, (right as Int), bottom);
            divider?.draw(c);

            i++
        }
    }
}


/**
 * public class SimpleDividerItemDecoration extends RecyclerView.ItemDecoration {
private Drawable mDivider;

public SimpleDividerItemDecoration(Context context) {
mDivider = context.getResources().getDrawable(R.drawable.line_divider);
}

@Override
public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
int left = parent.getPaddingLeft();
int right = parent.getWidth() - parent.getPaddingRight();

int childCount = parent.getChildCount();
for (int i = 0; i < childCount; i++) {
View child = parent.getChildAt(i);

RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();

int top = child.getBottom() + params.bottomMargin;
int bottom = top + mDivider.getIntrinsicHeight();

mDivider.setBounds(left, top, right, bottom);
mDivider.draw(c);
}
}
}
 **/