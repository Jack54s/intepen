package com.jack.intepen.dao;

import com.jack.intepen.entity.Elder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by 11407 on 30/030.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ElderDaoTest {

    @Autowired
    private ElderDao elderDao;
    @Test
    public void queryElder() throws Exception {
        List<Elder> elderList = elderDao.queryElder();
        assertEquals(2, elderList.size());
    }

    @Test
    public void queryElderById() throws Exception {

    }

    @Test
    public void insertElder() throws Exception {

    }

    @Test
    public void updateElder() throws Exception {

    }

    @Test
    public void deleteElder() throws Exception {

    }

}