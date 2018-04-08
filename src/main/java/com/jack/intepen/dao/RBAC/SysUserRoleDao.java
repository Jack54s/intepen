package com.jack.intepen.dao.RBAC;

import com.jack.intepen.entity.RBAC.SysUserRole;
import org.springframework.stereotype.Repository;

import java.util.Set;

/**
 * Created by 11407 on 5/005.
 */
@Repository
public interface SysUserRoleDao {

    int insertUserRole(SysUserRole userRole);
    int deleteUserRole(int id);
    int updateUserRole(SysUserRole userRole);
    SysUserRole queryUserRoleById(int id);
    Set<Integer> queryRoleByUserId(int userId);
}
