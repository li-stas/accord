<%--
  Created by IntelliJ IDEA.
  User: Pro
  Date: 27.05.2020
  Time: 9:54
  To change this template use File | Settings | File Templates.
--%>
<<!DOCTYPE html>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="page" tagdir="/WEB-INF/tags" %>

<page:template>
    <jsp:attribute name="title">Javastudy.ru MVC_HTML5_Angular</jsp:attribute>
    <jsp:body>

        <c:url value="/accordOrdUpdateSave" var="accordOrdUpdateSave"/>
        <c:url value="/accordOrdList" var="accordOrdList"/>
        <c:url value="/accordSeekKPl" var="accordSeekKPl"/>
        <c:url value="/accordSeekKGp" var="accordSeekKGp"/>

        <!-- Page Content -->
        <div class="container">

            <!-- Page Heading/Breadcrumbs -->
            <div class="row">
                <div class="col-lg-12">
                    <h1 class="page-header">Update ${oRs1.ttn}
                        <small> Order</small>
                    </h1>
                    <ol class="breadcrumb">
                        <li><a href="index.html">Home</a>
                        </li>
                        <li><a href="${accordOrdList}?kta=${oRs1.kta}">Отменить</a>
                        </li>
                        <li class="active">Update Order</li>
                    </ol>
                </div>
            </div>
            <!-- /.row -->

            <!-- Content Row -->
            <div class="row">
                <!-- Contact Details Column -->
                <div class="col-md-4">
                        <%--<h3>Информация о товарной позиции Заявки №${oRs2.ttn}</h3>
                        <p>Заявка: ${oRs2.ttn}</p>--%>
                    <p>Торговое место: ${oRs1.tMesto} ${oTMesto.ntMesto}</p>
                    <p>Сумма: ${oRs1.sdv}</p>
                    <p>Дата: ${oRs1.dvp}</p>
                        <%--<p>Количестов: ${oRs2.kvp}</p>--%>
                </div>
            </div>
            <!-- /.row -->

            <div class="row">
                <div class="col-md-8">
                        <%--<h3>Измените количество</h3>--%>
                    <script>
                        var request = new XMLHttpRequest();
                        function searchKPl() {
                            var name = document.vinform.kpl.value;
                            /* вызов с контролера */
                            var url = "${accordSeekKPl}?val=" + name
                                + "&numTtn=${oRs1.ttn}"; //var url = "/accordOrdSeekTov?val=" + name;
                            try {
                                request.onreadystatechange = function() {
                                    if (request.readyState == 4) {
                                        var val = request.responseText;
                                        document.getElementById('mylocation').innerHTML = val;
                                    }
                                }
                                request.open("GET", url, true);
                                request.send();
                            } catch (e) {
                                alert("Unable to connect to server");
                            }
                        }
                        function searchKGp() {
                            var cNameKPl = document.vinform.kpl.value;
                            var name = document.vinform.kgp.value;
                            /* вызов с контролера */
                            //alert("cNameKPl="+cNameKPl);
                            var url = "${accordSeekKGp}?val=" + name
                                + "&numTtn=${oRs1.ttn}"
                                + "&numKPl=${oKPl.kKL}"
                                + "&cNameKPl=" + cNameKPl;
                            try {
                                request.onreadystatechange = function() {
                                    if (request.readyState == 4) {
                                        var val = request.responseText;
                                        document.getElementById('mylocation').innerHTML = val;
                                    }
                                }
                                request.open("GET", url, true);
                                request.send();
                            } catch (e) {
                                alert("Unable to connect to server");
                            }
                        }
                    </script>
                    <form name="vinform" id="vinform" action="${accordOrdUpdateSave}" method="post" novalidate="true">

                        <input type="hidden" name="numTtn" value="${oRs1.ttn}">
                        <input type="hidden" name="numKPl" value="${oKPl.kKL}">
                        <input type="hidden" name="numKGp" value="${oKGp.kGp}">

                        <div class="control-group form-group">
                            <div class="controls">
                                <label>Плательщик:</label>
                                <input type="text" class="form-control" id="idkpl" name="kpl" value="${oKPl.nKL}"
                                       required data-validation-required-message="Пожалуйста введите плательщика"
                                       onkeyup="searchKPl()"
                                >
                            </div>
                        </div>
                        <div class="control-group form-group">
                            <div class="controls">
                                <label>Грузоплучатель:</label>
                                <input type="text" class="form-control" id="idkgp" name="kgp" value="${oKGp.nGrpol}"
                                       required data-validation-required-message="Пожалуйста введите грузополучателя"
                                       onkeyup="searchKGp()"
                                >
                            </div>
                        </div>
                        <div id="success"></div>

                        <button type="submit" class="btn btn-primary">Записать</button>
                    </form>
                    <span id="mylocation"></span>
                </div>

            </div>
            <!-- /.row -->

            <hr>
        </div>
        <!-- /.container -->
    </jsp:body>

</page:template>