package com.jack.intepen.web;

import com.jack.Shiro.FamilyRealm;
import com.jack.intepen.dto.IntepenResult;
import com.jack.intepen.entity.Family;
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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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
    FamilyRealm familyRealm;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @RequestMapping(value = "/doFamilyLogin", method = RequestMethod.POST)
    private IntepenResult<Family> doFamilyLogin(@RequestBody Map<String, String> account){

        logger.info("-----------------------POST:/doFamilyLogin--------------------");

        Subject currentUser = SecurityUtils.getSubject();

        Family family = familyService.getFamilyByAccount(account.get("account"));

        if(family == null ){
            return new IntepenResult<>(false, "账号未注册！");
        }

        if(!currentUser.isAuthenticated()){
            logger.info("----------------------Realm:{}----------------------", familyRealm.getName());
            UsernamePasswordToken token = new UsernamePasswordToken(account.get("account"),
                    EncryptionUtils.SHA512Encode(account.get("password"), family.getSalt()), familyRealm.getName());
            token.setRememberMe(true);
            try{
                currentUser.login(token);
            } catch (UnknownAccountException uae) {
                return new IntepenResult<>(false, "账号未注册！");
            } catch (IncorrectCredentialsException ice) {
                logger.info("--------------------mistake:{}---------------------", ice.getStackTrace());
                return new IntepenResult<>(false, "密码错误！");
            } catch (AuthenticationException ae) {
                return new IntepenResult<>(false, "账号认证错误！");
            }
        }
        else{
            return new IntepenResult<>(false, "请勿重复登录！");
        }

        Session session = currentUser.getSession();
        session.setAttribute("account", account.get("account"));

        return new IntepenResult<>(true, family);
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    private IntepenResult<Family> familyRegister(@RequestBody Map<String, String> account){

        logger.info("---------------------POST:/register---------------------");

        if(familyService.getFamilyByAccount(account.get("account")) != null){
            return new IntepenResult<>(false, "账号已注册！");
        }
        else{
            Family family = new Family();
            family.setAccount(account.get("account"));
            family.setPassword(account.get("password"));
            familyService.addFamily(family);
            return new IntepenResult<>(true, familyService.getFamilyByAccount(account.get("account")));
        }
    }

    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    private IntepenResult<Boolean> logout(HttpServletRequest request){

        Subject currentUser = SecurityUtils.getSubject();

        if(currentUser.isAuthenticated()){
            currentUser.logout();
            return new IntepenResult<>(true, true);
        }
        else{
            return new IntepenResult<>(false, "账号未登录！");
        }
    }
}
