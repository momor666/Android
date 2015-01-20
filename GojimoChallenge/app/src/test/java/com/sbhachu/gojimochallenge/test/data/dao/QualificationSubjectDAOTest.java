package com.sbhachu.gojimochallenge.test.data.dao;

import com.j256.ormlite.support.ConnectionSource;
import com.sbhachu.gojimochallenge.data.DatabaseHelper;
import com.sbhachu.gojimochallenge.data.dao.QualificationSubjectDAO;
import com.sbhachu.gojimochallenge.data.model.Qualification;
import com.sbhachu.gojimochallenge.data.model.QualificationSubject;
import com.sbhachu.gojimochallenge.data.model.Subject;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import java.util.Date;
import java.util.UUID;

import static com.j256.ormlite.dao.Dao.CreateOrUpdateStatus;
import static org.fest.assertions.api.Assertions.assertThat;

@RunWith(RobolectricTestRunner.class)
@Config(emulateSdk = 18)
public class QualificationSubjectDAOTest {

    @Mock
    private DatabaseHelper databaseHelper;

    @Mock
    private ConnectionSource connectionSource;

    @Mock
    private QualificationSubjectDAO qualificationSubjectDAO;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        databaseHelper = new DatabaseHelper(Robolectric.application.getApplicationContext());
        connectionSource = databaseHelper.getConnectionSource();
        qualificationSubjectDAO = new QualificationSubjectDAO(connectionSource,
                                                              QualificationSubject.class);
    }

    @After
    public void tearDown() throws Exception {
        qualificationSubjectDAO = null;

        connectionSource.close();
        connectionSource = null;

        databaseHelper.close();
        databaseHelper = null;
    }

    @Test
    public void testDaoCreateOrUpdate() throws Exception {
        // Given
        final String id = UUID.randomUUID().toString();

        // When
        final QualificationSubject nonPersistentQualificationSubject =
                qualificationSubjectDAO.queryForId(id);

        // Then
        assertThat(nonPersistentQualificationSubject).isNull();

        // When
        final Qualification qualification = new Qualification(id, "qualification", null, new Date(),
                                                              new Date(), "http://insert-link");

        final Subject subject = new Subject(id, "subject", "http://insertlink", "#FF9900");

        final QualificationSubject qualificationSubject = new QualificationSubject(id,
                                                                                   qualification,
                                                                                   subject);
        // Then
        assertThat(qualificationSubject).isNotNull();

        // When
        final CreateOrUpdateStatus status =
                qualificationSubjectDAO.createOrUpdate(qualificationSubject);

        // Then
        assertThat(status).isNotNull();
        assertThat(status.getNumLinesChanged()).isEqualTo(1);
        assertThat(status.isCreated()).isTrue();
        assertThat(status.isUpdated()).isFalse();

        assertThat(qualificationSubjectDAO.countOf()).isEqualTo(1);
    }
}
