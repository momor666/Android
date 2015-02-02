package com.sbhachu.fanatixchallenge.event;

/**
 * Created by sbhachu on 11/01/2015.
 */
public class ApplicationEvents {

    public static class DeviceOfflineEvent extends BaseEvent {
        public static DeviceOfflineEvent INSTANCE = new DeviceOfflineEvent();

        private DeviceOfflineEvent() {
        }
    }

    public static class SelectedFriendCountEvent extends BaseEvent<Integer> {
        protected SelectedFriendCountEvent(Integer selectedFriendCount) {
            super(selectedFriendCount);
        }
    }

    public static DeviceOfflineEvent phoneOfflineEvent() {
        return DeviceOfflineEvent.INSTANCE;
    }

    public static SelectedFriendCountEvent selectedFriendCountEvent(Integer selectedFriendCount) {
        return new SelectedFriendCountEvent(selectedFriendCount);
    }

}
