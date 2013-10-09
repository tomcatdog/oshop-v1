package test.manager.security;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import me.xiaoy.mvc.manager.security.entity.Permission;
import me.xiaoy.mvc.manager.security.service.PermissionService;

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
public class PermissionTest {

	@Resource
	private PermissionService permissionService;
	
	private static List<Permission> list = new ArrayList<Permission>();
	
	@Before
	public void init() {
		list.add(new Permission("goods_add", "添加商品", "/goods/add", null, 1, null, null, null, null));
		list.add(new Permission("goods_view", "查看商品", "/goods/add", null, 2, null, null, null, null));
		list.add(new Permission("goods_update", "更新商品", "/goods/add", null, 3, null, null, null, null));
		list.add(new Permission("goods_delete", "删除商品", "/goods/add", null, 4, null, null, null, null));
		
		list.add(new Permission("order_add", "添加订单", null, "order_add", 1, null, null, null, null));
		list.add(new Permission("order_view", "查看订单", null, "order_view", 2, null, null, null, null));
		list.add(new Permission("order_update", "更新订单", null, "order_update", 3, null, null, null, null));
		list.add(new Permission("order_delete", "删除订单", null, "order_delete", 4, null, null, null, null));
	}
	
	@Test
	@Rollback(false)
	public void testSavePermissionList() {
		Long result = permissionService.save(list);
		Assert.isTrue(result > 0);
	}

}
