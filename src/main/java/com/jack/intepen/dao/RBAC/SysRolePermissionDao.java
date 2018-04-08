package com.jack.intepen.dao.RBAC;

import com.jack.intepen.entity.RBAC.SysRolePermission;
import org.springframework.stereotype.Repository;

import java.util.Set;

/**
 * Created by 11407 on 5/005.
 */
@Repository
public interface SysRolePermissionDao {

    int insertRolePermission(SysRolePermission rolePermission);
    int deleteRolePermission(int id);
    int updateRolePermission(SysRolePermission rolePermission);
    SysRolePermission queryRolePermissionById(int id);
    Set<Integer> queryPermissionByRoleId(int roleId);
}
