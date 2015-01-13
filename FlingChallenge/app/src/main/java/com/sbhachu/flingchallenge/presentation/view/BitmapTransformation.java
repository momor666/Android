package com.sbhachu.flingchallenge.presentation.view;

/**
 * Created by sbhachu on 12/01/2015.
 */

import android.graphics.Bitmap;

import com.sbhachu.flingchallenge.data.model.ImageItem;
import com.sbhachu.flingchallenge.event.ApplicationEventBus;
import com.sbhachu.flingchallenge.event.ApplicationEvents;
import com.squareup.picasso.Transformation;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EBean;

/**
 * BitmapTransformation
 * This class was only created for the purpose of accessing the source image width and height.
 * The Picasso library can handle the image resizing out of the box, but abstracts the source dimensions.
 *
 * When an image is downloaded an event is dispatched by this class with the source dimensions
 * appended to the ImageItem object
 */
@EBean
public class BitmapTransformation implements Transformation {

    @Bean
    public ApplicationEventBus eventBus;

    private static final int MAX_WIDTH = 400;
    private static final int MAX_HEIGHT = 400;
    private ImageItem imageItem;

    public BitmapTransformation() {
    }

    @Override
    public Bitmap transform(Bitmap source) {

        if (imageItem != null) {
            imageItem.setSourceWidth(source.getWidth());
            imageItem.setSourceHeight(source.getHeight());

            eventBus.post(ApplicationEvents.imageDimensionsEvent(imageItem));
        }

        Bitmap result = Bitmap.createScaledBitmap(source, MAX_WIDTH, MAX_HEIGHT, false);
        if (result != source) {
            source.recycle();
        }
        return result;
    }

    @Override
    public String key() {
        return MAX_WIDTH + "x" + MAX_HEIGHT;
    }

    public void setImageItem(ImageItem imageItem) {
        this.imageItem = imageItem;
    }

    public ImageItem getImageItem() {
        return imageItem;
    }
};
