package ru.javastudy.mvcHtml5Angular.mvc.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.javastudy.mvcHtml5Angular.mvc.jdbc.JDBCAccordDAO;
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
    DBAccordOrdersJSONImpl getAccordOrderJSON() {
        int status = 200;
        List<DBAccordOrderRs1AndRs2> dbAccordOrderRs1AndRs2List = null;
        try {
            dbAccordOrderRs1AndRs2List = dbAccordOrderdService.queryOrderAccordRs1AndRs2JDBC2JSON(); //JDBC
        } catch (Exception e) {
            status = 503;
            log.error(e.getMessage() + " Exception", e);
            //e.printStackTrace();
        }
        System.out.println(dbAccordOrderRs1AndRs2List);
        DBAccordOrdersJSONImpl dbAccordOrdersJSONImpl = new DBAccordOrdersJSONImpl(status, dbAccordOrderRs1AndRs2List.size());
        dbAccordOrdersJSONImpl.setOrderList(dbAccordOrderRs1AndRs2List);

        return dbAccordOrdersJSONImpl;
    }

    @RequestMapping(value = "/rest/getAccordOrderRs2InRs1JSON", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    DBAccordOrdersJSONImpl getAccordOrderRs2InRs1JSON(
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
        DBAccordOrdersJSONImpl dbAccordOrdersJSONImpl = new DBAccordOrdersJSONImpl(status, dbAccordOrderRs1List.size());
        dbAccordOrdersJSONImpl.setOrderList(dbAccordOrderRs1List);
        return dbAccordOrdersJSONImpl;
    }

    @RequestMapping(value = "/rest/deleteAccordOrderRs1", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    DBAccordOrdersJSONImpl deleleAccordOrderRs1(
            @RequestParam(value = "ttn", defaultValue = "606060", required = false) Integer numTtn) {

        System.out.println("RestAccordController: delete called nomTtn=" + numTtn);

        int status = 200;
        int cntRec = 0;

        try {
            cntRec = dbAccordOrderdService.queryOrderRs1Delete(numTtn);
        } catch (Exception e) {
            status = 503;
            log.error(e.getMessage() + " Exception", e);
            //e.printStackTrace();
        }
        System.out.println("cntRec = " + cntRec);
        DBAccordOrdersJSONImpl dbAccordOrdersJSONImpl = new DBAccordOrdersJSONImpl(status, cntRec);

        return dbAccordOrdersJSONImpl;
    }

    @RequestMapping(value = "/rest/updateAccordOrderRs1", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    DBAccordOrdersJSONImpl updateAccordOrderRs1(
            @RequestParam(value = "ttn", defaultValue = "606060", required = false) Integer numTtn,
            @RequestParam(value = "tmesto", defaultValue = "606060", required = false) Integer numTMesto) {

        System.out.printf("RestAccordController: delete called nomTtn = %d numTMesto = %d", numTtn, numTMesto);

        int status = 200;
        int cntRec = 0;

        try {
            cntRec = dbAccordOrderdService.queryOrderRs1Delete(numTtn);
        } catch (Exception e) {
            status = 503;
            log.error(e.getMessage() + " Exception", e);
            //e.printStackTrace();
        }
        System.out.println("cntRec = " + cntRec);
        DBAccordOrdersJSONImpl dbAccordOrdersJSONImpl = new DBAccordOrdersJSONImpl(status, cntRec);

        return dbAccordOrdersJSONImpl;
    }


}
