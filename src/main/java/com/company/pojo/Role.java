package com.company.pojo;

public class Role {
    private int id;
    private String roleCode;
    private String roleName;
    private String createdBy;
    private String creationDate;
    private String modifyBy;
    private String modifyDate;
    public void setId (int id) {
        this.id = id;
    }

    public void setRoleCode (String roleCode) {
        this.roleCode = roleCode;
    }

    public void setRoleName (String roleName) {
        this.roleName = roleName;
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

    public int getId () {
        return this.id;
    }

    public String getRoleCode () {
        return this.roleCode;
    }

    public String getRoleName () {
        return this.roleName;
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
        return "Role{" +
                "id=" + id +
                ", roleCode='" + roleCode + '\'' +
                ", roleName='" + roleName + '\'' +
                ", createdBy='" + createdBy + '\'' +
                ", creationDate='" + creationDate + '\'' +
                ", modifyBy='" + modifyBy + '\'' +
                ", modifyDate='" + modifyDate + '\'' +
                '}';
    }
}