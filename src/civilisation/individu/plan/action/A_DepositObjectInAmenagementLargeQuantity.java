package civilisation.individu.plan.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.swing.ImageIcon;

import turtlekit.kernel.Patch;
import turtlekit.kernel.Turtle;
import civilisation.Configuration;
import civilisation.amenagement.Amenagement;
import civilisation.amenagement.TypeAmenagement;
import civilisation.group.Group;
import civilisation.individu.Humain;
import civilisation.inventaire.Objet;

/**
 *  Ajoute des objet a l'inventaire de l'amenagement 
 *  (il faut que celui qui possède l'aménagement soit en vie, on ne travaille pas avec la qualité)
 *  @author Bruno YUN
 */

public class A_DepositObjectInAmenagementLargeQuantity extends Action
{

	Objet objet;
	Integer variation;
	TypeAmenagement a;
	
	public Action effectuer(Humain h)
	{
		if (variation != 0)
		{
				
			//Trouvons l'aménagement sur lequel nous sommes
			boolean doAction = false;
			//Patch pos = new Patch(0, 0);
			Patch pos = new Patch();
			pos.setCoordinates(0, 0);
			Amenagement cible=null;
			if (!h.getEsprit().getGroups().isEmpty())
			{
				for (Group grp : h.getEsprit().getGroups().keySet())
				{
					for (Humain grpH : grp.getMembers())
						if (grpH.getBuildings().containsKey(a.getNom()))
						{
							pos = grpH.getBuildings().get(a.getNom()).getPosition();
							if(pos.x == h.xcor() && pos.y == h.ycor())
							{
								cible=grpH.getBuildings().get(a.getNom());
								doAction = true;
								break;
							}	
						}
					if (doAction)
						break;
				}
			}
			/*if(cible != null && cible.getInventaire() != null)
				System.out.println("J'ai trouvé l'aménagement");
			else
				System.out.println("Pas d'aménagement"); */
			
			
				
			if(h.getInventaire().getListeObjets().get(objet.getNom()) != null && h.getInventaire().getListeObjets().get(objet.getNom()).get(1)!= 0)
			{
					
				
				int nombreObjet = h.getInventaire().getListeObjets().get(objet.getNom()).get(1);
				
				//System.out.println("Je suis "+ h.getID() + " et j'ai "+ h.getInventaire().getListeObjets().get(objet.getNom()).get(1));
				
				if(cible != null)
				{
					
					//h.getInventaire().deleteObjets(objet,variation);
				
					if (nombreObjet < variation)
					{
						cible.getInventaire().addObjets(objet, nombreObjet);
						
						//Il faut enlever les objets de l'inventaire de l'agent
						HashMap<String, HashMap<Integer, Integer>> Bag = h.getInventaire().getListeObjets();
						HashMap<Integer, Integer> temp= new HashMap<Integer, Integer>();
						temp.put(1,0);
						Bag.put(objet.getNom(), temp);
						h.getInventaire().setListeObjets(Bag);
					}
					else
					{
						cible.getInventaire().addObjets(objet, variation);
						
						//Il faut enlever les objets de l'inventaire de l'agent
						HashMap<String, HashMap<Integer, Integer>> Bag = h.getInventaire().getListeObjets();
						HashMap<Integer, Integer> temp= new HashMap<Integer, Integer>();
						temp.put(1,nombreObjet-variation);
						Bag.put(objet.getNom(), temp);
						h.getInventaire().setListeObjets(Bag);
					}
				
				}
					
				
				
					//System.out.println("J' avais "+ nombreObjet + "et maintenant j'ai "+ h.getInventaire().getListeObjets().get(objet.getNom()).get(1));
				} 
			
			
		}
		return nextAction;
	}
	
	@Override
	public void parametrerOption(OptionsActions option)
	{
		super.parametrerOption(option);
		if (option.getParametres().get(0).getClass().equals(Objet.class)){
			objet = (Objet) option.getParametres().get(0);
		}else if (option.getParametres().get(0).getClass() == Integer.class)
			this.variation = (Integer) option.getParametres().get(0);
		else if (option.getParametres().get(0).getClass() == TypeAmenagement.class)
			this.a = (TypeAmenagement) option.getParametres().get(0);
		
	}
	
	@Override
	public ArrayList<String[]> getSchemaParametres(){
		
		if (schemaParametres == null){
			schemaParametres = new ArrayList<String[]>();
			String[] attr = {"**Objet**" , "objetToCompare"};
			String[] n = {"**Integer**" , "n", "-10" , "100" , "1"};
			String[] ameName = {"**Amenagement**" , "Amenagement"};

			schemaParametres.add(attr);
			schemaParametres.add(n);
			schemaParametres.add(ameName);

		}
		return schemaParametres;	
	}
	
	
	@Override
	public String getInfo()
	{
		return super.getInfo() + " Add n object (large quantity and quality 1 (default)) to the group amenagement.<html>";
	}

	public boolean isDeprecated()
	{
		return false;
	}
}


