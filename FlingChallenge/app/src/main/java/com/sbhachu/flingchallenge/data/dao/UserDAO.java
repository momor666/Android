package com.sbhachu.flingchallenge.data.dao;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.support.DatabaseConnection;
import com.sbhachu.flingchallenge.data.model.User;

import java.sql.SQLException;
import java.sql.Savepoint;
import java.util.List;

/**
 * Created by sbhachu on 12/01/2015.
 */
public class UserDAO extends BaseDaoImpl<User, Integer> implements Dao<User, Integer> {

    private ConnectionSource connectionSource;

    public UserDAO(ConnectionSource connectionSource, Class<User> dataClass) throws SQLException {
        super(connectionSource, dataClass);
        this.connectionSource = connectionSource;
    }

    @Override
    public ConnectionSource getConnectionSource() {
        return connectionSource;
    }

    /**
     * Batch inserts User records into the User database table.
     * TODO: As this is also used in the ImageItemDAO, it should be moved to a generic base class
     */
    public void batchInsert(List<User> users) throws SQLException {
        DatabaseConnection databaseConnection = this.startThreadConnection();
        Savepoint savepoint = null;
        try {
            savepoint = databaseConnection.setSavePoint(null);
            doInsert(users);
        } finally {
            databaseConnection.commit(savepoint);
            this.endThreadConnection(databaseConnection);
        }
    }

    private void doInsert(List<User> users) {
        for (User user : users) {
            try {
                if (user != null) {
                    this.createOrUpdate(user);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
