<%@ page language="java" import="java.util.*,java.net.URL" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!--Header-part-->
<div id="header">
	<h1><a href="dashboard.html">OShop Admin</a></h1>
</div>
<!--close-Header-part--> 

<!--top-Header-menu-->
<div id="user-nav" class="navbar navbar-inverse">
  <ul class="nav">
    <li  class="dropdown" id="profile-messages" ><a title="" href="#" data-toggle="dropdown" data-target="#profile-messages" class="dropdown-toggle"><i class="icon icon-user"></i>  <span class="text">欢迎归来 </span><b class="caret"></b></a>
      <ul class="dropdown-menu">
        <li><a href="#"><i class="icon-user"></i> 个人中心</a></li>
        <li class="divider"></li>
        <li><a href="#"><i class="icon-check"></i> 我的任务</a></li>
        <li class="divider"></li>
        <li><a href="<%=basePath %>admin/security/logout"><i class="icon-key"></i> 安全退出</a></li>
      </ul>
    </li>
    <li class="dropdown" id="menu-messages"><a href="#" data-toggle="dropdown" data-target="#menu-messages" class="dropdown-toggle"><i class="icon icon-envelope"></i> <span class="text">消息</span> <span class="label label-important">5</span> <b class="caret"></b></a>
      <ul class="dropdown-menu">
        <li><a class="sAdd" title="" href="#"><i class="icon-plus"></i> 写消息</a></li>
        <li class="divider"></li>
        <li><a class="sInbox" title="" href="#"><i class="icon-envelope"></i> 收件箱</a></li>
        <li class="divider"></li>
        <li><a class="sOutbox" title="" href="#"><i class="icon-arrow-up"></i> 发件箱</a></li>
        <li class="divider"></li>
        <li><a class="sTrash" title="" href="#"><i class="icon-trash"></i> 垃圾箱</a></li>
      </ul>
    </li>
    <li class=""><a title="" href="#"><i class="icon icon-cog"></i> <span class="text">设置</span></a></li>
    <li class=""><a title="" href="<%=basePath %>admin/security/logout"><i class="icon icon-share-alt"></i> <span class="text">安全退出</span></a></li>
  </ul>
</div>
<!--close-top-Header-menu-->
<!--start-top-serch-->
<div id="search">
  <input type="text" placeholder="Search here..."/>
  <button type="submit" class="tip-bottom" title="Search"><i class="icon-search icon-white"></i></button>
</div>
<!--close-top-serch-->
<!--sidebar-menu-->
<div id="sidebar"><a href="<%=basePath %>admin/security/index" class="visible-phone"><i class="icon icon-home"></i> 首页</a>
  <ul>
    <li class="active"><a href="<%=basePath %>admin/security/index"><i class="icon icon-home"></i> <span> 首页</span></a> </li>
    <li class="submenu"> <a href="#"><i class="icon icon-th-list"></i> <span> 商品管理</span> <span class="label label-important">2</span></a>
      <ul>
        <li><a id="_goods_info_List" href="<%=basePath%>goods/info/info_List">商品管理</a></li>
        <li><a id="_goods_List" href="<%=basePath%>goods/classification/goods_List">分类管理</a></li>
      </ul>
    </li>
    <li class="submenu"> <a href="#"><i class="icon icon-reorder"></i> <span>订单管理</span> <span class="label label-important">5</span></a>
      <ul>
        <li><a href="#">订单管理</a></li>
        <li><a href="#">收货人管理</a></li>
        <li><a href="#">支付及配送方式</a></li>
        <li><a href="#">发票信息</a></li>
        <li><a href="#">退货管理</a></li>
      </ul>
    </li>
    <li class="submenu"> <a href="#"><i class="icon icon-user"></i> <span>会员管理</span> <span class="label label-important">5</span></a>
      <ul>
        <li><a href="#">会员管理</a></li>
        <li><a href="#">会员等级</a></li>
        <li><a href="#">会员注册项</a></li>
        <li><a href="#">评论管理</a></li>
        <li><a href="#">咨询管理</a></li>
      </ul>
    </li>
    <li class="submenu"> <a href="#"><i class="icon icon-cloud"></i> <span>内容管理</span> <span class="label label-important">9</span></a>
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
    <li class="submenu"> <a href="#"><i class="icon icon-signal"></i> <span>统计报表</span> <span class="label label-important">5</span></a>
      <ul>
        <li><a href="#">预存款</a></li>
        <li><a href="#">访问统计</a></li>
        <li><a href="#">销售统计</a></li>
        <li><a href="#">热销排行</a></li>
        <li><a href="#">会员消费统计</a></li>
      </ul>
    </li>
    <li class="submenu"> <a href="#"><i class="icon icon-desktop"></i> <span>系统管理</span></a>
      <ul>
        <li><a href="<%=basePath %>admin/system/param/list">参数设置</a></li>
        <li><a href="<%=basePath %>admin/security/account/list">管理员</a></li>
        <li><a href="<%=basePath %>admin/security/role/list">角色管理</a></li>
        <li><a id="system_log" href="<%=basePath %>admin/system/log/list">日志管理</a></li>
      </ul>
    </li>
  </ul>
</div>
<!--sidebar-menu-->
