package com.sbhachu.gojimochallenge.presentation.fragment;

import com.sbhachu.gojimochallenge.data.model.Qualification;

/**
 * FragmentFactory.class
 * Used to get instances of Fragments, is overkill here, but with larger applications can be useful
 */
public class FragmentFactory {

    public static QualificationListFragment buildQualificationListFragment() {
        return QualificationListFragment_.builder().build();
    }

    public static SubjectListFragment buildSubjectListFragment(Qualification qualification) {
        return SubjectListFragment_.builder().qualification(qualification).build();
    }
}
