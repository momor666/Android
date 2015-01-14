package com.sbhachu.flingchallenge.test.data.dao;

import com.j256.ormlite.support.ConnectionSource;
import com.sbhachu.flingchallenge.data.DatabaseHelper;
import com.sbhachu.flingchallenge.data.dao.UserDAO;
import com.sbhachu.flingchallenge.data.model.User;

import org.fest.assertions.api.Assertions;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import java.util.ArrayList;
import java.util.List;

import static org.fest.assertions.api.Assertions.assertThat;
import static org.mockito.MockitoAnnotations.initMocks;

/**
 * Created by sbhachu on 14/01/2015.
 */
@RunWith(RobolectricTestRunner.class)
@Config(emulateSdk = 18)
public class UserDAOTest {
    
    @Mock
    private DatabaseHelper databaseHelper;
    
    @Mock
    private ConnectionSource connectionSource;
    
    @Mock
    private UserDAO userDAO;

    @Before
    public void setUp() throws Exception {
        initMocks(this);
        databaseHelper = new DatabaseHelper(Robolectric.application.getApplicationContext());
        connectionSource = databaseHelper.getConnectionSource();
        userDAO = new UserDAO(connectionSource, User.class);
    }

    @After
    public void tearDown() throws Exception {
        userDAO = null;

        connectionSource.close();
        connectionSource = null;

        databaseHelper.close();
        databaseHelper = null;
    }

    @Test
    public void shouldBatchInsertUsers() throws Exception {
        // Given
        final User user_1 = new User(1, 1, 0, 0, 0);
        final User user_2 = new User(2, 1, 0, 0, 0);
        final User user_3 = new User(3, 1, 0, 0, 0);
        
        final List<User> userList = new ArrayList<>();
        userList.add(user_1);
        userList.add(user_2);
        userList.add(user_3);
        
        // Then
        assertThat(userList).isNotNull().isNotEmpty().hasSize(3);

        // When
        userDAO.batchInsert(userList);

        // Then
        assertThat(userDAO.countOf()).isEqualTo(3);
    }
}
