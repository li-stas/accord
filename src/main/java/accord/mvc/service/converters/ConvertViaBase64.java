package accord.mvc.service.converters;

import accord.mvc.accordorder.ApiAccordController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.Base64;

/**
 * https://sprosi.pro/questions/1011533/peredacha-obyekta-so-stranitsyi-jsp-obratno-v-servlet
 */
@Service
public class ConvertViaBase64 {
    private static final Logger log = LoggerFactory.getLogger(ApiAccordController.class);
    public Object oStrDeCode64( String base64String) {
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

    public String oObjCode64(Object oRs1) {
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
    }
}
