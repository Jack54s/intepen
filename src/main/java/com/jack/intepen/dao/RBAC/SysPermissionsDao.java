package com.jack.intepen.dao.RBAC;

import com.jack.intepen.entity.RBAC.SysPermissions;
import org.springframework.stereotype.Repository;

/**
 * Created by 11407 on 5/005.
 */
@Repository
public interface SysPermissionsDao {

    int insertPermission(SysPermissions permission);
    int deletePermission(int id);
    int updatePermission(SysPermissions permission);
    SysPermissions queryPermissionById(int id);
}
