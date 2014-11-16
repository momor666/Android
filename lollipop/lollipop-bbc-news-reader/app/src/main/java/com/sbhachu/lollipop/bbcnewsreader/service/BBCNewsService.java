package com.sbhachu.lollipop.bbcnewsreader.service;

import android.app.IntentService;
import android.content.Intent;
import android.os.Bundle;
import android.os.ResultReceiver;

import com.sbhachu.lollipop.bbcnewsreader.data.model.RSS;
import com.sbhachu.lollipop.bbcnewsreader.network.BBCNewsRestTemplate;
import com.sbhachu.lollipop.bbcnewsreader.util.NetworkStateUtil;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EIntentService;
import org.androidannotations.annotations.ServiceAction;
import org.androidannotations.annotations.rest.RestService;

/**
 * Created by sbhachu on 28/10/2014.
 */
@EIntentService
public class BBCNewsService extends IntentService {

    private static final String TAG = BBCNewsService.class.getSimpleName();

    @Bean
    public NetworkStateUtil networkStateUtil;

    @RestService
    public BBCNewsRestTemplate newsRestTemplate;

    public BBCNewsService() {
        super("BBCNewsService");
    }

    @ServiceAction
    public void fetchNewsFeedData(ResultReceiver result, int type) {
        if (networkStateUtil.isNetworkAvailable()) {

            RSS rss = null;

            switch (type) {
                case 0:
                    rss = newsRestTemplate.getTopStories();
                    break;
                case 1:
                    rss = newsRestTemplate.getWorldNewsStories();
                    break;
                case 2:
                    rss = newsRestTemplate.getUKNewsStories();
                    break;
                case 3:
                    rss = newsRestTemplate.getBusinessNewsStories();
                    break;
                case 4:
                    rss = newsRestTemplate.getPoliticsNewsStories();
                    break;
                case 5:
                    rss = newsRestTemplate.getHealthNewsStories();
                    break;
                case 6:
                    rss = newsRestTemplate.getEducationNewsStories();
                    break;
                case 7:
                    rss = newsRestTemplate.getScienceAndEnvironmentNewsStories();
                    break;
                case 8:
                    rss = newsRestTemplate.getTechnologyNewsStories();
                    break;
                case 9:
                    rss = newsRestTemplate.getEntertainmentAndArtsNewsStories();
                    break;
                default:
                    break;
            }

            if (rss != null) {
                Bundle bundle = new Bundle();
                bundle.putParcelable("rss", rss);

                result.send(2, bundle);
            } else {
                result.send(0, null);
            }
        } else {
            result.send(1, null);
        }
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        //TODO: Nothing Here
    }
}
