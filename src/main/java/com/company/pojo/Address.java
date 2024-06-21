package com.company.pojo;

public class Address {
    private int id;
    private String contact;
    private String addressDesc;
    private String postCode;
    private String tel;
    private String createdBy;
    private String creationDate;
    private String modifyBy;
    private String modifyDate;
    private String userId;

    public void setId (int id) {
        this.id = id;
    }

    public void setContact (String contact) {
        this.contact = contact;
    }

    public void setAddressDesc (String addressDesc) {
        this.addressDesc = addressDesc;
    }

    public void setPostCode (String postCode) {
        this.postCode = postCode;
    }

    public void setTel (String tel) {
        this.tel = tel;
    }

    public void setCreatedBy (String createdBy) {
        this.createdBy = createdBy;
    }

    public void setCreationDate (String creationDate) {
        this.creationDate = creationDate;
    }

    public void setModifyBy (String modifyBy) {
        this.modifyBy = modifyBy;
    }

    public void setModifyDate (String modifyDate) {
        this.modifyDate = modifyDate;
    }

    public void setUserId (String userId) {
        this.userId = userId;
    }

    public int getId () {
        return this.id;
    }

    public String getContact () {
        return this.contact;
    }

    public String getAddressDesc () {
        return this.addressDesc;
    }

    public String getPostCode () {
        return this.postCode;
    }

    public String getTel () {
        return this.tel;
    }

    public String getCreatedBy () {
        return this.createdBy;
    }

    public String getCreationDate () {
        return this.creationDate;
    }

    public String getModifyBy () {
        return this.modifyBy;
    }

    public String getModifyDate () {
        return this.modifyDate;
    }

    public String getUserId () {
        return this.userId;
    }

}