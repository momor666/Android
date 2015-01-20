package com.sbhachu.gojimochallenge.data.dao;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.support.DatabaseConnection;
import com.sbhachu.gojimochallenge.data.model.Qualification;

import java.sql.SQLException;
import java.sql.Savepoint;
import java.util.Collection;

public class QualificationDAO extends BaseDaoImpl<Qualification, String> implements Dao<Qualification, String> {

    private ConnectionSource connectionSource;

    public QualificationDAO(ConnectionSource connectionSource, Class<Qualification> dataClass) throws SQLException {
        super(connectionSource, dataClass);
        this.connectionSource = connectionSource;
    }

    @Override
    public ConnectionSource getConnectionSource() {
        return connectionSource;
    }
}
