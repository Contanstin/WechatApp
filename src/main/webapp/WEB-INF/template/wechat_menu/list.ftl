<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="content-type" content="text/html; charset=utf-8" />
    <title></title>
    <link href="${base}/resources/css/common.css" rel="stylesheet" type="text/css" />
    <link href="${base}/resources/css/bootstrap.css" rel="stylesheet" type="text/css"/>
    <link href="${base}/resources/css/layer/skin/layer.css" rel="stylesheet" type="text/css" />
    <script type="text/javascript" src="${base}/resources/js/jquery.js"></script>
    <script type="text/javascript" src="${base}/resources/js/jquery.form.js"></script>
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
    <style type="text/css">
        table.gridtable td {
            border-width: 1px;
            padding: 8px;
            border-style: solid;
            border-color: #44A3BB;
        }
        table.list td{text-align:center}
        .dataDiv{
            position:absolute;
            left:30%;
            top:30%;
            background-color: #fff;
            padding: 1px;
            z-index: 999;
            display: none;
        }
        .path{padding: 5px;}
    </style>
</head>
<body>
<div id="back" class="back"></div>
    <form action="list.jhtml" id="listForm" >
        <div class="path">
            <div style="float: left" >
                <span  class="arrow">软件版本:</span>
                <select name="versionType" class="versionType" id="versionType" style="width: 120px" >

                </select>
         </div>
            <button type="button" onclick=" $.firstPageSkip();">查询</button>
        </div>


