package com.company.pojo;

import java.util.Date;

public class User {
    private int id;
    private String userCode;
    private String userName;
    private String userPassword;
    private String gender;
    private Date birthday;
    private String phone;
    private String address;
    private String userRole;
    private String createdBy;
    private String creationDate;
    private String modifyBy;
    private String modifyDate;
    private int age;
    private String userRoleName;
    public int getAge() {
        Date date = new Date();
        Integer age = date.getYear()-birthday.getYear();
        return age;
    }

    public String getUserRoleName() {
        return userRoleName;
    }

    public void setUserRoleName(String userRoleName) {
        this.userRoleName = userRoleName;
    }

    public void setId (int id) {
        this.id = id;
    }

    public void setUserCode (String userCode) {
        this.userCode = userCode;
    }

    public void setUserName (String userName) {
        this.userName = userName;
    }

    public void setUserPassword (String userPassword) {
        this.userPassword = userPassword;
    }

    public void setGender (String gender) {
        this.gender = gender;
    }

    public void setBirthday (Date birthday) {
        this.birthday = birthday;
    }

    public void setPhone (String phone) {
        this.phone = phone;
    }

    public void setAddress (String address) {
        this.address = address;
    }

    public void setUserRole (String userRole) {
        this.userRole = userRole;
    }

    public void setCreatedBy (String createdBy) {
        this.createdBy = createdBy;
    }

    public void setCreationDate (String creationDate) {
        this.creationDate = creationDate;
    }

    public void setModifyBy (String modify) {
        this.modifyBy = modifyBy;
    }

    public void setModifyDate (String modifyDate) {
        this.modifyDate = modifyDate;
    }

    public int getId () {
        return this.id;
    }

    public String getUserCode () {
        return this.userCode;
    }

    public String getUserName () {
        return this.userName;
    }

    public String getUserPassword () {
        return this.userPassword;
    }

    public String getGender () {
        return this.gender;
    }

    public Date getBirthday () {
        return this.birthday;
    }

    public String getPhone () {
        return this.phone;
    }

    public String getAddress () {
        return this.address;
    }

    public String getUserRole () {
        return this.userRole;
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

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userCode='" + userCode + '\'' +
                ", userName='" + userName + '\'' +
                ", userPassword='" + userPassword + '\'' +
                ", gender='" + gender + '\'' +
                ", birthday='" + birthday + '\'' +
                ", phone='" + phone + '\'' +
                ", address='" + address + '\'' +
                ", userRole='" + userRole + '\'' +
                ", createdBy='" + createdBy + '\'' +
                ", creationDate='" + creationDate + '\'' +
                ", modifyBy='" + modifyBy + '\'' +
                ", modifyDate='" + modifyDate + '\'' +
                '}';
    }

}