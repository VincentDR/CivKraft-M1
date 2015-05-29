package civilisation.individu.plan.action;

import java.util.ArrayList;

import civilisation.amenagement.Amenagement;
import civilisation.amenagement.Amenagement_Champ;
import civilisation.amenagement.TypeAmenagement;
import civilisation.individu.Humain;
import civilisation.inventaire.Objet;

/**
 *  D�truit l'am�nagement
 *  
 *  @param Created Amenagement ,l'amenagement a d�truire
 *  
 *  @return l'action suivante dans la liste d'actions de l'agent
 */

public class A_EraseAmenagement extends Action{

	TypeAmenagement amenagement;
	public Action effectuer(Humain h)
	{	
		Amenagement a = new Amenagement(h.getPatch(),h,amenagement);
		h.getPatch().getMark(amenagement.getNom().toLowerCase().toString());
		h.getBuildings().remove((amenagement.getNom().toLowerCase().toString()));
		h.getCommunaute().detruire(a,h);
		
		return nextAction;
	}
	
	@Override
	public void parametrerOption(OptionsActions option){
		super.parametrerOption(option);

		if (option.getParametres().get(0).getClass() == TypeAmenagement.class) {
			amenagement = (TypeAmenagement) option.getParametres().get(0);
		//	System.out.println(objet);
		}

	}
	

	/**
	 * Retourne la structure des param_tres.
	 * Permet de d_terminer la pr_sentation de la fen_tre de r_glages.
	 */
	@Override
	public ArrayList<String[]> getSchemaParametres(){
		
		if (schemaParametres == null){
			schemaParametres = new ArrayList<String[]>();
			String[] attrName = {"**Amenagement**" , "Amenagement"};

			schemaParametres.add(attrName);

		}
		return schemaParametres;	
	}
}
