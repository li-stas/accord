<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="page" tagdir="/WEB-INF/tags" %>

<page:template>
    <jsp:body>
        <c:url value="/rest/getAccordOrderJSON" var="getAccordOrderJSON"/>
        <c:url value="/rest/getAccordOrderRs2InRs1JSON" var="getAccordOrderRs2InRs1JSON"/>
        <c:url value="/rest/deleteAccordOrderRs1" var="deleteAccordOrderRs1"/>
        <c:url value="/rest/updateAccordOrderRs1" var="updateAccordOrderRs1"/>
        <c:url value="/rest/insertOrderRs1" var="insertOrderRs1"/>
        <!-- Page Content -->
        <div class="container">

            <!-- Page Heading/Breadcrumbs -->
            <div class="row">
                <div class="col-lg-12">
                    <h1 class="page-header">JDBC в Spring
                        <small>JDBCTemplate</small>
                    </h1>
                    <ol class="breadcrumb">
                        <li><a href="index.html">Home</a>
                        </li>
                        <li class="active">Rest services (JSON)</li>
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
                            <%--<a href="${getRestUsers}" class="list-group-item">Get Rest Users </a>
                            <a href="${getRestPosts}" class="list-group-item">Get Rest Posts </a>
                            <a href="${getPostsById}" class="list-group-item">Get Rest Posts By Id </a>
                            <a href="${deletePostById}" class="list-group-item">Delete Post By Id </a>--%>
                        <a href="${getAccordOrderJSON}" class="list-group-item">Get Order Rs1&Rs2</a>
                        <a href="${getAccordOrderRs2InRs1JSON}?kta=364" class="list-group-item">Get Order Rs2InRs1</a>
                        <a href="${deleteAccordOrderRs1}?ttn=631184" class="list-group-item">Delete ttn=631184</a>
                        <a href="${updateAccordOrderRs1}?ttn=631242&tmesto=12621" class="list-group-item">update ttn=631242&tmesto=12621</a>
                        <a href="${insertOrderRs1};dvp=2020-04-01;tmesto=31609;kta=559;prz=0" class="list-group-item">insert Order</a>
                    </div>
                </div>

                <!-- Content Column -->
                <div class="col-md-9">
                    <c:if test="${not empty resultObject}">
                        Result:
                        <c:if test="${resultObject == 'true'}">
                            <font color="green"><b>${resultObject}</b></font>
                        </c:if>
                        <c:if test="${resultObject == 'false'}">
                            <font color="red"><b>${resultObject}</b></font>
                        </c:if>
                        <c:if test="${resultObject != 'true' and resultObject != 'false'}">
                            <p>${resultObject}</p>
                        </c:if>
                    </c:if>
                </div>
                <a href="javascript:history.back()">Назад</a>
            </div>
            <!-- /.row -->

            <hr>

        </div>
        <!-- /.container -->

    </jsp:body>
</page:template>

