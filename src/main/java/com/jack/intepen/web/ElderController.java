package com.jack.intepen.web;

import com.jack.intepen.dto.IntepenResult;
import com.jack.intepen.entity.Elder;
import com.jack.intepen.enums.AuthcEnum;
import com.jack.intepen.enums.ElderEnum;
import com.jack.intepen.service.ElderService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.function.BooleanSupplier;

/**
 * Created by 11407 on 30/030.
 */
@RestController
@RequestMapping("/elder")
public class ElderController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    private ElderService elderService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    private IntepenResult<List> listElder(){

        logger.info("------------------GET:/elder/list-----------------");

        List<Elder> list = elderService.getElderList();
        if(list != null && list.size() != 0){
            return  new IntepenResult<List>(AuthcEnum.SUCCESS.getCode(), list);
        }
        else{
            return new IntepenResult<>(ElderEnum.LIST_ELDER_ERROR.getCode(), ElderEnum.LIST_ELDER_ERROR.getError());
        }
    }

    @RequestMapping(value = "/profile/{id}", method = RequestMethod.GET)
    private IntepenResult<Elder> queryElder(@PathVariable(value = "id") Integer id ){

        logger.info("------------------GET:/elder/profile-----------------");

        Elder elder = elderService.getElderById(id);
        if(elder != null){
            return  new IntepenResult<>(AuthcEnum.SUCCESS.getCode(), elder);
        }
        else{
            return new IntepenResult<>(ElderEnum.QUERY_ELDER_ERROR.getCode(), ElderEnum.QUERY_ELDER_ERROR.getError());
        }
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    private IntepenResult<Boolean> addElder(@RequestBody Elder elder){

        logger.info("------------------Post:/elder/add------------------");

        Subject currentUser = SecurityUtils.getSubject();

        if(!currentUser.isPermitted("elder:add")){
            return new IntepenResult<>(AuthcEnum.UNAUTHORIZING.getCode(), AuthcEnum.UNAUTHORIZING.getError());
        }

        boolean success = elderService.addElder(elder);

        if(success){
            return new IntepenResult<>(AuthcEnum.SUCCESS.getCode(), AuthcEnum.SUCCESS.getError());
        }
        else{
            return new IntepenResult<>(ElderEnum.ADD_ERROR.getCode(), ElderEnum.ADD_ERROR.getError());
        }
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    private IntepenResult<Boolean> editElder(@RequestBody Elder elder){

        logger.info("------------------Post:/elder/add------------------");

        Subject currentUser = SecurityUtils.getSubject();

        if(!currentUser.isPermitted("elder:edit")){
            return new IntepenResult<>(AuthcEnum.UNAUTHORIZING.getCode(), AuthcEnum.UNAUTHORIZING.getError());
        }

        boolean success = elderService.modifyElder(elder);

        if(success){
            return new IntepenResult<>(AuthcEnum.SUCCESS.getCode(), AuthcEnum.SUCCESS.getError());
        }
        else{
            return new IntepenResult<>(ElderEnum.EDIT_ERROR.getCode(), ElderEnum.EDIT_ERROR.getError());
        }
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    private IntepenResult<Boolean> deleteElder(@RequestBody Map<String, Integer> id){

        logger.info("------------------Post:/elder/delete------------------");

        Subject currentUser = SecurityUtils.getSubject();

        if(!currentUser.isPermitted("elder:delete")){
            return new IntepenResult<>(AuthcEnum.UNAUTHORIZING.getCode(), AuthcEnum.UNAUTHORIZING.getError());
        }

        boolean success = elderService.deleteElder(id.get("id"));

        if(success){
            return new IntepenResult<>(AuthcEnum.SUCCESS.getCode(), AuthcEnum.SUCCESS.getError());
        }
        else{
            return new IntepenResult<>(ElderEnum.DELETE_ERROR.getCode(), ElderEnum.DELETE_ERROR.getError());
        }
    }
}
