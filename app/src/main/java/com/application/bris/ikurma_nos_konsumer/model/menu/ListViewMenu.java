package com.application.bris.ikurma_nos_konsumer.model.menu;

/**
 * Created by PID on 28/04/19.
 */

public class ListViewMenu {
    private int icon;
    private String title;
    private int jmlPipeline;
    private int jmlHotprospek;
    private int jmlApproved;
    private int jmlRejected;

    public ListViewMenu(int icon, String title, int jmlPipeline, int jmlHotprospek, int jmlApproved, int jmlRejected) {
        this.icon = icon;
        this.title = title;
        this.jmlPipeline = jmlPipeline;
        this.jmlHotprospek = jmlHotprospek;
        this.jmlApproved = jmlApproved;
        this.jmlRejected = jmlRejected;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public int getJmlPipeline() {
        return jmlPipeline;
    }

    public void setJmlPipeline(int jmlPipeline) {
        this.jmlPipeline = jmlPipeline;
    }

    public int getJmlHotprospek() {
        return jmlHotprospek;
    }

    public void setJmlHotprospek(int jmlHotprospek) {
        this.jmlHotprospek = jmlHotprospek;
    }

    public int getJmlApproved() {
        return jmlApproved;
    }

    public void setJmlApproved(int jmlApproved) {
        this.jmlApproved = jmlApproved;
    }

    public int getJmlRejected() {
        return jmlRejected;
    }

    public void setJmlRejected(int jmlRejected) {
        this.jmlRejected = jmlRejected;
    }
}
