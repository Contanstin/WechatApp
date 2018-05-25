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
    <script src="${base}/resources/js/jquery.treeTable.js" type="text/javascript"> </script>
    <script type="text/javascript">
        $(function(){
            var option = {
                theme:'vsStyle',
                expandLevel : 1,
                beforeExpand : function($treeTable, id) {
                },
                onSelect : function($treeTable, id) {
                    window.console && console.log('onSelect:' + id);
                }
            };
            $('#listTable').treeTable(option);
        });
    </script>
</head>
<body>
<div class="path">

</div>
<form id="listForm" action="authoritiesMenuList.jhtml">
    [#--<a title="fun_add" href="javascript:addMenu(0)">新增</a>--]
    <button type="button" onclick="addMenu(0)">新增</button>
    <table id="listTable" class="list" style="text-align:center">
        <tr>
            <th>
            ${message("Authority.menu.menuName")}
            </th>
            <th>
            ${message("Authority.menu.orderNum")}
            </th>
            <th>
            ${message("Authority.menu.id")}
            </th>
            <th>
            ${message("Authority.menu.description")}
            </th>
            <th>
            ${message("Authority.menu.authorities")}
            </th>
            <th>
            ${message("Authority.menu.menuUrl")}
            </th>
            <th>
            ${message("Authority.menu.update")}
            </th>
            <th>
            ${message("Authority.menu.delete")}
            </th>
        </tr>
    [#list MenuLs as menuLs]
        <tr id="${menuLs.id}" [#if menuLs.parentId!=0] pId="${menuLs.parentId}"[/#if]>
            <td>
            ${menuLs.menuName}
            </td>
            <td>
            ${menuLs.orderNum}
            </td>
            <td>
            ${menuLs.id}
            </td>
            <td>
            ${menuLs.description}
            </td>
            <td>
            ${menuLs.authorities}
            </td>
            <td>
            ${menuLs.menuUrl}
            </td>
            <td>
                <a title="fun_update${menuLs.id}" href="javascript:updateMenu('${menuLs.id}','${menuLs.menuName}','${menuLs.parentId}','${menuLs.description}','${menuLs.menuType}','${menuLs.orderNum}','${menuLs.authorities}','${menuLs.menuUrl}')">[${message("Authority.update")}]</a>
                [#if menuLs.menuType==1]
                    <a title="fun_add${menuLs.id}" href="javascript:addMenu('${menuLs.id}')">[新增二级菜单]</a>
                [/#if]
            </td>
            <td>
                <a title="fun_delete${menuLs.id}" href="javascript:deleteMenu('${menuLs.id}','${menuLs.menuName}','${menuLs.parentId}','${menuLs.description}','${menuLs.menuType}','${menuLs.orderNum}','${menuLs.authorities}','${menuLs.menuUrl}')">[${message("Authority.delete")}]</a>
            </td>
        </tr>
        [#list menuLs.submenu as submenus]
            <tr id="${submenus.id}"  pId="${submenus.parentId}">
                <td>
                ${submenus.menuName}
                </td>
                <td>
                ${submenus.orderNum}
                </td>
                <td>
                ${submenus.id}
                </td>
                <td>
                ${submenus.description}
                </td>
                <td>
                ${submenus.authorities}
                </td>
                <td>
                ${submenus.menuUrl}
                </td>
                <td>
                    <a title="fun_update${submenus.id}" href="javascript:updateMenu('${submenus.id}','${submenus.menuName}','${submenus.parentId}','${submenus.description}','${submenus.menuType}','${submenus.orderNum}','${submenus.authorities}','${submenus.menuUrl}')">[${message("Authority.update")}]</a>
                </td>
                <td>
                    <a title="fun_delete${submenus.id}" href="javascript:deleteMenu('${submenus.id}','${submenus.menuName}','${submenus.parentId}','${submenus.description}','${submenus.menuType}','${submenus.orderNum}','${submenus.authorities}','${submenus.menuUrl}')">[${message("Authority.delete")}]</a>
                </td>
            </tr>
        [/#list]
    [/#list]
    </table>
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
</script>
<script type="text/javascript" src="${base}/resources/js/layer.js"></script>
<script type="text/javascript">
    function updateMenu(id,menuName,parentId,description,menuType,orderNum,authorities,menuUrl)
    {
        layer.open({
            type: 1,
            title:  '更新操作',
            area: ['400px', '250px'],
            content:'<table><tr><td width="25%">菜单名称：</td><td><input type="text" id="menuName" name="menuName" value="'+menuName+
            '" maxlength="50" /></td></tr>'+
            '<tr><td>父菜单ID：</td><td><input type="text" id="parentId" name="parentId" value="'+parentId+
            '" maxlength="3" /></td></tr>'+
            '<tr><td>菜单描述：</td><td><input type="text" id="description" name="description" value="'+description+
            '" maxlength="50" /></td></tr>'+
            '<tr><td>菜单类型：</td><td><input type="text" id="menuType" name="menuType" value="'+menuType+
            '" maxlength="1" /></td></tr>'+
            '<tr><td>菜单排序规则号：</td><td><input type="text" id="orderNum" name="orderNum" value="'+orderNum+
            '" maxlength="10" /></td></tr>'+
            '<tr><td>菜单权限名称：</td><td><input type="text" id="authorities" name="authorities" value="'+authorities+
            '" maxlength="50" /></td></tr>'+
            '<tr><td>菜单URL：</td><td><input type="text" id="menuUrl" name="menuUrl" size="30" value="'+menuUrl+
            '" maxlength="50" /></td></tr></table>',
            btn:['确认', '取消'],
            yes:function(index) {
                var menuName=$('#menuName').val();
                var parentId=$('#parentId').val();
                var description=$('#description').val();
                var menuType=$('#menuType').val();
                var orderNum=$('#orderNum').val();
                var authorities=$('#authorities').val();
                var menuUrl=$('#menuUrl').val();
                $.ajax({
                    type: "POST",
                    url: "./updateMenu.jhtml",
                    data: {id: id, menuName: menuName, parentId: parentId,description:description,menuType:menuType,orderNum:orderNum,authorities:authorities,menuUrl:menuUrl},
                    success: function (data) {
                        window.location.reload();
                        layer.close(index);
                    }
                });
            }
        });
    }
</script>
<script type="text/javascript" src="${base}/resources/js/layer.js"></script>
<script type="text/javascript">
    function deleteMenu(id,menuName,parentId,description,menuType,orderNum,authorities,menuUrl)
    {
        layer.open({
            type: 1,
            title: '删除操作',
            area: ['380px', '230px'],
            content:'删除记录：'+'</br>菜单ID:'+id+'</br>菜单名称:'+menuName+'</br>父级菜单ID:'+parentId+'</br>菜单详细描述:'+description+'</br>菜单类型:'+menuType+'</br>子菜单的排序规则:'+orderNum+'</br>权限名称:'+authorities+'</br>跳转目录或地址:'+menuUrl,
            btn:['确认', '取消'],
            yes:function(index) {
                $.ajax({
                    type: "POST",
                    url: "./deleteMenu.jhtml",
                    data:{id: id},
                    success: function (data) {
                        window.location.reload();
                        layer.close(index);
                    }
                });
            }
        });
    }
</script>
<script type="text/javascript" src="${base}/resources/js/layer.js"></script>
<script type="text/javascript">
    function addMenu(pid){
        var mtype=2;
        if(pid==0){
            mtype=1;
        }
        layer.open({
            type: 1,
            title: '新增菜单',
            area: ['400px', '250px'],
            content:'<table><tr><td width="25%">菜单名称：</td><td><input type="text" id="menuName" name="menuName" value="${menuName}" maxlength="50" /></td></tr>'+
            '<tr><td>父菜单ID：</td><td><input type="text" id="parentId" name="parentId" value="'+pid+'" maxlength="3" readonly="readonly" /></td></tr>'+
            '<tr><td>菜单描述：</td><td><input type="text" id="description" name="description" value="${description}" maxlength="50" /></td></tr>'+
            '<tr><td>菜单类型：</td><td><input type="text" id="menuType" name="menuType" value="'+mtype+'" maxlength="1" readonly="readonly"/></td></tr>'+
            '<tr><td>菜单权限名称：</td><td><input type="text" id="authorities" name="authorities" value="${authorities}" maxlength="50" /></td></tr>'+
            '<tr><td>菜单URL：</td><td><input type="text" id="menuUrl" name="menuUrl" value="${menuUrl}" size="30" maxlength="50" /></td></tr></table>',
            btn:['确认', '取消'],
            yes:function(index) {
                var menuName=$('#menuName').val();
                var parentId=$('#parentId').val();
                var description=$('#description').val();
                var menuType=$('#menuType').val();
                var authorities=$('#authorities').val();
                var menuUrl=$('#menuUrl').val();
                if ($.trim(menuName) == ''|| $.trim(parentId) == ''|| $.trim(menuType) == '' || $.trim(menuUrl) == '') {
                    alert('菜单名称、父菜单ID、菜单类型、菜单URL，不能为空');
                } else {
                    $.ajax({
                        type: "POST",
                        url: "./addMenu.jhtml",
                        data: {menuName: menuName, parentId: parentId, description: description, menuType: menuType, authorities: authorities, menuUrl: menuUrl},
                        success: function (data) {
                            window.location.reload();
                            layer.close(index);
                        }
                    });
                }
            }
        });
    }
</script>
<script type="text/javascript" src="${base}/resources/js/layer.js"></script>
<script type="text/javascript">
    function addAuthority() {
        var $check_boxes = $('input[type=checkbox][checked=checked][id!=selectAll]');
        if($check_boxes.length<=0){ alert('请勾选用户角色需要添加的菜单');return;   }
        var ids = new Array();
        var errmsg='';
        $check_boxes.each(function(){
            ids.push($(this).val());
        });
        if(errmsg!=''){
            alert(errmsg);
            return;
        }
        layer.open({
            type: 1,
            title: '批量添加角色权限',
            area: ['500px', '300px'],
            content:'<span class="arrow">角色Id：</span><input type="text" id="roleId" name="roleId" value="${roleId}" maxlength="10" />'+'<br>',
            btn:['确认', '取消'],
            yes:function(index) {
                var roleId=$('#roleId').val();
                if ($.trim(roleId) == '') {
                    alert('角色Id不能为空');
                } else {
                    layer.close(index);
                    $.ajax({
                        type: "POST",
                        url: "./addUserAuthority.jhtml",
                        data: {roleId: roleId, ids:ids},
                        success: function (data) {

                        }

                    });
                }
            }
        });
    }
</script>
</body>
</html>