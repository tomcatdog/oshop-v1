package test.manager.system.parm;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import me.xiaoy.core.common.page.PageList;
import me.xiaoy.core.constants.Constant;
import me.xiaoy.mvc.manager.system.parm.entity.SysParam;
import me.xiaoy.mvc.manager.system.parm.service.SysParamService;

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
public class SysParamTest {
	
	@Resource
	private SysParamService sysParamService;
	
	private List<SysParam> list = new ArrayList<SysParam>();
	
	@Before
	public void init() {
		list.add(new SysParam(Constant.SYS_PARAM_GROUP_SECURITY, "max_login_count", "5", 1, 1, "登陆失败次数限制， 超出则一定时间内不允许再次登陆", new Date()));
		list.add(new SysParam(Constant.SYS_PARAM_GROUP_SECURITY, "max_login_time", "1800000", 1, 1, "登陆失败超出次数限制后， 间隔多少时间允许再次登陆， 单位为毫秒， 30分钟即为30*60*1000", new Date()));
		list.add(new SysParam(Constant.SYS_PARAM_GROUP_SECURITY, "max_login_time_name", "半小时", 1, 1, "MAX_LOGIN_TIME参数值对应的中文", new Date()));
		list.add(new SysParam(Constant.SYS_PARAM_GROUP_UPLOADFILE, "upload_file_path", Constant.SYS_PARAM_UPLOADFILE, 0,  1, "上传文件路径", new Date()));
	}
	
	@Test
	@Rollback(false)
	public void testSaveSysParam() {
		Long result = sysParamService.save(list);
		Assert.isTrue(result > 0);
	}
	
	@Test
	public void testList() {
		PageList<SysParam> page = new PageList<SysParam>();
		page = sysParamService.query(page, null, null);
		for(SysParam p : page.getList()) {
			System.out.println(p.getParamName()+","+p.getParamValue());
		}
	}
	
	@Test
	public void testInitParams() {
		List<SysParam> list = sysParamService.getInitParams();
		for(SysParam p : list) {
			System.out.println(p.getParamName()+"|"+p.getParamValue());
		}
	}
	
}
