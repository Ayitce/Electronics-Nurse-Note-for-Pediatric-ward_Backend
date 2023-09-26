package enp.enp_backend.MedCalculator;

import enp.enp_backend.MedUtils.*;
import enp.enp_backend.entity.Triage;
import org.springframework.security.core.parameters.P;

import java.text.ParseException;

public class MedCalculator implements IMedCalculator {

    Triage triage;
    MPEWBean mpewBean;
    Sepsis sepsis;
    Shock shock;
    Seizure seizure;
    Press press;
    public MedCalculator(Triage triage) {
        this.triage = triage;
        sepsis = new Sepsis(triage);
        shock = new Shock(triage);
        seizure = new Seizure(triage);
        press = new Press(triage);
        mpewBean = new MPEWBean(triage);
    }

    @Override
    public int getMPEWS() throws ParseException {
        return mpewBean.getTotalScore();
    }

    @Override
    public int getSeverity()  throws ParseException{
        return mpewBean.getSeverityScore();
    }
    @Override
    public String getSepsis() throws ParseException {
        return sepsis.getSepsisResult();
    }

    @Override
    public String getPress() throws ParseException {
        return press.getPressResult();
    }

    @Override
    public String getShock() throws ParseException {
        return shock.getShockResult();
    }

    @Override
    public String getSeisure() throws ParseException {
        return seizure.getSeizureResult();
    }
}
