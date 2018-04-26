package com.jack.intepen.web;

import com.jack.intepen.dto.IntepenResult;
import com.jack.intepen.entity.Elder;
import com.jack.intepen.entity.MedicalRecord;
import com.jack.intepen.enums.AuthcEnum;
import com.jack.intepen.enums.ElderEnum;
import com.jack.intepen.service.ElderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by 11407 on 30/030.
 */
@RestController
@RequestMapping("/elder")
@Api(value = "elder", description = "Elder相关的API")
public class ElderController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    private ElderService elderService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ApiOperation(value = "/elder/list", notes = "列出所有的老人")
    private IntepenResult<List> listElder(){

        logger.info("------------------GET:/elder/list-----------------");

        List<Elder> list = elderService.getElderList();
        if(list != null){
            return  new IntepenResult<>(AuthcEnum.SUCCESS.getCode(), list);
        }
        else{
            return new IntepenResult<>(ElderEnum.LIST_ELDER_ERROR.getCode(), ElderEnum.LIST_ELDER_ERROR.getError());
        }
    }

    @RequestMapping(value = "/profile/{id}", method = RequestMethod.GET)
    @ApiOperation(value = "/elder/profile", notes = "查询某个老人信息")
    private IntepenResult<Elder> queryElderById(@ApiParam(value = "老人的ID", required = true)
                                                    @PathVariable(value = "id") Integer id ){

        logger.info("------------------GET:/elder/profile-----------------");

        if(id == null && id <= 0){
            return new IntepenResult<>(AuthcEnum.PARAM_ERROR.getCode(), AuthcEnum.PARAM_ERROR.getError());
        }
        else{
            Elder elder = elderService.getElderById(id);
            if(elder != null){
                return  new IntepenResult<>(AuthcEnum.SUCCESS.getCode(), elder);
            }
            else{
                return new IntepenResult<>(ElderEnum.QUERY_ELDER_ERROR.getCode(), ElderEnum.QUERY_ELDER_ERROR.getError());
            }
        }
    }

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    @ApiOperation(value = "/elder/search", notes = "按名字或者身份证号搜索一个老人")
    private IntepenResult<List> queryElderByNameOrIdCard(@ApiParam(value = "身份证号") @RequestParam(value = "idCard", required = false) String idCard,
                                                         @ApiParam(value = "名字") @RequestParam(value = "name", required = false) String name){

        logger.info("------------------GET:/elder/search-----------------");

        logger.info("--------------name:{},idCard:{}----------------", name, idCard);

        if(name == null && "".equals(name) && idCard == null && "".equals(idCard)){
            logger.info("--------------------if---------------------");
            return new IntepenResult<>(AuthcEnum.PARAM_ERROR.getCode(), AuthcEnum.PARAM_ERROR.getError());
        }
        else if(name != null && !("".equals(name) ) && idCard != null && !("".equals(idCard))){
            List<Elder> elders = new ArrayList<>();

            Elder elder = elderService.getElderByIdCard(idCard);
            if(elder == null){
                return new IntepenResult<>(AuthcEnum.SUCCESS.getCode(), new ArrayList());
            }
            if(elder.getName().equals(name)){
                elders.add(elder);
                return new IntepenResult<>(AuthcEnum.SUCCESS.getCode(), elders);
            }
            else{
                return new IntepenResult<>(ElderEnum.QUERY_ELDER_ERROR.getCode(), ElderEnum.QUERY_ELDER_ERROR.getError());
            }
        }
        else if(name != null && !("".equals(name)) && (idCard == null || "".equals(idCard))){
            List<Elder> elders = elderService.getElderByName(name);
            if(elders != null){
                return  new IntepenResult<>(AuthcEnum.SUCCESS.getCode(), elders);
            }
            else{
                return new IntepenResult<>(ElderEnum.QUERY_ELDER_ERROR.getCode(), ElderEnum.QUERY_ELDER_ERROR.getError());
            }
        }
        else if((name == null || "".equals(name)) && idCard != null && !("".equals(idCard))){
            List<Elder> elders = new ArrayList<>();

            Elder elder = elderService.getElderByIdCard(idCard);
            if(elder == null){
                return new IntepenResult<>(AuthcEnum.SUCCESS.getCode(), elders);
            }
            else{
                elders.add(elder);
                return new IntepenResult<>(AuthcEnum.SUCCESS.getCode(), elders);
            }
        }
        else{
            logger.info("--------------------else---------------------");
            return new IntepenResult<>(AuthcEnum.PARAM_ERROR.getCode(), AuthcEnum.PARAM_ERROR.getError());
        }
    }

    @RequestMapping(value = "/undistributed", method = RequestMethod.GET)
    @ApiOperation(value = "/elder/undistributed", notes = "查询未分配护工的老人")
    private IntepenResult<List> getUndistributedElder(){

        logger.info("------------------GET:/elder/undistributed-----------------");

        List<Elder> elders = elderService.getUndistributedElder();

        if(elders != null){
            return new IntepenResult<>(AuthcEnum.SUCCESS.getCode(), elders);
        }
        else{
            return new IntepenResult<>(ElderEnum.QUERY_UNDISTRIBUTED_ELDER.getCode(), ElderEnum.QUERY_UNDISTRIBUTED_ELDER.getError());
        }
    }

    @RequestMapping(value = "/distribute", method = RequestMethod.POST)
    @ApiOperation(value = "/elder/distribute", notes = "为老人分配护工")
    private IntepenResult<Boolean> distributeNurse(@ApiParam(value = "一个存储老人id和护工ID（nurseId）的Map", required = true)
                                                       @RequestBody Map<String, Integer> map){

        logger.info("-----------------POST:/elder/distribute--------------------");

        boolean success = elderService.distributeNurse(map.get("id"), map.get("nurseId"));

        if(success){
            return new IntepenResult<>(AuthcEnum.SUCCESS.getCode(), AuthcEnum.SUCCESS.getError());
        }
        else{
            return new IntepenResult<>(ElderEnum.DISTRIBUTE_NURSE_ERROR.getCode(), ElderEnum.DISTRIBUTE_NURSE_ERROR.getError());
        }
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ApiOperation(value = "/elder/add", notes = "增加一个老人")
    private IntepenResult<Boolean> addElder(@ApiParam(value = "一个老人对象", required = true)
                                                @RequestBody Elder elder){

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
    @ApiOperation(value = "/elder/edit", notes = "编辑老人身份信息")
    private IntepenResult<Boolean> editElder(@ApiParam(value = "一个老人对象", required = true)
                                                 @RequestBody Elder elder){

        logger.info("------------------Post:/elder/edit------------------");

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
    @ApiOperation(value = "/elder/delete", notes = "删除一个老人")
    private IntepenResult<Boolean> deleteElder(@ApiParam(value = "老人id", required = true)
                                                   @RequestBody Map<String, Integer> id){

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

    @RequestMapping(value = "/medicalrecord/{id}", method = RequestMethod.GET)
    @ApiOperation(value = "/elder/medicalrecord", notes = "查询老人的病历")
    private IntepenResult<List> getMedicalRecord(@ApiParam(value = "老人的id", required = true)
                                                     @PathVariable(value = "id") Integer elderId){

        logger.info("------------------GET:/elder/medicalrecord---------------");

        List<MedicalRecord> medicalRecords = elderService.getMedicalRecords(elderId);

        if(medicalRecords == null){
            return new IntepenResult<>(ElderEnum.QUERY_MEDICAL_RECORD_ERROR.getCode(), ElderEnum.QUERY_MEDICAL_RECORD_ERROR.getError());
        }

        return new IntepenResult<>(AuthcEnum.SUCCESS.getCode(), medicalRecords);
    }
}
