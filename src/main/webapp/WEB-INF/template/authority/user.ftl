<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="content-type" content="text/html; charset=utf-8" />
    <title></title>
    <link href="${base}/resources/css/common.css" rel="stylesheet" type="text/css" />
    <link href="${base}/resources/css/bootstrap.css" rel="stylesheet" type="text/css"/>
    <link href="${base}/resources/css/jquery.datetimepicker.css" rel="stylesheet" type="text/css" />
    <link href="${base}/resources/css/layer/skin/layer.css" rel="stylesheet" type="text/css" />
    <script type="text/javascript" src="${base}/resources/js/jquery.js"></script>
    <script type="text/javascript" src="${base}/resources/js/list.js"></script>
    <style type="text/css">
        table.gridtable{
            width:400px;
        }
        table.gridtable td {
            border-width: 1px;
            padding: 8px;
            border-style: solid;
            border-color: #44A3BB;
        }
        table.list td{text-align:center}
    </style>
</head>
<body>
<div class="path">

</div>
<form id="listForm" action="userList.jhtml">
    [#--<div class="bar">--]
        [#--<a href="adduser.jhtml" class="iconButton">--]
            [#--<span class="addIcon">&nbsp;</span>添加--]
        [#--</a>--]
    [#--</div>--]
        <button type="button" onclick="location.href='adduser.jhtml'">新增</button>
    <table id="listTable" class="list">
        <tr>
            <th>账号</th>
            <th>角色名称</th>
            <th>所属部门</th>
            <th>操作</th>
        </tr>
    [#list ls as ls]
        <tr>
            <input type="hidden" id="id" value="${ls.id}"/>
            <td>${ls.userName}</td>
            <td>${ls.rolename}</td>
            <td>${ls.department}</td>
            <td>
                <button type="button" onclick="javascript:window.location.href='adduser.jhtml?userId='+ ${ls.id}">编辑</button>
                <button type="button" onclick="deleteUser('${ls.id}','${ls.userName}')">删除</button>
            </td>
        </tr>
    [/#list]
    </table>
</form>
<script type="text/javascript">
    function deleteUser(id,userName)
    {
        layer.open({
            type: 1,
            title: '删除操作',
            area: ['200px', '140px'],
            content:'确认删除用户：'+userName+"?",
            btn:['确认', '取消'],
            yes:function(index) {

                $.ajax({
                    type: "POST",
                    url: "./userDelete.jhtml",
                    data:{id: id},
                    success: function (data) {
                        window.location.reload();
                        layer.close(index);
                    }
                });
            }
        });
    }
    //    function edituser(id) {
    //        window.location.href=adduser.jhtml?userId=id;
    //    }
</script>
<script type="text/javascript" src="${base}/resources/js/layer.js"></script>
</body>
</html>