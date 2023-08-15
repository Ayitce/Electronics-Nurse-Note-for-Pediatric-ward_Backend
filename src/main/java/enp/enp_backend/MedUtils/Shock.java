package enp.enp_backend.MedUtils;

import enp.enp_backend.entity.Triage;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

public class Shock implements IShock {
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

    public Shock(Triage triage) {
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

    public String getAbnormalVitalSign() throws ParseException {
        int ageMonth = this.getMonthsFromBirthday();
        String abnormalText = "";
        for (int i = 0; i < vitalSign.length; i++) {
            if (ageMonth >= vitalSign[i][0] && ageMonth < vitalSign[i][1]) {
                Boolean isAbnormalBP = false;
                if (i >= 3 && i <= 6) {
                    if (triage.getVitalSign().getSystolic_blood_pressure() < vitalSign[i][4] + (ageMonth / 12) * 2)
                        isAbnormalBP = true;
                } else {
                    if (triage.getVitalSign().getSystolic_blood_pressure() < vitalSign[i][4])
                        isAbnormalBP = true;
                }

                if (triage.getVitalSign().getHeartRate() > vitalSign[i][2] || isAbnormalBP)
                    abnormalText = "abnormal vital sign with uncompensated/hypotensive";
                else if (triage.getVitalSign().getRespiratoryRate() > vitalSign[i][3] ||
                        triage.getVitalSign().getTemperature() < vitalSign[i][5] ||
                        triage.getVitalSign().getTemperature() > vitalSign[i][6])
                    abnormalText = "abnormal vital sign";
            }
        }
        return abnormalText;
    }

    public Boolean isNarrowPulse() {
        if (triage.getVitalSign().getSystolic_blood_pressure() <= 20 ||
                (triage.getVitalSign().getSystolic_blood_pressure() - triage.getVitalSign().getDiastolic_blood_pressure()) < 25)
            return true;
        else
            return false;
    }

    public Boolean isWidePulse() {
        if (triage.getVitalSign().getSystolic_blood_pressure() - triage.getVitalSign().getDiastolic_blood_pressure() > 30)
            return true;
        else
            return false;
    }

    public Boolean checkSymptom() {
        if (triage.getInitialImpression().getIrritable() ||
                triage.getInitialImpression().getStupor_drownsiness() ||
                triage.getInitialImpression().getMotting_skin() ||
                triage.getPhysicalExam().getWeak_pulse() ||
                triage.getPhysicalExam().getBounding_pulse() ||
                triage.getPhysicalExam().getCap_refill() ||
                triage.getPhysicalExam().getFlash_cap())
            return true;
        else
            return false;
    }

    @Override
    public String getShockResult() throws ParseException {
        String resultText = "";
        if (checkSymptom() && getAbnormalVitalSign() == "abnormal vital sign with uncompensated/hypotensive")
            resultText += "Uncompensated shock / Hypotensive shock" + "\n";
        else if (checkSymptom() && getAbnormalVitalSign() == "abnormal vital sign")
            resultText += "Compensated shock" + "\n";

        if (resultText != "") {
            if (isNarrowPulse())
                resultText += "Narrow pulse pressure";
            else if (isWidePulse())
                resultText += "Wide pulse pressure";
        }
        return resultText;
    }
}
