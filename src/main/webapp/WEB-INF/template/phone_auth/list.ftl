<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="content-type" content="text/html; charset=utf-8" />
    <title></title>
    <link href="${base}/resources/css/common.css" rel="stylesheet" type="text/css" />
    <link href="${base}/resources/css/bootstrap.css" rel="stylesheet" type="text/css"/>
    <link href="${base}/resources/css/bootstrap-switch.css" rel="stylesheet" type="text/css">
    <script type="text/javascript" src="${base}/resources/js/jquery.js"></script>
    <script type="text/javascript" src="${base}/resources/js/jquery.form.js"></script>
    <script type="text/javascript" src="${base}/resources/js/list.js"></script>
    <script type="text/javascript" src="${base}/resources/js/bootstrap-switch.js"></script>
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
                    <span  class="arrow">软件名称:</span>
                    <select name="versionType" id="versionType" >
                        <option  value="" >全部</option>
                        <option  value="1" >Mont70</option>
                        <option  value="2" >Mont71</option>
                        <option  value="3" >HD5L</option>
                    </select>
               <span  class="arrow">授权码:</span>
                    <input type="text" name="authCode" id="authCode"  />
                </div>
                <button type="button" onclick=" $.firstPageSkip();">查询</button>
         </div>



<div>
    <input type="button" onclick="addRecord();return false;" value="新增">
    <table id="listTable" class="list" style="margin-top:5px;">
        <tr>
            <th>软件名称</th>
            <th>授权码</th>
            <th>操作</th>
        </tr>
    [#list page.list as auth]
        <tr>
            <td>${auth.versionName}</td>
            <td>${auth.authCode}</td>
            <td>   <button onclick="modify('${auth.id}','${auth.versionType}','${auth.authCode}');
                  return false;">修改</button>
                <button onclick="deleteAuth('${auth.id}');return false;">删除</button>
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
                <td colspan="2">软件授权基本信息</td>
            </tr>
            <tr>
                <td><span class="requiredField">*</span>软件名称:</td>
                [#--<td><input type="text"  name="versionName" id="qversionName" value=""></td>--]
                <td colspan="2">
                    <select name="versionType" id="qversionType">
                        <option  value="1" >Mont70</option>
                        <option  value="2" >Mont71</option>
                        <option  value="3" >HD5L</option>
                    </select>
                </td>
            </tr>
            <tr>
                <td><span class="requiredField">*</span>授权码:</td>
                <td><input type="text"  name="authCode" id="qauthCode" value="" maxlength="2"></td>
            </tr>
            <tr>
                <td>请选择文件:</td>
                <td><input type="file" name="file"></td>
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
        var versionType = '${search.versionType}';
        if(versionType==1){
            $('#versionType').val(1);
        } else if (versionType==2){
            $('#versionType').val(2);
        }else if(versionType==3){
            $('#versionType').val(3);
        }
        var authCode = '${search.authCode}';
        if(authCode){
            $('#authCode').val(authCode);
        }

    });

    //异步提交
    $('#dataForm').submit(function() {

        var authCode = $('#qauthCode').val();
        if(!authCode||isNaN(authCode)){
            alert("输入格式不正确");
            return false;
        }

        $("#btn").attr("disabled", true);
        var option = {
            type : 'POST',
            dataType:'json',
            url: "upload.jhtml",
            success : function(data) {
                var obj = eval('(' + data + ')')
                if(obj.code=="SUCCESS"){
                    window.location.reload();
                }else if(obj.code=="FAIL"){
                    alert("已存在重复授权码，不可保存");
                    window.location.reload();
                }else{
                    alert("保存失败");
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
    var modify = function(id,versionType, authCode){
        $('#id').val(id);
        if(versionType==1){
            $('#qversionType').val(1);
        } else if (versionType==2){
            $('#qversionType').val(2);
        }else {
            $('#qversionType').val(3);
        }
        $('#qauthCode').val(authCode);
        $('#back').show();
        $('#dataDiv').show(time);
    }

    var deleteAuth = function(id){
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