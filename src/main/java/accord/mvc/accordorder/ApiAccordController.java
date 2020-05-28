package accord.mvc.accordorder;

import accord.mvc.model.*;
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
public class ApiAccordController {
    private static final Logger log = LoggerFactory.getLogger(ApiAccordController.class);
    private int numKtaRs1 = 364;
    @Autowired
    private JBDCAccordDAOKPl dbAccordDAOKPl;
    @Autowired
    private JBDCAccordDAOKGp dbAccordDAOKGp;
    @Autowired
    private JBDCAccordDAOTMesto dbAccordDAOTMesto;
    @Autowired
    private JBDCAccordDAORs1 dbAccordDAORs1;
    @Autowired
    private DBAccordOrderdService dbAccordOrderdService;

    @RequestMapping(value = "/accordOrdList", method = RequestMethod.GET)
    public ModelAndView accordOrdList(
            @RequestParam(value = "kta", defaultValue = "364", required = false) Integer numKta) {
        System.out.printf("numKta=%d\n", numKta);

        List<String> aHead = Arrays.asList("Ном.заказа", "Дата", "Сумма, грн", "Тогр.место", "Товар", "Изменить", "Удалить");

        List<DBAccordOrderRs1> dbAccordOrderRs1List = null;
        dbAccordOrderRs1List = dbAccordOrderdService.queryOrderAccordRs2InRs1JDBC2JSON(numKta); //JDBC

        boolean result = false;
        final ModelAndView mv = new ModelAndView("/accord/order/apiorder01");
        mv.addObject("resultObject", "rs1");
        mv.addObject("aHead", aHead);
        mv.addObject("oListRec", dbAccordOrderRs1List);
        return mv;
    }

    @RequestMapping(value = "/accordOrdAdd", method = RequestMethod.GET)
    public ModelAndView accordOrdAdd() {
        boolean result = false;
        return new ModelAndView("/accord/order/apiorder01", "resultObject", result);
    }

    @RequestMapping(value = "/accordOrdDel", method = RequestMethod.POST)
    public ModelAndView accordOrdDel(
            @RequestParam(value = "numTtn", required = false) int numTtn) {
        System.out.printf("CTRL accordOrdDel- numTtn=%d\n", numTtn);
        boolean result = false;
        if (dbAccordOrderdService.queryOrderRs1Delete(numTtn) > 0) {
            result = true;
        }
        return new ModelAndView("/accord/order/apiorder01", "resultObject", result);
    }

    @RequestMapping(value = "/accordOrdFilt", method = RequestMethod.GET)
    public ModelAndView accordOrdFilt() {
        boolean result = false;

        return new ModelAndView("/accord/order/apiorder01", "resultObject", result);
    }

    @RequestMapping(value = "/accordOrdUpdate", method = RequestMethod.POST)
    public ModelAndView accordOrdUpdate(
            @RequestParam(value = "numTtn", required = false) int numTtn) {

        System.out.printf("CTRL accordOrdUpdate- numTtn=%d\n", numTtn);

        DBAccordOrderRs1 oRs1 = dbAccordDAORs1.findByTtn(numTtn);
        DBAccordOrderTMesto oTMesto = dbAccordDAOTMesto.findByTMesto(oRs1.getTMesto());
        System.out.printf("  tMesto=%d" + oTMesto +"\n", oRs1.getTMesto());

        DBAccordOrderKPl oKPl = dbAccordDAOKPl.findByKPl(oTMesto.getkPl());
        System.out.printf("  kpl=%d" + oKPl +"\n", oTMesto.getkPl());

        DBAccordOrderKGp oKGp = dbAccordDAOKGp.findByKGp(oTMesto.getkGp());
        System.out.printf("  kgp=%d" + oKGp +"\n", oTMesto.getkGp());

        final ModelAndView mv = new ModelAndView("/accord/order/updaters1");
        mv.addObject("oKPl", oKPl);
        mv.addObject("oKGp", oKGp);
        mv.addObject("oTMesto", oTMesto);
        mv.addObject("oRs1", oRs1);
        return mv;
    }

