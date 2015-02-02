package com.sbhachu.fanatixchallenge.presentation.fragment;


import android.app.Fragment;
import android.widget.TextView;
import android.widget.Toast;

import com.sbhachu.fanatixchallenge.R;
import com.sbhachu.fanatixchallenge.data.model.Friend;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.FragmentArg;
import org.androidannotations.annotations.OptionsItem;
import org.androidannotations.annotations.OptionsMenu;
import org.androidannotations.annotations.ViewById;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

@EFragment(R.layout.fragment_huddle_invitation)
@OptionsMenu(R.menu.menu_invitation)
public class HuddleInvitationFragment extends Fragment {

    @FragmentArg
    public ArrayList<Friend> friendList;

    @ViewById(R.id.invite_list_text_view)
    public TextView inviteHeaderTextView;

    public HuddleInvitationFragment() {
        // Required empty public constructor
    }

    @AfterViews
    public void afterViews() {
        setHasOptionsMenu(true);

        inviteHeaderTextView.setText(getString(
                R.string.pre_invite_list) + " " + getNonPrimaryFriends() + " " + getString(
                R.string.post_invite_list));
    }

    private String getNonPrimaryFriends() {
        List<String> nonPrimary = new ArrayList<>();
        if (friendList != null) {
            for (Friend friend : friendList) {
                if (!friend.isPrimary())
                    nonPrimary.add(friend.getName());
            }
        }
        return StringUtils.join(nonPrimary, ", ");
    }

    @OptionsItem(R.id.action_invite)
    public void sendInvite() {
        Toast.makeText(getActivity().getApplicationContext(), getString(R.string.not_implemented),
                       Toast.LENGTH_LONG).show();
    }
}
