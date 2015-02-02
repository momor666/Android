package com.sbhachu.fanatix.test.presentation.fragment;

import android.widget.ListView;
import android.widget.TextView;

import com.sbhachu.fanatixchallenge.R;
import com.sbhachu.fanatixchallenge.presentation.adapter.FriendListItemAdapter;
import com.sbhachu.fanatixchallenge.presentation.adapter.FriendListItemAdapter_;
import com.sbhachu.fanatixchallenge.presentation.fragment.FriendListFragment;
import com.sbhachu.fanatixchallenge.presentation.fragment.FriendListFragment_;
import com.sbhachu.fanatixchallenge.presentation.view.FriendListHeaderView;
import com.sbhachu.fanatixchallenge.presentation.view.FriendListHeaderView_;

import org.fest.assertions.api.ANDROID;
import org.fest.assertions.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.util.FragmentTestUtil;

@RunWith(RobolectricTestRunner.class)
@Config(emulateSdk = 18)
public class FriendListFragmentTest {

    private FriendListFragment friendListFragment;

    @Before
    public void setUp() throws Exception {
        friendListFragment = FriendListFragment_.builder().build();
        FragmentTestUtil.startFragment(friendListFragment);
    }

    @Test
    public void testPreconditions() throws Exception {
        ANDROID.assertThat(friendListFragment).isNotNull();
    }

    @Test
    public void shouldDisplayHeaderTitle() throws Exception {
        // given
        final FriendListHeaderView headerView = FriendListHeaderView_.build(Robolectric.application.getApplicationContext());
        final TextView title = (TextView) headerView.findViewById(R.id.header_title);

        // then
        ANDROID.assertThat(title)
                .isNotNull()
                .isVisible()
                .hasText(Robolectric.application.getString(R.string.create_huddle));
    }

    @Test
    public void shouldDisplayHeaderTopic() throws Exception {
        // given
        final FriendListHeaderView headerView = FriendListHeaderView_.build(Robolectric.application.getApplicationContext());
        final TextView topic = (TextView) headerView.findViewById(R.id.header_topic);

        // then
        ANDROID.assertThat(topic)
                .isNotNull()
                .isVisible()
                .hasText(Robolectric.application.getString(R.string.demo_huddle_topic));
    }


    @Test
    public void shouldDisplayFriendList() throws Exception {
        // given
        final ListView listView = (ListView) friendListFragment.getView().findViewById(
                R.id.friends_list_view);

        // then
        Assertions.assertThat(listView.getAdapter()).isInstanceOf(FriendListItemAdapter_.class);
        ANDROID.assertThat(listView).isNotNull().isVisible();
    }
}
