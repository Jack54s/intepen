package com.jack.intepen.web;

import com.jack.intepen.dto.IntepenResult;
import com.jack.intepen.entity.Elder;
import com.jack.intepen.entity.Nurse;
import com.jack.intepen.enums.AuthcEnum;
import com.jack.intepen.enums.NurseEnum;
import com.jack.intepen.service.NurseService;
import com.jack.intepen.vo.NurseProfile;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by 11407 on 6/006.
 */
@RestController
@RequestMapping(value = "/nurse")
public class NurseController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    private NurseService nurseService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    private IntepenResult<List> listNurse(){

        logger.info("------------------GET:/nurse/list-----------------");

        List<Nurse> list = nurseService.getNurseList();
        if(list != null && list.size() != 0){
            return  new IntepenResult<List>(AuthcEnum.SUCCESS.getCode(), list);
        }
        else{
            return new IntepenResult<>(NurseEnum.LIST_NURSE_ERROR.getCode(), NurseEnum.LIST_NURSE_ERROR.getError());
        }
    }

    @RequestMapping(value = "/profile/{account}", method = RequestMethod.GET)
    private IntepenResult<Nurse> queryNurseByAccount(@PathVariable(value = "account") String account ){

        logger.info("------------------GET:/nurse/profile-----------------");

        Nurse nurse = nurseService.getNurseByAccount(account);
        if(nurse != null){
            return  new IntepenResult<>(AuthcEnum.SUCCESS.getCode(), nurse);
        }
        else{
            return new IntepenResult<>(NurseEnum.QUERY_NURSE_ERROR.getCode(), NurseEnum.QUERY_NURSE_ERROR.getError());
        }
    }

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    private IntepenResult<List> queryNurseByIdOrName(@RequestParam(value = "id", required = false) Integer id,
                                                 @RequestParam(value = "name", required = false) String name){

        logger.info("------------------GET:/nurse/search-----------------");

        if(name == null && "".equals(name) && id == null){
            return new IntepenResult<>(AuthcEnum.PARAM_ERROR.getCode(), AuthcEnum.PARAM_ERROR.getError());
        }
        else if(name != null && !("".equals(name) ) && id != null && !("".equals(id))){
            List<NurseProfile> nurseProfiles = new LinkedList<>();

            NurseProfile nurseProfile = nurseService.getNurseProfileById(id);

            if(nurseProfile.getName().equals(name)){
                nurseProfiles.add(nurseProfile);
                return new IntepenResult<>(AuthcEnum.SUCCESS.getCode(), nurseProfiles);
            }
            else{
                return new IntepenResult<>(NurseEnum.QUERY_NURSE_ERROR.getCode(), NurseEnum.QUERY_NURSE_ERROR.getError());
            }
        }
        else if(name != null && !("".equals(name)) && id == null){
            List<NurseProfile> nurseProfiles = nurseService.getNurseProfileByName(name);
            if(nurseProfiles != null){
                return  new IntepenResult<>(AuthcEnum.SUCCESS.getCode(), nurseProfiles);
            }
            else{
                return new IntepenResult<>(NurseEnum.QUERY_NURSE_ERROR.getCode(), NurseEnum.QUERY_NURSE_ERROR.getError());
            }
        }
        else if((name == null || "".equals(name)) && id != null){
            List<NurseProfile> nurseProfiles = new LinkedList<>();

            nurseProfiles.add(nurseService.getNurseProfileById(id));

            return new IntepenResult<>(AuthcEnum.SUCCESS.getCode(), nurseProfiles);
        }
        else{
            return new IntepenResult<>(AuthcEnum.PARAM_ERROR.getCode(), AuthcEnum.PARAM_ERROR.getError());
        }
    }

    @RequestMapping(value = "/elders", method = RequestMethod.GET)
    private IntepenResult<List> getEldersByNurse(HttpServletRequest request){

        logger.info("------------------GET:/nurse/elders-----------------");

        Integer nurseId = (Integer)request.getSession().getAttribute("id");

        List<Elder> elders = nurseService.getElderByNurse(nurseId);

        if(elders != null){
            return new IntepenResult<>(AuthcEnum.SUCCESS.getCode(), elders);
        }
        else{
            return new IntepenResult<>(NurseEnum.QUERY_ELDER_NURSED.getCode(), NurseEnum.QUERY_ELDER_NURSED.getError());
        }
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    private IntepenResult<Boolean> addNurse(@RequestBody Nurse nurse){

        logger.info("------------------Post:/nurse/add------------------");

        Subject currentUser = SecurityUtils.getSubject();

        if(!currentUser.isPermitted("nurse:add")){
            return new IntepenResult<>(AuthcEnum.UNAUTHORIZING.getCode(), AuthcEnum.UNAUTHORIZING.getError());
        }

        boolean success = nurseService.addNurse(nurse);

        if(success){
            return new IntepenResult<>(AuthcEnum.SUCCESS.getCode(), AuthcEnum.SUCCESS.getError());
        }
        else{
            return new IntepenResult<>(NurseEnum.ADD_ERROR.getCode(), NurseEnum.ADD_ERROR.getError());
        }
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    private IntepenResult<Boolean> editNurse(@RequestBody NurseProfile nurseProfile){

        logger.info("------------------Post:/nurse/add------------------");

        Subject currentUser = SecurityUtils.getSubject();

        if(!currentUser.isPermitted("nurse:edit")){
            return new IntepenResult<>(AuthcEnum.UNAUTHORIZING.getCode(), AuthcEnum.UNAUTHORIZING.getError());
        }

        boolean success = nurseService.modifyNurseProfile(nurseProfile);

        if(success){
            return new IntepenResult<>(AuthcEnum.SUCCESS.getCode(), AuthcEnum.SUCCESS.getError());
        }
        else{
            return new IntepenResult<>(NurseEnum.EDIT_ERROR.getCode(), NurseEnum.EDIT_ERROR.getError());
        }
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    private IntepenResult<Boolean> deleteNurse(@RequestBody Map<String, Integer> id){

        logger.info("------------------Post:/nurse/delete------------------");

        Subject currentUser = SecurityUtils.getSubject();

        if(!currentUser.isPermitted("nurse:delete")){
            return new IntepenResult<>(AuthcEnum.UNAUTHORIZING.getCode(), AuthcEnum.UNAUTHORIZING.getError());
        }

        boolean success = nurseService.deleteNurse(id.get("id"));

        if(success){
            return new IntepenResult<>(AuthcEnum.SUCCESS.getCode(), AuthcEnum.SUCCESS.getError());
        }
        else{
            return new IntepenResult<>(NurseEnum.DELETE_ERROR.getCode(), NurseEnum.DELETE_ERROR.getError());
        }
    }
}
