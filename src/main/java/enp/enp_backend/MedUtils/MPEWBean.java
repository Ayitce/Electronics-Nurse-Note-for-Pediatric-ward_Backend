package enp.enp_backend.MedUtils;

import enp.enp_backend.entity.Consciousness;
import enp.enp_backend.entity.Triage;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

public class MPEWBean implements IMPEWS {
    SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH);
    private final Log logger = LogFactory.getLog(this.getClass());
    Triage triage;
   // VitalSign vitalSign;
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
            {4, 4, 4, 0, 0, 0, 1, 1, 2, 4,4},
            {4, 4, 4, 0, 0, 1, 1, 2, 4, 4,4},
            {4, 4, 4, 0, 1, 1, 2, 4, 4, 4,4},
            {4, 4, 4, 1, 2, 4, 4, 4, 4, 4,4},
            {4, 4, 4, 4, 4, 4, 4, 4, 4, 4,4},
    };

    public MPEWBean(Triage triage) {
        this.triage = triage;
    }
    public int getMonthsFromBirthday() throws ParseException {
        Date birth = formatter.parse(triage.getAdmit().getPatient().getDateOfBirth());
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
    public int getHeartRateScore() throws ParseException {
        int ageMonth = this.getMonthsFromBirthday();

        if (ageMonth <= 3) {
            return heartRate[0][Math.round(triage.getHeartRate() / 10)];
        } else if (ageMonth <= 12) {
            return heartRate[1][Math.round(triage.getHeartRate() / 10)];
        } else if (ageMonth <= 48) {
            return heartRate[2][Math.round(triage.getHeartRate() / 10)];
        } else if (ageMonth <= 144) {
            return heartRate[3][Math.round(triage.getHeartRate() / 10)];
        } else {
            return heartRate[4][Math.round(triage.getHeartRate() / 10)];
        }

    }

    @Override
    public int getRespiratoryRateScore() throws ParseException {
        int ageMonth = this.getMonthsFromBirthday();

        if (triage.getRespiratoryRate() < 30) {
            if (ageMonth <= 3) {
                return respiratoryRateLessThan30[0][triage.getRespiratoryRate()];
            } else if (ageMonth <= 12) {
                return respiratoryRateLessThan30[1][triage.getRespiratoryRate()];
            } else if (ageMonth <= 48) {
                return respiratoryRateLessThan30[2][triage.getRespiratoryRate()];
            } else if (ageMonth <= 144) {
                return respiratoryRateLessThan30[3][triage.getRespiratoryRate()];
            } else {
                return respiratoryRateLessThan30[4][triage.getRespiratoryRate()];
            }
        } else {
            if (ageMonth <= 3) {
                return respiratoryRateMoreThan30[0][Math.round(triage.getRespiratoryRate() / 10)];
            } else if (ageMonth <= 12) {
                return respiratoryRateMoreThan30[1][Math.round(triage.getRespiratoryRate() / 10)];
            } else if (ageMonth <= 48) {
                return respiratoryRateMoreThan30[2][Math.round(triage.getRespiratoryRate() / 10)];
            } else if (ageMonth <= 144) {
                return respiratoryRateMoreThan30[3][Math.round(triage.getRespiratoryRate() / 10)];
            } else {
                return respiratoryRateMoreThan30[4][Math.round(triage.getRespiratoryRate() / 10)];
            }
        }
    }

    @Override
    public int getTemperatureScore() {
        if (triage.getTemperature() < 34 || triage.getTemperature() >= 40) {
            return 4;
        } else if (triage.getTemperature() < 35 || triage.getTemperature() >= 39) {
            return 2;
        } else if (triage.getTemperature() < 36 || triage.getTemperature() >= 38) {
            return 1;
        } else {
            return 0;
        }

    }

    @Override
    public int getOxygenSaturationScore() {
        if (triage.getOxygenSaturation() < 85) {
            return 4;
        } else if (triage.getOxygenSaturation() < 90) {
            return 2;
        } else if (triage.getOxygenSaturation() < 95) {
            return 1;
        } else {
            return 0;
        }
    }

    @Override
    public int getOxygenTherapyScore() {
        if (triage.getOxygenTherapy() < 1) {
            return 0;
        } else if (triage.getOxygenTherapy() < 2) {
            return 1;
        } else if (triage.getOxygenTherapy() < 5) {
            return 2;
        } else {
            return 4;
        }
    }

    @Override
    public int getConsciousnessScore() {
        if (triage.getConsciousness() == Consciousness.A) {
            return 0;
        } else if (triage.getConsciousness() == Consciousness.V) {
            return 2;
        } else {
            return 4;
        }
    }

    @Override
    public int getTotalScore() throws ParseException {
        return this.getHeartRateScore() +
                this.getRespiratoryRateScore() +
                this.getTemperatureScore() +
                this.getOxygenSaturationScore() +
                this.getOxygenTherapyScore() +
                this.getConsciousnessScore();
    }

    @Override
    public int getSeverityScore() throws ParseException {
        int severity = 0;
        if(this.getHeartRateScore() > severity)
            severity = this.getHeartRateScore();
        if(this.getRespiratoryRateScore() > severity)
            severity = this.getRespiratoryRateScore();
        if(this.getTemperatureScore() > severity)
            severity = this.getTemperatureScore();
        if(this.getOxygenSaturationScore() > severity)
            severity = this.getOxygenSaturationScore();
        if(this.getOxygenTherapyScore() > severity)
            severity = this.getOxygenTherapyScore();
        if(this.getConsciousnessScore() > severity)
            severity = this.getConsciousnessScore();
        return severity;
    }

}
