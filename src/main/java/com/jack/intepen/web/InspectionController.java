package com.jack.intepen.web;

import com.jack.intepen.dto.IntepenResult;
import com.jack.intepen.entity.Inspection;
import com.jack.intepen.enums.AuthcEnum;
import com.jack.intepen.service.InspectionService;
import com.jack.intepen.vo.InspectionResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;

/**
 * Created by 11407 on 26/026.
 */
@RestController
@RequestMapping(value = "/inspection")
@Api(value = "inspection", description = "与巡查结果相关的Api")
public class InspectionController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private InspectionService inspectionService;

    @RequestMapping(value = "/listtoday", method = RequestMethod.GET)
    @ApiOperation(value = "/inspection/listtoday", notes = "列出今天的巡查信息")
    public IntepenResult<List> getTodayInspections(){

        logger.info("-------------GET:/inspection/listtoday------------");

        List<InspectionResult> list = inspectionService.getTodayInspectionResult();
        if(list != null){
            return new IntepenResult<>(AuthcEnum.SUCCESS.getCode(), list);
        }
        else{
            return new IntepenResult<>(AuthcEnum.LIST_ERROR.getCode(), AuthcEnum.LIST_ERROR.getError());
        }
    }

    @RequestMapping(value = "/listbydate", method = RequestMethod.GET)
    @ApiOperation(value = "/inspection/listbydate/{date}", notes = "列出指定日期的巡查记录")
    public IntepenResult<List> getInspectionByDate(@ApiParam(value = "指定的日期", required = true)
                                                       @PathVariable(value = "date") Date date){
        logger.info("-------------GET:/inspection/listbydate------------");

        List<InspectionResult> list = inspectionService.getInspectionResultByDate(date);

        if(list != null){
            return new IntepenResult<>(AuthcEnum.SUCCESS.getCode(), list);
        }
        else{
            return new IntepenResult<>(AuthcEnum.LIST_ERROR.getCode(), AuthcEnum.LIST_ERROR.getError());
        }
    }

    @RequestMapping(value = "/listbyelder/{id}", method = RequestMethod.GET)
    @ApiOperation(value = "/inspection/listbyelder", notes = "列出指定老人的巡查记录")
    public IntepenResult<List> getInspectionByElder(@ApiParam(value = "老人的ID", required = true)
                                                        @PathVariable(value = "id") Integer id){

        logger.info("-------------GET:/inspection/listbyelder-------------");

        List<InspectionResult> list = inspectionService.getInspectionResultByElderId(id);

        if(list != null){
            return new IntepenResult<>(AuthcEnum.SUCCESS.getCode(), list);
        }
        else{
            return new IntepenResult<>(AuthcEnum.LIST_ERROR.getCode(), AuthcEnum.LIST_ERROR.getError());
        }
    }

    @RequestMapping(value = "/listbydatezone", method = RequestMethod.GET)
    @ApiOperation(value = "/inspection/listbydatezone", notes = "列出指定日期区间的巡查记录")
    public IntepenResult<List> getInspectionByDateZone(@ApiParam(value = "日期的左端点", required = true) @RequestParam(value = "dateA") Date dateA,
                                                       @ApiParam(value = "日期的右端点") @RequestParam(value = "dateB", required = false) Date dateB){

        logger.info("------------GET:/inspection/listbydatezone-------------");

        if(dateB == null){
            java.util.Date day = new java.util.Date();
            dateB = new Date(day.getTime());
        }

        List<InspectionResult> list = inspectionService.getInspectionResultByDateZone(dateA, dateB);

        if(list != null){
            return new IntepenResult<>(AuthcEnum.SUCCESS.getCode(), list);
        }
        else{
            return new IntepenResult<>(AuthcEnum.LIST_ERROR.getCode(), AuthcEnum.LIST_ERROR.getError());
        }
    }

    @RequestMapping(value = "/listbyelderanddatezone", method = RequestMethod.GET)
    @ApiOperation(value = "/inspection/listbyelderanddatezone", notes = "列出指定老人的指定日期区间内的巡查记录")
    public IntepenResult<List> listInspectionByNameandDateZone(@ApiParam(value = "老人的姓名", required = true) @RequestParam(value = "elderName") String elderName,
                                                               @ApiParam(value = "日期区间的左端点", required = true) @RequestParam(value = "dateA") Date dateA,
                                                               @ApiParam(value = "日期区间的右端点", required = false) @RequestParam(value = "dateB") Date dateB){

        logger.info("--------------GET:/inspection/listbyelderanddatezone---------");

        if(dateA == null && dateB == null){
            List list = inspectionService.getInspectionResultByElderName(elderName);

            if(list != null){
                return new IntepenResult<>(AuthcEnum.SUCCESS.getCode(), list);
            }
            else{
                return new IntepenResult<>(AuthcEnum.LIST_ERROR.getCode(), AuthcEnum.LIST_ERROR.getError());
            }
        }
        if(dateA != null && dateB == null){
            java.util.Date day = new java.util.Date();
            dateB = new Date(day.getTime());
        }
        List list = inspectionService.getInspectionByElderNameAndDateZone(elderName, dateA, dateB);

        if(list != null){
            return new IntepenResult<>(AuthcEnum.SUCCESS.getCode(), list);
        }
        else{
            return new IntepenResult<>(AuthcEnum.LIST_ERROR.getCode(), AuthcEnum.LIST_ERROR.getError());
        }
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    @ApiOperation(value = "/inspection/edit", notes = "编辑巡查记录")
    public IntepenResult<Boolean> editInspection(Inspection inspection){

        logger.info("------------------Post:/inspection/edit------------------");

        boolean success = inspectionService.modifyInspection(inspection);

        if(success){
            return new IntepenResult<>(AuthcEnum.SUCCESS.getCode(), AuthcEnum.SUCCESS.getError());
        }
        else{
            return new IntepenResult<>(AuthcEnum.EDIT_ERROR.getCode(), AuthcEnum.EDIT_ERROR.getError());
        }
    }
}
