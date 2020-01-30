package Actions;

import com.ugos.jiprolog.engine.JIPEngine;
import com.ugos.jiprolog.engine.JIPQuery;
import com.ugos.jiprolog.engine.JIPTerm;
import com.ugos.jiprolog.engine.JIPVariable;

import java.util.ArrayList;

public class MedicationsProlog {
    public ArrayList<String> prolog(String bolest, ArrayList<String> notes) {
        JIPEngine engine = new JIPEngine();
        ArrayList<String> medicaments = new ArrayList<>();
        int brojac = 1;
        if (notes.size() != 0) {
            for (String note : notes) {
                engine.assertz(engine.getTermParser().parseTerm("bolest(" + bolest + ")."));
                engine.assertz(engine.getTermParser().parseTerm("note(" + note + ")."));

                engine.assertz(engine.getTermParser().parseTerm("medicament(moxifloxacin):- bolest(cataract),\\+ note(arthritis),\\+ note(blood_circulation_problems),\\+ note(heart_problems),\\+ note(high_blood_pressure),\\+ note(diabetes),\\+ note(liver_or_kidney_disease),\\+ note(seizures_or_epilepsy),\\+ note(head_injury)."));
                engine.assertz(engine.getTermParser().parseTerm("medicament(latanoprost):- bolest(cataract),\\+ note(been_using_other_medications_lately),\\+ note(eye_surgery),\\+ note(astma)."));
                engine.assertz(engine.getTermParser().parseTerm("medicament(travoprost):- bolest(cataract),\\+ note(swelling_or_an_infection_in_your_eye),\\+ note(eye_surgery)."));
                engine.assertz(engine.getTermParser().parseTerm("medicament(brimonidine):- bolest(cataract),\\+ note(depression),\\+ note(low_blood_pressure),\\+ note(clogged_arteries)."));
                engine.assertz(engine.getTermParser().parseTerm("medicament(nepafenac):- bolest(cataract),\\+ note(diabetes),\\+ note(arthritis),\\+ note(clogged_arteries)."));
                engine.assertz(engine.getTermParser().parseTerm("medicament(phenylephrine):- bolest(cataract),\\+ note(heart_problems),\\+ note(high_blood_pressure),\\+ note(diabetes),\\+ note(enlarged_prostate_or_urination_problems)."));

                engine.assertz(engine.getTermParser().parseTerm("medicament(latanoprost):- bolest(chronic_glaucoma),\\+ note(been_using_other_medications_lately),\\+ note(eye_surgery),\\+ note(astma)."));
                engine.assertz(engine.getTermParser().parseTerm("medicament(travoprost):- bolest(chronic_glaucoma),\\+ note(swelling_or_an_infection_in_your_eye),\\+ note(eye_surgery)."));
                engine.assertz(engine.getTermParser().parseTerm("medicament(bimatoprost):- bolest(chronic_glaucoma),\\+ note(eye_surgery)."));
                engine.assertz(engine.getTermParser().parseTerm("medicament(brimonidine):- bolest(chronic_glaucoma),\\+ note(depression),\\+ note(low_blood_pressure),\\+ note(clogged_arteries)."));
                engine.assertz(engine.getTermParser().parseTerm("medicament(timolol):- bolest(chronic_glaucoma),\\+ note(diabetes),\\+ note(sleep_apnea),\\+ note(liver_or_kidney_disease),\\+ note(overactive_thyroid)."));
                engine.assertz(engine.getTermParser().parseTerm("medicament(prednisolone):- bolest(chronic_glaucoma),\\+ note(liver_or_kidney_disease),\\+ note(depression),\\+ note(tuberculosis),\\+ note(thyroid_disorder),\\+ note(mental_illness),\\+ note(high_blood_pressure),\\+ note(multiple_sclerosis)."));

                engine.assertz(engine.getTermParser().parseTerm("medicament(moxifloxacin):- bolest(conjunctivitis),\\+ note(arthritis),\\+ note(blood_circulation_problems),\\+ note(heart_problems),\\+ note(high_blood_pressure),\\+ note(diabetes),\\+ note(liver_or_kidney_disease),\\+ note(seizures_or_epilepsy),\\+ note(head_injury)."));
                engine.assertz(engine.getTermParser().parseTerm("medicament(sulfacetamide_ophthalmic):- bolest(conjunctivitis),\\+ note(astma)."));
                engine.assertz(engine.getTermParser().parseTerm("medicament(erythromycin):- bolest(conjunctivitis),\\+ note(liver_or_kidney_disease),\\+ note(heart_rhythm_disorder),\\+ note(low_levels_of_potassium_or_magnesium),\\+ note(pregnancy)."));
                engine.assertz(engine.getTermParser().parseTerm("medicament(gentamicin):- bolest(conjunctivitis),\\+ note(pregnancy)."));
                engine.assertz(engine.getTermParser().parseTerm("medicament(ciprofloxacin):- bolest(conjunctivitis),\\+ note(astma)."));
                engine.assertz(engine.getTermParser().parseTerm("medicament(ofloxacin):- bolest(conjunctivitis),\\+ note(bone_problems),\\+ note(muscle_or_nerve_disorder),\\+ note(arthritis),\\+ note(heart_problem),\\+ note(high_blood_pressure),\\+ note(low_levels_of_potassium)."));

                engine.assertz(engine.getTermParser().parseTerm("medicament(latanoprost):- bolest(blepharitis),\\+ note(been_using_other_medications_lately),\\+ note(eye_surgery),\\+ note(astma)."));
                engine.assertz(engine.getTermParser().parseTerm("medicament(phenylephrine):- bolest(blepharitis),\\+ note(heart_problems),\\+ note(high_blood_pressure),\\+ note(diabetes),\\+ note(enlarged_prostate_or_urination_problems)."));
                engine.assertz(engine.getTermParser().parseTerm("medicament(erythromycin):- bolest(blepharitis),\\+ note(liver_or_kidney_disease),\\+ note(heart_rhythm_disorder),\\+ note(low_levels_of_potassium_or_magnesium),\\+ note(pregnancy)."));
                engine.assertz(engine.getTermParser().parseTerm("medicament(ocular_lubricant):- bolest(blepharitis)."));
                engine.assertz(engine.getTermParser().parseTerm("medicament(bimatoprost):- bolest(blepharitis),\\+ note(eye_surgery)."));

                engine.assertz(engine.getTermParser().parseTerm("medicament(phenylephrine):- bolest(macular_degeneration),\\+ note(heart_problems),\\+ note(high_blood_pressure),\\+ note(diabetes),\\+ note(enlarged_prostate_or_urination_problems)."));
                engine.assertz(engine.getTermParser().parseTerm("medicament(prednisolone):- bolest(macular_degeneration),\\+ note(liver_or_kidney_disease),\\+ note(depression),\\+ note(tuberculosis),\\+ note(thyroid_disorder),\\+ note(mental_illness),\\+ note(high_blood_pressure),\\+ note(multiple_sclerosis)."));
                engine.assertz(engine.getTermParser().parseTerm("medicament(latanoprost):- bolest(macular_degeneration),\\+ note(been_using_other_medications_lately),\\+ note(eye_surgery),\\+ note(astma)."));
                engine.assertz(engine.getTermParser().parseTerm("medicament(bevacizumab):- bolest(macular_degeneration),\\+ note(high_blood_pressure),\\+ note(heart_attack),\\+ note(stomach_or_intestinal_bleeding)."));

                engine.assertz(engine.getTermParser().parseTerm("medicament(cyclosporine_ophthalmic):- bolest(dry_eye_of_unknown_cause),\\+ note(pregnancy),\\+ note(astma)."));
                engine.assertz(engine.getTermParser().parseTerm("medicament(latanoprost):- bolest(dry_eye_of_unknown_cause),\\+ note(been_using_other_medications_lately),\\+ note(eye_surgery),\\+ note(astma)."));
                engine.assertz(engine.getTermParser().parseTerm("medicament(loteprednol):- bolest(dry_eye_of_unknown_cause),\\+ note(eye_infection),\\+ note(pregnancy)."));
                engine.assertz(engine.getTermParser().parseTerm("medicament(phenylephrine):- bolest(dry_eye_of_unknown_cause),\\+ note(heart_problems),\\+ note(high_blood_pressure),\\+ note(diabetes),\\+ note(enlarged_prostate_or_urination_problems)."));
                engine.assertz(engine.getTermParser().parseTerm("medicament(travoprost):- bolest(dry_eye_of_unknown_cause),\\+ note(swelling_or_an_infection_in_your_eye),\\+ note(eye_surgery)."));
                engine.assertz(engine.getTermParser().parseTerm("medicament(ocular_lubricant):- bolest(dry_eye_of_unknown_cause)."));
                engine.assertz(engine.getTermParser().parseTerm("medicament(timolol):- bolest(dry_eye_of_unknown_cause),\\+ note(diabetes),\\+ note(sleep_apnea),\\+ note(liver_or_kidney_disease),\\+ note(overactive_thyroid)."));

                engine.assertz(engine.getTermParser().parseTerm("medicament(phenylephrine):- bolest(eye_alignment_disorder),\\+ note(heart_problems),\\+ note(high_blood_pressure),\\+ note(diabetes),\\+ note(enlarged_prostate_or_urination_problems)."));
                engine.assertz(engine.getTermParser().parseTerm("medicament(cyclosporine_ophthalmic):- bolest(eye_alignment_disorder),\\+ note(pregnancy),\\+ note(astma)."));
                engine.assertz(engine.getTermParser().parseTerm("medicament(tropicamide):- bolest(eye_alignment_disorder)."));
                engine.assertz(engine.getTermParser().parseTerm("medicament(atropine):- bolest(eye_alignment_disorder),\\+ note(enlarged_prostate),\\+ note(heart_rhythm_disorder),\\+ note(liver_or_kidney_disease),\\+ note(astma)."));
                engine.assertz(engine.getTermParser().parseTerm("medicament(dexamethasone):- bolest(eye_alignment_disorder),\\+ note(thyroid_disorder),\\+ note(malaria),\\+ depression(eye_infection),\\+ depression(liver_or_kidney_disease)."));
                engine.assertz(engine.getTermParser().parseTerm("medicament(fluorescein):- bolest(eye_alignment_disorder),\\+ note(shortness_of_breath),\\+ note(swelling_of_face)."));

                engine.assertz(engine.getTermParser().parseTerm("medicament(gentamicin):- bolest(corneal_abrasion),\\+ note(pregnancy)."));
                engine.assertz(engine.getTermParser().parseTerm("medicament(erythromycin):- bolest(corneal_abrasion),\\+ note(liver_or_kidney_disease),\\+ note(heart_rhythm_disorder),\\+ note(low_levels_of_potassium_or_magnesium),\\+ note(pregnancy)."));
                engine.assertz(engine.getTermParser().parseTerm("medicament(tetracaine):- bolest(corneal_abrasion),\\+ note(heart_rhythm_disorder),\\+ note(high_blood_pressure)."));
                engine.assertz(engine.getTermParser().parseTerm("medicament(proparacaine):- bolest(corneal_abrasion),\\+ note(heart_problems),\\+ note(overactive_thyroid)."));
                engine.assertz(engine.getTermParser().parseTerm("medicament(tobramycin_injection):- bolest(corneal_abrasion),\\+ note(kidney_disease),\\+ note(muscle_disorder),\\+ note(metabolic_disorder),\\+ note(astma)."));

                engine.assertz(engine.getTermParser().parseTerm("medicament(moxifloxacin):- bolest(cornea_infection),\\+ note(arthritis),\\+ note(blood_circulation_problems),\\+ note(heart_problems),\\+ note(high_blood_pressure),\\+ note(diabetes),\\+ note(liver_or_kidney_disease),\\+ note(seizures_or_epilepsy),\\+ note(head_injury)."));
                engine.assertz(engine.getTermParser().parseTerm("medicament(erythromycin):- bolest(cornea_infection),\\+ note(liver_or_kidney_disease),\\+ note(heart_rhythm_disorder),\\+ note(low_levels_of_potassium_or_magnesium),\\+ note(pregnancy)."));
                engine.assertz(engine.getTermParser().parseTerm("medicament(proparacaine):- bolest(cornea_infection),\\+ note(heart_problems),\\+ note(overactive_thyroid)."));
                engine.assertz(engine.getTermParser().parseTerm("medicament(loteprednol):- bolest(cornea_infection),\\+ note(eye_infection),\\+ note(pregnancy)."));
                engine.assertz(engine.getTermParser().parseTerm("medicament(dexamethasone):- bolest(cornea_infection),\\+ note(thyroid_disorder),\\+ note(malaria),\\+ depression(eye_infection),\\+ depression(liver_or_kidney_disease)."));

                engine.assertz(engine.getTermParser().parseTerm("medicament(phenylephrine):- bolest(retinal_detachment),\\+ note(heart_problems),\\+ note(high_blood_pressure),\\+ note(diabetes),\\+ note(enlarged_prostate_or_urination_problems)."));
                engine.assertz(engine.getTermParser().parseTerm("medicament(prednisolone):- bolest(retinal_detachment),\\+ note(liver_or_kidney_disease),\\+ note(depression),\\+ note(tuberculosis),\\+ note(thyroid_disorder),\\+ note(mental_illness),\\+ note(high_blood_pressure),\\+ note(multiple_sclerosis)."));
                engine.assertz(engine.getTermParser().parseTerm("medicament(cyclosporine_ophthalmic):- bolest(retinal_detachment),\\+ note(pregnancy),\\+ note(astma)."));
                engine.assertz(engine.getTermParser().parseTerm("medicament(tropicamide):- bolest(retinal_detachment)."));

                engine.assertz(engine.getTermParser().parseTerm("medicament(glatiramer_injection):- bolest(optic_neuritis),\\+ note(pregnancy)."));
                engine.assertz(engine.getTermParser().parseTerm("medicament(fluorescein):- bolest(optic_neuritis),\\+ note(shortness_of_breath),\\+ note(swelling_of_face)."));
                engine.assertz(engine.getTermParser().parseTerm("medicament(naltrexone):- bolest(optic_neuritis),\\+ note(liver_or_kidney_disease),\\+ note(bleeding_disorder),\\+ note(drug_or_alcohol_addiction)."));
                engine.assertz(engine.getTermParser().parseTerm("medicament(brinzolamide):- bolest(optic_neuritis),\\+ note(liver_or_kidney_disease)."));
                engine.assertz(engine.getTermParser().parseTerm("medicament(desvenlafaxine):- bolest(optic_neuritis),\\+ note(mental_illness),\\+ note(depression),\\+ note(migraine),\\+ note(high_blood_pressure),\\+ note(high_cholesterol),\\+ note(seizures_or_epilepsy),\\+ note(lung_or_breathing_problems),\\+ note(bleeding_or_blood_clotting_disorder)."));

                engine.assertz(engine.getTermParser().parseTerm("medicament(prednisolone):- bolest(iridocyclitis),\\+ note(liver_or_kidney_disease),\\+ note(depression),\\+ note(tuberculosis),\\+ note(thyroid_disorder),\\+ note(mental_illness),\\+ note(high_blood_pressure),\\+ note(multiple_sclerosis)."));
                engine.assertz(engine.getTermParser().parseTerm("medicament(cyclosporine_ophthalmic):- bolest(iridocyclitis),\\+ note(pregnancy),\\+ note(astma)."));
                engine.assertz(engine.getTermParser().parseTerm("medicament(brimonidine):- bolest(iridocyclitis),\\+ note(depression),\\+ note(low_blood_pressure),\\+ note(clogged_arteries)."));
                engine.assertz(engine.getTermParser().parseTerm("medicament(tropicamide):- bolest(iridocyclitis)."));
                engine.assertz(engine.getTermParser().parseTerm("medicament(loteprednol):- bolest(iridocyclitis),\\+ note(eye_infection),\\+ note(pregnancy)."));
                engine.assertz(engine.getTermParser().parseTerm("medicament(atropine):- bolest(iridocyclitis),\\+ note(enlarged_prostate),\\+ note(heart_rhythm_disorder),\\+ note(liver_or_kidney_disease),\\+ note(astma)."));

                engine.assertz(engine.getTermParser().parseTerm("medicament(tetracaine):- bolest(subconjunctival_hemorrhage),\\+ note(heart_rhythm_disorder),\\+ note(high_blood_pressure)."));
                engine.assertz(engine.getTermParser().parseTerm("medicament(fluorescein):- bolest(subconjunctival_hemorrhage),\\+ note(shortness_of_breath),\\+ note(swelling_of_face)."));
                engine.assertz(engine.getTermParser().parseTerm("medicament(prednisolone):- bolest(subconjunctival_hemorrhage),\\+ note(liver_or_kidney_disease),\\+ note(depression),\\+ note(tuberculosis),\\+ note(thyroid_disorder),\\+ note(mental_illness),\\+ note(high_blood_pressure),\\+ note(multiple_sclerosis)."));
                engine.assertz(engine.getTermParser().parseTerm("medicament(gentamicin):- bolest(subconjunctival_hemorrhage),\\+ note(pregnancy)."));
                engine.assertz(engine.getTermParser().parseTerm("medicament(erythromycin):- bolest(subconjunctival_hemorrhage),\\+ note(liver_or_kidney_disease),\\+ note(heart_rhythm_disorder),\\+ note(low_levels_of_potassium_or_magnesium),\\+ note(pregnancy)."));
                engine.assertz(engine.getTermParser().parseTerm("medicament(atropine):- bolest(subconjunctival_hemorrhage),\\+ note(enlarged_prostate),\\+ note(heart_rhythm_disorder),\\+ note(liver_or_kidney_disease),\\+ note(astma)."));
                engine.assertz(engine.getTermParser().parseTerm("medicament(sulfacetamide_ophthalmic):- bolest(subconjunctival_hemorrhage),\\+ note(astma)."));

                engine.assertz(engine.getTermParser().parseTerm("medicament(phenylephrine):- bolest(floaters),\\+ note(heart_problems),\\+ note(high_blood_pressure),\\+ note(diabetes),\\+ note(enlarged_prostate_or_urination_problems)."));
                engine.assertz(engine.getTermParser().parseTerm("medicament(tropicamide):- bolest(floaters)."));
                engine.assertz(engine.getTermParser().parseTerm("medicament(ocular_lubricant):- bolest(floaters)."));
                engine.assertz(engine.getTermParser().parseTerm("medicament(loteprednol):- bolest(floaters),\\+ note(eye_infection),\\+ note(pregnancy)."));
                engine.assertz(engine.getTermParser().parseTerm("medicament(fluorescein):- bolest(floaters),\\+ note(shortness_of_breath),\\+ note(swelling_of_face)."));
                engine.assertz(engine.getTermParser().parseTerm("medicament(cyclosporine_ophthalmic):- bolest(floaters),\\+ note(pregnancy),\\+ note(astma)."));

                JIPQuery query2 = engine.openSynchronousQuery("medicament(X).");

                JIPTerm solution;
                ArrayList<String> medicationsCopy = new ArrayList<>();
                JIPQuery query = engine.openSynchronousQuery("medicament(X).");
                while ((solution = query.nextSolution()) != null) {
                    for (JIPVariable var : solution.getVariables()) {
                        if (brojac == 1) {
                            medicationsCopy.add(var.getValue().toString());
                        } else {
                            for (String test : medicaments) {
                                if (test.equals(var.getValue().toString())) {
                                    medicationsCopy.add(test);
                                }
                            }
                        }
                    }
                }
                medicaments = medicationsCopy;
                brojac++;
            }
        } else {
            engine.assertz(engine.getTermParser().parseTerm("bolest(" + bolest + ")."));

            engine.assertz(engine.getTermParser().parseTerm("medicament(moxifloxacin):- bolest(conjunctivitis)."));
            engine.assertz(engine.getTermParser().parseTerm("medicament(sulfacetamide_ophthalmic):- bolest(conjunctivitis)."));
            engine.assertz(engine.getTermParser().parseTerm("medicament(erythromycin):- bolest(conjunctivitis)."));
            engine.assertz(engine.getTermParser().parseTerm("medicament(gentamicin):- bolest(conjunctivitis)."));
            engine.assertz(engine.getTermParser().parseTerm("medicament(ciprofloxacin):- bolest(conjunctivitis)."));
            engine.assertz(engine.getTermParser().parseTerm("medicament(ofloxacin):- bolest(conjunctivitis)."));

            engine.assertz(engine.getTermParser().parseTerm("medicament(cyclosporine_ophthalmic):- bolest(dry_eye_of_unknown_cause)."));
            engine.assertz(engine.getTermParser().parseTerm("medicament(latanoprost):- bolest(dry_eye_of_unknown_cause)."));
            engine.assertz(engine.getTermParser().parseTerm("medicament(loteprednol):- bolest(dry_eye_of_unknown_cause)."));
            engine.assertz(engine.getTermParser().parseTerm("medicament(phenylephrine):- bolest(dry_eye_of_unknown_cause)."));
            engine.assertz(engine.getTermParser().parseTerm("medicament(travoprost):- bolest(dry_eye_of_unknown_cause)."));
            engine.assertz(engine.getTermParser().parseTerm("medicament(ocular_lubricant):- bolest(dry_eye_of_unknown_cause)."));
            engine.assertz(engine.getTermParser().parseTerm("medicament(timolol):- bolest(dry_eye_of_unknown_cause)."));

            engine.assertz(engine.getTermParser().parseTerm("medicament(moxifloxacin):- bolest(cataract)."));
            engine.assertz(engine.getTermParser().parseTerm("medicament(latanoprost):- bolest(cataract)."));
            engine.assertz(engine.getTermParser().parseTerm("medicament(travoprost):- bolest(cataract)."));
            engine.assertz(engine.getTermParser().parseTerm("medicament(brimonidine):- bolest(cataract)."));
            engine.assertz(engine.getTermParser().parseTerm("medicament(nepafenac):- bolest(cataract)."));
            engine.assertz(engine.getTermParser().parseTerm("medicament(phenylephrine):- bolest(cataract)."));

            engine.assertz(engine.getTermParser().parseTerm("medicament(latanoprost):- bolest(blepharitis)."));
            engine.assertz(engine.getTermParser().parseTerm("medicament(phenylephrine):- bolest(blepharitis)."));
            engine.assertz(engine.getTermParser().parseTerm("medicament(erythromycin):- bolest(blepharitis)."));
            engine.assertz(engine.getTermParser().parseTerm("medicament(ocular_lubricant):- bolest(blepharitis)."));
            engine.assertz(engine.getTermParser().parseTerm("medicament(bimatoprost):- bolest(blepharitis)."));

            engine.assertz(engine.getTermParser().parseTerm("medicament(latanoprost):- bolest(chronic_glaucoma)."));
            engine.assertz(engine.getTermParser().parseTerm("medicament(travoprost):- bolest(chronic_glaucoma)."));
            engine.assertz(engine.getTermParser().parseTerm("medicament(bimatoprost):- bolest(chronic_glaucoma)."));
            engine.assertz(engine.getTermParser().parseTerm("medicament(brimonidine):- bolest(chronic_glaucoma)."));
            engine.assertz(engine.getTermParser().parseTerm("medicament(timolol):- bolest(chronic_glaucoma)."));
            engine.assertz(engine.getTermParser().parseTerm("medicament(prednisolone):- bolest(chronic_glaucoma)."));

            engine.assertz(engine.getTermParser().parseTerm("medicament(phenylephrine):- bolest(macular_degeneration)."));
            engine.assertz(engine.getTermParser().parseTerm("medicament(prednisolone):- bolest(macular_degeneration)."));
            engine.assertz(engine.getTermParser().parseTerm("medicament(latanoprost):- bolest(macular_degeneration)."));
            engine.assertz(engine.getTermParser().parseTerm("medicament(bevacizumab):- bolest(macular_degeneration)."));

            engine.assertz(engine.getTermParser().parseTerm("medicament(gentamicin):- bolest(corneal_abrasion)."));
            engine.assertz(engine.getTermParser().parseTerm("medicament(erythromycin):- bolest(corneal_abrasion)."));
            engine.assertz(engine.getTermParser().parseTerm("medicament(tetracaine):- bolest(corneal_abrasion)."));
            engine.assertz(engine.getTermParser().parseTerm("medicament(proparacaine):- bolest(corneal_abrasion)."));
            engine.assertz(engine.getTermParser().parseTerm("medicament(tobramycin_injection):- bolest(corneal_abrasion)."));

            engine.assertz(engine.getTermParser().parseTerm("medicament(moxifloxacin):- bolest(cornea_infection)."));
            engine.assertz(engine.getTermParser().parseTerm("medicament(erythromycin):- bolest(cornea_infection)."));
            engine.assertz(engine.getTermParser().parseTerm("medicament(proparacaine):- bolest(cornea_infection)."));
            engine.assertz(engine.getTermParser().parseTerm("medicament(loteprednol):- bolest(cornea_infection)."));
            engine.assertz(engine.getTermParser().parseTerm("medicament(dexamethasone):- bolest(cornea_infection)."));

            engine.assertz(engine.getTermParser().parseTerm("medicament(phenylephrine):- bolest(retinal_detachment)."));
            engine.assertz(engine.getTermParser().parseTerm("medicament(prednisolone):- bolest(retinal_detachment)."));
            engine.assertz(engine.getTermParser().parseTerm("medicament(cyclosporine_ophthalmic):- bolest(retinal_detachment)."));
            engine.assertz(engine.getTermParser().parseTerm("medicament(tropicamide):- bolest(retinal_detachment)."));

            engine.assertz(engine.getTermParser().parseTerm("medicament(glatiramer_injection):- bolest(optic_neuritis)."));
            engine.assertz(engine.getTermParser().parseTerm("medicament(fluorescein):- bolest(optic_neuritis)."));
            engine.assertz(engine.getTermParser().parseTerm("medicament(naltrexone):- bolest(optic_neuritis)."));
            engine.assertz(engine.getTermParser().parseTerm("medicament(brinzolamide):- bolest(optic_neuritis)."));
            engine.assertz(engine.getTermParser().parseTerm("medicament(desvenlafaxine):- bolest(optic_neuritis)."));

            engine.assertz(engine.getTermParser().parseTerm("medicament(prednisolone):- bolest(iridocyclitis)."));
            engine.assertz(engine.getTermParser().parseTerm("medicament(cyclosporine_ophthalmic):- bolest(iridocyclitis)."));
            engine.assertz(engine.getTermParser().parseTerm("medicament(brimonidine):- bolest(iridocyclitis)."));
            engine.assertz(engine.getTermParser().parseTerm("medicament(tropicamide):- bolest(iridocyclitis)."));
            engine.assertz(engine.getTermParser().parseTerm("medicament(loteprednol):- bolest(iridocyclitis)."));
            engine.assertz(engine.getTermParser().parseTerm("medicament(atropine):- bolest(iridocyclitis)."));

            engine.assertz(engine.getTermParser().parseTerm("medicament(tetracaine):- bolest(subconjunctival_hemorrhage)."));
            engine.assertz(engine.getTermParser().parseTerm("medicament(fluorescein):- bolest(subconjunctival_hemorrhage)."));
            engine.assertz(engine.getTermParser().parseTerm("medicament(prednisolone):- bolest(subconjunctival_hemorrhage)."));
            engine.assertz(engine.getTermParser().parseTerm("medicament(gentamicin):- bolest(subconjunctival_hemorrhage)."));
            engine.assertz(engine.getTermParser().parseTerm("medicament(erythromycin):- bolest(subconjunctival_hemorrhage)."));
            engine.assertz(engine.getTermParser().parseTerm("medicament(atropine):- bolest(subconjunctival_hemorrhage)."));
            engine.assertz(engine.getTermParser().parseTerm("medicament(sulfacetamide_ophthalmic):- bolest(subconjunctival_hemorrhage)."));

            engine.assertz(engine.getTermParser().parseTerm("medicament(phenylephrine):- bolest(floaters)."));
            engine.assertz(engine.getTermParser().parseTerm("medicament(tropicamide):- bolest(floaters)."));
            engine.assertz(engine.getTermParser().parseTerm("medicament(ocular_lubricant):- bolest(floaters)."));
            engine.assertz(engine.getTermParser().parseTerm("medicament(loteprednol):- bolest(floaters)."));
            engine.assertz(engine.getTermParser().parseTerm("medicament(fluorescein):- bolest(floaters)."));
            engine.assertz(engine.getTermParser().parseTerm("medicament(cyclosporine_ophthalmic):- bolest(floaters)."));

            engine.assertz(engine.getTermParser().parseTerm("medicament(phenylephrine):- bolest(eye_alignment_disorder)."));
            engine.assertz(engine.getTermParser().parseTerm("medicament(cyclosporine_ophthalmic):- bolest(eye_alignment_disorder)."));
            engine.assertz(engine.getTermParser().parseTerm("medicament(tropicamide):- bolest(eye_alignment_disorder)."));
            engine.assertz(engine.getTermParser().parseTerm("medicament(atropine):- bolest(eye_alignment_disorder)."));
            engine.assertz(engine.getTermParser().parseTerm("medicament(dexamethasone):- bolest(eye_alignment_disorder)."));
            engine.assertz(engine.getTermParser().parseTerm("medicament(fluorescein):- bolest(eye_alignment_disorder)."));

            JIPQuery query2 = engine.openSynchronousQuery("medicament(X).");

            JIPTerm solution;
            ArrayList<String> medicationsCopy = new ArrayList<>();
            JIPQuery query = engine.openSynchronousQuery("medicament(X).");
            while ((solution = query.nextSolution()) != null) {
                for (JIPVariable var : solution.getVariables()) {
                    medicaments.add(var.getValue().toString());
                }
            }
        }
        ArrayList<String> unique = new ArrayList<>();
        for (String m : medicaments) {
            if (unique.size() != 0) {
                boolean found = false;
                for (String u : unique) {
                    if (u.equals(m)) {
                        found = true;
                    }
                }
                if (found == false) {
                    unique.add(m);
                }
            } else {
                unique.add(m);
            }
        }

        return unique;
    }
}
