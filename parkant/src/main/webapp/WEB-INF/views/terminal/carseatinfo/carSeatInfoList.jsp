<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>车位信息功能管理</title>
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
		<li class="active"><a href="${ctx}/carseatinfo/carSeatInfo/">车位信息功能列表</a></li>
		<shiro:hasPermission name="carseatinfo:carSeatInfo:edit"><li><a href="${ctx}/carseatinfo/carSeatInfo/form">车位信息功能添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="carSeatInfo" action="${ctx}/carseatinfo/carSeatInfo/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>车位名称：</label>
				<input path="parkingName"  maxlength="10" class="input-medium"/>
			</li>
			<li><label>拥有者ID：</label>
				<input path="uId"  maxlength="64" class="input-medium"/>
			</li>
			<li><label>车位锁编号：</label>
				<input path="parkingNum"  maxlength="30" class="input-medium"/>
			</li>
			<li><label>停车场ID：</label>
				<input path="pId"  maxlength="64" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>车位名称</th>
				<th>拥有者ID</th>
				<th>车位锁编号</th>
				<th>停车场ID</th>
				<shiro:hasPermission name="carseatinfo:carSeatInfo:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="carSeatInfo">
			<tr>
				<td><a href="${ctx}/carseatinfo/carSeatInfo/form?id=${carSeatInfo.id}">
					${carSeatInfo.parkingName}
				</a></td>
				<td>
					${carSeatInfo.uId}
				</td>
				<td>
					${carSeatInfo.parkingNum}
				</td>
				<td>
					${carSeatInfo.pId}
				</td>
				<shiro:hasPermission name="carseatinfo:carSeatInfo:edit"><td>
    				<a href="${ctx}/carseatinfo/carSeatInfo/form?id=${carSeatInfo.id}">修改</a>
					<a href="${ctx}/carseatinfo/carSeatInfo/delete?id=${carSeatInfo.id}" onclick="return confirmx('确认要删除该车位信息功能吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>