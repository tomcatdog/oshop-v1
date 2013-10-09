<%@page import="me.xiaoy.core.common.page.PageList"%>
<%@page import="me.xiaoy.core.constants.Constant"%>
<%@page import="java.util.*"%>
<%@include file="/WEB-INF/pages/include/taglibs.jsp"%>
<%@page pageEncoding="UTF-8"%>
<%
String rootUrl = String.valueOf(request.getAttribute("url"));
String pageNumberName = Constant.PAGE_NUMBER_NAME;
String pageSizeName = Constant.PAGE_SIZE_NAME;
if(rootUrl != null && rootUrl.length() > 0) {
	rootUrl += "?";
}
Map parameterMap = (Map)request.getAttribute("parameters");
StringBuffer params = new StringBuffer();
if(parameterMap != null) {
	Iterator iter = parameterMap.entrySet().iterator();
	while(iter.hasNext()){
		Map.Entry entry = (Map.Entry)iter.next();
		String key = String.valueOf(entry.getKey());
		if(!key.equals(pageNumberName)) {
			Object val = entry.getValue();
			if(val instanceof String[]) {
				for(String paraValue : (String[])val) {
					params.append(key + "=" + paraValue + "&");
				}
			} else {
				params.append(key + "=" + val + "&");
			}
		}
	}
}
rootUrl += params.toString();
if(rootUrl.endsWith("?")) {
	rootUrl += pageNumberName;
} else {
	rootUrl = rootUrl + "&" + pageNumberName;
}
int maxPageNum = Constant.MAX_PAGE_NUM;
int leftNum = maxPageNum/2;
int rightNum = maxPageNum/2-1;

PageList pagination = ((PageList)request.getAttribute("page"));
int startPage = Math.max(pagination.getNumber() - leftNum, 1);
int endPage = Math.min(pagination.getNumber() + rightNum, pagination.getAllPage());
if(startPage == 1) {
	endPage = Math.min(maxPageNum, pagination.getAllPage());
}
if(endPage == pagination.getAllPage()) {
	startPage = Math.max(endPage-maxPageNum, 1);
}
%>
<%if(pagination.getAllSize()>0) { %>
<div class="pagination">
	<%if(pagination.getNumber() > 1) {%>
		<a class="previous" href="
		<%=rootUrl %>=<%=pagination.getNumber()-1%>">&laquo; 上一页 
		</a>
	<%} %>
	<%if(startPage > 1) { %>
		<a class="number" href="
			<%=rootUrl %>=1"> 1 </a>
		<%if(startPage == 3) {%>
			<a class="number" href="
			<%=rootUrl %>=2"> 2 </a>
		<%} else if(startPage > 2) {%>
			<a class="number" href="
			<%=rootUrl %>=<%=startPage/2+1%>">...</a>
		<%} %>
	<%} %>
	<%
	for(int i=0;i<endPage-startPage+1;i++) {
		int pageIndex = startPage + i;
		if(pagination.getAllPage() > 1) {
			if(pageIndex == pagination.getNumber()) {
	%>
			<span class="number current"
				title="第<%=pageIndex %>页">
				<%=pageIndex %>
			</span>
			<%} else { %>
				<a class="number" href="
				<%=rootUrl %>=<%=pageIndex %>">
					<%=pageIndex %>
				</a>
			<%} %>
		<%} %>
	<%} %>
	<%if(endPage != pagination.getAllPage()) { %>
		<%if(endPage == pagination.getAllPage()-2) { %>
			<a class="number" href="
			<%=rootUrl %>=<%=pagination.getAllPage()-1 %>">
				<%=pagination.getAllPage()-1 %>
			</a>
		<%} else if(endPage < pagination.getAllPage()-1) { %>
			<a class="number" href="
			<%=rootUrl %>=<%=endPage+(pagination.getAllPage()-endPage)/2 %>">...</a>
		<%}%>
		<a class="number" href="
		<%=rootUrl %>=<%=pagination.getAllPage() %>">
			<%=pagination.getAllPage() %>
		</a>
	<%} %>
	<%if(pagination.getNumber() < pagination.getAllPage()) {%>
		<a class="next" href="
		<%=rootUrl %>=<%=pagination.getNumber()+1 %>">下一页 &raquo; 
		</a>
	<%} %>
	共<%=pagination.getAllSize() %>条记录，<%=pagination.getAllPage() %>页
</div>
<%} else { %>
	<span class="view">没有符合条件的记录</span>
<%} %>
<div class="clear"></div>