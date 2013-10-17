package me.xiaoy.mvc.manager.system.log.dao;

import java.io.Serializable;

import me.xiaoy.core.common.page.PageList;
import me.xiaoy.mvc.manager.system.log.entity.SysLog;


public interface SysLogDao {

	Serializable save(SysLog t);

	PageList<SysLog> list(PageList<SysLog> page,SysLog t);

	void delete(Long[] ids);

	void delete(Long id);


}
