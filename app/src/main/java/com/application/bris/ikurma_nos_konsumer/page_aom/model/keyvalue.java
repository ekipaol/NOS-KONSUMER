package com.application.bris.ikurma_nos_konsumer.page_aom.model;

/**
 * Created by idong on 08/05/2019.
 */

public class keyvalue {
    private String name;
    private String value;

    public keyvalue(String name, String value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
