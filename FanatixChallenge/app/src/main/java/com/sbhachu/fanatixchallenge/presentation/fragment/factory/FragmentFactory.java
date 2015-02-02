package com.sbhachu.fanatixchallenge.presentation.fragment.factory;

import com.sbhachu.fanatixchallenge.data.model.Friend;
import com.sbhachu.fanatixchallenge.presentation.fragment.FriendListFragment;
import com.sbhachu.fanatixchallenge.presentation.fragment.FriendListFragment_;
import com.sbhachu.fanatixchallenge.presentation.fragment.HuddleInvitationFragment;
import com.sbhachu.fanatixchallenge.presentation.fragment.HuddleInvitationFragment_;
import com.sbhachu.fanatixchallenge.presentation.fragment.HuddleFragment;
import com.sbhachu.fanatixchallenge.presentation.fragment.HuddleFragment_;
import com.sbhachu.fanatixchallenge.presentation.fragment.PlaceholderFragment;
import com.sbhachu.fanatixchallenge.presentation.fragment.PlaceholderFragment_;

import java.util.ArrayList;

/**
 * FragmentFactory.class
 * Used to get instances of Fragments, is overkill here, but with larger applications can be useful
 */
public class FragmentFactory {

    public static PlaceholderFragment buildPlaceholderFragment() {
        return PlaceholderFragment_.builder().build();
    }

    public static FriendListFragment buildFriendListFragment(int itemId) {
        return FriendListFragment_.builder().itemId(itemId).build();
    }

    public static HuddleFragment buildHuddleParticipantListFragment() {
        return HuddleFragment_.builder().build();
    }

    public static HuddleInvitationFragment buildHuddleInvitationFragment(ArrayList<Friend> friends) {
        return HuddleInvitationFragment_.builder().friendList(friends).build();
    }
}
