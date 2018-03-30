package com.jack.intepen.web;

import com.jack.intepen.dto.IntepenResult;
import com.jack.intepen.entity.OldMan;
import com.jack.intepen.service.OldManService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by 11407 on 30/030.
 */
@RestController
@RequestMapping("/oldman")
public class OldManController {

    @Autowired
    private OldManService oldManService;

    @RequestMapping(value = "/profile", method = RequestMethod.GET)
    private IntepenResult<List> listOldMan(){
        List<OldMan> list = oldManService.getOldManList();
        if(list != null && list.size() != 0){
            return  new IntepenResult<List>(true, list);
        }
        else{
            return new IntepenResult(false, "获取老人列表失败！");
        }
    }
}
