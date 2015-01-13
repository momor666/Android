package com.sbhachu.flingchallenge.presentation.fragment;


import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ListView;
import android.widget.TextView;

import com.sbhachu.flingchallenge.R;
import com.sbhachu.flingchallenge.data.DatabaseHelper;
import com.sbhachu.flingchallenge.data.UserMetrics;
import com.sbhachu.flingchallenge.data.dao.ImageItemDAO;
import com.sbhachu.flingchallenge.data.dao.UserDAO;
import com.sbhachu.flingchallenge.data.model.ImageItem;
import com.sbhachu.flingchallenge.data.model.User;
import com.sbhachu.flingchallenge.event.ApplicationEventBus;
import com.sbhachu.flingchallenge.event.ApplicationEvents;
import com.sbhachu.flingchallenge.network.RestClient;
import com.sbhachu.flingchallenge.presentation.adapter.ImageItemAdapter;
import com.sbhachu.flingchallenge.service.ImageService;
import com.sbhachu.flingchallenge.util.PhoneConnectivityUtil;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.OrmLiteDao;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.rest.RestService;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * ImageList fragment
 * Essentially contains the logic for displaying the image list.
 *
 * The logic related to displaying the user metrics should bubble up and be handled by the
 * MainActivity to prevent replication when a tablet fragment be added.
 */
@EFragment(R.layout.fragment_image_list)
public class ImageListFragment extends Fragment {

    private static final String TAG = ImageListFragment.class.getSimpleName();

    @ViewById(R.id.image_list)
    public ListView imageListView;

    @ViewById(R.id.offline_message)
    public TextView offlineMessageTextView;

    @RestService
    public RestClient restClient;

    @OrmLiteDao(helper = DatabaseHelper.class, model = ImageItem.class)
    public ImageItemDAO imageItemDAO;

    @OrmLiteDao(helper = DatabaseHelper.class, model = User.class)
    public UserDAO userDAO;

    @Bean
    public ApplicationEventBus eventBus;

    @Bean
    public PhoneConnectivityUtil phoneConnectivityUtil;

    @Bean
    public ImageItemAdapter adapter;

    @Bean
    public UserMetrics userMetrics;

    public ImageService imageService;

    private List<ImageItem> imageItems;

    private int lastItemCheck = 0;

    public ImageListFragment() {
    }

    @Override
    public void onResume() {
        eventBus.register(this);
        super.onResume();
    }

    @Override
    public void onPause() {
        eventBus.unregister(this);
        super.onPause();
    }

    @AfterViews
    public void afterViews() {
        imageService = new ImageService(eventBus, phoneConnectivityUtil, restClient, imageItemDAO,
                                        userDAO);
        imageItems = new ArrayList<>();

        adapter.setImageItems(imageItems);
        imageListView.setAdapter(adapter);

        imageListView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScroll(AbsListView absListView, int firstVisibleIndex, int visibleItemCount, int totalCount) {
                final int lastItemIndex = firstVisibleIndex + visibleItemCount;
                if (lastItemIndex == totalCount) {
                    if (lastItemCheck != lastItemIndex) {
                        userMetrics.buildUserMetrics();
                        userMetrics.displayUserMetrics();
                        lastItemCheck = lastItemIndex;
                    }
                } else {
                    lastItemCheck = 0;
                }
            }

            @Override
            public void onScrollStateChanged(AbsListView absListView, int i) {
                // NOT USED
            }
        });

        loadData();
    }

    @Background
    public void loadData() {
        imageService.loadImageData();
    }

    @UiThread
    public void updateList() {
        adapter.setImageItems(imageItems);
        adapter.notifyDataSetChanged();
    }

    public void onEvent(ApplicationEvents.ImageItemDataLoadedEvent event) {
        offlineMessageTextView.setVisibility(View.GONE);

        imageItems = event.getData().get();
        updateList();
    }

    public void onEvent(ApplicationEvents.DeviceOfflineEvent event) {
        offlineMessageTextView.setVisibility(View.VISIBLE);
    }

    public void onEvent(ApplicationEvents.ImageDimensionsEvent event) throws SQLException {
        ImageItem imageItem = event.getData().get();
        updateImageItem(imageItem);
    }

    @Background
    public void updateImageItem(ImageItem imageItem) {
        try {
            imageItemDAO.update(imageItem);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
