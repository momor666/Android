package com.sbhachu.fanatixchallenge.data.model;

import java.io.Serializable;

/**
 * Base Class to introduce the 'selected' property
 */
public abstract class BaseFriend implements Serializable {

    private transient boolean selected;

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }
}
