package com.model;

public class EditTextModel {

    int UserID,MobileNo,UsersImage;
    String Gender,FullName,EmailID,DOB;

    public EditTextModel(int userID, int mobileNo, int usersImage, String gender, String fullName, String emailID, String DOB) {
        UserID = userID;
        MobileNo = mobileNo;
        UsersImage = usersImage;
        Gender = gender;
        FullName = fullName;
        EmailID = emailID;
        this.DOB = DOB;
    }

    public int getUserID() {
        return UserID;
    }

    public void setUserID(int userID) {
        UserID = userID;
    }

    public int getMobileNo() {
        return MobileNo;
    }

    public void setMobileNo(int mobileNo) {
        MobileNo = mobileNo;
    }

    public int getUsersImage() {
        return UsersImage;
    }

    public void setUsersImage(int usersImage) {
        UsersImage = usersImage;
    }

    public String getGender() {
        return Gender;
    }

    public void setGender(String gender) {
        Gender = gender;
    }

    public String getFullName() {
        return FullName;
    }

    public void setFullName(String fullName) {
        FullName = fullName;
    }

    public String getEmailID() {
        return EmailID;
    }

    public void setEmailID(String emailID) {
        EmailID = emailID;
    }

    public String getDOB() {
        return DOB;
    }

    public void setDOB(String DOB) {
        this.DOB = DOB;
    }
}
