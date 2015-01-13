package com.sbhachu.flingchallenge.test.service;

import com.sbhachu.flingchallenge.data.dao.ImageItemDAO;
import com.sbhachu.flingchallenge.data.dao.UserDAO;
import com.sbhachu.flingchallenge.data.model.ImageItem;
import com.sbhachu.flingchallenge.event.ApplicationEventBus;
import com.sbhachu.flingchallenge.event.ApplicationEvents;
import com.sbhachu.flingchallenge.network.RestClient;
import com.sbhachu.flingchallenge.service.ImageService;
import com.sbhachu.flingchallenge.util.PhoneConnectivityUtil;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.BDDMockito.*;

/**
 * Created by sbhachu on 11/01/2015.
 */
@RunWith(MockitoJUnitRunner.class)
public class ImageServiceTest {

    @Mock
    private ImageService imageService;

    @Mock
    private PhoneConnectivityUtil phoneConnectivityUtil;

    @Mock
    private ImageItemDAO imageItemDAO;

    @Mock
    private UserDAO userDAO;

    @Mock
    private ApplicationEventBus eventBus;

    @Mock
    private RestClient restClient;

    @Before
    public void setUp() throws Exception {
        imageService = new ImageService(eventBus, phoneConnectivityUtil, restClient, imageItemDAO, userDAO);
    }

    @Test
    public void shouldDispatchDeviceOfflineEventIfDeviceIsOffline() throws SQLException {
        // Given
        given(phoneConnectivityUtil.isOnline()).willReturn(false);
        given(imageItemDAO.getRowCount()).willReturn(0l);

        // When
        imageService.loadImageData();

        // Then
        verify(eventBus).post(ApplicationEvents.phoneOfflineEvent());
    }

    @Test
    public void shouldLoadAllImageItemDataAndDispatchImageItemDataLoadedEvent() throws Exception {
        // Given
        given(phoneConnectivityUtil.isOnline()).willReturn(true);
        given(imageItemDAO.getRowCount()).willReturn(0l);
        final List<ImageItem> imageItems = new ArrayList<>();

        // When
        imageService.loadImageData();

        // Then
        verify(eventBus).post(ApplicationEvents.responseReceivedEvent(imageItems));
    }
}
