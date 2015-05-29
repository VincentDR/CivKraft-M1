package civilisation.individu.plan.action;

import civilisation.individu.Humain;

public class A_Birth extends Action{

	@Override
	public Action effectuer(Humain h) {
		Humain child = new Humain(h.getCiv(), h.getCommunaute(),h,h);
		h.createTurtle(child);
		h.getEnfants().add(child);
		child.moveTo(h.getX(), h.getY());
		return nextAction;
	}

	
	@Override
	public String getInfo() {
		return super.getInfo() + "Create a new agent.<html>";
	}

	
	
}
