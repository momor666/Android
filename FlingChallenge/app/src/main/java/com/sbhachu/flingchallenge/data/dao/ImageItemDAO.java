package com.sbhachu.flingchallenge.data.dao;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.support.DatabaseConnection;
import com.sbhachu.flingchallenge.data.model.ImageItem;

import java.sql.SQLException;
import java.sql.Savepoint;
import java.util.List;

/**
 * Created by sbhachu on 11/01/2015.
 */
public class ImageItemDAO extends BaseDaoImpl<ImageItem, Integer> implements Dao<ImageItem, Integer> {

    private ConnectionSource connectionSource;

    public ImageItemDAO(ConnectionSource connectionSource, Class<ImageItem> dataClass) throws SQLException {
        super(connectionSource, dataClass);
        this.connectionSource = connectionSource;
    }

    @Override
    public ConnectionSource getConnectionSource() {
        return connectionSource;
    }

    public long getRowCount() throws SQLException {
        return this.queryBuilder().countOf();
    }

    /**
     * Batch inserts ImageItem records into the database table.
     * The records are stored using stable IDs and not auto-incremented table IDs, therefore
     * a simple createOrUpdate can be used.  This also ensures only a single copy of the
     * record without flushing the table.
     *
     * This strategy can only work if the ID on the object remains static and is not changed
     * on the server-side
     */
    public void batchInsert(List<ImageItem> imageItems) throws SQLException {
        DatabaseConnection databaseConnection = this.startThreadConnection();
        Savepoint savepoint = null;
        try {
            savepoint = databaseConnection.setSavePoint(null);
            doInsert(imageItems);
        } finally {
            databaseConnection.commit(savepoint);
            this.endThreadConnection(databaseConnection);
        }
    }

    /**
     * Iterates over the ImageItem collection and executes a createOrUpdate
     */
    private void doInsert(List<ImageItem> imageItems) {
        for (ImageItem imageItem : imageItems) {
            try {
                if (imageItem != null) {
                    this.createOrUpdate(imageItem);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
