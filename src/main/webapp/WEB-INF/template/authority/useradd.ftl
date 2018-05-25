<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="content-type" content="text/html; charset=utf-8" />
    <link href="${base}/resources/css/common.css" rel="stylesheet" type="text/css" />
    <link href="${base}/resources/css/bootstrap.css" rel="stylesheet" type="text/css"/>
    <link href="${base}/resources/css/jquery.datetimepicker.css" rel="stylesheet" type="text/css" />
    <link href="${base}/resources/css/layer/skin/layer.css" rel="stylesheet" type="text/css" />
    <script type="text/javascript" src="${base}/resources/js/jquery.js"></script>
    <script type="text/javascript" src="${base}/resources/js/list.js"></script>
    <script type="text/javascript" src="${base}/resources/js/jquery.validate.js"></script>
    <style type="text/css">
        .roles label {
            width: 150px;
            display: block;
            float: left;
            padding-right: 6px;
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

            // 表单验证
            $inputForm.validate({
                rules: {
                    userName: {
                        required: true,
                        pattern: /^[0-9a-z_A-Z\u4e00-\u9fa5]+$/,
                        minlength: 2,
                        maxlength: 20
                    },
                    password: {
                        required: true,
                        pattern: /^[^\s&\"<>]+$/,
                        minlength: 4,
                        maxlength: 20
                    },
                    roleId: "required"
                },
                messages: {
                    userName: {
                        required:"用户名必须输入",
                        pattern: "非法字符"
                    },
                    password: {
                        required:"请输入密码",
                        pattern: "非法字符"
                    },
                    roleId: {
                        required:"请选择一个角色"
                    }
                }
            });

        });
    </script>
</head>
<body>
<div class="path">
</div>
<form id="inputForm" action="userAdd.jhtml" method="post">
    <table class="input">
        <tr>
            <th>
                <span class="requiredField">*</span>用户名:
            </th>
            <td>
                <input type="text" name="userName" class="text" maxlength="20" />
            </td>
        </tr>
        <tr>
            <th>
                <span class="requiredField">*</span>密码:
            </th>
            <td>
                <input type="text" id="password" name="password" class="text" maxlength="20" />
            </td>
        </tr>
        <tr>
            <th>
                所属部门:
            </th>
            <td>
                <input type="text" name="department" class="text" maxlength="200" />
            </td>
        </tr>
        <tr class="roles">
            <th>
                <span class="requiredField">*</span>角色:
            </th>
            <td>
					<span class="fieldSet">
                    [#list roles as role]
                        <label>
								<input type="radio" name="roleId" value="${role.id}" />${role.name}
							</label>
                    [/#list]
					</span>
            </td>
        </tr>
    </table>
    <table class="input">
        <tr>
            <th>
                &nbsp;
            </th>
            <td>
                <input type="submit" class="button" value="确 定" />
                <input type="button" class="button" value="返 回" onclick="location.href='userList.jhtml'" />
            </td>
        </tr>
    </table>
</form>
</body>
</html>