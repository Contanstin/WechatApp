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

         </div>



<div>
    <input type="button" onclick="addRecord();return false;" value="新增">
    <table id="listTable" class="list" style="margin-top:5px;">
        <tr>
            <th>软件版本名称</th>
            <th>描述</th>
            <th>操作</th>
        </tr>
    [#list page.list as obj]
        <tr>
            <td>${obj.name}</td>
            <td>${obj.description}</td>
            <td>   <button onclick="modify('${obj.id}','${obj.name}','${obj.description}');
                  return false;">修改</button>
                <button onclick="deleteMenu('${obj.id}');return false;">删除</button>
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
    <form id="dataForm" action="edit.jhtml" method="POST" >
        <input name="id" id="id" value="" type="hidden">
        <input name="departmentType" class="departmentType" value="" type="hidden">
        <table class="gridtable">
            <tr align="center">
                <td colspan="2">软件版本基本信息</td>
            </tr>
            <tr>
                <td><span class="requiredField">*</span>软件版本名称:</td>
                <td><input type="text"  name="name" id="name" value=""></td>

            </tr>
            <tr>
                <td><span class="requiredField">*</span>描述:</td>
                <td><input type="text"  name="description" id="description" value="" ></td>
            </tr>
            <tr>

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
        var departmentType = '${search.departmentType}';
        if(departmentType){
            $('.departmentType').val(departmentType);
        }

    });

    //异步提交
    $('#dataForm').submit(function() {

        if(!$('#name').val()){
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
    var modify = function(id,name,description ){
        $('#id').val(id);
        $('#name').val(name);
        $('#description').val(description);
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