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
                <div style="float: left" >
                    <span  class="arrow">软件版本:</span>
                    <select name="versionType" class="versionType" id="versionType" style="width: 120px" >

                    </select>
                    <span  class="arrow">手册类型:</span>
                    <select name="manualType" class="manualType" id="manualType" style="width: 120px" >

                    </select>
               <span  class="arrow">手册名称:</span>
                 <input type="text" name="manualName" id="manualName"  />
                    <input name="departmentType" class="departmentType" value="" type="hidden">
                </div>
                <button type="button" onclick=" $.firstPageSkip();">查询</button>
         </div>



<div>
    <input type="button" onclick="addRecord();return false;" value="新增">
    <table id="listTable" class="list" style="margin-top:5px;">
        <tr>
            <th>手册名称</th>
            <th>手册类型</th>
            <th>格式</th>
            <th>url</th>
            [#--<th>图片</th>--]
            <th>排序</th>
            <th>软件版本</th>
            <th>是否使用</th>
            <th>是否推荐</th>
            <th>操作</th>
        </tr>
    [#list page.list as obj]
        <tr>
            <td>${obj.manualName}</td>
            <td>${obj.name}</td>
            <td>${obj.manualFormat}</td>
            <td>${obj.manualUrl}</td>
            [#--<td>${obj.manualUrl}</td>--]
            <td>${obj.orderNum}</td>
            <td>${obj.versionName}</td>
            <td>
                [#if obj.status==1 ]启用
                [#elseif obj.status==2 ]禁用
                [/#if]</td>
            <td>
                [#if obj.getIsRecommend()==1 ]推荐
                [#elseif obj.getIsRecommend()==2 ]不推荐
                [/#if]
            </td>
            <td>
            <button onclick="modify('${obj.id}','${obj.realName}','${obj.manualName}','${obj.manualType}', '${obj.status}','${obj.getImageUrl()}','${obj.orderNum}');
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
    <form id="dataForm" action="edit.jhtml" method="POST" enctype="multipart/form-data">
        <input name="id" id="id" value="" type="hidden">
        <input name="realName" id="realName" value="" type="hidden">
        <input name="departmentType" class="departmentType" value="" type="hidden">
        <table class="gridtable">
            <tr align="center">
                <td colspan="2">手册基本信息</td>
            </tr>
            <tr>
                <td>手册名称:</td>
                <td><input type="text"  name="manualName" id="qmanualName" value=""></td>
            </tr>
            <tr>
                <td>手册类型:</td>
                <td colspan="2">
                    <select name="manualType" class="manualType"  >

                    </select>
                </td>
            </tr>
            <tr>
                <td>排序:</td>
                <td><input type="text"  name="orderNum" id="qorderNum" value="" onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')" ></td>
            </tr>
            <tr>
                <td>软件版本:</td>
                <td colspan="2">
                    <select name="versionType" class="versionType"  >

                    </select>
                </td>
            </tr>
            <tr>
                <td>绑定图片:</td>
                <td colspan="2">
                    <select name="imageUrl" class="imageUrl"  >

                    </select>
                </td>
            </tr>
            <tr>
                <td>是否使用:</td>
                <td colspan="2">
                    <select name="status" id="qstatus">
                        <option  value="1" >使用</option>
                        <option  value="2" >停用</option>
                    </select>
                </td>
            </tr>

            <tr>
                <td>是否推荐:</td>
                <td colspan="2">
                    <select  id="IsRecommend" name="IsRecommend">
                        <option  value="1" >推荐</option>
                        <option  value="2" >不推荐</option>
                    </select>
                </td>
            </tr>
            <tr>
                <td>视频地址:</td>
                <td><input type="text"  name="videoUrl" id="qvideoUrl" value=""></td>
            </tr>
            <tr>
                <td>请选择文件:</td>
                <td><input type="file" name="file" id="qfile" multiple="multiple" ></td>
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
        var manualType = '${search.manualType}';
        var versionType = '${search.versionType}';
        var imageUrl = '';
        var departmentType = '${search.departmentType}';
        if(departmentType){
            $('.departmentType').val(departmentType);
        }
        $.ajax({
            url:'../manual_type/findManualType.jhtml',
            type:'POST',
            dataType: 'json' ,
            data:{
                departmentType:departmentType
            },
            success:function(data){
                $(".manualType").empty()
                $(".manualType").append("<option  value=''>请选择</option>")
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
        $.ajax({
            url:'../version_type/findVersionType.jhtml',
            type:'POST',
            dataType: 'json' ,
            data:{
                departmentType:departmentType
            },
            success:function(data){
                $(".versionType").empty()
                $(".versionType").append("<option  value=''>请选择</option>")
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
        //获取图片
        $.ajax({
            url:'../slideshow/findImages.jhtml',
            type:'POST',
            dataType: 'json' ,
            data:{
                departmentType:departmentType
            },
            success:function(data){
                $(".imageUrl").empty()
                $(".imageUrl").append("<option  value=''>请选择</option>")
                for (var i = 0; i < data.length; i++) {
                    var str="";
                    if (data[i].imageUrl==parseInt(imageUrl)){
                        str = "<option  value='"+data[i].imageUrl+"' selected='selected' > "
                                +data[i].imageName+"</option>"
                    }else {
                        str = "<option  value='"+data[i].imageUrl+"'> "
                                +data[i].imageName+"</option>"
                    }
                    $(".imageUrl").append(str)
                };
            }
        });

        var manualName = '${search.manualName}';
        if(manualName){
            $('#manualName').val(manualName);
        }
        if(manualType){
            $('#manualType').val(manualType);
        }
        if(versionType){
            $('#versionType').val(versionType);
        }
        if(imageUrl){
            $('#imageUrl').val(imageUrl);
        }

    });

    //异步提交
    $('#dataForm').submit(function() {
        if(!$('#qmanualName').val()&&!$('#qfile').val()){
            alert("必填项不能为空");
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

    var modify = function(id,realName,manualName, manualType, status,recommend,image,orderNum,videoUrl){
        $('#id').val(id);
        $('#realName').val(realName);
        $('#qmanualName').val(manualName);
        $('#qmanualType').val(manualType);
        $('#IsRecommend').val(recommend);
        $('#qorderNum').val(orderNum);
        $('#imageUrl').val(image);
        $('#qvideoUrl').val(videoUrl);
        if(status==1){
            $('#qstatus').val(1);
        } else {
            $('#qstatus').val(2);
        }
        if(recommend==1){
            $('#IsRecommend').val(1);
        } else {
            $('#IsRecommend').val(2);
        }

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

    var cancel = function(){
        $('#back').hide();
        $('#dataDiv').hide(time);
        $(':input','#dataForm')
                .not(':button, :submit, :reset')
                .val('');
    }

</script>
</html>