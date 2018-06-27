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
                [#--<div style="float: left" >--]
                    [#--<span  class="arrow">软件名称:</span>--]
                    [#--<select name="versionType" id="versionType" >--]
                        [#--<option  value="" >请选择</option>--]
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
            <th>图片名称</th>
            <th>储存名称</th>
            <th>排序</th>
            <th>是否轮播</th>
            <th>是否启用</th>
            <th>创建时间</th>
            <th>修改时间</th>
            <th>操作</th>
        </tr>
    [#list page.list as obj]
        <tr>
            <td>${obj.imageName}</td>
            <td>${obj.imageUrl}</td>
            <td>${obj.orderNum}</td>
            <td>
                [#if obj.isEnable==1 ]启用
                [#elseif obj.isEnable==2 ]禁用
                [/#if]
            </td>
            <td>
                [#if obj.status==1 ]启用
                [#elseif obj.status==2 ]禁用
                [/#if]
            </td>
            <td>${(obj.gmtCreate?string('yyyy-MM-dd'))!}</td>
            <td>${(obj.gmtModified?string('yyyy-MM-dd'))!}</td>
            <td>
                <button  onclick="showPic('${obj.realName}');return false;">预览</button>
                <button  onclick="download('${obj.realName}');return false;">下载</button>
                <button onclick="modify('${obj.id}','${obj.realName}','${obj.imageName}','${obj.orderNum}','${obj.isEnable}','${obj.status}');
                  return false;">修改</button>
                <button onclick="deleteObj('${obj.id}','${obj.realName}');return false;">删除</button>
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
        <input name="realName" id="realName" value="" type="hidden">
        <input name="departmentType" class="departmentType" value="" type="hidden">
        <table class="gridtable">
            <tr align="center">
                <td colspan="2">轮播图基本信息</td>
            </tr>
            <tr>
                <td><span></span>图片名称:</td>
                <td><input type="text"  name="imageName" id="qimageName" value=""></td>
            </tr>
            <tr>
                <td><span class="requiredField">*</span>图片排序:</td>
                <td><input type="text"  name="orderNum" id="qorderNum" value="" onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')"></td>
            </tr>
            <tr>
                <td><span class="requiredField">*</span>是否轮播:</td>
                <td colspan="2">
                    <select name="isEnable" id="qisEnable">
                        <option  value="2" >禁用</option>
                        <option  value="1" >启用</option>
                    </select>
                </td>
            </tr>

            <tr>
                <td><span class="requiredField">*</span>是否启用:</td>
                <td colspan="2">
                    <select name="status" id="qstatus">
                        <option  value="1" >启用</option>
                        <option  value="2" >禁用</option>
                    </select>
                </td>
            </tr>

            <tr>
                <td>请选择文件:</td>
                <td><input type="file" name="file" multiple="multiple" ></td>
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
        var departmentType = '${search.departmentType}';
        if(departmentType){
            $('.departmentType').val(departmentType);
        }

    });

    //异步提交
    $('#dataForm').submit(function() {
        if(!$('#qorderNum').val()){
            alert("输入项不能为空");
            return false;
        }
        var orderNum = $('#qorderNum').val();
        if(!orderNum||isNaN(orderNum)){
            alert("输入格式不正确");
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
    var modify = function(id,realName,imageName, orderNum,isEnable,status){
        $('#id').val(id);
        $('#realName').val(realName);
        $('#qstatus').val(status);
        $('#qisEnable').val(isEnable);
        $('#qimageName').val(imageName);
        $('#qorderNum').val(orderNum);
        $('#back').show();
        $('#dataDiv').show(time);
    }

    var deleteObj= function(id,realName){
        if(confirm("确认删除吗")){
            $.ajax({
                url:'delete.jhtml',
                type:'POST',
                data:{
                    id:id ,
                    realName:realName
                },
                success:function(data){
                    window.location.reload();
                }
            });
        }
    }

    var showPic= function(realName){
        window.location.href="showPic.jhtml?realName="+realName;
    }
    var download= function(realName){
        window.location.href="download.jhtml?realName="+realName;
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