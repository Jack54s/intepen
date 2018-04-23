package com.jack.intepen.dao;

import com.jack.intepen.entity.Events;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by 11407 on 13/013.
 */
@Repository
public interface EventsDao {
    int insertEvent(Events event);
    int deleteEvent(int id);
    int updateEvent(Events event);
    List<Events> queryAllEvents();
    List<Events> queryUncompletedEvents();
    int completedEvent(int id);
}
