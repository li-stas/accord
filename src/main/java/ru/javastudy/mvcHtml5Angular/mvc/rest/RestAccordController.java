package ru.javastudy.mvcHtml5Angular.mvc.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.javastudy.mvcHtml5Angular.mvc.rest.model.*;
import ru.javastudy.mvcHtml5Angular.mvc.service.DBAccordOrderdService;

import java.util.List;

/**
 * Created for JavaStudy.ru on 28.02.2016.
 */
@Controller
public class RestAccordController {
    private static final Logger log = LoggerFactory.getLogger(RestAccordController.class);
    /* src\main\java\ru\javastudy\mvcHtml5Angular\mvc\service\dbLogService.java
     * будет выбирать данные с таблиц
     */
    @Autowired
    private DBAccordOrderdService dbAccordOrderdService;

    /**
     * look to mvc-config.xml for <mvc:message-converters>. It can produce 'pretty' json response.
     */
    @RequestMapping(value = "/rest/getAccordOrderJSON", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    DBAccordOrdersJSON getAccordOrderJSON() {
        int status = 200;
        List<DBAccordOrderRs1AndRs2JSON> dbAccordOrderRs1AndRs2JSONList = null;
        try {
            dbAccordOrderRs1AndRs2JSONList = dbAccordOrderdService.queryOrderAccordRs1AndRs2JDBC2JSON(); //JDBC
        } catch (Exception e) {
            status = 503;
            log.error(e.getMessage() + " Exception", e);
            //e.printStackTrace();
        }
        System.out.println(dbAccordOrderRs1AndRs2JSONList);
        DBAccordOrdersJSON dbAccordOrdersJSON = new DBAccordOrdersJSON(status);
        dbAccordOrdersJSON.setOrderList(dbAccordOrderRs1AndRs2JSONList);
        return dbAccordOrdersJSON;
    }

    @RequestMapping(value = "/rest/getAccordOrderRs2InRs1JSON", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    DBAccordOrdersJSON getAccordOrderRs2InRs1JSON(
            @RequestParam(value = "kta", defaultValue = "364", required = false) Integer numKta) {
        int status = 200;
        List<DBAccordOrderRs1> dbAccordOrderRs1List = null;
        try {
            dbAccordOrderRs1List = dbAccordOrderdService.queryOrderAccordRs2InRs1JDBC2JSON(numKta); //JDBC
        } catch (Exception e) {
            status = 503;
            log.error(e.getMessage() + " Exception", e);
            //e.printStackTrace();
        }
        System.out.println(dbAccordOrderRs1List);
        DBAccordOrdersJSON dbAccordOrdersJSON = new DBAccordOrdersJSON(status);
        dbAccordOrdersJSON.setOrderList(dbAccordOrderRs1List);
        return dbAccordOrdersJSON;
    }
}
