package com.sbhachu.fanatixchallenge.presentation.fragment;


import android.app.Fragment;
import android.app.FragmentTransaction;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.sbhachu.fanatixchallenge.R;
import com.sbhachu.fanatixchallenge.data.model.Friend;
import com.sbhachu.fanatixchallenge.event.ApplicationEventBus;
import com.sbhachu.fanatixchallenge.event.ApplicationEvents;
import com.sbhachu.fanatixchallenge.presentation.adapter.FriendListItemAdapter;
import com.sbhachu.fanatixchallenge.presentation.fragment.factory.FragmentFactory;
import com.sbhachu.fanatixchallenge.service.FanatixDataService;
import com.sbhachu.fanatixchallenge.util.PhoneConnectivityUtil;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.FragmentArg;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import static com.sbhachu.fanatixchallenge.application.ApplicationConfiguration.APP_ID;
import static com.sbhachu.fanatixchallenge.application.ApplicationConfiguration.APP_PLATFORM;
import static com.sbhachu.fanatixchallenge.application.ApplicationConfiguration.APP_VERSION;
import static com.sbhachu.fanatixchallenge.application.ApplicationConfiguration.AUTH_FANATIX_ID;
import static com.sbhachu.fanatixchallenge.application.ApplicationConfiguration.AUTH_FANATIX_TOKEN;
import static com.sbhachu.fanatixchallenge.application.ApplicationConfiguration.INCLUDE_ALL;

@EFragment(R.layout.fragment_friendlist)
public class FriendListFragment extends Fragment {

    public static final String TAG = FriendListFragment.class.getSimpleName();

    @ViewById(R.id.friends_list_view)
    public ListView listView;

    @ViewById(R.id.friend_list_progressbar)
    public ProgressBar progressBar;

    @FragmentArg
    public int itemId;

    @Bean
    public FanatixDataService fanatixDataService;

    @Bean
    public FriendListItemAdapter adapter;

    @Bean
    public ApplicationEventBus eventBus;

    @Bean
    public PhoneConnectivityUtil phoneConnectivityUtil;

    private Map<String, Friend[]> friendMap;

    private Menu menu;

    public FriendListFragment() {
        // Required empty public constructor
    }

    @Override
    public void onResume() {
        eventBus.getDefault().register(this);
        super.onResume();
    }

    @Override
    public void onPause() {
        eventBus.getDefault().unregister(this);
        super.onPause();
    }

    @AfterViews
    public void afterViews() {
        setHasOptionsMenu(true);
        listView.setAdapter(adapter);

        if (friendMap == null)
            loadData();
    }

    @Background
    public void loadData() {
        progressBar.setVisibility(View.VISIBLE);

        if (phoneConnectivityUtil.isOnline()) {
            friendMap = fanatixDataService.getRestTemplate().fetchFriendList(APP_ID, APP_VERSION,
                                                                             APP_PLATFORM,
                                                                             INCLUDE_ALL,
                                                                             itemId,
                                                                             AUTH_FANATIX_ID,
                                                                             AUTH_FANATIX_TOKEN);
            updateList();
        } else {
            // TODO: show no connectivity error message
        }
    }

    @UiThread(delay = 500)
    public void updateList() {
        if (friendMap != null) {
            Iterator i = friendMap.entrySet().iterator();

            while (i.hasNext()) {
                Map.Entry<String, Friend[]> category = (Map.Entry<String, Friend[]>) i.next();
                adapter.addSectionHeaderItem(category.getKey());

                for (Friend friend : category.getValue()) {
                    adapter.addItem(friend);
                }
            }
        }
        progressBar.setVisibility(View.GONE);
    }

    // create a huddle based on selected friends, non primary users should be invited
    public void createHuddle() {
        List<Friend> selectedFriends = adapter.getSelectedFriends();

        if (!selectedFriends.isEmpty()) {

            Boolean containsNonPrimary = false;

            for (Friend friend : selectedFriends) {
                if (!friend.isPrimary()) {
                    containsNonPrimary = true;
                }
            }

            Fragment fragment;
            if (containsNonPrimary) {
                fragment = FragmentFactory.buildHuddleInvitationFragment(
                        (ArrayList<Friend>) selectedFriends);
            } else {
                fragment = FragmentFactory.buildHuddleParticipantListFragment();
            }

            FragmentTransaction ft = getActivity().getFragmentManager().beginTransaction();
            ft.setCustomAnimations(R.animator.slide_fragment_horizontal_right_in,
                                   R.animator.slide_fragment_horizontal_right_out,
                                   R.animator.slide_fragment_horizontal_right_in,
                                   R.animator.slide_fragment_horizontal_right_out);
            ft.addToBackStack("friendlist_fragment");
            ft.replace(R.id.fragment_container, fragment);
            ft.commit();
        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(com.sbhachu.fanatixchallenge.R.menu.menu_friend_list, menu);
        this.menu = menu;

        MenuItem menuItem = menu.findItem(R.id.action_create_huddle);
        menuItem.setEnabled(adapter.getSelectedFriends().size() > 0);

        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        boolean handled = super.onOptionsItemSelected(item);
        if (handled) {
            return true;
        }
        int itemId_ = item.getItemId();
        if (itemId_ == com.sbhachu.fanatixchallenge.R.id.action_create_huddle) {
            createHuddle();
            return true;
        }
        return false;
    }

    // event listener - used to enable/disable the create huddle action item
    public void onEvent(ApplicationEvents.SelectedFriendCountEvent event) {
        MenuItem menuItem = menu.findItem(R.id.action_create_huddle);
        int count = event.getData().get();
        menuItem.setEnabled(count > 0);
    }

}
