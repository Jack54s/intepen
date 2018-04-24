package com.jack.intepen.web;

import com.jack.config.ThresholdConfiguration;
import com.jack.intepen.dto.IntepenResult;
import com.jack.intepen.entity.Threshold;
import com.jack.intepen.enums.AuthcEnum;
import com.jack.intepen.enums.ThresholdEnum;
import com.jack.intepen.service.ThresholdService;
import com.sun.org.apache.xpath.internal.operations.Bool;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * Created by 11407 on 24/024.
 */
@RestController
@RequestMapping(value = "/threshold")
@Api(value = "threshold", description = "阈值相关Api")
public class ThresholdController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ThresholdConfiguration thresholdConfiguration;

    @Autowired
    private ThresholdService thresholdService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ApiOperation(value = "/threshold/list", notes = "列出所有的阈值")
    public IntepenResult<List> listAllThreshold(){

        logger.info("------------------GET:/threshold/list-----------------");

        List<Threshold> thresholds = thresholdService.getAllThreshold();

        if(thresholds != null){
            return new IntepenResult<>(AuthcEnum.SUCCESS.getCode(), thresholds);
        }
        else{
            return new IntepenResult<>(ThresholdEnum.LIST_ALL_THRESHOLD_ERROR.getCode(), ThresholdEnum.LIST_ALL_THRESHOLD_ERROR.getError());
        }
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ApiOperation(value = "/threshold/add", notes = "增加一个阈值项")
    public IntepenResult<Boolean> addThreshold(@ApiParam(value = "一个阈值对象，不需要id", required = true)
                                                   @RequestBody Threshold threshold){

        logger.info("------------------Post:/threshold/add------------------");

        Threshold th = new Threshold();

        th.addObserver(thresholdConfiguration);

        th.setItem(threshold.getItem());
        th.setThreshold(threshold.getThreshold());

        boolean success = thresholdService.addThreshold(th);

        if(success){
            return new IntepenResult<>(AuthcEnum.SUCCESS.getCode(), AuthcEnum.SUCCESS.getError());
        }
        else{
            return new IntepenResult<>(ThresholdEnum.ADD_ERROR.getCode(), ThresholdEnum.ADD_ERROR.getError());
        }
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    @ApiOperation(value = "/threshold/edit", notes = "修改一个阈值")
    public IntepenResult<Boolean> modifyThreshold(@ApiParam(value = "一个完整的Threshold对象", required = true)
                                                      @RequestBody Threshold threshold){

        logger.info("---------------POST:/threshold/edit--------------");

        Threshold th = threshold;
        th.addObserver(thresholdConfiguration);
        th.setThreshold(threshold.getThreshold());

        boolean success = thresholdService.modifyThreshold(th);

        if(success){
            return new IntepenResult<>(AuthcEnum.SUCCESS.getCode(), AuthcEnum.SUCCESS.getError());
        }
        else{
            return new IntepenResult<>(ThresholdEnum.EDIT_ERROR.getCode(), ThresholdEnum.EDIT_ERROR.getError());
        }
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ApiOperation(value = "/threshold/delete", notes = "删除一个阈值项")
    public IntepenResult<Boolean> removeThreshold(@ApiParam(value = "一个存放id的Map", required = true)
                                                      @RequestBody Map<String, Integer> id){

        logger.info("------------------Post:/threshold/delete------------------");

        boolean success = thresholdService.removeThreshold(id.get("id"));

        if(success){
            return new IntepenResult<>(AuthcEnum.SUCCESS.getCode(), AuthcEnum.SUCCESS.getError());
        }
        else{
            return new IntepenResult<>(ThresholdEnum.DELETE_ERROR.getCode(), ThresholdEnum.DELETE_ERROR.getError());
        }
    }
}
