package com.luxoft.mfcautotests.model;

public class InsuranceCompany {

    private String name;
    private int adminUiSeqNo;
    private int uiSeqNo;

    public String getName() {
        return name;
    }

    public int getAdminUiSeqNo() {
        return adminUiSeqNo;
    }

    public int getUiSeqNo() {
        return uiSeqNo;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAdminUiSeqNo(int adminUiSeqNo) {
        this.adminUiSeqNo = adminUiSeqNo;
    }

    public void setUiSeqNo(int uiSeqNo) {
        this.uiSeqNo = uiSeqNo;
    }

    @Override
    public String toString() {
        return "InsuranceCompany{" +
                "name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        InsuranceCompany that = (InsuranceCompany) o;

        return name != null ? name.equals(that.name) : that.name == null;
    }

    @Override
    public int hashCode() {
        return name != null ? name.hashCode() : 0;
    }
}
