package me.xiaoy.mvc.manager.system.parm.dao;

import java.io.Serializable;
import java.util.List;

import me.xiaoy.core.common.page.PageList;
import me.xiaoy.mvc.manager.security.entity.LoginAccount;
import me.xiaoy.mvc.manager.system.parm.entity.SysParam;

public interface SysParamDao {

	Long save(List<SysParam> list);

	PageList<SysParam> query(PageList<SysParam> page, LoginAccount currentUser, SysParam param);

	Serializable save(SysParam param);

	void update(SysParam param);

	SysParam get(Long id);

	List<SysParam> getInitParams();

	String getParam(String key);

	void delete(Long id);

	void delete(Long[] ids);

	List<String> getGroup();

}
