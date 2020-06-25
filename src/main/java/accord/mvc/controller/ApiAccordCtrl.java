package accord.mvc.controller;

import accord.mvc.model.DBAccordOrderKGp;
import accord.mvc.model.DBAccordOrderKPl;
import accord.mvc.model.DBAccordOrderRs1;
import accord.mvc.model.DBAccordOrderTMesto;
import accord.mvc.service.converters.ConvertViaBase64;
import accord.mvc.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@Controller
public class ApiAccordCtrl {
    private static final Logger log = LoggerFactory.getLogger(ApiAccordCtrl.class);

    @Autowired
    private AuthenticationFacade authenticationFacade;
    @Autowired
    private ConvertViaBase64 convertViaBase64;
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

    private int numKtaRs1; // = 364;

    @RequestMapping(value = "/accordOrdList", method = RequestMethod.GET)
    public ModelAndView accordOrdList() {
        /*        @RequestParam(value = "kta", defaultValue = "364", required = false) Integer numKta*/
        numKtaRs1 =  authenticationFacade.getNumKta();
        System.out.printf("Call /accordOrdList numKta=%d\n", numKtaRs1);

       /* Authentication authentication = authenticationFacade.getAuthentication();
        System.out.printf(" username=%s numKta=%d\n", authentication.getName(), authenticationFacade.getNumKta());
        System.out.printf(" authentication=%s\n", authentication.toString());*/

        List<String> aHead = Arrays.asList("Ном.заказа", "Дата", "Сумма, грн", "Тогр.место", "Товар", "Изменить", "Удалить");

        List<DBAccordOrderRs1> dbAccordOrderRs1List = null;
        dbAccordOrderRs1List = dbAccordOrderdService.queryOrderAccordRs2InRs1JDBC2JSON(numKtaRs1); //JDBC

        boolean result = false;
        final ModelAndView mv = new ModelAndView("/accord/order/apiorder01");
        mv.addObject("resultObject", "rs1");
        mv.addObject("aHead", aHead);
        mv.addObject("oListRec", dbAccordOrderRs1List);
        return mv;
    }

    @RequestMapping(value = "/accordOrdAdd", method = RequestMethod.GET)
    public ModelAndView accordOrdAdd(
            @RequestParam(value = "numKPl", required = false) int numKPl,
            @RequestParam(value = "numKGp", required = false) int numKGp)   {

        DBAccordOrderRs1 oRs1 = new DBAccordOrderRs1();
        oRs1.setDvp(LocalDateTime.now());
        oRs1.setKta(numKtaRs1);

        DBAccordOrderTMesto oTMesto = new DBAccordOrderTMesto();
        DBAccordOrderKPl oKPl = new DBAccordOrderKPl();

        if (numKPl != 0) {
            oKPl = dbAccordDAOKPl.findByKPl(numKPl);
            System.out.printf("  !=kpl=%d " + oKPl +"\n", numKPl);
        }
        DBAccordOrderKGp oKGp = new DBAccordOrderKGp();
        if (numKGp != 0) {
            oKGp = dbAccordDAOKGp.findByKGp(numKGp);
            System.out.printf("  !=kgp=%d " + oKGp +"\n", numKGp);
        }

        return getModelAndViewUpdateRs1(oRs1, oTMesto, oKPl, oKGp);
    }

