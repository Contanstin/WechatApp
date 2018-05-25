<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="content-type" content="text/html; charset=utf-8" />
    <title></title>
    <link href="${base}/resources/css/common.css" rel="stylesheet" type="text/css" />
    <link href="${base}/resources/css/bootstrap.css" rel="stylesheet" type="text/css"/>
    <link href="${base}/resources/css/jquery.datetimepicker.css" rel="stylesheet" type="text/css" />
    <link href="${base}/resources/css/layer/skin/layer.css" rel="stylesheet" type="text/css" />
    <script type="text/javascript" src="${base}/resources/js/jquery.js"></script>
    <script type="text/javascript" src="${base}/resources/js/list.js"></script>
<script type="text/javascript">
</script>
</head>
<body>
	<div class="path">

	</div>
	<form id="listForm" action="list.jhtml" method="get">
        <div class="bar">
            <div class="menuWrap">
                <div class="search">
                    <button type="button" onclick="location.href='add.jhtml'">新增</button>
                </div>
            </div>
        </div>
		<table id="listTable" class="list" style="text-align:center">
			<tr>
				<th>名称</th>
                <th>是否内置</th>
				<th>描述</th>
                <th>创建日期</th>
				<th>操作</th>
			</tr>
			[#list list as role]
				<tr>
					<td>
						${role.name}
					</td>
					<td>
						${message(role.isSystem?string('admin.common.true', 'admin.common.false'))}
					</td>
					<td>
						${role.description}
					</td>
					<td>
						${(role.createTime?string("yyyy-MM-dd HH:mm:ss"))!}
					</td>
					<td>
						<a href="edit.jhtml?id=${role.id}">${message("admin.common.edit")}</a>
                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                        <a href="delete.jhtml?id=${role.id}">[删除]</a>
					</td>
				</tr>
			[/#list]
		</table>
	</form>
</body>
</html>