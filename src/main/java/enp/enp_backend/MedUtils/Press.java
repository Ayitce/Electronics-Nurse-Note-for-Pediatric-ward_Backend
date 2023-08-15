package enp.enp_backend.MedUtils;

import enp.enp_backend.entity.Triage;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

public class Press implements IPress {
    SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH);

    Triage triage;
    int respiratory[][] = {
            {0, 12, 60},
            {12, 36, 40},
            {36, 156, 30},
            {156, 999, 20}
    };

    public Press(Triage triage) {
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

    public Boolean isAbnormalRes() throws ParseException {
        int ageMonth = getMonthsFromBirthday();
        for (int i = 0; i < respiratory.length; i++) {
            if (ageMonth >= respiratory[i][0] && ageMonth < respiratory[i][1]) {
                if (triage.getVitalSign().getRespiratoryRate() > respiratory[i][2])
                    return true;
            }
        }
        return false;
    }

    public int countScore() throws ParseException {
        int score = 0;
        if (isAbnormalRes())
            score += 1;
        if (triage.getPhysicalExam().getWheezing() > 0)
            score += 1;
        if (triage.getInitialImpression().getNasal_flaring() ||
                triage.getInitialImpression().getScalene_muscle() ||
                triage.getInitialImpression().getSubcostral_retraction() ||
                triage.getInitialImpression().getSupersternal_retraction())
            score += 1;
        if (triage.getVitalSign().getOxygenSaturation() < 95)
            score += 1;
        if (triage.getInitialImpression().getDehedration() || triage.getAdd().getPoor_feeding())
            score += 1;

        return score;
    }

    public int getPramScore() {
        int score = 0;
        score += triage.getPhysicalExam().getAirEntry() + triage.getPhysicalExam().getWheezing();
        if (triage.getInitialImpression().getSupersternal_retraction())
            score += 2;
        if (triage.getInitialImpression().getScalene_muscle())
            score += 2;
        if (triage.getVitalSign().getOxygenSaturation() >= 92 && triage.getVitalSign().getOxygenSaturation() <= 94)
            score += 1;
        else if (triage.getVitalSign().getOxygenSaturation() < 92)
            score += 2;
        return score;
    }

    @Override
    public String getPressResult() throws ParseException {
        String resultText = "";
        resultText += "PRAM Score = " + getPramScore() + "\n";
        resultText += "Suspected Score = " + countScore() + "\n";
        if (countScore() <= 1)
            resultText += "Mild respiratory distress";
        else if (countScore() <= 3)
            resultText += "Moderate respiratory distress";
        else if (countScore() <= 5 || triage.getInitialImpression().getGrunting())
            resultText += "Severe respiratory distress / Possible respiratory failure";
        return resultText;
    }
}
