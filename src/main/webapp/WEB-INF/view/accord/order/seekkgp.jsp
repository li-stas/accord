<!DOCTYPE html>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="page" tagdir="/WEB-INF/tags" %>

<page:template>
    <jsp:attribute name="title">Javastudy.ru MVC_HTML5_Angular</jsp:attribute>
    <jsp:body>

        <c:url value="/accordOrdChoiceKGp" var="accordOrdChoiceKGp"/>

        <c:if test="${empty name}">
            <p>Please enter name!</p>
        </c:if>
        <c:if test="${not empty name}">
            <table border="1" cellpadding="5" cellspacing="5">
                    <%--начало вывод заголовка--%>
                <tr>
                    <c:forEach var="cStr" items="${aHead}">
                        <td>${cStr}</td>
                    </c:forEach>
                </tr>
                    <%--END вывод заголовка--%>
                <c:if test="${not empty oListKGp}">
                <c:forEach var="oKGp" items="${oListKGp}">
                <tr>
                    <td>${oKGp.kGp}</td>
                    <td>${oKGp.nGrpol}</td>
                    <td>${oKGp.adr}</td>
                    <td>
                        <form action="${accordOrdChoiceKGp}" method="post">
                            <input type="hidden" name="numTtn" value="${numTtn}">
                            <input type="hidden" name="numKGp" value="${oKGp.kGp}">
                            <input type="hidden" name="cNameKPl" value="${cNameKPl}">
                            <input type="submit" value="Выбрать" style="float:left">
                        </form>
                    </td>
                <tr>
                    </c:forEach>
                    </c:if>
                    <c:if test="${empty oListKGp}">
                <tr>
                    <td>Нет еще данных!</td>
                </tr>
                </c:if>
            </table>
        </c:if>
    </jsp:body>

</page:template>

