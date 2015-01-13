package com.sbhachu.flingchallenge.test.presentation;

import android.support.v7.widget.Toolbar;

import com.sbhachu.flingchallenge.presentation.activity.MainActivity_;

import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import static org.fest.assertions.api.ANDROID.assertThat;

/**
 * The Robolectric UI tests aren't behaving with Lollipop
 * Causing Theme App Compat errors when executed, no time to find a workaround
 */
@RunWith(RobolectricTestRunner.class)
@Config(emulateSdk = 18, reportSdk = 18)
public class MainActivityTest {

    private MainActivity_ activity;

    @Before
    public void setUp() throws Exception {
        activity = Robolectric.setupActivity(MainActivity_.class);

    }

    @Test
    public void shouldDisplayToolbarWithSpecifiedTitle() throws Exception {
        // Given
        Toolbar toolbar = activity.toolbar;

        // Then
        assertThat(toolbar).isNotNull();

        // Given
        Assertions.assertThat(toolbar.getTitle()).isEqualTo("Fling Challenge");
    }
}
