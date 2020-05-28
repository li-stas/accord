<!DOCTYPE html>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="page" tagdir="/WEB-INF/tags" %>

<page:template>
    <jsp:attribute name="title">Javastudy.ru MVC_HTML5_Angular</jsp:attribute>

    <jsp:body>
        <c:url value="/accordOrdRs2Save" var="accordOrdRs2Save"/>
        <!-- Page Content -->
        <div class="container">

            <!-- Page Heading/Breadcrumbs -->
            <div class="row">
                <div class="col-lg-12">
                    <h1 class="page-header">Info
                        <small>MnTov</small>
                    </h1>
                    <ol class="breadcrumb">
                        <li><a href="index.html">Home</a>
                        </li>
                        <li class="active">Info Wares</li>
                    </ol>
                </div>
            </div>
            <!-- /.row -->

            <!-- Content Row -->
            <div class="row">
                <!-- Contact Details Column -->
                <div class="col-md-4">
                    <h3>Информация о товарной позиции Заявки №${oRs2.ttn}</h3>
                    <p>
                        Заявка: ${oRs2.ttn}
                    </p>
                    <p>
                        Товар: ${oRs2.mnTov} ${oRs2.nat}
                    </p>
                    <p>
                        Цена: ${oRs2.zen}
                    </p>
                    <p>
                        Количестов: ${oRs2.kvp}
                    </p>
                </div>
            </div>
            <!-- /.row -->

            <!-- Contact Form -->
            <div class="row">
                <div class="col-md-8">
                    <h3>Измените количество</h3>
                    <form:form name="updateRs2" id="rs2Form" action="${accordOrdRs2Save}" method="post" novalidate="true">
                        <input type="hidden" name="numTtn" value="${oRs2.ttn}">
                        <input type="hidden" name="mnTov" value="${oRs2.mnTov}">
                        <input type="hidden" name="nat" value="${oRs2.nat}">
                        <input type="hidden" name="zen" value="${oRs2.zen}">
                        <div class="control-group form-group">
                            <div class="controls">
                                <label>Количестов:</label>

                                <%--ввод только + ---%>
                                <style>
                                    .raz {
                                        all: unset;
                                        -moz-appearance: textfield;
                                        width: 3em;
                                        text-align: center;
                                    }
                                    .raz::-webkit-inner-spin-button {
                                        display: none;
                                    }
                                </style>
                                <button type="button" onclick="this.nextElementSibling.stepDown()">-</button>
                                <input type="number" min="0" max="100" name="kvp" value="${oRs2.kvp}" readonly class="raz">
                                <button type="button" onclick="this.previousElementSibling.stepUp()">+</button>

                                <%--
                                <input type="number" size="4" name="kvp" value="${oRs2.kvp}" onkeyup="return proverka(this);" onchange="return proverka(this);" />
                                <script type="text/javascript">
                                    function proverka(input) {
                                        input.value = input.value.replace(/[^\d,]/g, '');
                                    };
                                </script>
                                --%>
                            </div>
                        </div>
                        <div id="success"></div>
                        <!-- For success/fail messages -->
                        <button type="submit" class="btn btn-primary">Записать</button>
                    </form:form>
                </div>

            </div>
            <!-- /.row -->

            <hr>
        </div>
        <!-- /.container -->
    </jsp:body>

</page:template>