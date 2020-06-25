package accord.mvc.controller;

import accord.mvc.model.DBAccordOrderRs2;
import accord.mvc.model.DBAccordOrderTov;
import accord.mvc.service.*;

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
public class ApiAccordCtrlRs2 {
    private static final Logger log = LoggerFactory.getLogger(ApiAccordCtrlRs2.class);

    @Autowired
    private  JBDCAccordDAORs2 dbAccordDAORs2;
    @Autowired
    private JBDCAccordDAOTov dbAccordDAOTov;

    @RequestMapping(value = "/accordOrdViewRs2", method = RequestMethod.POST)
    public ModelAndView accordOrdViewRs2(
            @RequestParam(value = "numTtn", required = false) int numTtn) {
        System.out.printf("CTRL accordOrdViewRs2 - numTtn=%d\n", numTtn);

        List<String> aHead =
                Arrays.asList("Код товара", "Наименование товара", "К-во", "Цена", "Сумма, грн", "Изменить", "Удалить");
        List<DBAccordOrderRs2> dbAccordOrderRs2List = dbAccordDAORs2.findRs2ByTtn(numTtn);

        final ModelAndView mv = new ModelAndView("/accord/order/apiorderrs2");
        mv.addObject("resultObject", "rs2");
        mv.addObject("numTtn", numTtn);
        mv.addObject("aHead", aHead);
        mv.addObject("oListRec", dbAccordOrderRs2List);
        return mv;
    }

    @RequestMapping(value = "/accordOrdViewRs2Back", method = RequestMethod.GET)
    public ModelAndView accordOrdViewRs2Back(
            @RequestParam(value = "numTtn", required = false) int numTtn) {
        return accordOrdViewRs2(numTtn);
    }

    @RequestMapping(value = "/accordOrdRs2dDel", method = RequestMethod.POST)
    public ModelAndView accordOrRs2dDel(
            @RequestParam(value = "numTtn", required = false) int numTtn,
            @RequestParam(value = "mnTov", required = false) int nMnTov
    ) {
        System.out.printf("CTRL accordOrdRs2dDel- numTtn=%d nMnTov=%d\n", numTtn, nMnTov);
        DBAccordOrderRs2 oRs2 = new DBAccordOrderRs2(numTtn, nMnTov, "cNat", 0, 0);
        boolean result = false;
        /*if (dbAccordOrderdService.queryOrderRs2Delete(numTtn, nMnTov) > 0) {*/
        if (dbAccordDAORs2.delete(oRs2) > 0) {
            result = true;
            return accordOrdViewRs2(numTtn);
        }
        return new ModelAndView("accord/order/apiorder01", "resultObject", result);
    }

    @RequestMapping(value = "/accordOrdRs2Update", method = RequestMethod.POST)
    public ModelAndView accordOrdRs2Update(
            @RequestParam(value = "numTtn", required = false) int numTtn,
            @RequestParam(value = "mnTov", required = false) int nMnTov,
            @RequestParam(value = "kvp", required = false) double nKvp,
            @RequestParam(value = "zen", required = false) double nZen,
            @RequestParam(value = "nat", required = false) String cNat
    ) {
        System.out.printf("CTRL accordOrdRs2Update- numTtn=%d nMnTov=%d cNat=%s nKvp=%.2f nZen=%.2f\n",
                numTtn, nMnTov, cNat, nKvp, nZen); //

        DBAccordOrderRs2 oRs2 = new DBAccordOrderRs2(numTtn, nMnTov, cNat, nKvp, nZen);

        final ModelAndView mv = new ModelAndView("/accord/order/updaters2");
        mv.addObject("oRs2", oRs2);
        mv.addObject("resultObject", "UpdateRs2");
        return mv;
    }


