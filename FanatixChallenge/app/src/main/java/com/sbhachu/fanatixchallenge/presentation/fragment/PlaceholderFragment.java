package com.sbhachu.fanatixchallenge.presentation.fragment;


import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.sbhachu.fanatixchallenge.R;
import com.sbhachu.fanatixchallenge.application.ApplicationConfiguration;
import com.sbhachu.fanatixchallenge.presentation.fragment.factory.FragmentFactory;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.OptionsItem;
import org.androidannotations.annotations.OptionsMenu;
import org.androidannotations.annotations.ViewById;

@EFragment(R.layout.fragment_placeholder)
@OptionsMenu(R.menu.menu_placeholder)
public class PlaceholderFragment extends Fragment {

    public static final String TAG = PlaceholderFragment.class.getSimpleName();

    private boolean isDebug;

    @ViewById(R.id.item_id_edit_text)
    public EditText itemIdEditText;

    public PlaceholderFragment() {
        // Required empty public constructor
    }

    @AfterViews
    public void afterViews() {
        setHasOptionsMenu(true);
        isDebug = false;
    }

    @OptionsItem(R.id.action_debug)
    public void toggleDebugMode() {
        isDebug = !isDebug;

        if (isDebug) {
            itemIdEditText.setVisibility(View.VISIBLE);
        } else {
            itemIdEditText.setVisibility(View.INVISIBLE);
        }
    }

    @Click(R.id.huddle_button)
    public void handleHuddleButtonClick() {

        String itemIdText = itemIdEditText.getText().toString();
        int itemId = -1;

        if (!itemIdText.isEmpty()) {
            itemId = Integer.parseInt(itemIdText);
        } else {
            itemId = ApplicationConfiguration.ITEM_ID;
        }

        // close keyboard
        InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(
                Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(itemIdEditText.getWindowToken(), 0);

        FragmentTransaction ft = getActivity().getFragmentManager().beginTransaction();
        ft.setCustomAnimations(R.animator.slide_fragment_horizontal_right_in,
                               R.animator.slide_fragment_horizontal_right_out,
                               R.animator.slide_fragment_horizontal_right_in,
                               R.animator.slide_fragment_horizontal_right_out);
        ft.addToBackStack("friendlist_fragment");
        ft.replace(R.id.fragment_container, FragmentFactory.buildFriendListFragment(itemId),
                   FriendListFragment.TAG);
        ft.commit();
    }

}
