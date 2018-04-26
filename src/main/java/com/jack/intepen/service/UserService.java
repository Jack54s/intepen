package com.jack.intepen.service;

import com.jack.intepen.dao.RBAC.*;
import com.jack.intepen.entity.RBAC.SysRoles;
import com.jack.intepen.entity.RBAC.SysUser;
import com.jack.intepen.service.UserInterface.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

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

    @Transactional
    public Boolean setUserRoles(Integer userId, Set<Integer> roles){

        for(Integer roleId : roles){
            try{
                int effectNum = sysUserRoleDao.insertUserRole(userId, roleId);
                if(effectNum <= 0 ){
                    return false;
                }
            }
            catch (Exception e){
                throw new RuntimeException("分配角色失败：" + e.getMessage());
            }
        }
        return true;
    }

    @Transactional
    public Boolean removeUsersAllRoles(Integer userId){
        if(userId > 0){
            try{
                int effectNum = sysUserRoleDao.deleteUserRoles(userId);
                return true;
            }
            catch (Exception e){
                throw new RuntimeException("删除用户角色信息失败：" + e.getMessage());
            }
        }
        else{
            throw new RuntimeException("用户ID不能为空！");
        }
    }
}
