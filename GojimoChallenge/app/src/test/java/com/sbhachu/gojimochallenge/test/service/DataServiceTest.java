package com.sbhachu.gojimochallenge.test.service;

import com.sbhachu.gojimochallenge.data.QualificationList;
import com.sbhachu.gojimochallenge.data.dao.QualificationDAO;
import com.sbhachu.gojimochallenge.data.dao.QualificationSubjectDAO;
import com.sbhachu.gojimochallenge.data.dao.SubjectDAO;
import com.sbhachu.gojimochallenge.event.ApplicationEventBus;
import com.sbhachu.gojimochallenge.network.RestClient;
import com.sbhachu.gojimochallenge.service.DataService;
import com.sbhachu.gojimochallenge.util.PhoneConnectivityUtil;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.annotation.RealObject;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static com.sbhachu.gojimochallenge.event.ApplicationEvents.phoneOfflineEvent;
import static com.sbhachu.gojimochallenge.event.ApplicationEvents.qualificationDataLoadedEvent;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class DataServiceTest {

    @Mock
    private DataService dataService;

    @Mock
    private PhoneConnectivityUtil phoneConnectivityUtil;

    @Mock
    private QualificationDAO qualificationDAO;

    @Mock
    private SubjectDAO subjectDAO;

    @Mock
    private QualificationSubjectDAO qualificationSubjectDAO;

    @Mock
    private ApplicationEventBus applicationEventBus;

    @Mock
    private RestClient restClient;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        dataService = new DataService(applicationEventBus, phoneConnectivityUtil, restClient,
                                      qualificationDAO, subjectDAO, qualificationSubjectDAO);
    }

    @After
    public void tearDown() throws Exception {
        dataService = null;
    }

    @Test
    public void testPreconditions() throws Exception {
        assertThat(dataService).isNotNull();
    }

    @Test
    public void testShouldDispatchDeviceOfflineEventIfDeviceIsOffline() throws Exception {
        // Given
        given(phoneConnectivityUtil.isOnline()).willReturn(false);
        given(qualificationDAO.countOf()).willReturn(0l);

        // When
        dataService.loadQualificationData();

        // Then
        verify(applicationEventBus).post(phoneOfflineEvent());
    }

    @Test
    public void testShouldLoadAllQualificationDataAndDispatchDataLoadedEvent() throws Exception {
        // Given
        given(phoneConnectivityUtil.isOnline()).willReturn(true);
        given(qualificationDAO.countOf()).willReturn(0l);
        given(restClient.fetchQualifications())
                .willReturn(new ResponseEntity<QualificationList>(null, new HttpHeaders(),
                                                                  HttpStatus.OK));

        // When
        dataService.loadQualificationData();

        // Then
        verify(applicationEventBus).post(qualificationDataLoadedEvent());
    }
}
