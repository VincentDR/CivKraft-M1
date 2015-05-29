package civilisation.individu.plan.action;

import java.util.ArrayList;

import civilisation.individu.Humain;
import civilisation.individu.cognitons.TypeCogniton;

/**
 * Contrairement à A_ChangeCognitonWeight, la valeur n'est pas ajoutée mais remplacée
 * @author Bruno VISSE
 *
 */

public class A_ReplaceCognitonWeight extends Action
{
	TypeCogniton cogniton;
	Double change;

	@Override
	public Action effectuer(Humain h)
	{
		if (h.getEsprit().ownCogniton(cogniton))
			h.getEsprit().getCognitonOfType(cogniton).setWeigth(change);
		else
			h.getEsprit().addWeightToCogniton(cogniton, change);
		
		return nextAction;
	}

	@Override
	public String getInfo()
	{
		return super.getInfo() + " Replace the weight for a cogniton.<br>If agent doesn't have the cogniton, add it.<html>";
	}
	
	@Override
	public void parametrerOption(OptionsActions option){
		super.parametrerOption(option);
		
		if (option.getParametres().get(0).getClass().equals(TypeCogniton.class))
			cogniton = (TypeCogniton) option.getParametres().get(0);
		if (option.getParametres().get(0).getClass().equals(Double.class))
			change = (Double) option.getParametres().get(0);
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
