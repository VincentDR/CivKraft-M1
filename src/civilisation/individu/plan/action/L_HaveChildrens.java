package civilisation.individu.plan.action;

import javax.swing.ImageIcon;

import civilisation.Configuration;
import civilisation.individu.Humain;

public class L_HaveChildrens extends LAction{

	public Action effectuer(Humain h)
	{
		if (nextAction != null) h.getEsprit().getActions().push(nextAction);
		Action a;
		if (h.getEnfants().size() > 0) {
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
		return super.getInfo() + " Play first action if the agent have childrens,<br> the second otherwise.<html>";
	}
	
	
	public boolean internActionsAreLinked() {
		return false;
	}
}
