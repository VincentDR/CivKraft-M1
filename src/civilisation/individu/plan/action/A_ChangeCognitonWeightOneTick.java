package civilisation.individu.plan.action;

import java.util.ArrayList;

import civilisation.individu.Humain;
import civilisation.individu.cognitons.TypeCogniton;

public class A_ChangeCognitonWeightOneTick extends Action
{
	TypeCogniton cogniton;
	Double change;

	@Override
	public Action effectuer(Humain h)
	{
		h.getEsprit().addWeightToCognitonOneTick(cogniton, change);
		
		return nextAction;
	}

	@Override
	public String getInfo()
	{
		return super.getInfo() + " Change the weight for a cogniton for one tick.<br>If agent doesn't have the cogniton, nothing is done.<html>";
	}
	
	@Override
	public void parametrerOption(OptionsActions option)
	{
		super.parametrerOption(option);
		
		if (option.getParametres().get(0).getClass().equals(TypeCogniton.class))
		{
			cogniton = (TypeCogniton) option.getParametres().get(0);
		}
		if (option.getParametres().get(0).getClass().equals(Double.class))
		{
			change = (Double) option.getParametres().get(0);
		}
	}
	
	@Override
	public ArrayList<String[]> getSchemaParametres()
	{	
		if (schemaParametres == null)
		{
			schemaParametres = new ArrayList<String[]>();
			
			String[] c = {"**Cogniton**" , "Cogniton"};
			String[] n = {"**Double**" , "n", "-10.0" , "10.0" , "0.1","100"};

			schemaParametres.add(c);
			schemaParametres.add(n);
		}
		return schemaParametres;	
	}
}
