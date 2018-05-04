package com.jack.intepen.web;

import com.jack.intepen.dto.IntepenResult;
import com.jack.intepen.entity.Events;
import com.jack.intepen.enums.AuthcEnum;
import com.jack.intepen.enums.EventsEnum;
import com.jack.intepen.service.EventsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;

/**
 * Created by 11407 on 20/020.
 */
@RestController
@RequestMapping(value = "/events")
@Api(value = "events", description = "事件列表Api")
public class EventsController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());


    @Autowired
    private EventsService eventsService;

    @RequestMapping(value = "/listall", method = RequestMethod.GET)
    @ApiOperation(value = "/events/listall", notes = "列出所有的事件")
    public IntepenResult<List> getAllEvents(){

        logger.info("------------------GET:/events/listall-----------------");

        List<Events> events = eventsService.getAllEvents();

        if(events.size() == 0){
            events.add(new Events());
        }
        if(events != null){
            return new IntepenResult<>(AuthcEnum.SUCCESS.getCode(), events);
        }
        else{
            return new IntepenResult<>(EventsEnum.LIST_ALL_EVENTS_ERROR.getCode(), EventsEnum.LIST_ALL_EVENTS_ERROR.getError());
        }
    }

    @RequestMapping(value = "/listuncompleted", method = RequestMethod.GET)
    @ApiOperation(value = "/events/listuncompleted", notes = "列出所有未完成的事件")
    public IntepenResult<List> getUncompletedEvents(){

        logger.info("--------------GET:/events/listuncompleted-----------");

        List<Events> events = eventsService.getUncompletedEvents();

        if(events.size() == 0){
            events.add(new Events());
        }
        if(events != null){
            return new IntepenResult<>(AuthcEnum.SUCCESS.getCode(), events);
        }
        else{
            return new IntepenResult<>(EventsEnum.LIST_UNCOMPLETED_EVENTS_ERROR.getCode(), EventsEnum.LIST_UNCOMPLETED_EVENTS_ERROR.getError());
        }
    }

    @RequestMapping(value = "/complete", method = RequestMethod.POST)
    @ApiOperation(value = "/events/complete", notes = "完成事件")
    public IntepenResult<Boolean> completedEvents(@ApiParam(value = "事件id的集合", required = true) Set<Integer> ids){

        logger.info("-------------POST:/events/complete-------------");

        if(eventsService.completedEvents(ids) == true){
            return new IntepenResult<>(AuthcEnum.SUCCESS.getCode(), true);
        }
        else{
            return new IntepenResult<>(EventsEnum.COMPLETE_EVENTS_ERROR.getCode(), EventsEnum.COMPLETE_EVENTS_ERROR.getError());
        }
    }
}
