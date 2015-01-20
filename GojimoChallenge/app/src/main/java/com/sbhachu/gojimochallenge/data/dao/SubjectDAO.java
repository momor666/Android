package com.sbhachu.gojimochallenge.data.dao;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.sbhachu.gojimochallenge.data.model.Subject;

import java.sql.SQLException;

public class SubjectDAO extends BaseDaoImpl<Subject, String> implements Dao<Subject, String> {

    private ConnectionSource connectionSource;

    public SubjectDAO(ConnectionSource connectionSource, Class<Subject> dataClass) throws SQLException {
        super(connectionSource, dataClass);
        this.connectionSource = connectionSource;
    }

    @Override
    public ConnectionSource getConnectionSource() {
        return connectionSource;
    }
}
