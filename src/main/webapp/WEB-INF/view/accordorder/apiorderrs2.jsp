<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="page" tagdir="/WEB-INF/tags" %>

<page:template>
    <jsp:body>
        <%--значение в контролере и имя переманной в здесь в мен--%>
        <c:url value="/accordOrdRs2Add" var="accordOrdRs2Add"/>
        <c:url value="/accordOrdRs2dDel" var="accordOrdRs2dDel"/>
        <c:url value="/accordOrdRs2Update" var="accordOrdRs2Update"/>
        <c:url value="/accordOrdList" var="accordOrdList"/>
        <!-- Page Content -->
        <div class="container">

            <!-- Page Heading/Breadcrumbs -->
            <div class="row">
                <div class="col-lg-12">
                    <h1 class="page-header">Заявка №${numTtn} List MnTov
                        <small>Список Товара </small>
                    </h1>
                    <ol class="breadcrumb">
                        <li><a href="index.html">Home</a></li>
                        <li class="active">Order List page</li>
                    </ol>
                </div>
            </div>
            <!-- /.row -->

            <!-- Content Row -->
            <div class="row">
                <!-- Sidebar Column -->
                <div class="col-md-3">
                    <div class="list-group">

                        <a href="index.html" class="list-group-item">Home</a>
                        <a href="${accordOrdRs2Add}?numTtn=${numTtn}" class="list-group-item">Добавить новую</a>
                        <a href="${accordOrdList}" class="list-group-item">Назад</a>

                    </div>
                </div>
                <!-- Content Column -->
                <div class="col-md-9">
                    <c:if test="${not empty resultObject}">
                        Result:
                        <c:if test="${resultObject == 'rs2'}">
                            <table border="1" cellpadding="5" cellspacing="5">

                                <%--начало вывод заголовка--%>
                                <tr>
                                    <c:forEach var="cStr" items="${aHead}">
                                        <td>${cStr}</td>
                                    </c:forEach>
                                </tr>
                                <%--END вывод заголовка--%>

                                <c:if test="${not empty oListRec}">
                                    <c:forEach var="oRs2" items="${oListRec}">
                                        <tr>
                                            <td>${oRs2.mnTov}</td>
                                            <td>${oRs2.nat}</td>
                                            <td>${oRs2.kvp}</td>
                                            <td>${oRs2.zen}</td>
                                            <td>${oRs2.svp}</td>
                                            <td>
                                                <form action="${accordOrdRs2Update}" method="post">
                                                    <input type="hidden" name="numTtn" value="${numTtn}">
                                                    <input type="hidden" name="mnTov" value="${oRs2.mnTov}">
                                                    <input type="hidden" name="nat" value="${oRs2.nat}">
                                                    <input type="hidden" name="zen" value="${oRs2.zen}">
                                                    <input type="hidden" name="kvp" value="${oRs2.kvp}">
                                                    <input type="submit" value="Изменить" style="float:left">
                                                </form>
                                            </td>
                                            <td>
                                                <form action="${accordOrdRs2dDel}" method="post">
                                                    <input type="hidden" name="numTtn" value="${numTtn}">
                                                    <input type="hidden" name="mnTov" value="${oRs2.mnTov}">
                                                    <input type="submit" value="Удалить " style="float:left">
                                                </form>
                                            </td>
                                        </tr>
                                    </c:forEach>
                                </c:if>
                                <c:if test="${empty oListRec}">
                                    <tr>
                                        <td>Нет еще данных!</td>
                                    </tr>
                                </c:if>
                            </table>
                        </c:if>


                        <c:if test="${resultObject == 'true'}">
                            <%--<font color="green"><b>${resultObject}</b></font>--%>
                            <span style="color: green; font-weight: bold;">${resultObject}</span>
                        </c:if>
                        <c:if test="${resultObject == 'false'}">
                            <%--<font color="red"><b>${resultObject}</b></font>--%>
                            <span style="color: red; font-weight: bold;">${resultObject}</span>
                        </c:if>
                        <c:if test="${resultObject != 'true' and resultObject != 'false'}">
                            <p>${resultObject}</p>
                        </c:if>
                    </c:if>
                </div>
            </div>
            <!-- /.row -->

            <hr>

        </div>
        <!-- /.container -->

    </jsp:body>
</page:template>

