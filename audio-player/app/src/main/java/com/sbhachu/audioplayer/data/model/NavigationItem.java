package com.sbhachu.audioplayer.data.model;

/**
 * Created by sbhachu on 22/12/2014.
 */
public class NavigationItem {

    String label;
    Integer count;

    public NavigationItem(String label, Integer count) {
        this.label = label;
        this.count = count;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("NavigationItem{");
        sb.append("label='").append(label).append('\'');
        sb.append(", count=").append(count);
        sb.append('}');
        return sb.toString();
    }
}
