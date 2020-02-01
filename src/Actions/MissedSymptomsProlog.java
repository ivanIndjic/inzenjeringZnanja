package Actions;

import com.ugos.jiprolog.engine.JIPEngine;
import com.ugos.jiprolog.engine.JIPQuery;
import com.ugos.jiprolog.engine.JIPTerm;
import com.ugos.jiprolog.engine.JIPVariable;

import java.util.ArrayList;

public class MissedSymptomsProlog {
    String missing = "";
    JIPQuery query2;

    public MissedSymptomsProlog(String bolest, ArrayList<String> symptoms) {
        JIPEngine engine = new JIPEngine();
        if (!bolest.equals("")) {
            engine.assertz(engine.getTermParser().parseTerm("bolest(" + bolest + ")."));
        }
        String symptomsString = "[";
        for (String symptom : symptoms) {
            symptomsString += symptom;
            symptomsString += ",";
        }
        symptomsString = symptomsString.substring(0, symptomsString.length() - 1);
        symptomsString += "]";
        if (!symptomsString.equals("]")) {
            if (bolest.equals("conjunctivitis")) {
                query2 = engine.openSynchronousQuery("findall(X,(member(X,[pain_in_eye,eye_redness,itchiness_of_eye,lacrimation,swollen_eye,white_discharge_from_eye,eye_burns_or_stings,cloudy_eye,foreign_body_sensation_in_eye]),not(member(X," + symptomsString + "))),D).");
            } else if (bolest.equals("cataract")) {
                query2 = engine.openSynchronousQuery("findall(X,(member(X,[diminished_vision,pain_in_eye,itchiness_of_eye,lacrimation,double_vision,blindness,spots_or_clouds_in_vision,eye_burns_or_stings,cloudy_eye,foreign_body_sensation_in_eye]),not(member(X," + symptomsString + "))),D).");
            } else if (bolest.equals("chronic_glaucoma")) {
                query2 = engine.openSynchronousQuery("findall(X,(member(X,[diminished_vision,double_vision,blindness,spots_or_clouds_in_vision,white_discharge_from_eye,eye_burns_or_stings,foreign_body_sensation_in_eye]),not(member(X," + symptomsString + "))),D).");
            } else if (bolest.equals("blepharitis")) {
                query2 = engine.openSynchronousQuery("findall(X,(member(X,[itchiness_of_eye,white_discharge_from_eye]),not(member(X," + symptomsString + "))),D).");
            } else if (bolest.equals("macular_degeneration")) {
                query2 = engine.openSynchronousQuery("findall(X,(member(X,[diminished_vision,double_vision,blindness,spots_or_clouds_in_vision,cloudy_eye,foreign_body_sensation_in_eye]),not(member(X," + symptomsString + "))),D).");
            } else if (bolest.equals("dry_eye_of_unknown_cause")) {
                query2 = engine.openSynchronousQuery("findall(X,(member(X,[diminished_vision,itchiness_of_eye,lacrimation,eye_burns_or_stings,foreign_body_sensation_in_eye]),not(member(X," + symptomsString + "))),D).");
            } else if (bolest.equals("eye_alignment_disorder")) {
                query2 = engine.openSynchronousQuery("findall(X,(member(X,[double_vision]),not(member(X," + symptomsString + "))),D).");
            } else if (bolest.equals("corneal_abrasion")) {
                query2 = engine.openSynchronousQuery("findall(X,(member(X,[pain_in_eye,eye_redness,eye_burns_or_stings,foreign_body_sensation_in_eye]),not(member(X," + symptomsString + "))),D).");
            } else if (bolest.equals("cornea_infection")) {
                query2 = engine.openSynchronousQuery("findall(X,(member(X,[pain_in_eye,eye_burns_or_stings,foreign_body_sensation_in_eye]),not(member(X," + symptomsString + "))),D).");
            } else if (bolest.equals("retinal_detachment")) {
                query2 = engine.openSynchronousQuery("findall(X,(member(X,[blindness,cloudy_eye]),not(member(X," + symptomsString + "))),D).");
            } else if (bolest.equals("optic_neuritis")) {
                query2 = engine.openSynchronousQuery("findall(X,(member(X,[blindness]),not(member(X," + symptomsString + "))),D).");
            } else if (bolest.equals("iridocyclitis")) {
                query2 = engine.openSynchronousQuery("findall(X,(member(X,[cloudy_eye]),not(member(X," + symptomsString + "))),D).");
            } else if (bolest.equals("subconjunctival_hemorrhage")) {
                query2 = engine.openSynchronousQuery("findall(X,(member(X,[pain_in_eye,eye_redness]),not(member(X," + symptomsString + "))),D).");
            } else if (bolest.equals("floaters")) {
                query2 = engine.openSynchronousQuery("findall(X,(member(X,[spots_or_clouds_in_vision]),not(member(X," + symptomsString + "))),D).");
            }

            JIPTerm solution;
            while ((solution = query2.nextSolution()) != null) {
                for (JIPVariable var : solution.getVariables()) {
                    missing = var.getValue() + "";
                }
            }
        }
    }

    public String[] getMissingSymptoms() {
        if (!missing.equals("")) {
            missing = missing.replaceAll("\\)", "");
            missing = missing.replaceAll("\\(", "");
            missing = missing.replaceAll("\\'", "");
            missing = missing.replaceAll("\\.", "");
            missing = missing.replaceAll("\\_", " ");
            if (missing.length() > 3) {
                missing = missing.substring(0, missing.length() - 3);
            }
        }
        String[] missingSymptoms = missing.split("\\,");
        return missingSymptoms;
    }
}
