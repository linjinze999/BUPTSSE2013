<%-- 
    Document   : RegistWindow
    Created on : 2015-12-18, 13:19:57
    Author     : Administrator
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>酷热音乐-用户注册</title>
        <style>
            .body{background: url(resource/images/LoginWindow_InterfaceBackground.jpg) no-repeat; background-size: 1340px 580px;
                 width: 1340px;height: 700px;background-color:black;position: fixed;}
            
            .tip{background: url(resource/images/LoginWindow_TipShow.gif) no-repeat; background-size: 100%,100%;width: 25%;height: 20%;}
            
            .login{background: url(resource/images/LoginWindow_LoginBackground2.gif) no-repeat; background-size: 100%,100%;width: 40%;height: 60%; 
                  position: absolute;left: 435px;top:80px;border: 5px  white solid;border-radius: 20px;color: white;background-color: rgba(10,10,10,0.4);}            
            .login p{text-align: center;font-size: 40px;border-bottom: 1px gray solid;margin-top: 5px;margin-bottom: 0px;}
            .login form{width: 100%;height: 100%; position: relative;border-top: 1px blue solid;}
            .login form a{font-size: 30px;margin-left: 8%;}
            .login_form_input1{background-color: rgba(200,200,200,0.2); width: 50%;height: 10%;border-radius:7px;margin-top: 20px;font-size: 25px;color: white;}
            .login_form_input2{background-color: blueviolet; width: 20%;height: 10%;border-radius:4px;margin-left: 20%;font-size: 25px;color: white;margin-top: 20px}
        </style>
    </head>
    <body class="body">
        <div class="tip"></div>
        <div class="login">
            <p>用户注册</p>
            <form action="regist" method="post">
                <a>账&nbsp;&nbsp;&nbsp;&nbsp;号：</a>
                <input type="text" class="login_form_input1" name="clientid" title="Enter your ID" required="true" placeholder="&nbsp;ID"/><br/>
                <a>密&nbsp;&nbsp;&nbsp;&nbsp;码：</a>
                <input type="password" class="login_form_input1" name="clientpassword" title="Enter your password" required="true" placeholder="&nbsp;password"/><br/>
                <a>重复密码：</a>
                <input type="password" class="login_form_input1" name="clientpasswordagain" title="Enter your password again" required="true" placeholder="&nbsp;password"/><br/>
                <a>昵&nbsp;&nbsp;&nbsp;&nbsp;称：</a>
                <input type="text" class="login_form_input1" name="clientname" title="Enter your name" required="true" placeholder="&nbsp;name"/><br/>
                <input type="button" class="login_form_input2" value="登录" onclick="window.location='LoginWindow.jsp';"/>
                <input type="submit" class="login_form_input2" value="注册"/>
            </form>
        </div>
    </body>
</html>
