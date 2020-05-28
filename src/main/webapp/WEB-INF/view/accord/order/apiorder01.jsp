<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="page" tagdir="/WEB-INF/tags" %>

<page:template>
    <jsp:body>
        <%--значение в контролере и имя переманной в здесь в мен--%>
        <c:url value="/accordOrdList" var="accordOrdList"/>
        <c:url value="/accordOrdUpdate" var="accordOrdUpdate"/>
        <c:url value="/accordOrdAdd" var="accordOrdAdd"/>
        <c:url value="/accordOrdDel" var="accordOrdDel"/>
        <c:url value="/accordOrdFilt" var="accordOrdFilt"/>
        <c:url value="/accordOrdViewRs2" var="accordOrdViewRs2"/>

        <!-- Page Content -->
        <div class="container">

            <!-- Page Heading/Breadcrumbs -->
            <div class="row">
                <div class="col-lg-12">
                    <h1 class="page-header">Order List
                        <small>Список заявок</small>
                    </h1>
                    <ol class="breadcrumb">
                        <li><a href="index.html">Home</a>
                        </li>
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
                        <c:if test="${empty resultObject or resultObject == 'true' or resultObject == 'false'}">
                            <a href="${accordOrdList}" class="list-group-item">Начать работу с Заявками</a>
                        </c:if>
                        <c:if test="${not (empty resultObject or resultObject == 'true' or resultObject == 'false')}">
                            <a href="${accordOrdAdd}" class="list-group-item">Добавить новую</a>
                            <a href="${accordOrdFilt}" class="list-group-item">Фильтр</a>
                        </c:if>

                    </div>
                </div>
                <!-- Content Column -->
                <div class="col-md-9">
                    <c:if test="${not empty resultObject}">
                        Result:
                        <c:if test="${resultObject == 'rs1'}">
                            <table border="1" cellpadding="5" cellspacing="5">

                                    <%--начало вывод заголовка--%>
                                <tr>
                                    <c:forEach var="cStr" items="${aHead}">
                                        <td>${cStr}</td>
                                    </c:forEach>
                                </tr>
                                    <%--END вывод заголовка--%>

                                <c:if test="${not empty oListRec}">

                                    <c:forEach var="oRs1" items="${oListRec}">

                                        <tr>
                                            <td>${oRs1.ttn}</td>
                                            <td>${oRs1.dvp}</td>
                                            <td>${oRs1.sdv}</td>
                                            <td>${oRs1.tMesto}</td>
                                            <td>
                                                <form action="${accordOrdViewRs2}" method="post">
                                                    <input type="hidden" name="numTtn" value="${oRs1.ttn}">
                                                    <input type="submit" value="Товар" style="float:left">
                                                </form>
                                            </td>
                                            <td>
                                                <form action = "${accordOrdUpdate}" method="post">
                                                    <input type="hidden" name="numTtn" value="${oRs1.ttn}">
                                                    <input type="hidden" name="tMesto" value="${oRs1.tMesto}">
                                                    <input type="submit" value="Изменить" style="float:left">
                                                </form>
                                            </td>
                                            <td>
                                                <form action="${accordOrdDel}" method="post">
                                                    <input type="hidden" name="numTtn" value="${oRs1.ttn}">
                                                    <input type="submit" value="Удалить" style="float:left">
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

