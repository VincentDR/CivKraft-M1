package civilisation.inspecteur.advancedcharts;

import javax.swing.JPanel;

public class AdvPanelPopulation extends JPanel{

	public AdvPanelPopulation()
	{
		this.add(new AdvSubPanelDisplaySelection(this));
	}
}
