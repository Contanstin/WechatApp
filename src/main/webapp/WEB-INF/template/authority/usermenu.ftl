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
</head>
<body>
<div class="path">

</div>
<form id="listForm" action="showUserAuthority.jhtml">
    <div class="bar">
        <div class="menuWrap">
            <div class="search">
                <span  class="arrow">${message("Authority.menu.name")}</span>
                <input type="text" id="name" name="name" value="${menu.name}" maxlength="30" />
                <span  class="arrow">${message("Authority.menu.menuName")}</span>
                <input type="text" id="menuName" name="menuName" value="${menu.menuName}" maxlength="20" />
                <button type="button" onclick=" $.firstPageSkip();">${message("system.message.search")}</button>
            </div>
        </div>
    </div>
    <table id="listTable" class="list">
        <tr>
            <th>
            ${message("Authority.menu.authorityId")}
            </th>
            <th>
            ${message("Authority.menu.name")}
            </th>
            <th>
            ${message("Authority.menu.roleId")}
            </th>
            <th>
            ${message("Authority.menu.menuName")}
            </th>
            <th>
            ${message("Authority.menu.id")}
            </th>
            <th>
            ${message("Authority.menu.authorities")}
            </th>
            <th>
            ${message("Authority.user.delete")}
            </th>
        </tr>
    [#list page.content as request]
        <tr>
            <td align="center">
            ${request.authorityId}
            </td>
            <td align="center">
            ${request.name}
            </td>
            <td align="center">
            ${request.roleId}
            </td>
            <td align="center">
            ${request.menuName}
            </td>
            <td align="center">
            ${request.menuId}
            </td>
            <td align="center">
            ${request.authorities}
            </td>
            <td>
                <a title="fun_delete${request.menuId}" href="javascript:deleteAuthorities('${request.authorityId}','${request.name}','${request.menuName}')">[${message("Authority.user.delete")}]</a>
            </td>
        </tr>
    [/#list]
    </table>
[@pagination]
    [#include "/include/pagein.ftl"]
[/@pagination]
</form>
<script type="text/javascript" src="${base}/resources/js/jquery.datetimepicker.full.min.js"></script>
<script type="text/javascript">
    $('#begindatetime').datetimepicker({
        format:"Y-m-d",      //格式化日期
        timepicker:false    //关闭时间选项
    });
    $('#enddatetime').datetimepicker({
        lang:"ch",           //语言选择中文
        format:"Y-m-d",      //格式化日期
        timepicker:true    //关闭时间选项
    });

    $(function(){
        $("#status").val("${search.status}");
    });
</script>
<script type="text/javascript" src="${base}/resources/js/layer.js"></script>
<script type="text/javascript">
    function deleteAuthorities(authorityId,name,menuName){
        layer.open({
            type: 1,
            title: '删除用户权限',
            area: ['500px', '300px'],
            content:'删除用户'+name+'对菜单'+'['+menuName+']'+'的权限',
            btn:['确认', '取消'],
            yes:function(index) {
                layer.close(index);
                $.ajax({
                    type: "POST",
                    url: "./deleteUserAuthority.jhtml",
                    data:{id: authorityId},
                    success: function (data) {
                    }
                });
            }
        });
    }
</script>

</body>
</html>