package com.sbhachu.gojimochallenge.test.data;

import android.content.Context;

import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.sbhachu.gojimochallenge.data.DatabaseHelper;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import static org.assertj.core.api.Assertions.assertThat;


@RunWith(RobolectricTestRunner.class)
@Config(emulateSdk = 18)
public class DatabaseHelperTest {

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
