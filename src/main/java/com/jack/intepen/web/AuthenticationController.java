package com.jack.intepen.web;

import com.jack.Shiro.LoginRealm;
import com.jack.intepen.dao.RBAC.SysUserDao;
import com.jack.intepen.dto.IntepenResult;
import com.jack.intepen.entity.Family;
import com.jack.intepen.entity.RBAC.SysUser;
import com.jack.intepen.enums.AuthcEnum;
import com.jack.intepen.service.FamilyService;
import com.jack.intepen.util.EncryptionUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * Created by 11407 on 3/003.
 */
@RestController
public class AuthenticationController {

    @Resource
    FamilyService familyService;

    @Resource
    LoginRealm loginRealm;

    @Resource
    private SysUserDao sysUserDao;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    private IntepenResult<SysUser> login(@RequestBody Map<String, String> account){

        logger.info("-----------------------POST:/doFamilyLogin--------------------");

        Subject currentUser = SecurityUtils.getSubject();

        SysUser user = sysUserDao.querySysUserByAccount(account.get("account"));

        if(user == null ){
            return new IntepenResult<>(AuthcEnum.UNKNOWN_ACCOUNT.getCode(), AuthcEnum.UNKNOWN_ACCOUNT.getError());
        }

        if(!currentUser.isAuthenticated()){
            UsernamePasswordToken token = new UsernamePasswordToken(account.get("account"),
                    EncryptionUtils.SHA512Encode(account.get("password"), user.getSalt()), loginRealm.getName());
            token.setRememberMe(true);
            try{
                currentUser.login(token);
            } catch (UnknownAccountException uae) {
                return new IntepenResult<>(AuthcEnum.UNKNOWN_ACCOUNT.getCode(), AuthcEnum.UNKNOWN_ACCOUNT.getError());
            } catch (IncorrectCredentialsException ice) {
                logger.info("--------------------mistake:{}---------------------", ice.getStackTrace());
                return new IntepenResult<>(AuthcEnum.INCORRECT_PASSWORD.getCode(), AuthcEnum.INCORRECT_PASSWORD.getError());
            } catch (AuthenticationException ae) {
                return new IntepenResult<>(AuthcEnum.AUTHENTICATION_ERROR.getCode(), AuthcEnum.AUTHENTICATION_ERROR.getError());
            }
        }
        else{
            return new IntepenResult<>(AuthcEnum.TOKEN_EXISTS.getCode(), AuthcEnum.TOKEN_EXISTS.getError());
        }

        Session session = currentUser.getSession();
        session.setAttribute("id", user.getId());
        session.setAttribute("account", account.get("account"));

        return new IntepenResult<>(AuthcEnum.SUCCESS.getCode(), user);
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    private IntepenResult<Family> register(@RequestBody Map<String, String> account){

        logger.info("---------------------POST:/register---------------------");

        if(familyService.getFamilyByAccount(account.get("account")) != null){
            return new IntepenResult<>(AuthcEnum.ACCOUNT_EXISTS.getCode(), AuthcEnum.ACCOUNT_EXISTS.getError());
        }
        else{
            Family family = new Family();
            family.setAccount(account.get("account"));
            family.setPassword(account.get("password"));
            familyService.addFamily(family);
            return new IntepenResult<>(0, familyService.getFamilyByAccount(account.get("account")));
        }
    }

    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    private IntepenResult logout(HttpServletRequest request){

        Subject currentUser = SecurityUtils.getSubject();

        if(currentUser.isAuthenticated()){
            currentUser.logout();
            return new IntepenResult<>(AuthcEnum.SUCCESS.getCode(), AuthcEnum.SUCCESS.getError());
        }
        else{
            return new IntepenResult<>(AuthcEnum.ACCOUNT_UNLOGIN.getCode(), AuthcEnum.ACCOUNT_UNLOGIN.getError());
        }
    }
    @ResponseBody
    @RequestMapping(value = "/returnlogin")
    private IntepenResult returnLogin(HttpServletRequest request){
        return new IntepenResult<>(AuthcEnum.ACCOUNT_UNLOGIN.getCode(), AuthcEnum.ACCOUNT_UNLOGIN.getError());
    }
}
