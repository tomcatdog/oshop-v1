package test.manager.security;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import me.xiaoy.mvc.manager.security.entity.Role;
import me.xiaoy.mvc.manager.security.service.RoleService;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)	
@ContextConfiguration(locations="classpath:spring-core.xml")
@Transactional
@TransactionConfiguration(transactionManager = "txManager", defaultRollback = true)
public class RoleTest {
	
	@Resource
	RoleService roleService;
	
	private static List<Role> roles = new ArrayList<Role>();
	
	@Before
	public void init() {
		roles.add(new Role("super", "超级管理员， 可管理所有内容", null, null));
		roles.add(new Role("merchant", "店铺总管理员", null, null));
		roles.add(new Role("merchant2", "店铺2级管理员， 可进行商品、订单管理", null, null));
		roles.add(new Role("merchant3", "店铺3级管理员， 可进行订单录入操作", null, null));
		roles.add(new Role("pay", "运营公司结算人员， 可进行对账操作", null, null));
	}

	@Test
	@Rollback(false)
	public void testSaveRoleList() {
		Long result = roleService.save(roles);
		Assert.assertTrue(result>0);
	}

}
