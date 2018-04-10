package com.jack.intepen.web;

import com.jack.intepen.dto.IntepenResult;
import com.jack.intepen.entity.Family;
import com.jack.intepen.enums.AuthcEnum;
import com.jack.intepen.enums.FamilyEnum;
import com.jack.intepen.service.FamilyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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
    private IntepenResult<List> listNurse(){

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
}
