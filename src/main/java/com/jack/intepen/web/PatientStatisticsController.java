package com.jack.intepen.web;

import com.jack.intepen.dto.IntepenResult;
import com.jack.intepen.entity.PatientStatistic;
import com.jack.intepen.enums.AuthcEnum;
import com.jack.intepen.service.PatientStatisticService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Date;
import java.util.List;

/**
 * Created by 11407 on 2/002.
 */
@RestController
@RequestMapping(value = "/patientstatistics")
@Api(value = "patientstatistics", description = "疾病统计相关Api")
public class PatientStatisticsController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private PatientStatisticService patientStatisticService;

    @RequestMapping(value = "/today", method = RequestMethod.GET)
    @ApiOperation(value = "/patientstatistics/today", notes = "获取今天的疾病统计信息")
    public IntepenResult<List> getPatientStatisticsToday(){

        logger.info("--------------GET:/patientstatistics/today-----------");

        List<PatientStatistic> statistics = patientStatisticService.getPatientStatisticsToday();

        if(statistics != null){
            return new IntepenResult<>(AuthcEnum.SUCCESS.getCode(), statistics);
        }
        else {
            return new IntepenResult<>(AuthcEnum.LIST_ERROR.getCode(), AuthcEnum.LIST_ERROR.getError());
        }
    }

    @RequestMapping(value = "/duration", method = RequestMethod.GET)
    @ApiOperation(value = "/patientstatistics/duration", notes = "获取一段时间的疾病统计")
    public IntepenResult<List> getPatientStatisticsInDuration(@ApiParam(value = "日期的左端点")
                                                                  @RequestParam(value = "dateA") Date dateA,
                                                              @ApiParam(value = "日期的右端点")
                                                                  @RequestParam(value = "dateB", required = false) Date dateB){

        logger.info("-------------GET:/patientstatistics/duration------------");

        if(dateB == null){
            java.util.Date day = new java.util.Date();
            dateB = new Date(day.getTime());
        }
        List<PatientStatistic> statistics = patientStatisticService.getPatientStatisticsByDateZone(dateA, dateB);

        if(statistics != null){
            return new IntepenResult<>(AuthcEnum.SUCCESS.getCode(), statistics);
        }
        else{
            return new IntepenResult<>(AuthcEnum.LIST_ERROR.getCode(), AuthcEnum.LIST_ERROR.getError());
        }
    }
}
