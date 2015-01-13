package com.sbhachu.flingchallenge.presentation.view;

import android.content.Context;
import android.net.Uri;
import android.os.Handler;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.sbhachu.flingchallenge.R;
import com.sbhachu.flingchallenge.data.model.ImageItem;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

/**
 * Created by sbhachu on 08/01/2015.
 */
@EViewGroup(R.layout.view_image_item)
public class ImageItemAdapterView extends RelativeLayout {

    @ViewById(R.id.image_view)
    public ImageView imageView;

    @ViewById(R.id.image_title)
    public TextView imageTitleTextView;

    @Bean
    public BitmapTransformation bitmapTransformation;

    public ImageItemAdapterView(Context context) {
        super(context);
    }

    public void bind(final ImageItem imageItem) {

        bitmapTransformation.setImageItem(imageItem);

        imageView.setAlpha(0.0f);
        imageView.setScaleX(0.1f);
        imageView.setScaleY(0.1f);

        imageTitleTextView.setText(imageItem.getId() + ". " + imageItem.getTitle());

        Picasso.with(getContext())
                .load(Uri.parse("http://challenge.superfling.com/photos/" + imageItem.getImageId()))
                .transform(bitmapTransformation)
                .noFade().noPlaceholder()
                .into(imageView, new Callback.EmptyCallback() {
                    @Override
                    public void onSuccess() {
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {

                                imageView.animate()
                                        .scaleX(1.0f)
                                        .scaleY(1.0f)
                                        .alpha(1.0f)
                                        .setDuration(500)
                                        .start();
                            }
                        }, 10);
                    }
                });


    }
}
