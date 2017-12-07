package com.luxoft.mfcautotests.config.forpages;

import org.springframework.stereotype.Component;

@Component
public class ElementToFindInList {

    private String type;
    private int intValue;

    public ElementToFindInList() {
    }

    public ElementToFindInList(int intValue) {
        this.intValue = intValue;
        type = "int";
    }

    public int getIntValue() {
        return intValue;
    }

    public String getType() {
        return type;
    }
}
