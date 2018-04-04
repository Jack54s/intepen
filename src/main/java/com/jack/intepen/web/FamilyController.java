package com.jack.intepen.web;

import com.jack.intepen.dto.IntepenResult;
import com.jack.intepen.entity.Family;
import com.jack.intepen.service.FamilyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by 11407 on 4/004.
 */
@RestController
public class FamilyController {

    @Autowired
    FamilyService familyService;

    @RequestMapping(value = "/family/{account}", method = RequestMethod.GET)
    private IntepenResult<Family> getFamilyByAccount(@PathVariable(value = "account") String account){

        Family family = familyService.getFamilyByAccount(account);

        if(family == null){
            return new IntepenResult<>(false, "未找到此账号");
        }
        else{
            return new IntepenResult<>(true, family);
        }
    }
}
