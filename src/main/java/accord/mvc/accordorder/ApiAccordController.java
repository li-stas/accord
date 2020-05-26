package accord.mvc.accordorder;

import accord.mvc.rest.RestAccordController;
import accord.mvc.rest.model.DBAccordOrderRs1;
import accord.mvc.rest.model.DBAccordOrderRs2;
import accord.mvc.service.DBAccordOrderdService;
import accord.mvc.service.JBDCAccordDAORs2;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Arrays;
import java.util.List;

@Controller
public class ApiAccordController {
    private static final Logger log = LoggerFactory.getLogger(RestAccordController.class);

    @Autowired
    private JBDCAccordDAORs2 dbAccordDAORs2;
    @Autowired
    private DBAccordOrderdService dbAccordOrderdService;

    @RequestMapping(value = "/accordOrdList", method = RequestMethod.GET)
    public ModelAndView accordOrdList(
            @RequestParam(value = "kta", defaultValue = "364", required = false) Integer numKta) {
        System.out.printf("numKta=%d\n",numKta);

        List<String> aHead = Arrays.asList("Ном.заказа", "Дата", "Сумма, грн", "Тогр.место","Товар","Изменить","Удалить");

        List<DBAccordOrderRs1> dbAccordOrderRs1List = null;
        dbAccordOrderRs1List = dbAccordOrderdService.queryOrderAccordRs2InRs1JDBC2JSON(numKta); //JDBC

        boolean result = false;
        final ModelAndView mv = new ModelAndView("/accordorder/apiorder01");
        mv.addObject("resultObject", "rs1");
        mv.addObject("aHead", aHead);
        mv.addObject("oListRec", dbAccordOrderRs1List);
        return mv;
    }

    @RequestMapping(value = "/accordOrdAdd", method = RequestMethod.GET)
    public ModelAndView accordOrdAdd() {
        boolean result = false;
        return new ModelAndView("/accordorder/apiorder01", "resultObject", result);
    }

    @RequestMapping(value = "/accordOrdDel", method = RequestMethod.POST)
    public ModelAndView accordOrdDel(
            @RequestParam(value = "numTtn",  required = false) int numTtn) {
        System.out.printf("numTtn=%d\n", numTtn);
        boolean result = false;
        if (dbAccordOrderdService.queryOrderRs1Delete(numTtn) > 0) {
            result = true;
        }
        return new ModelAndView("/accordorder/apiorder01", "resultObject", result);
    }

    @RequestMapping(value = "/accordOrdFilt", method = RequestMethod.GET)
    public ModelAndView accordOrdFilt() {
        boolean result = false;

        return new ModelAndView("/accordorder/apiorder01", "resultObject", result);
    }

    @RequestMapping(value = "/accordOrdViewRs2", method = RequestMethod.POST)
    public ModelAndView accordOrdViewRs2(
            @RequestParam(value = "numTtn",  required = false) int numTtn) {
        System.out.printf("numTtn=%d\n", numTtn);

        List<String> aHead =
                Arrays.asList("Код товара", "Наименование товара", "К-во", "Цена","Сумма, грн","Изменить","Удалить");
        List<DBAccordOrderRs2> dbAccordOrderRs2List = dbAccordOrderdService.queryOrderRs2(numTtn);

        final ModelAndView mv = new ModelAndView("/accordorder/apiorderrs2");
        mv.addObject("resultObject", "rs2");
        mv.addObject("numTtn", numTtn);
        mv.addObject("aHead", aHead);
        mv.addObject("oListRec", dbAccordOrderRs2List);
        return mv;
    }

    @RequestMapping(value = "/accordOrdRs2dDel", method = RequestMethod.POST)
    public ModelAndView accordOrRs2dDel(
            @RequestParam(value = "numTtn",  required = false) int numTtn,
            @RequestParam(value = "mnTov",  required = false) int nMnTov
            ) {
        System.out.printf("CTRL - numTtn=%d nMnTov=%d\n", numTtn, nMnTov);
        boolean result = false;
        if (dbAccordOrderdService.queryOrderRs2Delete(numTtn, nMnTov) > 0) {
            result = true;
            return accordOrdViewRs2(numTtn);
        }
        return new ModelAndView("/accordorder/apiorder01", "resultObject", result);
    }

    @RequestMapping(value = "/accordOrdRs2Update", method = RequestMethod.POST)
    public ModelAndView accordOrdRs2Update(
            @RequestParam(value = "numTtn",  required = false) int numTtn,
            @RequestParam(value = "mnTov",  required = false) int nMnTov,
            @RequestParam(value = "kvp",  required = false) double nKvp,
            @RequestParam(value = "zen",  required = false) double nZen,
            @RequestParam(value = "nat",  required = false) String cNat
    ) {
        System.out.printf("CTRL - numTtn=%d nMnTov=%d cNat=%s nKvp=%.2f nZen=%.2f\n",
                numTtn, nMnTov, cNat , nKvp, nZen ); //

        DBAccordOrderRs2 oRs2 = new DBAccordOrderRs2(numTtn, nMnTov, cNat , nKvp, nZen);

        final ModelAndView mv = new ModelAndView("/accordorder/updaters2");
        mv.addObject("oRs2", oRs2);
        /*mv.addObject("numTtn", numTtn);*/
        /*mv.addObject("mnTov", nMnTov);
        mv.addObject("nat", cNat);
        mv.addObject("kvp", nKvp);
        mv.addObject("zen", nZen);*/
        mv.addObject("resultObject", "UpdateRs2");
        return mv;
    }
    @RequestMapping(value = "/accordOrdRs2Save", method = RequestMethod.POST)
    public ModelAndView accordOrdRs2Save(
            @RequestParam(value = "numTtn",  required = false) int numTtn,
            @RequestParam(value = "mnTov",  required = false) int nMnTov,
            @RequestParam(value = "kvp",  required = false) double nKvp,
            @RequestParam(value = "zen",  required = false) double nZen,
            @RequestParam(value = "nat",  required = false) String cNat
    ) {
        System.out.printf("CTRL_SaveRs2 - numTtn=%d nMnTov=%d cNat=%s nKvp=%.2f nZen=%.2f\n",
                numTtn , nMnTov, cNat , nKvp, nZen ); //

        boolean result = false;
        if (dbAccordDAORs2.update(new DBAccordOrderRs2(numTtn, nMnTov, cNat , nKvp, nZen)) > 0) {
            result = true;
            return accordOrdViewRs2(numTtn);
        }
        return new ModelAndView("/accordorder/updaters2", "resultObject", result);
    }
}
