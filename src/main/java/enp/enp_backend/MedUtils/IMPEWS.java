package enp.enp_backend.MedUtils;

import java.text.ParseException;

public interface IMPEWS {
    int getHeartRateScore() throws ParseException;
    int getRespiratoryRateScore() throws ParseException;
    int getTemperatureScore();
    int getOxygenSaturationScore();
    int getOxygenTherapyScore();
    int getConsciousnessScore();
    int getTotalScore() throws ParseException;
}
