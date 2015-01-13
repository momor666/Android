package com.sbhachu.flingchallenge.test.data.dao;

import com.sbhachu.flingchallenge.data.dao.ImageItemDAO;
import com.sbhachu.flingchallenge.data.model.ImageItem;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.fest.assertions.api.Assertions.*;

/**
 * Created by sbhachu on 11/01/2015.
 */
@RunWith(MockitoJUnitRunner.class)
public class ImageItemDAOTest {

    @Mock
    private ImageItemDAO imageItemDAO;

    @Before
    public void setUp() throws Exception {
        //imageItemDao =

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
