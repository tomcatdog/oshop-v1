package me.xiaoy.mvc.manager.merchant.dao.impl;

import org.springframework.stereotype.Repository;

import me.xiaoy.core.base.BaseDao;
import me.xiaoy.mvc.manager.merchant.dao.MerchantDao;
import me.xiaoy.mvc.manager.merchant.entity.Merchant;

@Repository(value="merchantDao")
public class MerchantDaoHibernate extends BaseDao<Merchant> implements MerchantDao{

}
