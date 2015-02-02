package com.sbhachu.fanatix.test.presentation.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.sbhachu.fanatixchallenge.R;
import com.sbhachu.fanatixchallenge.data.model.Friend;
import com.sbhachu.fanatixchallenge.presentation.adapter.FriendListItemAdapter;
import com.sbhachu.fanatixchallenge.presentation.adapter.FriendListItemAdapter_;

import org.fest.assertions.api.ANDROID;
import org.fest.assertions.api.Assertions;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowDrawable;

@RunWith(RobolectricTestRunner.class)
@Config(emulateSdk = 18)
public class FriendListItemAdapterTest {

    private FriendListItemAdapter adapter;

    @Before
    public void setUp() throws Exception {
        adapter = FriendListItemAdapter_.getInstance_(
                Robolectric.application.getApplicationContext());
    }

    @After
    public void tearDown() throws Exception {
        adapter = null;
    }

    @Test
    public void shouldDisplayUsername() {
        // given
        final Friend friend = new Friend("friend-id", "robolectric-user", "http://url-to-an-image",
                                         "robolectric united", "abc123", true, false);
        adapter.addItem(friend);

        // when
        View view = adapter.getView(0, null, null);
        TextView userNameView = (TextView) view.findViewById(R.id.friend_item_name_text_view);

        // then
        ANDROID.assertThat(userNameView)
                .isNotNull()
                .isVisible()
                .hasText("robolectric-user");
    }

    @Test
    public void shouldDisplayUserImage() throws Exception {
        // given
        final Friend friend = new Friend("friend-id", "robolectric-user", "http://url-to-an-image",
                                         "robolectric united", "abc123", true, false);
        adapter.addItem(friend);

        // when
        View view = adapter.getView(0, null, null);
        ImageView userImageView = (ImageView) view.findViewById(R.id.friend_image_view);

        // then
        ANDROID.assertThat(userImageView)
                .isNotNull()
                .isVisible();
    }

    @Test
    public void shouldDisplayUserReasonIfAvailable() throws Exception {
        // given
        final Friend friend = new Friend("friend-id", "robolectric-user", "http://url-to-an-image",
                                         "robolectric united", "abc123", true, false);
        adapter.addItem(friend);

        // when
        View view = adapter.getView(0, null, null);
        TextView userReasonView = (TextView) view.findViewById(R.id.friend_item_reason);

        // then
        ANDROID.assertThat(userReasonView)
                .isNotNull()
                .isVisible()
                .hasText("Likes Robolectric United");
    }

    @Test
    public void shouldNotDisplayUserReasonIfNull() throws Exception {
        // given
        final Friend friend = new Friend("friend-id", "robolectric-user", "http://url-to-an-image",
                                         null, "abc123", true, false);
        adapter.addItem(friend);

        // when
        View view = adapter.getView(0, null, null);
        TextView userReasonView = (TextView) view.findViewById(R.id.friend_item_reason);

        // then
        ANDROID.assertThat(userReasonView)
                .isNotNull()
                .isGone()
                .hasText("");
    }

    @Test
    public void shouldDisplayGreenListIfPrimaryAndHasChat() throws Exception {
        // given
        final Friend friend = new Friend("friend-id", "robolectric-user", "http://url-to-an-image",
                                         null, "abc123", true, true);
        adapter.addItem(friend);

        // when
        View view = adapter.getView(0, null, null);
        View statusLightView = view.findViewById(R.id.friend_status);

        ShadowDrawable shadowDrawable = Robolectric.shadowOf(statusLightView.getBackground());

        // then
        ANDROID.assertThat(statusLightView)
                .isNotNull()
                .isVisible();

        Assertions.assertThat(shadowDrawable.getCreatedFromResId())
                .isEqualTo(R.drawable.green_light);
    }

    @Test
    public void shouldDisplayGreyLightIfNotPrimaryAndHasChat() throws Exception {
        // given
        final Friend friend = new Friend("friend-id", "robolectric-user", "http://url-to-an-image",
                                         null, "abc123", true, false);
        adapter.addItem(friend);

        // when
        View view = adapter.getView(0, null, null);
        View statusLightView = view.findViewById(R.id.friend_status);

        ShadowDrawable shadowDrawable = Robolectric.shadowOf(statusLightView.getBackground());

        // then
        ANDROID.assertThat(statusLightView)
                .isNotNull()
                .isVisible();

        Assertions.assertThat(shadowDrawable.getCreatedFromResId())
                .isEqualTo(R.drawable.grey_light);
    }

    @Test
    public void shouldNotDisplayLightIfNotPrimaryAndHasNoChat() throws Exception {
        // given
        final Friend friend = new Friend("friend-id", "robolectric-user", "http://url-to-an-image",
                                         null, "abc123", false, false);
        adapter.addItem(friend);

        // when
        View view = adapter.getView(0, null, null);
        View statusLightView = view.findViewById(R.id.friend_status);

        // then
        ANDROID.assertThat(statusLightView)
                .isNotNull()
                .isGone();
    }
}
