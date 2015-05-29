package civilisation.inspecteur.advancedcharts;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;

import I18n.I18nList;


public class ChartWindow extends javax.swing.JFrame {
	JTabbedPane contentPane;
	  


	  public ChartWindow(String nomFenetre){
		    super(nomFenetre);
		    this.setSize(350,550);

		    contentPane = new JTabbedPane();

		    contentPane.addTab(I18nList.CheckLang("Population"), new AdvPanelPopulation());
		    contentPane.addTab(I18nList.CheckLang("Mind"), new AdvPanelMind());
		    contentPane.addTab(I18nList.CheckLang("Selection"), new AdvPanelSelection());
		    this.setTitle(nomFenetre);
		    this.setVisible(true);
		    this.setContentPane(contentPane);
		    }
}
