package com.jack.Shiro;

import com.jack.intepen.dao.RBAC.SysUserDao;
import com.jack.intepen.entity.Family;
import com.jack.intepen.entity.RBAC.SysUser;
import com.jack.intepen.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by 11407 on 3/003.
 */
@Repository
public class LoginRealm extends AuthorizingRealm {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    UserService userService;

    @Autowired
    private SysUserDao sysUserDao;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {

        Set<String> roles;
        String account = (String)principals.getPrimaryPrincipal();
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        roles = userService.getRoles(account);
        authorizationInfo.setRoles(roles);
        authorizationInfo.setStringPermissions(userService.getPermissions(account));

        Subject currentUser = SecurityUtils.getSubject();
        Session session = currentUser.getSession();

        session.setAttribute("role", roles);
        return authorizationInfo;
    }

    /**
     * 用于认证
     *
     * @param token
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {

        String account;
        String password;
        if(token != null) {
            if(token.getPrincipal() != null){
                account = token.getPrincipal().toString();
            }
            else{
                throw new AuthenticationException("账号为空！");
            }
            if(token.getCredentials() != null){
                password = new String((char[]) token.getCredentials());
            }
            else{
                throw new AuthenticationException("密码为空！");
            }
        }
        else{
            throw new AuthenticationException("token为空！");
        }

        SysUser user = sysUserDao.querySysUserByAccount(account);

        if(user == null){
            throw new UnknownAccountException("无效的用户名！");
        }
        else{

            if(password.equals(user.getPassword())){
                SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(account, password, getName());
                return info;
            }
            else{
                throw new IncorrectCredentialsException("密码错误！");
            }
        }
    }
}