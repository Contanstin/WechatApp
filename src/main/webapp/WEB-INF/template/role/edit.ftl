<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xmlns="http://www.w3.org/1999/html">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>权限管理</title>
    <link href="${base}/resources/css/common.css" rel="stylesheet" type="text/css" />
    <link href="${base}/resources/css/bootstrap.css" rel="stylesheet" type="text/css"/>
    <link href="${base}/resources/css/jquery.datetimepicker.css" rel="stylesheet" type="text/css" />
    <link href="${base}/resources/css/layer/skin/layer.css" rel="stylesheet" type="text/css" />
    <script type="text/javascript" src="${base}/resources/js/jquery.js"></script>
    <script type="text/javascript" src="${base}/resources/js/list.js"></script>
<style type="text/css">
.authorities label {
	min-width: 120px;
	_width: 120px;
	display: block;
	float: left;
	padding-right: 4px;
	_white-space: nowrap;
}
</style>
<script type="text/javascript">
$().ready(function() {

	var $inputForm = $("#inputForm");
	var $selectAll = $("#inputForm .selectAll");
	
	$selectAll.click(function() {
		var $this = $(this);
		var $thisCheckbox = $this.closest("tr").find(":checkbox");
		if ($thisCheckbox.filter(":checked").size() > 0) {
			$thisCheckbox.prop("checked", false);
		} else {
			$thisCheckbox.prop("checked", true);
		}
		return false;
	});
	
//	// 表单验证
//	$inputForm.validate({
//		rules: {
//			name: "required",
//			authorities: "required"
//		}
//	});
	
});
</script>
</head>
<body>
	<div class="path">
		<a href="${base}/admin/common/index.jhtml">权限管理</a> &raquo; 角色管理
	</div>
	<form id="inputForm" action="save.jhtml" method="post">
		<input type="hidden" name="id" value="${role.id}" />
		<table class="input">
			<tr>
				<th>
					<span class="requiredField">*</span>角色名:
				</th>
				<td>
					<input type="text" name="name" class="text" value="${role.name}" maxlength="200" />
				</td>
			</tr>
			<tr>
				<th>
					角色描述:
				</th>
				<td>
					<input type="text" name="description" class="text" value="${role.description}" maxlength="200" />
				</td>
			</tr>
			<tr>
				<td colspan="2">
					&nbsp;
				</td>
			</tr>
			[#list menu as smenu]
				[#if smenu_index!=0]
					</span>
					</td>
					</tr>
				[/#if]
				<tr class="authorities">
					<th>
						<a href="javascript:;" class="selectAll" title="${message("admin.role.selectAll")}">${smenu.menuName}</a>
					</th>
					<td>
						<span class="fieldSet">
				[#list smenu.submenu as submenus]
                    <label>
						<input type="checkbox" name="authorities" value="${submenus.id};${submenus.parentId};${submenus.authorities}" [#if rolemenus?contains(","+submenus.id+",")] checked="checked"[/#if] />${submenus.menuName}
					</label>
				[/#list]
			[/#list]
			[#if menu?? && (menu?size > 0) ]
            	</span></td></tr>
			[/#if]
			[#if role.isSystem]
				<tr>
					<th>
						&nbsp;
					</th>
					<td>
						<span class="tips">${message("admin.role.editSystemNotAllowed")}</span>
					</td>
				</tr>
			[/#if]
			<tr>
				<th>
					&nbsp;
				</th>
				<td>
					<input type="submit" class="button" value="提交" [#if role.isSystem] disabled="disabled"[/#if] />
					<input type="button" class="button" value="返回" onclick="location.href='list.jhtml'" />
				</td>
			</tr>
		</table>
	</form>
</body>
</html>