package com.sbhachu.flingchallenge.event;

import com.sbhachu.flingchallenge.data.model.ImageItem;

import java.util.List;

/**
 * Created by sbhachu on 11/01/2015.
 */
public class ApplicationEvents {

    public static class DeviceOfflineEvent extends BaseEvent {
        public static DeviceOfflineEvent INSTANCE = new DeviceOfflineEvent();

        private DeviceOfflineEvent() {
        }
    }

    public static class ImageItemDataLoadedEvent extends BaseEvent<List<ImageItem>> {
        protected ImageItemDataLoadedEvent(List<ImageItem> data) {
            super(data);
        }
    }

    public static class ImageDimensionsEvent extends BaseEvent<ImageItem> {
        protected ImageDimensionsEvent(ImageItem data) {
            super(data);
        }
    }

    public static DeviceOfflineEvent phoneOfflineEvent() {
        return DeviceOfflineEvent.INSTANCE;
    }

    public static ImageItemDataLoadedEvent responseReceivedEvent(List<ImageItem> images) {
        return new ImageItemDataLoadedEvent(images);
    }

    public static ImageDimensionsEvent imageDimensionsEvent(ImageItem imageItem) {
        return new ImageDimensionsEvent(imageItem);
    }

}
