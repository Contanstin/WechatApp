<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="content-type" content="text/html; charset=utf-8" />
    <title>WechatApp</title>
    <link href="${base}/resources/css/common.css" rel="stylesheet" type="text/css" />
    <link href="${base}/resources/css/main.css" rel="stylesheet" type="text/css" />
    <script type="text/javascript" src="${base}/resources/js/jquery.js"></script>
    <script type="text/javascript" src="${base}/resources/js/jquery.form.js"></script>
    <style type="text/css">
        *{
            font: 12px tahoma, Arial, Verdana, sans-serif;
        }
        html, body {
            width: 100%;
            height: 100%;
            overflow: hidden;
        }
        .passwordDiv{
            position:absolute;
            left:50%;
            top:20%;
            background-color: #fff;
            padding: 1px;
            z-index: 999;
            display: none;
        }
    </style>
    <script type="text/javascript">
        $().ready(function() {

            var $nav = $("#nav a");
            var $menu = $("#menu dl");
            var $menuItem = $("#menu a");

            $nav.click(function() {
                var $this = $(this);
                $nav.removeClass("current");
                $this.addClass("current");
                var $currentMenu = $($this.attr("href"));
                $menu.hide();
                $currentMenu.show();
                var test = $currentMenu.find("dd").first().find("a");
                $currentMenu.find("dd").first().find("a").click();
                return false;
            });

            $menuItem.click(function() {
                var $this = $(this);
                $menuItem.removeClass("current");
                $this.addClass("current");
            });

        });
    </script>
</head>
<body>
<script type="text/javascript">
    if (self != top) {
        top.location = self.location;
    };
</script>
<table class="main">
    <tr>
        <th class="logo">
            <a href="main.jhtml">
                WechatApp
            </a>
        </th>
        <th>
            <div id="nav" class="nav">
                <ul>
                <#list menus as fmenu>
                    <li>
                        <a href="${"#"+fmenu.authorities}">${fmenu.menuName}</a>
                    </li>
                </#list>
                </ul>
            </div>
            <div class="link">
            </div>
            <div class="link">
                <strong>${username}</strong>
                ${message("admin.main.hello")}!
                <a href="#" onclick="changePassword()">[修改密码]</a>
                <a href="../logout.jhtml" target="_top">[退出]</a>
            </div>
        </th>
    </tr>
    <tr>
        <td id="menu" class="menu">
        <#if (menus?size > 0)>
            <#list menus as menu>
                <#if menu_index==0>
                <dl id="${menu.authorities}" class="default">
                <#else>
                </dl>
                <dl id="${menu.authorities}">
                </#if>
                <dt>${menu.menuName}</dt>
                <#list menu.submenu as submenu>
                    <dd>
                        <a href="${base+submenu.menuUrl}" target="iframe">${submenu.menuName}</a>
                    </dd>
                </#list>
            </#list>
        </dl>
        </#if>
        </td>
        <td>
            <iframe id="iframe" name="iframe" src="${base+firsturl}" frameborder="0"></iframe>
        </td>
    </tr>
</table>
<div class="passwordDiv" id="passwordDiv">
    <form id="passwordForm" method="post" action="${base}/authority/changPassword.jhtml" >
        <table>
            <tr>
                <td style="border-width:1px;padding: 8px;border-style: solid;border-color:#44A3B">新密码:</td>
                <td style="border-width:1px;padding: 8px;border-style: solid;border-color:#44A3B"><input type="password" name="newpassword" id="newpassword" value="" maxlength="10"></td>
            </tr>
            <tr>
                <td style="border-width:1px;padding: 8px;border-style: solid;border-color:#44A3B">确认密码:</td>
                <td style="border-width:1px;padding: 8px;border-style: solid;border-color:#44A3B"><input type="password" name="confirmpassword" id="confirmpassword" value="" maxlength="10"></td>
            </tr>
            <tr align="center">
                <td colspan="2" style="border-width:1px;padding: 8px;border-style: solid;border-color:#44A3B"><input type="submit" value="保存">
                    &nbsp; <button type="button" onclick="cancel();">取消</button>
                </td>
            </tr>
        </table>
    </form>
</div>
<script type="application/javascript">
    var time = 100;
    var changePassword = function(){
        $('#passwordDiv').show(time);
    }
    var cancel = function(){
        $('#passwordDiv').hide(time);
        $(':input','#passwordForm')
                .not(':button, :submit, :reset, :hidden')
                .val('');
    }
    $('#passwordForm').submit(function() {
        if($('#newpassword').val() == ""){
            alert("新密码必须输入");
            return false;
        };
        if($.trim($('#newpassword').val()).length <3){
            alert("新密码长度不能小于3位");
            return false;
        };
        if($('#newpassword').val()!=$('#confirmpassword').val()){
            alert("两次输入的密码不一致");
            return false;
        };

        var option = {
            dataType:'json',
            success : function(data) {
                var obj = eval('(' + data + ')')
                if(obj.code=="SUCCESS"){
                    alert("密码修改成功！");
                    cancel();
                }else if(obj.code=="FAIL"){
                    alert(obj.message);
                }else{
                    alert("密码修改失败");
                }
            }
        };
        $(this).ajaxSubmit(option);
        return false;
    });
</script>
</body>
</html>