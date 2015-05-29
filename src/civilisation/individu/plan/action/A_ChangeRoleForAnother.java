package civilisation.individu.plan.action;

import java.util.ArrayList;

import civilisation.group.Group;
import civilisation.group.GroupAndRole;
import civilisation.group.GroupModel;
import civilisation.individu.Humain;
import civilisation.individu.cognitons.TypeCogniton;

public class A_ChangeRoleForAnother extends Action
{
	GroupModel group;
	String role;
	
	@Override
	public Action effectuer(Humain h)
	{
		Group gr = h.getEsprit().getConcreteGroup(group);
		
		if (gr != null) {
			
			gr.leaveGroup(h.getEsprit());
			//On s'assure que l'agent est au moins dans le groupe.
			//System.out.println("Un chef est née pour le groupe" + gr);
			h.getEsprit().joinRestrictiveGroup(gr, role);
			
			
		}
		
		return nextAction;
	}

	@Override
	public String getInfo()
	{
		return super.getInfo() + " Change the role of a agent for another (The agent has to be in a group, does nothing if the agent is already in that role).<html>";
	}
	
	@Override
	public void parametrerOption(OptionsActions option)
	{
		super.parametrerOption(option);
		
		if (option.getParametres().get(0).getClass() == GroupAndRole.class) {
			group = ((GroupAndRole) option.getParametres().get(0)).getGroupModel();
			role = ((GroupAndRole) option.getParametres().get(0)).getRole();
		}
		
	}
	
	@Override
	public ArrayList<String[]> getSchemaParametres()
	{	
		if (schemaParametres == null)
		{
			schemaParametres = new ArrayList<String[]>();
			
			String[] role1 = {"**GroupAndRole**" , "Change to that role :"};
			schemaParametres.add(role1);
		}
		return schemaParametres;	
	}
}
