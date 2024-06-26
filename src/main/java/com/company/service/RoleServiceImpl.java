package com.company.service;

import com.company.dao.RoleDao;
import com.company.dao.RoleDaoImpl;
import java.util.ArrayList;
import com.company.pojo.Role;
public class RoleServiceImpl implements RoleService {
    RoleDao roleDao;
    public RoleServiceImpl () {
        roleDao = new RoleDaoImpl();
    }

    @Override
    public ArrayList<Role> selectAllRole () {
        ArrayList<Role> roleList = roleDao.selectAllRole();
        return roleList;
    }
}