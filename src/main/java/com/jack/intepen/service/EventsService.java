package com.jack.intepen.service;

import com.jack.intepen.dao.EventsDao;
import com.jack.intepen.entity.Events;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

/**
 * Created by 11407 on 20/020.
 */
@Service
public class EventsService {

    @Autowired
    EventsDao eventsDao;

    public List<Events> getUncompletedEvents(){
        return eventsDao.queryUncompletedEvents();
    }

    @Transactional
    public boolean completedEvents(Set<Integer> ids){
        for(Integer id : ids){
            if(eventsDao.completedEvent(id) != 1){
                return false;
            }
        }
        return true;
    }

    public List<Events> getAllEvents(){
        return eventsDao.queryAllEvents();
    }
}
