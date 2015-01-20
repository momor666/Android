package com.sbhachu.gojimochallenge.event;

import com.sbhachu.gojimochallenge.data.model.Subject;

import java.util.List;

public class ApplicationEvents {

    public static class DeviceOfflineEvent extends BaseEvent {
        private DeviceOfflineEvent() {
        }
    }

    public static class QualificationDataLoadedEvent extends BaseEvent {
        protected QualificationDataLoadedEvent() {
        }
    }

    public static class RestClientErrorEvent extends BaseEvent<String> {
        protected RestClientErrorEvent(String data) {
            super(data);
        }
    }

    public static class SubjectDataLoadedEvent extends BaseEvent<List<Subject>> {
        protected SubjectDataLoadedEvent(List<Subject> data) {
            super(data);
        }
    }

    public static DeviceOfflineEvent phoneOfflineEvent() {
        return new DeviceOfflineEvent();
    }

    public static QualificationDataLoadedEvent qualificationDataLoadedEvent() {
        return new QualificationDataLoadedEvent();
    }

    public static RestClientErrorEvent errorResponseEvent(String error) {
        return new RestClientErrorEvent(error);
    }

    public static SubjectDataLoadedEvent subjectDataLoadedEvent(List<Subject> subjects) {
        return new SubjectDataLoadedEvent(subjects);
    }



}
