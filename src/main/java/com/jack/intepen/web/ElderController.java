package com.jack.intepen.web;

import com.jack.intepen.dto.IntepenResult;
import com.jack.intepen.entity.Elder;
import com.jack.intepen.service.ElderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

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

        logger.info("------------------GET:/Elder/profileslist-----------------");

        List<Elder> list = ElderService.getElderList();
        if(list != null && list.size() != 0){
            return  new IntepenResult<List>(true, list);
        }
        else{
            return new IntepenResult(false, "获取老人列表失败！");
        }
    }

    @RequestMapping(value = "/profileslist/{id}", method = RequestMethod.GET)
    private IntepenResult<Elder> queryElder(@PathVariable(value = "id") Integer id ){

        logger.info("------------------GET:/Elder/profileslist-----------------");

        Elder elder = ElderService.getElderById(id);
        if(elder != null){
            return  new IntepenResult<Elder>(true, elder);
        }
        else{
            return new IntepenResult(false, "获取老人列表失败！");
        }
    }
}
