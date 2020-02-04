package Actions;

import com.ugos.jiprolog.engine.JIPEngine;
import com.ugos.jiprolog.engine.JIPQuery;
import com.ugos.jiprolog.engine.JIPTerm;
import com.ugos.jiprolog.engine.JIPVariable;

import java.util.ArrayList;

public class PreventiveExaminationsProlog {
    public PreventiveExaminationsProlog(String race, String sex, Integer years) {
        JIPEngine engine = new JIPEngine();
        engine.assertz(engine.getTermParser().parseTerm("years(" + years + ")."));
        engine.assertz(engine.getTermParser().parseTerm("sex(" + sex + ")."));
        engine.assertz(engine.getTermParser().parseTerm("race(" + race + ")."));
        //postavi brojeve za stringove i poredi jesu li ti brojevi
        engine.assertz(engine.getTermParser().parseTerm("bolest(cataract):- years(" + years + ")," + years + ">=60."));
        engine.assertz(engine.getTermParser().parseTerm("bolest(conjunctivitis):- years(" + years + ")," + years + "<15."));
        engine.assertz(engine.getTermParser().parseTerm("bolest(chronic_glaucoma):- years(" + years + ")," + years + ">=60; race(" + race + ")," + race + "==Black."));
        engine.assertz(engine.getTermParser().parseTerm("bolest(blepharitis):- years(" + years + ")," + years + ">=60."));
        engine.assertz(engine.getTermParser().parseTerm("bolest(macular_degeneration):- years(" + years + ")," + years + ">=60."));
        engine.assertz(engine.getTermParser().parseTerm("bolest(eye_alignment_disorder):- years(" + years + ")," + years + "<15; race(" + race + ")," + race + "==Other."));
        engine.assertz(engine.getTermParser().parseTerm("bolest(optic_neuritis):- years(" + years + ")," + years + ">29," + years + "<45."));
        engine.assertz(engine.getTermParser().parseTerm("bolest(iridocyclitis):- race(" + race + ")," + race + "==Black."));
        engine.assertz(engine.getTermParser().parseTerm("bolest(floaters):- years(" + years + ")," + years + ">=60."));
        engine.assertz(engine.getTermParser().parseTerm("bolest(subconjunctival_hemorrhage):- years(" + years + ")," + years + ">4," + years + "<15; sex(" + sex + ")," + sex + "==Male."));
        engine.assertz(engine.getTermParser().parseTerm("bolest(retinal_detachment):- years(" + years + ")," + years + ">44," + years + "<75."));
        engine.assertz(engine.getTermParser().parseTerm("bolest(dry_eye_of_unknown_cause):- years(" + years + ")," + years + ">=60."));
        engine.assertz(engine.getTermParser().parseTerm("bolest(corneal_abrasion):- years(" + years + ")," + years + ">29," + years + "<45."));

        JIPQuery query = engine.openSynchronousQuery("bolest(X).");
        JIPTerm solution;
        ArrayList<String> diseases = new ArrayList<>();
        while ((solution = query.nextSolution()) != null) {
            for (JIPVariable var : solution.getVariables()) {
                diseases.add(var.getValue().toString());
            }
        }
        ArrayList<String> unique = new ArrayList<>();
        for (String disease : diseases) {
            boolean found = false;
            for (String u : unique) {
                if (u.equals(disease)) {
                    found = true;
                }
            }
            if (found == false) {
                unique.add(disease);
                System.out.println(disease);
            }
        }
    }
}
