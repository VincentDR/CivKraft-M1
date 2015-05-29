package civilisation.individu.plan.action;

import java.util.ArrayList;

import civilisation.Configuration;
import civilisation.amenagement.Amenagement;
import civilisation.amenagement.Amenagement_Champ;
import civilisation.amenagement.TypeAmenagement;
import civilisation.individu.Humain;
import civilisation.inventaire.Objet;

/**
 *  Cr�e un am�nagement sur le patch
 *  @param Created Amenagement ,l'amenagement a cr�er
 *  
 *  @return l'action suivante dans la liste d'actions de l'agent
 */

public class A_CreateFacility extends Action{

	TypeAmenagement amenagement;
	public Action effectuer(Humain h)
	{
		if(!h.getPatch().isMarkPresent(amenagement.getNom().toLowerCase().toString()))
		{
			if(amenagement.getRecette().size() > 0)
			{
				boolean test = true;
				for(int i = 0; i < amenagement.getRecette().size(); ++i)
				{
					if(h.getInventaire().possede(amenagement.getRecette().get(i)) < amenagement.getNombre().get(i) )
					{
						test = false;
					}
				}
				if(test)
				{
					Amenagement a = new Amenagement(h.getPatch(),h,amenagement);
					for(int i = 0; i < Configuration.amenagements.size();++i)
					{
						if(Configuration.amenagements.get(i).getNom().equals(a.getType().getNom()))
						{
							h.getPatch().dropMark(Configuration.amenagements.get(i).getNom().toLowerCase().toString(), a);
						}
					}
					
					h.getCommunaute().construire(a);
					h.getBuildings().put(amenagement.getNom(), a);
					for(int i = 0; i < amenagement.getRecette().size(); ++i)
					{
						h.getInventaire().deleteObjets(amenagement.getRecette().get(i), amenagement.getNombre().get(i));
					}
				}
			}
			else
			{
				Amenagement a = new Amenagement(h.getPatch(),h,amenagement);
				for(int i = 0; i < Configuration.amenagements.size();++i)
				{
					if(Configuration.amenagements.get(i).getNom().equals(a.getType().getNom()))
					{
						h.getPatch().dropMark(Configuration.amenagements.get(i).getNom().toLowerCase().toString(), a);
					}
				}
				
				h.getCommunaute().construire(a);
				h.getBuildings().put(amenagement.getNom(), a);
			}
			
		}

		
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
			String[] attrName = {"**Amenagement**" , "Facility"};

			schemaParametres.add(attrName);

		}
		return schemaParametres;	
	}
	
	public String getInfo() {
		return super.getInfo() + " Create a facility on the patch if the agent own objects from recipe.<html>";
	}
}
