<%-- 
    Document   : LoginOrRegistFailWindow
    Created on : 2015-12-18, 21:46:42
    Author     : Administrator
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>酷热音乐-错误提示</title>
        <style>
            .body{background: url(resource/images/LoginWindow_InterfaceBackground.jpg) no-repeat; background-size: 1340px 580px;
                 width: 1340px;height: 700px;background-color:black;position: fixed;}
            
            .tip{background: url(resource/images/LoginWindow_TipShow.gif) no-repeat; background-size: 100%,100%;width: 25%;height: 20%;}
            
            .login{background: url(resource/images/LoginWindow_LoginBackground2.gif) no-repeat; background-size: 100%,100%;width: 40%;height: 40%; 
                  position: relative;left: 435px;top:20px;border: 5px  white solid;border-radius: 20px;color: white;background-color: rgba(10,10,10,0.4);}            
            .login_p{text-align: center;font-size: 40px;border-bottom: 1px gray solid;margin-top: 5px;margin-bottom: 0px;}
            .login form{width: 100%;height: 100%; position: relative;border-top: 1px blue solid;text-align: center}
            .login_form_p{font-size: 30px;}
            .login_form_input{background-color: blueviolet; width: 20%;height: 15%;border-radius:4px;font-size: 25px;color: white;margin-top: 20px}
        </style>
    </head>
    <%! String errortype="";%>
    <% errortype=request.getParameter("errortype");%>
    <body class="body" onload="show()">
        <div class="tip"></div>
        <div class="login">
            <p id="type" class="login_p"></p>
            <form>
                <p class="login_form_p"></p>
                <input type="button" class="login_form_input" value="返回" onclick="back()"/>
            </form>
        </div>
            <script>
                var type=arguments[0];
                var errortype=arguments[1];
                
                function show(){
                    var p=document.getElementsByClassName('login_p');
                    var a=document.getElementsByClassName('login_form_p');
                    if(<%=errortype%>=="11"){
                        p[0].innerText="用户登录";
                        a[0].innerText="错误：密码不匹配！";
                    }
                    else if(<%=errortype%>=="12"){
                        p[0].innerText="用户登录";
                        a[0].innerText="错误：用户不存在！";
                    }
                    else if(<%=errortype%>=="21"){
                        p[0].innerText="用户注册";
                        a[0].innerText="错误：密码不一致！";
                    }
                    else if(<%=errortype%>=="22"){
                        p[0].innerText="用户注册";
                        a[0].innerText="错误：已有该账号！";
                    }
                    else if(<%=errortype%>=="23"){
                        p[0].innerText="用户注册";
                        a[0].innerText="错误：已有该昵称！";
                    }
                    else if(<%=errortype%>=="24"){
                        p[0].innerText="用户注册";
                        a[0].innerText="错误：未知错误！注册失败。";
                    }
                    else{
                        p[0].innerText="未知";
                        a[0].innerText="错误：未知错误！";
                    }
                }
                function back(){
                    if(<%=errortype%>=="11" || <%=errortype%>=="12"){
                        window.location='LoginWindow.jsp';
                    }
                    else{
                        window.location='RegistWindow.jsp';
                    }
                }
            </script>
    </body>
</html>
