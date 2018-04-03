package com.jack.Shiro;

import com.jack.intepen.entity.Family;
import com.jack.intepen.service.FamilyService;
import com.jack.intepen.service.UserInterface.SysUserService;
import com.jack.intepen.util.EncryptionUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by 11407 on 3/003.
 */
public class FamilyRealm extends AuthorizingRealm {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    FamilyService familyService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
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

        String account = token.getPrincipal().toString();
        String password = new String((char[]) token.getCredentials());

        logger.info("-----------------------password:{}--------------------", password);

        Family family = familyService.getFamilyByAccount(account);

        if(family == null){
            throw new UnknownAccountException("无效的用户名！");
        }
        else{
            password = EncryptionUtils.SHA512Encode(password, family.getSalt());
            logger.info("-----------------------password:{}--------------------", password);
            logger.info("-----------------------password:{}--------------------", family.getPassword());
            logger.info("-----------------------equals:{}----------------------", password.equals(family.getPassword()));
            if(password.equals(family.getPassword())){
                logger.info("------------------accessed------------------");
                return new SimpleAuthenticationInfo(account, password, getName());
            }
            else{
                logger.info("-------------------lost--------------------");
                throw new IncorrectCredentialsException("密码错误！");
            }
        }
    }
}
