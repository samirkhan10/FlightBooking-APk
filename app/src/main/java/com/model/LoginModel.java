package com.model;

public class LoginModel  {

    String MobileNo;

    public LoginModel(String mobileNo) {
        MobileNo = mobileNo;
    }

    public String getMobileNo() {
        return MobileNo;
    }

    public void setMobileNo(String mobileNo) {
        MobileNo = mobileNo;
    }
}
