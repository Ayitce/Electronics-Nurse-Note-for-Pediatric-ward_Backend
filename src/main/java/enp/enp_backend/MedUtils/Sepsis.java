package enp.enp_backend.MedUtils;

import enp.enp_backend.entity.Triage;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

public class Sepsis implements ISepsis {
    SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH);

    Triage triage;
    double vitalSign[][] = {
            {0, 1, 205, 60, 60, 36, 38},
            {1, 3, 205, 60, 70, 36, 38},
            {3, 12, 190, 60, 70, 36, 38.5},
            {12, 24, 190, 40, 70, 36, 38.5},
            {24, 48, 140, 40, 70, 36, 38.5},
            {48, 72, 140, 34, 70, 36, 38.5},
            {72, 120, 140, 30, 70, 36, 38.5},
            {120, 156, 100, 30, 90, 36, 38.5},
            {156, 999, 100, 16, 90, 36, 38.5}
    };

    public Sepsis(Triage triage) {
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
    public String getAbnormalVitalSign() throws ParseException {
        int ageMonth = this.getMonthsFromBirthday();
        String abnormalText = "";
        for (int i = 0; i < vitalSign.length; i++) {
            if (ageMonth >= vitalSign[i][0] && ageMonth < vitalSign[i][1]) {
                if (triage.getHeartRate() > vitalSign[i][2])
                    abnormalText += "ABNORMAL Heart Rate = " + triage.getHeartRate() + "\n";
                if (triage.getRespiratoryRate() > vitalSign[i][3])
                    abnormalText += "ABNORMAL Respiratory Rate = " + triage.getRespiratoryRate() + "\n";
                if (i >= 3 && i <= 6) {
                    if (triage.getSystolic_blood_pressure() < vitalSign[i][4] + (ageMonth / 12) * 2)
                        abnormalText += "ABNORMAL Systolic Blood Pressure = " + triage.getSystolic_blood_pressure() + "\n";
                } else {
                    if (triage.getSystolic_blood_pressure() < vitalSign[i][4])
                        abnormalText += "ABNORMAL Systolic Blood Pressure = " + triage.getSystolic_blood_pressure() + "\n";
                }
                if (triage.getTemperature() < vitalSign[i][5] || triage.getTemperature() > vitalSign[i][6])
                    abnormalText += "ABNORMAL Body Temp = " + triage.getTemperature() + "\n";

            }
        }
        return abnormalText;
    }

    private Boolean isCheckedRiskFactor() {
        if (triage.getOrgantranplantation())
            return true;
        else if (triage.getPrimary_immune_defencing())
            return true;
        else if (triage.getPostSplenectomy_asplenia())
            return true;
        else if (triage.getMalignancy())
            return true;
        else if (triage.getBedRidden_cerebralPulsy())
            return true;
        else if (triage.getCenter_iv_catheter())
            return true;
        else
            return false;
    }

    private int countSymptom() throws ParseException {
        int count = 0;
        if (getAbnormalVitalSign() != "")
            count += 1;
        if (triage.getWeak_pulse())
            count += 1;
        if (triage.getBounding_pulse())
            count += 1;
        if (triage.getCap_refill())
            count += 1;
        if (triage.getFlash_cap())
            count += 1;
        if (triage.getMotting_skin())
            count += 1;
        if (triage.getPetichea())
            count += 1;
        if (triage.getIrritable())
            count += 1;
        if (triage.getStupor_drownsiness())
            count += 1;

        return count;
    }

    @Override
    public Boolean isSepticShock() throws ParseException {
        if (triage.getSuspected_infection() && isCheckedRiskFactor() && countSymptom() >= 2)
            return true;
        else
            return false;
    }

    @Override
    public Boolean isSepsis() throws ParseException {
        if (triage.getSuspected_infection() && !isCheckedRiskFactor() && countSymptom() >= 3)
            return true;
        else
            return false;
    }

    @Override
    public String getSepsisResult() throws ParseException {
        String result = "";
        if(isSepticShock())
            result+= "Suspected Septic Shock "+ "<br/>" + "Risk Factors >= 1" + "<br/>" + getAbnormalVitalSign();
        else if(isSepsis())
            result+= "Suspected Sepsis "+ "<br/>" + "Risk Factors >= 1" + "<br/>" + getAbnormalVitalSign();
        return result;
    }
}
