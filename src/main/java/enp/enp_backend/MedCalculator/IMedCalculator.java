package enp.enp_backend.MedCalculator;

import java.text.ParseException;

public interface IMedCalculator  {
    int getMPEWS() throws ParseException;
    String getSepsis() throws ParseException;
    String getPress() throws ParseException;
    String getShock() throws ParseException;
    String getSeisure() throws ParseException;
}