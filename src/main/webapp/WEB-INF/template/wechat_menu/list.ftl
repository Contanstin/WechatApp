<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="content-type" content="text/html; charset=utf-8" />
    <title></title>
    <link href="${base}/resources/css/common.css" rel="stylesheet" type="text/css" />
    <link href="${base}/resources/css/bootstrap.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript" src="${base}/resources/js/jquery.js"></script>
    <script type="text/javascript" src="${base}/resources/js/jquery.form.js"></script>
    <script type="text/javascript" src="${base}/resources/js/list.js"></script>
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
                [#--<div style="float: left" >--]
                    [#--<span  class="arrow">软件名称:</span>--]
                    [#--<select name="versionType" id="versionType" >--]
                        [#--<option  value="" >全部</option>--]
                        [#--<option  value="1" >Mont70</option>--]
                        [#--<option  value="2" >Mont71</option>--]
                        [#--<option  value="3" >HD5L</option>--]
                    [#--</select>--]
               [#--<span  class="arrow">授权码:</span>--]
                    [#--<input type="text" name="authCode" id="authCode"  />--]
                [#--</div>--]
                [#--<button type="button" onclick=" $.firstPageSkip();">查询</button>--]
         </div>



<div>
    <input type="button" onclick="addRecord();return false;" value="新增">
    <table id="listTable" class="list" style="margin-top:5px;">
        <tr>
            <th>菜单名称</th>
            <th>菜单url</th>
            <th>描述</th>
            <th>排序</th>
            <th>是否使用</th>
            <th>操作</th>
        </tr>
    [#list page.list as m]
        <tr>
            <td>${m.menuName}</td>
            <td>${m.menuUrl}</td>
            <td>${m.description}</td>
            <td>${m.orderNum}</td>
            <td>
                [#if m.status==1 ]启用
                [#elseif m.status==2 ]禁用
                [/#if]
            </td>
            <td>   <button onclick="modify('${m.id}','${m.menuName}','${m.menuUrl}','${m.description}','${m.orderNum}','${m.status}');
                  return false;">修改</button>
                <button onclick="deleteMenu('${m.id}');return false;">删除</button>
            </td>
        </tr>
    [/#list]
    </table>
[@pagination]
    [#include "/include/pagein.ftl"]
[/@pagination]
</div>
</form>

<div class="dataDiv" id="dataDiv">
    <form id="dataForm" action="upload.jhtml" method="POST" enctype="multipart/form-data" >
        <input name="id" id="id" value="" type="hidden">
        <table class="gridtable">
            <tr align="center">
                <td colspan="2">微信菜单基本信息</td>
            </tr>
            <tr>
                <td><span class="requiredField">*</span>菜单名称:</td>
                <td><input type="text"  name="menuName" id="qmenuName" value=""></td>

            </tr>
            <tr>
                <td><span class="requiredField">*</span>菜单url:</td>
                <td><input type="text"  name="menuUrl" id="qmenuUrl" value="" ></td>
            </tr>
            <tr>
                <td><span class="requiredField">*</span>描述:</td>
                <td><input type="text"  name="description" id="qdescription" value="" ></td>
            </tr>
            <tr>
                <td><span class="requiredField">*</span>排序:</td>
                <td><input type="text"  name="orderNum" id="qorderNum" value="" ></td>
            </tr>
            <tr>
                <td><span class="requiredField">*</span>是否使用:</td>
                <td colspan="2">
                    <select name="status" id="qstatus">
                        <option  value="1" >启用</option>
                        <option  value="2" >禁用</option>
                    </select>
                </td>
            </tr>

            <tr align="center">
                <td colspan="2"><input type="submit" id="btn"  value="保存">
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

    var addRecord = function(){
        $('#back').show();
        $('#dataDiv').show(time);
    }
    var modify = function(id,menuName, menuUrl,description,orderNum,status){
        $('#id').val(id);
        if(status==1){
            $('#qstatus').val(1);
        } else if (status==2){
            $('#qstatus').val(2);
        }
        $('#qmenuName').val(menuName);
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