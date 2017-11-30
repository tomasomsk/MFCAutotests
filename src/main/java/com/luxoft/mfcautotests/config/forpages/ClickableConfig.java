package com.luxoft.mfcautotests.config.forpages;

import org.springframework.stereotype.Component;

@Component
public class ClickableConfig {

    private String elementDisabledMarker;
    public static ClickableConfig defaultConfig = new ClickableConfig();

    public ClickableConfig() {
        elementDisabledMarker = "default";
    }

    public ClickableConfig(String elementDisabledMarker) {
        this.elementDisabledMarker = elementDisabledMarker;
    }

    public String getElementDisabledMarker() {
        return elementDisabledMarker;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ClickableConfig that = (ClickableConfig) o;

        return elementDisabledMarker != null ? elementDisabledMarker.equals(that.elementDisabledMarker) : that.elementDisabledMarker == null;
    }

    @Override
    public int hashCode() {
        return elementDisabledMarker != null ? elementDisabledMarker.hashCode() : 0;
    }
}
