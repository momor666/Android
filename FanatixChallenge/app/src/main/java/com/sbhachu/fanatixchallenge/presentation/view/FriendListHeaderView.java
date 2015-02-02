package com.sbhachu.fanatixchallenge.presentation.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import com.sbhachu.fanatixchallenge.R;

import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

@EViewGroup(R.layout.view_friend_list_header)
public class FriendListHeaderView extends LinearLayout {

    public FriendListHeaderView(Context context) {
        super(context);
    }

    public FriendListHeaderView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
}
