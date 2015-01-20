package com.sbhachu.gojimochallenge.presentation.fragment;


import android.app.Fragment;
import android.os.Handler;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.sbhachu.gojimochallenge.R;
import com.sbhachu.gojimochallenge.data.DatabaseHelper;
import com.sbhachu.gojimochallenge.data.dao.QualificationDAO;
import com.sbhachu.gojimochallenge.data.dao.QualificationSubjectDAO;
import com.sbhachu.gojimochallenge.data.dao.SubjectDAO;
import com.sbhachu.gojimochallenge.data.model.Qualification;
import com.sbhachu.gojimochallenge.data.model.Subject;
import com.sbhachu.gojimochallenge.event.ApplicationEventBus;
import com.sbhachu.gojimochallenge.event.ApplicationEvents;
import com.sbhachu.gojimochallenge.network.RestClient;
import com.sbhachu.gojimochallenge.network.RestClientErrorHandler;
import com.sbhachu.gojimochallenge.presentation.adapter.SubjectListAdapter;
import com.sbhachu.gojimochallenge.service.DataService;
import com.sbhachu.gojimochallenge.util.PhoneConnectivityUtil;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.FragmentArg;
import org.androidannotations.annotations.OrmLiteDao;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.rest.RestService;

import java.util.ArrayList;
import java.util.List;

/**
 * SubjectListFragment.class
 * Represents the Subject List
 */
@EFragment(R.layout.fragment_subject_list)
public class SubjectListFragment extends Fragment {

    public static final String TAG = SubjectListFragment.class.getSimpleName();

    @FragmentArg
    public Qualification qualification;

    @ViewById(R.id.subject_list)
    public ListView subjectListView;

    @ViewById(R.id.no_subjects_message)
    public TextView noSubjectsTextView;

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
    public SubjectListAdapter adapter;

    private DataService dataService;

    private List<Subject> subjectList;


    public SubjectListFragment() {
        // Required empty public constructor
    }

    @Override
    public void onResume() {
        eventBus.registerSticky(this);
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

        subjectList = new ArrayList<>();
        adapter.setSubjects(subjectList);
        subjectListView.setAdapter(adapter);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                loadSubjectData();
            }
        }, 0);
    }

    @Background
    public void loadSubjectData() {
        dataService.loadSubjectData(qualification);
    }

    @UiThread
    public void updateList() {

        if (subjectList.isEmpty()) {
            noSubjectsTextView.setVisibility(View.VISIBLE);
            subjectListView.setVisibility(View.GONE);
            return;
        }

        adapter.setSubjects(subjectList);
        adapter.notifyDataSetChanged();
    }

    public void onEvent(ApplicationEvents.SubjectDataLoadedEvent event) {
        subjectList = event.getData().get();
        updateList();
    }
}
