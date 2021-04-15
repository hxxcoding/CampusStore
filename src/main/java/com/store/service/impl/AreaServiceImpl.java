package com.store.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.store.dao.AreaDao;
import com.store.entity.Area;
import com.store.service.AreaService;

@Service
public class AreaServiceImpl implements AreaService {
	@Autowired
	private AreaDao areaDao;

	private static String AREALISTKEY = "arealist";

	@Override
	public List<Area> getAreaList() {
		return areaDao.queryArea();
	}
}
