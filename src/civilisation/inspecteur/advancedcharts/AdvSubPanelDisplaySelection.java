package civilisation.inspecteur.advancedcharts;

import I18n.I18nList;

import civilisation.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

public class AdvSubPanelDisplaySelection extends JPanel{

	JButton civilisations = new JButton(I18nList.CheckLang("Civilisations"));

	HashMap<String, JCheckBox> choiceCiv = new HashMap<>();
	
	public AdvSubPanelDisplaySelection(AdvPanelPopulation advPanelPopulation)
	{
		updateDialogPanels();
		civilisations.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JCheckBox[] array = new JCheckBox[choiceCiv.values().size()]; 
				array = choiceCiv.values().toArray(array);

				JOptionPane.showMessageDialog(null,array, I18nList.CheckLang("choose Civilisations"),JOptionPane.PLAIN_MESSAGE);
			}
		});
		this.add(civilisations);
		
		
	}

	private void updateDialogPanels() {
		for(Civilisation c:Configuration.civilisations)
		{
			if(choiceCiv.get(c.getNom()) == null)
			{
				choiceCiv.put(c.getNom(), new JCheckBox(c.getNom()));
			}
		}
		
	}
	
}
