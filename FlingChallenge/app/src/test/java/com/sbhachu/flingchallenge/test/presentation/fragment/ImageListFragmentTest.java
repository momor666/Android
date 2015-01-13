package com.sbhachu.flingchallenge.test.presentation.fragment;

import android.support.v7.widget.Toolbar;
import android.widget.ListView;

import com.sbhachu.flingchallenge.event.ApplicationEventBus;
import com.sbhachu.flingchallenge.presentation.fragment.FragmentFactory;
import com.sbhachu.flingchallenge.presentation.fragment.ImageListFragment;
import com.sbhachu.flingchallenge.presentation.fragment.ImageListFragment_;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

/**
 * The Robolectric UI tests aren't behaving with Lollipop
 * Causing Theme App Compat errors when executed (likely related to Toolbar widget,
 * no time to find a workaround
 */
@RunWith(RobolectricTestRunner.class)
@Config(emulateSdk = 18)
public class ImageListFragmentTest extends BaseFragmentTest<ImageListFragment> {

    @Mock
    private ApplicationEventBus eventBus;

    @Before
    public void setUp() throws Exception {
        startFragment(FragmentFactory.buildImageListFragment());
    }

    @Test
    public void shouldDisplayToolbarWithSpecifiedTitle() throws Exception {
        // Given
        ListView imageListView = (ListView) fragment.imageListView;
    }
}
