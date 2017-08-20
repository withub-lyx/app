package com.portal.auth.controller.form;

/**
 * Created by liuquan on 2016/12/19 18:13
 */
public class TreeItemState {
    private boolean selected;
    private boolean opened;

    public TreeItemState() {
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public boolean isOpened() {
        return opened;
    }

    public void setOpened(boolean opened) {
        this.opened = opened;
    }
}