<div>
    <input type="button" onclick="addRecord();return false;" value="新增">
    <table id="listTable" class="list" style="text-align:center">
        <tr>
        <th>菜单名称</th>
            <th>菜单类型</th>
            <th>菜单url</th>
            <th>描述</th>
            <th>排序</th>
            <th>手册类型</th>
            <th>软件版本</th>
            <th>是否使用</th>
            <th>
             更新
            </th>
            <th>
            删除
            </th>
        </tr>
    [#list page as menuLs]
        <tr id="${menuLs.id}" [#if menuLs.parentId!=0] pId="${menuLs.parentId}"[/#if]>
            <td>
            ${menuLs.menuName}
            </td>
            <td>
            ${menuLs.menuType}
            </td>
            <td>
            ${menuLs.menuUrl}
            </td>
            <td>
            ${menuLs.description}
            </td>
            <td>
            ${menuLs.orderNum}
            </td>
            <td>
            ${menuLs.manualName}
            </td>
            <td>
            ${menuLs.versionName}
            </td>
            <td>
                [#if menuLs.status==1 ]启用
                [#elseif menuLs.status==2 ]禁用
                [/#if]
            </td>
            <td>
                [#if menuLs.menuType==1]
                    <a  href="javascript:addRecord('${menuLs.id}')">[新增二级菜单]</a>
                [/#if]
                <a  href="javascript:modify('${menuLs.id}','${menuLs.parentId}','${menuLs.menuName}','${menuLs.menuType}','${menuLs.menuUrl}','${menuLs.description}','${menuLs.orderNum}','${submenus.manualType}','${menuLs.versionType}','${menuLs.status}')">[更新]</a>
            </td>
            <td>
                <a  href="javascript:deleteMenu('${menuLs.id}')">[删除]</a>
            </td>
        </tr>
        [#list menuLs.children as submenus]
            <tr id="${submenus.id}"  pId="${submenus.parentId}">
                <td>
                ${submenus.menuName}
                </td>
                <td>
                ${submenus.menuType}
                </td>
                <td>
                ${submenus.menuUrl}
                </td>
                <td>
                ${submenus.description}
                </td>
                <td>
                ${submenus.orderNum}
                </td>
                <td>
                ${submenus.manualName}
                </td>
                <td>
                ${submenus.versionName}
                </td>
                <td>
                    [#if submenus.status==1 ]启用
                    [#elseif submenus.status==2 ]禁用
                    [/#if]
                </td>
                <td>
                    <a  href="javascript:modify('${submenus.id}','${submenus.parentId}','${submenus.menuName}','${submenus.menuType}','${submenus.menuUrl}','${submenus.description}','${submenus.orderNum}','${submenus.manualType}','${submenus.versionType}','${submenus.status}')">[更新]</a>
                </td>
                <td>
                    <a  href="javascript:deleteMenu('${submenus.id}')">[删除]</a>
                </td>
            </tr>
        [/#list]
    [/#list]
    </table>
</div>
</form>

<div class="dataDiv" id="dataDiv">
    <form id="dataForm" action="upload.jhtml" method="POST" enctype="multipart/form-data" >
        <input name="id" id="id" value="" type="hidden">
        <table class="gridtable">
            <tr align="center">
                <td colspan="4">微信菜单基本信息</td>
            </tr>
            <tr>
                <td><span class="requiredField">*</span>菜单名称:</td>
                <td><input type="text"  name="menuName" id="qmenuName" value=""></td>
                <td><span class="requiredField">*</span>菜单类型:</td>
                <td><input type="text"  name="menuType" id="qmenuType"  readonly="readonly"></td>
            </tr>
            <tr>
                <td><span class="requiredField">*</span>菜单url:</td>
                <td><input type="text"  name="menuUrl" id="qmenuUrl" value="" ></td>
                <td><span class="requiredField">*</span>排序:</td>
                <td><input type="text"  name="orderNum" id="qorderNum" value="" onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')" ></td>
            </tr>
            <tr>
                <td>软件版本:</td>
                <td colspan="1">
                    <select name="versionType" class="versionType"  >
                    </select>
                </td>
                <td><span class="requiredField">*</span>是否使用:</td>
                <td colspan="1">
                    <select name="status" id="qstatus">
                        <option  value="1" >启用</option>
                        <option  value="2" >禁用</option>
                    </select>
                </td>
            </tr>
            <tr>
                <td>手册类型:</td>
                <td colspan="1">
                    <select name="manualType" class="manualType" id="manualType" style="width: 120px" >

                    </select>
                </td>
                <td><span class="requiredField">*</span>父id:</td>
                <td><input type="text"  name="parentId" id="parentId" readonly="readonly" value="" ></td>
            </tr>
            <tr>
                <td><span class="requiredField">*</span>描述:</td>
                <td ><input type="text"  name="description" id="qdescription" value=""  ></td>
            </tr>
            <tr align="center">
                <td colspan="4"><input type="submit" id="btn"  value="保存">
                    &nbsp; <button type="button" onclick="cancel();">取消</button>
                </td>
            </tr>
        </table>
    </form>
</div>


</body>


<script type="text/javascript">
    var time = 200;

    $(function(){
        var versionType = '${search.versionType}';
        if(versionType){
            $('#versionType').val(versionType);
        }
        $.ajax({
            url:'../manual/findVersionType.jhtml',
            type:'GET',
            dataType: 'json' ,
            data:{
            },
            success:function(data){
                $(".versionType").empty()
   /*             $(".versionType").append("<option  value=''>全部</option>")*/
                for (var i = 0; i < data.length; i++) {
                    var str="";
                    if (data[i].id==parseInt(versionType)){
                        str = "<option  value='"+data[i].id+"' selected='selected' > "
                                +data[i].name+"</option>"
                    }else {
                        str = "<option  value='"+data[i].id+"'> "
                                +data[i].name+"</option>"
                    }
                    $(".versionType").append(str)
                };
            }
        });
        $.ajax({
            url:'../manual/findManualType.jhtml',
            type:'GET',
            dataType: 'json' ,
            data:{
            },
            success:function(data){
                $(".manualType").empty()
                for (var i = 0; i < data.length; i++) {
                    var str="";
                    if (data[i].id==parseInt(manualType)){
                        str = "<option  value='"+data[i].id+"' selected='selected' > "
                                +data[i].name+"</option>"
                    }else {
                        str = "<option  value='"+data[i].id+"'> "
                                +data[i].name+"</option>"
                    }
                    $(".manualType").append(str)
                };
            }
        });
    });

    //异步提交
    $('#dataForm').submit(function() {

        if(!$('#qmenuName').val()&&!$('#qorderNum').val()&&!$('#qdescription').val()&&!$('#qmenuUrl').val()){
            alert("输入项不能为空");
            return false;
        }
        $("#btn").attr("disabled", true);
        var option = {
            type : 'POST',
            dataType:'json',
            url: "edit.jhtml",
            success : function(data) {
                var obj = eval('(' + data + ')')
                if(obj.code=="SUCCESS"){
                    window.location.reload();
                }else if(obj.code=="FAIL"){
                    alert(obj.message);
                    window.location.reload();
                }
            }
        };
        $(this).ajaxSubmit(option);
        return false;
    });

    var addRecord = function(pid){

        $('#qversionType').val($('#versionType option:selected') .val())
        if (pid!=null){
            $('#qmenuType').val(2);
            $('#parentId').val(pid);
        }else {
            $('#qmenuType').val(1);
            $('#parentId').val(0);
        }
        $('#back').show();
        $('#dataDiv').show(time);
    }
    var modify = function(id,parentId,menuName, menuType,menuUrl,description,orderNum,manualType,versionType,status){
        $('#id').val(id);
        $('#parentId').val(parentId);
        $('#manualType').val(manualType);
        if(status==1){
            $('#qstatus').val(1);
        } else if (status==2){
            $('#qstatus').val(2);
        }
        $('#qmenuName').val(menuName);
        $('#qmenuType').val(menuType);
        $('#qversionType').val(versionType);
        $('#qmenuUrl').val(menuUrl);
        $('#qdescription').val(description);
        $('#qorderNum').val(orderNum);
        $('#back').show();
        $('#dataDiv').show(time);
    }

    var deleteMenu = function(id){
        if(confirm("确认删除吗")){
            $.ajax({
                url:'delete.jhtml',
                type:'POST',
                data:{
                    id:id
                },
                success:function(data){
                    window.location.reload();
                }
            });
        }
    }

    var cancel = function(){
        $('#back').hide();
        $('#dataDiv').hide(time);
        $(':input','#dataForm')
                .not(':button, :submit, :reset')
                .val('');
    }







</script>
</html>