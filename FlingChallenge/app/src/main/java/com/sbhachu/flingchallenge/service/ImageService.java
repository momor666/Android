package com.sbhachu.flingchallenge.service;

import com.sbhachu.flingchallenge.data.dao.ImageItemDAO;
import com.sbhachu.flingchallenge.data.dao.UserDAO;
import com.sbhachu.flingchallenge.data.model.ImageItem;
import com.sbhachu.flingchallenge.data.model.User;
import com.sbhachu.flingchallenge.data.model.dto.ImageItemDTO;
import com.sbhachu.flingchallenge.event.ApplicationEventBus;
import com.sbhachu.flingchallenge.event.ApplicationEvents;
import com.sbhachu.flingchallenge.network.RestClient;
import com.sbhachu.flingchallenge.util.PhoneConnectivityUtil;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by sbhachu on 11/01/2015.
 */
public class ImageService {

    private static final String TAG = ImageService.class.getSimpleName();

    public ApplicationEventBus eventBus;
    public PhoneConnectivityUtil phoneConnectivityUtil;
    public RestClient restClient;
    public ImageItemDAO imageItemDAO;
    public UserDAO userDAO;
    private List<ImageItem> images;

    /**
     * Required for testing, this object is managed by Android Annotations and not
     * manually instantiated
     */
    public ImageService(ApplicationEventBus eventBus, PhoneConnectivityUtil phoneConnectivityUtil,
                        RestClient restClient, ImageItemDAO imageItemDAO, UserDAO userDAO) {
        this.eventBus = eventBus;
        this.phoneConnectivityUtil = phoneConnectivityUtil;
        this.restClient = restClient;
        this.imageItemDAO = imageItemDAO;
        this.userDAO = userDAO;
    }

    /**
     * Load Image Data
     * <p/>
     * If image item data doesn't exist in database, load the data from remote service and persist
     * items to the database table.  Otherwise read all image items from the local database table.
     * <p/>
     * The data will only be requested from remote service once, this strategy is fine for static
     * data set, but for a real world application with dynamic data sets, this is insufficient.
     */
    public void loadImageData() {

        List<ImageItemDTO> imageItemDTOs = new ArrayList<>();
        images = new ArrayList<>();

        try {
            long count = imageItemDAO.getRowCount();

            if (count > 0) {
                images = imageItemDAO.queryForAll();
            } else {
                if (!phoneConnectivityUtil.isOnline()) {
                    eventBus.post(ApplicationEvents.phoneOfflineEvent());
                    return;
                }

                imageItemDTOs = restClient.fetchImageList();

                buildImageItemListFromDTOs(imageItemDTOs);
                buildUserListFromDTOs(imageItemDTOs);
            }

            eventBus.post(ApplicationEvents.responseReceivedEvent(images));

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Iterates over the ImageItemDTO collection and adds ImageItem objects to collection
     */
    private void buildImageItemListFromDTOs(List<ImageItemDTO> imageItemDTOs) throws SQLException {

        for (ImageItemDTO dto : imageItemDTOs) {
            images.add(new ImageItem(dto));
        }

        if (images.size() > 0) {
            imageItemDAO.batchInsert(images);
        }
    }

    /**
     * Iterates over the ImageItemDTO collection and adds User objects to collection
     * TODO: Merge with above method
     */
    private void buildUserListFromDTOs(List<ImageItemDTO> imageItemDTOs) throws SQLException {
        final List<User> users = new ArrayList<>();

        for (ImageItemDTO dto : imageItemDTOs) {
            User user = new User(dto);

            if (!users.contains(user)) {
                users.add(user);
            }
        }

        if (users.size() > 0) {
            userDAO.batchInsert(users);
        }
    }

}
