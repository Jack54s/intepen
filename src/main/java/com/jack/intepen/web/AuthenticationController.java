package com.jack.intepen.web;

import com.jack.shiro.LoginRealm;
import com.jack.intepen.dao.RBAC.SysUserDao;
import com.jack.intepen.dto.IntepenResult;
import com.jack.intepen.entity.Family;
import com.jack.intepen.entity.RBAC.SysUser;
import com.jack.intepen.enums.AuthcEnum;
import com.jack.intepen.service.FamilyService;
import com.jack.intepen.service.UserService;
import com.jack.intepen.util.EncryptionUtils;
import io.swagger.annotations.*;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by 11407 on 3/003.
 */
@RestController
@Api(value = "authentication", description = "登录注册注销相关的API")
public class AuthenticationController {

    @Autowired
    FamilyService familyService;

    @Resource
    LoginRealm loginRealm;

    @Autowired
    private SysUserDao sysUserDao;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ApiOperation(value = "/login", notes = "登录，返回用户信息")
    private IntepenResult<Map<String, Object>> login(@ApiParam(value = "一个存储username和password的Map", required = true)
                                                         @RequestBody Map<String, String> account){

        logger.info("-----------------------POST:/login--------------------");

        Subject currentUser = SecurityUtils.getSubject();

        SysUser user = sysUserDao.querySysUserByAccount(account.get("username"));

        if(user == null ){
            return new IntepenResult<>(AuthcEnum.UNKNOWN_ACCOUNT.getCode(), AuthcEnum.UNKNOWN_ACCOUNT.getError());
        }

        if(!currentUser.isAuthenticated()){
            UsernamePasswordToken token = new UsernamePasswordToken(account.get("username"),
                    EncryptionUtils.SHA512Encode(account.get("password"), user.getSalt()), loginRealm.getName());
            token.setRememberMe(true);
            try{
                currentUser.login(token);
            } catch (UnknownAccountException uae) {
                return new IntepenResult<>(AuthcEnum.UNKNOWN_ACCOUNT.getCode(), AuthcEnum.UNKNOWN_ACCOUNT.getError());
            } catch (IncorrectCredentialsException ice) {
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
        session.setAttribute("username", user.getAccount());

        Map<String, Object> userToken = new HashMap<>();

        userToken.put("id", user.getId());
        userToken.put("username", user.getAccount());
        userToken.put("role", userService.getRoles(user.getId()));

        return new IntepenResult<>(AuthcEnum.SUCCESS.getCode(), userToken);
    }

    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    @ApiOperation(value = "/logout", notes = "注销")
    private IntepenResult logout(){

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
    @ApiIgnore
    @ApiOperation(value = "/returnlogin", notes = "不可调用的接口，用于没有认证的返回")
    private IntepenResult returnLogin(){
        return new IntepenResult<>(AuthcEnum.ACCOUNT_UNLOGIN.getCode(), AuthcEnum.ACCOUNT_UNLOGIN.getError());
    }
}
