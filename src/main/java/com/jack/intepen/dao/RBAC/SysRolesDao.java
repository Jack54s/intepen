package com.jack.intepen.dao.RBAC;

import com.jack.intepen.entity.RBAC.SysRoles;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by 11407 on 5/005.
 */
@Repository
public interface SysRolesDao {

    int insertRole(SysRoles role);
    int deleteRole(int id);
    int updateRole(SysRoles role);
    List<SysRoles> queryRoles();
    SysRoles queryRoleById(int id);
}
