package com.jack.intepen.web;

import com.jack.intepen.dto.IntepenResult;
import com.jack.intepen.entity.Elder;
import com.jack.intepen.service.ElderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.function.BooleanSupplier;

/**
 * Created by 11407 on 30/030.
 */
@RestController
@RequestMapping("/elder")
public class ElderController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    private ElderService ElderService;

    @RequestMapping(value = "/profileslist", method = RequestMethod.GET)
    private IntepenResult<List> listElder(){

        logger.info("------------------GET:/elder/profileslist-----------------");

        List<Elder> list = ElderService.getElderList();
        if(list != null && list.size() != 0){
            return  new IntepenResult<List>(true, list);
        }
        else{
            return new IntepenResult<>(false, "获取老人列表失败！");
        }
    }

    @RequestMapping(value = "/profileslist/{id}", method = RequestMethod.GET)
    private IntepenResult<Elder> queryElder(@PathVariable(value = "id") Integer id ){

        logger.info("------------------GET:/elder/profileslist-----------------");

        Elder elder = ElderService.getElderById(id);
        if(elder != null){
            return  new IntepenResult<>(true, elder);
        }
        else{
            return new IntepenResult<>(false, "获取老人列表失败！");
        }
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    private IntepenResult<Boolean> addElder(@RequestBody Elder elder){

        logger.info("------------------Post:/elder/add------------------");

        boolean success = ElderService.addElder(elder);

        if(success){
            return new IntepenResult<>(true, true);
        }
        else{
            return new IntepenResult<>(false, "插入失败！");
        }
    }

}
