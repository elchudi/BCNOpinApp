package bcn.appjam.bcnopinapp.beans;

import java.util.ArrayList;
import java.util.List;

public class PetitionsStatic {

	public static List<SignPetition> petitions = new ArrayList<SignPetition>();

	public void addPetition(SignPetition pet){
		this.petitions.add(pet);
		
		
	}
}
