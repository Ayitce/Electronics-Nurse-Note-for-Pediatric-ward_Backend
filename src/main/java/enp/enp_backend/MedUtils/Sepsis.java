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
                if (triage.getVitalSign().getHeartRate() > vitalSign[i][2])
                    abnormalText += "ABNORMAL Heart Rate = " + triage.getVitalSign().getHeartRate() + "\n";
                if (triage.getVitalSign().getRespiratoryRate() > vitalSign[i][3])
                    abnormalText += "ABNORMAL Respiratory Rate = " + triage.getVitalSign().getRespiratoryRate() + "\n";
                if (i >= 3 && i <= 6) {
                    if (triage.getVitalSign().getSystolic_blood_pressure() < vitalSign[i][4] + (ageMonth / 12) * 2)
                        abnormalText += "ABNORMAL Systolic Blood Pressure = " + triage.getVitalSign().getSystolic_blood_pressure() + "\n";
                } else {
                    if (triage.getVitalSign().getSystolic_blood_pressure() < vitalSign[i][4])
                        abnormalText += "ABNORMAL Systolic Blood Pressure = " + triage.getVitalSign().getSystolic_blood_pressure() + "\n";
                }
                if (triage.getVitalSign().getTemperature() < vitalSign[i][5] || triage.getVitalSign().getTemperature() > vitalSign[i][6])
                    abnormalText += "ABNORMAL Body Temp = " + triage.getVitalSign().getTemperature() + "\n";

            }
        }
        return abnormalText;
    }

    private Boolean isCheckedRiskFactor() {
        if (triage.getRiskFactor().getOrgantranplantation())
            return true;
        else if (triage.getRiskFactor().getPrimary_immune_defencing())
            return true;
        else if (triage.getRiskFactor().getPostSplenectomy_asplenia())
            return true;
        else if (triage.getRiskFactor().getMalignancy())
            return true;
        else if (triage.getRiskFactor().getBedRidden_cerebralPulsy())
            return true;
        else if (triage.getRiskFactor().getCenter_iv_catheter())
            return true;
        else
            return false;
    }

    private int countSymptom() throws ParseException {
        int count = 0;
        if (getAbnormalVitalSign() != "")
            count += 1;
        if (triage.getPhysicalExam().getWeak_pulse())
            count += 1;
        if (triage.getPhysicalExam().getBounding_pulse())
            count += 1;
        if (triage.getPhysicalExam().getCap_refill())
            count += 1;
        if (triage.getPhysicalExam().getFlash_cap())
            count += 1;
        if (triage.getInitialImpression().getMotting_skin())
            count += 1;
        if (triage.getInitialImpression().getPetichea())
            count += 1;
        if (triage.getInitialImpression().getIrritable())
            count += 1;
        if (triage.getInitialImpression().getStupor_drownsiness())
            count += 1;

        return count;
    }

    @Override
    public Boolean isSepticShock() throws ParseException {
        if (triage.getRiskFactor().getSuspected_infection() && isCheckedRiskFactor() && countSymptom() >= 2)
            return true;
        else
            return false;
    }

    @Override
    public Boolean isSepsis() throws ParseException {
        if (triage.getRiskFactor().getSuspected_infection() && !isCheckedRiskFactor() && countSymptom() >= 3)
            return true;
        else
            return false;
    }

    @Override
    public String getSepsisResult() throws ParseException {
        String result = "";
        if(isSepticShock())
            result+= "Suspected Septic Shock "+ "\n" + "Risk Factors >= 1" + "\n" + getAbnormalVitalSign();
        else if(isSepsis())
            result+= "Suspected Sepsis "+ "\n" + "Risk Factors >= 1" + "\n" + getAbnormalVitalSign();
        return result;
    }
}
