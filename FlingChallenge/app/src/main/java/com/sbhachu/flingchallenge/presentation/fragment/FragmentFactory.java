package com.sbhachu.flingchallenge.presentation.fragment;

/**
 * FragmentFactory
 * Used to get instances of Fragments, is overkill here, but with larger applications can be useful
 */
public class FragmentFactory {

    public static final ImageListFragment buildImageListFragment() {
        return ImageListFragment_.builder().build();
    }

}
