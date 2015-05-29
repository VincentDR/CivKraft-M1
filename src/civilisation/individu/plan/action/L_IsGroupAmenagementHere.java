package civilisation.individu.plan.action;

import java.util.ArrayList;
import javax.swing.ImageIcon;

import civilisation.Configuration;
import civilisation.amenagement.TypeAmenagement;
import civilisation.group.Group;
import civilisation.individu.Humain;

public class L_IsGroupAmenagementHere extends LAction
{
	TypeAmenagement objet;
	
	@Override
	public Action effectuer(Humain h)
	{
		if (nextAction != null)
			h.getEsprit().getActions().push(nextAction);
		Action a;
		
		boolean doAction = false;
		if (!h.getPatch().isMarkPresent(objet.getNom().toLowerCase().toString()))
			if (!h.getEsprit().getGroups().isEmpty())
				for (Group grp : h.getEsprit().getGroups().keySet())
				{
					for (Humain grpH : grp.getMembers())
						if (grpH.getBuildings().containsKey(objet.getNom()))
						{
							doAction = true;
							break;
						}
					if (doAction)
						break;
				}

		if (doAction)
			a = listeActions.get(0).effectuer(h);
		else
			a = listeActions.get(1).effectuer(h);
		return a;
	}

	@Override
	public ImageIcon getIcon()
	{
		return Configuration.getIcon("processor.png");
	}
	
	@Override
	public int getNumberActionSlot()
	{
		return 2;
	}
	
	@Override
	public String getInfo()
	{
		return super.getInfo() + " Play first action if there is an amenagement which belongs to the agent's group,<br> the second otherwise.<html>";
	}
	
	@Override
	public void parametrerOption(OptionsActions option)
	{
		super.parametrerOption(option);
		
		if (option.getParametres().get(0).getClass() == TypeAmenagement.class)
			objet = (TypeAmenagement) option.getParametres().get(0);
	}
	
	@Override
	public ArrayList<String[]> getSchemaParametres()
	{
		if (schemaParametres == null)
		{
			schemaParametres = new ArrayList<String[]>();
				
			String[] cog = {"**Amenagement**" , "amenagement"};
			schemaParametres.add(cog);
		}
		return schemaParametres;	
	}

	public boolean internActionsAreLinked()
	{
		return false;
	}
}
