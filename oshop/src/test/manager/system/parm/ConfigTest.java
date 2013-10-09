package test.manager.system.parm;

import me.xiaoy.core.config.Config;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;


@RunWith(SpringJUnit4ClassRunner.class)	
@ContextConfiguration(locations="classpath:spring-core.xml")
@Transactional
@TransactionConfiguration(transactionManager = "txManager", defaultRollback = true)
public class ConfigTest implements Runnable{
	
	@Test
	public void testConfig() {
		for(int i=0;i<10;i++) {
			new ConfigTest().run();
		}
	}

	public void run() {
		System.out.println(Thread.currentThread().getName()+":"+Config.getInstance().get("test"));
	}

}
