package civilisation.individu.plan.action;



import java.awt.Color;

import turtlekit.kernel.Patch;
import civilisation.Configuration;
import civilisation.amenagement.Amenagement;
import civilisation.individu.Humain;

public class A_DieAndRemoveFacilities extends Action{

	@Override
	public Action effectuer(Humain h) {
	/*		for(int j = 0; j < Configuration.amenagements.size();++j)
			{
				if(h.getBuildings().containsKey(Configuration.amenagements.get(j).getNom()))
				{
					Patch pos = h.getBuildings().get(Configuration.amenagements.get(j).getNom()).getPosition();
					h.getPatchAt(pos.x - h.xcor(), pos.y - h.ycor()).getMark(Configuration.amenagements.get(j).getNom().toLowerCase().toString());
				}
				
			}*/
		
		
		for(Amenagement a : h.getBuildings().values())
		{
			Amenagement atemp = (Amenagement)a.getPosition().getMark(a.getType().getNom().toLowerCase().toString());
			/*
			if(atemp == null)
			{
				System.out.println("ACTION  A_DieAndRemoveFacilities \n >> l'am�nagement " + a.getType().getNom().toLowerCase().toString() + "n'a pas �t� trouv� su son patch");
				if(a.getPosition().equals(h.getPatch()))
				{
					System.out.println(">>agent sur le patch");
				}
				else
				{
					System.out.println(">>agent hors du patch <<");
				}
			}
			*/
			h.getCommunaute().detruire(a, h);
		}
		
		h.die();
		
		return nextAction;
	}

	
	@Override
	public String getInfo() {
		return super.getInfo() + " Kill the agent. Remove his facilities<html>";
	}

	
	
}
