<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>停车信息功能管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			
		});
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        }
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/parkinfo/parkingInfo/">停车信息功能列表</a></li>
		<shiro:hasPermission name="parkinfo:parkingInfo:edit"><li><a href="${ctx}/parkinfo/parkingInfo/form">停车信息功能添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="parkingInfo" action="${ctx}/parkinfo/parkingInfo/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>收费标准：</label>
				<input path="sDesc"  maxlength="50" class="input-medium"/>
			</li>
			<li><label>停车场名称：</label>
				<input path="sName"  maxlength="30" class="input-medium"/>
			</li>
			<li><label>所属省份：</label>
				<input path="province"  maxlength="10" class="input-medium"/>
			</li>
			<li><label>所属城市：</label>
				<input path="city"  maxlength="10" class="input-medium"/>
			</li>
			<li><label>具体位置：</label>
				<input path="address"  maxlength="50" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<shiro:hasPermission name="parkinfo:parkingInfo:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="parkingInfo">
			<tr>
				<shiro:hasPermission name="parkinfo:parkingInfo:edit"><td>
    				<a href="${ctx}/parkinfo/parkingInfo/form?id=${parkingInfo.id}">修改</a>
					<a href="${ctx}/parkinfo/parkingInfo/delete?id=${parkingInfo.id}" onclick="return confirmx('确认要删除该停车信息功能吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>