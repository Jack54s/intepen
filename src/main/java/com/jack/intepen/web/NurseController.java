package com.jack.intepen.web;

import com.jack.intepen.dto.IntepenResult;
import com.jack.intepen.entity.Elder;
import com.jack.intepen.entity.Nurse;
import com.jack.intepen.enums.AuthcEnum;
import com.jack.intepen.enums.NurseEnum;
import com.jack.intepen.service.NurseService;
import com.jack.intepen.vo.ElderProfile;
import com.jack.intepen.vo.NurseProfile;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by 11407 on 6/006.
 */
@RestController
@RequestMapping(value = "/nurse")
@Api(value = "nurse", description = "与护工相关的Api")
public class NurseController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    private NurseService nurseService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ApiOperation(value = "/nurse/list", notes = "列出所有的护工")
    private IntepenResult<List> listNurse(){

        logger.info("------------------GET:/nurse/list-----------------");

        List<NurseProfile> list = nurseService.getNurseProfileList();

        if(list.size() == 0){
            list.add(new NurseProfile());
        }
        if(list != null && list.size() != 0){
            return  new IntepenResult<List>(AuthcEnum.SUCCESS.getCode(), list);
        }
        else{
            return new IntepenResult<>(NurseEnum.LIST_NURSE_ERROR.getCode(), NurseEnum.LIST_NURSE_ERROR.getError());
        }
    }

    @RequestMapping(value = "/profile/{account}", method = RequestMethod.GET)
    @ApiOperation(value = "/nurse/profile", notes = "查询护工的信息")
    private IntepenResult<Nurse> queryNurseByAccount(@ApiParam(value = "护工的账号", required = true)
                                                         @PathVariable(value = "account") String account ){

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
    @ApiOperation(value = "/nurse/search", notes = "按id或者姓名搜索")
    private IntepenResult<List> queryNurseByIdOrName(@ApiParam(value = "护工id") @RequestParam(value = "id", required = false) Integer id,
                                                 @ApiParam(value = "护工的姓名") @RequestParam(value = "name", required = false) String name){

        logger.info("------------------GET:/nurse/search-----------------");

        if(name == null && "".equals(name) && id == null){
            return new IntepenResult<>(AuthcEnum.PARAM_ERROR.getCode(), AuthcEnum.PARAM_ERROR.getError());
        }
        else if(name != null && !("".equals(name) ) && id != null && !("".equals(id))){
            List<NurseProfile> nurseProfiles = new ArrayList<>();

            NurseProfile nurseProfile = nurseService.getNurseProfileById(id);

            if(nurseProfile.getName().equals(name)){
                nurseProfiles.add(nurseProfile);
                if(nurseProfiles.size() == 0){
                    nurseProfiles.add(new NurseProfile());
                }
                return new IntepenResult<>(AuthcEnum.SUCCESS.getCode(), nurseProfiles);
            }
            else{
                return new IntepenResult<>(NurseEnum.QUERY_NURSE_ERROR.getCode(), NurseEnum.QUERY_NURSE_ERROR.getError());
            }
        }
        else if(name != null && !("".equals(name)) && id == null){
            List<NurseProfile> nurseProfiles = nurseService.getNurseProfileByName(name);

            if(nurseProfiles.size() == 0){
                nurseProfiles.add(new NurseProfile());
            }
            if(nurseProfiles != null){
                return  new IntepenResult<>(AuthcEnum.SUCCESS.getCode(), nurseProfiles);
            }
            else{
                return new IntepenResult<>(NurseEnum.QUERY_NURSE_ERROR.getCode(), NurseEnum.QUERY_NURSE_ERROR.getError());
            }
        }
        else if((name == null || "".equals(name)) && id != null){
            List<NurseProfile> nurseProfiles = new ArrayList<>();

            if(nurseService.getNurseProfileById(id) != null){
                nurseProfiles.add(nurseService.getNurseProfileById(id));
            }

            if(nurseProfiles.size() == 0){
                nurseProfiles.add(new NurseProfile());
            }
            return new IntepenResult<>(AuthcEnum.SUCCESS.getCode(), nurseProfiles);
        }
        else{
            return new IntepenResult<>(AuthcEnum.PARAM_ERROR.getCode(), AuthcEnum.PARAM_ERROR.getError());
        }
    }

    @RequestMapping(value = "/elders", method = RequestMethod.GET)
    @ApiOperation(value = "/nurse/elders", notes = "列出此护工护理的老人")
    private IntepenResult<List> getEldersByNurse(HttpServletRequest request){

        logger.info("------------------GET:/nurse/elders-----------------");

        Integer nurseId = (Integer)request.getSession().getAttribute("id");

        List<ElderProfile> elders = nurseService.getElderByNurse(nurseId);

        if(elders.size() == 0){
            elders.add(new ElderProfile());
        }
        if(elders != null){
            return new IntepenResult<>(AuthcEnum.SUCCESS.getCode(), elders);
        }
        else{
            return new IntepenResult<>(NurseEnum.QUERY_ELDER_NURSED.getCode(), NurseEnum.QUERY_ELDER_NURSED.getError());
        }
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ApiOperation(value = "/nurse/add", notes = "新增一个护工")
    private IntepenResult<Boolean> addNurse(@ApiParam(value = "一个护工对象", required = true)
                                                @RequestBody Nurse nurse){

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
    @ApiOperation(value = "/nurse/edit", notes = "编辑护工的信息")
    private IntepenResult<Boolean> editNurse(@ApiParam(value = "护工的个人信息", required = true)
                                                 @RequestBody NurseProfile nurseProfile){

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
    @ApiOperation(value = "/nurse/delete", notes = "删除一个护工")
    private IntepenResult<Boolean> deleteNurse(@ApiParam(value = "护工id", required = true)@RequestBody Map<String, Integer> id){

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
