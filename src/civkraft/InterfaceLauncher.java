package civkraft;

import java.awt.AWTEvent;
import java.awt.BorderLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.util.concurrent.Semaphore;

import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.filechooser.FileNameExtensionFilter;

import I18n.I18nList;

import civilisation.CivLauncher;
import madkit.kernel.Madkit;
import turtlekit.kernel.TurtleKit;

public class InterfaceLauncher extends JFrame implements MouseListener{
	
	private static final long serialVersionUID = 1L;
	private JPanel civkraftPanelSolo;
	private JPanel civkraftPanelMulti;

	private JPanel choixLang;

	private JButton civkraftBoutonSolo;
	private JButton civkraftBoutonHeberger;
	private JButton civkraftBoutonRejoindre;
	private JLabel civkraftTextSolo;
	private JLabel civkraftTextMulti;
	private JPanel civkraftSuperPanel;
	private Semaphore sema;
	private int choix;

	protected JComboBox langBox;
	protected JButton valider;
	String[] elemLang = new String[]{"Eng","Fr","Rus"};

	public InterfaceLauncher(Semaphore s, int c) throws HeadlessException {
		super();
		sema = s;
		choix = c;
		
		civkraftPanelSolo = new JPanel(new BorderLayout());
		civkraftPanelMulti = new JPanel(new BorderLayout());

		choixLang =  new JPanel(new BorderLayout());
		//valider = new JButton("Valider");
		civkraftBoutonSolo = new JButton(I18nList.CheckLang("Solo / Editeur"));
		civkraftBoutonHeberger = new JButton(I18nList.CheckLang("Heberger une partie"));
		civkraftBoutonRejoindre = new JButton(I18nList.CheckLang("Rejoindre une partie"));
		civkraftTextSolo = new JLabel("Metaciv", JLabel.CENTER);
		civkraftTextMulti = new JLabel(I18nList.CheckLang("Civkraft : Jeu multijoueur"), JLabel.CENTER);
		civkraftSuperPanel = new JPanel();
		langBox = new JComboBox(elemLang);
		
		
		civkraftBoutonSolo.addMouseListener(this);
		civkraftBoutonHeberger.addMouseListener(this);
		civkraftBoutonRejoindre.addMouseListener(this);
		//valider.addMouseListener(this);

		
		civkraftPanelSolo.add(civkraftTextSolo, BorderLayout.NORTH);
		civkraftPanelSolo.add(civkraftBoutonSolo, BorderLayout.SOUTH);
		
		civkraftPanelMulti.add(civkraftTextMulti, BorderLayout.NORTH);
		
		civkraftPanelMulti.add(civkraftBoutonHeberger, BorderLayout.WEST);
		civkraftPanelMulti.add(civkraftBoutonRejoindre, BorderLayout.EAST);
		
		//choixLang.add(valider, BorderLayout.WEST);
		choixLang.add(langBox, BorderLayout.CENTER);
		
		civkraftSuperPanel.add(civkraftPanelSolo, BorderLayout.NORTH);
		civkraftSuperPanel.add(civkraftPanelMulti, BorderLayout.CENTER);
		civkraftSuperPanel.add(choixLang, BorderLayout.SOUTH);
		
		this.add(civkraftSuperPanel, BorderLayout.CENTER);
		this.setTitle("Metaciv");
		this.setSize(360,180);
		this.setLocationRelativeTo(null);
	}
	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		if(arg0.getSource().equals(civkraftBoutonSolo)){
			
			String s = langBox.getSelectedItem().toString();
			//I18nList.infoLang = s;
			//System.out.println("actionPerdormed : "+ s);

			this.dispose();			
			//this.setVisible(false);
			setChoix(1);
			sema.release();
		}
		if(arg0.getSource().equals(civkraftBoutonHeberger)){

			String s = langBox.getSelectedItem().toString();
			//I18nList.infoLang = s;
			//System.out.println("actionPerdormed : "+ s);
	
			this.dispose();
			setChoix(2);
			sema.release();
			// Il faudra passer la semaphore dans le constructeur
			// Ne pas oublier de la release ensuite pour debloquer le main de CivLauncher.
			//InterfaceServeur iS = new InterfaceServeur(sema,512,256);
			//iS.setVisible(true);
			//sema.release();
			/*new Madkit(
					"--network true"
					,"--desktop false"
					,"--launchAgents"
					,Serveur.class.getName() + ",true,1;"
					);*/
		}
		if(arg0.getSource().equals(civkraftBoutonRejoindre)){

			String s = langBox.getSelectedItem().toString();
			//I18nList.infoLang = s;
			//System.out.println("actionPerdormed : "+ s);

			this.dispose();
			setChoix(3);
			sema.release();
			// Il faudra passer la semaphore dans le constructeur
			// Ne pas oublier de la release ensuite pour debloquer le main de CivLauncher.
			//Client c = new Client(sema, 512,256);
			/*new Madkit 
			( 
					"--network true"
					,"--desktop false"
					,"--launchAgents"
					,Client.class.getName() + ",true,1;" //,
					);*/
			//InterfaceClient iC = new InterfaceClient(sema, 512, 256);
			//iC.setVisible(true);
		}
		/*if(arg0.getSource().equals(valider)){
			
			String s = langBox.getSelectedItem().toString();
			//setLang(s);
			I18nList.infoLang = s;
			System.out.println("actionPerdormed : "+ s);
			
			this.dispose();
			I18nList i18nList = new I18nList();	
			setChoix(4);
			sema.release();
		}*/
	}
	
	public String getLang(){
		 String valeur = langBox.getSelectedItem().toString();
	     System.out.println("getLang a recuperer : "+(String)valeur);
	   	return valeur;
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	public int getChoix() {
		return choix;
	}
	public void setChoix(int choix) {
		this.choix = choix;
	}
}
