package civilisation.individu.plan.action;

import java.util.ArrayList;

import javax.swing.ImageIcon;

import civilisation.Configuration;
import civilisation.ItemPheromone;
import civilisation.individu.Humain;
import civilisation.inventaire.Objet;

public class L_CompareNombreObjets extends LAction{
	
	Objet objet;
	Comparator comp;
	Double d;
	
	@Override
	public Action effectuer(Humain h) {
		
		if (nextAction != null) h.getEsprit().getActions().push(nextAction);
		Action a;
		
		int numberObjectsToCompare=0;
		if(h.getInventaire().getListeObjets().get(objet.getNom()) != null)
			numberObjectsToCompare = h.getInventaire().getListeObjets().get(objet.getNom()).get(1);
		//Attention cela ne fonctionne que si l'objet est de qualité 1 (par défaut)
		
		if (comp.compare((double) numberObjectsToCompare, d)) {
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
		return super.getInfo() + " Compare the number of a specific object owned by the agent.<html>";
	}
	
	@Override
	public void parametrerOption(OptionsActions option){
		super.parametrerOption(option);
//		System.out.println("option "+option.getParametres().get(0).getClass());
		if (option.getParametres().get(0).getClass().equals(Objet.class)){
			objet = (Objet) option.getParametres().get(0);
		} else
		if (option.getParametres().get(0).getClass().equals(Comparator.class)){
			comp = (Comparator) option.getParametres().get(0);
		} else
		if (option.getParametres().get(0).getClass().equals(Double.class)){
			d = (Double) option.getParametres().get(0);
		}
	}
	
	@Override
	public ArrayList<String[]> getSchemaParametres(){
		
		if (schemaParametres == null){
			schemaParametres = new ArrayList<String[]>();
			
			
			String[] attr = {"**Objet**" , "objetToCompare"};
			String[] comp = {"**Comparator**" , "comparator"};		
			String[] val = {"**Double**" , "n", "-100.0" , "100.0" , "1.0", "100"};
			
			schemaParametres.add(attr);
			schemaParametres.add(comp);
			schemaParametres.add(val);

		}
		return schemaParametres;	
	}
	
	public boolean internActionsAreLinked() {
		return false;
	}
}
