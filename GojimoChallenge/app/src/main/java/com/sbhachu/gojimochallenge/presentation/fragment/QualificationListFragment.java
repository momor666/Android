package com.sbhachu.gojimochallenge.presentation.fragment;


import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;

import com.sbhachu.gojimochallenge.R;
import com.sbhachu.gojimochallenge.data.DatabaseHelper;
import com.sbhachu.gojimochallenge.data.dao.QualificationDAO;
import com.sbhachu.gojimochallenge.data.dao.QualificationSubjectDAO;
import com.sbhachu.gojimochallenge.data.dao.SubjectDAO;
import com.sbhachu.gojimochallenge.data.model.Qualification;
import com.sbhachu.gojimochallenge.event.ApplicationEventBus;
import com.sbhachu.gojimochallenge.event.ApplicationEvents;
import com.sbhachu.gojimochallenge.network.RestClient;
import com.sbhachu.gojimochallenge.network.RestClientErrorHandler;
import com.sbhachu.gojimochallenge.presentation.adapter.QualificationListAdapter;
import com.sbhachu.gojimochallenge.service.DataService;
import com.sbhachu.gojimochallenge.util.PhoneConnectivityUtil;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ItemClick;
import org.androidannotations.annotations.OrmLiteDao;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.rest.RestService;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener;
import static com.sbhachu.gojimochallenge.presentation.fragment.FragmentFactory.buildSubjectListFragment;

/**
 * QualificationListFragment.class
 * Represents the qualification list
 */
@EFragment(R.layout.fragment_qualification_list)
public class QualificationListFragment extends Fragment {

    public static final String TAG = QualificationListFragment.class.getSimpleName();

    @ViewById(R.id.pull_to_refresh)
    public SwipeRefreshLayout swipeRefreshLayout;

    @ViewById(R.id.qualification_list)
    public ListView qualificationListView;

    @ViewById(R.id.offline_message)
    public TextView offlineMessageTextView;

    @Bean
    public ApplicationEventBus eventBus;

    @RestService
    public RestClient restClient;

    @Bean
    public PhoneConnectivityUtil phoneConnectivityUtil;

    @Bean
    public RestClientErrorHandler errorHandler;

    @OrmLiteDao(helper = DatabaseHelper.class)
    public QualificationSubjectDAO qualificationSubjectDAO;

    @OrmLiteDao(helper = DatabaseHelper.class)
    public QualificationDAO qualificationDAO;

    @OrmLiteDao(helper = DatabaseHelper.class)
    public SubjectDAO subjectDAO;


    @Bean
    public QualificationListAdapter adapter;

    private DataService dataService;

    private List<Qualification> qualificationList;


    public QualificationListFragment() {
        // Required empty public constructor
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
        restClient.setRestErrorHandler(errorHandler);

        dataService = new DataService(eventBus, phoneConnectivityUtil, restClient,
                                      qualificationDAO, subjectDAO, qualificationSubjectDAO);

        if (swipeRefreshLayout != null) {
            swipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary,
                                                       R.color.colorPrimaryDark);
            swipeRefreshLayout.setOnRefreshListener(qualificationListRefreshListener);
        }

        qualificationList = new ArrayList<>();
        adapter.setQualificationList(qualificationList);
        qualificationListView.setAdapter(adapter);

        loadLocalData();
    }

    @Background
    public void loadLocalData() {
        try {
            qualificationList = qualificationDAO.queryForAll();
            if (qualificationDAO.countOf() > 0) {
                updateList();
            } else {
                loadRemoteData();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Background
    public void loadRemoteData() {
        dataService.loadQualificationData();
    }

    public void onEvent(Object object) {
        if (object == null)
            return;

        if (object instanceof ApplicationEvents.DeviceOfflineEvent) {
            Log.e(TAG, "Device is offline");
        } else if (object instanceof ApplicationEvents.QualificationDataLoadedEvent) {
            try {
                qualificationList = qualificationDAO.queryForAll();
                updateList();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else if (object instanceof ApplicationEvents.RestClientErrorEvent) {
            Log.e(TAG, ((ApplicationEvents.RestClientErrorEvent) object).getData().get());
        }
    }

    @UiThread
    public void updateList() {
        adapter.setQualificationList(qualificationList);
        adapter.notifyDataSetChanged();

        if (swipeRefreshLayout.isRefreshing()) {
            swipeRefreshLayout.setRefreshing(false);
        }
    }

    @ItemClick(R.id.qualification_list)
    public void qualificationItemClick(final Qualification qualification) {
        Log.i(TAG, qualification.getName());


        FragmentTransaction ft = getActivity().getFragmentManager().beginTransaction();
        ft.setCustomAnimations(R.animator.slide_fragment_horizontal_right_in,
                               R.animator.slide_fragment_horizontal_right_out,
                               R.animator.slide_fragment_horizontal_right_in,
                               R.animator.slide_fragment_horizontal_right_out);
        ft.addToBackStack("subject_fragment");
        ft.replace(R.id.fragment_container, buildSubjectListFragment(qualification),
                   SubjectListFragment.TAG);
        ft.commit();
    }

    private OnRefreshListener qualificationListRefreshListener = new OnRefreshListener() {
        @Override
        public void onRefresh() {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    loadRemoteData();
                }
            }, 2000);
        }
    };
}
