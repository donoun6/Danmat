package com.cross.Danmat.TwentyHills.Service;

import java.util.List;
import java.util.Random;

import com.cross.Danmat.TwentyHills.Dao.TwentyHillsDao;
import com.cross.Danmat.TwentyHills.Domain.TwentyHills;

public class TwentyHillsServiceImpl implements TwentyHillsService {
	Random rd = new Random();
	TwentyHillsDao twentyHillsDao;
	
	public TwentyHillsServiceImpl(TwentyHillsDao twentyHillsDao) {
		this.twentyHillsDao = twentyHillsDao;
	}



	@Override
	public TwentyHills load_word() {
		List<TwentyHills> twentyHills_list = twentyHillsDao.findAll();
		TwentyHills twentyHills = twentyHillsDao.findAllByWid(rd.nextInt(twentyHills_list.size()));
		return twentyHills;
	}
}
