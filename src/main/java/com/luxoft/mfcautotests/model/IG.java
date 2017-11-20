package com.luxoft.mfcautotests.model;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Component;

@Component
public class IG {

    private String lastNameLatin;
    private String nameLatin;
    private String secondNameLatin;
    private String lastName;
    private String name;
    private String secondName;
    private Passport passport;
    private String citizenship;
    private String dateOfBirth;
    private String countryOfBirth;
    private String placeOfBirth;
    private String sex;

    public IG() {
        String randomString = RandomStringUtils.randomAlphabetic( 8).toUpperCase();

        lastNameLatin = randomString;
        nameLatin = randomString;
        passport = new Passport();
        passport.setSerNum(randomString);
        citizenship = "Узбекистан";
    }

    public String getLastNameLatin() {
        return lastNameLatin;
    }

    public String getNameLatin() {
        return nameLatin;
    }

    public String getSecondNameLatin() {
        return secondNameLatin;
    }

    public String getLastName() {
        return lastName;
    }

    public String getName() {
        return name;
    }

    public String getSecondName() {
        return secondName;
    }

    public Passport getPassport() {
        return passport;
    }

    public String getCitizenship() {
        return citizenship;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public String getCountryOfBirth() {
        return countryOfBirth;
    }

    public String getPlaceOfBirth() {
        return placeOfBirth;
    }

    public String getSex() {
        return sex;
    }

    public void setLastNameLatin(String lastNameLatin) {
        this.lastNameLatin = lastNameLatin;
    }

    public void setNameLatin(String nameLatin) {
        this.nameLatin = nameLatin;
    }

    public void setSecondNameLatin(String secondNameLatin) {
        this.secondNameLatin = secondNameLatin;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public void setPassport(Passport passport) {
        this.passport = passport;
    }

    public void setCitizenship(String citizenship) {
        this.citizenship = citizenship;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public void setCountryOfBirth(String countryOfBirth) {
        this.countryOfBirth = countryOfBirth;
    }

    public void setPlaceOfBirth(String placeOfBirth) {
        this.placeOfBirth = placeOfBirth;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
}
