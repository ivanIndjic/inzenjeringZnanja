package model;

import java.util.ArrayList;
import java.util.HashMap;

public class BazaPodataka {

	private HashMap<String,ArrayList<String>> bolesti=new HashMap<String,ArrayList<String>>();
	public HashMap<String,ArrayList<String>> getBolesti(){
		ArrayList<String> conjunctivitis=new ArrayList<>();
		conjunctivitis.add("eye redness");
		conjunctivitis.add("pain in eye");
		conjunctivitis.add("itchiness of eye");
		conjunctivitis.add("swollen eye");
		conjunctivitis.add("white discharge from eye");
		conjunctivitis.add("lacrimation");
		
		ArrayList<String> blepharitis=new ArrayList<>();
		blepharitis.add("diminished vision");
		blepharitis.add("eye redness");
		blepharitis.add("pain in eye");
		blepharitis.add("itchiness of eye");
		blepharitis.add("swollen eye");
		blepharitis.add("lacrimation");
		blepharitis.add("spots or clouds in vision");
		blepharitis.add("white discharge from eye");
		blepharitis.add("eye burns or stings");
		
		ArrayList<String> chronic_glaucoma=new ArrayList<>();
		chronic_glaucoma.add("diminished vision");
		chronic_glaucoma.add("pain in eye");
		chronic_glaucoma.add("spots or clouds in vision");
		chronic_glaucoma.add("lacrimation");
		chronic_glaucoma.add("foreign body sensation in eye");
		chronic_glaucoma.add("blidness");
		chronic_glaucoma.add("itchiness of eye");
		chronic_glaucoma.add("white discharge from eye");
		chronic_glaucoma.add("eye burns or stings");
		chronic_glaucoma.add("double vision");
		
		ArrayList<String> cataract=new ArrayList<>();
		cataract.add("diminished vision");
		cataract.add("pain in eye");
		cataract.add("spots or clouds in vision");
		cataract.add("lacrimation");
		cataract.add("foreign body sensation in eye");
		cataract.add("blidness");
		cataract.add("itchiness of eye");
		cataract.add("eye burns or stings");
		cataract.add("double vision");
		cataract.add("cloudy eye");
		
		ArrayList<String> macular_degeneration=new ArrayList<>();
		macular_degeneration.add("diminished vision");
		macular_degeneration.add("pain in eye");
		macular_degeneration.add("spots or clouds in vision");
		macular_degeneration.add("lacrimation");
		macular_degeneration.add("foreign body sensation in eye");
		macular_degeneration.add("blidness");
		macular_degeneration.add("itchiness of eye");
		macular_degeneration.add("eye burns or stings");
		macular_degeneration.add("double vision");
		
		ArrayList<String> dry_eye_of_unknown_cause=new ArrayList<>();
		dry_eye_of_unknown_cause.add("diminished vision");
		dry_eye_of_unknown_cause.add("pain in eye");
		dry_eye_of_unknown_cause.add("spots or clouds in vision");
		dry_eye_of_unknown_cause.add("lacrimation");
		dry_eye_of_unknown_cause.add("foreign body sensation in eye");
		dry_eye_of_unknown_cause.add("eye redness");
		dry_eye_of_unknown_cause.add("itchiness of eye");
		dry_eye_of_unknown_cause.add("eye burns or stings");
		dry_eye_of_unknown_cause.add("double vision");
		dry_eye_of_unknown_cause.add("white discharge from eye");
		
		ArrayList<String> eye_alignment_disorder=new ArrayList<>();
		eye_alignment_disorder.add("diminished vision");
		eye_alignment_disorder.add("pain in eye");
		eye_alignment_disorder.add("double vision");
		eye_alignment_disorder.add("foreign body sensation in eye");
		
		ArrayList<String> corneal_abrasion=new ArrayList<>();
		corneal_abrasion.add("diminished vision");
		corneal_abrasion.add("pain in eye");
		corneal_abrasion.add("swollen eye");
		corneal_abrasion.add("lacrimation");
		corneal_abrasion.add("foreign body sensation in eye");
		corneal_abrasion.add("eye redness");
		corneal_abrasion.add("itchiness of eye");
		corneal_abrasion.add("eye burns or stings");
		corneal_abrasion.add("white discharge from eye");
		
		ArrayList<String> cornea_infection=new ArrayList<>();
		cornea_infection.add("diminished vision");
		cornea_infection.add("pain in eye");
		cornea_infection.add("swollen eye");
		cornea_infection.add("lacrimation");
		cornea_infection.add("foreign body sensation in eye");
		cornea_infection.add("eye redness");
		cornea_infection.add("itchiness of eye");
		cornea_infection.add("eye burns or stings");
		cornea_infection.add("spots or clouds in vision");
		
		ArrayList<String> retinal_detachment=new ArrayList<>();
		retinal_detachment.add("diminished vision");
		retinal_detachment.add("pain in eye");
		retinal_detachment.add("blidness");
		retinal_detachment.add("lacrimation");
		retinal_detachment.add("cloudy eye");
		retinal_detachment.add("double vision");
		retinal_detachment.add("itchiness of eye");
		retinal_detachment.add("eye burns or stings");
		retinal_detachment.add("spots or clouds in vision");
		
		ArrayList<String> optic_neuritis=new ArrayList<>();
		optic_neuritis.add("diminished vision");
		optic_neuritis.add("pain in eye");
		optic_neuritis.add("blidness");
		optic_neuritis.add("spots or clouds in vision");
		
		ArrayList<String> iridocyclitis=new ArrayList<>();
		iridocyclitis.add("diminished vision");
		iridocyclitis.add("pain in eye");
		iridocyclitis.add("spots or clouds in vision");
		iridocyclitis.add("lacrimation");
		iridocyclitis.add("foreign body sensation in eye");
		iridocyclitis.add("eye redness");
		iridocyclitis.add("itchiness of eye");
		iridocyclitis.add("eye burns or stings");
		iridocyclitis.add("cloudy eye");
		iridocyclitis.add("swollen eye");
		
		ArrayList<String> subconjunctival_hemorrhage=new ArrayList<>();
		subconjunctival_hemorrhage.add("diminished vision");
		subconjunctival_hemorrhage.add("pain in eye");
		subconjunctival_hemorrhage.add("swollen eye");
		subconjunctival_hemorrhage.add("blidness");
		subconjunctival_hemorrhage.add("foreign body sensation in eye");
		subconjunctival_hemorrhage.add("eye redness");
		subconjunctival_hemorrhage.add("eye burns or stings");
		
		ArrayList<String> floaters=new ArrayList<>();
		floaters.add("diminished vision");
		floaters.add("pain in eye");
		floaters.add("spots or clouds in vision");
		floaters.add("lacrimation");
		floaters.add("foreign body sensation in eye");
		floaters.add("itchiness of eye");
		floaters.add("eye burns or stings");
		
		bolesti.put("conjunctivitis",conjunctivitis);
		bolesti.put("blepharitis",blepharitis);
		bolesti.put("chronic_glaucoma",chronic_glaucoma);
		bolesti.put("cataract",cataract);
		bolesti.put("macular_degeneration", macular_degeneration);
		bolesti.put("dry_eye_of_unknown_cause",dry_eye_of_unknown_cause);
		bolesti.put("eye_alignment_disorder",eye_alignment_disorder);
		bolesti.put("corneal_abrasion",corneal_abrasion);
		bolesti.put("cornea_infection",cornea_infection);
		bolesti.put("retinal_detachment",retinal_detachment);
		bolesti.put("optic_neuritis",optic_neuritis);
		bolesti.put("iridocyclitis",iridocyclitis);
		bolesti.put("subconjunctival_hemorrhage",subconjunctival_hemorrhage);
		bolesti.put("floaters",floaters);
		return bolesti;
	}


}
