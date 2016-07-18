<%@ page language="java" contentType="text/html;charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>异常页面</title>
</head>
<body>
	<center style="color:red;font-size:36px;">出错啦</center>
	<br />
	<hr />
	<s:property value="exception" />
	<s:debug></s:debug>
</body>
</html>
