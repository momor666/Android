package com.sbhachu.bbcnewsreader.presentation.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.widget.ListView;
import android.widget.Toast;

import com.sbhachu.bbcnewsreader.R;
import com.sbhachu.bbcnewsreader.application.BBCNewsReaderApplicationConfig;
import com.sbhachu.bbcnewsreader.data.model.Item;
import com.sbhachu.bbcnewsreader.data.model.RSS;
import com.sbhachu.bbcnewsreader.presentation.activity.MainActivity;
import com.sbhachu.bbcnewsreader.presentation.adapter.NewsListItemAdapter;
import com.sbhachu.bbcnewsreader.service.BBCNewsService_;
import com.sbhachu.bbcnewsreader.service.receiver.BBCNewsServiceReceiver;
import com.sbhachu.bbcnewsreader.util.Logger;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.FragmentArg;
import org.androidannotations.annotations.ItemClick;
import org.androidannotations.annotations.ViewById;

import java.util.List;

@EFragment(R.layout.fragment_news_list)
public class NewsListFragment extends Fragment implements BBCNewsServiceReceiver.BBCNewsServiceListener {

    private static final String TAG = NewsListFragment.class.getSimpleName();

    private static final String NEWS_CATEGORY = "category";

    @ViewById(R.id.news_list)
    public ListView newsList;

    @FragmentArg(NEWS_CATEGORY)
    public int category = -1;

    private InteractionListener interactionListener;

    @Bean
    public NewsListItemAdapter listItemAdapter;

    public static NewsListFragment_ newInstance(int category) {
        NewsListFragment_ fragment = new NewsListFragment_();
        Bundle args = new Bundle();
        args.putInt(NEWS_CATEGORY, category);
        fragment.setArguments(args);
        return fragment;
    }

    public NewsListFragment() {
    }

    @AfterViews
    public void afterViews() {

        interactionListener = (MainActivity) getActivity();

        newsList.setAdapter(listItemAdapter);

        BBCNewsServiceReceiver serviceReceiver = new BBCNewsServiceReceiver(new Handler());
        serviceReceiver.setListener(this);

        BBCNewsService_.intent(getActivity()).fetchNewsFeedData(serviceReceiver, category).start();
    }

    @Override
    public void onResultReceived(int resultCode, Bundle resultData) {
        switch (resultCode) {
            case 0:
                if (BBCNewsReaderApplicationConfig.DEBUG_MODE)
                    Logger.d(TAG, "News data request failed.");

                Toast.makeText(getActivity(), "News data request failed.", Toast.LENGTH_LONG).show();
                break;
            case 1:
                if (BBCNewsReaderApplicationConfig.DEBUG_MODE)
                    Logger.d(TAG, "Network error, please try again later.");

                Toast.makeText(getActivity(), "Network error, please try again later.", Toast.LENGTH_LONG).show();
                break;
            case 2:
                final RSS rss = (RSS) resultData.get("rss");

                if (BBCNewsReaderApplicationConfig.DEBUG_MODE)
                    Logger.d(TAG, rss.toString());

                updateList(rss.getChannel().getItems());
                break;
        }
    }

    public void updateList(List<Item> items) {
        listItemAdapter.setItems(items);
        listItemAdapter.notifyDataSetChanged();
    }

    @ItemClick(R.id.news_list)
    public void onListItemClick(Item item) {
        if (interactionListener != null)
            interactionListener.onNewsItemSelected(item);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        interactionListener = null;
    }

    public interface InteractionListener {
        public void onNewsItemSelected(Item item);
    }

}
