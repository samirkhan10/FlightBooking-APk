package com.model;

public class PassPortModel {

    int UserID,PassportImage_Front,PassportImage_Back;
    String FirstName,LastName,Passport_No,Issue_Place,Issue_Date,ExpiryDate;

    public PassPortModel(int userID, int passportImage_Front, int passportImage_Back, String firstName, String lastName, String passport_No, String issue_Place, String issue_Date, String expiryDate) {
        UserID = userID;
        PassportImage_Front = passportImage_Front;
        PassportImage_Back = passportImage_Back;
        FirstName = firstName;
        LastName = lastName;
        Passport_No = passport_No;
        Issue_Place = issue_Place;
        Issue_Date = issue_Date;
        ExpiryDate = expiryDate;
    }

    public int getUserID() {
        return UserID;
    }

    public void setUserID(int userID) {
        UserID = userID;
    }

    public int getPassportImage_Front() {
        return PassportImage_Front;
    }

    public void setPassportImage_Front(int passportImage_Front) {
        PassportImage_Front = passportImage_Front;
    }

    public int getPassportImage_Back() {
        return PassportImage_Back;
    }

    public void setPassportImage_Back(int passportImage_Back) {
        PassportImage_Back = passportImage_Back;
    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public String getPassport_No() {
        return Passport_No;
    }

    public void setPassport_No(String passport_No) {
        Passport_No = passport_No;
    }

    public String getIssue_Place() {
        return Issue_Place;
    }

    public void setIssue_Place(String issue_Place) {
        Issue_Place = issue_Place;
    }

    public String getIssue_Date() {
        return Issue_Date;
    }

    public void setIssue_Date(String issue_Date) {
        Issue_Date = issue_Date;
    }

    public String getExpiryDate() {
        return ExpiryDate;
    }

    public void setExpiryDate(String expiryDate) {
        ExpiryDate = expiryDate;
    }
}