    @RequestMapping(value = "/accordOrdRs2Save", method = RequestMethod.POST)
    public ModelAndView accordOrdRs2Save(
            @RequestParam(value = "numTtn", required = false) int numTtn,
            @RequestParam(value = "mnTov", required = false) int nMnTov,
            @RequestParam(value = "kvp", required = false) double nKvp,
            @RequestParam(value = "zen", required = false) double nZen,
            @RequestParam(value = "nat", required = false) String cNat
    ) {
        System.out.printf("CTRL_SaveRs2 - numTtn=%d nMnTov=%d cNat=%s nKvp=%.2f nZen=%.2f\n",
                numTtn, nMnTov, cNat, nKvp, nZen); //

        boolean result = false;
        if (dbAccordDAORs2.update(new DBAccordOrderRs2(numTtn, nMnTov, cNat, nKvp, nZen)) > 0) {
            result = true;
            return accordOrdViewRs2(numTtn);
        }
        return new ModelAndView("/accord/order/updaters2", "resultObject", result);
    }


    @RequestMapping(value = "/accordOrdRs2Add", method = RequestMethod.GET)
    public ModelAndView accordOrdRs2Update(
            @RequestParam(value = "numTtn", required = false) Integer numTtn
    ) {
        System.out.printf("CTRL accordOrdRs2Add- numTtn=%d \n", numTtn);

        DBAccordOrderRs2 oRs2 = new DBAccordOrderRs2(numTtn, 0, "", 0, 0);

        final ModelAndView mv = new ModelAndView("/accord/order/addrs2");
        mv.addObject("oRs2", oRs2);
        mv.addObject("resultObject", "AddRs2");
        return mv;
    }

    @RequestMapping(value = "/accordOrdSeekTov", method = RequestMethod.GET)
    public ModelAndView accordOrdSeekTov(
            @RequestParam(value = "numTtn", required = false) int numTtn,
            @RequestParam(value = "val", required = false) String name
    ) {
        //int numTtn=0;
        System.out.printf("CTRL_accordOrdSeekTov numTtn=%d val=%s\n", numTtn, name);
        List<String> aHead =
                Arrays.asList("Код товара", "Наименование товара", "Ед.изм.", "К-во", "Цена", "Выбрать");

        List<DBAccordOrderTov> oListTov = dbAccordDAOTov.selectStartsWith(name);

        final ModelAndView mv = new ModelAndView("/accord/order/seektov");
        mv.addObject("aHead", aHead);
        mv.addObject("oListTov", oListTov);
        mv.addObject("numTtn", numTtn);
        mv.addObject("name", name);
        return mv;
    }

    @RequestMapping(value = "/accordOrdChoiceTov", method = RequestMethod.POST)
    public ModelAndView accordOrChoiceTov(
            @RequestParam(value = "numTtn", required = false) int numTtn,
            @RequestParam(value = "mnTov", required = false) int nMnTov,
            @RequestParam(value = "cenPr", required = false) double nZen,
            @RequestParam(value = "nat", required = false) String cNat
    ) {
        System.out.printf("CTRL accordOrdChoiceTov- numTtn=%d nMnTov=%d\n", numTtn, nMnTov);

        DBAccordOrderRs2 oRs2 = new DBAccordOrderRs2(numTtn, nMnTov, cNat, 0, nZen);

        final ModelAndView mv = new ModelAndView("/accord/order/addrs2");
        mv.addObject("oRs2", oRs2);
        mv.addObject("resultObject", "AddRs2");
        return mv;

    }

    @RequestMapping(value = "/accordOrdRs2AddSave", method = RequestMethod.POST)
    public ModelAndView accordOrdRs2AddSave(
            @RequestParam(value = "numTtn", required = false) int numTtn,
            @RequestParam(value = "mnTov", required = false) int nMnTov,
            @RequestParam(value = "kvp", required = false) double nKvp,
            @RequestParam(value = "zen", required = false) double nZen,
            @RequestParam(value = "nat", required = false) String cNat
    ) {
        System.out.printf("CTRL_Rs2AddSave - numTtn=%d nMnTov=%d cNat=%s nKvp=%.2f nZen=%.2f\n",
                numTtn, nMnTov, cNat, nKvp, nZen); //

        boolean result = false;

        if (dbAccordDAORs2.save(new DBAccordOrderRs2(numTtn, nMnTov, cNat, nKvp, nZen)) > 0) {
            result = true;
            return accordOrdViewRs2(numTtn);
        }
        return new ModelAndView("/accord/order/updaters2", "resultObject", result);
    }
}
