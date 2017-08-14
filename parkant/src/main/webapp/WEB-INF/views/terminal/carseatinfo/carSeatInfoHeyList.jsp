<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>车位信息功能管理</title>
	<meta name="decorator" content="default"/>
	<%@include file="/WEB-INF/views/include/treetable.jsp" %>
	<script type="text/javascript">
		$(document).ready(function() {
			var tpl = $("#treeTableTpl").html().replace(/(\/\/\<!\-\-)|(\/\/\-\->)/g,"");
			var data = ${fns:toJson(list)}, ids = [], rootIds = [];
			for (var i=0; i<data.length; i++){
				ids.push(data[i].id);
			}
			ids = ',' + ids.join(',') + ',';
			for (var i=0; i<data.length; i++){
				if (ids.indexOf(','+data[i].parentId+',') == -1){
					if ((','+rootIds.join(',')+',').indexOf(','+data[i].parentId+',') == -1){
						rootIds.push(data[i].parentId);
					}
				}
			}
			for (var i=0; i<rootIds.length; i++){
				addRow("#treeTableList", tpl, data, rootIds[i], true);
			}
			$("#treeTable").treeTable({expandLevel : 5});
		});
		function addRow(list, tpl, data, pid, root){
			for (var i=0; i<data.length; i++){
				var row = data[i];
				if ((${fns:jsGetVal('row.parentId')}) == pid){
					$(list).append(Mustache.render(tpl, {
						dict: {
						blank123:0}, pid: (root?0:pid), row: row
					}));
					addRow(list, tpl, data, row.id);
				}
			}
		}
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/carseatinfo/carSeatInfo/">车位信息功能列表</a></li>
		<shiro:hasPermission name="carseatinfo:carSeatInfo:edit"><li><a href="${ctx}/carseatinfo/carSeatInfo/form">车位信息功能添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="carSeatInfo" action="${ctx}/carseatinfo/carSeatInfo/" method="post" class="breadcrumb form-search">
		<ul class="ul-form">
			<li><label>车位名称：</label>
				<input class="form-control" type="text" id="parkingName" name="parkingName" max="10"  >
			</li>
			<li><label>拥有者ID：</label>
				<input class="form-control" type="text" id="uId" name="uId" max="64"  >
			</li>
			<li><label>车位锁编号：</label>
				<input class="form-control" type="text" id="parkingNum" name="parkingNum" max="30"  >
			</li>
			<li><label>停车场ID：</label>
				<input class="form-control" type="text" id="pId" name="pId" max="64"  >
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="treeTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>车位名称</th>
				<th>拥有者ID</th>
				<th>车位锁编号</th>
				<th>停车场ID</th>
				<shiro:hasPermission name="carseatinfo:carSeatInfo:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody id="treeTableList"></tbody>
	</table>
	<script type="text/template" id="treeTableTpl">
		<tr id="{{row.id}}" pId="{{pid}}">
			<td><a href="${ctx}/carseatinfo/carSeatInfo/form?id={{row.id}}">
				{{row.parkingName}}
			</a></td>
			<td>
				{{row.uId}}
			</td>
			<td>
				{{row.parkingNum}}
			</td>
			<td>
				{{row.pId}}
			</td>
			<shiro:hasPermission name="carseatinfo:carSeatInfo:edit"><td>
   				<a href="${ctx}/carseatinfo/carSeatInfo/form?id={{row.id}}">修改</a>
				<a href="${ctx}/carseatinfo/carSeatInfo/delete?id={{row.id}}" onclick="return confirmx('确认要删除该车位信息功能及所有子车位信息功能吗？', this.href)">删除</a>
				<a href="${ctx}/carseatinfo/carSeatInfo/form?parent.id={{row.id}}">添加下级车位信息功能</a> 
			</td></shiro:hasPermission>
		</tr>
	</script>
</body>
</html>