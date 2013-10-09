package test.manager.system.log;

import java.util.Date;

import javax.annotation.Resource;

import me.xiaoy.mvc.manager.security.entity.LoginAccount;
import me.xiaoy.mvc.manager.system.log.service.LogService;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

/**
 * 需要进行事务测试，请加上@Transactional
 * 测试数据不加入数据库中加入@Rollback(true)或者使用defaultRollback = true
 * @author qq
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)	
@ContextConfiguration(locations="classpath:spring-core.xml")
@Transactional
@TransactionConfiguration(transactionManager = "txManager", defaultRollback = true)
public class LogTest {
	
	@Resource
	private LogService logService;

	@Test
	@Rollback(false)
	public void testSaveLog() {
		LoginAccount account = new LoginAccount();
		account.setCreateTime(new Date());
		account.setLoginName("test");
		account.setPassword("test");
		account.setId(1000L);
		
		String ip = "192.168.1.1";
		String operation = "test testSaveLog";
		logService.save(account, ip, operation);
	}

}
