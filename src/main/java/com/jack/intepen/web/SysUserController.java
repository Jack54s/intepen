package com.jack.intepen.web;

import com.jack.intepen.dto.IntepenResult;
import com.jack.intepen.enums.AuthcEnum;
import com.jack.intepen.service.UserService;
import com.jack.intepen.vo.UserInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by 11407 on 3/003.
 */
@RestController
@RequestMapping(value = "/user")
@Api(value = "user", description = "与用户相关的Api")
public class SysUserController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/info/{token}", method = RequestMethod.GET)
    @ApiOperation(value = "/user/info", notes = "获取用户的基本信息")
    public IntepenResult<UserInfo> getUserInfo(@ApiParam(value = "用户Token", required = true)
                                         @PathVariable(value = "token") String token,
                                     HttpServletRequest request){

        logger.info("------------GET:/user/info--------------");

        Integer id = Integer.parseInt(token);

        UserInfo userInfo = new UserInfo();

        userInfo.setRoles(userService.getRoles(id));
        userInfo.setToken(token);
        userInfo.setName(userService.getUserById(id).getName());
        userInfo.setAvatar(null);

        return new IntepenResult<UserInfo>(AuthcEnum.SUCCESS.getCode(), userInfo);
    }
}
