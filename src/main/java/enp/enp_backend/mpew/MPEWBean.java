package enp.enp_backend.mpew;

import enp.enp_backend.entity.Consciousness;
import enp.enp_backend.entity.VitalSign;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class MPEWBean implements IMPEWS {

    private final Log logger = LogFactory.getLog(this.getClass());
    VitalSign vitalSign;
    int heartRate[][] = {
            {4, 4, 4, 4, 4, 4, 4, 4, 2, 1, 1, 0, 0, 0, 0, 1, 1, 1, 2, 4, 4},
            {4, 4, 4, 4, 4, 4, 4, 2, 1, 1, 0, 0, 0, 0, 0, 1, 1, 2, 4, 4, 4},
            {4, 4, 4, 4, 4, 4, 2, 1, 1, 0, 0, 0, 1, 1, 1, 2, 2, 4, 4, 4, 4},
            {4, 4, 4, 4, 4, 2, 1, 0, 0, 0, 0, 1, 1, 2, 2, 4, 4, 4, 4, 4, 4},
            {4, 4, 4, 4, 2, 1, 0, 0, 0, 0, 1, 1, 2, 2, 4, 4, 4, 4, 4, 4, 4},
    };
    int respiratoryRateLessThan30[][] = {
            {4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 2, 2, 2, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
            {4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 2, 2, 2, 2, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0},
            {4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 2, 2, 2, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 2, 2, 2, 2, 2, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 2, 1, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 2},
    };

    //-------index 0-2 have no value------
    int respiratoryRateMoreThan30[][] = {
            {4, 4, 4, 0, 0, 0, 1, 1, 2, 4},
            {4, 4, 4, 0, 0, 1, 1, 2, 4, 4},
            {4, 4, 4, 0, 1, 1, 2, 4, 4, 4},
            {4, 4, 4, 1, 2, 4, 4, 4, 4, 4},
            {4, 4, 4, 4, 4, 4, 4, 4, 4, 4},
    };

    public MPEWBean(VitalSign vitalSign) {
        this.vitalSign = vitalSign;
    }
    public int getMonthsFromBirthday() {
        Date birth = vitalSign.getDateOfBirth();
        Date today = new Date();

        Calendar startCalendar = new GregorianCalendar();
        startCalendar.setTime(birth);
        Calendar endCalendar = new GregorianCalendar();
        endCalendar.setTime(today);

        int diffYear = endCalendar.get(Calendar.YEAR) - startCalendar.get(Calendar.YEAR);
        int ageMonth = diffYear * 12 + endCalendar.get(Calendar.MONTH) - startCalendar.get(Calendar.MONTH);
        return ageMonth;
    }
    @Override
    public int getHeartRateScore() {
        int ageMonth = this.getMonthsFromBirthday();

        if (ageMonth <= 3) {
            return heartRate[0][Math.round(vitalSign.getHeartRate() / 10)];
        } else if (ageMonth <= 12) {
            return heartRate[1][Math.round(vitalSign.getHeartRate() / 10)];
        } else if (ageMonth <= 48) {
            return heartRate[2][Math.round(vitalSign.getHeartRate() / 10)];
        } else if (ageMonth <= 144) {
            return heartRate[3][Math.round(vitalSign.getHeartRate() / 10)];
        } else {
            return heartRate[4][Math.round(vitalSign.getHeartRate() / 10)];
        }

    }

    @Override
    public int getRespiratoryRateScore() {
        int ageMonth = this.getMonthsFromBirthday();

        if (vitalSign.getRespiratoryRate() < 30) {
            if (ageMonth <= 3) {
                return respiratoryRateLessThan30[0][vitalSign.getRespiratoryRate()];
            } else if (ageMonth <= 12) {
                return respiratoryRateLessThan30[1][vitalSign.getRespiratoryRate()];
            } else if (ageMonth <= 48) {
                return respiratoryRateLessThan30[2][vitalSign.getRespiratoryRate()];
            } else if (ageMonth <= 144) {
                return respiratoryRateLessThan30[3][vitalSign.getRespiratoryRate()];
            } else {
                return respiratoryRateLessThan30[4][vitalSign.getRespiratoryRate()];
            }
        } else {
            if (ageMonth <= 3) {
                return respiratoryRateMoreThan30[0][Math.round(vitalSign.getRespiratoryRate() / 10)];
            } else if (ageMonth <= 12) {
                return respiratoryRateMoreThan30[1][Math.round(vitalSign.getRespiratoryRate() / 10)];
            } else if (ageMonth <= 48) {
                return respiratoryRateMoreThan30[2][Math.round(vitalSign.getRespiratoryRate() / 10)];
            } else if (ageMonth <= 144) {
                return respiratoryRateMoreThan30[3][Math.round(vitalSign.getRespiratoryRate() / 10)];
            } else {
                return respiratoryRateMoreThan30[4][Math.round(vitalSign.getRespiratoryRate() / 10)];
            }
        }
    }

    @Override
    public int getTemperatureScore() {
        if (vitalSign.getTemperature() < 34 || vitalSign.getTemperature() >= 40) {
            return 4;
        } else if (vitalSign.getTemperature() < 35 || vitalSign.getTemperature() >= 39) {
            return 2;
        } else if (vitalSign.getTemperature() < 36 || vitalSign.getTemperature() >= 38) {
            return 1;
        } else {
            return 0;
        }

    }

    @Override
    public int getOxygenSaturationScore() {
        if (vitalSign.getOxygenSaturation() < 85) {
            return 4;
        } else if (vitalSign.getOxygenSaturation() < 90) {
            return 2;
        } else if (vitalSign.getOxygenSaturation() < 95) {
            return 1;
        } else {
            return 0;
        }
    }

    @Override
    public int getOxygenTherapyScore() {
        if (vitalSign.getOxygenTherapy() < 1) {
            return 0;
        } else if (vitalSign.getOxygenTherapy() < 2) {
            return 1;
        } else if (vitalSign.getOxygenTherapy() < 5) {
            return 2;
        } else {
            return 4;
        }
    }

    @Override
    public int getConsciousnessScore() {
        if (vitalSign.getConsciousness() == Consciousness.A) {
            return 0;
        } else if (vitalSign.getConsciousness() == Consciousness.V) {
            return 2;
        } else {
            return 4;
        }
    }

    @Override
    public int getTotalScore() {
        return this.getHeartRateScore() +
                this.getRespiratoryRateScore() +
                this.getTemperatureScore() +
                this.getOxygenSaturationScore() +
                this.getOxygenTherapyScore() +
                this.getConsciousnessScore();
    }
}
