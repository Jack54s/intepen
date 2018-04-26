package com.jack.intepen.service;

import com.jack.intepen.dao.NurseDao;
import com.jack.intepen.dao.RBAC.SysPermissionsDao;
import com.jack.intepen.dao.RBAC.SysRolePermissionDao;
import com.jack.intepen.dao.RBAC.SysRolesDao;
import com.jack.intepen.dao.RBAC.SysUserRoleDao;
import com.jack.intepen.entity.Elder;
import com.jack.intepen.entity.Nurse;
import com.jack.intepen.entity.RBAC.SysRoles;
import com.jack.intepen.service.UserInterface.SysUserService;
import com.jack.intepen.util.EncryptionUtils;
import com.jack.intepen.vo.NurseProfile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by 11407 on 3/003.
 */
@Service
public class NurseService implements SysUserService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private NurseDao nurseDao;

    @Autowired
    private SysUserRoleDao sysUserRoleDao;

    @Autowired
    private SysRolesDao sysRolesDao;

    @Autowired
    private SysRolePermissionDao sysRolePermissionDao;

    @Autowired
    private SysPermissionsDao sysPermissionsDao;

    public List<Nurse> getNurseList(){
        return nurseDao.queryNurse();
    }

    public Nurse getNurseByAccount(String account){
        return nurseDao.queryNurseByAccount(account);
    }

    public Nurse getNurseById(int id){ return nurseDao.queryNurseById(id); }

    public List<Nurse> getNurseByName(String name) { return nurseDao.queryNurseByName(name); }

    public NurseProfile getNurseProfileByAccount(String account) { return nurseDao.queryNurseProfileByAccount(account); }

    public NurseProfile getNurseProfileById(int id) { return nurseDao.queryNurseProfileById(id); }

    public List<NurseProfile> getNurseProfileByName(String name) { return nurseDao.queryNurseProfileByName(name);}

    @Transactional
    public boolean addNurse(Nurse nurse){

        logger.info("--------------------------addNurse----------------------");

        String salt = EncryptionUtils.getSalt(128);
        String password = EncryptionUtils.SHA512Encode(nurse.getPassword(), salt);
        nurse.setPassword(password);
        nurse.setSalt(salt);

        if(nurse.getAccount() != null && !"".equals(nurse.getAccount())){
            try{
                int effectNum = nurseDao.insertNurse(nurse);
                if(effectNum > 0 ){
                    return true;
                }
                else{
                    throw new RuntimeException("插入护工信息失败！");
                }
            }
            catch (Exception e){
                throw new RuntimeException("插入护工信息失败：" + e.getMessage());
            }
        }
        else{
            throw new RuntimeException("护工账号不能为空！");
        }
    }

    public boolean modifyNurseProfile(NurseProfile nurseProfile){

        logger.info("--------------------------modifyNurse----------------------");

        if(nurseProfile.getId() != null && !"".equals(nurseProfile.getId())){
            try{
                int effectNum = nurseDao.updateNurseProfile(nurseProfile);
                if(effectNum > 0 ){
                    return true;
                }
                else{
                    throw new RuntimeException("更新护工信息失败！");
                }
            }
            catch (Exception e){
                throw new RuntimeException("更新护工信息失败：" + e.getMessage());
            }
        }
        else{
            throw new RuntimeException("护工ID不能为空！");
        }

    }

    public boolean deleteNurse(int id){

        logger.info("--------------------------deleteNurse----------------------");

        if(id > 0){
            try{
                nurseDao.deleteNurse(id);
            }
            catch (Exception e){
                throw new RuntimeException("删除护工信息失败：" + e.getMessage());
            }
        }
        else{
            throw new RuntimeException("护工ID不能为空！");
        }
        return true;
    }

    public List<Elder> getElderByNurse(int nurseId){
        return nurseDao.queryElderByNurse(nurseId);
    }

    public Set<String> getRoles(String account){

        Nurse nurse = nurseDao.queryNurseByAccount(account);
        Set<Integer> roleIds = sysUserRoleDao.queryRoleByUserId(nurse.getId());
        Set<String> roles = new HashSet<>();
        for(Integer roleId : roleIds){
            SysRoles role = sysRolesDao.queryRoleById(roleId);
            roles.add(role.getRole());
        }
        return roles;
    }

    public Set<String> getPermissions(String account) {

        Nurse nurse = nurseDao.queryNurseByAccount(account);
        Set<Integer> roleIds = sysUserRoleDao.queryRoleByUserId(nurse.getId());
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
