package com.application.bris.ikurma_nos_konsumer.model.menu;

/**
 * Created by idong on 12/06/2019.
 */

public class ListViewSubmenuHotprospek {
    private int icon;
    private String title;

    public ListViewSubmenuHotprospek(int icon, String title) {
        this.icon = icon;
        this.title = title;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
