package com.luxoft.mfcautotests.model;

import org.openqa.selenium.Point;

public class BoxWithInsuranceCompany {
    private String name;
    private Point location;

    public String getName() {
        return name;
    }

    public Point getLocation() {
        return location;
    }

    public BoxWithInsuranceCompany setName(String name) {
        this.name = name;
        return this;
    }

    public void setLocation(Point location) {
        this.location = location;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BoxWithInsuranceCompany that = (BoxWithInsuranceCompany) o;

        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        return location != null ? location.equals(that.location) : that.location == null;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (location != null ? location.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "BoxWithInsuranceCompany{" +
                "name='" + name + '\'' +
                ", location=" + location +
                '}';
    }
}
