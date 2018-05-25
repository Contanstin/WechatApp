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
    <form action="manage.jhtml" id="listForm" >
            <div class="path">
                <div style="float: left" >
               <span  class="arrow">客户名称:</span>
                 <input type="text" name="username" id="username"  />
               <span  class="arrow">公司名称:</span>
                    <input type="text" name="company" id="company"  />
               <span  class="arrow">手机号码:</span>
                    <input type="text" name="phoneNumber" id="phoneNumber"  />
               <span  class="arrow">软件名称:</span>
                    <select name="versionType" id="versionType" >
                        <option  value="" >全部</option>
                        <option  value="1" >Mont70</option>
                        <option  value="2" >Mont71</option>
                        <option  value="3" >HD5L</option>
                    </select>
                </div>

                <button type="button" onclick=" $.firstPageSkip();">查询</button>
         </div>



<div>
    <input type="button" onclick="addRecord();return false;" value="新增">
    <table id="listTable" class="list" style="margin-top:5px;">
        <tr>
            <th>姓名</th>
            <th>密码</th>
            <th>公司</th>
            <th>邮箱</th>
            <th>手机号</th>
            <th>地址</th>
            <th>授权数量</th>
            <th>授权码</th>
            <th>授权状态</th>
            <th>语言（国家）</th>
            <th>软件版本</th>
            <th>软件</th>
            <th>操作</th>
        </tr>
    [#list page.list as userPhone]
        <tr>
            <td>${userPhone.username}</td>
            <td>${userPhone.password}</td>
            <td>${userPhone.company}</td>
            <td>${userPhone.email}</td>
            <td>${userPhone.phoneNumber}</td>
            <td>${userPhone.address}</td>
            <td>${userPhone.authNum}</td>
            <td>${userPhone.mcuCode}</td>
            <td>${userPhone.passCheck}</td>
            <td>${userPhone.language}</td>
            <td>${userPhone.version}</td>
            <td>${userPhone.versionName}</td>
            <td>
                <button onclick="modify('','${userPhone.username}','${userPhone.password}',
                        '${userPhone.company}', '${userPhone.email}', '${userPhone.phoneNumber}',
                        '${userPhone.address}','${userPhone.authNum}','${userPhone.mcuCode}','${userPhone.passCheck}',
                        '${userPhone.language}', '${userPhone.version}', '${userPhone.versionType}');
                        return false;">克隆</button>
                <button onclick="modify('${userPhone.id}','${userPhone.username}','${userPhone.password}',
                    '${userPhone.company}', '${userPhone.email}', '${userPhone.phoneNumber}',
                    '${userPhone.address}','${userPhone.authNum}','${userPhone.mcuCode}','${userPhone.passCheck}',
                    '${userPhone.language}', '${userPhone.version}', '${userPhone.versionType}');
                  return false;">修改</button>
                <button onclick="deletePhone('${userPhone.id}');return false;">删除</button>
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
    <form id="dataForm" action="edit.jhtml" method="POST">
        <input name="id" id="id" value="" type="hidden">
        <table class="gridtable">
            <tr align="center">
                <td colspan="4">手机用户基本信息</td>
            </tr>
            [#--姓名	公司	邮箱	手机号	地址	授权数量	授权状态	语言（国家）	授权码	软件版本	软件--]
            <tr>
                <td><span style="color: red">*</span>姓名:</td>
                <td><input type="text"  name="username" id="qusername" value=""></td>
                <td><span style="color: red">*</span>密码:</td>
                <td><input type="text"  name="password" id="qpassword" value=""></td>
            </tr>
            <tr>
                <td>公司:</td>
                <td><input type="text"  name="company" id="qcompany" value=" "></td>
                <td><span style="color: red">*</span>邮箱:</td>
                <td><input type="text"  name="email" id="qemail" value=""></td>
            </tr>
            <tr>
                <td><span style="color: red">*</span>手机号:</td>
                <td><input type="text"  name="phoneNumber" id="qphoneNumber" value=""></td>
                <td>地址:</td>
                <td><input type="text"  name="address" id="qaddress" value=""></td>
            </tr>
            <tr>
                <td>软件名称:</td>
            [#--    <td><input type="text"  name="versionName" id="qversionName" value=""></td>--]
                <td colspan="1">
                    <select name="versionType" id="qversionType" onchange="changeVersion()">
                        <option  value="1" >Mont70</option>
                        <option  value="2" >Mont71</option>
                        <option  value="3" >HD5L</option>
                    </select>
                </td>
                <td>软件版本:</td>
                <td><input type="text"  name="version" id="qversion" value=""></td>
            </tr>
            <tr>
                <td><span style="color: red">*</span>授权码:</td>
                <td>
                [#--<input type="text"  name="mcuCode" id="qmcuCode" value="">--]
                    <span class="fieldSet" id="qmcuCode" >

                    </span>
                </td>
                <td><span style="color: red">*</span>授权数量:</td>
                <td><input type="text"  name="authNum" id="qauthNum" value=""></td>
            </tr>
            <tr>
                <td>授权状态:</td>
                <td colspan="1">
                    <select name="passCheck" id="qpassCheck">
                        <option  value="1" >使用</option>
                        <option  value="2" >停用</option>
                    </select>
                </td>
                <td>语言（国家）:</td>
                <td><input type="text"  name="language" id="qlanguage" value=""></td>
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
        var username = '${search.username}';
        if(username){
            $('#username').val(username);
        }

        var company = '${search.company}';
        if(company){
            $('#company').val(company);
        }

        var phoneNumber = '${search.phoneNumber}';
        if(phoneNumber){
            $('#phoneNumber').val(phoneNumber);
        }

        var versionType = '${search.versionType}';
        if(versionType==1){
            $('#versionType').val(1);
        } else if (versionType==2){
            $('#versionType').val(2);
        }else if(versionType==3){
            $('#versionType').val(3);
        }

    });

    //异步提交
    $('#dataForm').submit(function() {
        if(!$('#qauthNum').val()||!$('#qusername').val()||!$('#qpassword').val()||!$('#qemail').val()||!$('#qphoneNumber').val()){
            alert("必填项不能为空");
            return false;
        }
        var authNum = $('#qauthNum').val();
        if(!authNum||isNaN(authNum)){
            alert("授权数量输入格式不正确");
            return false;
        }
        var phoneNumber = $('#qphoneNumber').val();
        if(!phoneNumber||isNaN(phoneNumber)){
            alert("手机号码输入格式不正确");
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
                    alert("已存在重复账号，不可保存");
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
        $.ajax({
            url:'../phone_auth/findByVersionType.jhtml',
            type:'POST',
            dataType: 'json' ,
            data:{
                versionType:1
            },
            success:function(data){
                $('#qmcuCode').empty()
                for (var i = 0; i < data.length; i++) {
                    var str= "<input type= 'checkbox'   name= 'authCodes' value='"+data[i].authCode +" ' /> "  +data[i].authCode +"&nbsp;&nbsp;"
                    $('#qmcuCode').append(str)
                };
            }
        });
        $('#back').show();
        $('#dataDiv').show(time);
    }

    var changeVersion=function(){
        var versionType=$("#qversionType option:selected").val();
        $.ajax({
            url:'../phone_auth/findByVersionType.jhtml',
            type:'POST',
            dataType: 'json' ,
            data:{
                versionType:versionType
            },
            success:function(data){
                $('#qmcuCode').empty()
                for (var i = 0; i < data.length; i++) {
                    var str= "<input type= 'checkbox'   name= 'authCodes' value='"+data[i].authCode +" ' /> "  +data[i].authCode +"&nbsp;&nbsp;"
                    $('#qmcuCode').append(str)
                };
            }
        });
    }

    var modify = function(id,username,password,company,email,phoneNumber,address, authNum, mcuCode, passCheck ,language ,version ,versionType){

        $.ajax({
            url:'../phone_auth/findByVersionType.jhtml',
            type:'POST',
            dataType: 'json' ,
            data:{
                versionType:versionType
            },
            success:function(data){
                $('#qmcuCode').empty()
                for (var i = 0; i < data.length; i++) {
                    var code = data[i].authCode;
                    console.log(code)
                    console.log(mcuCode)
                    var st;
                    if (mcuCode.indexOf(code)>=0){
                        st= "<input type= 'checkbox'   name= 'authCodes' value='"+code +"'  checked='checked' /> "
                                +code +"&nbsp;&nbsp;"
                    }else {
                        st= "<input type= 'checkbox'   name= 'authCodes' value='"+code +"'  /> "
                                +code +"&nbsp;&nbsp;"
                    }
                    $('#qmcuCode').append(st)
                };
            }
        });

        $('#id').val(id);
        $('#qusername').val(username);
        $('#qpassword').val(password);
        $('#qcompany').val(company);
        $('#qemail').val(email);
        $('#qphoneNumber').val(phoneNumber);
        $('#qaddress').val(address);
        $('#qauthNum').val(authNum);
        $('#qmcuCode').val(mcuCode);
        $('#qlanguage').val(language);
        $('#qversion').val(version);
        if(versionType==1){
            $('#qversionType').val(1);
        } else if (versionType==2){
            $('#qversionType').val(2);
        }else {
            $('#qversionType').val(3);
        }
        if(passCheck=="0"){
            $('#qpassCheck').val("2");
        } else {
            $('#qpassCheck').val("1");
        }
        $('#back').show();
        $('#dataDiv').show(time);
    }

    var deletePhone = function(id){
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