package enp.enp_backend.MedUtils;

import enp.enp_backend.entity.Consciousness;
import enp.enp_backend.entity.Triage;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

public class Seizure implements ISeizure {

    SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH);

    Triage triage;

    public Seizure(Triage triage) {
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

    public int countScore() throws ParseException {
        int score = 0;
        if (triage.getPhysicalExam().getConsciousness() == Consciousness.P ||
                triage.getPhysicalExam().getConsciousness() == Consciousness.U)
            score += 1;
        if (triage.getAdd().getGeneralize_seizure())
            score += 1;
        if (triage.getAdd().getComoatose_stage_seizure() || triage.getAdd().getGCS() < 8)
            score += 2;
        if (triage.getAdd().getHistory_of_seizure())
            score += 2;
        if (getMonthsFromBirthday() < 24)
            score += 2;
        return score;
    }

    @Override
    public String getSeizureResult() throws ParseException {
        String resultText = "";
        if (countScore() > 0 && countScore() < 3)
            resultText += "Suspected seizure (score = " + countScore() + ")";
        else if (countScore() >= 3)
            resultText += "Possible severe seizure (score = " + countScore() + ")";
        return resultText;
    }
}
