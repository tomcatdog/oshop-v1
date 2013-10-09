package me.xiaoy.mvc.manager.merchant.service.impl;

import java.io.Serializable;

import javax.annotation.Resource;

import me.xiaoy.mvc.manager.merchant.dao.MerchantDao;
import me.xiaoy.mvc.manager.merchant.entity.Merchant;
import me.xiaoy.mvc.manager.merchant.service.MerchantService;

import org.springframework.stereotype.Service;

@Service(value="merchantService")
public class MerchantServiceImpl implements MerchantService{
	
	@Resource
	private MerchantDao merchantDao;
	
	public Serializable save(Merchant m) {
		return merchantDao.save(m);
	}

}
