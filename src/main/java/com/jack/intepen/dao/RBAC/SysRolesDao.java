package com.jack.intepen.dao.RBAC;

import com.jack.intepen.entity.RBAC.SysRoles;
import org.springframework.stereotype.Repository;

/**
 * Created by 11407 on 5/005.
 */
@Repository
public interface SysRolesDao {

    int insertRole(SysRoles role);
    int deleteRole(int id);
    int updateRole(SysRoles role);
    SysRoles queryRoleById(int id);
}
