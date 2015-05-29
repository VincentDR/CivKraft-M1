package civilisation.individu.plan.action;

import java.util.ArrayList;


import civilisation.Configuration;
import civilisation.individu.Humain;
import civilisation.inventaire.Objet;

/**
 *  Ajoute un objet a l'inventaire de l'agent 
 *  @param Changed Object , l'objet a ajouter
 *  @param Integer , le nombre d'objets a ajouter
 *  
 *  @return l'action suivante dans la liste d'actions de l'agent
 */

public class A_AddItem extends Action{

	String ObjectName;
	Integer variation;
	
	
	public Action effectuer(Humain h) {
		// Old code :
		/*for (int i = 0 ; i < variation ; i++) {
			h.getInventaire().addObjets(Configuration.getObjetByName(ObjectName),1);
		}*/
		if (variation > 0)
			h.getInventaire().addObjets(Configuration.getObjetByName(ObjectName),variation);
		return nextAction;
	}
	
	@Override
	public void parametrerOption(OptionsActions option){
		super.parametrerOption(option);
//		System.out.println("Yolo");
		if (option.getParametres().get(0).getClass().equals(Objet.class)) {
			ObjectName = ((Objet) option.getParametres().get(0)).getNom();
//			System.out.println(ObjectName);
		} 
		else if (option.getParametres().get(0).getClass() == Integer.class) {
			variation = (Integer) option.getParametres().get(0);
		}

	}
	
	@Override
	public ArrayList<String[]> getSchemaParametres(){
		
		if (schemaParametres == null){
			schemaParametres = new ArrayList<String[]>();
			String[] attrName = {"**Objet**" , "Modified object"};
			String[] n = {"**Integer**" , "n", "-10" , "10" , "1"};

			schemaParametres.add(attrName);
			schemaParametres.add(n);

		}
		return schemaParametres;	
	}
	
	
	@Override
	public String getInfo() {
		return super.getInfo() + " Add an object to the inventory.<html>";
	}

}
