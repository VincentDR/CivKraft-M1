package civilisation.individu.plan.action;

import java.util.ArrayList;

import civilisation.Configuration;
import civilisation.amenagement.Amenagement;
import civilisation.amenagement.TypeAmenagement;
import civilisation.individu.Humain;
import civilisation.inventaire.Objet;

/**
 *  Ajoute un objet a l'inventaire de l'amenagement
 *  @author Bruno VISSE
 */

public class A_AddObjectInAmenagement extends Action
{

	String ObjectName;
	Integer variation;
	TypeAmenagement a;
	
	public Action effectuer(Humain h)
	{
		/* Useless loop ?
		for (int i = 0 ; i < variation ; i++)
		{
			((Amenagement)h.getPatch().getMark(a.getNom())).getInventaire().addObjets(Configuration.getObjetByName(ObjectName), 1);
			h.getInventaire().deleteObjets(Configuration.getObjetByName(ObjectName),1);
		}*/
		/*if (((Amenagement)h.getPatch().getMark(a.getNom())) != null && variation != 0)
		{
			if (h.getInventaire().getListeObjets() != null)
			{
				if (h.getInventaire().getListeObjets().get(ObjectName) != null && ((Amenagement)h.getPatch().getMark(a.getNom())).getInventaire() != null)
				{
					if (h.getInventaire().possede(new Objet(ObjectName)) < variation)
					{
						((Amenagement)h.getPatch().getMark(a.getNom())).getInventaire().addObjets(Configuration.getObjetByName(ObjectName), h.getInventaire().possede(new Objet(ObjectName)));
						h.getInventaire().deleteObjets(Configuration.getObjetByName(ObjectName),h.getInventaire().getListeObjets().get(ObjectName).size());
					}
					else
					{
						if (((Amenagement)h.getPatch().getMark(a.getNom())).getInventaire() != null)
						{
							((Amenagement)h.getPatch().getMark(a.getNom())).getInventaire().addObjets(Configuration.getObjetByName(ObjectName), this.variation);
							h.getInventaire().deleteObjets(Configuration.getObjetByName(ObjectName),this.variation);
						}
					}
				}
			}
		}*/
		if (variation != 0)
		{
			try
			{
				if (h.getInventaire().possede(new Objet(ObjectName)) < variation)
				{
					((Amenagement)h.getPatch().getMark(a.getNom())).getInventaire().addObjets(Configuration.getObjetByName(ObjectName), h.getInventaire().possede(new Objet(ObjectName)));
					h.getInventaire().deleteObjets(Configuration.getObjetByName(ObjectName),h.getInventaire().getListeObjets().get(ObjectName).size());
				}
				else
				{
					((Amenagement)h.getPatch().getMark(a.getNom())).getInventaire().addObjets(Configuration.getObjetByName(ObjectName), this.variation);
					h.getInventaire().deleteObjets(Configuration.getObjetByName(ObjectName),this.variation);
				}
			}
			catch(java.lang.NullPointerException e)
			{
				return nextAction;
			}
		}
		return nextAction;
	}
	
	@Override
	public void parametrerOption(OptionsActions option)
	{
		super.parametrerOption(option);
		if (option.getParametres().get(0).getClass().equals(Objet.class))
			this.ObjectName = ((Objet) option.getParametres().get(0)).getNom();
		else if (option.getParametres().get(0).getClass() == Integer.class)
			this.variation = (Integer) option.getParametres().get(0);
		else if (option.getParametres().get(0).getClass() == TypeAmenagement.class)
			this.a = (TypeAmenagement) option.getParametres().get(0);
	}
	
	@Override
	public ArrayList<String[]> getSchemaParametres(){
		
		if (schemaParametres == null){
			schemaParametres = new ArrayList<String[]>();
			String[] attrName = {"**Objet**" , "Modified object"};
			String[] n = {"**Integer**" , "n", "-10" , "10" , "1"};
			String[] ameName = {"**Amenagement**" , "Amenagement"};

			schemaParametres.add(attrName);
			schemaParametres.add(n);
			schemaParametres.add(ameName);

		}
		return schemaParametres;	
	}
	
	
	@Override
	public String getInfo()
	{
		return super.getInfo() + " Add n object to the amenagement.<html>";
	}

	public boolean isDeprecated()
	{
		return false;
	}
}
