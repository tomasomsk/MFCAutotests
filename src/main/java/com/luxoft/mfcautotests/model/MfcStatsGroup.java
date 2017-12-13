package com.luxoft.mfcautotests.model;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class MfcStatsGroup {

    String name;
    List<MfcStatsItem> mfcStatsItems = new ArrayList<>();

    public String getName() {
        return name;
    }

    public List<MfcStatsItem> getMfcStatsItems() {
        return mfcStatsItems;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMfcStatsItems(List<MfcStatsItem> mfcStatsItems) {
        this.mfcStatsItems = mfcStatsItems;
    }

    public void addMfcStatsItem(MfcStatsItem mfcStatsItem) {
        this.mfcStatsItems.add(mfcStatsItem);
    }
}
