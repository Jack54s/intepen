package com.jack.intepen.web;

import com.jack.intepen.dto.IntepenResult;
import com.jack.intepen.enums.AuthcEnum;
import com.jack.intepen.service.PhysiologicalDataService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by 11407 on 27/027.
 */
@RestController
@RequestMapping(value = "/physiologicaldata")
@Api(value = "physiologicaldata", description = "与实时生理数据相关的Api")
public class PhysiologicalDataController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    PhysiologicalDataService physiologicalDataService;

    @RequestMapping(value = "/getaelder", method = RequestMethod.GET)
    @ApiOperation(value = "/physiologicaldata/getaelder", notes = "获得一个老人的所有生理数据")
    public IntepenResult<List> getElderData(@ApiParam(value = "老人的ID", required = true)
                                                @PathVariable(value = "id") String id){
        return new IntepenResult<>(AuthcEnum.ERROR.getCode(), "缺少设备关联表");
    }
}
