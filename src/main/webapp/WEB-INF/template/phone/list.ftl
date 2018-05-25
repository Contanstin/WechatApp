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
               <span  class="arrow">客户名字:</span>
                 <input type="text" name="username" id="username"  />
               <span  class="arrow">公司名称:</span>
                    <input type="text" name="company" id="company"  />
                </div>
                <button type="button" onclick=" $.firstPageSkip();">查询</button>
         </div>



<div>
    [#--<input type="button" onclick="addRecord();return false;" value="新增手机">--]
    <table id="listTable" class="list" style="margin-top:5px;">
        <tr>
            <th>姓名</th>
            <th>公司</th>
            <th>邮箱</th>
            <th>手机号</th>
            <th>地址</th>
            <th>授权数量</th>
            <th>授权状态</th>
            <th>语言（国家）</th>
            <th>授权码</th>
            <th>软件版本</th>
            <th>软件</th>
            <th>操作</th>
        </tr>
    [#list page.list as userPhone]
        <tr>
            <td>${userPhone.username}</td>
            <td>${userPhone.company}</td>
            <td>${userPhone.email}</td>
            <td>${userPhone.phoneNumber}</td>
            <td>${userPhone.address}</td>
            <td>${userPhone.authNum}</td>
            <td>${userPhone.passCheck}</td>
            <td>${userPhone.language}</td>
            <td>${userPhone.mcuCode}</td>
            <td>${userPhone.version}</td>
            <td>${userPhone.versionName}</td>
            <td>   <button onclick="modify('${userPhone.id}','${userPhone.authNum}','${userPhone.mcuCode}', '${userPhone.passCheck}');
                  return false;">授权设置</button>
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
        <table class="gridtable">
            <tr align="center">
                <td colspan="2">授权基本信息</td>
            </tr>
            <tr>
                <td>授权数量:</td>
                <td><input type="text"  name="authNum" id="qauthNum" value=""></td>
            </tr>
            <tr>
                <td>授权码:</td>
                <td><input type="text"  name="mcuCode" id="qmcuCode" value=""></td>
            </tr>
            <tr>
                <td>授权状态:</td>
                <td colspan="2">
                    <select name="passCheck" id="qpassCheck">
                        <option  value="1" >使用</option>
                        <option  value="2" >停用</option>
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
        var username = '${search.username}';
        if(username){
            $('#username').val(username);
        }

        var company = '${search.company}';
        if(company){
            $('#company').val(company);
        }

    });

    //异步提交
    $('#dataForm').submit(function() {
        if(!$('#qmcuCode').val()||!$('#qauthNum').val()){
            alert("必填项不能为空");
            return false;
        }

        var authNum = $('#qauthNum').val();
        if(!authNum||isNaN(authNum)){
            alert("输入格式不正确");
            return false;
        }
        $("#btn").attr("disabled", true);
        var option = {
            type : 'POST',
            dataType:'json',
            url: "edit.jhtml",
            success : function(data) {
                window.location.reload();
            }
        };
        $(this).ajaxSubmit(option);
        return false;
    });

    var addRecord = function(){
        $('#back').show();
        $('#dataDiv').show(time);
    }
    var modify = function(id,authNum, mcuCode, passCheck){
        $('#id').val(id);
        $('#qauthNum').val(authNum);
        $('#qmcuCode').val(mcuCode);
        if(passCheck==0){
            $('#qpassCheck').val(1);
        } else {
            $('#qpassCheck').val(2);
        }
        $('#back').show();
        $('#dataDiv').show(time);
    }

    var deleteSchool = function(id){
        if(confirm("确认删除吗")){
            $.ajax({
                url:'delete.jhtml',
                type:'POST',
                data:{
                    Id:id
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