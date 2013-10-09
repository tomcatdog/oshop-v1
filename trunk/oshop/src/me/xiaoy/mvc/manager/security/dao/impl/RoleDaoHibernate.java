package me.xiaoy.mvc.manager.security.dao.impl;

import java.util.ArrayList;
import java.util.List;

import me.xiaoy.core.base.BaseDao;
import me.xiaoy.core.common.page.PageList;
import me.xiaoy.core.utils.StringUtil;
import me.xiaoy.mvc.manager.security.dao.RoleDao;
import me.xiaoy.mvc.manager.security.entity.LoginAccount;
import me.xiaoy.mvc.manager.security.entity.Role;

import org.springframework.stereotype.Repository;

@Repository("roleDao")
public class RoleDaoHibernate extends BaseDao<Role> implements RoleDao {

	@Override
	public PageList<Role> query(PageList<Role> page,
			LoginAccount currentUser, Role role) {
		StringBuffer hql = new StringBuffer("from " + entityClass.getName() + " as t where 1=1");
		List<Object> params = new ArrayList<Object>();
		if(currentUser == null) {
			//开发期间注释
//			return null;
		} else {
			//超级管理员为平台使用用户， 拥有所有权限， 查询不做限制， 否则只能查询所属用户的内容
			if(!currentUser.isSuperAdmin()) {
				//TODO 查询全部角色
			}
		}
		if(role != null) {
			if(StringUtil.isNotEmpty(role.getRoleName())) {
				hql.append(" and t.roleName like ?");
				params.add("%"+role.getRoleName()+"%");
			}
		}
		return super.query(page, hql.toString(), params.toArray());
	}

	@Override
	public void delete(Long id) {
		super.delete(id);
	}

	@Override
	public Role get(Long id) {
		return super.get(id);
	}


}
