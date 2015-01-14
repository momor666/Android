package com.sbhachu.flingchallenge.test.data.dao;

import android.content.Context;

import com.j256.ormlite.support.ConnectionSource;
import com.sbhachu.flingchallenge.data.DatabaseHelper;
import com.sbhachu.flingchallenge.data.dao.ImageItemDAO;
import com.sbhachu.flingchallenge.data.model.ImageItem;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import java.util.ArrayList;
import java.util.List;

import static org.fest.assertions.api.Assertions.*;
import static org.mockito.MockitoAnnotations.initMocks;

/**
 * Created by sbhachu on 11/01/2015.
 */
@RunWith(RobolectricTestRunner.class)
@Config(emulateSdk = 18)
public class ImageItemDAOTest {

    @Mock
    private DatabaseHelper databaseHelper;

    @Mock
    private ConnectionSource connectionSource;

    @Mock
    private ImageItemDAO imageItemDAO;

    @Before
    public void setUp() throws Exception {
        initMocks(this);
        databaseHelper = new DatabaseHelper(Robolectric.application.getApplicationContext());
        connectionSource = databaseHelper.getConnectionSource();
        imageItemDAO = new ImageItemDAO(connectionSource, ImageItem.class);
    }

    @After
    public void tearDown() throws Exception {
        imageItemDAO = null;

        connectionSource.close();
        connectionSource = null;

        databaseHelper.close();
        databaseHelper = null;
    }

    @Test
    public void shouldCountRowsInImageItemTable() throws Exception {

        // When
        final long count = imageItemDAO.getRowCount();

        // Then
        assertThat(count).isEqualTo(0);
    }

    @Test
    public void shouldBatchInsertImageItems() throws Exception {
        // Given
        final ImageItem imageItem_1 = new ImageItem(1, 1, 300, "Image Item 1", 400, 400);
        final ImageItem imageItem_2 = new ImageItem(2, 1, 299, "Image Item 2", 400, 400);
        final ImageItem imageItem_3 = new ImageItem(3, 2, 298, "Image Item 3", 400, 400);

        final List<ImageItem> imageItems = new ArrayList<>();
        imageItems.add(imageItem_1);
        imageItems.add(imageItem_2);
        imageItems.add(imageItem_3);

        // Then
        assertThat(imageItems).isNotNull().isNotEmpty().hasSize(3);

        // When
        imageItemDAO.batchInsert(imageItems);

        final long count = imageItemDAO.getRowCount();

        // Then
        assertThat(count).isEqualTo(3);
    }
}
