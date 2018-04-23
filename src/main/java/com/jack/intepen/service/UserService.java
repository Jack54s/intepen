package com.jack.intepen.service;

import com.jack.intepen.dao.RBAC.*;
import com.jack.intepen.entity.RBAC.SysRoles;
import com.jack.intepen.entity.RBAC.SysUser;
import com.jack.intepen.service.UserInterface.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by 11407 on 6/006.
 */
@Repository
public class UserService implements SysUserService {

    @Autowired
    private SysUserRoleDao sysUserRoleDao;

    @Autowired
    private SysRolesDao sysRolesDao;

    @Autowired
    private SysRolePermissionDao sysRolePermissionDao;

    @Autowired
    private SysPermissionsDao sysPermissionsDao;

    public Set<String> getRoles(Integer userId){

        Set<Integer> roleIds = sysUserRoleDao.queryRoleByUserId(userId);
        Set<String> roles = new HashSet<>();

        for(Integer roleId : roleIds){
            SysRoles role = sysRolesDao.queryRoleById(roleId);
            roles.add(role.getRole());
        }
        return roles;
    }

    public Set<String> getPermissions(Integer userId) {

        Set<Integer> roleIds = sysUserRoleDao.queryRoleByUserId(userId);
        Set<Integer> permissionIds = new HashSet<>();
        Set<String> permissions = new HashSet<>();
        for(Integer roleId : roleIds){
            permissionIds.addAll(sysRolePermissionDao.queryPermissionByRoleId(roleId));
        }
        for(Integer permissionId : permissionIds){
            permissions.add(sysPermissionsDao.queryPermissionById(permissionId).getPermission());
        }
        return permissions;
    }
}
