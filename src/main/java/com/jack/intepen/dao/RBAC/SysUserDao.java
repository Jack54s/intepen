package com.jack.intepen.dao.RBAC;

import com.jack.intepen.entity.RBAC.SysUser;
import org.springframework.stereotype.Repository;

/**
 * Created by 11407 on 5/005.
 */
@Repository
public interface SysUserDao {
    int insertSysUser(SysUser sysUser);
    int updateSysUser(SysUser sysUser);
    int deleteSysUser(int id);
    SysUser querySysUserById(int id);
    SysUser querySysUserByAccount(String account);
}
