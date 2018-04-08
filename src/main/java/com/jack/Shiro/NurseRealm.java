package com.jack.Shiro;

import com.jack.intepen.entity.Nurse;
import com.jack.intepen.service.NurseService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by 11407 on 5/005.
 */
/*public class NurseRealm extends AuthorizingRealm {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    NurseService nurseService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {

        Set<String> roles = new HashSet<>();
        String account = (String)principals.getPrimaryPrincipal();
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        roles.add("nurse");
        roles.addAll(nurseService.getRoles(account));
        authorizationInfo.setRoles(roles);
        authorizationInfo.setStringPermissions(nurseService.getPermissions(account));
        return authorizationInfo;
    }

    /**
     * 用于认证
     *
     * @param token
     * @return
     * @throws AuthenticationException
     */
/*    @Override
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

        Nurse nurse = nurseService.getNurseByAccount(account);

        if(nurse == null){
            throw new UnknownAccountException("无效的用户名！");
        }
        else{

            if(password.equals(nurse.getPassword())){
                SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(account, password, getName());
                return info;
            }
            else{
                throw new IncorrectCredentialsException("密码错误！");
            }
        }
    }
}*/
