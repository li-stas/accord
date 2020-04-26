package ru.javastudy.mvcHtml5Angular.mvc.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.javastudy.mvcHtml5Angular.mvc.rest.model.*;
import ru.javastudy.mvcHtml5Angular.mvc.service.DBAccordOrderdService;

import java.util.List;

/**
 * Created for JavaStudy.ru on 28.02.2016.
 */
@Controller
public class RestAccordController {

    /* src\main\java\ru\javastudy\mvcHtml5Angular\mvc\service\dbLogService.java
    * будет выбирать данные с таблиц
    */
    @Autowired
    private DBAccordOrderdService dbAccordOrderdService;


    /** look to mvc-config.xml for <mvc:message-converters>. It can produce 'pretty' json response. */
    @RequestMapping(value = "/rest/getAccordOrderJSON", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    DBAccordOrdersJSON getAllDBLogsJSON() {
        List<DBAccordOrderRs1AndRs2JSON> dbAccordOrderRs1AndRs2JSONList = null;
        try {
            dbAccordOrderRs1AndRs2JSONList = dbAccordOrderdService.queryOrderAccordRs1AndRs2JDBC2JSON(); //JDBC
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(dbAccordOrderRs1AndRs2JSONList);
        DBAccordOrdersJSON dbAccordOrdersJSON = new DBAccordOrdersJSON();
        dbAccordOrdersJSON.setOrderList(dbAccordOrderRs1AndRs2JSONList);
        return dbAccordOrdersJSON;
    }
}
