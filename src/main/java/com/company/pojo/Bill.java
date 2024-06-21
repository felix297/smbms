package com.company.pojo;

public class Bill {
    private int id;
    private String billCode;
    private String productName;
    private String productDesc;
    private String productUnit;
    private String productCount;
    private String totalPrice;
    private String isPayment;
    private String createdBy;
    private String creationDate;
    private String modifyBy;
    private String modifyDate;
    private String providerId;

    public void setId (int id) {
        this.id = id;
    }

    public void setBillCode (String billCode) {
        this.billCode = billCode;
    }

    public void setProductName (String productName) {
        this.productName = productName;
    }

    public void setProductDesc (String productDesc) {
        this.productDesc = productDesc;
    }

    public void setProductUnit (String productUnit) {
        this.productUnit = productUnit;
    }

    public void setProductCount (String productCount) {
        this.productCount = productCount;
    }

    public void setTotalPrice (String totalPrice) {
        this.totalPrice = totalPrice;
    }

    public void setIsPayment (String isPayment) {
        this.isPayment = isPayment;
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

    public void setProviderId (String providerId) {
        this.providerId = providerId;
    }

    public int getId () {
        return this.id;
    }

    public String getBillCode () {
        return this.billCode;
    }

    public String getProductName () {
        return this.productName;
    }

    public String getProductDesc () {
        return this.productDesc;
    }

    public String getProductUnit () {
        return this.productUnit;
    }

    public String getProductCount () {
        return this.productCount;
    }

    public String getTotalPrice () {
        return this.totalPrice;
    }

    public String getIsPayment () {
        return this.isPayment;
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

    public String getProviderId () {
        return this.providerId;
    }

}