package com.sbhachu.gojimochallenge.test.data.dao;

import com.j256.ormlite.support.ConnectionSource;
import com.sbhachu.gojimochallenge.data.DatabaseHelper;
import com.sbhachu.gojimochallenge.data.dao.SubjectDAO;
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

import java.util.UUID;

import static com.j256.ormlite.dao.Dao.CreateOrUpdateStatus;
import static org.fest.assertions.api.Assertions.assertThat;

@RunWith(RobolectricTestRunner.class)
@Config(emulateSdk = 18)
public class SubjectDAOTest {

    @Mock
    private DatabaseHelper databaseHelper;

    @Mock
    private ConnectionSource connectionSource;

    @Mock
    private SubjectDAO subjectDAO;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        databaseHelper = new DatabaseHelper(Robolectric.application.getApplicationContext());
        connectionSource = databaseHelper.getConnectionSource();
        subjectDAO = new SubjectDAO(connectionSource, Subject.class);
    }

    @After
    public void tearDown() throws Exception {
        subjectDAO = null;

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
        final Subject nonPersistentSubject = subjectDAO.queryForId(id);

        // Then
        assertThat(nonPersistentSubject).isNull();

        // When
        final Subject subject = new Subject(id, "subject", "http://insertlink", "#FF9900");

        // Then
        assertThat(subject).isNotNull();

        // When
        final CreateOrUpdateStatus status = subjectDAO.createOrUpdate(subject);

        // Then
        assertThat(status).isNotNull();
        assertThat(status.getNumLinesChanged()).isEqualTo(1);
        assertThat(status.isCreated()).isTrue();
        assertThat(status.isUpdated()).isFalse();

        assertThat(subjectDAO.countOf()).isEqualTo(1);
    }
}