    @RequestMapping(value = "/accordSeekKPl", method = RequestMethod.GET)
    public ModelAndView accordSeekKPl(
            @RequestParam(value = "numTtn", required = false) int numTtn,
            @RequestParam(value = "val", required = false) String name
    ) {

        System.out.printf("CTRL_accordSeekKPl numTtn=%d val=%s\n", numTtn, name);
        List<String> aHead =
                Arrays.asList("Код плат","Наименование плательщика", "Адрес", "Выбрать");

        // DBAccordOrderRs1 oRs1 = dbAccordDAORs1.findByTtn(numTtn);
        List<DBAccordOrderKPl> oListKPl = dbAccordDAOKPl.selectStartsWith(numKtaRs1, name);

        final ModelAndView mv = new ModelAndView("/accord/order/seekkpl");
        mv.addObject("aHead", aHead);
        mv.addObject("oListKPl", oListKPl);
        mv.addObject("numTtn", numTtn);
        mv.addObject("name", name);
        return mv;
    }

    @RequestMapping(value = "/accordOrdChoiceKPl", method = RequestMethod.POST)
    public ModelAndView accordOrdChoiceKPl(
            @RequestParam(value = "numTtn", required = false) int numTtn,
            @RequestParam(value = "numKPl", required = false) int numKPl
    ) {
        DBAccordOrderKPl oKPl;
        DBAccordOrderKGp oKGp;

        System.out.printf("CTRL accordOrdChoiceKPl- numTtn=%d numKPl=%d \n", numTtn, numKPl);

        DBAccordOrderRs1 oRs1 = dbAccordDAORs1.findByTtn(numTtn);
        DBAccordOrderTMesto oTMesto = dbAccordDAOTMesto.findByTMesto(oRs1.getTMesto());

        if (oTMesto.getkPl() != numKPl) { // сменился Плательщик
            oKPl =  dbAccordDAOKPl.findByKPl(numKPl);
            //получить список ГП для этого ТА и Пл-ка
            List<DBAccordOrderKGp> oListKGp = dbAccordDAOKGp.oListKGp4KPl(numKtaRs1, numKPl);
            //из списка выбрать первого из списка
            oKGp = oListKGp.get(0);

            System.out.printf("  !=kpl=%d " + oKPl +"\n", numKPl);
            System.out.printf("  !=kgp=%d " + oKGp +"\n", oKGp.getkGp());

        } else {
            oKPl =  dbAccordDAOKPl.findByKPl(oTMesto.getkPl());
            oKGp = dbAccordDAOKGp.findByKGp(oTMesto.getkGp());
            System.out.printf("  tMesto=%d " + oTMesto +"\n", oRs1.getTMesto());
            System.out.printf("  kpl=%d " + oKPl +"\n", oTMesto.getkPl());
            System.out.printf("  kgp=%d " + oKGp +"\n", oTMesto.getkGp());
        }
        final ModelAndView mv = new ModelAndView("/accord/order/updaters1");
        mv.addObject("oKPl", oKPl);
        mv.addObject("oKGp", oKGp);
        mv.addObject("oTMesto", oTMesto);
        mv.addObject("oRs1", oRs1);
        return mv;
    }

    @RequestMapping(value = "/accordSeekKGp", method = RequestMethod.GET)
    public ModelAndView accordSeekKGp(
            @RequestParam(value = "val", required = false) String name,
            @RequestParam(value = "numTtn", required = false) int numTtn,
            @RequestParam(value = "numKPl", required = false) int numKPl,
            @RequestParam(value = "cNameKPl", required = false) String cNameKPl
    ) {
        System.out.printf("CTRL_accordSeeKGp numTtn=%d val=%s numKPl=%d cNameKPl=%s \n", numTtn, name, numKPl, cNameKPl);

        List<String> aHead =
                Arrays.asList("Код грузпол","Наименование грузполучателя", "Адрес", "Выбрать");

        List<DBAccordOrderKGp> oListKGp;
        if (cNameKPl.isEmpty()) { //выбор ВСЕХ ГП для данного ТА
            oListKGp = dbAccordDAOKGp.selectStartsWith(numKtaRs1, name);
        } else { //выбор ГП для данного ТА и Плат=ка
            oListKGp = dbAccordDAOKGp.selectStartsWith(numKtaRs1, numKPl, name);
        }

        final ModelAndView mv = new ModelAndView("/accord/order/seekkgp");
        mv.addObject("aHead", aHead);
        mv.addObject("oListKGp", oListKGp);
        mv.addObject("numTtn", numTtn);
        mv.addObject("name", name);
        mv.addObject("cNameKPl", cNameKPl);
        return mv;
    }

