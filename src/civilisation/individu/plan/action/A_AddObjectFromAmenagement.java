package civilisation.individu.plan.action;

import java.util.ArrayList;

import civilisation.Configuration;
import civilisation.amenagement.Amenagement;
import civilisation.amenagement.TypeAmenagement;
import civilisation.individu.Humain;
import civilisation.inventaire.Objet;

public class A_AddObjectFromAmenagement extends Action
{
	String ObjectName;
	Integer variation;
	TypeAmenagement a;
	
	public Action effectuer(Humain h)
	{
		if (variation != 0)
		{
			try
			{
				if (((Amenagement)h.getPatch().getMark(a.getNom())).getInventaire().possede(new Objet(ObjectName)) < variation)
				{
					((Amenagement)h.getPatch().getMark(a.getNom())).getInventaire().deleteObjets(Configuration.getObjetByName(ObjectName), ((Amenagement)h.getPatch().getMark(a.getNom())).getInventaire().possede(new Objet(ObjectName)));
					h.getInventaire().addObjets(Configuration.getObjetByName(ObjectName),((Amenagement)h.getPatch().getMark(a.getNom())).getInventaire().possede(new Objet(ObjectName)));
				}
				else
				{
					((Amenagement)h.getPatch().getMark(a.getNom())).getInventaire().deleteObjets(Configuration.getObjetByName(ObjectName), this.variation);
					h.getInventaire().addObjets(Configuration.getObjetByName(ObjectName),this.variation);
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
