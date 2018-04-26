package com.jack.intepen.web;

import com.jack.intepen.dto.IntepenResult;
import com.jack.intepen.entity.RBAC.SysRoles;
import com.jack.intepen.enums.AuthcEnum;
import com.jack.intepen.enums.RolesEnum;
import com.jack.intepen.service.SysRolesService;
import com.jack.intepen.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by 11407 on 26/026.
 */
@RestController
@RequestMapping(value = "/roles")
@Api(value = "roles", description = "与用户角色有关的Api")
public class RolesController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private SysRolesService sysRolesService;

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ApiOperation(value = "/roles/list", notes = "列出所有的角色")
    public IntepenResult<List> listRoles(){

        logger.info("------------------GET:/roles/list-----------------");

        List<SysRoles> list = sysRolesService.getAllRoles();
        if(list != null){
            return  new IntepenResult<>(AuthcEnum.SUCCESS.getCode(), list);
        }
        else{
            return new IntepenResult<>(RolesEnum.LIST_ROLES_ERROR.getCode(), RolesEnum.LIST_ROLES_ERROR.getError());
        }
    }

    @RequestMapping(value = "/listownned", method = RequestMethod.GET)
    @ApiOperation(value = "/roles/listownned", notes = "列出用户拥有的角色")
    public IntepenResult<Set> listOwnnedRoles(HttpServletRequest request){

        logger.info("------------------GET:/roles/listownned-----------------");

        Set<String> roles = userService.getRoles((Integer)request.getSession().getAttribute("id"));


        if(roles != null){
            return  new IntepenResult<>(AuthcEnum.SUCCESS.getCode(), roles);
        }
        else{
            return new IntepenResult<>(RolesEnum.LIST_ROLES_ERROR.getCode(), RolesEnum.LIST_ROLES_ERROR.getError());
        }
    }

    @RequestMapping(value = "/listunownned", method = RequestMethod.GET)
    @ApiOperation(value = "/roles/listunownned", notes = "列出用户未拥有的角色")
    public IntepenResult<List> listUnownnedRoles(HttpServletRequest request){

        logger.info("------------------GET:/roles/listunownned-----------------");

        Set<String> roles = userService.getRoles((Integer)request.getSession().getAttribute("id"));

        List<SysRoles> list = sysRolesService.getAllRoles();

        list.removeIf(sysRoles -> roles.contains(sysRoles.getRole()));
        if(list != null){
            return  new IntepenResult<>(AuthcEnum.SUCCESS.getCode(), list);
        }
        else{
            return new IntepenResult<>(RolesEnum.LIST_ROLES_ERROR.getCode(), RolesEnum.LIST_ROLES_ERROR.getError());
        }
    }

    @RequestMapping(value = "/distribute", method = RequestMethod.POST)
    @ApiOperation(value = "/roles/distribute", notes = "分配用户角色")
    @Transactional
    public IntepenResult<Boolean> distributeRoles(@ApiParam(value = "一个存储用户id和角色id集合的Map", required = true)
                                                  @RequestBody Map<Integer, Set<Integer>> roles){

        logger.info("-----------------POST:/roles/distributeroles---------------");

        for(Integer userId : roles.keySet()){
            if(userService.removeUsersAllRoles(userId)){
                logger.info("-------rolesSet:{}--------------", roles.get(userId));
                if(!userService.setUserRoles(userId, roles.get(userId))){
                    return new IntepenResult<>(RolesEnum.SET_ROLES_ERROR.getCode(), RolesEnum.SET_ROLES_ERROR.getError());
                }
            }
            else{
                return new IntepenResult<>(RolesEnum.REMOVE_ROLES_ERROR.getCode(), RolesEnum.REMOVE_ROLES_ERROR.getError());
            }
        }
        return new IntepenResult<>(AuthcEnum.SUCCESS.getCode(), AuthcEnum.SUCCESS.getError());
    }
}