    @RequestMapping(value = "/accordOrdChoiceKGp", method = RequestMethod.POST)
    public ModelAndView accordOrdChoiceKPl(
            @RequestParam(value = "numTtn", required = false) int numTtn,
            @RequestParam(value = "numKGp", required = false) int numKGp,
            @RequestParam(value = "cNameKPl", required = false) String cNameKPl
    ) {
        System.out.printf("CTRL accordOrdChoiceKGp- numTtn=%d numKGp=%d \n", numTtn, numKGp);

        DBAccordOrderKPl oKPl;
        DBAccordOrderKGp oKGp;
        DBAccordOrderRs1 oRs1 = dbAccordDAORs1.findByTtn(numTtn);
        DBAccordOrderTMesto oTMesto = dbAccordDAOTMesto.findByTMesto(oRs1.getTMesto());

        if (cNameKPl.isEmpty()) { // Пл-щик не выбран
            // выбрать ВСЕХ Пл-ков для ГП и ТА
            List<DBAccordOrderKPl> oListKPl = dbAccordDAOKPl.oListKGp4KGp(numKtaRs1, numKGp);
            //из списка выбрать первого из списка
            oKPl = oListKPl.get(0);
            oKGp = dbAccordDAOKGp.findByKGp(numKGp);
            System.out.printf("  !=kpl=%d " + oKPl +"\n", oKPl.getkKL());
            System.out.printf("  !=kgp=%d " + oKGp +"\n", numKGp);
        } else {
            oKPl =  dbAccordDAOKPl.findByKPl(oTMesto.getkPl());
            oKGp = dbAccordDAOKGp.findByKGp(numKGp);
            System.out.printf("  tMesto=%d " + oTMesto +"\n", oRs1.getTMesto());
            System.out.printf("  kpl=%d " + oKPl +"\n", oTMesto.getkPl());
            System.out.printf("  kgp=%d " + oKGp +"\n", oTMesto.getkGp());
        }

        final ModelAndView mv = new ModelAndView("/accord/order/updaters1");
        mv.addObject("oKPl", oKPl);
        mv.addObject("oKGp", oKGp);
        mv.addObject("oTMesto", oTMesto);
        mv.addObject("oRs1", oRs1);
        return mv;
    }

    @RequestMapping(value = "/accordOrdUpdateSave", method = RequestMethod.POST)
    public ModelAndView accordOrdUpdateSave(
            @RequestParam(value = "numTtn", required = false) int numTtn,
            @RequestParam(value = "numKPl", required = false) int numKPl,
            @RequestParam(value = "numKGp", required = false) int numKGp) {
        System.out.printf("CTRL accordOrdUpdateSave- numTtn=%d numKPl=%d numKGp=%d \n", numTtn, numKGp, numKPl);
        // получение Шапок ном ТТН (она ОДНА, но в списке)
        DBAccordOrderRs1 oRs1 = dbAccordDAORs1.findByTtn(numTtn);
        DBAccordOrderTMesto oTMesto = dbAccordDAOTMesto.findByTMesto4KplKGp(numKPl, numKGp);
        int numTMesto =  oTMesto.gettMesto();
        System.out.printf("  tMesto=%d " + oTMesto +"\n", numTMesto);
        oRs1.setTMesto(numTMesto);
        int cntRec = dbAccordDAORs1.update(oRs1);
        return accordOrdList(numKtaRs1);
    }
}
