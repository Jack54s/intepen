package com.jack.intepen.web;

import com.jack.intepen.dto.IntepenResult;
import com.jack.intepen.entity.Events;
import com.jack.intepen.enums.AuthcEnum;
import com.jack.intepen.enums.EventsEnum;
import com.jack.intepen.service.EventsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by 11407 on 20/020.
 */
@RestController
@RequestMapping(value = "/events")
public class EventsController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());


    @Autowired
    private EventsService eventsServic;

    @RequestMapping(value = "/listall", method = RequestMethod.GET)
    public IntepenResult<List> getAllEvents(){

        logger.info("------------------GET:/elder/list-----------------");

        List<Events> events = eventsServic.getAllEvents();

        if(events != null){
            return new IntepenResult<>(AuthcEnum.SUCCESS.getCode(), events);
        }
        else{
            return new IntepenResult<>(EventsEnum.LIST_ALL_EVENTS_ERROR.getCode(), EventsEnum.LIST_ALL_EVENTS_ERROR.getError());
        }
    }
}
