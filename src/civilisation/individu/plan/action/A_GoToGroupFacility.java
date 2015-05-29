package civilisation.individu.plan.action;

import java.util.ArrayList;

import turtlekit.kernel.Patch;
import civilisation.amenagement.TypeAmenagement;
import civilisation.group.Group;
import civilisation.individu.Humain;

public class A_GoToGroupFacility extends Action
{
	/**
	 * Va dans l'aménagement du groupe de l'individu
	 * @author brunoyun
	 */
	
	TypeAmenagement amenagement;
	
	public Action effectuer(Humain h)
	{	
		boolean doAction = false;
		//Patch pos = new Patch(0, 0);
		Patch pos = new Patch();
		pos.setCoordinates(0, 0);
		if (!h.getEsprit().getGroups().isEmpty())
		{
			for (Group grp : h.getEsprit().getGroups().keySet())
			{
				for (Humain grpH : grp.getMembers())
					if (grpH.getBuildings().containsKey(amenagement.getNom()))
					{
						pos = grpH.getBuildings().get(amenagement.getNom()).getPosition();
						doAction = true;
						break;
					}
				if (doAction)
					break;
			}
		}
		
		
		if (doAction)
		{
			//On est sur l'aménagement
			if(pos.x == h.xcor() && pos.y == h.ycor())
		 		return nextAction;
			h.face(pos);
			h.fd(1);
			return this;
		}
		else
			return nextAction;
	}
	
	/**
	 * Donne des infos sur l'action
	 */
	@Override
	public String getInfo() {
		return super.getInfo() + "Go to one of a group member facility.<html>";
	}
	
	@Override
	public void parametrerOption(OptionsActions option)
	{
		super.parametrerOption(option);
		if (option.getParametres().get(0).getClass() == TypeAmenagement.class)
			amenagement = (TypeAmenagement) option.getParametres().get(0);
	}
	
	@Override
	public ArrayList<String[]> getSchemaParametres(){
		
		if (schemaParametres == null)
		{
			schemaParametres = new ArrayList<String[]>();
			String[] attrName = {"**Amenagement**" , "Amenagement"};
			schemaParametres.add(attrName);
		}
		return schemaParametres;	
	}
}
