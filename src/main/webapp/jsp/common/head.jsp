<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>超市订单管理系统</title>
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" />
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/public.css" />
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.4.1.js"></script>
</head>
<body>
    <!--头部-->
    <header class="publicHeader">
        <h1>超市订单管理系统</h1>
        <div class="publicHeaderR">
            <p><span>下午好！</span><span style="color: #fff21b"> ${sessionId.userName}</span> , 欢迎你！</p>
            <a href="${pageContext.request.contextPath}/logout">退出</a>
        </div>
    </header>

    <!--时间-->
    <section class="publicTime">
        <span id="time"></span>
        <span id="browser">温馨提示：为了能正常浏览，请使用高版本浏览器！（IE10+）</span>
    </section>

     <!--主体内容-->
     <section class="publicMian ">
         <div class="left">
             <h2 class="leftH2"><span class="span1"></span>功能列表 <span></span></h2>
             <nav>
                 <ul class="list">
                     <li><a href="${pageContext.request.contextPath}/jsp/bill.do?method=query">订单管理</a></li>
                     <li><a href="${pageContext.request.contextPath}/jsp/provider.do?method=query">供应商管理</a></li>
                     <li><a href="${pageContext.request.contextPath}/user?method=queryUser">用户管理</a></li>
                     <li><a href="${pageContext.request.contextPath}/jsp/pwdmodify.jsp">密码修改</a></li>
                     <li><a href="${pageContext.request.contextPath}/logout">退出系统</a></li>
                 </ul>
             </nav>
         </div>
         <input type="hidden" id="path" name="path" value="${pageContext.request.contextPath}"/>
         <input type="hidden" id="referer" name="referer" value="<%=request.getHeader("Referer")%>"/>