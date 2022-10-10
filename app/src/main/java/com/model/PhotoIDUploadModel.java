package com.model;

public class PhotoIDUploadModel {

    String UserID,MemberName,ID_No,NameOn_Id;
    int UploadPhotoId;

    public PhotoIDUploadModel(String userID, String memberName, String ID_No, String nameOn_Id, int uploadPhotoId) {
        UserID = userID;
        MemberName = memberName;
        this.ID_No = ID_No;
        NameOn_Id = nameOn_Id;
        UploadPhotoId = uploadPhotoId;
    }

    public String getUserID() {
        return UserID;
    }

    public void setUserID(String userID) {
        UserID = userID;
    }

    public String getMemberName() {
        return MemberName;
    }

    public void setMemberName(String memberName) {
        MemberName = memberName;
    }

    public String getID_No() {
        return ID_No;
    }

    public void setID_No(String ID_No) {
        this.ID_No = ID_No;
    }

    public String getNameOn_Id() {
        return NameOn_Id;
    }

    public void setNameOn_Id(String nameOn_Id) {
        NameOn_Id = nameOn_Id;
    }

    public int getUploadPhotoId() {
        return UploadPhotoId;
    }

    public void setUploadPhotoId(int uploadPhotoId) {
        UploadPhotoId = uploadPhotoId;
    }
}
