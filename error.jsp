<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html lang="true">
  <head>
    <html:base />
    
    <title>Error</title>

	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link type="image/x-icon" href="img/police.ico" rel="icon"/>
	<link rel="shortcut icon" type="image/ico" href="img/police.ico"/>
  <style>
	body{
		font-size:12px;
		margin:0px auto;
		font-family:arial;
	}
	#login_all{
		border:1px solid rgb(51, 102, 153);
		width:300px;
		margin:200px auto;
		text-align:left;
	}
	#login_header{
		background-image: url(img/logo.jpg) ;
		height:27px;
		width:270px;
	}

	#login_footer{
		width:290px;
		text-align:right;
		margin:5px 0;
	}
  </style>
  <script language=javascript>
		function countDown(secs,surl)
		{
		 tiao.innerText=secs;
		 if(--secs>0){
		     setTimeout("countDown("+secs+",'"+surl+"')",1000);
		     }
		 else{
		  
		     location.href=surl;
		     }
		 }
  </script>
  </head>
  
  <body>   
    <center>
  <div id="login_all">
  <div id="login_header"></div>
  <div id="login_body">
  	<br />系统出现一个错误...
	<br />系统将在<span id="tiao" style="color:red">5</span>秒后直接跳转回主页面。
	<br />点击"返回系统"，立即跳转。
	<script language="javascript">countDown(5,'Map.do?action=publish');</script>
  </div>
  <div id="login_footer">
	<a href="Map.do?action=publish">返回系统</a>
  </div>
  </div>
   </center>
  </body>
</html:html>