    @RequestMapping(value = "/accordOrdDel", method = RequestMethod.POST)
    public ModelAndView accordOrdDel(
            @RequestParam(value = "numTtn", required = false) int numTtn) {
        System.out.printf("CTRL accordOrdDel- numTtn=%d\n", numTtn);
        boolean result = false;
        if (dbAccordDAORs1.delete(numTtn) > 0) {
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

        return getModelAndViewUpdateRs1(oRs1, oTMesto, oKPl, oKGp);
    }


    @RequestMapping(value = "/accordSeekKPl", method = RequestMethod.GET)
    public ModelAndView accordSeekKPl(
            @RequestParam(value = "oRs1Code", required = false) String oRs1Code,
            @RequestParam(value = "numTtn", required = false) int numTtn,
            @RequestParam(value = "val", required = false) String name
    ) {

        System.out.printf("CTRL_accordSeekKPl numTtn=%d val=%s\n", numTtn, name);
        List<String> aHead =
                Arrays.asList("Код плат","Наименование плательщика", "Адрес", "Выбрать");

        List<DBAccordOrderKPl> oListKPl = dbAccordDAOKPl.selectStartsWith(numKtaRs1, name);

        final ModelAndView mv = new ModelAndView("/accord/order/seekkpl");
        mv.addObject("aHead", aHead);
        mv.addObject("oListKPl", oListKPl);
        mv.addObject("numTtn", numTtn);
        mv.addObject("name", name);
        mv.addObject("oRs1Code", oRs1Code);
        return mv;
    }

    @RequestMapping(value = "/accordOrdChoiceKPl", method = RequestMethod.POST)
    public ModelAndView accordOrdChoiceKPl(
            @RequestParam(value = "oRs1Code", required = false) String oRs1Code,
            @RequestParam(value = "numTtn", required = false) int numTtn,
            @RequestParam(value = "numKPl", required = false) int numKPl
    ) {
        DBAccordOrderRs1 oRs1;
        DBAccordOrderTMesto oTMesto;
        DBAccordOrderKPl oKPl;
        DBAccordOrderKGp oKGp;

        System.out.printf("CTRL accordOrdChoiceKPl- numTtn=%d numKPl=%d \n", numTtn, numKPl);
        System.out.printf(" oRs1Code- %s \n", oRs1Code);

        if (numTtn == 0) {
            oRs1 = (DBAccordOrderRs1) convertViaBase64.oStrDeCode64(oRs1Code);
            oKPl =  dbAccordDAOKPl.findByKPl(numKPl);
            //получить список ГП для этого ТА и Пл-ка
            List<DBAccordOrderKGp> oListKGp = dbAccordDAOKGp.oListKGp4KPl(numKtaRs1, numKPl);
            //из списка выбрать первого из списка
            oKGp = oListKGp.get(0);

            oTMesto = dbAccordDAOTMesto.findByTMesto4KplKGp(numKPl, oKGp.getkGp());
            oRs1.setTMesto(oTMesto.gettMesto());

        } else {
            oRs1 = dbAccordDAORs1.findByTtn(numTtn);
            oTMesto = dbAccordDAOTMesto.findByTMesto(oRs1.getTMesto());

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
        }

        return getModelAndViewUpdateRs1(oRs1, oTMesto, oKPl, oKGp);
    }

    @RequestMapping(value = "/accordSeekKGp", method = RequestMethod.GET)
    public ModelAndView accordSeekKGp(
            @RequestParam(value = "oRs1Code", required = false) String oRs1Code,
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
        mv.addObject("oRs1Code", oRs1Code);
        return mv;
    }

    @RequestMapping(value = "/accordOrdChoiceKGp", method = RequestMethod.POST)
    public ModelAndView accordOrdChoiceKGp(
            @RequestParam(value = "oRs1Code", required = false) String oRs1Code,
            @RequestParam(value = "numTtn", required = false) int numTtn,
            @RequestParam(value = "numKGp", required = false) int numKGp,
            @RequestParam(value = "cNameKPl", required = false) String cNameKPl
    ) {
        System.out.printf("CTRL accordOrdChoiceKGp- numTtn=%d numKGp=%d \n", numTtn, numKGp);

        DBAccordOrderKPl oKPl;
        DBAccordOrderKGp oKGp;
        DBAccordOrderRs1 oRs1;
        DBAccordOrderTMesto oTMesto;

        if (numTtn == 0) {
            oRs1 = (DBAccordOrderRs1) convertViaBase64.oStrDeCode64(oRs1Code);
            if (cNameKPl.isEmpty()) {
                // выбрать ВСЕХ Пл-ков для ГП и ТА
                List<DBAccordOrderKPl> oListKPl = dbAccordDAOKPl.oListKGp4KGp(numKtaRs1, numKGp);
                //из списка выбрать первого из списка
                oKPl = oListKPl.get(0);

                oKGp = dbAccordDAOKGp.findByKGp(numKGp);
                oTMesto = dbAccordDAOTMesto.findByTMesto4KplKGp(oKPl.getkKL(), numKGp);

                oRs1.setTMesto(oTMesto.gettMesto());
            } else {
                oTMesto = dbAccordDAOTMesto.findByTMesto(oRs1.getTMesto());
                oKPl =  dbAccordDAOKPl.findByKPl(oTMesto.getkPl());
                oKGp = dbAccordDAOKGp.findByKGp(numKGp);
            }
        } else {
            oRs1 = dbAccordDAORs1.findByTtn(numTtn);
            oTMesto = dbAccordDAOTMesto.findByTMesto(oRs1.getTMesto());

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
        }


        return getModelAndViewUpdateRs1(oRs1, oTMesto, oKPl, oKGp);
    }

    @RequestMapping(value = "/accordOrdUpdateSave", method = RequestMethod.POST)
    public ModelAndView accordOrdUpdateSave(
            @RequestParam(value = "oRs1Code", required = false) String oRs1Code,
            @RequestParam(value = "numTtn", required = false) int numTtn,
            @RequestParam(value = "numKPl", required = false) int numKPl,
            @RequestParam(value = "numKGp", required = false) int numKGp) {
        System.out.printf("CTRL accordOrdUpdateSave- numTtn=%d numKPl=%d numKGp=%d \n", numTtn, numKGp, numKPl);


        int numTMesto = 0;
        if (numKPl != 0 && numKGp !=0) {
            DBAccordOrderTMesto oTMesto = dbAccordDAOTMesto.findByTMesto4KplKGp(numKPl, numKGp);
            numTMesto = oTMesto.gettMesto();
            System.out.printf("  tMesto=%d " + oTMesto +"\n", numTMesto);
        } else {
            // станисца обновления реквизитов
            accordOrdAdd(numKPl, numKGp);
        }

        int cntRec = 0;
        DBAccordOrderRs1 oRs1;
        if (numTtn == 0) {
            oRs1 = (DBAccordOrderRs1) convertViaBase64.oStrDeCode64(oRs1Code);
            oRs1.setTMesto(numTMesto);

            System.out.println("oRs1 = " + oRs1);
            cntRec = dbAccordDAORs1.save(oRs1);
            System.out.println("cntRec = " + cntRec);
        }
        else {
            // получение Шапок ном ТТН (она ОДНА, но в списке)
            oRs1 = dbAccordDAORs1.findByTtn(numTtn);
            oRs1.setTMesto(numTMesto);
            cntRec = dbAccordDAORs1.update(oRs1);
        }
        // список Заявок ТА
        //return accordOrdList(numKtaRs1);
        return accordOrdList();
    }

  /*  private Object oStrDeCode64( String base64String) {
        final byte[] objToBytes = Base64.getDecoder().decode(base64String);
        ByteArrayInputStream bais = new ByteArrayInputStream(objToBytes);
        Object oObj = null;
        try {
            ObjectInputStream ois = new ObjectInputStream(bais);
            oObj = ois.readObject();
        } catch (IOException e) {
            log.error(e.getMessage() + " IOException" + e);
        } catch (ClassNotFoundException e) {
            log.error(e.getMessage() + " ClassNotFoundException" + e);
        }
        return oObj;
    }

    private String oObjCode64(Object oRs1) {
        final ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            final ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(oRs1);
            oos.flush();
        } catch (IOException e) {
            log.error(e.getMessage() + " IOException", e);
        }
        final String result = new String(Base64.getEncoder().encode(baos.toByteArray()));
        return result;
    }*/

    private ModelAndView getModelAndViewUpdateRs1(DBAccordOrderRs1 oRs1, DBAccordOrderTMesto oTMesto, DBAccordOrderKPl oKPl, DBAccordOrderKGp oKGp) {
        final ModelAndView mv = new ModelAndView("/accord/order/updaters1");
        mv.addObject("oKPl", oKPl);
        mv.addObject("oKGp", oKGp);
        mv.addObject("oTMesto", oTMesto);
        mv.addObject("oRs1", oRs1);
        mv.addObject("oRs1Code", convertViaBase64.oObjCode64(oRs1));
        return mv;
    }
}
