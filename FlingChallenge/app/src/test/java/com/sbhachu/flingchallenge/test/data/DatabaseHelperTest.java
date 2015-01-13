package com.sbhachu.flingchallenge.test.data;

import android.content.Context;

import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.sbhachu.flingchallenge.data.DatabaseHelper;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by sbhachu on 10/01/2015.
 */
@RunWith(RobolectricTestRunner.class)
@Config(emulateSdk = 18)
public class DatabaseHelperTest {

    private static final String TAG = DatabaseHelperTest.class.getSimpleName();

    private Context context;

    @Before
    public void setUp() throws Exception {
        context = Robolectric.application.getApplicationContext();
    }

    @Test
    public void testPreconditions() throws Exception {
        assertThat(context).isNotNull();
    }

    @Test
    public void shouldVerifyDatabaseHelperHasBeenCreated() throws Exception {
        DatabaseHelper databaseHelper = OpenHelperManager.getHelper(context, DatabaseHelper.class);
        assertThat(databaseHelper).isNotNull();
    }
}
