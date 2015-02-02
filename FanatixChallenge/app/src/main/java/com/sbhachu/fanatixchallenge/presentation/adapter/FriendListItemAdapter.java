package com.sbhachu.fanatixchallenge.presentation.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;

import com.sbhachu.fanatixchallenge.R;
import com.sbhachu.fanatixchallenge.data.model.Friend;
import com.sbhachu.fanatixchallenge.event.ApplicationEventBus;
import com.sbhachu.fanatixchallenge.event.ApplicationEvents;
import com.sbhachu.fanatixchallenge.presentation.view.FriendListSectionHeaderView;
import com.sbhachu.fanatixchallenge.presentation.view.FriendListSectionHeaderView_;
import com.sbhachu.fanatixchallenge.presentation.view.FriendListItemView;
import com.sbhachu.fanatixchallenge.presentation.view.FriendListItemView_;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EBean;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

@EBean
public class FriendListItemAdapter extends BaseAdapter {

    private static final int TYPE_HEADER = 0;
    private static final int TYPE_ITEM = 1;

    public Context context;
    private LayoutInflater layoutInflater;

    @Bean
    public ApplicationEventBus eventBus;

    private List<Object> friends = new ArrayList<>();
    private TreeSet<Integer> sectionHeader = new TreeSet<>();

    public FriendListItemAdapter(Context context) {
        this.context = context;
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return friends.size();
    }

    @Override
    public Object getItem(int position) {
        return friends.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        int rowType = getItemViewType(position);

        switch (rowType) {
            case TYPE_HEADER:
                FriendListSectionHeaderView friendListSectionHeaderView;
                if (convertView == null) {
                    friendListSectionHeaderView = FriendListSectionHeaderView_.build(context);
                } else {
                    friendListSectionHeaderView = (FriendListSectionHeaderView) convertView;
                }

                String category = (String) friends.get(position);

                if (category != null) {
                    friendListSectionHeaderView.bind(category);
                }

                return friendListSectionHeaderView;

            case TYPE_ITEM:
                FriendListItemView friendListItemView;
                if (convertView == null) {
                    friendListItemView = FriendListItemView_.build(context);
                } else {
                    friendListItemView = (FriendListItemView) convertView;
                }

                final Friend friend = (Friend) friends.get(position);
                if (friend != null) {
                    friendListItemView.bind(friend);
                }

                friendListItemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        FriendListItemView itemView = (FriendListItemView) view;
                        CheckBox checkBox = (CheckBox) itemView.findViewById(R.id.friend_item_checkbox);

                        if (checkBox.isChecked())
                            checkBox.setChecked(false);
                        else
                            checkBox.setChecked(true);


                        friend.setSelected(checkBox.isChecked());

                        eventBus.getDefault().post(ApplicationEvents.selectedFriendCountEvent(
                                getSelectedFriendCount()));
                    }
                });

                return friendListItemView;

            default:
                return null;
        }
    }

    public void addItem(final Friend item) {
        friends.add(item);
        notifyDataSetChanged();
    }

    public void addSectionHeaderItem(final String item) {
        friends.add(item);
        sectionHeader.add(friends.size() - 1);
        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {
        return sectionHeader.contains(position) ? TYPE_HEADER : TYPE_ITEM;
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }

    private int getSelectedFriendCount() {
        int count = 0;
        for (Object item : friends) {
            if (item instanceof Friend) {
                Friend friend = (Friend) item;
                if (friend.isSelected()) {
                    count++;
                }
            }
        }
        return count;
    }

    public List<Friend> getSelectedFriends() {
        final List<Friend> selectedFriends = new ArrayList<>();
        for (Object item : friends) {
            if (item instanceof Friend) {
                Friend friend = (Friend) item;
                if (friend.isSelected()) {
                    selectedFriends.add(friend);
                }
            }
        }
        return selectedFriends;
    }
}
