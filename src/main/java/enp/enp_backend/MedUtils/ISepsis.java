package enp.enp_backend.MedUtils;

import java.text.ParseException;

public interface ISepsis {
    String getAbnormalVitalSign() throws ParseException;
    Boolean isSepticShock() throws ParseException;
    Boolean isSepsis() throws ParseException;
    String getSepsisResult() throws ParseException;
}
