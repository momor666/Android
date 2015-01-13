package com.sbhachu.flingchallenge.data;

import android.util.Log;

import com.sbhachu.flingchallenge.data.dao.ImageItemDAO;
import com.sbhachu.flingchallenge.data.dao.UserDAO;
import com.sbhachu.flingchallenge.data.model.ImageItem;
import com.sbhachu.flingchallenge.data.model.User;

import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.OrmLiteDao;
import org.apache.commons.lang3.StringUtils;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

import static org.androidannotations.annotations.EBean.Scope;

/**
 * Created by sbhachu on 12/01/2015.
 */
@EBean(scope = Scope.Singleton)
public class UserMetrics {

    private static final String TAG = UserMetrics.class.getSimpleName();

    @OrmLiteDao(helper = DatabaseHelper.class, model = ImageItem.class)
    public ImageItemDAO imageItemDAO;

    @OrmLiteDao(helper = DatabaseHelper.class, model = User.class)
    public UserDAO userDAO;

    /**
     * Builds user metrics and populates the User database table
     *
     * Performance here could be improved by querying for images by user id, instead
     * of iterating over entire collection and branching by user id, unfortunately no time to test.
     */
    @Background(serial = "metrics")
    public void buildUserMetrics() {
        try {
            List<ImageItem> images = imageItemDAO.queryForAll();
            List<User> users = userDAO.queryForAll();

            Collections.sort(images, ImageItem.USER_ID_COMPARATOR);

            for (User user : users) {

                int imageCount = 0;
                int maxWidth = 0;
                int maxHeight = 0;
                int averageImageSize = 0;
                int pixels = 0;

                for (ImageItem item : images) {
                    if (item.getUserId() == user.getUserId()) {
                        imageCount++;
                        maxWidth = (item.getSourceWidth() > maxWidth) ? item.getSourceWidth() : maxWidth;
                        maxHeight = (item.getSourceHeight() > maxHeight) ? item.getSourceHeight() : maxHeight;
                        pixels += (item.getSourceWidth() * item.getSourceHeight());
                        averageImageSize = pixels / imageCount;
                    }
                }
                user.setPostCount(imageCount);
                user.setMaxImageWidth(maxWidth);
                user.setMaxImageHeight(maxHeight);
                user.setAverageImageSize(averageImageSize);

                userDAO.createOrUpdate(user);
            }
        } catch (SQLException e) {
            Log.e(TAG, e.getMessage());
        }
    }

    /**
     * Prints User metrics table to LogCat (debug).
     * Uses Apache Commons StringUtils to pad strings, for correct table alignment
     *
     * Runs as a Background task, the Serial flag in the annotation ensure that this method runs
     * sequentially after the buildUserMetrics method above, and is not executed in parallel,
     * which would result in errors
     */
    @Background(serial = "metrics")
    public void displayUserMetrics() {
        try {
            List<User> users = userDAO.queryForAll();

            Log.d(TAG, "+---------------------------------------------------------------------------------------+");
            Log.d(TAG, "| User Metrics Report                                                                   |");
            Log.d(TAG, "+-------+-------------+---------+-----------------+------------------+------------------+");
            Log.d(TAG, "| ID    | Username    | Posts   | Avg Size (px)   | Max Width (px)   | Max Height (px)  |");
            Log.d(TAG, "+-------+-------------+---------+-----------------+------------------+------------------+");
            for (User user : users) {
                Log.d(TAG, "| " + StringUtils.rightPad(String.valueOf(user.getUserId()), 6) +
                        "| " + StringUtils.rightPad(user.getUsername(), 12) +
                        "| " + StringUtils.rightPad(String.valueOf(user.getPostCount()),8) +
                        "| " + StringUtils.rightPad(String.format("%,d", user.getAverageImageSize()), 16) +
                        "| " + StringUtils.rightPad(String.format("%,d", user.getMaxImageWidth()), 17) +
                        "| " + StringUtils.rightPad(String.format("%,d", user.getMaxImageHeight()), 17) +
                        "|");
            }
            Log.d(TAG, "+-------+-------------+---------+-----------------+------------------+------------------+");

        }
        catch (SQLException e) {
            Log.e(TAG, e.getMessage());
        }
    }
}
