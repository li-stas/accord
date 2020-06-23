<!DOCTYPE html>
<%@tag description="Template Site tag" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>


 <%--*.html - добавить в mvc-config.xml--%>
<c:url value="/jdbcrptord01.html" var="jdbcaccordrptord01"/>
<c:url value="/jdbcrpttov01.html" var="jdbcaccordrpttov01"/>
<c:url value="/restaccord.html" var="restaccord"/>
<c:url value="/accordorder.html" var="accordorder"/>
<c:url value="/security/admin.html" var="admin" />



<!-- Navigation -->
<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
    <div class="container">
        <!-- Brand and toggle get grouped for better mobile display -->
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="index.html"><spring:message code="navMenu.home"/></a>
        </div>
        <!-- Collect the nav links, forms, and other content for toggling -->
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav navbar-right">
                <security:authorize access="hasAnyRole('ROLE_ADMIN','ROLE_SUPER_USER', 'ROLE_USER')" var="isUSer"/>


                <c:if test="${not isUSer}">
                    <li style="padding-top: 15px; padding-bottom: 15px; color: red">
                        <c:if test="${empty param.error}">
                            <spring:message code="navMenu.notLogin"/>
                        </c:if>
                    </li>
                    <li> <a style="color: Green;" href="<c:url value="/login.html"/>"><spring:message code="navMenu.login"/></a> </li>
                </c:if>

                <c:if test="${isUSer}">
                    <li style="padding-top: 15px; padding-bottom: 15px; color: green">
                        <spring:message code="navMenu.existLogin"/>
                        <security:authentication property="principal.username"/>  <spring:message code="navMenu.existLoginRole"/>
                        <b><security:authentication property="principal.authorities"/></b>

                    </li>
                    <li> <a style="color: red;" href="<c:url value="/j_spring_security_logout"/>"><spring:message code="navMenu.logout"/></a> </li>
                </c:if>



                <%--добавляет пунк в верхнем меню--%>
                <li class="dropdown">
                    <%--code="navMenu.accord" -> src\main\resources\locales>messages_ru.properties--%>
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown"><spring:message code="navMenu.accord"/><b class="caret"></b></a>
                    <ul class="dropdown-menu">
                        <c:if test="${isUSer}">
                        <li>
                            <%--переменная обявлена выше, которая ссылается на html -странищу.
                            принажатии ссылки вызывается mvc-config.xml
                            --%>
                            <a href="${jdbcaccordrptord01}">Отчет продаж Торговых агентов</a>
                        </li>
                        <li>
                            <a href="${jdbcaccordrpttov01}">Отчет продаж Продукции</a>
                        </li>
                        <li>
                            <a href="${restaccord}">Rest Services JSON</a>
                        </li>
                        <li>
                            <a href="${accordorder}">API Order Accord</a>
                        </li>
                        </c:if>

                        <c:if test="${not isUSer}">
                        <li>
                            <a href="${admin}" class="list-group-item">SingIn (admin.jsp)</a>
                        </li>
                        </c:if>
                    </ul>
                </li>

            </ul>
        </div>

    </div>
    <!-- /.container -->
</nav>
