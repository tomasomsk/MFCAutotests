package com.luxoft.mfcautotests.model;

import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class MfcStatsItemForEquals {

    int number;
    String name;
    String dimension;
    int fillingType; // 1 (AUTO) Автоматизировано через сервисы интеграции, 2 (MANUAL) Вручную, 3 (OVERRIDE) Вручную правка администратором
    int dataSource; // an_data_source
    String value;
    String valueYear;
    String valuePrevYear;

    public int getNumber() {
        return number;
    }

    public String getName() {
        return name;
    }

    public String getDimension() {
        return dimension;
    }

    public int getFillingType() {
        return fillingType;
    }

    public int getDataSource() {
        return dataSource;
    }

    public String getValue() {
        return value;
    }

    public String getValueYear() {
        return valueYear;
    }

    public String getValuePrevYear() {
        return valuePrevYear;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDimension(String dimension) {
        this.dimension = dimension;
    }

    public void setFillingType(int fillingType) {
        this.fillingType = fillingType;
    }

    public void setDataSource(int dataSource) {
        this.dataSource = dataSource;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public void setValueYear(String valueYear) {
        this.valueYear = valueYear;
    }

    public void setValuePrevYear(String valuePrevYear) {
        this.valuePrevYear = valuePrevYear;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MfcStatsItemForEquals that = (MfcStatsItemForEquals) o;
        return number == that.number &&
                fillingType == that.fillingType &&
                dataSource == that.dataSource &&
                Objects.equals(name, that.name) &&
                Objects.equals(dimension, that.dimension) &&
                Objects.equals(value, that.value) &&
                Objects.equals(valueYear, that.valueYear) &&
                Objects.equals(valuePrevYear, that.valuePrevYear);
    }

    @Override
    public int hashCode() {

        return Objects.hash(number, name, dimension, fillingType, dataSource, value, valueYear, valuePrevYear);
    }
//
//    public boolean equalsAsUiObject(MfcStatsItemForEquals mfcStatsItem) {
//        boolean result;
//        result = (this.getName().equalsIgnoreCase(mfcStatsItem.getName()) &
//                this.getDimension().equalsIgnoreCase(mfcStatsItem.getDimension()) &
//                this.getFillingType() == mfcStatsItem.getFillingType() &
//                this.getDataSource() == mfcStatsItem.getDataSource() &
//                this.getValue().equalsIgnoreCase(mfcStatsItem.getValue()) &
//                this.getValueYear().equalsIgnoreCase(mfcStatsItem.getValueYear()) &
//                this.getValuePrevYear().equalsIgnoreCase(mfcStatsItem.getValuePrevYear()));
//
//        return result;
//    }
}
