package com.luxoft.mfcautotests.model;

import org.springframework.stereotype.Component;

@Component
public class MfcStatsItem {

    int number;
    String name;
    String calculationLogicAccountingPeriod;
    String calculationLogicCurrentYear;
    String calculationLogicLastYear;
    String dimension;
    int fillingType; // 1 (AUTO) Автоматизировано через сервисы интеграции, 2 (MANUAL) Вручную, 3 (OVERRIDE) Вручную правка администратором
    int dataSource; // an_data_source
    int itemCode; //1 - только ежедневный, 2 - ежедневный и ежемесячный, 3 - только ежемесячный
    String isDashboard; // Y, N
    String itemIdPeriod;
    String itemIdYear;
    String itemIdPrevYear;
    String parameter;
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

    public int getItemCode() {
        return itemCode;
    }

    public String getIsDashboard() {
        return isDashboard;
    }

    public String getItemIdPeriod() {
        return itemIdPeriod;
    }

    public String getItemIdYear() {
        return itemIdYear;
    }

    public String getItemIdPrevYear() {
        return itemIdPrevYear;
    }

    public String getParameter() {
        return parameter;
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

    public void setCalculationLogicAccountingPeriod(String calculationLogicAccountingPeriod) {
        this.calculationLogicAccountingPeriod = calculationLogicAccountingPeriod;
    }

    public void setCalculationLogicCurrentYear(String calculationLogicCurrentYear) {
        this.calculationLogicCurrentYear = calculationLogicCurrentYear;
    }

    public void setCalculationLogicLastYear(String calculationLogicLastYear) {
        this.calculationLogicLastYear = calculationLogicLastYear;
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

    public void setItemCode(int itemCode) {
        this.itemCode = itemCode;
    }

    public void setIsDashboard(String isDashboard) {
        this.isDashboard = isDashboard;
    }

    public void setItemIdPeriod(String itemIdPeriod) {
        this.itemIdPeriod = itemIdPeriod;
    }

    public void setItemIdYear(String itemIdYear) {
        this.itemIdYear = itemIdYear;
    }

    public void setItemIdPrevYear(String itemIdPrevYear) {
        this.itemIdPrevYear = itemIdPrevYear;
    }

    public void setParameter(String parameter) {
        this.parameter = parameter;
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

//    public MfcStatsItemUi transformToMfcStatsItemUi() {
//        MfcStatsItemUi result = new MfcStatsItemUi();
//        result.setNumber(this.getNumber());
//        result.setName(this.getName());
//        result.setDimension(this.getDimension());
//        result.setFillingType(this.getFillingType());
//        result.setDataSource(this.getDataSource());
//        result.setValue(this.getValue());
//        result.setValueYear(this.getValueYear());
//        result.setValuePrevYear(this.getValuePrevYear());
//        return result;
//    }

    public boolean equalsAsUiObject(MfcStatsItem mfcStatsItem) {
        boolean result;
        result = (this.getName().equalsIgnoreCase(mfcStatsItem.getName()) &
                this.getDimension().equalsIgnoreCase(mfcStatsItem.getDimension()) &
                this.getFillingType() == mfcStatsItem.getFillingType() &
                this.getDataSource() == mfcStatsItem.getDataSource() &
                this.getValue().equalsIgnoreCase(mfcStatsItem.getValue()) &
                this.getValueYear().equalsIgnoreCase(mfcStatsItem.getValueYear()) &
                this.getValuePrevYear().equalsIgnoreCase(mfcStatsItem.getValuePrevYear()));

        return result;
    }
}
