package com.sbhachu.lollipop.bbcnewsreader.presentation.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.sbhachu.lollipop.bbcnewsreader.R;
import com.sbhachu.lollipop.bbcnewsreader.application.BBCNewsReaderApplicationConfig;
import com.sbhachu.lollipop.bbcnewsreader.data.model.Item;
import com.sbhachu.lollipop.bbcnewsreader.data.model.RSS;
import com.sbhachu.lollipop.bbcnewsreader.presentation.adapter.DataListAdapter;
import com.sbhachu.lollipop.bbcnewsreader.presentation.view.DataListItemView;
import com.sbhachu.lollipop.bbcnewsreader.service.BBCNewsService_;
import com.sbhachu.lollipop.bbcnewsreader.service.receiver.BBCNewsServiceReceiver;
import com.sbhachu.lollipop.bbcnewsreader.util.Logger;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.FragmentArg;
import org.androidannotations.annotations.ViewById;

import java.util.List;

@EFragment(R.layout.fragment_news_list)
public class NewsListFragment extends Fragment implements BBCNewsServiceReceiver.BBCNewsServiceListener {

    private static final String TAG = NewsListFragment.class.getSimpleName();

    private static final String NEWS_CATEGORY = "category";

    @ViewById(R.id.news_list_recycler)
    public RecyclerView recyclerView;

    @ViewById(R.id.progress_spinner)
    public ProgressBar progressBar;

    @FragmentArg(NEWS_CATEGORY)
    public int category = -1;

    private DataListAdapter dataListAdapter;

    private List<Item> itemList;

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

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setSmoothScrollbarEnabled(true);
        recyclerView.setLayoutManager(linearLayoutManager);
        dataListAdapter = new DataListAdapter(getActivity());


        recyclerView.setAdapter(dataListAdapter);

        recyclerView.setItemAnimator(new DefaultItemAnimator());

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                fetchData();
            }
        }, 500);

        dataListAdapter.setItemClickListener(new DataListAdapter.ItemClickListener() {
            @Override
            public void onClick(DataListItemView view, int position) {
                Item item = itemList.get(position);

                Fragment fragment = NewsWebFragment_.newInstance(item.getLink());

                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();

                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                fragmentTransaction.replace(R.id.main_container, fragment)
                        .addToBackStack("web_view")
                        .commit();
            }
        });
    }

    public void fetchData() {
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

                itemList = rss.getChannel().getItems();
                renderData();
                break;
        }
    }

    public void renderData() {
        if (itemList != null) {

            progressBar.setVisibility(View.GONE);

            dataListAdapter.setItems(itemList);
            dataListAdapter.notifyDataSetChanged();

        }
    }
}
