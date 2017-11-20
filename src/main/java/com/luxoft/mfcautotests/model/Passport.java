package com.luxoft.mfcautotests.model;

import org.springframework.stereotype.Component;

@Component
public class Passport {

    private String serNum;
    private String issueDate;
    private String expirationDate;
    private String issuer;

    public String getSerNum() {
        return serNum;
    }

    public String getIssueDate() {
        return issueDate;
    }

    public String getExpirationDate() {
        return expirationDate;
    }

    public String getIssuer() {
        return issuer;
    }

    public void setSerNum(String serNum) {
        this.serNum = serNum;
    }

    public void setIssueDate(String issueDate) {
        this.issueDate = issueDate;
    }

    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
    }

    public void setIssuer(String issuer) {
        this.issuer = issuer;
    }
}
