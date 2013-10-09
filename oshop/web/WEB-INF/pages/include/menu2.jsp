<%@ page language="java" import="java.util.*,java.net.URL" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<div id="sidebar">
	<div id="sidebar-wrapper">
		<a href="#"><img id="logo" src="<%=basePath %>resources/images/logo.png" alt="logo" />
		</a>
		<div id="profile-links">
			<a href="<%=basePath %>" target="_blank">查看站点</a> | 
			<a href="<%=basePath %>admin/security/logout">安全退出</a>
		</div>
		<ul id="main-nav">
			<li><a id="dashboard" href="#/" class="nav-top-item no-submenu"> 仪表盘 </a></li>
			<li>
				<a id="goods" href="#" class="nav-top-item">商品管理 </a>
				<ul id="goods_sub">
					<li><a id="_goods_info_List" class="" href="<%=basePath%>goods/info/info_List">商品管理</a></li>
					<li><a id="_goods_List"href="<%=basePath%>goods/classification/goods_List">分类管理</a></li>
					<li><a href="#">商品属性</a></li>
					<li><a href="#">库存管理</a></li>
					<li><a href="#">品牌管理</a></li>
				</ul>
			</li>
			<li>
				<a href="#" class="nav-top-item">订单管理</a>
				<ul>
					<li><a href="#">订单管理</a></li>
					<li><a href="#">收货人管理</a></li>
					<li><a href="#">支付及配送方式</a></li>
					<li><a href="#">发票信息</a></li>
					<li><a href="#">退货管理</a></li>
				</ul>
			</li>
			<li>
				<a href="#" class="nav-top-item">会员管理</a>
				<ul>
					<li><a href="#">会员管理</a></li>
					<li><a href="#">会员等级</a></li>
					<li><a href="#">会员注册项</a></li>
					<li><a href="#">评论管理</a></li>
					<li><a href="#">咨询管理</a></li>
				</ul>
			</li>
			<li>
				<a href="#" class="nav-top-item">内容管理</a>
				<ul>
					<li><a href="#">导航管理</a></li>
					<li><a href="#">文章管理</a></li>
					<li><a href="#">文章分类</a></li>
					<li><a href="#">地区管理</a></li>
					<li><a href="#">标签管理</a></li>
					<li><a href="#">友情链接</a></li>
					<li><a href="#">广告管理</a></li>
					<li><a href="#">广告位</a></li>
					<li><a href="#">静态化</a></li>
				</ul>
			</li>
			<li>
				<a href="#" class="nav-top-item">统计管理</a>
				<ul>
					<li><a href="#">预存款</a></li>
					<li><a href="#">访问统计</a></li>
					<li><a href="#">销售统计</a></li>
					<li><a href="#">热销排行</a></li>
					<li><a href="#">会员消费统计</a></li>
				</ul>
			</li>
			<li>
				<a id="sys_mgr" href="#" class="nav-top-item">系统管理</a>
				<ul id="sys_mgr_sub">
					<li><a id="param_mgr" href="<%=basePath %>admin/system/param/list">参数设置</a></li>
					<li><a href="#">配送方式</a></li>
					<li><a href="#">支付方式</a></li>
					<li><a href="#">支付插件</a></li>
					<li><a id="admin_mgr" href="<%=basePath %>admin/security/account/list">管理员</a></li>
					<li><a id="role_mgr" href="<%=basePath %>admin/security/role/list">角色管理</a></li>
					<li><a id="log_mgr" href="<%=basePath %>admin/system/log/list">日志管理</a></li>
				</ul>
			</li>
		</ul>
	</div>
</div>
