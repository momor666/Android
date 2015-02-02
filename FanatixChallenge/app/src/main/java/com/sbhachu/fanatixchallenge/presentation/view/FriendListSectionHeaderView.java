package com.sbhachu.fanatixchallenge.presentation.view;

import android.content.Context;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sbhachu.fanatixchallenge.R;

import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;
import org.apache.commons.lang3.text.WordUtils;

/**
 * Created by sbhachu on 30/01/15.
 */
@EViewGroup(R.layout.view_friend_list_section_header)
public class FriendListSectionHeaderView extends LinearLayout {

    @ViewById(R.id.friend_category_header)
    public TextView friendCategoryHeader;

    public FriendListSectionHeaderView(Context context) {
        super(context);
    }

    public void bind(String header) {
        friendCategoryHeader.setText(WordUtils.capitalize(header));
    }
}
