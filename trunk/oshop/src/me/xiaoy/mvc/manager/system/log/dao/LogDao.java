package me.xiaoy.mvc.manager.system.log.dao;

import java.io.Serializable;

import me.xiaoy.core.common.page.PageList;
import me.xiaoy.mvc.manager.system.log.entity.Log;


public interface LogDao {

	Serializable save(Log t);

	PageList<Log> list(PageList<Log> page,Log t);

	void delete(Long[] ids);

	void delete(Long id);


}
