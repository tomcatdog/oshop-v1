package test.manager.security;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import me.xiaoy.mvc.manager.security.entity.LoginAccount;
import me.xiaoy.mvc.manager.security.service.LoginAccountService;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

@RunWith(SpringJUnit4ClassRunner.class)	
@ContextConfiguration(locations="classpath:spring-core.xml")
@Transactional
@TransactionConfiguration(transactionManager = "txManager", defaultRollback = true)
public class LoginAccountTest {
	
	@Resource
	private LoginAccountService loginAccountService;
	private List<LoginAccount> list = new ArrayList<LoginAccount>();
	
	@Before
	public void init() {
		for(int i=0;i<10;i++) {
			list.add(new LoginAccount("q" + i, "123", 1, null, null, 1, 0, null, null, new Date()));
		}
		list.add(new LoginAccount("q123", "123", 1, null, null, 1, 0, null, null, new Date()));
	}
	
	@Test
	@Rollback(false)
	public void testSaveLoginAccountList() {
		Long result = loginAccountService.save(list);
		Assert.isTrue(result > 0);
	}
	
}
