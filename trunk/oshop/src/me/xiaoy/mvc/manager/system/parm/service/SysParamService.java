package me.xiaoy.mvc.manager.system.parm.service;

import java.io.Serializable;
import java.util.List;

import me.xiaoy.core.common.page.PageList;
import me.xiaoy.mvc.manager.security.entity.LoginAccount;
import me.xiaoy.mvc.manager.system.parm.entity.SysParam;

public interface SysParamService {

	Long save(List<SysParam> list);

	PageList<SysParam> query(PageList<SysParam> page, LoginAccount currentUser, SysParam param);

	Serializable save(SysParam param);

	SysParam get(Long id);

	void update(SysParam param);
	
	List<SysParam> getInitParams();

	String getParam(String key);

	void delete(LoginAccount currentUser, String ip, Long id);

	void delete(LoginAccount currentUser, String ip, Long[] ids);

	List<String> getGroup();

}
