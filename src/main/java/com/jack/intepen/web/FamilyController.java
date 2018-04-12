package com.jack.intepen.web;

import com.jack.intepen.dto.IntepenResult;
import com.jack.intepen.entity.Elder;
import com.jack.intepen.entity.Family;
import com.jack.intepen.enums.AuthcEnum;
import com.jack.intepen.enums.FamilyEnum;
import com.jack.intepen.service.FamilyService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by 11407 on 4/004.
 */
@RestController
@RequestMapping(value = "/family")
public class FamilyController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    FamilyService familyService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
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
    private IntepenResult<Family> getFamilyByAccount(@PathVariable(value = "account") String account){

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
    private IntepenResult<Boolean> addFamily(@RequestBody Family family){

        logger.info("------------------Post:/family/add------------------");

        Subject currentUser = SecurityUtils.getSubject();

        if(!currentUser.isPermitted("family:add")){
            return new IntepenResult<>(AuthcEnum.UNAUTHORIZING.getCode(), AuthcEnum.UNAUTHORIZING.getError());
        }

        boolean success = familyService.addFamily(family);

        if(success){
            return new IntepenResult<>(AuthcEnum.SUCCESS.getCode(), AuthcEnum.SUCCESS.getError());
        }
        else{
            return new IntepenResult<>(FamilyEnum.ADD_ERROR.getCode(), FamilyEnum.ADD_ERROR.getError());
        }
    }

    @RequestMapping(value = "/elders", method = RequestMethod.GET)
    private IntepenResult<List> getEldersByFamily(HttpServletRequest request){

        logger.info("------------------GET:/family/elders----------------");

        Integer familyId = (Integer) request.getSession().getAttribute("id");

        List<Elder> elders = familyService.getEldersByFamilyId(familyId);
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
