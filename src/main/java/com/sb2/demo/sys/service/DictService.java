package com.sb2.demo.sys.service;


import com.sb2.demo.common.base.dao.GenericDao;
import com.sb2.demo.common.base.service.GenericService;
import com.sb2.demo.sys.dao.DictDao;
import com.sb2.demo.sys.entity.Dict;
import com.sb2.demo.sys.entity.QueryDict;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *@author linzf
 **/
@Service("dictService")
@Transactional(rollbackFor={IllegalArgumentException.class})
public class DictService extends GenericService<Dict, QueryDict> {
	@Autowired
	@SuppressWarnings("SpringJavaAutowiringInspection")
	private DictDao dictDao;
	@Override
	protected GenericDao<Dict, QueryDict> getDao() {
		return dictDao;
	}
}