package com.jack.intepen.dao;

import com.jack.intepen.entity.OldMan;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by 11407 on 30/030.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class OldManDaoTest {

    @Autowired
    private OldManDao oldManDao;
    @Test
    public void queryOldMan() throws Exception {
        List<OldMan> oldManList = oldManDao.queryOldMan();
        assertEquals(2, oldManList.size());
    }

    @Test
    public void queryOldManById() throws Exception {

    }

    @Test
    public void insertOldMan() throws Exception {

    }

    @Test
    public void updateOldMan() throws Exception {

    }

    @Test
    public void deleteOldMan() throws Exception {

    }

}