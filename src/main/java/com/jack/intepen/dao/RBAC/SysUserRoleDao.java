package com.jack.intepen.dao.RBAC;

import com.jack.intepen.entity.RBAC.SysUserRole;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Set;

/**
 * Created by 11407 on 5/005.
 */
@Repository
public interface SysUserRoleDao {

    int insertUserRole(SysUserRole userRole);
    int insertUserRole(@Param("userId") Integer userId, @Param("roleId") Integer roleId);
    int deleteUserRoles(int userId);
    //int updateUserRole(SysUserRole userRole);
    SysUserRole queryUserRoleById(int id);
    Set<Integer> queryRoleByUserId(int userId);
}
