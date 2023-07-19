package enp.enp_backend.mpew;

public interface IMPEWS {
    int getHeartRateScore();
    int getRespiratoryRateScore();
    int getTemperatureScore();
    int getOxygenSaturationScore();
    int getOxygenTherapyScore();
    int getConsciousnessScore();
    int getTotalScore();
}
