package com.sbhachu.gojimochallenge.test.presentation.activity;

import android.support.v7.widget.Toolbar;

import com.sbhachu.gojimochallenge.presentation.activity.MainActivity_;

import org.fest.assertions.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import static org.fest.assertions.api.ANDROID.assertThat;
import static org.mockito.Mockito.mock;

@RunWith(RobolectricTestRunner.class)
@Config(emulateSdk = 18)
public class MainActivityTest {

    private MainActivity_ activity;

    private Toolbar toolbar;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        activity = Robolectric.setupActivity(MainActivity_.class);
        activity.toolbar = mock(Toolbar.class);
    }

    @Test
    public void shouldDisplayToolbarWithSpecifiedTitle() throws Exception {

        // Then
        assertThat(toolbar).isNotNull();

        // Given
        Assertions.assertThat(activity.getTitle()).isEqualTo("Gojimo Challenge");
    }
}
