package com.company.pojo;

public class Role {
    private int id;
    private String roleCode;
    private String roleName;
    private String createdBy;
    private String creationDate;
    private String modifyBy;
    private String modifyDate;

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
}