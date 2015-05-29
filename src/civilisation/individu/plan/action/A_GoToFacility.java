package civilisation.individu.plan.action;

import java.util.ArrayList;

import turtlekit.kernel.Patch;
import civilisation.amenagement.Amenagement;
import civilisation.amenagement.TypeAmenagement;
import civilisation.individu.Humain;

/**
 * 
 * @author DTEAM
 * 
 * L'agent se dirige vers l'aménagement qu'il possède
 * 
 * @param Created Amenagement l'aménagement cible
 *
 */

public class A_GoToFacility extends Action{

	TypeAmenagement amenagement;
	public Action effectuer(Humain h)
	{	
		Patch pos;
		if(h.getBuildings().containsKey(amenagement.getNom()))
		{
		 	pos = h.getBuildings().get(amenagement.getNom()).getPosition();
		 	if(pos.x == h.xcor() && pos.y == h.ycor())
		 	{
		 		return nextAction;
		 	}
		 	else
		 	{
		 		h.face(h.getBuildings().get(amenagement.getNom()).getPosition());
				h.fd(1);
				return this;
		 	}
		}
		else
		{
			return nextAction;
		}
		
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
}
