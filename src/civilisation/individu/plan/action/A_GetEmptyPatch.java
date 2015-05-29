package civilisation.individu.plan.action;

import civilisation.Configuration;
import civilisation.individu.Humain;

public class A_GetEmptyPatch extends Action{

	public Action effectuer(Humain h)
	{
		int x = Integer.MAX_VALUE;
		int y = Integer.MAX_VALUE;
		double distance = Double.MAX_VALUE;
		boolean test = false;
		int rayon = 0;
		while(rayon < Configuration.VisionRadius)
		{
			for(int i = -rayon; i <= rayon;++i)
			{
				for(int j = -rayon; j <= rayon;++j)
				{
					for(int k = 0; k < Configuration.amenagements.size();++k)
					{
						if(h.getPatchAt(i, j ).isMarkPresent( Configuration.amenagements.get(k).getNom().toLowerCase().toString()))
						{	
							test = true;
						}
					}
					if(!test)
					{
						double	distance2 = Math.abs(i) + Math.abs(j);
						if(distance2 < distance)
						{
							x = i;
							y = j;
							distance = distance2;
							h.setCible(h.getPatchAt(x,y));
							return nextAction;
						}
					}
					test = false;
				}
			}
			rayon++;
		}
		
		
		return nextAction;
	}
	
	@Override
	public String getInfo() {
		return super.getInfo() + " Met la cible de Allervers sur le patch le plus proche dans le champ de vision ne contenant aucun amenagement.<html>";
	}
}
