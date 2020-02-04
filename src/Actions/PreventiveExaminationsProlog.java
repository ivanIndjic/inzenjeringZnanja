package Actions;

import com.ugos.jiprolog.engine.JIPEngine;
import com.ugos.jiprolog.engine.JIPQuery;
import com.ugos.jiprolog.engine.JIPTerm;
import com.ugos.jiprolog.engine.JIPVariable;
import view.PreventiveRBR;

import javax.naming.ldap.HasControls;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class PreventiveExaminationsProlog {
    public static Map<String,String> diseasesAndTests= new HashMap<>();
    public static ArrayList<String> unique = new ArrayList<>();
    public PreventiveExaminationsProlog(String race, String sex, Integer years) {
        diseasesAndTests.put("cataract", "Patients with cataract often receive lens and cataract procedures, ophthalmic examination and evaluation. Ophthalmologic and otologic diagnosis and treatment. Other therapeutic procedures on eyelids, conjunctiva and cornea. Diagnostic procedures on eye, other intraocular therapeutic procedures, glaucoma procedures and destruction of lesion of retina and choroid.");
        diseasesAndTests.put("conjunctivitis","Patients with conjunctivitis often receive ophthalmic examination and evaluation, influenzavirus antibody assay. Nonoperative removal of foreign body, other or therapeutic nervous system procedures. Other therapeutic ear procedures, other extraocular muscle and orbit therapeutic procedures and other non-or therapeutic procedures on nose, mouth and pharynx.");
        diseasesAndTests.put("chronic_glaucoma", "Patients with chronic glaucoma often receive ophthalmic examination and evaluation. Other therapeutic procedures on eyelids, conjunctiva and cornea. Ophthalmologic and otologic diagnosis and treatment, lens and cataract procedures, diagnostic procedures on eye, glaucoma procedures. Other intraocular therapeutic procedures and other extraocular muscle and orbit therapeutic procedures.");
        diseasesAndTests.put("blepharitis","Patients with blepharitis often receive ophthalmic examination and evaluation. Other therapeutic procedures on eyelids, conjunctiva and cornea. Ophthalmologic and otologic diagnosis and treatment, lens and cataract procedures. Diagnostic procedures on eye and physical therapy exercises, manipulation and other procedures.");
        diseasesAndTests.put("macular_degeneration","Patients with macular degeneration often receive ophthalmic examination and evaluation. ophthalmologic and otologic diagnosis and treatment, other therapeutic procedures on eyelids, conjunctiva and cornea. Other intraocular therapeutic procedures, other therapeutic procedures, diagnostic procedures on eye, lens and cataract procedures and other extraocular muscle and orbit therapeutic procedures.");
        diseasesAndTests.put("eye_alignment_disorder","Patients with eye alignment disorder often receive ophthalmologic and otologic diagnosis and treatment. Other diagnostic procedures (interview, evaluation, consultation). Other therapeutic procedures on eyelids, conjunctiva and cornea. Other therapeutic procedures, ophthalmic examination and evaluation. Other extraocular muscle and orbit therapeutic procedures, occupational therapy assessment and referral to home health care service.");
        diseasesAndTests.put("optic_neuritis","Patients with optic neuritis often receive radiographic imaging procedure, magnetic resonance imaging. Other diagnostic procedures (interview, evaluation, consultation). Diagnostic spinal tap, ophthalmic examination and evaluation, diagnostic ultrasound of heart (echocardiogram). Other therapeutic procedures on eyelids, conjunctiva, cornea and incision and drainage.");
        diseasesAndTests.put("iridocyclitis","Patients with iridocyclitis often receive ophthalmic examination and evaluation, other therapeutic procedures on eyelids, conjunctiva and cornea. Ophthalmologic and otologic diagnosis and treatment, nonoperative removal of foreign body, repair of retinal tear and detachment. Other extraocular muscle and orbit therapeutic procedures. Diagnostic procedures on eye and other intraocular therapeutic procedures.");
        diseasesAndTests.put("floaters","Patients with floaters often receive ophthalmic examination and evaluation, other intraocular therapeutic procedures, other therapeutic procedures on eyelids, conjunctiva and cornea. Ophthalmologic and otologic diagnosis and treatment, biopsy and diagnostic procedures on eye.");
        diseasesAndTests.put("subconjunctival_hemorrhage","Patients with subconjunctival hemorrhage often receive x-ray computed tomography, cat scan of head and prothrombin time assay. Ophthalmic examination and evaluation, suturing of wound, blood alcohol, nonoperative removal of foreign body and culture wound.");
        diseasesAndTests.put("retinal_detachment","Patients with retinal detachment often receive ophthalmic examination and evaluation, repair of retinal tear and detachment. Other therapeutic procedures on eyelids, conjunctiva, cornea and excision. Other intraocular therapeutic procedures, ophthalmologic and otologic diagnosis and treatment. Wound care management and other extraocular muscle and orbit therapeutic procedures.");
        diseasesAndTests.put("dry_eye_of_unknown_cause","Patients with dry eye of unknown cause often receive ophthalmic examination and evaluation. Other therapeutic procedures on eyelids, conjunctiva and cornea. Ophthalmologic and otologic diagnosis and treatment, diagnostic procedures on eye. Other extraocular muscle and orbit therapeutic procedures. Other intraocular therapeutic procedures. Lens and cataract procedures and physical therapy exercises, manipulation and other procedures .");
        diseasesAndTests.put("corneal_abrasion","Patients with corneal abrasion often receive nonoperative removal of foreign body, wound care management. Ophthalmic examination and evaluation and other therapeutic procedures on eyelids, conjunctiva and cornea.");
        JIPEngine engine = new JIPEngine();
        engine.assertz(engine.getTermParser().parseTerm("years(" + years + ")."));
        engine.assertz(engine.getTermParser().parseTerm("sex(" + sex + ")."));
        engine.assertz(engine.getTermParser().parseTerm("race(" + race + ")."));
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

        for (String disease : diseases) {
            boolean found = false;
            for (String u : unique) {
                if (u.equals(disease)) {
                    found = true;
                }
            }
            if (!found) {
                unique.add(disease);
                System.out.println(disease);
            }
        }
        PreventiveRBR rbr = new PreventiveRBR();
        rbr.drawPanel(diseases);
    }
}
