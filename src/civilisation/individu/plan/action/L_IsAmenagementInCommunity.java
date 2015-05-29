package civilisation.individu.plan.action;

import java.util.ArrayList;

import javax.swing.ImageIcon;

import civilisation.Configuration;
import civilisation.amenagement.TypeAmenagement;
import civilisation.individu.Humain;

public class L_IsAmenagementInCommunity extends LAction{

	TypeAmenagement objet;

	
	@Override
	public Action effectuer(Humain h) {
		if (nextAction != null) h.getEsprit().getActions().push(nextAction);
		Action a;
		if (h.getCommunaute().getBatiments().containsKey(objet.getNom())) {
			a = listeActions.get(0).effectuer(h);
		} else {
			a = listeActions.get(1).effectuer(h);
		}
		return a;
		
	}

	@Override
	public ImageIcon getIcon(){
		return Configuration.getIcon("processor.png");
	}
	
	@Override
	public int getNumberActionSlot(){
		return 2;
	}
	
	@Override
	public String getInfo() {
		return super.getInfo() + " Play first action if comunity owns an amenagement,<br> the second otherwise.<html>";
	}
	
	@Override
	public void parametrerOption(OptionsActions option){
		super.parametrerOption(option);
		
		if (option.getParametres().get(0).getClass() == TypeAmenagement.class) {
			objet = (TypeAmenagement) option.getParametres().get(0);
	//		System.out.println(objet.getNom());
		}
	}
	
	@Override
	public ArrayList<String[]> getSchemaParametres(){
		
		if (schemaParametres == null){
			schemaParametres = new ArrayList<String[]>();
			
			
			String[] cog = {"**Amenagement**" , "Facility"};
			
			schemaParametres.add(cog);


		}
		return schemaParametres;	
	}
	
	public boolean internActionsAreLinked() {
		return false;
	}
}
