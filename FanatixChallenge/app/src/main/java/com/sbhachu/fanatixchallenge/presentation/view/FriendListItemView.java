package com.sbhachu.fanatixchallenge.presentation.view;

import android.content.Context;
import android.net.Uri;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sbhachu.fanatixchallenge.R;
import com.sbhachu.fanatixchallenge.data.model.Friend;
import com.sbhachu.fanatixchallenge.presentation.view.transformation.RoundedImageTransformation;
import com.squareup.picasso.Picasso;

import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;
import org.apache.commons.lang3.text.WordUtils;

/**
 * Created by sbhachu on 30/01/15.
 */
@EViewGroup(R.layout.view_friend_list_item)
public class FriendListItemView extends LinearLayout {

    @ViewById(R.id.friend_item_checkbox)
    public CheckBox friendItemCheckbox;

    @ViewById(R.id.friend_image_view)
    public ImageView friendImageView;

    @ViewById(R.id.friend_item_name_text_view)
    public TextView friendItemNameTextView;

    @ViewById(R.id.friend_item_reason)
    public TextView friendReasonTextView;

    @ViewById(R.id.friend_status)
    public View friendStatus;

    public FriendListItemView(Context context) {
        super(context);
    }

    public void bind(Friend friend) {

        if (friend != null) {
            // reset views
            friendStatus.setVisibility(VISIBLE);
            friendReasonTextView.setVisibility(VISIBLE);

            friendItemNameTextView.setText(friend.getName());
            friendItemCheckbox.setChecked(friend.isSelected());

            Picasso.with(getContext())
                    .load(Uri.parse(friend.getImage()))
                    .transform(new RoundedImageTransformation(15, 0))
                    .fit()
                    .into(friendImageView);

            if (friend.isPrimary() != null && friend.hasChat() != null) {
                if (friend.isPrimary() && friend.hasChat()) {
                    friendStatus.setBackground(getResources().getDrawable(R.drawable.green_light));
                } else if (friend.isPrimary() && !friend.hasChat()) {
                    friendStatus.setBackground(getResources().getDrawable(R.drawable.grey_light));
                } else if (!friend.isPrimary()) {
                    friendStatus.setVisibility(GONE);
                }
            }


            if (friend.getTeam() != null) {
                friendReasonTextView.setText("Likes " + WordUtils.capitalize(friend.getTeam()));
            } else {
                friendReasonTextView.setVisibility(GONE);
            }

        }
    }
}
