package com.jack.intepen.service;

import com.jack.intepen.dao.FamilyDao;
import com.jack.intepen.dao.RBAC.SysPermissionsDao;
import com.jack.intepen.dao.RBAC.SysRolePermissionDao;
import com.jack.intepen.dao.RBAC.SysRolesDao;
import com.jack.intepen.dao.RBAC.SysUserRoleDao;
import com.jack.intepen.entity.Family;
import com.jack.intepen.entity.RBAC.SysRoles;
import com.jack.intepen.service.UserInterface.SysUserService;
import com.jack.intepen.util.EncryptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * Created by 11407 on 3/003.
 */
@Service
public class FamilyService implements SysUserService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private FamilyDao familyDao;

    @Autowired
    private SysUserRoleDao sysUserRoleDao;

    @Autowired
    private SysRolePermissionDao sysRolePermissionDao;

    @Autowired
    private SysRolesDao sysRolesDao;

    @Autowired
    private SysPermissionsDao sysPermissionsDao;

    public List<Family> getFamilyList(){ return familyDao.queryFamily(); }

    public Family getFamilyByAccount(String account){
        return familyDao.queryFamilyByAccount(account);
    }

    @Transactional
    public boolean addFamily(Family family){

        logger.info("--------------------------addFamily----------------------");

        String salt = EncryptionUtils.getSalt(128);
        String password = EncryptionUtils.SHA512Encode(family.getPassword(), salt);
        family.setPassword(password);
        family.setSalt(salt);

        if(family.getAccount() != null && !"".equals(family.getAccount())){
            try{
                int effectNum = familyDao.insertFamily(family);
                if(effectNum > 0 ){
                    return true;
                }
                else{
                    logger.info("家属注册时发生异常");
                    throw new RuntimeException("插入家属信息失败！");
                }
            }
            catch (Exception e){
                throw new RuntimeException("插入家属信息失败：" + e.getMessage());
            }
        }
        else{
            throw new RuntimeException("家属账号不能为空！");
        }
    }

    public boolean modifyFamily(Family family){

        logger.info("---------------------------modifyFamily------------------------");

        if(family.getId() != null && !"".equals(family.getId())){
            try{
                int effectNum = familyDao.updateFamily(family);
                if(effectNum > 0 ){
                    return true;
                }
                else{
                    throw new RuntimeException("更新家属信息失败！");
                }
            }
            catch (Exception e){
                throw new RuntimeException("更新家属信息失败：" + e.getMessage());
            }
        }
        else{
            throw new RuntimeException("家属ID不能为空！");
        }

    }

    public boolean deleteFamily(int id){

        logger.info("------------------------------deleteFamily-----------------------");

        if(id > 0){
            try{
                int effectNum = familyDao.deleteFamily(id);
                if(effectNum > 0 ){
                    return true;
                }
                else{
                    throw new RuntimeException("删除家属信息失败！");
                }
            }
            catch (Exception e){
                throw new RuntimeException("删除家属信息失败：" + e.getMessage());
            }
        }
        else{
            throw new RuntimeException("家属ID不能为空！");
        }
    }

    public Set<String> getRoles(String account){

        Family family = familyDao.queryFamilyByAccount(account);
        Set<Integer> roleIds = sysUserRoleDao.queryRoleByUserId(family.getId());
        Set<String> roles = new HashSet<>();
        for(Integer roleId : roleIds){
            SysRoles role = sysRolesDao.queryRoleById(roleId);
            roles.add(role.getRole());
        }
        return roles;
    }

    public Set<String> getPermissions(String account) {

        Family family = familyDao.queryFamilyByAccount(account);
        Set<Integer> roleIds = sysUserRoleDao.queryRoleByUserId(family.getId());
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
//
//    public int checkPasswordByAccount(String account, String password){
//
//        logger.info("--------------------checkPasswordByAccount-----------------------");
//
//        Family family = familyDao.queryFamilyByAccount(account);
//        if(family == null){
//            return 0;
//        }
//
//        password = EncryptionUtils.SHA512Encode(password, family.getSalt());
//        if(password.equals(family.getPassword())){
//            return family.getId();
//        }
//        else{
//            return 0;
//        }
//    }
}
