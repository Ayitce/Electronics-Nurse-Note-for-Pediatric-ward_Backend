package enp.enp_backend.MedUtils;

import enp.enp_backend.entity.Triage;
import org.apache.tomcat.util.buf.StringUtils;
import org.mapstruct.ap.shaded.freemarker.template.utility.StringUtil;

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
                    if (triage.getSystolic_blood_pressure() < vitalSign[i][4] + (ageMonth / 12) * 2)
                        isAbnormalBP = true;
                } else {
                    if (triage.getSystolic_blood_pressure() < vitalSign[i][4])
                        isAbnormalBP = true;
                }

                if (triage.getHeartRate() > vitalSign[i][2] || isAbnormalBP)
                    abnormalText = "abnormal vital sign with uncompensated/hypotensive";
                else if (triage.getRespiratoryRate() > vitalSign[i][3] ||
                        triage.getTemperature() < vitalSign[i][5] ||
                        triage.getTemperature() > vitalSign[i][6])
                    abnormalText = "abnormal vital sign";
            }
        }
        return abnormalText;
    }

    public Boolean isNarrowPulse() {
        if (triage.getSystolic_blood_pressure() <= 20 ||
                (triage.getSystolic_blood_pressure() - triage.getDiastolic_blood_pressure()) < 25)
            return true;
        else
            return false;
    }

    public Boolean isWidePulse() {
        if (triage.getSystolic_blood_pressure() - triage.getDiastolic_blood_pressure() > 30)
            return true;
        else
            return false;
    }

    public Boolean checkSymptom() {
        if (triage.getIrritable() ||
                triage.getStupor_drownsiness() ||
                triage.getMotting_skin() ||
                triage.getWeak_pulse() ||
                triage.getBounding_pulse() ||
                triage.getCap_refill() ||
                triage.getFlash_cap())
            return true;
        else
            return false;
    }

    @Override
    public String getShockResult() throws ParseException {
        String resultText = "";
        if (checkSymptom() && getAbnormalVitalSign().equals("abnormal vital sign with uncompensated/hypotensive"))
            resultText += "Uncompensated shock / Hypotensive shock" + "\n";
        else if (checkSymptom() && getAbnormalVitalSign().equals("abnormal vital sign"))
            resultText += "Compensated shock" + "\n";

        if (!resultText.equals("")) {
            if (isNarrowPulse())
                resultText += "Narrow pulse pressure";
            else if (isWidePulse())
                resultText += "Wide pulse pressure";
        }
        return resultText;
    }
}
