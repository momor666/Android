package com.sbhachu.gojimochallenge.data.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import com.sbhachu.gojimochallenge.data.dao.QualificationSubjectDAO;

import java.io.Serializable;

/**
 * QualificationSubject database entity, can implement Parcelable interface if data is to be
 * passed via Bundle not needed for this use case.
 */
@DatabaseTable(daoClass = QualificationSubjectDAO.class, tableName = "qualification_subject")
public class QualificationSubject implements Serializable {

    @DatabaseField(id = true)
    private String id;

    @DatabaseField(foreign = true, columnName = "qualification_id")
    private Qualification qualification;

    @DatabaseField(foreign = true, columnName = "subject_id")
    private Subject subject;

    public QualificationSubject() {
    }

    public QualificationSubject(String id, Qualification qualification, Subject subject) {
        this.id = id;
        this.qualification = qualification;
        this.subject = subject;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Qualification getQualification() {
        return qualification;
    }

    public void setQualification(Qualification qualification) {
        this.qualification = qualification;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }
}
