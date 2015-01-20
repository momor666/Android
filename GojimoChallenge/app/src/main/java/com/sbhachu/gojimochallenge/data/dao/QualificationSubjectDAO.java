package com.sbhachu.gojimochallenge.data.dao;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.sbhachu.gojimochallenge.data.model.QualificationSubject;

import java.sql.SQLException;

public class QualificationSubjectDAO extends BaseDaoImpl<QualificationSubject, String> implements Dao<QualificationSubject, String> {

    private ConnectionSource connectionSource;

    public QualificationSubjectDAO(ConnectionSource connectionSource, Class<QualificationSubject> dataClass) throws SQLException {
        super(connectionSource, dataClass);
        this.connectionSource = connectionSource;
    }

    @Override
    public ConnectionSource getConnectionSource() {
        return connectionSource;
    }



}
