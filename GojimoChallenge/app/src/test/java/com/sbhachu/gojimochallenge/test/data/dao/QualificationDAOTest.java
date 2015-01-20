package com.sbhachu.gojimochallenge.test.data.dao;

import com.j256.ormlite.support.ConnectionSource;
import com.sbhachu.gojimochallenge.data.DatabaseHelper;
import com.sbhachu.gojimochallenge.data.dao.QualificationDAO;
import com.sbhachu.gojimochallenge.data.model.Qualification;

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
public class QualificationDAOTest {

    @Mock
    private DatabaseHelper databaseHelper;

    @Mock
    private ConnectionSource connectionSource;

    @Mock
    private QualificationDAO qualificationDAO;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        databaseHelper = new DatabaseHelper(Robolectric.application.getApplicationContext());
        connectionSource = databaseHelper.getConnectionSource();
        qualificationDAO = new QualificationDAO(connectionSource, Qualification.class);
    }

    @After
    public void tearDown() throws Exception {
        qualificationDAO = null;

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
        final Qualification nonPersistentQualification = qualificationDAO.queryForId(id);

        // Then
        assertThat(nonPersistentQualification).isNull();

        // When
        final Qualification qualification = new Qualification(id, "qualification", null, new Date(),
                                                              new Date(), "http://insert-link");

        // Then
        assertThat(qualification).isNotNull();

        // When
        final CreateOrUpdateStatus status = qualificationDAO.createOrUpdate(qualification);

        // Then
        assertThat(status).isNotNull();
        assertThat(status.getNumLinesChanged()).isEqualTo(1);
        assertThat(status.isCreated()).isTrue();
        assertThat(status.isUpdated()).isFalse();

        assertThat(qualificationDAO.countOf()).isEqualTo(1);
    }
}
