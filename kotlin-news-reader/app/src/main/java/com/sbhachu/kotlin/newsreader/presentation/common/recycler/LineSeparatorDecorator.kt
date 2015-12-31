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