<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>登陆</title>
    <meta http-equiv="content-type" content="text/html; charset=utf-8" />
    <link href="${base}/resources/css/supersized.css" rel="stylesheet" type="text/css" />
    <link href="${base}/resources/css/login.css" rel="stylesheet" type="text/css" />
    <link href="${base}/resources/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
    <link href="${base}/resources/css/jquery.datetimepicker.css" rel="stylesheet" type="text/css" />
    <script type="text/javascript" src="${base}/resources/js/jquery.js"></script>
    <script type="text/javascript" src="${base}/resources/js/jquery.tools.js"></script>
    <script type="text/javascript" src="${base}/resources/js/jquery.validate.js"></script>
    <script type="text/javascript" src="${base}/resources/js/tooltips.js"></script>

    <script type="text/javascript">
        $().ready(function() {
            var $inputForm = $("#loginForm");
            var $captchaImage = $("#captchaImage");
            // 表单验证
            <#--$inputForm.validate({-->
                <#--rules: {-->
                    <#--userName: {-->
                        <#--required: true,-->
                        <#--minlength: 2,-->
                        <#--maxlength: 20,-->
                    <#--},-->
                    <#--password: {-->
                        <#--required: true,-->
                        <#--minlength: 4,-->
                        <#--maxlength: 20-->
                    <#--},-->
                    <#--captcha: {-->
                        <#--rangelength:[4,4]-->
                    <#--}-->
                <#--},-->
                <#--messages: {-->
                    <#--userName: {-->
                        <#--required: "${message("system.validate.required")}",-->
                        <#--minlength: "${message("system.validate.minlength")}",-->
                        <#--maxlength: "${message("system.validate.maxlength")}"-->
                    <#--},-->
                    <#--password: {-->
                        <#--required: "${message("system.validate.required")}",-->
                        <#--minlength: "${message("system.validate.minlength")}",-->
                        <#--maxlength: "${message("system.validate.maxlength")}"-->
                    <#--},-->
                    <#--captcha: {-->
                        <#--rangelength: "${message("system.validate.captcha")}"-->
                    <#--}-->
                <#--}-->
            <#--});-->
            // 更换验证码
            $captchaImage.click( function() {
                $captchaImage.attr("src", "${base}/common/captcha.jhtml?captchaId=${captchaId}&timestamp=" + (new Date()).valueOf());
            });
        });
    </script>

</head>
<body>
<div class="page-container">
    <div class="main_box">
        <div class="login_box">
            <div class="login_logo">
                ${Request[FormAuthenticationFilter.DEFAULT_ERROR_KEY_ATTRIBUTE_NAME]}
                    <h1>深圳海浦蒙特科技有限公司</h1>
            </div>
            <div class="login_form">
                <form id="loginForm" action="login.jhtml" method="post">
                    <input type="hidden" name="captchaId" value="${captchaId}" />
                    <div class="form-group">
                        <label for="j_username" class="t">${message("login.username")}:</label>
                        <input type="text" id="userName" name="userName" class="form-control x319 in" maxlength="20"/>
                    </div>
                    <div class="form-group">
                        <label for="j_password" class="t"> ${message("login.password")}:</label>
                        <input type="password" id="password" name="password" class="password form-control x319 in" maxlength="20" autocomplete="off"  />
                    </div>
                    <div class="form-group">
                        <label for="j_captcha" class="t">${message("login.captcha")}:</label>
                        <input type="text" id="captcha" name="captcha" class="form-control x164 in" maxlength="4" autocomplete="off" /><img id="captchaImage" class="captchaImage" src="${base}/common/captcha.jhtml?captchaId=${captchaId}" />
                    </div>
                    <div class="form-group space">
                        <button type="submit"  id="submit_btn"
                                class="btn btn-primary btn-lg">${message("Page.submit")}</button>
                    </div>
                </form>
            </div>
        </div>
        <div class="bottom">Copyright &copy; 2011 - 2018</div>
    </div>
</div>
<script src="${base}/resources/js/supersized.3.2.7.min.js"></script>
<script src="${base}/resources/js/supersized-init.js"></script>
<script src="${base}/resources/js/scripts.js"></script>
<div style="text-align:center;">
</div>
</body>
<#if (errmsg??)>
<script type="text/javascript">
show_err_msg("${message(errmsg)}");
</script>
</#if>
</html>