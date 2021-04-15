package com.store.dao;

import com.store.BaseTest;
import com.store.entity.Area;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import org.springframework.beans.factory.annotation.Autowired;

public class AreaDaoTest extends BaseTest {
    @Autowired
    private AreaDao areaDao;

    @Test
    public void testQueryArea() {
        List<Area> areaList = areaDao.queryArea();
        assertEquals(2,areaList.size());
    }
}
