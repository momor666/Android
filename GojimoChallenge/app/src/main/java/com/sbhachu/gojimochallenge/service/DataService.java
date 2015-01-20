package com.sbhachu.gojimochallenge.service;

import com.j256.ormlite.stmt.PreparedQuery;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.stmt.SelectArg;
import com.sbhachu.gojimochallenge.data.QualificationList;
import com.sbhachu.gojimochallenge.data.dao.QualificationDAO;
import com.sbhachu.gojimochallenge.data.dao.QualificationSubjectDAO;
import com.sbhachu.gojimochallenge.data.dao.SubjectDAO;
import com.sbhachu.gojimochallenge.data.model.Qualification;
import com.sbhachu.gojimochallenge.data.model.QualificationSubject;
import com.sbhachu.gojimochallenge.data.model.Subject;
import com.sbhachu.gojimochallenge.event.ApplicationEventBus;
import com.sbhachu.gojimochallenge.event.ApplicationEvents;
import com.sbhachu.gojimochallenge.network.RestClient;
import com.sbhachu.gojimochallenge.util.PhoneConnectivityUtil;

import org.springframework.http.ResponseEntity;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * DataService.class
 * Loads remote data and update local SQLite database
 */
public class DataService {

    private ApplicationEventBus applicationEventBus;
    private PhoneConnectivityUtil phoneConnectivityUtil;
    private RestClient restClient;
    private QualificationDAO qualificationDAO;
    private SubjectDAO subjectDAO;
    private QualificationSubjectDAO qualificationSubjectDAO;


    public DataService(ApplicationEventBus applicationEventBus,
                       PhoneConnectivityUtil phoneConnectivityUtil,
                       RestClient restClient,
                       QualificationDAO qualificationDAO,
                       SubjectDAO subjectDAO,
                       QualificationSubjectDAO qualificationSubjectDAO) {
        this.applicationEventBus = applicationEventBus;
        this.phoneConnectivityUtil = phoneConnectivityUtil;
        this.restClient = restClient;
        this.qualificationDAO = qualificationDAO;
        this.subjectDAO = subjectDAO;
        this.qualificationSubjectDAO = qualificationSubjectDAO;
    }

    public void loadQualificationData() {
        if (!phoneConnectivityUtil.isOnline()) {
            applicationEventBus.post(ApplicationEvents.phoneOfflineEvent());
            return;
        }

        ResponseEntity<QualificationList> response = restClient.fetchQualifications();

        /**
         * TODO - Logic to reload data when data is stale
         * The Cache-Control header always returns "must-revalidate", need more information about implementation to implement this.
         */

        QualificationList qualificationList = response.getBody();

        try {
            if (qualificationList != null && !qualificationList.isEmpty()) {

                for (Qualification qualification : qualificationList) {

                    qualificationDAO.createOrUpdate(qualification);

                    if (qualification.getSubjects() != null && !qualification.getSubjects().isEmpty()) {
                        List<Subject> subjects = (List<Subject>) qualification.getSubjects();
                        for (Subject subject : subjects) {
                            subjectDAO.createOrUpdate(subject);

                            String key = UUID.nameUUIDFromBytes(
                                    (qualification.getId() + subject.getId()).getBytes()).toString();

                            QualificationSubject qualificationSubject = new QualificationSubject(
                                    key, qualification, subject);
                            qualificationSubjectDAO.createOrUpdate(qualificationSubject);
                        }
                    }
                }

                applicationEventBus.post(ApplicationEvents.qualificationDataLoadedEvent());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void loadSubjectData(Qualification qualification) {

        List<Subject> subjects = new ArrayList<>();

        try {
            QueryBuilder<QualificationSubject, String> qualificationSubjectQueryBuilder = qualificationSubjectDAO.queryBuilder();
            qualificationSubjectQueryBuilder.selectColumns("subject_id");
            qualificationSubjectQueryBuilder.where().eq("qualification_id", new SelectArg());

            QueryBuilder<Subject, String> subjectQueryBuilder = subjectDAO.queryBuilder();
            subjectQueryBuilder.where().in("id", qualificationSubjectQueryBuilder);
            subjectQueryBuilder.orderBy("title", true);
            PreparedQuery<Subject> preparedQuery = subjectQueryBuilder.prepare();

            preparedQuery.setArgumentHolderValue(0, qualification);
            subjects = subjectDAO.query(preparedQuery);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        applicationEventBus.postSticky(ApplicationEvents.subjectDataLoadedEvent(subjects));
    }
}
