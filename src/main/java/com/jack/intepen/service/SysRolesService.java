package com.jack.intepen.service;

import com.jack.intepen.dao.RBAC.SysRolesDao;
import com.jack.intepen.entity.RBAC.SysRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by 11407 on 26/026.
 */
@Service
public class SysRolesService {

    @Autowired
    private SysRolesDao sysRolesDao;

    public List<SysRoles> getAllRoles(){
        return sysRolesDao.queryRoles();
    }
}
