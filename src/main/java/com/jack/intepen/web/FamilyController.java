package com.jack.intepen.web;

import com.jack.intepen.dto.IntepenResult;
import com.jack.intepen.entity.Elder;
import com.jack.intepen.entity.Family;
import com.jack.intepen.enums.AuthcEnum;
import com.jack.intepen.enums.FamilyEnum;
import com.jack.intepen.service.FamilyService;
import com.jack.intepen.vo.ElderProfile;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * Created by 11407 on 4/004.
 */
@RestController
@RequestMapping(value = "/family")
@Api(value = "family", description = "家属相关Api")
public class FamilyController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    FamilyService familyService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ApiOperation(value = "/family/list", notes = "列出所有家属")
    private IntepenResult<List> listFamily(){

        logger.info("------------------GET:/family/list-----------------");

        List<Family> list = familyService.getFamilyList();
        if(list != null && list.size() != 0){
            return  new IntepenResult<List>(AuthcEnum.SUCCESS.getCode(), list);
        }
        else{
            return new IntepenResult<>(FamilyEnum.LIST_FAMILY_ERROR.getCode(), FamilyEnum.LIST_FAMILY_ERROR.getError());
        }
    }
    @RequestMapping(value = "/profile/{account}", method = RequestMethod.GET)
    @ApiOperation(value = "/family/profile", notes = "家属资料")
    private IntepenResult<Family> getFamilyByAccount(@ApiParam(value = "家属账号", required = true)@PathVariable(value = "account") String account){

        logger.info("------------------GET:/family/profile-----------------");


        Family family = familyService.getFamilyByAccount(account);

        if(family == null){
            return new IntepenResult<>(FamilyEnum.UNKNOWN_ACCOUNT.getCode(), FamilyEnum.UNKNOWN_ACCOUNT.getError());
        }
        else{
            return new IntepenResult<>(AuthcEnum.SUCCESS.getCode(), family);
        }
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ApiOperation(value = "/family/add", notes = "增加一个家属")
    private IntepenResult<Family> addFamily(@ApiParam(value = "一个存储username和password的Map", required = true)
                                           @RequestBody Map<String, String> account){

        logger.info("---------------------POST:/family/add---------------------");

        if(familyService.getFamilyByAccount(account.get("account")) != null){
            return new IntepenResult<>(AuthcEnum.ACCOUNT_EXISTS.getCode(), AuthcEnum.ACCOUNT_EXISTS.getError());
        }
        else{
            Family family = new Family();
            family.setAccount(account.get("account"));
            family.setPassword(account.get("password"));
            familyService.addFamily(family);
            return new IntepenResult<>(AuthcEnum.SUCCESS.getCode(), familyService.getFamilyByAccount(account.get("account")));
        }
    }

    @RequestMapping(value = "/elders", method = RequestMethod.GET)
    @ApiOperation(value = "/family/elders", notes = "列出家属的所有老人")
    private IntepenResult<List> getEldersByFamily(HttpServletRequest request){

        logger.info("------------------GET:/family/elders----------------");

        Integer familyId = (Integer) request.getSession().getAttribute("id");

        List<ElderProfile> elders = familyService.getEldersByFamilyId(familyId);
        if(elders == null){
            return new IntepenResult<>(AuthcEnum.ERROR.getCode(), AuthcEnum.ERROR.getError());
        }
        else if(elders.size() == 0){
            return new IntepenResult<>(FamilyEnum.NO_ELDERS.getCode(), FamilyEnum.NO_ELDERS.getError());
        }
        else{
            return new IntepenResult<>(AuthcEnum.SUCCESS.getCode(), elders);
        }
    }
}
